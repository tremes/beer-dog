
var FUNCTIONS = {}

FUNCTIONS.addNew = function(id, pageToGo) {

    $(id).submit(function(event) {
        event.preventDefault(); //prevent default action 
        let post_url = $(this).attr("action"); //get form action url
        let form_data = $(this).serialize(); //Encode form elements for submission
              
        $.post(post_url, form_data).then(function(data, status, jqxhr){
            let location = jqxhr.getResponseHeader("location");
            let uriArr  = location.split("/");
            let id = uriArr[uriArr.length - 1];
            window.location.replace(pageToGo + "?id=" + id);
        }).catch(function (error) {
           if(error.responseText.includes("arg0")) {
               // we get [PARAMETER]\r[addRestaurant.arg0]\r[The Pub name must not be empty!]\r[]\r\r
               const errMsg = error.responseText.split('\r')[2].replace(/[\[\]]/g,'');
               $("#arg0err").text(errMsg);
           }
        });
    });
}

FUNCTIONS.update = function(formId, itemId, pageToGo) {
    $(formId).submit(function(event) {
        event.preventDefault(); 
        let postUrl = $(this).attr("action"); //get form action url
        let formData = $(this).serialize(); //Encode form elements for submission
        $.ajax({
            url:`${postUrl}/${itemId}`,
            type: 'PUT',
            data: formData,
        }).then( () => {
           FUNCTIONS.redirectToPage(`${pageToGo}?id=${itemId}`);
        }).catch( (error) => {
            if(error.responseText.includes("arg0")) {
                // we get [PARAMETER]\r[addRestaurant.arg0]\r[The Pub name must not be empty!]\r[]\r\r
                const errMsg = error.responseText.split('\r')[2].replace(/[\[\]]/g,'');
                $("#arg0err").text(errMsg);
            }
        });
    });
}

/**
 * Send get request to provided URI and return the result.
 * @param {*} uri - uri to send get request to
 */
FUNCTIONS.getAll = function(uri) {
    return $.get(uri);
}

FUNCTIONS.getById = function(url, id) {
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