
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
	public void setDamageBox(Rectangle damageBox) {
		this.damageBox = damageBox;
	}
	public Rectangle getDamageBox() {
		return damageBox;
	}
	public void setBoxBreiteHoehe(double width,double heigth) {
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
