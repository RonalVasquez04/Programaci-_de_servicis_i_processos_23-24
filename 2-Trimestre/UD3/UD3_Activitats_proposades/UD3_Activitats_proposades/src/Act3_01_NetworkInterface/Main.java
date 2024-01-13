package Act3_01_NetworkInterface;
import java.net.*;
import java.util.Enumeration;

public class Main {

	public static void main(String[] args) throws SocketException {
		int i = 1;
		
		//obtencio de l'enumeracio d'interficies
		Enumeration<NetworkInterface> netInterfaceList = NetworkInterface.getNetworkInterfaces();

		//per a cada element en la llista d'interficies...
		while (netInterfaceList.hasMoreElements()) {
			//s'obté l'interficie
			NetworkInterface inf = netInterfaceList.nextElement();
			//es mostra el nom
			System.out.println("Interficie "+ i);
			System.out.println("\tNom interficie "+ i +" : " + inf.getDisplayName());
			// i la MAC de l'interficie
			byte[] mac = inf.getHardwareAddress();

			//formatem els bytes en un String
			if (mac != null) {
				StringBuilder sb = new StringBuilder(18);	//string de 18 chars buit
				for (byte b : mac) {
					if (sb.length() > 0)
						sb.append(':');
					sb.append(String.format("%02x", b));	//conversió del byte a hexadecimal
				}//for
				System.out.println("\tMAC interficie "+ i +" : " + sb.toString());
			}//if
			System.out.println("---");
			i++;
		}
	
	}//main
}//class
