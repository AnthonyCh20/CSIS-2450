package card;

public class PokemonCard extends Card {

	private String subType;
	private String type;
	private String evolution;
	private String weakness;
	private String flavorText;
	private String attack;
	private String attackCost;
	private String attackText;
	private String attackDmg;

	private String hp;

	public PokemonCard(String id, String superType, String name,String type,String cardImg, String subtype, String evolution,
			String weakness, String flavorText, String attack, String attackCost, String attackText, String hp,String attackDmg) 
	{
		super(id, superType, name, cardImg);
		this.subType = subtype;
		this.type = type;
		this.evolution = evolution;
		this.weakness = weakness;
		this.flavorText = flavorText;
		this.attack = attack;
		this.attackCost = attackCost;
		this.attackText = attackText;
		this.attackDmg = attackDmg;
		this.hp = hp;
	}

	public String getType() {
		return type;
	}

	public String getSubType() {
		return subType;
	}

	public String getEvolution() {
		return evolution;
	}

	public String getWeakness() {
		return weakness;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public String getAttack() {
		return attack;
	}

	public String getAttackCost() {
		return attackCost;
	}

	public String getAttackText() {
		return attackText;
	}

	public String getHp() {
		return hp;
	}

	public String getAttackDmg() {
		return attackDmg;
	}

	@Override
	public String print() {
		return super.print() + String.format("Subtype: %s\nEvolution: %s\nWeakness: %s\nFlavor Text: %s\nAttack: %s\nAttack Cost: %s\nAttack Text: %s\nHP: %s", subType,evolution,weakness,flavorText,attack,attackCost,attackText,hp);
	}

}
