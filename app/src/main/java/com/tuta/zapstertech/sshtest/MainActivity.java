package com.tuta.zapstertech.sshtest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
public String msg;
Button sendBtn;
EditText Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBtn=findViewById(R.id.send);
        Name=findViewById(R.id.name);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send sendcode=new send();
                msg=Name.getText().toString();
                sendcode.execute();
            }
        });
    }
    private class send extends AsyncTask<Void,Void,Void>{
        Socket s;
        PrintWriter pw;
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                s=new Socket("192.168.43.140",8000);
                pw=new PrintWriter(s.getOutputStream());
                pw.write(msg);
                pw.flush();
                pw.close();
                s.close();

            }catch(UnknownHostException e){
                System.out.println("Fail");
                e.printStackTrace();
            }catch(IOException e){
                System.out.println("Fail");
                e.printStackTrace();
            }
            return null;
        }
    }
}
