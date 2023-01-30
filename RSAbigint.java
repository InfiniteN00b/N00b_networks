import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSAbigint {
    static BigInteger p, q, n, phi, e, d;
    static SecureRandom secureRandom;
    static int bitLength = 64;

    static String encrypt(String msg){
        return new BigInteger(msg.getBytes()).modPow(e, n).toString();
    }

    static String decrypt(String msg){
        return new String(new BigInteger(msg).modPow(d, n).toByteArray());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        secureRandom = new SecureRandom();

        p = BigInteger.probablePrime(bitLength, secureRandom);
        q = BigInteger.probablePrime(bitLength, secureRandom);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.probablePrime(bitLength / 2, secureRandom);

        while(e.gcd(phi).compareTo(BigInteger.ONE) != 0 && e.compareTo(phi) < 0)
            e = e.add(BigInteger.ONE);
        d = e.modInverse(phi);

        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("n: " + n);
        System.out.println("phi: " + phi);
        System.out.println("e: " + e);
        System.out.println("d: " + d);

        System.out.println("Enter message: ");
        String message = sc.nextLine();

        String encrypt = encrypt(message);
        System.out.println("Encrypted message: " + encrypt);

        String decrypt = decrypt(encrypt);
        System.out.println("Decrypted message: " + decrypt);
    }
}
