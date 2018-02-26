import java.util.UUID;

public class CUpdate {

    private UUID id;
    private  Attack amall;
    private Player player;

    public CUpdate(UUID id, Attack amall, Player player) {
        this.id = id;
        this.amall = amall;
        this.player = player;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Attack getAmall() {
        return amall;
    }

    public void setAmall(Attack amall) {
        this.amall = amall;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
