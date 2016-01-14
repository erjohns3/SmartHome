package com.example.androidclient;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	EditText text1;
	Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item);
		text1 = (EditText) findViewById(R.id.text1);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Thread t = new Thread() {
			public void run() {
				try {
					Socket s = new Socket("130.126.255.70", 5000);
					DataOutputStream dos = new DataOutputStream(
							s.getOutputStream());
					dos.writeUTF(text1.getText().toString());
					dos.flush();
					dos.close();
					s.close();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
		t.start();
		Toast.makeText(this, "This message sent", Toast.LENGTH_SHORT).show();
	}
}
