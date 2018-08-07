
    FUNCTIONS.getAll("rest/restaurants").then(function(data){
        data.forEach(function(restaurant){
            $('#pubs').append("<li><a href=./pub-detail.html?id=" + restaurant.id + ">" + restaurant.name + "</a> " + restaurant.address.city + "</li>");
       });
    })
    