package com.insa.ghome;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class ReceptionThread implements Runnable {
	protected BufferedReader in;
	protected BlockingQueue<String> receivedQueue;
	
	public ReceptionThread(BufferedReader input, BlockingQueue<String> queueIn)
	{
		in = input;
		receivedQueue = queueIn;
	}
	
	@Override
	public void run() {
		String message;
		while(true)
		{
			try {
				message = in.readLine();
				System.out.println(message);
				try {
					receivedQueue.put(message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Lost Connection");
			}
		}
	}
}
