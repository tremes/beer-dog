$('#newPubForm').submit(function(event){
    
    event.preventDefault(); //prevent default action 
    var post_url = $(this).attr("action"); //get form action url
    var form_data = $(this).serialize(); //Encode form elements for submission

    $.post( post_url, form_data, function( response ) {
         window.location.replace("index.html");
     });
});