import java.util.UUID;

public class CUpdate {

    private UUID id;
    private int attackMode,jumpheight, movementspeed;
    private boolean isLookingRight, isJumping, isAttacking;
    private Rectangle pSelf;
    private  Attack amall;

    public CUpdate(UUID id, int attackMode, int jumpheight, int movementspeed, boolean isLookingRight, boolean isJumping, boolean isAttacking, Rectangle pSelf, Attack amall) {
        this.id = id;
        this.attackMode = attackMode;
        this.jumpheight = jumpheight;
        this.movementspeed = movementspeed;
        this.isLookingRight = isLookingRight;
        this.isJumping = isJumping;
        this.isAttacking = isAttacking;
        this.pSelf = pSelf;
        this.amall = amall;
    }
}
