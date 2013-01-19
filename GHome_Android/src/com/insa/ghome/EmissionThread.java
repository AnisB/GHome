package com.insa.ghome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EmissionThread implements Runnable {

	String address;
	
	public EmissionThread(String serverAddress)
	{
		address = serverAddress;
	}
	
	@Override
	public void run() {
		Socket socket;
		BufferedReader in;
		PrintWriter out;

		InetAddress localhost;
		try {
			localhost = InetAddress.getByName("localhost");
			System.out.println(localhost.getHostAddress());
			InetAddress serverAddress = InetAddress.getByName(address);
			socket = new Socket(serverAddress, 4500);//, localhost, 9000);
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
            out = new PrintWriter(socket.getOutputStream());
            
            Thread reception = new Thread(new ReceptionThread(in));
            reception.start();
            
            out.println("C patate");
            out.flush();
            
            System.out.println("Message sent");
		}
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
