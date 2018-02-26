public class SUpdate {
    private boolean isHitted, isAttacking;
    private double damage, health, mana, cooldown;
    private Rectangle pOther1,pOther2;

    public SUpdate(boolean isHitted, boolean isAttacking, double damage, double health, double mana, double cooldown, Rectangle pOther1, Rectangle pOther2) {
        this.isHitted = isHitted;
        this.isAttacking = isAttacking;
        this.damage = damage;
        this.health = health;
        this.mana = mana;
        this.cooldown = cooldown;
        this.pOther1 = pOther1;
        this.pOther2 = pOther2;
    }
}
