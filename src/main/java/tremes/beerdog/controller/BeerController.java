package tremes.beerdog.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tremes.beerdog.model.Beer;
import tremes.beerdog.service.BeerService;

@Path("/beer")
public class BeerController {

  @Inject BeerService beerService;

  @GET
  @Produces({ MediaType.APPLICATION_JSON})
  public List<Beer> getBeers() {
    return beerService.getBeers();
  }

  @POST
  @Path("/create")
  public void insertBeer(@FormParam("name") String name, @FormParam("brewery") String brewery) {
    Beer beer = new Beer(name, brewery);
    beerService.createBeer(beer);
  }
}
