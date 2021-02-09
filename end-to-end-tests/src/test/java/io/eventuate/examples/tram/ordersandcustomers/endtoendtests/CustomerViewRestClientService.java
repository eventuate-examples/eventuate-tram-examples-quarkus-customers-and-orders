package io.eventuate.examples.tram.ordersandcustomers.endtoendtests;

import io.eventuate.examples.tram.ordersandcustomers.orderhistory.common.CustomerView;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/customers")
@RegisterRestClient(configKey="customer-view-api")
public interface CustomerViewRestClientService {

  @Path("/{customerId}")
  @GET
  CustomerView getCustomer(@PathParam("customerId") Long customerId);
}
