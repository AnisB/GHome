package com.insa.ghome;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceptionThread implements Runnable {
	BufferedReader in;
	
	public ReceptionThread(BufferedReader input)
	{
		in = input;
	}
	
	@Override
	public void run() {
		String message;
		while(true)
		{
			try {
				message = in.readLine();
				System.out.println(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Lost Connection");
			}
		}
	}
}
