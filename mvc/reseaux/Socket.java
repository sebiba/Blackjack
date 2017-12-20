package reseaux;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Socket {
	
	public static void main(String[] zero) {
		
		InetAddress Adresse ;

		try {

			Adresse = InetAddress.getLocalHost();
			System.out.println("L'adresse locale est : "+Adresse ); 
			
		
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
	}

}
