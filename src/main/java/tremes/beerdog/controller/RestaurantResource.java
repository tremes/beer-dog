package tremes.beerdog.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.sql.SQLOutput;
import java.util.List;

import tremes.beerdog.model.Address;
import tremes.beerdog.model.Restaurant;
import tremes.beerdog.service.RestaurantService;

@Path("/restaurants")
@RequestScoped
public class RestaurantResource {

  @Inject RestaurantService restaurantService;

  @Context UriInfo uriInfo;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Restaurant> getRestaurants() {
    return restaurantService.getRestaurants();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response getConfigurationById(@PathParam("id") Integer id) {
    Restaurant restaurant = restaurantService.getRestaurantById(Long.valueOf(id));

    if (restaurant == null) {
      return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
    }

    return Response.status(javax.ws.rs.core.Response.Status.OK).entity(restaurant).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response addRestaurant(@FormParam("name") String name, @FormParam("street") String street, @FormParam("zipcode") String zipcode,
          @FormParam("city") String city) {
    Address newAddress = new Address(street, city, zipcode);
    Restaurant newRestaurant = new Restaurant(name, newAddress);
    restaurantService.addNew(newRestaurant);

    URI uri = uriInfo.getAbsolutePathBuilder().path(newRestaurant.getId().toString()).build();
    Response res = Response.created(uri).build();
    return res;
  }

}
