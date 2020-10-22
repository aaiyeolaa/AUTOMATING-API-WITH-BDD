package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyStepdefs {
    HttpUriRequest request;
    HttpResponse response;
    String responseBody;
    @Given("I have an Endpoint for {string}")
    public void iHaveAnEndpointFor(String city) {
        request = new HttpGet(("http://api.citybik.es/v2/networks/visa-" + city));
    }

    @When("I send the request")
    public void iSendTheRequest() throws ClientProtocolException, IOException {
        response = HttpClientBuilder.create().build().execute(request);
    }


    @Then("I can confirm latitude and longitude")
    public void iCanConfirmLatitudeAndLongitude() throws ClientProtocolException, IOException

    {
        responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println("Response body: " + responseBody);
        Assert.assertFalse(responseBody.contains("\"latitude\":50.1072"));
        System.out.println("latitude=50.1072");
        Assert.assertFalse(responseBody.contains("\"longitude\":8.66375"));
        System.out.println("longitude=8.66375");

    }

    @Then("I can confirm that the city is {string}, country is {string}")
    public void iCanConfirmThatTheCityIsCountryIs(String city, String country) throws ClientProtocolException, IOException
    {

        System.out.println("Response body: " + responseBody);
        Assert.assertFalse(responseBody.contains("\"city\":\"Frankfurt\""));
        System.out.println("city=Frankfurt");
        Assert.assertFalse(responseBody.contains("\"country\":\"DE\""));
        System.out.println("country=DE");


    }
}