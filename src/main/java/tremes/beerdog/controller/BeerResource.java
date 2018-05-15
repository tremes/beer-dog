package tremes.beerdog.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import tremes.beerdog.model.Beer;
import tremes.beerdog.model.Restaurant;
import tremes.beerdog.service.BeerService;
import tremes.beerdog.service.RestaurantService;

@Path("/beers")
@RequestScoped
public class BeerResource {

  @Inject BeerService beerService;

  @Inject RestaurantService restaurantService;

  @Context
  UriInfo uriInfo;


  @GET
  @Produces({ MediaType.APPLICATION_JSON})
  public List<Beer> getBeers() {
    return beerService.getBeers();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response addBeer(@FormParam("name") String name, @FormParam("brewery") String brewery, @FormParam("rest_id") Long id) {
    Restaurant restaurant = restaurantService.getRestaurantById(id);
    Beer beer = new Beer(name, brewery, restaurant);
    beerService.addNew(beer);
    Link lnk = Link.fromUri(uriInfo.getPath()).rel("self").build();
    return Response.seeOther(lnk.getUri()).build();
  }
}
