Feature: the training can be deleted
  Scenario: client makes call to DELETE /training/workload/10
    When the client calls training/workload/10
    Then the client receives a status code of 200
    And the deleted training is returned