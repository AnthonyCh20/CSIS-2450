package apiClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class ApiClient
{
	public static void main(String args[]) throws IOException
	{
		Scanner input = new Scanner(System.in);
		StringBuilder userIn = new StringBuilder();

		System.out.println("Please enter Category:(eg. ability, berry, pokemon)");
		String user = input.next();
		userIn.append(user + "/");
		System.out.println("Enter search item: ");
		user = input.next();
		userIn.append(user);
		
		userIn.append("?limit=20&offset=20");

		try
		{
			ObjectMapper mapper = new ObjectMapper();
			// API URL
			URL url = new URL("https://pokeapi.co/api/v2/" + userIn.toString());
			// Open connection to URL
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// Set request to GET
			conn.setRequestMethod("GET");

			conn.setRequestProperty("Accept", "application/json");

			int responseCode = conn.getResponseCode();

			System.out.println("Response Code: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK)
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String inLine;

				StringBuilder sb = new StringBuilder();

				while ((inLine = reader.readLine()) != null)
				{
					sb.append(inLine);
				}

				reader.close();

				String json = sb.toString();

				JsonNode rootNode = mapper.readTree(json);

				if (rootNode.has("name"))
				{
					String pokemonName = rootNode.get("name").asText();
					System.out.println("Pokemon Name: " + pokemonName);

					/*
					 * Pulling the starter abilities from the root JSON node tree
					 */
					if (rootNode.has("abilities"))
					{
						JsonNode abilitiesNode = rootNode.get("abilities");
						
						// If abilities is array print abilities in a dashed list.
						if (abilitiesNode.isArray())
						{
							System.out.println("Abilities: ");
							for (JsonNode abilityNode : abilitiesNode)
							{
								JsonNode abilityDetails = abilityNode.get("ability");
								String abilityName = abilityDetails.get("name").asText();

								System.out.println(String.format("- %s", abilityName));
							}
						}
					}

					if (rootNode.has("base_experience"))
					{
						int baseXP = rootNode.get("base_experience").asInt();
						System.out.println("Base XP: " + baseXP);
					}
				}

			}

		} catch (ProtocolException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
