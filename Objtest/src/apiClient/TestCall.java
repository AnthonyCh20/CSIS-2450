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

import card.Card;
import card.PokemonCard;

public class TestCall {

	public Card testCall(String apiUrl) {
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
						checkCardType(card);
						return createPokemonCard(card);

					}
					// System.out.println(toString());
				} else {
					System.out.println("Data Error!!!!");
				} // data extraction end

			} else {
				System.out.println("API request failed.");
			} // if(response == 200) end

		} catch (Exception e) {
			e.printStackTrace();
		} // end catch
		return null;
	}// end main

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

	/**
	 * @param card
	 */
	private void checkCardType(JsonNode card) {
		String key = card.get("supertype").asText();

		switch (key) {
		case "Pokémon": {
			PokemonCard pokeCard = createPokemonCard(card);
			System.out.println(pokeCard.print());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}

	}

	private PokemonCard createPokemonCard(JsonNode card) {
		String id = card.get("id").asText();
		String supertype = card.get("supertype").asText();
		String name = card.get("name").asText();
		String type = extractType(card);
		String cardImg = card.get("images").get("large").asText();
		String subtype = extractSubtype(card);
		String evolution = extractEvolution(card);
		String weakness = extractWeakness(card);
		String flavorText = card.has("flavorText") ? card.get("flavorText").asText() : "";
		String attack = extractAttacks(card);
		String attackCost = extractAttackCost(card);
		String attackText = extractAttackText(card);
		String hp = card.get("hp").asText();
		String attackDmg = extractDmg(card);
//		String setName = extractSetName(card);
//		String setSeries = extractSetSeries(card);
		return new PokemonCard(id,supertype,name,type,cardImg,subtype,evolution,weakness,
				flavorText,attack,attackCost,attackText,hp,attackDmg);
	}

	private String extractDmg(JsonNode card) {
		if(card.has("attacks") && card.get("attacks").isArray()) {
			JsonNode attack = card.get("attacks");
			for(JsonNode a : attack) {
				return a.get("damage").asText();
			}
		}
		return "";
	}

	private String extractAttackCost(JsonNode card) {
		if(card.has("attacks") && card.get("attacks").isArray()) {
			JsonNode attack = card.get("attacks");
			for(JsonNode a : attack) {
				return a.get("cost").get(0).asText();
			}
		}
		return "";
	}

	private String extractAttackText(JsonNode card) {
		if(card.has("attacks") && card.get("attacks").isArray()) {
			JsonNode attack = card.get("attacks");
			for(JsonNode a : attack) {
				return a.get("text").asText();
			}
		}
		return "";
	}

	private String extractAttacks(JsonNode card) {
		if(card.has("attacks") && card.get("attacks").isArray()) {
			JsonNode attack = card.get("attacks");
			if(attack.isArray() && attack.size() > 1) {
				String[] atkList = new String[attack.size()];
				int i = 0;
				for(JsonNode a : attack) {
					atkList[i] = a.asText();
					i++;
				}
			}
		}
		return "";
	}

	private String extractWeakness(JsonNode card) {
		if(card.has("weaknesses") && card.get("weaknesses").isArray()) {
			JsonNode weakness = card.get("weaknesses");
			JsonNode weak = weakness.get(0);
			return weak.get("type").asText() + " " + weak.get("value").asText();
		}
		return "";
	}

	private String extractEvolution(JsonNode card) {
		if(card.has("evolvesTo") && card.get("evolvesTo").isArray()) {
			JsonNode evoTo = card.get("evolvesTo");
			return evoTo.asText();
		}else if(card.has("evolvesFrom")) {
			JsonNode evoFrom = card.get("evolvesFrom");
			return evoFrom.asText();
		}
		return "";
	}

	private String extractType(JsonNode card) {
		if(card.has("types") && card.get("types").isArray()) {
			JsonNode type = card.get("types");
			return type.get(0).asText();
		}
		return "";
	}

	private String extractSubtype(JsonNode card) {
		if (card.has("subtypes") && card.get("subtypes").isArray()) {
			JsonNode sub = card.get("subtypes");
			return sub.get(0).asText();
		}
		return "";
	}
}// end class
