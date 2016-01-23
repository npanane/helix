/**
 * Created by nuwan.n.bandara on 9/15/2014.
 */
var address;
var city;
var state;
var zip;
var lat;
var lng;



function getAddressCoordinates(){

    $.ajax({
        url: "https://maps.googleapis.com/maps/api/geocode/json",
        type: "GET",
        data: {address:address+city+state+zip,key:"AIzaSyCnbvJp-UZM-50G8PpXA0YDX9r7z5GH45E"},
        dataType: "json",
        success: function (result) {
            lat =result.results[0].geometry.location.lat;
            lng =result.results[0].geometry.location.lng;


        },
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        }
    });



}

function getClientAddressInfo(){

    var clientId=document.getElementById("selectClient").value;

    $.ajax({
        url: 'clientsMap/loadAddressDetails',
        data: ({clientId:clientId}),
        success: function(data) {
            getAddressDetails(data);
        }
    });

}

function getAddressDetails(data) {

    var addressDetails = data.getElementsByTagName("details");
    if (addressDetails == null || addressDetails.length < 1) {
        addressDetails = data.getElementsByTagName("address/details");
    }
    address=addressDetails[0].getAttribute("address");
    city=addressDetails[0].getAttribute("city");
    state=addressDetails[0].getAttribute("state");
    zip=addressDetails[0].getAttribute("zip");

    getAddressCoordinates();
    loadScript();


}


function gmap_default_init() {
  /*  var mapOptions = {

        zoom: 15,
        center: new google.maps.LatLng(lat,lng),
        markers: [{
            latitude: 7.3340002,

            longitude:  80.4782342,
            html: "name",
            popup: true
        }]

    };


    var map = new google.maps.Map(document.getElementById('gmap-default'), mapOptions);*/


    var myLatlng = new google.maps.LatLng(lat,lng);
    var mapOptions = {
        zoom: 15,
        center: myLatlng
    }
    var map = new google.maps.Map(document.getElementById('gmap-default'), mapOptions);

    var marker = new google.maps.Marker({
        position: myLatlng,
        map: map,
        title:address+','+city+','+state
    });

}


function gmap_initialize() {
   gmap_default_init();

}

function loadScript() {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = 'https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=weather&sensor=false&' + 'callback=gmap_initialize';
    document.body.appendChild(script);
}

