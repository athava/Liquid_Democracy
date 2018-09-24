'use strict';

//mono gia to confirmation tou password
function conf_psw_regex_validate() {
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

function for_username(thisthis, error_msg){
    if( check_pattern_validity(thisthis, error_msg)){
        document.getElementById('video_sect').style.visibility='visible';
    }else{
        document.getElementById('video_sect').style.visibility='hidden';
            document.getElementById('enable_video').checked = false;
            hidevideo();
    }
    
}

function check_pattern_validity(thisthis, error_msg){
    if(thisthis.checkValidity() == false){
       
       document.getElementById(error_msg).innerHTML='pattern missmatch';
       document.getElementById(error_msg).style.color='#FF2222';
       thisthis.value='';
       return false;
    }
    else{
       document.getElementById(error_msg).innerHTML='ok';
       document.getElementById(error_msg).style.color='green';
       return true;
       
    }
}

//einai gia na elegxei ama to username kai to email pou eisagei o xrisths thn register forma iparxein idi sto database h an einai elefthera 
function username_email_database_validation_ajax(id, error_msg){
    var element = document.getElementById(id).name + '=' + document.getElementById(id).value;
    var xhttp = new  XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
           
        }else if(xhttp.status == 445){
            document.getElementById(error_msg).innerHTML=document.getElementById(id).name + ' already exists';
            document.getElementById(error_msg).style.color='#FF2222';
            document.getElementById(id).value='';
        }
    };
    xhttp.open('POST', 'username_email_valid');
    xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    
    xhttp.send(element);
}

//tsekarei poia fields einai required kai den exoun simplirwthei kai an den einai ola sumpliromena den proxoraei sto server side validation
function required_fields() {
    var errorFound = true;
    if((document.getElementById('usrnm').value.length) < 1){
       document.getElementById('usrnm_err').innerHTML='required field';
       document.getElementById('usrnm_err').style.color='#FF2222';
       errorFound = false;
    }
    if((document.getElementById('email').value.length) < 1){
       document.getElementById('email_err').innerHTML='required field';
       document.getElementById('email_err').style.color='#FF2222';
       errorFound = false;
    }
    if((document.getElementById('psw').value.length) < 1){
       document.getElementById('psw_err').innerHTML='required field';
       document.getElementById('psw_err').style.color='#FF2222';
       errorFound = false;
    }
    if((document.getElementById('psw_conf').value.length) < 1){
       document.getElementById('val_error').innerHTML='required field';
       document.getElementById('val_error').style.color='#FF2222';
       errorFound = false;
    }
    if((document.getElementById('fname').value.length) < 1){
       document.getElementById('fname_err').innerHTML='required field';
       document.getElementById('fname_err').style.color='#FF2222';
    }
    if((document.getElementById('lname').value.length) < 1){
       document.getElementById('lname_err').innerHTML='required field';
       document.getElementById('lname_err').style.color='#FF2222';
       errorFound = false;
    }
    if((document.getElementById('birthday').value.length) < 1){
       document.getElementById('birthday_err').innerHTML='required field';
       document.getElementById('birthday_err').style.color='#FF2222';
       errorFound = false;
    }
    if((document.getElementById('country').value.length) < 1){
       document.getElementById('country_err').innerHTML='required field';
       document.getElementById('country_err').style.color='#FF2222';
       errorFound = false;
    }
    if((document.getElementById('city').value.length) < 1){
       document.getElementById('city_err').innerHTML='required field';
       document.getElementById('city_err').style.color='#FF2222';
       errorFound = false;
    }
    if((document.getElementById('city').value.length) < 1){
       document.getElementById('city_err').innerHTML='required field';
       document.getElementById('city_err').style.color='#FF2222';
       errorFound = false;
    }
    if((document.getElementById('occupation').value.length) < 1){
       document.getElementById('occupation_err').innerHTML='required field';
       document.getElementById('occupation_err').style.color='#FF2222';
       errorFound = false;
    }
    if(errorFound){
        validator_ajax();
    }
    
}

window.onload = already_loged_in;


//an o xrisths einai idi sindemenes se paei sto personal info alliws athn selida gia login
function already_loged_in(){
   
    var xhttp = new  XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById('mybody').innerHTML=xhttp.responseText;
        }
    };
    xhttp.open('POST', 'already_loged_in');
    xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhttp.send('session');
}

//kanei to validation se server-side kai an ola einai kala kanei add to user sth database
function validator_ajax(){
    var gender;
    var tempgen = document.getElementsByName('gender');
    for(var i=0; i<3; i++){
        if(tempgen[i].checked){
            gender=tempgen[i].value;
        }
    }
    var elements ='username='+ document.getElementById('usrnm').value+'&email='+ document.getElementById('email').value+'&password='+document.getElementById('psw').value+'&conf_password='+document.getElementById('psw_conf').value+'&firstname='+document.getElementById('fname').value+'&lastname='+document.getElementById('lname').value+'&birthday='+document.getElementById('birthday').value+'&gender='+gender+'&country='+document.getElementById('country').value+'&city='+document.getElementById('city').value+'&address='+document.getElementById('address').value+'&occupation='+document.getElementById('occupation').value+'&interests='+document.getElementById('interests').value+'&gen_info='+document.getElementById('gen_info').value; 
   
    var xhttp = new  XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
           
           document.getElementById('mybody').innerHTML=xhttp.responseText;
           document.getElementById('welcome_msg').innerHTML = 'User is successfully created';
           document.getElementById('welcome_msg').style.color = 'green';
        }else if(xhttp.status == 445){
            document.getElementById('server-side-error').innerHTML= xhttp.responseText;
        }
    };
    xhttp.open('POST', 'validator');
    xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    
    xhttp.send(elements);
}


//gia na kanei log in enas xrisths pou einai idi registered
function login_ajax(){
    var user = 'username='+ document.getElementById('usrnm').value+'&password='+document.getElementById('psw').value;
    var xhttp = new  XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById('mybody').innerHTML=xhttp.responseText;
        }else if(xhttp.status == 446){
            document.getElementById('login_err').innerHTML='User does not exists';
            document.getElementById('login_err').style.color='#FF2222';
        }else if(xhttp.status == 447){
            document.getElementById('login_err').innerHTML='Wrong Password';
            document.getElementById('login_err').style.color='#FF2222';
            document.getElementById('psw').value='';
        }
    };
    xhttp.open('POST', 'login');
    xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    
    xhttp.send(user);
}


//otan eimaste sthn arxiki selida gia login an den theme login kai theme register, ayth se petaei sth selida tou register
function sign_up_ajax(){
    var xhttp = new  XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById('mybody').innerHTML=xhttp.responseText;
        }
    };
    xhttp.open('POST', 'sign_up');
    xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
     xhttp.send('sign_up');
  
}

function view_all_users_ajax(){
     var xhttp = new  XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById('mybody').innerHTML=xhttp.responseText;
        }
    };
    xhttp.open('POST', 'view_all_users');
    xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhttp.send('aa');
}

function log_out_ajax(){
    var xhttp = new  XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById('mybody').innerHTML=xhttp.responseText;
        }
    };
    xhttp.open('POST', 'log_out');
    xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhttp.send('logout');
}


//se petaei sth selida gia na kanei edit ta pedia alla gia na ta kaneis update kalei thn update
function edit_user_ajax(){
    var xhttp = new  XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById('mybody').innerHTML=xhttp.responseText;
        }
    };
    xhttp.open('POST', 'edit_user');
    xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhttp.send('edit')
}


//kanei telika update tis plirofories pou eginan sto edit user 
function update_user_ajax(){
    var gender;
    var tempgen = document.getElementsByName('gender');
    for(var i=0; i<3; i++){
        if(tempgen[i].checked){
            gender=tempgen[i].value;
        }
    }
    var elements ='password='+document.getElementById('psw').value+'&firstname='+document.getElementById('fname').value+'&lastname='+document.getElementById('lname').value+'&birthday='+document.getElementById('birthday').value+'&gender='+gender+'&country='+document.getElementById('country').value+'&city='+document.getElementById('city').value+'&address='+document.getElementById('address').value+'&occupation='+document.getElementById('occupation').value+'&interests='+document.getElementById('interests').value+'&gen_info='+document.getElementById('gen_info').value; 

    var xhttp = new  XMLHttpRequest();
    xhttp.onload = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            document.getElementById('mybody').innerHTML=xhttp.responseText;
        }
    };
    xhttp.open('POST', 'update_user');
    xhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhttp.send(elements);
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
      
          	document.getElementById('address_err').innerHTML='location ok';
       		document.getElementById('address_err').style.color='green';
       		resultsMap.setCenter(results[0].geometry.location);

       		document.getElementById('address_but').style.visibility='visible';

            var marker = new google.maps.Marker({
              map: resultsMap,
              position: results[0].geometry.location
            });
           
          } else {
		document.getElementById('address_err').innerHTML='location doesn\'t exists';
       		document.getElementById('address_err').style.color='#FF2222';
                document.getElementById('address').value='';
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
