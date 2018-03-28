package cn.edu.gdmec.android.cserverproject;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText input,show;
    Button ss;
    OutputStream os;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input=findViewById(R.id.input);
        show=findViewById(R.id.show);
        ss=findViewById(R.id.ss);
        Socket s;
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==0x123){
                    show.append("\n"+msg.obj.toString());
                }
            }
        };
        // 详见StrictMode文档
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());


        try {
            s=new Socket("192.168.199.209",2008);
            new Thread(new ClientThread(s,handler)).start();
            os=s.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.d("ll", "onClick: ok");
                    os.write((input.getText().toString()+"/r/n").getBytes("utf-8"));
                    //os.write((input.getText().toString()+"/r/n").getBytes("utf-8"));
                    input.setText("");
                  //  Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
