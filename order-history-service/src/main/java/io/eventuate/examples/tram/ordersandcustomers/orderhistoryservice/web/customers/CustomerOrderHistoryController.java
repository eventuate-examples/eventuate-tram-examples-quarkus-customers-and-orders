package io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.web.customers;

import io.eventuate.examples.tram.ordersandcustomers.orderhistory.common.CustomerView;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.CustomerViewRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/customers")
public class CustomerOrderHistoryController {

  @Inject
  CustomerViewRepository customerViewRepository;

  @Path("/{customerId}")
  @GET
  public CustomerView getCustomer(@PathParam("customerId") Long customerId) {
    return Optional
            .ofNullable(customerViewRepository.findById(customerId))
            .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
  }
}
