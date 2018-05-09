package 五子棋;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;

public class Client_frame extends JFrame{
	public static Client_qipan qipan;
	Cilent_talk talk;
	public Client_frame(){
		qipan=new Client_qipan(); 
		talk=new Cilent_talk();
		this.setSize(1150, 550);
		//���ùر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���þ���
	//	this.setLocationRelativeTo(null);
		this.setTitle("������_�ͻ���");
		this.add(qipan,BorderLayout.CENTER); 
		this.add(talk,BorderLayout.WEST);
		
		this.setVisible(true);
	}
}
