package mca.rest.api.controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestTemplate;

public class MockController {
	
	private final static String uriMock = "http://localhost:3001/";
	private final static String similarIds = "product/%s/similarids";
	private final static String product = "product/%s";
	
	/**
	 * Method that return the ids of similiar product of the given one
	 * 
	 * @param productId The Id of the product
	 * @return An array with the Ids of similiar products
	 */
	public static int[] getSimilarIds(String productId) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder uri = new StringBuilder(uriMock);
		uri.append(String.format(similarIds, productId));
		return restTemplate.getForObject(uri.toString(), int[].class);
	}
	
	/**
	 * Retrieves a product by Id
	 * 
	 * @param productId The Id of the product
	 * @return A JsonObject of the product
	 */
	public static JSONObject getProduct(int productId) throws Exception{
		JSONParser parser = new JSONParser();
		
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder uri = new StringBuilder(uriMock);
		uri.append(String.format(product, productId));
		String res =  restTemplate.getForObject(uri.toString(), String.class);
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(res);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return json;
	}

}
