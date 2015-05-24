package fourth_task;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;

public class ClientG {

	private JFrame frame; //рамка
	private JTextField textForSending; // текст
	private Client client; 
	private JTextField txtName;
	private TextArea textArea;
	private JTextField textNameOfRecipient;
	private volatile boolean in;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientG window = new ClientG();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientG() {
		initialize();
	}

	public void startListening() {
		new Thread(new Runnable() {
			public void run() {
				while (in) {
					byte[] incomingData = new byte[1024];
					DatagramPacket p = new DatagramPacket(incomingData,
							incomingData.length);
					System.out.println("I'm listening");
					try {
						client.recieveMessage(p);
					} catch (IOException e) {
						System.err.println("Can't get the message" + e);
					}
					System.out.println("=====>");
					Message msg = null;
					try {
						msg = client.getMessageFromBytes(p.getData());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					textArea.append(msg.toString() + '\n');
				}
			}
		}).start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		in = false;
		frame = new JFrame();
		frame.setBounds(100, 100, 470, 320);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) { // закрываем окно чата - прощаемся
				if (in) { 
					Message buyServer = client.formGoodbuyMessage();
					try {
						client.sendMessage(buyServer);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					in = false;
				}
				System.out.println("Buy");
				e.getWindow().dispose();
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panelForMessages = new JPanel();
		panelForMessages.setBounds(5, 5, 330, 275);
		frame.getContentPane().add(panelForMessages);
		panelForMessages.setLayout(null);

		JPanel panelForGetting = new JPanel();
		panelForGetting.setBounds(0, 0, 330, 201);
		panelForMessages.add(panelForGetting);
		panelForGetting.setLayout(new CardLayout(0, 0));

		textArea = new TextArea();
		textArea.setEditable(false);
		panelForGetting.add(textArea, "name_32238910945477");

		JPanel panelForSending = new JPanel();
		panelForSending.setBounds(-3, 213, 336, 61);
		panelForMessages.add(panelForSending);
		panelForSending.setLayout(new CardLayout(0, 0));

		textForSending = new JTextField();
		panelForSending.add(textForSending, "name_10163533542484");
		textForSending.setColumns(10);

		JPanel panelForButtons = new JPanel();
		panelForButtons.setBounds(342, 0, 106, 275);
		frame.getContentPane().add(panelForButtons);
		panelForButtons.setLayout(null);

		JButton btnSend = new JButton("Send all");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//реакция на нажатие
				Message msg = client.formMessage(textForSending.getText(),
						"all");							//генерация сообщения из введенного текста
				textForSending.setText("");
				try {
					client.sendMessage(msg);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSend.setBounds(0, 200, 110, 25);
		panelForButtons.add(btnSend);

		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!in) {
						client = new Client(txtName.getText()); //созд нов. клиента
						Message helloServer = client.formWelcomingMessage(); 
						client.sendMessage(helloServer); //приветствие от сервера
						in = true;
						btnLogin.setText("logout"); //меняем кнопку
						startListening();
						textForSending.setText("");
					} else { //если уже залогинен, выходим и прощаемся:(
						Message buyServer = client.formGoodbuyMessage(); 
						client.sendMessage(buyServer);
						in = false;
						btnLogin.setText("login");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(0, 36, 110, 25);
		panelForButtons.add(btnLogin);

		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(0, 12, 106, 19);
		panelForButtons.add(txtName);
		txtName.setColumns(10);

		textNameOfRecipient = new JTextField();
		textNameOfRecipient.setBounds(0, 232, 106, 19);
		panelForButtons.add(textNameOfRecipient);
		textNameOfRecipient.setColumns(10);

		JButton btnSendForHim = new JButton("Send to him");
		btnSendForHim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message msg = client.formMessage(textForSending.getText(),
						textNameOfRecipient.getText());
				try {
					client.sendMessage(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSendForHim.setBounds(0, 252, 110, 25);
		panelForButtons.add(btnSendForHim);
	}
}
