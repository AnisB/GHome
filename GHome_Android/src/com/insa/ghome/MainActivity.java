package com.insa.ghome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView coucou = null;
	private EditText log = null;
	private EditText ip = null;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        coucou = (TextView)findViewById(R.id.textView1);
        log = (EditText)findViewById(R.id.log);
        ip = (EditText)findViewById(R.id.editTextIP);

        coucou.setText(R.string.hello_world);
        final Button bConnect = (Button)findViewById(R.id.buttonConnect);
        bConnect.setOnClickListener(new View.OnClickListener() {
        	
			
			@Override
			public void onClick(View arg0) {
				String serverAddress = ip.getText().toString();
				log.append(serverAddress);
				
	            Thread emission = new Thread(new EmissionThread(serverAddress));
				
	            emission.start();
			}
		});
//        setContentView(R.layout.activity_main);
    }
    

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
    
}
