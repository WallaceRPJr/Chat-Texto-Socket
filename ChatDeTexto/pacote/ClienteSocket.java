import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;

public class ClienteSocket {
    private  String login;
    private  Socket socket;
    private  BufferedReader in; // Obj de Entrada
    private  PrintWriter out;  // Obj de Saida

    public ClienteSocket(Socket socket, String login) throws IOException{
        this.login = login;
        this.socket = socket;
        System.out.println("CLIENTE " + socket.getRemoteSocketAddress() + " ONLINE");
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public SocketAddress getRemoteSocketAddress(){
        return  socket.getRemoteSocketAddress(); 
    }
    public String getNome(){
        return login;
    }

    public void setNome(String nome){
        this.login = nome;
    }
     
    public String getMsg(){
        try {
            return in.readLine();
        } catch (IOException e) {
           return null;
        }
    }

    public boolean sendMsg(String msg){
        out.println(msg);
        return !out.checkError();
    }


    public void close(){
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
}