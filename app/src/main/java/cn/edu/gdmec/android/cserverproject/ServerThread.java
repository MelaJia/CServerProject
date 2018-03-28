package cn.edu.gdmec.android.cserverproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
* @author:JRainbow Chen
* @date��2018��3��21��
* @title:
**/

public class ServerThread implements Runnable {
	
	//���嵱ǰ�߳��������Socket
	Socket s=null;
	//���߳��������Socket����Ӧ��������
	BufferedReader br=null;
	public ServerThread(Socket s) throws Exception{
		this.s=s;
		//��ʼ����Socket��Ӧ��������
		br=new BufferedReader(new InputStreamReader(s.getInputStream(),"utf-8"));
		System.out.println("��ã�"+br);
	}
	

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String content=null;
		//����ѭ�����ϵش�Socket�ж�ȡ�ͻ��˷��͹���������
		
				try {
					while((content=readFromClient())!=null){
						//����SocketList��ÿ��Socket
						//����ȡ����������ÿ��Socket����һ��
						for(Socket s:MyServer.socketList){
							OutputStream os;
					os = s.getOutputStream();
					os.write(("server have receied msg:"+content+"\n").getBytes("utf-8"));
					
				} 
				System.out.println(("msg from client:"+content+"\n"));
					}
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


	}

	/**
	 * �����ȡ�ͻ��˰����ݵķ���
	 * @return
	 */
	private String readFromClient() {
		// TODO Auto-generated method stub
	
		try {
			return br.readLine();
		}
		//��������쳣��������socket��Ӧ�Ŀͻ����Ѿ��ر�
		catch (IOException e) {
			// ɾ����Socket
			MyServer.socketList.remove(s);
			e.printStackTrace();
		}
		return null;
	}

}
