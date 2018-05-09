package 五子棋;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Send_message {
	Socket socket;
	String message;

	public Send_message(String message, Socket socket) {
		this.message = message;
		this.socket = socket;
	}

	public void send(String message, Socket socket) {
		OutputStream os;
		try {
			os = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			System.out.println("send������Ҫ���͸��Է�����Ϣ��"+message);	
			//����������������ȥ
			bw.write(message);
			//����һ��
			bw.newLine();
			bw.flush(); // ��ջ���
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
