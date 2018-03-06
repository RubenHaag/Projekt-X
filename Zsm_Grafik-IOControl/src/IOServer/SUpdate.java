package IOServer;

import java.io.*;
import java.util.UUID;

public class SUpdate {
    private Player p1,p2,p3;
    private boolean bosswin, endGame;
    private double cooldown;

    public SUpdate(Player p1, Player p2, Player p3, boolean bosswin, boolean endGame) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.bosswin = bosswin;
        this.endGame = endGame;
    }
    public byte[] toByteArray() throws IOException {
        byte[] buffer;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        buffer = p1.toByteArray();
        out.write(buffer.length);
        out.write(buffer);

        buffer = p2.toByteArray();
        out.write(buffer.length);
        out.write(buffer);

        buffer = p3.toByteArray();
        out.write(buffer.length);
        out.write(buffer);

        out.writeBoolean(bosswin);
        out.writeBoolean(endGame);
        out.writeDouble(cooldown);

        byte[] data = baos.toByteArray();
        return data;
    }
    public void fromByteArray(byte[] data) throws IOException {
        int length;
        byte[] buffer;
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream in = new DataInputStream(bais);

        length = in.readInt();
        buffer = new byte[length];
        in.readNBytes(buffer, 0, length);
        p1.fromByteArray(buffer);

        length = in.readInt();
        buffer = new byte[length];
        in.readNBytes(buffer, 0, length);
        p2.fromByteArray(buffer);

        length = in.readInt();
        buffer = new byte[length];
        in.readNBytes(buffer, 0, length);
        p3.fromByteArray(buffer);

        bosswin = in.readBoolean();
        endGame = in.readBoolean();
        cooldown = in.readDouble();
    }
    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public Player getP3() {
        return p3;
    }

    public void setP3(Player p3) {
        this.p3 = p3;
    }

    public boolean isBosswin() {
        return bosswin;
    }

    public void setBosswin(boolean bosswin) {
        this.bosswin = bosswin;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

}
