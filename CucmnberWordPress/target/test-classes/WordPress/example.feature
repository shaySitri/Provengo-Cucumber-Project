Feature: Testing how Disallowed Comment key affect new Comments

    Scenario: Admin Login to Site
    Given Admin on Login Page
    When Admin enter Username <UserName> and Password <Password>
    And Admin press Log in Button
    Then Admin moved to wp-admin Page

    Scenario: Admin Enter to new Disallowed Comment Keys
    Given Admin on wp-admin Page And Admin Logged in
    When Admin press Settings button
    And Admin press Discussion
    And Admin Insert new Disallowed Comment Key Word <Word>
    And Admin press Save Changes
    Then Discussion settings updated

    Scenario: Guest Enter to site
    Given Guest on Login page
    When User press Go To Wordpress button
    Then Guest moved to Posts Page

    Scenario: Guest Enter to Post
    Given Guest on Posts Main Page
    When Guest press Post Title <Title>
    Then Post Content and Comments are displayed

    Scenario: Guest Writing Comment
    Given Guest on Post Page
    When User enters Comment <Comment>
    And Comment contain Disallowed Comment Key <Word>
    Then Comment moved to Trash and not displayed on Page

