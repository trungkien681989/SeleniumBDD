Feature: Login Feature
  Verify if user is able to Login in to the site

  Scenario Outline: Welcome message is displayed for authenticated user
    Given user is on homepage
    When user navigates to Login Page
    And user enters username and Password
    Then success "<message>" is displayed

    Examples: 
      | message                                                                                   |
      | Welcome to your account. Here you can manage all of your personal information and orders. |
      | Here you can manage all of your personal information and orders.                          |
      | Welcome to your account.                                                                  |
