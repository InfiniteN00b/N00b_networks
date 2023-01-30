#include "ns3/core-module.h" //biuld for ns3, architecture of ns3
#include "ns3/network-module.h" //creating the topology (ring, bus, mesh etc...)
#include "ns3/internet-module.h" //to implement tcpip protocol stack interface
#include "ns3/point-to-point-module.h" //creating point-to-point channel between 2 nodes
#include "ns3/applications-module.h" //to support execution of any command/application
using namespace ns3;
NS_LOG_COMPONENT_DEFINE("InfiniteN00bFX");
int main(int argc, char *argv[]){
  CommandLine cmd;
  cmd.Parse(argc, argv);
  LogComponentEnable("UdpEchoClientApplication", LOG_LEVEL_INFO);
  LogComponentEnable("UdpEchoServerApplication", LOG_LEVEL_INFO);
  NodeContainer nodes;
  nodes.Create(3);
  InternetStackHelper stack;
  stack.Install(nodes);
  PointToPointHelper PTP; //create link
  PTP.SetDeviceAttribute("DataRate", StringValue("5Mbps"));
  PTP.SetChannelAttribute("Delay", StringValue("2ms"));
  NetDeviceContainer device; //link the node with the channel, acts as Network interface card
  device = PTP.Install(nodes.Get(0), nodes.Get(1));


  Ipv4AddressHelper address;
  address.SetBase("10.1.1.0", "255.255.255.0");//2 ip addresses
  //1st ip addr : network ip addr
  //2nd ip addr : subnet mask address (size of the network -> number of nodes to be implemented)
  Ipv4InterfaceContainer interfaces = address.Assign(device); //set address to the nodes created


  device = PTP.Install(nodes.Get(1), nodes.Get(2));
  address.SetBase("10.1.2.0", "255.255.255.0");
  interfaces = address.Assign(device); //set address to the nodes created


  Ptr<RateErrorModel> em = CreateObject<RateErrorModel> ();
  em->SetAttribute("ErrorRate", DoubleValue (0.00002));
  device.Get(1) -> SetAttribute("ReceiveErrorModel", PointerValue (em));


  Ipv4GlobalRoutingHelper::PopulateRoutingTables();
  UdpEchoServerHelper echoServer(9);//msg transfer using UDP
  //9 is port number for echo -> an application
  ApplicationContainer serverApp1 = echoServer.Install(nodes.Get(2));  //setting node 1 as server
  //set time for which server is active
  serverApp1.Start(Seconds(1.0));
  serverApp1.Stop(Seconds(10.0));
  UdpEchoClientHelper echoClient1(interfaces.GetAddress(1), 9); //tell client which is the server
  echoClient1.SetAttribute("MaxPackets", UintegerValue(10)); //max number of packets
  echoClient1.SetAttribute("Interval", TimeValue(Seconds(1.0))); //in ms
  echoClient1.SetAttribute("PacketSize", UintegerValue(1024)); //bits


  ApplicationContainer clientApp1 = echoClient1.Install(nodes.Get(0)); //setting node 0 as client
  //set time for which server is active
  clientApp1.Start(Seconds(2.0));
  clientApp1.Stop(Seconds(10.0));


  AsciiTraceHelper ascii;
  PTP.EnableAsciiAll(ascii.CreateFileStream("infinity1.tr"));
  Simulator :: Run();
  Simulator :: Destroy();
  return 0;
}
// ./waf --run scratch/sample --vis
