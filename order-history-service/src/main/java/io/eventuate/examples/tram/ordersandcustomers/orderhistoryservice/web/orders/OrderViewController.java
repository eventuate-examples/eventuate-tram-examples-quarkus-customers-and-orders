package io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.web.orders;

import io.eventuate.examples.tram.ordersandcustomers.orderhistory.common.OrderView;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.OrderViewRepository;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Path("/orders")
public class OrderViewController {

  @Inject
  OrderViewRepository orderViewRepository;

  @Path("/{orderId}")
  public OrderView getCustomer(@PathParam("orderId") Long orderId) {
    return orderViewRepository
            .findById(orderId)
            .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
  }
}
