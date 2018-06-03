console.log("Connected");

$(document).ready(function() {
    const id = FUNCTIONS.getURLParameter("id");
    $.get("rest/restaurants/" + id, function(data, status) {
       $('#pubName').text(data.name);    
       $('#street').text(data.address.street);
       $('#city').text(data.address.city);  
       $('#zipcode').text(data.address.zipcode);   
    });
});
