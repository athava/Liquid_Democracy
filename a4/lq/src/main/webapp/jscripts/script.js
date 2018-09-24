/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

"use strict";

function VerifyPass() {

    var pw = document.getElementById('pw');
    var cpw = document.getElementById('cpw');

    if (pw.value === cpw.value) {
        document.getElementById('passwordMssg').innerHTML = '';
    } else {
        document.getElementById('passwordMssg').innerHTML = 'ERROR';
        pw.value = '';
        cpw.value = '';
    }


}

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 4,
        center: {lat: 48.118, lng: 12.088}
    });

    var geocoder = new google.maps.Geocoder();

    document.getElementById('address').addEventListener('blur', function () {
        codeAddress(geocoder, map);
    });
}


function codeAddress(geocoder, resultsMap) {
    closeMap();
    document.getElementById("mapbtn").style.visibility = "hidden";

    var country = document.getElementById('country').value;
    var city = document.getElementById('city').value;
    var specific = document.getElementById('address').value;

    var address = specific + ", " + city + ", " + country;

    geocoder.geocode({'address': address}, function (results, status) {
        console.log(results);
        if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);

            var marker = new google.maps.Marker({
                map: resultsMap,
                position: results[0].geometry.location
            });
            document.getElementById('addressMssg').innerHTML = '';
            document.getElementById("mapbtn").style.visibility = "visible";
            document.getElementById('mapbtn').addEventListener('click', function () {
                openMap();
            });
        } else {
            document.getElementById("mapbtn").style.visibility = "hidden";
            document.getElementById('addressMssg').innerHTML = 'invalid address';
        }
    });
}


function openMap() {
    document.getElementById("map").style.visibility = "visible";
    document.getElementById("mapbtn").value = "close map";
    document.getElementById('mapbtn').addEventListener('click', function () {
        closeMap();
    });
}


function closeMap() {
    document.getElementById("map").style.visibility = "hidden";
    document.getElementById("mapbtn").value = "show map";
    document.getElementById('mapbtn').addEventListener('click', function () {
        openMap();
    });
}


function enablevideo() {
    document.getElementById("video").style.visibility = "visible";
    document.getElementById("snap").style.visibility = "visible";
}


function showbtn() {
    document.getElementById("upload").style.visibility = "visible";
}


function valid_user(element) {
    var pattern = /[a-zA-Z]{8,}/;
    var username = document.getElementById('username').value;

    validation(element);

    if (pattern.test(username))
        document.getElementById('up_photo').style.visibility = "visible";
    else
        document.getElementById('up_photo').style.visibility = "hidden";
}


function validation(element) {
    var data;
    document.getElementById(element.id + "_msg").innerHTML = "";
    document.getElementById('signup').disabled = false;

    data = dformat(data, element.id, element.value);
    ajax_req("POST", "checkUser", data);
}


function dformat(data, key, val) {
    return data + "&" + key + "=" + val;
}


function submit_inputs() {
    if (!is_valid()) {
        return;
    }

    var data;
    ajax_req("POST", "register", form2data(data));
}


function is_valid() {
    var i = 0;
    var inputs = document.getElementsByTagName('input');

    for (var index = 0; index < inputs.length; ++index) {
        // deal with inputs[index] element.
        if (inputs[index].id != '') {
            document.getElementById(inputs[index].id).style.background = '#ffffff';

            if (inputs[index].checkValidity() == false) {
                inputs[index].value = "";
                document.getElementById(inputs[index].id).style.background = '#ff0000'
                i++;
            }
        }
    }

    var radios = document.getElementsByName('gender');
    var count = 0;
    for (var k = 0; k < radios.length; k++) {
        if (radios[k].checked)
            count++;
    }

    if (count == 0)
        i++;

    if (i)
        return false;

    return true;
}


function form2data(data) {
    data = dformat(data, 'username', document.getElementById('username').value);
    data = dformat(data, 'email', document.getElementById('email').value);
    data = dformat(data, 'password', document.getElementById('pw').value);
    data = dformat(data, 'fname', document.getElementById('fname').value);
    data = dformat(data, 'lname', document.getElementById('lname').value);
    data = dformat(data, 'birthday', document.getElementById('bd').value);

    var radios = document.getElementsByName('gender');
    var gender = "";
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].checked)
            gender = radios[i].value;
    }

    data = dformat(data, 'gender', gender);
    data = dformat(data, 'country', document.getElementById('country').value);
    data = dformat(data, 'town', document.getElementById('city').value);
    data = dformat(data, 'address', document.getElementById('address').value);
    data = dformat(data, 'occupation', document.getElementById('occupation').value);
    data = dformat(data, 'interests', document.getElementById('interests').value);
    data = dformat(data, 'info', document.getElementById('ginfo').value);

    return data;
}


function form2data2(data) {
    data = dformat(data, 'username', document.getElementById('username').innerHTML);
    data = dformat(data, 'email', document.getElementById('email').value);
    data = dformat(data, 'password', document.getElementById('pw').innerHTML);
    data = dformat(data, 'fname', document.getElementById('fname').value);
    data = dformat(data, 'lname', document.getElementById('lname').value);
    data = dformat(data, 'birthday', document.getElementById('bd').value);
    data = dformat(data, 'gender', document.getElementById('gender').innerHTML.toString().trim());
    data = dformat(data, 'country', document.getElementById('country').value);
    data = dformat(data, 'town', document.getElementById('city').value);
    data = dformat(data, 'address', document.getElementById('address').value);
    data = dformat(data, 'occupation', document.getElementById('occupation').value);
    data = dformat(data, 'interests', document.getElementById('interests').value);
    data = dformat(data, 'info', document.getElementById('ginfo').value);

    return data;
}


function Status_400_handler(msg) {
    var splitted = msg.split(" ", 100);
    document.getElementById('answer_container').innerHTML += "<h4>" + splitted[0] + "</h4>";

    for (var i = 1; i < splitted.length; i++) {
        document.getElementById('answer_container').innerHTML += splitted[i] + "<br>";
    }
}

function pageload() {
    ajax_req('POST', 'checksession', '');
}

function register() {
    ajax_req('POST', 'reg_site.jsp', '');
}

function login() {
    ajax_req('POST', 'login_site.jsp', '');
}

function logout() {
    ajax_req('POST', 'logout', '');
}

function fetch_info() {
    ajax_req('POST', 'pinfo.jsp', '');
}

function fetch_users() {
    ajax_req('POST', 'list_of_users', '');
}

function edit_info() {
    ajax_req('POST', 'edit_pinfo.jsp', '');
}

function update_info() {
    var data;
    var jtext = form2data2(data);
    ajax_req("POST", "update_pinfo", jtext);
}

function new_pi() {
    ajax_req('POST', 'add_initiative.jsp', '');
}

function fetch_my_pi(param) {
    var data= dformat('', 'action', param);
    ajax_req('POST', "my_pis", data);
}

function fetch_pis(param) {
    var data = dformat('', 'action', param);
    ajax_req('POST', 'view_all_polls', data);
}


function checkNsend() {
    document.getElementById('username').style.background = '#ffffff';
    document.getElementById('pw').style.background = '#ffffff';
    var check = 0;
    //check
    if (document.getElementById('username').value == '') {
        document.getElementById('username').style.background = '#ff0000';
        check++;
    }

    if (document.getElementById('pw').value == '') {
        document.getElementById('pw').style.background = '#ff0000';
        check++;
    }

    if (check)
        return;

    //N send
    var data = '';
    data = dformat(data, 'username', document.getElementById('username').value);
    data = dformat(data, 'password', document.getElementById('pw').value);

    ajax_req('POST', 'login', data);

}


function eval_json(jresponse) {
    if (jresponse.logged == 'true') {
        logged(jresponse.username);

        if (jresponse.action == 'login') {
            //ajax_req('POST', 'pinfo.jsp', '');
            document.getElementById('answer_container').innerHTML = '';
        } else if (jresponse.action == 'register') {
            ajax_req('POST', 'reg_ok_site.jsp', '');
        } else if (jresponse.action == 'checksession') {
            ajax_req('POST', 'pinfo.jsp', '');
        } else if (jresponse.action == 'update_pinfo') {
            ajax_req('POST', 'pinfo.jsp', '');
        }

    } else {
        not_logged();

        if (jresponse.action == 'login') {
            if (jresponse.message == 'wrong password') {
                document.getElementById('password_msg').innerHTML = jresponse.message;
                document.getElementById('username_msg').innerHTML = '';
            } else {
                document.getElementById('username_msg').innerHTML = jresponse.message;
                document.getElementById('password_msg').innerHTML = '';
            }
        } else if (jresponse.action == 'logout') {
            ajax_req('POST', 'login_site.jsp', '');
        }

    }

}


function logged(name) {
    document.getElementById('logged_user').innerHTML = name;
    document.getElementById('log-out-cmd').innerHTML = "Log out";
    document.getElementById('log-in-cmd').innerHTML = "";
    document.getElementById('polls_id').innerHTML = "Polls<span class=\"caret\"></span>";
    document.getElementById('register-cmd').innerHTML = "";
    document.getElementById('reg-users-cmd').innerHTML = "Users";

    document.getElementById('log-out-cmd').style.visibility = "visible";
    document.getElementById('log-in-cmd').style.visibility = "hidden";
    document.getElementById('register-cmd').style.visibility = "hidden";
    document.getElementById('polls').style.visibility = "visible";
    document.getElementById('polls_id').style.visibility = "visible";
    document.getElementById('reg-users-cmd').style.visibility = "visible";
}


function not_logged() {
    document.getElementById('logged_user').innerHTML = '';
    document.getElementById('log-out-cmd').innerHTML = "";
    document.getElementById('polls_id').innerHTML = "";
    document.getElementById('log-in-cmd').innerHTML = "Log in";
    document.getElementById('register-cmd').innerHTML = "Register";
    document.getElementById('reg-users-cmd').innerHTML = "";

    document.getElementById('log-out-cmd').style.visibility = "hidden";
    document.getElementById('log-in-cmd').style.visibility = "visible";
    document.getElementById('register-cmd').style.visibility = "visible";
    document.getElementById('polls').style.visibility = "hidden";
    document.getElementById('polls_id').style.visibility = "hidden";
    document.getElementById('reg-users-cmd').style.visibility = "hidden";
}


function ajax_req(method, uri, data) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState) {
            if (xhttp.status == 200) {
                document.getElementById('answer_container').innerHTML = xhttp.responseText;
            } else if (xhttp.status == 220) {
                var response = JSON.parse(xhttp.responseText);
                eval_json(response);
                // alert("."+xhttp.responseText+".");
            } else if (xhttp.status == 400) {
                document.getElementById('answer_container').innerHTML = "<h3 style=\"color:red;\">Status Code 400 - Bad Request</h3>";
                Status_400_handler(xhttp.responseText);
            } else if (xhttp.status == 420) {
                document.getElementById('username_msg').innerHTML = "taken";
                document.getElementById('signup').disabled = true;
            } else if (xhttp.status == 421) {
                document.getElementById('email_msg').innerHTML = "already in use";
                document.getElementById('signup').disabled = true;
            }
        } else {
            //alert(xhttp.readyState);
        }
    };

    if (method == 'POST') {
        xhttp.open(method, uri, true);
        xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhttp.send(data);
    } else {
        xhttp.open('GET', uri + '?' + data);
        xhttp.send();
    }

}


function create_pi(){
    if(!check_pi(1))
        return;
    
    var data = '';
    data = dformat(data, 'action', 'create');
    data = dformat(data, 'title', document.getElementById('title').value);
    data = dformat(data, 'description', document.getElementById('description').value);
    data = dformat(data, 'category', document.getElementById('category').value);

    ajax_req('POST', 'add_initiative', data);
    
}

function check_pi(K){
    if(K==1){
        var inputs= [
                    document.getElementById('title'),
                    document.getElementById('description'),
                    document.getElementById('category')
                ];
    }
    else{
        var inputs= [
                    document.getElementById('title'),
                    document.getElementById('description'),
                    document.getElementById('category'),
                    document.getElementById('date')
                ];
    }
    
    
    var check=0;
    
    for(var i=0; i<inputs.length; i++){
        inputs[i].style.background = '#ffffff';
        
        if(inputs[i].value==''){
            inputs[i].style.background = '#ff0000';
            check++;
        }
    }
    
    if (check)
        return false;
    
    return true;
}

function add_vote(vote, initiative_id, pis) {
    var data = '';
    data = dformat(data, 'vote', vote);
    data = dformat(data, 'initiative_id', initiative_id);
    data = dformat(data, 'action', 'active');
    data = dformat(data, 'pis_page', pis);
    ajax_req('POST', "add_vote", data);
}


function call_update_pi(pi_id){
    var data = '';
    
    data = dformat(data, 'initiative_id', pi_id);
    ajax_req('POST', "update_pi.jsp", data);
    
}

function update_pi(pi_id){
    if(!check_pi(0))
        return;
    
    //date--> index 0: year, index 1: month, index 2: day
    //time--> index 0: hours, index 1: minutes
    
    var datetime= document.getElementById('date').value.toString().split('T');
    var date= datetime[0].toString().split('-');
    var time= datetime[1].toString().split(':');
    
    var data = '';
    data = dformat(data, 'action', 'update');
     data = dformat(data, 'initiative_id', pi_id);
    data = dformat(data, 'title', document.getElementById('title').value);
    data = dformat(data, 'description', document.getElementById('description').value);
    data = dformat(data, 'category', document.getElementById('category').value);
    data = dformat(data, 'year', date[0]);
    data = dformat(data, 'month', date[1]);
    data = dformat(data, 'day', date[2]);
    data = dformat(data, 'hours', time[0]);
    data = dformat(data, 'minutes', time[1]);

    ajax_req('POST', 'add_initiative', data);
}

function activate_pi(pi_id){
    var data = '';
    data = dformat(data, 'initiative_id', pi_id);
    data = dformat(data, 'action', 'activate');
    
    ajax_req('POST', 'add_initiative', data);
}

