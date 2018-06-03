package tremes.beerdog.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;
import java.sql.SQLOutput;
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

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response geBeerById(@PathParam("id") Integer id) {
    Beer beer = beerService.getBeerById(Long.valueOf(id));

    if (beer == null) {
      return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
    }

    return Response.status(javax.ws.rs.core.Response.Status.OK).entity(beer).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response addBeer(@FormParam("beerName") String name, @FormParam("brewery") String brewery, @FormParam("pubs") Long id) {
    Restaurant restaurant = restaurantService.getRestaurantById(id);
    Beer beer = new Beer(name, brewery, restaurant);
    beerService.addNew(beer);
    URI uri = uriInfo.getAbsolutePathBuilder().path(beer.getId().toString()).build();
    Response res = Response.created(uri).build();
    return res;
  }
}
