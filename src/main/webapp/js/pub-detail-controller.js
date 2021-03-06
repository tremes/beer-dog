

var currentPub = {}

$(document).ready(function() {
    const id = FUNCTIONS.getURLParameter("id");

    //load restaurant by id and fill in details
    $.get(`rest/restaurants/${id}`, function(pub, status) {
        $('#pubName').text(pub.name);    
        $('#street').text(pub.address.street);
        $('#city').text(pub.address.city);  
        $('#zipcode').text(pub.address.zipcode); 
    }).done(function(pub){
        currentPub = pub;
    });

    // load beers for particular restaurant
    $.get(`rest/beers?rest_id=${id}`, function(beers, status) {
        beers.forEach(beer => {
            $('#beers').append(`<li><a href=./beer-detail.html?id=${beer.id}>${beer.name}</a> ${beer.brewery}</li>`);
        });
    });

});

//update pub button handler
$('#updatePub').click(function(){
    FUNCTIONS.redirectToPage(`update-pub.html?id=${currentPub.id}`)
});

// delete pub button handler
$("#removePub").click(function() {
    $.ajax({
        url: `rest/restaurants/${currentPub.id}`,
        type: 'DELETE',
        success: FUNCTIONS.redirectToPage("index.html"),
      });
});

// add new beer button handler
$("#addBeer").click(function() {
    FUNCTIONS.redirectToPage(`new-beer.html?rest_id=${currentPub.id}`);
});