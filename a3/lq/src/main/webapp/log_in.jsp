<%-- 
    Document   : log_in
    Created on : Dec 1, 2017, 6:10:27 PM
    Author     : dona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container myheader">
    <div class="page-header"><h1>Liquid Democracy</h1><p>Sign in</p>
    </div>
</div>


<div class="container">
    <div class="tablee form-group">
        <table >
            <tr>
                <td>
                    <label class="control-label" for="usrnm">Username:</label>
                </td>  
                <td class="td2">
                    <input class=" form-control" type="text" id="usrnm" name="username">
                    <small style="visibility: hidden;" id="usrnm_err"></small>
                </td> 	
            </tr>

            <tr>
                <td>
                    <label class="control-label" for="psw">Password:</label>
                </td>  
                <td class="td2">
                    <input class=" form-control" type="password" id="psw" name="password" >
                    <small id="login_err"></small>
                </td> 
            </tr>

            <tr>
                <td>
                    <label class="control-label" for="enable_video">auto login</label>
                    <input type="radio" name="video option" id="enable_video"  >
                </td> 
            </tr>

            <tr>
                <td>

                </td>
                <td>
                    <input class="btn btn-info " id="sub_but"  value="log in" onclick="login_ajax()">
                </td>  
                <td >
                    <input class="btn btn-info " id="register_button"  value="register here" onclick="sign_up_ajax()">
                </td> 
            </tr>
        </table>
    </div>
</div>

<div id="video_container" class="container col-lg-5 col-lg-offset-7">
    <video id='video' width='280' height='210' autoplay></video>
    <input  class=" btn btn-info " type='button' id="snap" value ='Take Photo'>
    <canvas id='canvas' width='280' height='210'></canvas>
    <input class=" btn btn-info " type='button' id="search" value ='find me'>

    <script>
        faceRec.init();
    </script>
</div>