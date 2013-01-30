package com.insa.ghome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

public class EmissionThread implements Runnable {

	protected String address;
	protected BlockingQueue<String> messageQueue;
	protected BlockingQueue<String> receivedQueue;
	
	public EmissionThread(String serverAddress, BlockingQueue<String> queueOut, BlockingQueue<String> queueIn)
	{
		address = serverAddress;
		messageQueue = queueOut;
		receivedQueue = queueIn;
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
			socket = new Socket(serverAddress, 4500);

            System.out.println("connected");
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
            out = new PrintWriter(socket.getOutputStream());
             
            Thread reception = new Thread(new ReceptionThread(in, receivedQueue));
            reception.start();
            
            while( true ) {
            	String message;
            	
            	try {
					message = messageQueue.take();

	        		out.println(message);
	                out.flush();
	                System.out.println("Message sent : " + message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }
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
