package card;

public class Card {
	String id;
	String superType;
	String name;
	String cardImg;
	
	Card(String newID, String newSuperType, String newName, String newCardImg){
		super();
		this.id = newID;
		this.superType = newSuperType;
		this.name = newName;
		this.cardImg = newCardImg;
	}
	
	public String getId() {
		return id;
	}

	public String getSuperType() {
		return superType;
	}

	public String getName() {
		return name;
	}

	public String getCardImg() {
		return cardImg;
	}

	public String print() {
		
		return String.format("ID: %s\nSupertype: %s\nName: %s\nImg URL: %s",id,superType,name,cardImg);
	}
}
