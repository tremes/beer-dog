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

import tremes.beerdog.model.Beer;
import tremes.beerdog.model.Restaurant;
import tremes.beerdog.service.BeerService;
import tremes.beerdog.service.RestaurantService;

@Path("/beers")
@RequestScoped
public class BeerResource {

  @Inject BeerService beerService;

  @Inject RestaurantService restaurantService;

  @GET
  @Produces({ MediaType.APPLICATION_JSON})
  public List<Beer> getBeers() {
    return beerService.getBeers();
  }

  @POST
  @Path("/create")
  public void addBeer(@FormParam("name") String name, @FormParam("brewery") String brewery, @FormParam("rest_id") Long id) {
    Restaurant restaurant = restaurantService.getRestaurantById(id);
    Beer beer = new Beer(name, brewery, restaurant);
    beerService.addNew(beer);
  }
}
