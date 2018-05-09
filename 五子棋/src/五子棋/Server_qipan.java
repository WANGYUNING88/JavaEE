package 五子棋;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Server_qipan extends JPanel {
	Image board, black, white;
	int hgex, hgey; // 棋子坐标的变量
	int bgex, bgey; // 棋子坐标的变量
	int cou = 0; // 表示连着棋子的数量

	Send_message sendmessage; // ���͵ķ���
	String message; // Ҫ���͵���Ϣ
	public static String control;
	public static boolean isgamestart = false; // 
	public static boolean myselfok = false; // baiqi表示自己准备好了
	public static boolean duifangok = false; // 
	public static boolean ismyselfxiaqi = false; //
	public static boolean isduifangxiaqi = true; // 
	// Ϊtrue��ʾ�Է����ˣ�false��ʾû��
	private MouseEvent xiaqie;
	public static ArrayList<String> myself_list = new ArrayList<String>(); // 我
	public static ArrayList<String> duifang_list = new ArrayList<String>(); // 对方

	public Server_qipan() {

		sendmessage = new Send_message(message, Cilent_talk.socket);

		this.setLayout(null); // ����
		JButton ks = new JButton("开始/暂停");
		ks.setBounds(10, 460, 100, 25);
		this.add(ks);
		JButton rs = new JButton("认输");
		rs.setBounds(115, 460, 80, 25);
		this.add(rs);
		JButton ht = new JButton("后退");
		ht.setBounds(200, 460, 80, 25);
		this.add(ht);
		JButton cxks = new JButton("重新开始ʼ");
		cxks.setBounds(290, 460, 100, 25);
		this.add(cxks);

		hgex = 0;
		hgey = 0;
		bgex = 0;
		bgey = 0;
		try {
			board = ImageIO.read(new File("img/board.gif"));
			black = ImageIO.read(new File("img/black.gif"));
			white = ImageIO.read(new File("img/white.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//  鼠标监听器，下棋位置
		MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Server_frame.isstart) {
//					System.out.println("\n-----\nisgamestart=" + isgamestart
//							+ "\nismyselfxiaqi=" + ismyselfxiaqi
//							+ "\nisduifangxiaqi=" + isduifangxiaqi
//							+ "\nismyselfok=" + myselfok + "\nduifangok="
//							+ duifangok);
					if (isgamestart) {
						xiaqie = e; // 把鼠标点的位置赋值给全局变量
						getmouse(xiaqie);
					} else {
						Server_talk.chantAear
								.append("\n---游戏还有准备好---");
					}
				} else {
					JOptionPane.showMessageDialog(null, "111");
				}
			}
		};
		// 鼠标监听器--------------开始/暂停
		MouseAdapter start = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				// System.out.println("kaishi��갴��ȥ��");
				if (Server_frame.isstart) {
					myselfok = true; // ��ʾ�Լ�׼����
					// ������Ϣ���Է�˵�Լ�׼������
					message = "ANNIU" + "-" + "SERVER" + "-" + "START";
					sendmessage.send(message, Server_frame.socket);
					System.out.println("duifangok=" + duifangok + "myselfok="
							+ myselfok);
					if (!duifangok) {
						Server_talk.chantAear
								.append("\n---对方还没准备好---");
					} else{
						isgamestart = true;
						Server_talk.chantAear
								.append("\n---双方准备好开始---");
					}

				} else {
					Server_talk.chantAear
							.append("\n---游戏没开始---");
				}
			}
		};
		MouseAdapter renshu = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Server_frame.isstart) {
					if (isgamestart) {
						// System.out.println("renshu��갴��ȥ��");
						myself_list.clear();
						duifang_list.clear();
						message = "ANNIU" + "-" + "SERVER" + "-" + "TOUXIANG";
						sendmessage.send(message, Server_frame.socket);
						repaint();
					} else {
						Server_talk.chantAear.append("\n---认输---");
					}
				} else {
					JOptionPane.showMessageDialog(null, "游戏未开始");
				}
			}
		};
		// ����
		MouseAdapter houtui = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Server_frame.isstart) {
					if (isgamestart) {
						if (!ismyselfxiaqi && isduifangxiaqi) {
							Server_talk.chantAear
									.append("\n---ϵͳ��Ϣ�����Լ������ʱ�򣬲��ܻ���---");
						} else {

							ismyselfxiaqi = false;
							isduifangxiaqi = true;
							myself_list.remove(myself_list.get(myself_list
									.size() - 1));// ����
							Server_talk.chantAear
									.append("\n---ϵͳ��Ϣ���Է�����һ���壡---");
							message = "ANNIU" + "-" + "SERVER" + "-" + "HUIQI";
							sendmessage.send(message, Server_frame.socket);
							repaint();
						}
					} else {
						Server_talk.chantAear.append("\n---ϵͳ��Ϣ����Ϸ��û��ʼ---");
					}
				} else {
					JOptionPane.showMessageDialog(null, "���ȿ�������������");
				}

			}
		};
		// ���¿�ʼ
		MouseAdapter chongxinkaishi = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if (Server_frame.isstart) {
					if (isgamestart) {
						myself_list.clear();
						duifang_list.clear();
						message = "ANNIU" + "-" + "SERVER" + "-"
								+ "CHONGXINLAI";
						sendmessage.send(message, Server_frame.socket);
						repaint();
					} else {
						Server_talk.chantAear.append("\n---ϵͳ��Ϣ����Ϸ��û��ʼ---");
					}
				} else {
					JOptionPane.showMessageDialog(null, "���ȿ�������������");
				}
			}
		};
		this.addMouseListener(mouse);
		cxks.addMouseListener(chongxinkaishi);
		ks.addMouseListener(start); // ��ʼ��ť���������
		rs.addMouseListener(renshu); // ���䰴ť���������
		ht.addMouseListener(houtui); // ���˰�ť���������

	}

	/*
	 * ��ȡ���λ�� �������λ�õ���������
	 */
	public void getmouse(MouseEvent e) {
		if (!ismyselfxiaqi && isduifangxiaqi) {
			// isgamestart��ʾ��Ϸ��ʼ��������������������
			// if(isgamestart){
			int x = e.getX();
			int y = e.getY();
			// ��ȥ�߾࣬���Ը��ӵĿ�ȣ�ȡ�����õ��ڼ�����
			hgex = (x - 18) / 25;
			hgey = (y - 18) / 25;
			if ((x - 18) % 25 > 12)
				hgex = hgex + 1;
			if ((y - 18) % 25 > 12)
				hgey = hgey + 1;
			// �Ѹ��ӵ�x�����y�������ַ����ö������ӣ����浽list����
			if (!myself_list.contains(hgex + "," + hgey)
					&& !duifang_list.contains(hgex + "," + hgey)) {
				myself_list.add(hgex + "," + hgey);
				// �Լ������˾Ͳ����£�Ҫ�ȶԷ�����֮�������
				ismyselfxiaqi = true; // �Լ�Ϊfalse��ʾ�Լ�û�£����Լ���
				isduifangxiaqi = false; // �Է�Ϊtrue����ʾ�Է�������
				// ���µ����ӷ��͸��Է�
				message = "XIAQI" + "-" + hgex + "," + hgey + "-" + "SERVER";
				System.out.println("�����������꣺" + message);
				sendmessage.send(message, Server_frame.socket);
			}
			repaint();
			// if (myself_list.size() >= 5) {
			heipanduan();
			// }
		}
		// if (ismyselfxiaqi && !isduifangxiaqi) {
		// JOptionPane.showMessageDialog(null, "��ȴ��Է����꣬�ڼ�������");
		// }
	}

	/*
	 * 
	 * ������
	 */
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
		// �����ȡ�����λ��,������
		for (int i = 0; i < myself_list.size(); i++) {
			String s = myself_list.get(i); // ��ü�������ĵ����ַ���
			String[] a = s.split(",");
			hgex = Integer.parseInt(a[0]);
			hgey = Integer.parseInt(a[1]);
			g.drawImage(black, hgex * 25 + 18 - 12, hgey * 25 + 18 - 12, null);
		}
		// �����ȡ�����λ�ã�������
		for (int i = 0; i < duifang_list.size(); i++) {
			String s = duifang_list.get(i); // ��ü�������ĵ����ַ���
			String[] b = s.split(",");
			bgex = Integer.parseInt(b[0]);
			bgey = Integer.parseInt(b[1]);
			g.drawImage(white, bgex * 25 + 18 - 12, bgey * 25 + 18 - 12, null);
		}
	}

	// ��Ӯ�жϵķ���,���ж�
	public void heipanduan() {
		// if (h_list.size() > 5) {// �������ٵ������֮���ִ��
		// f�ж��ǰ��廹�Ǻ���,true��ʾ����,false��ʾ����
		// �����ж���Ӯ
		for (int i = 0; i < myself_list.size(); i++) {
			cou = 0; // ���ж�ÿһ�����ӵ�ʱ�򣬶�Ҫ��cou��ֵΪ0
			String s = myself_list.get(i); // ��ü�������ĵ����ַ���
			String[] a = s.split(",");
			hgex = Integer.parseInt(a[0]);
			int tempx = hgex;
			hgey = Integer.parseInt(a[1]);
			int tempy = hgey;
			// �����ж�
			// �����ĳ����x����+1��Y���䣬��ʾ�����ж���û����������
			for (int j = 0; j < 4; j++) {
				hgex++;
				if (myself_list.contains(hgex + "," + hgey)) {
					cou++;
				}
			}
			// �����ж�
			if (cou < 4) {
				hgex = tempx;
				hgey = tempy;
				cou = 0;
				for (int j = 0; j < 4; j++) {
					hgey++;
					if (myself_list.contains(hgex + "," + hgey))
						cou++;
				}
			}
			// ���Ϸ����ж�
			if (cou < 4) { // С��4��ʾ����û�����ӣ��Ͱ�couֵ��Ϊ0�����¿�ʼ
				cou = 0;
				hgex = tempx;
				hgey = tempy;
				for (int j = 0; j < 4; j++) {
					hgey--;
					hgex++;
					if (myself_list.contains(hgex + "," + hgey))
						cou++;
				}
			}
			// �����ж�
			if (cou < 4) {
				cou = 0;
				hgex = tempx;
				hgey = tempy;
				for (int j = 0; j < 4; j++) {
					hgey++;
					hgex++;
					if (myself_list.contains(hgex + "," + hgey))
						cou++;
				}
			}
			if (cou == 4) {
				JOptionPane.showMessageDialog(null, "��ϲ����Ӯ��");
				isgamestart = false;
				// ���Է������Լ�Ӯ����Ϣ

				message = "XIAQI" + "-" + "WIN" + "-" + "SERVER";
				sendmessage.send(message, Server_frame.socket);

			}
		}
	}

}
