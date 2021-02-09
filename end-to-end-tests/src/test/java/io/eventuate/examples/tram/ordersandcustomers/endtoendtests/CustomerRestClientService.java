package io.eventuate.examples.tram.ordersandcustomers.endtoendtests;


import io.eventuate.examples.tram.ordersandcustomers.customers.webapi.CreateCustomerRequest;
import io.eventuate.examples.tram.ordersandcustomers.customers.webapi.CreateCustomerResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/customers")
@RegisterRestClient(configKey="customer-api")
public interface CustomerRestClientService {
  @POST
  CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);
}
