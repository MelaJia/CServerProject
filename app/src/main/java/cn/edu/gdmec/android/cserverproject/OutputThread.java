package cn.edu.gdmec.android.cserverproject;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by HP on 2018/3/22.
 */


public class OutputThread implements Runnable{
    //该线程负责处理的Socket
    private Socket s;
    private android.os.Handler handler;
    //该线程所处理的Socket对应的输入流
    BufferedReader br=null;

    public OutputThread(Socket s, Handler handler) throws IOException {
        this.s = s;
        this.handler = handler;
        br=new BufferedReader(new InputStreamReader(s.getInputStream()));
    }

    @Override
    public void run() {
        String content=null;
        //不断读取socket输入流中的内容
        try {
            while ((content=br.readLine())!=null){
                //每当读到来自服务器的数据之后，发送消息通知程序界面显示改数据
                Message msg=new Message();
                msg.what=0x123;
                msg.obj=content;
                handler.sendMessage(msg);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

