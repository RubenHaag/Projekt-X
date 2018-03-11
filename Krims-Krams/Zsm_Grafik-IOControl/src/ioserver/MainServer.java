
package ioserver;

import server.Server;

public class MainServer {
    public static void main(String[] args) {
        ServerVerwaltung sv = new ServerVerwaltung();
        Server s = new Server(sv.getCLU0(),sv.getCLU1(), sv.getCLU2(), sv);
    }
}