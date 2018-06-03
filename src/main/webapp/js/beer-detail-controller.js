console.log("Connected");

$(document).ready(function() {
    const id = FUNCTIONS.getURLParameter("id");
    $.get("rest/beers/" + id, function(beer, status) {
       $('#beerName').text(beer.name);    
       $('#brewery').text(beer.brewery);
       $('#type').text(beer.type);
       $('#rating').text(beer.rating);
    });
});
