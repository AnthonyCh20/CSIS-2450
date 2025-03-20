package apiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestCall {

	private String id;
	private String subType;
	private String pokemonName;
	private String superType;
	private String evolution;
	private String weakness;
	private String flavorText;
	private String attack;
	private String attackCost;
	private String attackText;

	private String cardImg;

	private String hp;

	public TestCall(String apiUrl) {
		// Create HTTPclient
		HttpClient client = HttpClient.newHttpClient();
		// Create a URL object
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).GET().build();

		try {
			ObjectMapper om = new ObjectMapper();

			// Send request and get response
			HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

			// Check response status code
			int statusCode = response.statusCode();

			// Read the API response
			if (statusCode == 200) { // 200 OK
				String json = readResponse(response);

				JsonNode rootNode = om.readTree(json);
				JsonNode dataArray = rootNode.get("data");

				if (dataArray != null && dataArray.isArray()) {
					for (JsonNode card : dataArray) {
						System.out.println(card.get("supertype").asText());
						checkCardType(card);

					}
					System.out.println(toString());
				} else {
					System.out.println("Data Error!!!!");
				} // data extraction end

			} else {
				System.out.println("API request failed.");
			} // if(response == 200) end

		} catch (Exception e) {
			e.printStackTrace();
		} // end catch
	}// end main

	/**
	 * @param card
	 */
	private void checkCardType(JsonNode card) {
		String key = card.get("supertype").asText();

		switch (key) {
		case "Pokémon": {

			setId(card);
			setHp(card);
			setPokemonName(card);
			setSuperType(card);
			setSubType(card);
			setEvolution(card);
			setWeakness(card);
			setCardImg(card);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}

	}

	// Get/Set ID
	public String getId() {
		return id;
	}

	private void setId(JsonNode card) {
		this.id = card.get("id").asText();
	}

	// Get/Set Pokemon name
	public String getPokemonName() {
		return pokemonName;
	}

	private void setPokemonName(JsonNode card) {
		this.pokemonName = card.get("name").asText();
	}

	// Get/Set evolution
	public String getEvolution() {
		return evolution;
	}

	private void setEvolution(JsonNode card) {

		if (card.has("evolvesTo") && card.get("evolvesTo").isArray()) {
			JsonNode evoTo = card.get("evolvesTo");
			for (JsonNode evo : evoTo) {
				this.evolution = evo.asText();
			}
		} else if (card.has("evolvesFrom") && card.get("evolvesFrom").isArray()) {
			JsonNode evoFrom = card.get("evolvesFrom");
			for (JsonNode evo : evoFrom) {
				this.evolution = evo.asText();
			}
		}
	}

	// Get/Set supertype
	public String getSuperType() {
		return superType;
	}

	private void setSuperType(JsonNode card) {
		if (card.has("supertype") && !card.get("supertype").isNull()) {
			this.superType = card.get("supertype").asText();
		}
	}

	// Get/Set subtype
	public String getSubType() {
		return subType;
	}

	private void setSubType(JsonNode card) {

		if (card.has("subtypes") && card.get("subtypes").isArray()) {
			JsonNode subType = card.get("subtypes");
			for (JsonNode st : subType) {

				this.subType = st.asText();
			}
		}
	}

	// Get/Set weakness
	public String getWeakness() {
		return weakness;
	}

	private void setWeakness(JsonNode card) {
		if (card.has("weaknesses") && card.get("weaknesses").isArray()) {
			JsonNode weakness = card.get("weaknesses");
			for (JsonNode weak : weakness) {
				this.weakness = weak.get("type").asText() + " " + weak.get("value").asText();
			}
		}
	}

	// Get/Set flavor text
	public String getFlavorText() {
		return flavorText;
	}

	private void setFlavorText(JsonNode card) {
		this.flavorText = flavorText;
	}

	// Get/Set attack
	public String getAttack() {
		return attack;
	}

	private void setAttack(JsonNode card) {
		this.attack = attack;
	}

	// Get/Set card image
	public String getCardImg() {
		return cardImg;
	}

	private void setCardImg(JsonNode card) {
		if (card.has("images") && card.get("images").isObject()) {
			JsonNode img = card.get("images");
			this.cardImg = img.get("large").asText();

		}
	}

	// Get/Set HP
	public String getHp() {
		return hp;
	}

	private void setHp(JsonNode card) {
		this.hp = card.get("hp").asText();
	}

	/**
	 * @param response
	 * @return String json
	 * @throws IOException
	 * 
	 *                     Reads the JSON response and returns it as a string value.
	 */
	private static String readResponse(HttpResponse<InputStream> response) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()));

		StringBuilder sb = new StringBuilder();

		String line;

		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}

		reader.close();

		String json = sb.toString();
		return json;
	}

	public String toString() {

		String ans = String.format("Id: %s \nSubtype: %s\nSupertype: %s\nWeakness: %s\nHP: %s", getId(), getSubType(),
				getSuperType(), getWeakness(), getHp());

		return ans;

	}

}// end class
