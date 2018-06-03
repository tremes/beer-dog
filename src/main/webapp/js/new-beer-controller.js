

FUNCTIONS.getAll("rest/restaurants").then(function(restaurants){
    restaurants.forEach(restaurant => {
        $('#rest_id').append("<option value= " + restaurant.id + ">" + restaurant.name + "</option>");
    });
});

FUNCTIONS.addNew('#newBeerForm', 'beer-detail.html');