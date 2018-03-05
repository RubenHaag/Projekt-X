
package ioserver;

import java.io.*;

public class Player {
    private int numberID;
    private int attackMode = 1;
    private int jumpheight = 200;
    private int movementspeed = 100;
    private Rectangle hb = new Rectangle(new Position(400,400), 10, 20);
    private double health = 100;
    private double damage;
    //todo regspeed
    private double regSpeed;
    private double mana = 100;
    private boolean isJumping, isHitted, isAttacking, isLookingRight, isSprinting, isBoss, isDead;
    private Attack amNormal = new Attack(new Rectangle(new Position(0,0), 10, 10), 0, 0, 0);
    private Attack amSpec1 = new Attack(new Rectangle(new Position(0,0), 10, 10), 1, 0, 0);
    private Attack amSpec2 = new Attack(new Rectangle(new Position(0,0), 10, 10), 2, 0, 0);
    private Rectangle gr =  new Rectangle(new Position(0,0), 10, 10);

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
    public byte[] toByteArray() throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        out.write(hb.getPos().getXPos());
        out.write(hb.getPos().getYPos());
        out.writeBoolean(isJumping);
        out.writeBoolean(isLookingRight);
        out.writeBoolean(isAttacking);
        byte[] data = baos.toByteArray();
        return data;
    }
    public void fromByteArray(byte[] data) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream in = new DataInputStream(bais);
        hb.getPos().setXPos(in.readInt());
        hb.getPos().setYPos(in.readInt());
        isJumping = in.readBoolean();
        isLookingRight = in.readBoolean();
        isAttacking = in.readBoolean();
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
