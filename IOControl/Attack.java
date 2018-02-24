
public class Attack {
	private Rectangle damageBox;
	private int damage;
	private AttackMode attackMode;

	public Attack(Rectangle damageBox, int damage, AttackMode attackMode) {
		this.damageBox = damageBox;
		this.damage = damage;
		this.attackMode = attackMode;
	}

	public void setDamageBox(Rectangle damageBox) {
		this.damageBox = damageBox;
	}

	public void setAttackMode(AttackMode attackMode) {
		this.attackMode = attackMode;
	}

	public Rectangle getDamageBox() {
		return damageBox;
	}

	public AttackMode getAttackMode() {
		return attackMode;
	}
	public void setBoxBreiteHoehe(double width,double heigth) {
		damageBox.setHeigth(heigth);
		damageBox.setWidth(width);
	}

	public void setPosition(Position pos) {
		damageBox.setPos(pos);
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}


}
