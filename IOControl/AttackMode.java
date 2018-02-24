
public class AttackMode {
	private Rectangle damageBox;
	private int damage;
	
	AttackMode(Rectangle damagebox, int damage) {
		this.setDamagebox(damagebox);
		this.setDamage(damage);
	}
	private void setDamagebox(Rectangle damagebox) {
		this.damageBox = damagebox;
		
	}
	public Rectangle getDamagebox() {
		return damageBox;
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
