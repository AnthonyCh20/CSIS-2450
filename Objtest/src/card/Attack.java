package card;

public class Attack {
	
	private String name;
	private String cost;
	private String damage;
	private String text;

	public Attack(String name, String cost, String damage, String text) {
		this.name = name;
		this.cost = cost;
		this.damage = damage;
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public String getCost() {
		return cost;
	}

	public String getDamage() {
		return damage;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "Attack [name=" + name + ", cost=" + cost + ", damage=" + damage + ", text=" + text + "]";
	}

}
