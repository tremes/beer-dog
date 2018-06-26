console.log("Connected");

var currentBeer = {};

$(document).ready(function() {
    const id = FUNCTIONS.getURLParameter("id");
    $.get("rest/beers/" + id, function(beer, status) {
       $('#beerName').text(beer.name);    
       $('#brewery').text(beer.brewery);
       $('#type').text(beer.type);
       $('#rating').text(beer.rating);
    }).done( function(beer) {
       currentBeer = beer;
    });
});

function redirectToPub(){
    window.location.replace("pub-detail.html?id=" + currentBeer.restaurant.id);
}

$("#removeBeer").click(function(){
    let id =  FUNCTIONS.getURLParameter("id");
    $.ajax({
        url: "rest/beers/" + id,
        type: 'DELETE',
        success: redirectToPub,
      });
});
