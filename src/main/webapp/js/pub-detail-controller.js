console.log("Connected");

$(document).ready(function() {
    const id = FUNCTIONS.getURLParameter("id");

    //load restaurant by id and fill in details
    $.get("rest/restaurants/" + id, function(pub, status) {
        $('#pubName').text(pub.name);    
        $('#street').text(pub.address.street);
        $('#city').text(pub.address.city);  
        $('#zipcode').text(pub.address.zipcode); 
    });

    // load beers for particular restaurant
    $.get("rest/beers?rest_id=" + id, function(beers, status) {
        beers.forEach(beer => {
            $('#beers').append("<li><a href=./beer-detail.html?id=" + beer.id + ">" + beer.name + "</a> " + beer.brewery + "</li>");
        });
    });

});
