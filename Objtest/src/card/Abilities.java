package card;

public class Abilities
{
	private String name;

	private String text;
	private String type;
	
	public Abilities(String name,String text, String type) {
		this.name = name;
		this.text = text;
		this.type = type;
	}
		
	public String getName()
	{
		return name;
	}
	
	public String getText()
	{
		return text;
	}
	
	public String getType()
	{
		return type;
	}
	
	@Override
	public String toString() {
		return "Abilities [name=" + name + ", text=" + text + ", type=" + type + "]";
	}
}
