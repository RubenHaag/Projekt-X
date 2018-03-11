
package ioserver;
import java.io.*;
import java.util.UUID;

public class CUpdate {

    private UUID id;
    private  Attack amAllg;
    private Player playerIO;

    public CUpdate(UUID id, Attack amAllg, Player playerIO) {
        this.id = id;
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
        byte[] att = amAllg.toByteArray();
        byte[] pl = playerIO.toByteArray();

        out.writeUTF(id.toString());
        out.write(att.length);
        out.write(att);
        out.write(playerIO.toByteArray());

        byte[] data = baos.toByteArray();
        return data;
    }
    public void fromByteArray(byte[] data) throws IOException {
        int length;
        byte[] buffer;
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream in = new DataInputStream(bais);

        id = UUID.fromString(in.readUTF());

        length = in.readInt();
        buffer = new byte[length];
        in.readNBytes(buffer, 0, length);
        amAllg.fromByteArray(buffer);

        length = in.readInt();
        buffer = new  byte[length];
        in.readNBytes(buffer, 0, length);
        playerIO.fromByteArray(buffer);
    }
}
