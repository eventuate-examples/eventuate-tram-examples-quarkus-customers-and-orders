package io.eventuate.examples.tram.ordersandcustomers.orders.web;

import io.eventuate.examples.tram.ordersandcustomers.commondomain.OrderDetails;
import io.eventuate.examples.tram.ordersandcustomers.orders.domain.Order;
import io.eventuate.examples.tram.ordersandcustomers.orders.service.OrderService;
import io.eventuate.examples.tram.ordersandcustomers.orders.webapi.CreateOrderRequest;
import io.eventuate.examples.tram.ordersandcustomers.orders.webapi.CreateOrderResponse;
import io.eventuate.examples.tram.ordersandcustomers.orders.webapi.GetOrderResponse;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/orders")
public class OrderController {

  @Inject
  OrderService orderService;

  @PersistenceContext
  EntityManager entityManager;

  @POST
  public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
    Order order = orderService.createOrder(new OrderDetails(createOrderRequest.getCustomerId(), createOrderRequest.getOrderTotal()));
    return new CreateOrderResponse(order.getId());
  }

  @GET
  @Path("/{orderId}")
  @Transactional
  public GetOrderResponse getOrder(@PathParam("orderId") Long orderId) {
     return Optional.ofNullable(entityManager.find(Order.class, orderId))
            .map(order -> new GetOrderResponse(order.getId(), order.getState()))
            .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
  }
}
