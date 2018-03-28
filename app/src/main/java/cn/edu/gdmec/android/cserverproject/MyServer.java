package cn.edu.gdmec.android.cserverproject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author:JRainbow Chen
 * @date��2018��3��21�� @title:
 **/

public class MyServer {
	// ���屣������socket��arrarylist
	public static ArrayList<Socket> socketList = new ArrayList<Socket>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ServerSocket ss;
		try {
			ss = new ServerSocket(2008);
			while (true) {
				// ���д����������һֱ�ȴ��ͻ�������
				Socket s = ss.accept();
				socketList.add(s);
				// ÿ���ͻ������Ӻ�����һ��ServerThread�߳�Ϊ�Ŀͻ��˷���
				new Thread(new ServerThread(s)).start();
			}
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
