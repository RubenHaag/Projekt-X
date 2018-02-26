public class Player {
    private int id,attackMode, mana, cooldown,jumpheight, movementspeed;
    private Rectangle hb;
    private double health, damage, regSpeed;
    private boolean isJumping,isHitted, isAttacking, isLookingRight, isSprinting, isBoss;
    private Attack amNormal,amSpec1,amSpec2;
    private Partikel pa;
    private Rectangle gr;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rectangle getHb() {
        return hb;
    }

    public void setHb(Rectangle hb) {
        this.hb = hb;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getRegSpeed() {
        return regSpeed;
    }

    public void setRegSpeed(double regSpeed) {
        this.regSpeed = regSpeed;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isHitted() {
        return isHitted;
    }

    public void setHitted(boolean hitted) {
        isHitted = hitted;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public boolean isLookingRight() {
        return isLookingRight;
    }

    public void setLookingRight(boolean lookingRight) {
        isLookingRight = lookingRight;
    }

    public boolean isSprinting() {
        return isSprinting;
    }

    public void setSprinting(boolean sprinting) {
        isSprinting = sprinting;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }

    public int getAttackMode() {
        return attackMode;
    }

    public void setAttackMode(int attackMode) {
        this.attackMode = attackMode;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getJumpheight() {
        return jumpheight;
    }

    public void setJumpheight(int jumpheight) {
        this.jumpheight = jumpheight;
    }

    public int getMovementspeed() {
        return movementspeed;
    }

    public void setMovementspeed(int movementspeed) {
        this.movementspeed = movementspeed;
    }

    public Attack getAmNormal() {
        return amNormal;
    }

    public void setAmNormal(Attack amNormal) {
        this.amNormal = amNormal;
    }

    public Attack getAmSpec1() {
        return amSpec1;
    }

    public void setAmSpec1(Attack amSpec1) {
        this.amSpec1 = amSpec1;
    }

    public Attack getAmSpec2() {
        return amSpec2;
    }

    public void setAmSpec2(Attack amSpec2) {
        this.amSpec2 = amSpec2;
    }

    public Partikel getPartikel() {
        return pa;
    }

    public void setPartikel(Partikel pa) {
        this.pa = pa;
    }

    public Rectangle getGr() {
        return gr;
    }

    public void setGr(Rectangle gr) {
        this.gr = gr;
    }
}
