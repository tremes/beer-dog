

const pubId = FUNCTIONS.getURLParameter("rest_id");

// we know for which pub we want to add new beer
if (pubId !== undefined) {
    FUNCTIONS.getById("rest/restaurants", pubId).done(function(pub){
        $("#rest_id").append("<option value="+ pub.id + ">" +  pub.name + "</option>")
    });
    
} else {
    FUNCTIONS.getAll("rest/restaurants").then(function(restaurants){
        restaurants.forEach(restaurant => {
            $('#rest_id').append("<option value= " + restaurant.id + ">" + restaurant.name + "</option>");
        });
    });
}

FUNCTIONS.addNew('#newBeerForm', 'beer-detail.html');