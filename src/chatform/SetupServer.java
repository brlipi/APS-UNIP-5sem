package chatform;

import connection.Server;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SetupServer extends JFrame {
	/**
	 * Server variables
	 */
	private static ServerSocket server;

	/**
	 *  Form variables
	 */
	private static final long serialVersionUID = 4998717362394143017L;
	private JPanel contentPane;
	private JTextField inputPort;
	private JButton btnOk;
	private JButton btnBack;
	private JPanel panelConfig;
	private JPanel panelStatus;
	private JLabel lblIp;
	private JLabel lblPort;
	private JLabel lblValueIP;
	private JLabel lblValuePort;
	private JButton btnStopConnection;
	private InetAddress inetAddress;
	private String hostaddress;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupServer frame = new SetupServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SetupServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		this.setLocationRelativeTo(null);
		this.setTitle("Aplicação de Conversa (Servidor)");
		
		panelConfig = new JPanel();
		contentPane.add(panelConfig, "panelConfig");
		panelConfig.setLayout(null);
		
		JLabel lblNumeroDaPorta = new JLabel("Número da porta:");
		lblNumeroDaPorta.setBounds(10, 35, 113, 18);
		panelConfig.add(lblNumeroDaPorta);
		lblNumeroDaPorta.setFont(new Font("Arial", Font.PLAIN, 15));
		
		inputPort = new JTextField();
		inputPort.setBounds(135, 35, 86, 20);
		panelConfig.add(inputPort);
		inputPort.setText("45454");
		inputPort.setColumns(10);
		
		btnOk = new JButton("Ok");
		btnOk.setBounds(90, 86, 73, 23);
		panelConfig.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				      		
				startServer();
			}
		});
		
		btnBack = new JButton("Voltar");
		btnBack.setBounds(177, 86, 73, 23);
		panelConfig.add(btnBack);
		
		panelStatus = new JPanel();
		contentPane.add(panelStatus, "panelStatus");
		panelStatus.setLayout(null);
		
		lblIp = new JLabel("IP:");
		lblIp.setFont(new Font("Arial", Font.PLAIN, 15));
		lblIp.setBounds(38, 11, 46, 14);
		panelStatus.add(lblIp);
		
		lblPort = new JLabel("Porta:");
		lblPort.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPort.setBounds(38, 36, 46, 14);
		panelStatus.add(lblPort);
		
		lblValueIP = new JLabel(); //TODO: JLabel(getIP())
		lblValueIP.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValueIP.setBounds(67, 11, 171, 14);
		panelStatus.add(lblValueIP);
		
		lblValuePort = new JLabel(); //TODO: JLabel(getPort())
		lblValuePort.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValuePort.setBounds(80, 36, 154, 14);
		panelStatus.add(lblValuePort);
		
		btnStopConnection = new JButton("Encerrar Conexão");
		btnStopConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				//TODO: Close connection. Maybe dispose() instead of exit() in case Connection can be reestablished
				System.exit(0);
			}
		});
		btnStopConnection.setBounds(94, 79, 144, 30);
		panelStatus.add(btnStopConnection);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetupServer.this.dispose();
				Start start = new Start();
				start.setVisible(true);
			}
		});
	}

	private void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(inputPort.getText()));
			new Thread(new Runnable() {
				@Override
				public void run() {		
					CardLayout c = (CardLayout)(contentPane.getLayout());
					c.show(contentPane, "panelStatus");
					try {
						inetAddress = InetAddress.getLocalHost();
						hostaddress = inetAddress.getHostAddress();
						lblValueIP.setText(hostaddress);
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					}					
					lblValuePort.setText(inputPort.getText());
					while (true) {
		                System.out.println("Waiting connection...");
		                try {
		                Socket connection = server.accept();
		                Thread serverThread = new Server(connection);
		                serverThread.start();
		                } catch (Exception e) {
		                	e.printStackTrace();
		                }		                
		            }
				}				
			}).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
