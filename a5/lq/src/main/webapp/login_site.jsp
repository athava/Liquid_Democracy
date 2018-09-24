<%-- 
    Document   : login
    Created on : Nov 29, 2017, 2:24:09 PM
    Author     : lanag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="container col-lg-4 col-md-4 col-sm-12 col-xs-12"> </div>


<div class="container col-lg-4 col-md-4 col-sm-12 col-xs-12">

    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="title" >
        <h3>Log in</h3>
    </div>						

    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="myfield">
        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
            Username: 
        </div>
        <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
            <input type="text" name="username" id="username" pattern="[A-Za-z]{8,}" title="Username" required>
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 star" id="username_msg"></div>
    </div>

    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="myfield">
        <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
            Password: 
        </div>
        <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
            <input type="password" name="pw" id="pw" pattern="(?=^.{8,10}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="password that must contain 8-10 characters, at least 1 character, 1 number and 1 symbol"  required>
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 star" id="password_msg"></div>
    </div>

    <input type="checkbox" id="photocheck" value="ok" onchange="test2();"> Log in by photo

    <button class="butt btn btn-success" id="login" onclick="checkNsend()">Log in</button>

</div>


<div class="container col-lg-4 col-md-4 col-sm-12 col-xs-12">

    <video class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id='video' autoplay></video>

    <div class="col-lg-6 col-md-6">
        <input class="col-lg-12 col-md-12 col-sm-12 col-xs-12 butt btn btn-info" onclick="showbtn()" type='button' id="snap" value ='Take Photo'>
    </div>

    <div class="col-lg-6 col-md-6">
        <input class="col-lg-12 col-md-12 col-sm-12 col-xs-12 butt btn btn-success" type='button' id="upload" value ='Upload Image'>
    </div>

    <div id="message"></div>

    <canvas class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id='canvas'></canvas>


    <script>faceRec.init('login');</script>
</div>