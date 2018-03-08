
package ioserver;
import Server.*;
public class MainServer {
    public static void main(String[] args) {
        ServerVerwaltung sv = new ServerVerwaltung();
        Server s = new Server(CLU1, CLU2, CLU3);
    }
}