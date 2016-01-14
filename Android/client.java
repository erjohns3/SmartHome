package com.example.eric.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    Socket clientSocket = null;
    BufferedReader in = null;
    PrintWriter out = null;
    String outletA = null, outletB = null, outletC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new ClientThread()).start();
    }

    public void connect(String select, String power) {

        try {

            out.println("OK");

            outletA = in.readLine();
            outletB = in.readLine();
            outletC = in.readLine();

            out.println(select);
            out.println(power);

            outletA = in.readLine();
            outletB = in.readLine();
            outletC = in.readLine();

        } catch (UnknownHostException uhe) {
            System.out.println("Unknown Host");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                InetAddress serverAddress = InetAddress.getByName("192.168.0.100");
                clientSocket = new Socket(serverAddress, 4444);
                in = new BufferedReader(new InputStreamReader(
                        clientSocket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        clientSocket.getOutputStream())), true);

                out.println("OK");

                outletA = in.readLine();
                outletB = in.readLine();
                outletC = in.readLine();

                out.println("A");
                out.println("1");

                outletA = in.readLine();
                outletB = in.readLine();
                outletC = in.readLine();

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void requestA1(View view) {
        connect("A", "1");
    }

    public void requestA0(View view) {
        connect("A", "0");
    }

    public void requestB1(View view) {
        connect("B", "1");
    }

    public void requestB0(View view) {
        connect("B", "0");
    }

    public void requestC1(View view) {
        connect("C", "1");
    }

    public void requestC0(View view) {
        connect("C", "0");
    }

    public void requestR(View view) {
        connect("", "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}