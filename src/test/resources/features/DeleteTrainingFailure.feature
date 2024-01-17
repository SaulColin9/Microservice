Feature: the training can not be deleted
  Scenario: client makes call to DELETE /training/workload/100
    When the client calls training/workload/100
    Then the client receives a status code of 403
    And receives a message and a null training