console.log("Connected");

function GetURLParameter(sParam) {

    var sPageURL = window.location.search.substring(1);
    console.log("Page URL is " + sPageURL);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}

$(document).ready(function() {
    const id = GetURLParameter("id");
    console.log(" ID is " + id);
    $.get("rest/restaurants/" + id, function(data, status) {
       var pub = JSON.stringify(data);
       $('#pubId').text(data.id);
       $('#pubName').text(data.name);     
    });
});
