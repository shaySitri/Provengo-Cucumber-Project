Feature: Testing how disallowed comment key affect new comments

    Scenario: Admin add disallowed comment keys
    Given Admin logged in
    When Admin on admin dashboard
    And  Admin navigate to discussion settings
    And Admin insert new disallowed comment key word <word>
    Then discussion settings updated

    Scenario: Guest writing comment on a post
    Given Post page exist
    When Guest click on post with title
    When Guest enters Comment <Comment>
    And  Guest post his comment
    Then New comment added to post page

    Scenario: Guest writing comment contain disallowed word on a post
    Given Admin disallowed comment key word <word>
    When Guest click on post with title
    And Guest add comment contains <word>
    And Guest post his disallowed comment
    Then New comment transfer to trash



