<%-- 
    Document   : register
    Created on : Dec 1, 2017, 3:47:03 AM
    Author     : dona
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    
        <div class="container myheader">
			<div class="page-header"><h1>Liquid Democracy</h1><p id="header_detail">Sign up</p></div>
		</div>
		

		<div class="container">
			<div class="form-horizontal" >
				<div class="tablee form-group">
					<table >
						<tr>
						    <td>
						    	<label class="control-label" for="usrnm">*Username:</label>
						    </td>  
						    <td class="td2">
                                                        <input class=" form-control" type="text" id="usrnm" name="username" pattern="^([A-Za-z]{8,})$" title="username has to be at least 8 characters" onchange="for_username(this,'usrnm_err'); username_email_database_validation_ajax('usrnm','usrnm_err');" >
                                                        <small id="usrnm_err"></small>
                                                    </td> 
						   <td id="video_sect">
						   		<label class="control-label" id="label_for_enable_video" for="enable_video">enable video</label>
						    	<input type="radio" name="video option" id="enable_video" >
						   	</td>
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="email">*Email:</label>
						    </td>  
						    <td class="td2">
						    	<input class=" form-control" type="email" id="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="email is wrong" onchange="check_pattern_validity(this,'email_err');  username_email_database_validation_ajax('email','email_err');" >
                                                        <small id="email_err"></small>
                                                    </td> 
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="psw">*Password:</label>
						    </td>  
						    <td class="td2">
						    	<input class=" form-control" type="password" id="psw" name="password" pattern="(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,10}$" title="password has to be from 8 to 10 characters, include 1 characters, 1 number, 1 special char" onchange="check_pattern_validity(this,'psw_err')" >
						   	<small id="psw_err"></small>
                                                    </td> 
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="psw_conf">*Password Confirmation:</label>
						    </td>  
						    <td class="td2">
						    	<input class=" form-control" type="password" id="psw_conf" name="password_conf" pattern="(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,10}$" title="password has to be from 8 to 10 characters, include 1 characters, 1 number, 1 special char" onchange="conf_psw_regex_validate()"  >
						    	<small id="val_error"></small>
						   	</td> 
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="fname">*First Name</label>
						    </td>  
						    <td class="td2">
						    	<input class=" form-control" type="text" id="fname" name="fname" pattern="[A-Za-z]{1,20}" onchange="check_pattern_validity(this,'fname_err')" >
						   	<small id="fname_err"></small>
                                                    </td> 
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="lname">*Last Name</label>
						    </td>  
						    <td class="td2">
						    	<input class=" form-control" type="text" id="lname" name="lname" pattern="[A-Za-z]{4,20}" onchange="check_pattern_validity(this,'lname_err')" >
						   	<small id="lname_err"></small>
                                                    </td> 
					  	</tr>

					  	<tr>
						    <td> 
						    	<label class=" control-label" for="birthday">*Birth Date:</label> 
						    </td>  
						    <td class="td2">
						    	<input class="form-control" type="text" id="birthday" name="birthday" pattern="^(?:(?:(?:0[1-9]|1\d|2[0-8])/(?:0[1-9]|1[0-2])|(?:29|30)/(?:0[13-9]|1[0-2])|31/(?:0[13578]|1[02]))/[1-9]\d{3}|29/02/(?:[1-9]\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00))$" onchange="check_pattern_validity(this,'birthday_err')">
                                                        <small id="birthday_err"></small>
						   	</td> 
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label">Gender:</label>	
						    </td>  
						    <td id="radio_but">
						    	<label class="control-label" for="male" >Male</label>
						    	<input class="form-inline" type="radio" name="gender" id="male" value="male" >
				  				
                                                        <label class="control-label" for="female">&nbsp;&nbsp;Female</label>
                                                        <input class="form-inline" type="radio" name="gender" id="female" value="female" >

                                                        <label class="control-label" for="other">&nbsp;&nbsp;Unknown</label>
                                                        <input class="form-inline" type="radio" name="gender" id="other" value="other" checked>
						   	</td> 
					  	</tr>

					  	<tr>
						    <td >
						    	<label class="control-label" for="country">*Country:</label> 
						    </td>  
						    <td class="td2">
					    		<select class="form-control" id="country" >
                                                            <option>Åland Islands</option>
									<option>Albania</option>
									<option>Algeria</option>
									<option>American Samoa</option>
									<option>Andorra</option>
									<option>Angola</option>
									<option>Anguilla</option>
									<option>Antarctica</option>
									<option>Antigua and Barbuda</option>
									<option>Argentina</option>
									<option>Armenia</option>
									<option>Aruba</option>
									<option>Australia</option>
									<option>Austria</option>
									<option>Azerbaijan</option>
									<option>Bahamas</option>
									<option>Bahrain</option>
									<option>Bangladesh</option>
									<option>Barbados</option>
									<option>Belarus</option>
									<option>Belgium</option>
									<option>Belize</option>
									<option>Benin</option>
									<option>Bermuda</option>
									<option>Bhutan</option>
									<option>Bolivia, Plurinational State of</option>
									<option>Bonaire, Sint Eustatius and Saba</option>
									<option>Bosnia and Herzegovina</option>
									<option>Botswana</option>
									<option>Bouvet Island</option>
									<option>Brazil</option>
									<option>British Indian Ocean Territory</option>
									<option>Brunei Darussalam</option>
									<option>Bulgaria</option>
									<option>Burkina Faso</option>
									<option>Burundi</option>
									<option>Cambodia</option>
									<option>Cameroon</option>
									<option>Canada</option>
									<option>Cape Verde</option>
									<option>Cayman Islands</option>
									<option>Central African Republic</option>
									<option>Chad</option>
									<option>Chile</option>
									<option>China</option>
									<option>Christmas Island</option>
									<option>Cocos (Keeling) Islands</option>
									<option>Colombia</option>
									<option>Comoros</option>
									<option>Congo</option>
									<option>Congo, the Democratic Republic of the</option>
									<option>Cook Islands</option>
									<option>Costa Rica</option>
									<option>Côte d'Ivoire</option>
									<option>Croatia</option>
									<option>Cuba</option>
									<option>Curaçao</option>
									<option>Cyprus</option>
									<option>Czech Republic</option>
									<option>Denmark</option>
									<option>Djibouti</option>
									<option>Dominica</option>
									<option>Dominican Republic</option>
									<option>Ecuador</option>
									<option>Egypt</option>
									<option>El Salvador</option>
									<option>Equatorial Guinea</option>
									<option>Eritrea</option>
									<option>Estonia</option>
									<option>Ethiopia</option>
									<option>Falkland Islands (Malvinas)</option>
									<option>Faroe Islands</option>
									<option>Fiji</option>
									<option>Finland</option>
									<option>France</option>
									<option>French Guiana</option>
									<option>French Polynesia</option>
									<option>French Southern Territories</option>
									<option>Gabon</option>
									<option>Gambia</option>
									<option>Georgia</option>
									<option>Germany</option>
									<option>Ghana</option>
									<option>Gibraltar</option>
									<option selected >Greece</option>
									<option>Greenland</option>
									<option>Grenada</option>
									<option>Guadeloupe</option>
									<option>Guam</option>
									<option>Guatemala</option>
									<option>Guernsey</option>
									<option>Guinea</option>
									<option>Guinea-Bissau</option>
									<option>Guyana</option>
									<option>Haiti</option>
									<option>Heard Island and McDonald Islands</option>
									<option>Holy See (Vatican City State)</option>
									<option>Honduras</option>
									<option>Hong Kong</option>
									<option>Hungary</option>
									<option>Iceland</option>
									<option>India</option>
									<option>Indonesia</option>
									<option>Iran, Islamic Republic of</option>
									<option>Iraq</option>
									<option>Ireland</option>
									<option>Isle of Man</option>
									<option>Israel</option>
									<option>Italy</option>
									<option>Jamaica</option>
									<option>Japan</option>
									<option>Jersey</option>
									<option>Jordan</option>
									<option>Kazakhstan</option>
									<option>Kenya</option>
									<option>Kiribati</option>
									<option>Korea, Democratic People's Republic of</option>
									<option>Korea, Republic of</option>
									<option>Kuwait</option>
									<option>Kyrgyzstan</option>
									<option>Lao People's Democratic Republic</option>
									<option>Latvia</option>
									<option>Lebanon</option>
									<option>Lesotho</option>
									<option>Liberia</option>
									<option>Libya</option>
									<option>Liechtenstein</option>
									<option>Lithuania</option>
									<option>Luxembourg</option>
									<option>Macao</option>
									<option>Macedonia, the former Yugoslav Republic of</option>
									<option>Madagascar</option>
									<option>Malawi</option>
									<option>Malaysia</option>
									<option>Maldives</option>
									<option>Mali</option>
									<option>Malta</option>
									<option>Marshall Islands</option>
									<option>Martinique</option>
									<option>Mauritania</option>
									<option>Mauritius</option>
									<option>Mayotte</option>
									<option>Mexico</option>
									<option>Micronesia, Federated States of</option>
									<option>Moldova, Republic of</option>
									<option>Monaco</option>
									<option>Mongolia</option>
									<option>Montenegro</option>
									<option>Montserrat</option>
									<option>Morocco</option>
									<option>Mozambique</option>
									<option>Myanmar</option>
									<option>Namibia</option>
									<option>Nauru</option>
									<option>Nepal</option>
									<option>Netherlands</option>
									<option>New Caledonia</option>
									<option>New Zealand</option>
									<option>Nicaragua</option>
									<option>Niger</option>
									<option>Nigeria</option>
									<option>Niue</option>
									<option>Norfolk Island</option>
									<option>Northern Mariana Islands</option>
									<option>Norway</option>
									<option>Oman</option>
									<option>Pakistan</option>
									<option>Palau</option>
									<option>Palestinian Territory, Occupied</option>
									<option>Panama</option>
									<option>Papua New Guinea</option>
									<option>Paraguay</option>
									<option>Peru</option>
									<option>Philippines</option>
									<option>Pitcairn</option>
									<option>Poland</option>
									<option>Portugal</option>
									<option>Puerto Rico</option>
									<option>Qatar</option>
									<option>Réunion</option>
									<option>Romania</option>
									<option>Russian Federation</option>
									<option>Rwanda</option>
									<option>Saint Barthélemy</option>
									<option>Saint Helena, Ascension and Tristan da Cunha</option>
									<option>Saint Kitts and Nevis</option>
									<option>Saint Lucia</option>
									<option>Saint Martin (French part)</option>
									<option>Saint Pierre and Miquelon</option>
									<option>Saint Vincent and the Grenadines</option>
									<option>Samoa</option>
									<option>San Marino</option>
									<option>Sao Tome and Principe</option>
									<option>Saudi Arabia</option>
									<option>Senegal</option>
									<option>Serbia</option>
									<option>Seychelles</option>
									<option>Sierra Leone</option>
									<option>Singapore</option>
									<option>Sint Maarten (Dutch part)</option>
									<option>Slovakia</option>
									<option>Slovenia</option>
									<option>Solomon Islands</option>
									<option>Somalia</option>
									<option>South Africa</option>
									<option>South Georgia and the South Sandwich Islands</option>
									<option>South Sudan</option>
									<option>Spain</option>
									<option>Sri Lanka</option>
									<option>Sudan</option>
									<option>Suriname</option>
									<option>Svalbard and Jan Mayen</option>
									<option>Swaziland</option>
									<option>Sweden</option>
									<option>Switzerland</option>
									<option>Syrian Arab Republic</option>
									<option>Taiwan, Province of China</option>
									<option>Tajikistan</option>
									<option>Tanzania, United Republic of</option>
									<option>Thailand</option>
									<option>Timor-Leste</option>
									<option>Togo</option>
									<option>Tokelau</option>
									<option>Tonga</option>
									<option>Trinidad and Tobago</option>
									<option>Tunisia</option>
									<option>Turkey</option>
									<option>Turkmenistan</option>
									<option>Turks and Caicos Islands</option>
									<option>Tuvalu</option>
									<option>Uganda</option>
									<option>Ukraine</option>
									<option>United Arab Emirates</option>
									<option>United Kingdom</option>
									<option>United States</option>
									<option>United States Minor Outlying Islands</option>
									<option>Uruguay</option>
									<option>Uzbekistan</option>
									<option>Vanuatu</option>
									<option>Venezuela, Bolivarian Republic of</option>
									<option>Viet Nam</option>
									<option>Virgin Islands, British</option>
									<option>Virgin Islands, U.S.</option>
									<option>Wallis and Futuna</option>
									<option>Western Sahara</option>
									<option>Yemen</option>
									<option>Zambia</option>
									<option>Zimbabwe</option>
							</select>
                                                        <small id="country_err"></small>
                                                    </td> 
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="city">*City:</label>
						    </td>  
						    <td class="td2">
                                                        <input class="form-control" type="text" id="city" name="city" pattern="[A-Za-z]{2,20}" onchange="check_pattern_validity(this,'city_err')" >
						   	<small id="city_err"></small>
                                                    </td> 
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="address">Address:</label>
						    </td>  
						    <td class="td2">
                                                        <input class="form-control" type="text" id="address"  name="address" pattern="[A-Za-z0-9 ]{0,100}" onchange="check_pattern_validity(this,'address_err'); initMap();">
						    	 <small id="address_err"></small>
						   	</td> 
							<td >
						    	 <input class="mapbutton btn btn-info" type="button" id="address_but" value="show map" onclick="showmap()" >
						   	</td>
						   	  
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="occupation">*Occupation:</label>
						    </td>  
						    <td class="td2">
						    	 <input class="  form-control" type="text" id="occupation" name="occupation" pattern="[A-Za-z0-9 ]{2,20}" onchange="check_pattern_validity(this,'occupation_err')" >
						   	<small id="occupation_err"></small>
                                                    </td> 
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="interests">Interests:</label>
						    </td>  
						    <td class="td2">
						    	<textarea class="form-control" id="interests" maxlength="100"  onchange="check_pattern_validity(this,'interests_err')"></textarea>
						   	<small id="interests_err"></small>
                                                    </td> 
					  	</tr>

					  	<tr>
						    <td>
						    	<label class="control-label" for="gen_info">General Information:</label>
						    </td>  
						    <td class="td2">
						    	<textarea class=" form-control" id="gen_info" maxlength="15" onchange="check_pattern_validity(this,'info_err')"></textarea>
						   	<small id="info_err"></small>
                                                    </td> 
					  	</tr>
					 </table>
				</div>
				<input class="btn btn-info " id="sub_but"  value="Submit" onclick="required_fields()" >
                                <div id ="server-side-error"></div>
			</div>
		</div>

		<div id="video_container" class="container col-lg-5 col-lg-offset-7">
			<video id='video' width='280' height='210' autoplay></video>
			<input  class=" btn btn-info " type='button' id="snap" value ='Take Photo'>
			<input class=" btn btn-info " type='button' id="upload" value ='Upload Image'>
			<canvas id='canvas' width='280' height='210'></canvas>

			
			<script>
				faceRec.init();
			</script>
		</div>
		
		<div id="map"></div>
