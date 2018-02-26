
public class Attack {
	private Rectangle damageBox;
	private double damage;

	public Attack(Rectangle damageBox, int damage) {
		this.damageBox = damageBox;
		this.damage = damage;
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
