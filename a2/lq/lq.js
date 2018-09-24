'use strict';


function Validate() {
    var password = document.getElementById('psw').value;
    var confirmPassword = document.getElementById('psw_conf').value;
    if (password != confirmPassword) {
       document.getElementById('val_error').innerHTML='passwords don\'t match';
       document.getElementById('val_error').style.color='#FF2222';
       document.getElementById('psw_conf').value='';
	}
	else{
	   	document.getElementById('val_error').innerHTML='passwords match';
	   	document.getElementById('val_error').style.color='green';
	}
}


function initMap() {
	var geocoder = new google.maps.Geocoder();
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 8,
      center: {lat: -34.397, lng: 150.644}
    });
    
    geocodeAddress(geocoder, map);
}

function geocodeAddress(geocoder, resultsMap) {
        var address = document.getElementById('country').value +', ' + document.getElementById('city').value +', '  + document.getElementById('address').value;
        
        geocoder.geocode({'address': address}, function(results, status) {
          if (status === 'OK') {
      
          	document.getElementById('address_val').innerHTML='location ok';
       		document.getElementById('address_val').style.color='green';
       		resultsMap.setCenter(results[0].geometry.location);

       		document.getElementById('address_but').style.visibility='visible';

            var marker = new google.maps.Marker({
              map: resultsMap,
              position: results[0].geometry.location
            });
           
          } else {
			document.getElementById('address_val').innerHTML='location doesn\'t exists';
       		document.getElementById('address_val').style.color='#FF2222';
          }
        });
      }

function showmap(){
	document.getElementById('map').style.visibility='visible';
	document.getElementById('address_but').addEventListener('click', function(){ hidemap();});
	document.getElementById('address_but').value='hide map';
}

function hidemap(){
	document.getElementById('map').style.visibility='hidden';
	document.getElementById('address_but').addEventListener('click', function(){ showmap();});
	document.getElementById('address_but').value='show map';

}

function username_regexp_val(){
	var str=document.getElementById('usrnm').value;
	var re = new RegExp("^([A-Za-z]{8,})$");
	if (re.test(str)) {
    document.getElementById('video_sect').style.visibility='visible';
	}else {
    document.getElementById('video_sect').style.visibility='hidden';
    document.getElementById('enable_video').checked = false;
    hidevideo();
	}
}

