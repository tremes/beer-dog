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

import tremes.beerdog.model.Address;
import tremes.beerdog.model.Restaurant;
import tremes.beerdog.service.RestaurantService;

@Path("/restaurants")
@RequestScoped
public class RestaurantResource {

    @Inject
    RestaurantService restaurantService;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getRestaurantById(@PathParam("id") Integer id) {
        Restaurant restaurant = restaurantService.getRestaurantById(Long.valueOf(id));

        if (restaurant == null) {
            return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
        }

        return Response.status(javax.ws.rs.core.Response.Status.OK).entity(restaurant).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRestaurant(@FormParam("name") @NotBlank(message = "Pub name must not be empty!") String name, @FormParam("street") String street, @FormParam("zipcode") String zipcode,
                                  @FormParam("city") String city) {
        Address newAddress = new Address(street, city, zipcode);
        Restaurant newRestaurant = new Restaurant(name, newAddress);
        restaurantService.addNew(newRestaurant);

        URI uri = uriInfo.getAbsolutePathBuilder().path(newRestaurant.getId().toString()).build();
        Response res = Response.created(uri).build();
        return res;
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRestaurant(@FormParam("name") @NotBlank(message = "Pub name must not be empty!") String name, @FormParam("street") String street, @FormParam("zipcode") String zipcode,
                                     @FormParam("city") String city, @PathParam("id") Long id) {
        Restaurant restToUpdate = restaurantService.getRestaurantById(id);
        if (restToUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        restToUpdate.setName(name);
        Address address = restToUpdate.getAddress();
        address.setCity(city);
        address.setStreet(street);
        address.setZipcode(zipcode);
        restToUpdate.setAddress(address);
        restaurantService.updateRestaurent(restToUpdate);
        return Response.status(Response.Status.OK).entity(restToUpdate).build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeRestaurant(@PathParam("id") Long id) {
        Restaurant restToRemove = restaurantService.getRestaurantById(id);
        if (restToRemove == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        restaurantService.removeRestaurant(restToRemove);
        return Response.status(Response.Status.OK).build();
    }

}
