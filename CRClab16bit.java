import java.util.*;
import java.util.Random;

public class CRClab16bit{
	void mod2div(int a[], int k){
		int gen[] = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1};
		int cnt = 0;
		for(int i = 0; i < k; i++){
			if(a[i] == gen[0]){
				for(int j = i; j < 17; j++)
					a[j] = a[j] ^ gen[cnt++];
				cnt = 0;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter length of data: ");
		int len = sc.nextInt();
		int data[] = new int[100];
		int datac[] = new int[100];

		int flag = 0, actLen;

		CRClab16bit obj = new CRClab16bit();

		System.out.println("Enter data bits: ");
		for(int i = 0; i < len; i++)
			data[i] = sc.nextInt();
		for(int i = 0; i < 16; i++)
			data[len++] = 0;
		actLen = len - 16;
		for(int i = 0; i < len; i++)
			datac[i] = data[i];
		obj.mod2div(data, actLen);
		for(int i = 0; i < len; i++)
			data[i] = data[i] ^ datac[i];
		System.out.println("Data transmmitted is: ");
		for(int i = 0; i < len; i++)
			System.out.print(data[i] + " ");
		System.out.println();

		System.out.println("Enter message received: ");
		for(int i = 0; i < len; i++)
			data[i] = sc.nextInt();
		obj.mod2div(data, actLen);

		for(int i = 0; i < len; i++)
			if(data[i] != 0){
				flag = 1;
				break;
			}

		if(flag == 1) System.out.println("Received message corrupted");
		else System.out.println("Message received without any error");

	}
}