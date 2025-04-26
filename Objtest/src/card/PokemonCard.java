package card;

import java.util.ArrayList;
import java.util.List;

public class PokemonCard extends Card
{

	private String subType;
	private String type;
	private String evolution;
	private String weakness;
	private String flavorText;
	private List<Attack> attack = new ArrayList<Attack>();
	private List<Abilities> abilities = new ArrayList<>();
	private String hp;

	public PokemonCard(String id, String superType, String name, String type, String cardImg, String subtype,
											String evolution, String weakness, String flavorText,
											List<Abilities> abilities, List<Attack> attack, String hp)
	{
		super(id, superType, name, cardImg);
		this.subType = subtype;
		this.type = type;
		this.evolution = evolution;
		this.weakness = weakness;
		this.flavorText = flavorText;
		this.hp = hp;

		if (abilities != null)
		{
			this.abilities = abilities;
		}

		if (attack != null)
		{
			this.attack.addAll(attack);
		}
	}

	public String getType()
	{
		return type;
	}

	public String getSubType()
	{
		return subType;
	}

	public String getEvolution()
	{
		return evolution;
	}

	public String getWeakness()
	{
		return weakness;
	}

	public String getFlavorText()
	{
		return flavorText;
	}

	public List<Attack> getAttack()
	{
		return attack;
	}

	public String getHp()
	{
		return hp;
	}

	public List<Abilities> getAbilities()
	{
		return abilities;
	}

	@Override
	public String print()
	{
		return super.print() + String.format("Subtype: %s\nEvolution: %s\nWeakness: %s\nFlavor Text: %s\nAttack: %s\nHP: %s",
												subType, evolution, weakness, flavorText, attack, hp);
	}

}
