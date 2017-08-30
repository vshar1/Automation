Feature: Vehicle information features
  Scenario: Open vehicle-information website
    Given I open vehicle information website
    When I navigate to vehicle enquiry page
    Then I can assert the vehicle details with csv file
