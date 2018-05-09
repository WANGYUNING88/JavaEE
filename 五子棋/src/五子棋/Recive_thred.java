package 五子棋;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.print.attribute.standard.Severity;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Recive_thred extends Thread {
	private Socket socket;
	TextArea chantAear;
	String who; // who=server��ʾ�Ǵӷ������Ǳ߷���������Ϣ
	String[] split;
	int xianxing = 1;
	JPanel jp = new JPanel();

	// public static ArrayList<String> list = new ArrayList<String>(); // ����

	public Recive_thred(Socket socket, TextArea chantAear, JPanel jp) {
		this.socket = socket;
		this.chantAear = chantAear;
		this.jp = jp;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		InputStream is;

		try {
			is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			while (true) {
				String msg = reader.readLine();
				System.out.println("recive�߳�����ܵ�����Ϣ" + msg);
				split = msg.split("-");
				// �������
				if (split[0].equals("CHANT")) {
					System.out.println(split[1]);
					if (split[1].equals("OK")) {
						chantAear.append("\n" + split[2]);
						Server_frame.clientconnect = true;
					} else {
						chantAear.append("\n" + split[1]);
					}

				} else if (split[0].equals("ANNIU")) {
					System.out.println("�������߳������水ť����control=" + split[1]);
					anniucontrol();
				}

				else if (split[0].equals("XIAQI"))//
				{
					System.out.println("����������");
					// �������
					who = split[2]; // ˭���İ�������
					System.out.println("�������߳��е�XIAQI�ж�");
					xiaqicontrol();

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 
	 * ��ť����
	 */
	public void anniucontrol() {

		if (split[1].equals("SERVER")) {
			// �յ��Ǵӷ�������������������Ϣ,Ҫ�Կͻ�����������޸�
			if (split[2].equals("START")) {
				System.out.println("�߳����棬�ͻ��˴ӷ������յ����¿�ʼ��ť");
				Client_qipan.duifangok = true;
				if (Client_qipan.myselfok) {
					Client_qipan.isgamestart = true;
					Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ������׼������������������---");
				}
				jp.repaint();
			}
			// ������Ͷ��
			if (split[2].equals("TOUXIANG")) {

				Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���Է��Ѿ�Ͷ������ϲ��Ӯ��---");
				JOptionPane.showMessageDialog(null, "yingle ,gameover");
				// ����������
				Client_qipan.myself_list.clear();
				Client_qipan.duifang_list.clear();
				Client_qipan.isgamestart = false;
				jp.repaint();

			}
			// ����������
			if (split[2].equals("HUIQI")) {
				Client_qipan.duifang_list.remove(Client_qipan.duifang_list
						.get(Client_qipan.duifang_list.size() - 1));// ����
				Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���Է�����һ���壡---");
				Client_qipan.isduifangxiaqi = false;
				Client_qipan.ismyselfxiaqi = true;
				jp.repaint();

			}
			// ���������¿�ʼ
			if (split[2].equals("CHONGXINLAI")) {
				Cilent_talk.chatAear.append("\n---ϵͳ��Ϣ���Է�Ҫ�����¿�ʼ!---");
				JOptionPane.showMessageDialog(null, "�Է�Ҫ��ʼ��ȷ�Ͼ�����������ݣ����¿�ʼ");
				Client_qipan.myself_list.clear();
				Client_qipan.duifang_list.clear();
				Client_qipan.isgamestart = false;
				Client_qipan.myselfok = true;
				Client_qipan.duifangok = false;
				jp.repaint();
			}

		} else if (split[1].equals("CLIENT")) {
			// �ͻ��˿�ʼ
			if (split[2].equals("START")) {
				Server_qipan.isduifangxiaqi = true;
				System.out.println("�߳����棬�������ӿͻ����յ����¿�ʼ��ť");
				Server_qipan.duifangok = true;
				if (Server_qipan.myselfok) {
					Server_qipan.isgamestart = true;
					Server_talk.chantAear.append("\n---ϵͳ��ʾ���Է�׼����������Ϸ��ʼ�ˣ������������---");
				}
				jp.repaint();
			}
			// �ͻ���Ͷ��
			if (split[2].equals("TOUXIANG")) {
				Server_talk.chantAear.append("\n---ϵͳ��Ϣ���Է��Ѿ�Ͷ������ϲ��Ӯ��---");
				JOptionPane.showMessageDialog(null, "yingle ,gameover");
				// ����������
				Server_qipan.myself_list.clear();
				Server_qipan.duifang_list.clear();
				Server_qipan.isgamestart = false;
				jp.repaint();

			}
			// �ͻ��˻���
			if (split[2].equals("HUIQI")) {
				if (Server_qipan.duifang_list.size() > 0)
					Server_qipan.duifang_list.remove(Server_qipan.duifang_list
							.get(Server_qipan.duifang_list.size() - 1));// ����
				Server_talk.chantAear.append("---ϵͳ��Ϣ���Է�����һ���壡---\n");
				Server_qipan.isduifangxiaqi = false;
				Server_qipan.ismyselfxiaqi = true;
				jp.repaint();
			}
			// �ͻ������¿�ʼ
			if (split[2].equals("CHONGXINLAI")) {
				Server_talk.chantAear.append("\n---ϵͳ��Ϣ���Է�Ҫ�����¿�ʼ!---");
				JOptionPane.showMessageDialog(null, "�Է�Ҫ��ʼ��ȷ�Ͼ�����������ݣ����¿�ʼ");
				Server_qipan.myself_list.clear();
				Server_qipan.duifang_list.clear();
				Server_qipan.isgamestart = false;
				Server_qipan.myselfok = false;
				Server_qipan.duifangok = true;
				jp.repaint();
			}
		}
	}

	/*
	 * 
	 * �������
	 */
	public void xiaqicontrol() {
		// ˭���£������ֵ�˭���ˣ���Ȼ�Ǵ�ֵ��������Ȼ�ǶԷ����ˣ�Ȼ����ӵ��Լ����Է����õ���������
		if (who.equals("SERVER")) {
			// ���� ���з��������͸��ͻ��ˣ��ͻ��˽��գ�����һ��Ҫ�ı���Ϣ
			if (split[1].equals("WIN")) {
				JOptionPane.showMessageDialog(null, "������Ŷ");
				Cilent_talk.chatAear
						.append("\n---ϵͳ��Ϣ---\n���Ѿ����ˣ�\nҪ���������Է�������ڵ�һ�ο�ʼ\n�������䣬�������¿�ʼ");
			} else {
				System.out.println("�ͻ��˽��յ���������split[1]=" + split[1]);
				String[] zuobiao = split[1].split(",");
				int tempx = Integer.parseInt(zuobiao[0]);
				int tempy = Integer.parseInt(zuobiao[1]);
				// �����������͹�����������������ֵ��ӵ��ͻ��������duifang��������
				if (!Client_qipan.duifang_list.contains(tempx + "," + tempy)
						&& !Client_qipan.myself_list.contains(tempx + ","
								+ tempy)) {
					// System.out.println("�����Ǹ��Է�������ֵ");
					Client_qipan.ismyselfxiaqi = false;
					Client_qipan.isduifangxiaqi = true;
					Client_qipan.duifang_list.add(tempx + "," + tempy);
					for (int i = 0; i < Client_qipan.duifang_list.size(); i++) {
						System.out.println();
						System.out.println("�Է�������������Ķ���"
								+ Client_qipan.duifang_list.get(i)); // ��ü�������ĵ����ַ���}
					}
				}
			}
			jp.repaint();
		} else if (who.equals("CLIENT")) {
			// ���� ���пͻ��˷��͸����������ͻ��˽��գ�����һ��Ҫ�ı���Ϣ
			if (split[1].equals("WIN")) {
				JOptionPane.showMessageDialog(null, "������Ŷ");
				Cilent_talk.chatAear
						.append("\n---ϵͳ��Ϣ---\n���Ѿ����ˣ�\nҪ���������Է�������ڵ�һ�ο�ʼ\n�������䣬�������¿�ʼ");
			} else {

				System.out.println("���������յ��ͻ��˵�split[1]=" + split[1]);
				String[] zuobiao = split[1].split(",");
				int tempx = Integer.parseInt(zuobiao[0]);
				int tempy = Integer.parseInt(zuobiao[1]);
				// ���ͻ��˷��͹�����������������ֵ��ӵ������������duifang��������
				if (!Server_qipan.myself_list.contains(tempx + "," + tempy)
						&& !Server_qipan.duifang_list.contains(tempx + ","
								+ tempy)) {

					Server_qipan.duifang_list.add(tempx + "," + tempy);
					Server_qipan.ismyselfxiaqi = false;
					Server_qipan.isduifangxiaqi = true;
				}
			}
		}
		jp.repaint();
	}
}
