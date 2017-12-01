package reseaux;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;


public class Client {
	
	public static void main(String[] zero) {
		
		
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		String message;
		int cpt = 0;

		try {
		
				socket = new Socket(InetAddress.getByName("MBP-de-Humbert"),2009);	
		        System.out.println("Demande de connexion...");
		        while(cpt <10){
			        in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			        String message_distant = in.readLine();
			        System.out.println(message_distant);
			        if(message_distant.equals("deco")){break;}
				    
			        out = new PrintWriter(socket.getOutputStream());
					message=JOptionPane.showInputDialog("entrez un message a envoyer");
				    out.println(message);
				    out.flush();
				    if(message.equals("deco")){break;}
				    System.out.println(message);
				    cpt++;
		        }
		        socket.close();
		       
		}catch (UnknownHostException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}

