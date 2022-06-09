Feature: Store Order

  Scenario Outline: Order any available pet

    Given Client perform post operation for end point "store/order"
      | pet status | quantity | status | complete   |
      | available  | 1        | placed | <complete> |
    Then Client should be able to a get the order using end point "store/order/{orderId}"
    Examples:
      | complete |
      | true     |
      | false    |