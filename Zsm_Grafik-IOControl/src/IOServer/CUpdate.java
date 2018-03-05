package IOServer;
import java.io.*;
import java.util.UUID;

public class CUpdate {

    private UUID id;
    private  Attack amAllg;
    private Player player;

    public CUpdate(UUID id, Attack amAllg, Player player) {
        this.id = id;
        this.amAllg = amAllg;
        this.player = player;
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
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public byte[] toByteArray() throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        byte[] att = amAllg.toByteArray();
        byte[] pl = player.toByteArray();

        out.writeUTF(id.toString());
        out.write(att.length);
        out.write(att);
        out.write(player.toByteArray());

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
        player.fromByteArray(buffer);
    }
}
