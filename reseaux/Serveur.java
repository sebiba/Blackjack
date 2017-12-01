package reseaux;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import java.io.PrintWriter;

	public class Serveur {
		
		public static void main(String[] zero) {
			
			ServerSocket socketserver  ;
			Socket socketduserveur ;
			BufferedReader in;
			PrintWriter out;
			String message;
			int cpt =0;
			try {
			
				socketserver = new ServerSocket(2009);
				System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
				socketduserveur = socketserver.accept(); 
			    System.out.println("Un zéro s'est connecté");
			    while(cpt<10){
				    out = new PrintWriter(socketduserveur.getOutputStream());
					message=JOptionPane.showInputDialog("entrez un message a envoyer");
				    out.println(message);
				    out.flush();
				    
			        in = new BufferedReader (new InputStreamReader (socketduserveur.getInputStream()));
			        String message_distant = in.readLine();
			        System.out.println(message_distant);
				    cpt++;
			    }
			        socketduserveur.close();
			        socketserver.close();
			        
			}catch (IOException e) {
				
				e.printStackTrace();
			}
		}

}
