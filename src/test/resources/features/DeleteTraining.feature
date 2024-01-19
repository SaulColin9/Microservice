Feature: the training can be deleted

  Scenario: client makes call to DELETE /training/workload/id
    When the client calls training/workload/id
    Then the client receives a status code of 200 when deleted
    And the deleted training is returned

  Scenario: client makes call to DELETE /training/workload/100 and fails
    When the client calls endpoint training/workload/100
    Then receives a message and a null training