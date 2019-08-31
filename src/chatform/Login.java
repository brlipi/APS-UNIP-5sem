package chatform;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 948747724372712259L;
    private JPanel contentPane;
    private JTextField inputIP;
    private JTextField inputPort;
    private JTextField inputUser;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
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
    public Login() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 374, 277);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Aplicação de Conversa (Cliente)");

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Login.this.dispose();
                setFocusableWindowState(false);

                connect();
            }
        });

        btnLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        btnLogin.setBounds(5, 198, 355, 39);
        contentPane.add(btnLogin);

        JLabel lblServerIp = new JLabel("IP do servidor:");
        lblServerIp.setFont(new Font("Arial", Font.PLAIN, 15));
        lblServerIp.setBounds(5, 11, 151, 39);
        contentPane.add(lblServerIp);

        inputIP = new JTextField();
        inputIP.setText("127.0.0.1");
        inputIP.setFont(new Font("Arial", Font.PLAIN, 15));
        inputIP.setBounds(138, 15, 222, 30);
        contentPane.add(inputIP);
        inputIP.setColumns(10);

        JLabel lblPortaDoServidor = new JLabel("Porta do servidor:");
        lblPortaDoServidor.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPortaDoServidor.setBounds(5, 72, 151, 39);
        contentPane.add(lblPortaDoServidor);

        inputPort = new JTextField();
        inputPort.setText("45454");
        inputPort.setFont(new Font("Arial", Font.PLAIN, 15));
        inputPort.setColumns(10);
        inputPort.setBounds(138, 76, 222, 30);
        contentPane.add(inputPort);

        JLabel lblUsurio = new JLabel("Nome de usuário:");
        lblUsurio.setFont(new Font("Arial", Font.PLAIN, 15));
        lblUsurio.setBounds(5, 134, 151, 39);
        contentPane.add(lblUsurio);

        inputUser = new JTextField();
        inputUser.setText("unip1");
        inputUser.setFont(new Font("Arial", Font.PLAIN, 15));
        inputUser.setColumns(10);
        inputUser.setBounds(138, 138, 222, 30);
        contentPane.add(inputUser);
    }

    private void connect() {
        try {
            Thread thread = new Thread(new Client(new String[] {getUser(), getIP(), getPort()}));
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getIP() {
        return inputIP.getText();
    }

    private String getPort() {
        return inputPort.getText();
    }

    private String getUser() {
        return inputUser.getText();
    }


}
