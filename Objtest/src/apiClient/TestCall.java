package apiClient;

import java.awt.Image;
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

		private String apiUrl;
		private String id;
		private String pokemonName;
		private String evolution;
		private String superType;
		private String subType;
		private String weakness;
		private String flavorText;
		private String attack;
		private int hp;
		private String cardImg;
		// Set the API endpoint to fetch a Charizard card
		
		public TestCall(String apiUrl){
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
							id = setId(card);
	//						pokemonName = checkNull(card.get("name").asText());
							superType = setSuperType(card);
							subType = setSubType(card);
	//						evolution = setEvolution(card);
							cardImg = setImage(card);
							
							toString(id,subType,superType);
							
						}
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

	public String getImage() {
		return this.cardImg;
	}
		
	private static String setImage(JsonNode card) {
		String url;
		
		if(card.has("images") && card.get("images").isObject()) {
			JsonNode imgNode = card.get("images");
			
			url = imgNode.get("large").asText();
			return url;
							
		}
		
		return null;
	}

	/**
	 * @param card
	 * @return 
	 */
	private static String setSuperType(JsonNode card) {
		String superType;
		
		if(card.has("supertype") && !card.get("supertype").isNull()) {
			superType = card.get("supertype").asText();
			return superType;
		}
		
		return null;
	}

	private static String setId(JsonNode card) {
		if (card.has("id") && !card.get("id").isNull()) {
			String ret = card.get("id").asText();

			if (ret != null) {
				return ret;
			}
		}
		return null;

	}

	private static String setSubType(JsonNode card) {
		String ret = null;

		if (card.has("subtypes") && card.get("subtypes").isArray()) {
			JsonNode subType = card.get("subtypes");
			for (JsonNode st : subType) {
				ret = st.asText();
			}

			return ret;
		}

		return ret;
	}

	/**
	 * @param id
	 * @param subType 
	 * @param superType 
	 * @param pokemonName
	 * @param evolution
	 * @param superType
	 */
	private static void toString(String id, String subType, String superType) {
		System.out.printf("Id: %s \nSubtype: %s\nSupertype: %s", id, subType,superType);
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

	/**
	 * @param card
	 * @return String
	 * 
	 *         Returns evolution of pokemon card. Returns null if no value found.
	 */
	private static String setEvolution(JsonNode card) {
		String ret = null;
		if (card.has("evolvesTo") && card.get("evolvesTo").isArray()) {
			JsonNode evo = card.get("evolvesTo");
			for (JsonNode e : evo) {
				ret = e.asText();
			}

			return ret;
		} else if (card.has("evolvesfrom") && card.get("evolvesFrom").isArray()) {
			JsonNode evo = card.get("evolvesFrom");
			for (JsonNode e : evo) {
				ret = e.asText();
			}

			return ret;

		}
		return ret;
	}

	/**
	 * @param card Checks to see if data is null
	 */
	private static boolean checkNull(String attribute) {
		if (attribute != null) {
			return true;
		}

		return false;
	}
}
