

const pubId = FUNCTIONS.getURLParameter("id");

FUNCTIONS.getById("rest/restaurants", pubId).done(function(pub){
    $("#pubName").val(pub.name);
    $("#pubStreet").val(pub.address.street);
    $("#pubCity").val(pub.address.city);
    $("#pubZipCode").val(pub.address.zipcode);
});

FUNCTIONS.update("#updatePubForm", pubId, "pub-detail.html");

