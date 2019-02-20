

const beerId = FUNCTIONS.getURLParameter("id");

FUNCTIONS.getById("rest/beers", beerId).done(function(beer){
    $("#beerName").val(beer.name);
    $("#brewery").val(beer.brewery);
    $("#rest_id").append(`<option value=${beer.restaurant.id}>${beer.restaurant.name}</option>`);
    FUNCTIONS.getAll("rest/restaurants").then(function(restaurants) {
        // remove original restaurant from the list of all restaurants
        const pubToRemove = restaurants.find(r => r.id === beer.restaurant.id);
        const index = restaurants.indexOf(pubToRemove);
        restaurants.splice(index, 1);
        restaurants.forEach(r => {
            $('#rest_id').append(`<option value=${r.id}>${r.name}</option>`);
        });
    });
});

FUNCTIONS.update("#updateBeerForm", beerId, "beer-detail.html");
