package tremes.beerdog.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import tremes.beerdog.model.Beer;
import tremes.beerdog.model.Restaurant;
import tremes.beerdog.service.BeerService;
import tremes.beerdog.service.RestaurantService;

@Path("/beers")
@RequestScoped
public class BeerResource {

    @Inject
    BeerService beerService;

    @Inject
    RestaurantService restaurantService;

    @Context
    UriInfo uriInfo;

    /**
     * @param id - id of beer
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response geBeerById(@PathParam("id") Integer id) {
        Beer beer = beerService.getBeerById(Long.valueOf(id));

        if (beer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(beer).build();
    }

    /**
     * @param rest_id - id of restaurant to get the beers for
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @QueryParam("rest_id")
    public List<Beer> geBeerByRestId(@QueryParam("rest_id") Integer rest_id) {
        if (rest_id == null) {
            return beerService.getBeers();
        } else {
            List<Beer> beers = beerService.getBeerByRestaurant(Long.valueOf(rest_id));
            return beers;
        }
    }

    /**
     * Create a new beer
     * @param name - name of the beer
     * @param brewery - brewery producing/brewing the beer
     * @param id - id of a restaurant which the beer is added to
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBeer(@FormParam("beerName") @NotBlank(message = "Beer name must not be empty!") String name, @FormParam("brewery") String brewery, @FormParam("pubs") Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        Beer beer = new Beer(name, brewery, restaurant);
        beerService.addNew(beer);
        URI uri = uriInfo.getAbsolutePathBuilder().path(beer.getId().toString()).build();
        Response res = Response.created(uri).build();
        return res;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBeer(@FormParam("beerName") @NotBlank(message = "Beer name must not be empty!") String name, @FormParam("brewery") String brewery, @PathParam("id") Long id, @FormParam("pubs") Long restaurantId){
        Beer beerToUpdate = beerService.getBeerById(id);
        if (beerToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        beerToUpdate.setName(name);
        beerToUpdate.setBrewery(brewery);
        beerToUpdate.setRestaurant(restaurantService.getRestaurantById(restaurantId));
        beerService.updateBeer(beerToUpdate);
        return Response.status(Response.Status.OK).entity(beerToUpdate).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeBeer(@PathParam("id") Long id) {
        Beer beerToRemove = beerService.getBeerById(id);
        if (beerToRemove == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        beerService.removeBeer(beerToRemove);
        return Response.status(Response.Status.OK).build();
    }
}
