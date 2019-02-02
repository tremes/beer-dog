
var currentBeer = {};

$(document).ready(function() {
    const id = FUNCTIONS.getURLParameter("id");
    $.get(`rest/beers/${id}`, function(beer, status) {
       $('#beerName').text(beer.name);    
       $('#brewery').text(beer.brewery);
       $('#type').text(beer.type);
       $('#rating').text(beer.rating);
    }).done( function(beer) {
       currentBeer = beer;
    });
});

// update beer button handler
$('#updateBeer').click(function(){
    FUNCTIONS.redirectToPage(`update-beer.html?id=${currentBeer.id}`);
});

// remove beer button handler
$("#removeBeer").click(function(){
    $.ajax({
        url: `rest/beers/${currentBeer.id}`,
        type: 'DELETE',
        success: FUNCTIONS.redirectToPage(`pub-detail.html?id=${currentBeer.restaurant.id}`),
      });
});

$("#backToPub").click(function(){
    FUNCTIONS.redirectToPage(`pub-detail.html?id=${currentBeer.restaurant.id}`);
});
