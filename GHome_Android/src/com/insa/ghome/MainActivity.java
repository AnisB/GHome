package com.insa.ghome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
//import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView coucou = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        coucou = (TextView)findViewById(R.id.textView1);
        coucou.setText(R.string.hello_world);
        final Button bConnect = (Button)findViewById(R.id.buttonConnect);
        bConnect.setOnClickListener(new View.OnClickListener() {
        	
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				coucou.setText("on a clique");
				Socket socket;
				BufferedReader in;
				PrintWriter out; 
				
				try   
		        {  
//		            socket = new Socket("if219-09.insa-lyon.fr", 2011); 
					socket = new Socket("134.214.105.28", 8880);
		              
		            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
		            out = new PrintWriter(socket.getOutputStream(), true);  
		            BufferedReader line = new BufferedReader(new InputStreamReader(System.in));  
		  
//		            out.println(line.readLine());  
		            out.println("A");  
		              
		            line.close();  
		            out.close();  
		            in.close();  
		              
		            socket.close();  
		              
		        } catch (IOException e) {  
		        }  
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
