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
  Scenario Outline: Create a workshop meeting event from 9:30am to 1:30pm every Friday of the week for next 3 months. And call a stand up to discuss the progress on Monday for 15 minutes.
    Given I have launched the Calendar App
    When It is a working Friday
    And Meeting is between 9:30am to 1:30pm
    Then I want to book a meeting with the title “Workshop”
    And I invite "<WorkshopNumber>" of people
    And I save the meeting
    Then I Check if the meeting is created as expected

    Examples:
      | WorkshopNumber | StandUpNumber |
      | 5              | 4             |

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
