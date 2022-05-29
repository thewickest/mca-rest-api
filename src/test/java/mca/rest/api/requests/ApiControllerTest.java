package mca.rest.api.requests;

import com.hackerrank.test.utility.Order;
import com.hackerrank.test.utility.OrderedTestRunner;
import com.hackerrank.test.utility.TestWatchman;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {
    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public TestWatcher watchman = TestWatchman.watchman;

    @Autowired
    private MockMvc mockMvc;

    @BeforeClass
    public static void setUpClass() {
        TestWatchman.watchman.registerClass(ApiControllerTest.class);
    }

    @AfterClass
    public static void tearDownClass() {
        TestWatchman.watchman.createReport(ApiControllerTest.class);
    }

    /**
     *	Tests if a product that exists is return
     *
     * @throws Exception
     *
     */
    @Test
    @Order(1)
    public void testExists() throws Exception {
        String response = mockMvc
        		.perform(MockMvcRequestBuilders.get("/product/1/similar"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        String expected = "[{\"price\":19.99,\"name\":\"Dress\",\"id\":\"2\",\"availability\":true},{\"price\":29.99,\"name\":\"Blazer\",\"id\":\"3\",\"availability\":false},{\"price\":39.99,\"name\":\"Boots\",\"id\":\"4\",\"availability\":true}]";
        Assert.assertEquals(response, expected);
    }
    
    /**
     * Test if a product that not exists returns 404
     * 
     * @throws Exception
     */
    @Test
    @Order(2)
    public void testNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/notExists"))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andReturn();
    }
    
    
    /**
     * Tests if a product that don't exists is returned as empty
     * 
     * @throws Exception
     */
    @Test
    @Order(3)
    public void testEmpty() throws Exception {
    	String response = mockMvc.perform(MockMvcRequestBuilders.get("/product/66/similar"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
    	
    	String expected = "[]";
    	Assert.assertEquals(response, expected);
    }
}
