#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Surfing Selection Feature
  Enter the Input Number in the Input dropdown

  @WeatherMapsRegression
  Scenario Outline: As a choosey surfer
    Given I like to surf in any of 2 beaches "<Out_of_top_ten>" of Sydney
    And I only like to surf on <Monday & Friday> in next 16 days
    When I look up the the weather forecast for the next 16 days with POSTAL CODES
    Then I check to if see the temperature is between <10℃ and 20℃>
    And I check wind speed to be between 5 and 15
    And I check to see if UV index is <= 12
    And I Pick (Display in logs/report) the best suitable 2 spots out of top 10 spots, based upon suitable weather forecast for the day
    Examples:
      | Out_of_top_ten |
      | BEACH_1        |
      | BEACH_2        |

  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
