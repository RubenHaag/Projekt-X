package ioserver;

import java.io.*;

public class Attack {
    private Rectangle damageBox;
    private double damage;
    private double cost;
    private double cooldownZeit;
    private double cooldown;

    public Attack(Rectangle damageBox, int damage,double cost,double cooldownZeit) {
        this.damageBox = damageBox;
        this.damage = damage;
        this.cost = cost;
        this.cooldownZeit = cooldownZeit;
    }

    public byte[] toByteArray()throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        out.write(damageBox.getPos().getXPos());
        out.write(damageBox.getPos().getYPos());
        out.write(damageBox.getWidth());
        out.write(damageBox.getHeigth());

        out.writeDouble(damage);
        out.writeDouble(cost);
        out.writeDouble(cooldownZeit);
        out.writeDouble(cooldown);
        byte[] data = baos.toByteArray();
        return data;
    }
    public void fromByteArray(byte[] data) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        DataInputStream in = new DataInputStream(bais);
        damageBox.getPos().setXPos(in.readInt());
        damageBox.getPos().setYPos(in.readInt());
        damageBox.setWidth(in.readInt());
        damageBox.setHeigth(in.readInt());

        damage = in.readDouble();
        cost = in.readDouble();
        cooldownZeit = in.readDouble();
        cooldown = in.readDouble();
    }

    public void setDamageBox(Rectangle damageBox) {
        this.damageBox = damageBox;
    }

    public Rectangle getDamageBox() {
        return damageBox;
    }

    public void setBoxBreiteHoehe(int width,int heigth) {
        damageBox.setHeigth(heigth);
        damageBox.setWidth(width);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double mana) {
        this.cost = mana;
    }

    public double getCooldownZeit() {
        return cooldownZeit;
    }

    public void setCooldownZeit(double cooldownZeit) {
        this.cooldownZeit = cooldownZeit;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void resetCooldown() {
        this.cooldown = cooldownZeit;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public void setPosition(Position pos) {
        damageBox.setPos(pos);
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
