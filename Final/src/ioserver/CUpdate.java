
package ioserver;
import java.io.*;
import java.util.UUID;

public class CUpdate {

    private UUID id;
    private  Attack amAllg;
    private Player playerIO;


    public CUpdate(UUID id, Attack amAllg, Player playerIO) {
        this.id = UUID.randomUUID();
        this.amAllg = amAllg;
        this.playerIO = playerIO;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Attack getAmAllg() {
        return amAllg;
    }

    public void setAmAllg(Attack amAllg) {
        this.amAllg = amAllg;
    }

    public Player getPlayer() {
        return playerIO;
    }

    public void setPlayer(Player playerIO) {
        this.playerIO = playerIO;
    }


    public byte[] toByteArray() throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        //Erstellt zwei neue Byte Arrays
        byte[] att = amAllg.toByteArray();
        byte[] pl = playerIO.toByteArray();

        //Um später die länge des später auszulesenden Arrays zu speichern
        out.writeInt(att.length);
        //Schreibt die Attacke
        out.write(att);
        out.writeInt(pl.length);
        out.write(pl);
        out.writeUTF(id.toString());
        byte[] data = baos.toByteArray();
        return data;
    }
    public void fromByteArray(byte[] data) throws IOException {
        int length;
        byte[] buffer;

        //Erschaffe einen Datainputstream um aus diesem Später ein Byte Array zu machen
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream in = new DataInputStream(bais);


        //Lese die Übergebene Attacke aus
        length = in.readInt();
        buffer = new byte[length];
        System.out.println("Länge: "+ length);
        in.read(buffer, 0, length);
        amAllg.fromByteArray(buffer);

        //Lese den zu übergebenen Spieler aus dem Buffer(Siehe ioserver.player)
        length = in.readInt();
        buffer = new  byte[length];
        in.read(buffer, 0, length);

        //Ändert die Parameter im Spieler entsprchend den gegebenheiten
        playerIO.fromByteArray(buffer);
        id = UUID.fromString(in.readUTF());
    }
}
