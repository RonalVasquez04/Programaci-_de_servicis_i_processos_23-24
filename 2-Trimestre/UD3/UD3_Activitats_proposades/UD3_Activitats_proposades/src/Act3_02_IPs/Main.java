package Act3_02_IPs;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) throws SocketException {
		try {
			int i = 1;
			// obtencio de l'enumeracio d'interficies
			Enumeration<NetworkInterface> netInterfaceList = NetworkInterface.getNetworkInterfaces();

			// per a cada element en la llista d'interficies...
			while (netInterfaceList.hasMoreElements()) {
				// s'obté l'interficie
				NetworkInterface nIinf = netInterfaceList.nextElement();
				// es mostra el nom
				System.out.println("Interficie " + i);
				System.out.println("\tNom interficie " + i + " : " + nIinf.getDisplayName());
				// i la MAC de l'interficie
				byte[] mac = nIinf.getHardwareAddress();

				// formatem els bytes en un String
				if (mac != null) {
					StringBuilder sb = new StringBuilder(18); // string de 18 chars buit
					for (byte b : mac) {
						if (sb.length() > 0)
							sb.append(':');
						sb.append(String.format("%02x", b)); // conversió del byte a hexadecimal
					} // for
					System.out.println("\tMAC interficie " + i + " : " + sb.toString());
				} // if

				ArrayList<InetAddress> ipList = new ArrayList<InetAddress>();
				
				// obtenim la llista d'adreces de l'interficie
				List<InterfaceAddress> infAddrList = nIinf.getInterfaceAddresses();
				
				for (InterfaceAddress infAddr : infAddrList) {
					//s'obté una adreça
					InetAddress addr = infAddr.getAddress();
					if (addr instanceof Inet4Address) {
						System.out.println("\t\t IPv4: " + addr.getHostAddress());
					} else {
						System.out.println("\t\t IPv6: " + addr.getHostAddress());
					}
					
					System.out.println("\t\t Màscara: " + infAddr.getNetworkPrefixLength() + " bits");
					
					if (addr instanceof Inet4Address) {
						System.out.println("\t\t Broadcast: " + infAddr.getBroadcast());
					}
					
					String accessible = (addr.isReachable(10)) ? "\t\t La IP és accessible" : "\t\t La IP NO és accessible";
					String privada = (addr.isSiteLocalAddress()) ? " , IP local" : " , IP privada";
					String loopback = (addr.isLoopbackAddress()) ? " i loopback" : "";
					System.out.println(accessible + privada + loopback + "\n\t\t---" );

				}
				
				System.out.println("---");
				i++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}// main
}// class
