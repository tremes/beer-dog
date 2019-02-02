

const pubId = FUNCTIONS.getURLParameter("id");

FUNCTIONS.getById("rest/restaurants", pubId).done(function(pub){
    $("#pubName").val(pub.name);
    $("#pubStreet").val(pub.address.street);
    $("#pubCity").val(pub.address.city);
    $("#pubZipCode").val(pub.address.zipcode);
});

$("#updatePubForm").submit(function(event) {
    event.preventDefault(); //prevent default action 
    let postUrl = $(this).attr("action"); //get form action url
    let formData = $(this).serialize(); //Encode form elements for submission
    $.ajax({
        url:`${postUrl}/${pubId}`,
        type: 'PUT',
        data: formData,
        success: FUNCTIONS.redirectToPage(`pub-detail.html?id=${pubId}`)
    });
});
    

