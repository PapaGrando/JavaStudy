import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

class Scratch {
    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock = null;
        final int PORT = 8189;
        try {
            serv = new ServerSocket(PORT);
            System.out.println("Started on " + PORT + " . Waiting init...");
            sock = serv.accept();
            System.out.println("Client connected");
            Scanner sc = new Scanner(sock.getInputStream());
            PrintWriter pw = new PrintWriter(sock.getOutputStream());
            while (true) {
                String str = sc.nextLine();
                if (str.equals("end")) break;
                pw.println("Echo: " + str);
                pw.flush();
            }
        } catch (IOException e) {
            System.out.println("Error");
        } finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyServer<AuthService> {
    private ServerSocket server;
    private Vector<ClientHandler> clients;
    private Auth authService;

    public Auth getAuthService() {
        return authService;
    }

    private final int PORT = 8189;

    public MyServer() {
        try {
            server = new ServerSocket(PORT);
            Socket socket = null;
            authService = new BaseAuthService();
            authService.start();
            clients = new Vector<>();
            while (true) {
                System.out.println("Server wait for connection");
                socket = server.accept();
                System.out.println("Client Connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Server err");
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            authService.stop();
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) return true;
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o);
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
    }

    interface Auth {
        void start();

        String getNickByLoginPass(String login, String pass);

        void stop();
    }

    class BaseAuthService implements Auth {
        private class Entry {
            private String login;
            private String pass;
            private String nick;

            public Entry(String login, String pass, String nick) {
                this.login = login;
                this.pass = pass;
                this.nick = nick;
            }
        }

        private ArrayList<Entry> entries;

        @Override
        public void start() {
        }

        @Override
        public void stop() {
        }

        public BaseAuthService() {
            entries = new ArrayList<>();
            entries.add(new Entry("login1", "pass1", "nick1"));
            entries.add(new Entry("login2", "pass2", "nick2"));
            entries.add(new Entry("login3", "pass3", "nick3"));
        }

        @Override
        public String getNickByLoginPass(String login, String pass) {
            for (Entry o : entries) {
                if (o.login.equals(login) && o.pass.equals(pass)) return o.nick;
            }
            return null;
        }
    }

    public class ClientHandler {
        private MyServer myServer;
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private String name;

        public String getName() {
            return name;
        }

        public ClientHandler(MyServer myServer, Socket socket) {
            try {
                this.myServer = myServer;
                this.socket = socket;
                this.in = new DataInputStream(socket.getInputStream());
                this.out = new DataOutputStream(socket.getOutputStream());
                this.name = "";
                new Thread(() -> {
                    try {
                        while (true) { // цикл авторизации
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                String[] parts = str.split("\\s");
                                String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                                if (nick != null) {
                                    if (!myServer.isNickBusy(nick)) {
                                        sendMsg("/authok " + nick);
                                        name = nick;
                                        myServer.broadcastMsg(name + " intered in chat");
                                        myServer.subscribe(this);
                                        break;
                                    } else sendMsg("login already used");
                                } else {
                                    sendMsg("Incorrect login/pass");
                                }
                            }
                        }
                        while (true) { // цикл получения сообщений
                            String str = in.readUTF();
                            System.out.println("от " + name + ": " + str);
                            if (str.equals("/end")) break;
                            myServer.broadcastMsg(name + ": " + str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        myServer.unsubscribe(this);
                        myServer.broadcastMsg(name + " is exit");
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (IOException e) {
                throw new RuntimeException("Client error");
            }
        }

        public void sendMsg(String msg) {
            try {
                out.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}