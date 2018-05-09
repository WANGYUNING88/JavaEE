package 五子棋;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sound.midi.Receiver;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class Server_frame extends JFrame {
	public static boolean isstart = false;
	public static boolean clientconnect=false;
	public static Socket socket;
	Server_qipan qipan;
	Server_talk talk;
	public static ServerSocket server;

	public Server_frame(){
		qipan = new Server_qipan();
		talk = new Server_talk();
		this.setSize(1000, 560);
		this.setTitle("五子棋_服务器");
		this.add(qipan, BorderLayout.CENTER);
		this.add(talk, BorderLayout.WEST);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu gameMenu = new JMenu("游戏(G)"); // 创建游戏菜单
		JMenu serMenu = new JMenu("帮助(H)"); // 
		gameMenu.setMnemonic('G');
		serMenu.setMnemonic('G');
		// ������Ϸ�Ӳ˵�������
		JMenuItem connect_ser = new JMenuItem("开启服务连接服务器", 'F');
		connect_ser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_MASK));
		connect_ser.addActionListener(new AbstractAction("开启服务器") {
			public void actionPerformed(ActionEvent event) {
				boolean flag = Serverstart();
				if (flag == false) {
					JOptionPane.showMessageDialog(null, "开启失败", "错误",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {

					// 服务器开启子线程
					Recive_thred rth = new Recive_thred(socket,
							Server_talk.chantAear,qipan);
					// Server_panel_center_qipan.control=rth.control();
					// System.out.println("Server_panel_center_qipan="+Server_panel_center_qipan.control);
					rth.start();
				}

			}
		});
		JMenuItem waitclient = new JMenuItem("退出", 'E');
		waitclient.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		waitclient.addActionListener(new AbstractAction("退出") {
			public void actionPerformed(ActionEvent event) {
				try {
					socket = server.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // �ȴ��ͻ�������
			}
		});
		JMenuItem exitItem = new JMenuItem("退出", 'E');
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		exitItem.addActionListener(new AbstractAction("退出") {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		gameMenu.add(connect_ser);
		gameMenu.addSeparator();
		gameMenu.add(waitclient);
		gameMenu.addSeparator();
		gameMenu.add(exitItem);
		menuBar.add(gameMenu);
		menuBar.add(serMenu);
		this.setVisible(true);
	}

	/*
	 *开启服务器,并且等待客户端连接
	 */
	public static boolean Serverstart() {
		try {
			// 开启服务器
			server = new ServerSocket(6666);
			isstart = true; //表示服务器开启了额
			 // 发送客户端用户基本信息(用户名和ip地址)
            JOptionPane.showMessageDialog(null, "服务器开始于:"
                    + server.getInetAddress().getLocalHost() + ":"
                    + server.getLocalPort());
            Server_talk.chantAear.append("\n" + "服务器开始于:"
                    + server.getInetAddress().getLocalHost() + ":"
                    + server.getLocalPort() + "\n");
			socket = server.accept();
			return isstart;
		} catch (Exception e) {
			isstart = false;// 未连接上
			return false;
		}
	}

}
