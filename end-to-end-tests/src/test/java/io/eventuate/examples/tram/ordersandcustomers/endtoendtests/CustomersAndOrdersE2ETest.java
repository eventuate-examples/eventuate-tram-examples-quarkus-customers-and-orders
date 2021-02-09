package io.eventuate.examples.tram.ordersandcustomers.endtoendtests;

import io.eventuate.examples.tram.ordersandcustomers.commondomain.Money;
import io.eventuate.examples.tram.ordersandcustomers.commondomain.OrderState;
import io.eventuate.examples.tram.ordersandcustomers.customers.webapi.CreateCustomerRequest;
import io.eventuate.examples.tram.ordersandcustomers.orderhistory.common.CustomerView;
import io.eventuate.examples.tram.ordersandcustomers.orderhistory.common.OrderInfo;
import io.eventuate.examples.tram.ordersandcustomers.orderhistory.common.OrderView;
import io.eventuate.examples.tram.ordersandcustomers.orders.webapi.CreateOrderRequest;
import io.eventuate.util.test.async.Eventually;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomersAndOrdersE2ETest{

  @RestClient
  CustomerRestClientService customerRestClientService;

  @RestClient
  OrderRestClientService orderRestClientService;

  @RestClient
  CustomerViewRestClientService customerViewRestClientService;

  @RestClient
  OrderViewRestClientService orderViewRestClientService;

  @Test
  public void shouldApprove() {
    Long customerId = createCustomer("Fred", new Money("15.00"));
    Long orderId = createOrder(customerId, new Money("12.34"));
    assertOrderState(orderId, OrderState.APPROVED);
  }

  @Test
  public void shouldReject() {
    Long customerId = createCustomer("Fred", new Money("15.00"));
    Long orderId = createOrder(customerId, new Money("123.34"));
    assertOrderState(orderId, OrderState.REJECTED);
  }

  @Test
  public void shouldRejectForNonExistentCustomerId() {
    Long customerId = System.nanoTime();
    Long orderId = createOrder(customerId, new Money("123.34"));
    assertOrderState(orderId, OrderState.REJECTED);
  }

  @Test
  public void shouldRejectApproveAndKeepOrdersInHistory() {
    Long customerId = createCustomer("John", new Money("1000"));

    Long order1Id = createOrder(customerId, new Money("100"));

    assertOrderState(order1Id, OrderState.APPROVED);

    Long order2Id = createOrder(customerId, new Money("1000"));

    assertOrderState(order2Id, OrderState.REJECTED);

    Eventually.eventually(100, 400, TimeUnit.MILLISECONDS, () -> {
      CustomerView customerView = getCustomerView(customerId);

      Map<String, OrderInfo> orders = customerView.getOrders();

      assertEquals(2, orders.size());

      assertEquals(orders.get(String.valueOf(order1Id)).getState(), OrderState.APPROVED);
      assertEquals(orders.get(String.valueOf(order2Id)).getState(), OrderState.REJECTED);
    });
  }

  private CustomerView getCustomerView(Long customerId) {
    CustomerView customerView = customerViewRestClientService.getCustomer(customerId);
    assertNotNull(customerView);
    return customerView;
  }


  private Long createCustomer(String name, Money credit) {
    return customerRestClientService.createCustomer(new CreateCustomerRequest(name, credit)).getCustomerId();
  }

  private Long createOrder(Long customerId, Money orderTotal) {
    return orderRestClientService.createOrder(new CreateOrderRequest(customerId, orderTotal)).getOrderId();
  }

  private void assertOrderState(Long id, OrderState expectedState) {
    Eventually.eventually(100, 400, TimeUnit.MILLISECONDS, () -> {
      OrderView orderView = orderViewRestClientService.getOrder(id);

      assertNotNull(orderView);

      assertEquals(expectedState, orderView.getState());
    });
  }
}
