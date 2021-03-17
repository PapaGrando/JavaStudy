import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

class Scratch {
    public static void main(String[] args) {
        new MyWindow();
    }
}

class MyWindow extends JFrame {
    private JTextField jtf;
    private JTextArea jta;
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket sock;
    private DataInputStream in;
    private DataOutputStream out;

    public MyWindow() {
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());
            setAuthorized(false);
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/authok")) {
                            setAuthorized(true);
                            break;
                        }
                        textArea.appendText(str + "\n");
                    }
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            break;
                        }
                        textArea.appendText(str + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    setAuthorized(false);
                }
            });
            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            System.out.println("Enable server first!");
        }
        setBounds(600, 300, 500, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jta = new JTextArea();
        jta.setEditable(false);
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jta);
        add(jsp, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSend = new JButton("SEND");
        bottomPanel.add(jbSend, BorderLayout.EAST);
        jtf = new JTextField();
        bottomPanel.add(jtf, BorderLayout.CENTER);
        jbSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jtf.getText().trim().isEmpty()) {
                    sendMsg();
                    jtf.grabFocus();
                }
            }
        });
        jtf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (in.) {
                            String w = in.nextLine();
                            if (w.equalsIgnoreCase("end session")) break;
                            jta.append(w);
                            jta.append("\n");
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.println("end");
                    out.flush();
                    sock.close();
                    out.close();
                    in.close();
                } catch (IOException exc) {
                }
            }
        });
        setVisible(true);
    }

    public void onAuthClick() {
        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
            loginField.clear();
            passField.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendMsg() {
        try {
            out.writeUTF(jtf.getText());
            jtf.setText("");
        } catch (IOException e) {
            System.out.println("Send err");
        }
    }
}