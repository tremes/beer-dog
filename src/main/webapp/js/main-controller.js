
$(document).ready(function() {
    $.get("rest/restaurants", function(data,status){
        var restaurants = JSON.stringify(data);
        data.forEach(function(restaurant){
            $('#pubs').append("<li><a href=./pub-detail.html?id=" + restaurant.id + ">" + restaurant.name + "</a> " + restaurant.address.city + "</li>");
       });
    }); 
});