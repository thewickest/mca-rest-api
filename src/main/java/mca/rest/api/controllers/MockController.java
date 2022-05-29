package mca.rest.api.controllers;

import org.json.simple.JSONObject;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
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
	public static int[] getSimilarIds(String productId) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder uri = new StringBuilder(uriMock);
		uri.append(String.format(similarIds, productId));
		int [] res = {};
		try {
			return restTemplate.getForObject(uri.toString(), int[].class);
		}catch (HttpClientErrorException e ) {
			return res;
		}
	}

	/**
	 * Retrieves a product by Id
	 * 
	 * @param productId The Id of the product
	 * @return A JsonObject of the product
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject getProduct(int productId) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder uri = new StringBuilder(uriMock);
		uri.append(String.format(product, productId));
		JSONObject json = new JSONObject();
		try {
			json = restTemplate.getForObject(uri.toString(), JSONObject.class);
		}catch (RestClientException e) {
			json.put("error", "Page Not found");
		}
		return json;
	}

}
