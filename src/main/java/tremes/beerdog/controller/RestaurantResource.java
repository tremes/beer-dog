package tremes.beerdog.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tremes.beerdog.model.Address;
import tremes.beerdog.model.Restaurant;
import tremes.beerdog.service.RestaurantService;

@Path("/restaurants")
@RequestScoped
public class RestaurantResource {

  @Inject RestaurantService restaurantService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Restaurant> getRestaurants() {
    return restaurantService.getRestaurants();
  }

  @POST
  @Path("/create")
  public void addRestaurant(@FormParam("name") String name, @FormParam("street") String street, @FormParam("zipcode") String zipcode, @FormParam("city") String city) {
    Address newAddress = new Address(street, city, zipcode);
    Restaurant newRestaurant = new Restaurant(name, newAddress);
    restaurantService.addNew(newRestaurant);
  }

}
