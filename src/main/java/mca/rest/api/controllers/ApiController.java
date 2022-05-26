package mca.rest.api.controllers;

import org.json.simple.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



/**
 *
 * A middleWare Api that returns Similar objects to the given one by Id.
 */
@RestController
public class ApiController {
    /**
     * Retrieves an array of similar objects of the given one by
     *
     * @param The Id of the product
     * @return Similar Objects to the Object given by Id
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/product/{productId}/similar", 
    				method = RequestMethod.GET,
    				produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public JSONArray getSimilar(@PathVariable String productId) {

    	JSONArray res = new JSONArray();
    	
    	try {
	    	int[] array = MockController.getSimilarIds(productId);
	        for(int i=0; i<array.length; i++) {
	        	res.add(MockController.getProduct(array[i]));
	        }
	        return res;
    	}catch(Exception e) {
    		return res;
    	}
    }
}
