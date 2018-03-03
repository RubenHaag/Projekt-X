import java.util.UUID;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
}
