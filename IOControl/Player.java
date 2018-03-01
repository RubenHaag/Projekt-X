public class Player {

    private int numberID,attackMode,jumpheight, movementspeed;
    private Rectangle hb;
    private double health, damage, regSpeed, mana;
    private boolean isJumping,isHitted, isAttacking, isLookingRight, isSprinting, isBoss, isDead;
    private Attack amNormal,amSpec1,amSpec2;
    private Rectangle gr;

    public Player(int numberID, int attackMode, int jumpheight, int movementspeed, Rectangle hb, double health,
			double damage, double regSpeed, double mana, boolean isJumping, boolean isHitted, boolean isAttacking,
			boolean isLookingRight, boolean isSprinting, boolean isBoss, boolean isDead, Attack amNormal,
			Attack amSpec1, Attack amSpec2, Rectangle gr) {
		super();
		this.numberID = numberID;
		this.attackMode = attackMode;
		this.jumpheight = jumpheight;
		this.movementspeed = movementspeed;
		this.hb = hb;
		this.health = health;
		this.damage = damage;
		this.regSpeed = regSpeed;
		this.mana = mana;
		this.isJumping = isJumping;
		this.isHitted = isHitted;
		this.isAttacking = isAttacking;
		this.isLookingRight = isLookingRight;
		this.isSprinting = isSprinting;
		this.isBoss = isBoss;
		this.isDead = isDead;
		this.amNormal = amNormal;
		this.amSpec1 = amSpec1;
		this.amSpec2 = amSpec2;
		this.gr = gr;
	}
    
    public Player() {
		super();
		this.numberID = 0;
		this.attackMode = 0;
		this.jumpheight = 0;
		this.movementspeed = 0;
		this.hb = new Rectangle(new Position(0,0), 10, 10);
		this.health = 0;
		this.damage = 0;
		this.regSpeed = 0;
		this.mana = 0;
		this.isJumping = false;
		this.isHitted = false;
		this.isAttacking = false;
		this.isLookingRight = false;
		this.isSprinting = false;
		this.isBoss = false;
		this.isDead = false;
		this.amNormal = new Attack(new Rectangle(new Position(0,0), 10, 10), 0, 0, 0);
		this.amSpec1 = new Attack(new Rectangle(new Position(0,0), 10, 10), 1, 0, 0);
		this.amSpec2 = new Attack(new Rectangle(new Position(0,0), 10, 10), 2, 0, 0);
		this.gr = new Rectangle(new Position(0,0), 10, 10);
	}

    public void setUpdateSSelf(Player p) {
        health = p.getHealth();
        damage = p.getDamage();
        mana = p.getMana();
        isDead = p.isDead();
        isHitted = p.isHitted();
        amSpec1.setCooldown(p.getAmSpec1().getCooldown());
        amSpec2.setCooldown(p.getAmSpec2().getCooldown());
    }

    public void setUpdateSOther(Player p) {
        health = p.getHealth();
        damage = p.getDamage();
        mana = p.getMana();
        isDead = p.isDead();
        isHitted = p.isHitted();
        isJumping = p.isJumping();
        isAttacking = p.isAttacking();
        isLookingRight = p.isLookingRight();
        hb.setPos(p.getHb().getPos());
    }
	public int getNumberID() {
        return numberID;
    }

    public void setNumberID(int numberID) {
        this.numberID = numberID;
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

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
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

    public Rectangle getGr() {
        return gr;
    }

    public void setGr(Rectangle gr) {
        this.gr = gr;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
}
