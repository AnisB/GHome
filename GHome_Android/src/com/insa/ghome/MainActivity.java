package com.insa.ghome;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	protected TextView coucou = null;
	protected EditText ip = null;
	protected EditText login = null;
	protected EditText password = null;
	
	protected EditText messageField = null;
	
	
	protected BlockingQueue<String> inQueue;
	protected BlockingQueue<String> outQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		coucou = (TextView) findViewById(R.id.textView1);
		ip = (EditText) findViewById(R.id.editTextIP);
		login = (EditText) findViewById(R.id.editTextLogin);
		password = (EditText) findViewById(R.id.editTextPW);
		
		messageField = (EditText) findViewById(R.id.messageField);

		coucou.setText(R.string.hello_world);
		final Button bConnect = (Button) findViewById(R.id.buttonConnect);

		inQueue = new LinkedBlockingQueue<String>();
		outQueue = new LinkedBlockingQueue<String>();
		bConnect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String serverAddress = ip.getText().toString();
				coucou.setText(serverAddress);

				Thread emission = new Thread(new EmissionThread(serverAddress,
						outQueue, inQueue));

				emission.start();

				String identificationMessage = "C "
						+ login.getText().toString() + " "
						+ password.getText().toString();
				try {
					outQueue.put(identificationMessage);
					String answer = inQueue.poll(5000, TimeUnit.MILLISECONDS);
					// TODO change hard-written values
					if (answer == null) {
						// TODO : notify timeout to user
					} else {
						if (answer.compareTo("C1") == 0) {
							setContentView(R.layout.main_window);
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		final Button bSend = (Button) findViewById(R.id.buttonSend);
		bSend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String message = messageField.getText().toString();
				
				
			}
		});
		
		// setContentView(R.layout.activity_main);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.activity_main, menu);
	// return true;
	// }

}
