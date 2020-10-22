Feature: Verify City Frankfurt is in Germany

  Scenario: Verify City
    Given I have an Endpoint for "visa-frankfurt"
    When I send the request
    Then I can confirm latitude and longitude
    Then I can confirm that the city is "Frankfurt", country is "DE"


