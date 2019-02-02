
var FUNCTIONS = {}

FUNCTIONS.addNew = function(id, pageToGo){

    $(id).submit(function(event) {
        event.preventDefault(); //prevent default action 
        let post_url = $(this).attr("action"); //get form action url
        let form_data = $(this).serialize(); //Encode form elements for submission
              
        $.post(post_url, form_data).then(function(data, status, jqxhr){
            let location = jqxhr.getResponseHeader("location");
            let uriArr  = location.split("/");
            let id = uriArr[uriArr.length - 1];
            window.location.replace(pageToGo + "?id=" + id);
        });
    });
}

/**
 * Send get request to provided URI and return the result.
 * @param {*} uri - uri to send get request to
 */
FUNCTIONS.getAll = function(uri){
    return $.get(uri);
}

FUNCTIONS.getById = function(url, id){
   return $.get(url + "/" + id);
}

/**
 * Extracts and returns url param from actual url location
 * @param {*} sParam - name of the param to extract
 */
FUNCTIONS.getURLParameter = function(sParam) {

    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}

FUNCTIONS.redirectToPage = function(page) {
    window.location.replace(page);
}