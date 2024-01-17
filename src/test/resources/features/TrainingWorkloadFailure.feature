Feature: the training workload can be retrieved
  Scenario: client makes call to POST /training/workload/John.Doe
    When the client calls /training/workload/John.Doe
    Then the client receives null trainer and empty summary