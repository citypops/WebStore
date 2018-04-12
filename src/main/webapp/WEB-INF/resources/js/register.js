var username,password,email,firstName,lastName,city,postcode,street,homeNo,phoneNo;
function init() {
    username    = document.getElementById('username');
    password    = document.getElementById('password');
    email       = document.getElementById('email');
    firstName   = document.getElementById('first_name');
    lastName    = document.getElementById('last_name');
    city        = document.getElementById('city');
    postcode    = document.getElementById('postcode');
    street      = document.getElementById('street');
    homeNo      = document.getElementById('home_no');
    phoneNo     = document.getElementById('phone_no');
}

function registerUser() {
    var json = JSON.stringify({
        username: username.value,
        password: password.value,
        email: email.value,
        firstName: firstName.value,
        lastName: lastName.value,
        city: city.value,
        postcode: postcode.value,
        street: street.value,
        homeNo: homeNo.value,
        phoneNo: phoneNo.value
    });
    genericRequest("POST", "/account", json,
        "Registration complete! You can now sign in by your username and password.",
        "/login"
    );
}