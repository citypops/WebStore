package citypops.webstore.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class User {

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 6, max = 64, message = "username length must be between 6 and 64 characters")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 6, max = 64, message = "password length  must be between 6 and 64 characters")
    private String password;
    @NotEmpty(message = "email cannot be empty")
    @Email
    @Size(max = 128, message = "email can count at most 128 characters")
    private String email;
    @NotEmpty(message = "Fisrt Name cannot be empty")
    @Size(max = 128, message = "First Name can count at most 128 characters")
    private String firstName;
    @NotEmpty(message = "Last Name cannot be empty")
    @Size(max = 128, message = "Last Name can count at most 128 characters")
    private String lastName;
    @NotEmpty(message = "City cannot be empty")
    @Size(max = 128, message = "City can count at most 128 characters")
    private String city;
    @NotEmpty(message = "Postcode cannot be empty")
    @Size(max = 32, message = "Postcode can count at most 32 characters")
    private String postcode;
    @NotEmpty(message = "Street cannot be empty")
    @Size(max = 128, message = "Street can count at most 128 characters")
    private String street;
    @NotEmpty(message = "Home number cannot be empty")
    @Size(max = 32, message = "Home number can count at most 32 characters")
    private String homeNo;
    @NotEmpty(message = "Phone number cannot be empty")
    @Size(max = 32, message = "Phone number can count at most 32 characters")
    private String phoneNo;
    @NotNull(message = "enabled cannot be empty")
    private boolean enabled;
    private List<String> roles;

    public User() {
        roles = new ArrayList<>();
    }

    public User(String username, String password, String email, String firstName, String lastName, String city,
                String postcode, String street, String homeNo, String phoneNo, boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
        this.homeNo = homeNo;
        this.phoneNo = phoneNo;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNo() {
        return homeNo;
    }

    public void setHomeNo(String homeNo) {
        this.homeNo = homeNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}