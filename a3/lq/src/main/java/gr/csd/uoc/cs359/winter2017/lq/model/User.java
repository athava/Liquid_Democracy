/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.csd.uoc.cs359.winter2017.lq.model;

import java.io.Serializable;

/**
 *
 * @author papadako
 */
public class User implements Serializable {

    private String userName;    // (unique)
    private String email;       // (unique)
    private String password;    // (could be encrypted in md5)
    private String firstName;
    private String lastName;
    private String birthDate;   // (format: ΗΗ/ΜΜ/ΧΧΧΧ)
    private Gender gender;
    private String country;
    private String address;
    private String occupation;
    private String interests;
    private String city;
    private String info;

    /**
     * Default Constructor
     *
     */
    public User() {
        this.userName = "";
        this.email = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.birthDate = "";
        this.country = "";
        this.city = "";
        this.occupation = "";
        // Not obligatory fields
        this.info = "";
        this.gender = Gender.UNKNOWN;
        this.address = "";
        this.interests = "";
    }

    /**
     * Constructor
     *
     * @param userName
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param occupation
     * @param country
     * @param city
     */
    public User(String userName,
            String email,
            String password,
            String firstName,
            String lastName,
            String birthDate,
            String occupation,
            String country,
            String city) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
        this.city = city;
        this.occupation = occupation;
        // Not obligatory fields
        this.info = "";
        this.gender = Gender.UNKNOWN;
        this.address = "";
        this.interests = "";
    }

    /**
     * Constructor
     *
     * @param userName
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param occupation
     * @param country
     * @param city
     * @param address
     * @param interests
     * @param info
     * @param gender
     */
    public User(String userName,
            String email,
            String password,
            String firstName,
            String lastName,
            String birthDate,
            Gender gender,
            String country,
            String city,
            String address,
            String occupation,
            String interests,
            String info) { System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.country = country;
        this.city = city;
        // Not obligatory fields
        this.info = info;
        this.gender = gender;
        this.address = address;
        this.interests = interests;
        toString();
    }

    /**
     * Constructor
     *
     * @param userName
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param occupation
     * @param country
     * @param city
     * @param address
     * @param interests
     * @param info
     * @param gender
     */
    public User(String userName,
             String email,
            String password,
            String firstName,
            String lastName,
            String birthDate,
            String gender,
            String country,
            String city,
            String address,
            String occupation,
            String interests,
            String info) { 
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
        this.address = address;
        this.city = city;
        // Not obligatory fields
        this.info = info;
        setGender(gender);
        this.interests = interests;
        this.occupation = occupation;
    }

    /**
     * Method that checks that all mandatory fields are set
     *
     * @throws Exception
     */
    public void checkFields() throws Exception {
        // Check that everything is ok
        if ((userName == null || userName.trim().isEmpty())
                || (email == null || email.trim().isEmpty())
                || (password == null || password.trim().isEmpty())
                || (firstName == null || firstName.trim().isEmpty())
                || (lastName == null || lastName.trim().isEmpty())
                || (birthDate == null || birthDate.trim().isEmpty())
                || (gender != Gender.FEMALE && gender != Gender.MALE && gender != Gender.UNKNOWN)
                || (city == null || city.trim().isEmpty())
                || ( country == null || country.trim().isEmpty())
                || (occupation == null || occupation.trim().isEmpty())) {
            throw new Exception("Missing fields!");  // Something went wrong with the fields
        }
    }
    
     public String required_fields() {
        String missingFields="";
        if ((userName == null || userName.trim().isEmpty())){
            missingFields= missingFields + "username, ";
        }
        if ((email == null || email.trim().isEmpty())){
            missingFields= missingFields + "email, ";
        }
        if ((password == null || password.trim().isEmpty())){
            missingFields= missingFields + "password, ";
        }
        if ((firstName == null || firstName.trim().isEmpty())){
            missingFields= missingFields + "first name, ";
        }
        if ((lastName == null || lastName.trim().isEmpty())){
            missingFields= missingFields + "last name, ";
        }
        if ((birthDate == null || birthDate.trim().isEmpty())){
            missingFields= missingFields + "birthday, ";
        }
        if ((gender != Gender.FEMALE && gender != Gender.MALE && gender != Gender.UNKNOWN)){
            missingFields= missingFields + "gender, ";
        }
        if ((city == null || city.trim().isEmpty())){
            missingFields= missingFields + "city, ";
        }
        if (( country == null || country.trim().isEmpty())){
            missingFields= missingFields + "country, ";
        }
        if ((occupation == null || occupation.trim().isEmpty())){
            missingFields= missingFields + "occupation";
        }
        return missingFields;
    }

    /* Getters and Setters */
    /**
     * Get the user name
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the username
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the email
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of this user
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the first name of the user
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get last name
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get BirthDate
     *
     * @return
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Set birthDate
     *
     * @param birthDate
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Get gender
     *
     * @return
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Set gender
     *
     * @param gender
     */
    public void setGender(String gender) {
        switch (gender.toLowerCase().trim()) {
            case "female":
                this.gender = Gender.FEMALE;
                break;
            case "male":
                this.gender = Gender.MALE;
                break;
            default:
                this.gender = Gender.UNKNOWN;
                break;
        }
    }

    /**
     * Get country
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set country
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get city
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Set city
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get address
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get occupation
     *
     * @return
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Set occupation
     *
     * @param occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Get interests
     *
     * @return
     */
    public String getInterests() {
        return interests;
    }

    /**
     * Set interests
     *
     * @param interests
     */
    public void setInterests(String interests) {
        this.interests = interests;
    }

    /**
     * Get info
     *
     * @return
     */
    public String getInfo() {
        return info;
    }

    /**
     * Set info
     *
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Enum for supporting gender values
     */
    public enum Gender {

        MALE("Male"), FEMALE("Female"), UNKNOWN("Unknown");
        private final String value;

        private Gender(String value) {
            this.value = value;
        }

        /**
         * Returns string representation of value
         *
         * @return
         */
        @Override
        public String toString() {
            return this.value;
        }
    }

    /**
     * Returns a string representation of this object
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User Name: ").append(userName).append("\n")
                .append("email: ").append(email).append("\n")
                .append("password: ").append(password).append("\n")
                .append("First Name: ").append(firstName).append("\n")
                .append("Last Name: ").append(lastName).append("\n")
                .append("Birth Date: ").append(birthDate).append("\n")
                .append("Gender: ").append(gender).append("\n")
                .append("Country: ").append(country).append("\n")
                .append("City: ").append(city).append("\n")
                .append("Address: ").append(address).append("\n")
                .append("Occupation: ").append(occupation).append("\n")
                .append("Interests: ").append(interests).append("\n")
                .append("Info: ").append(info).append("\n");

        return sb.toString();

    }

}
