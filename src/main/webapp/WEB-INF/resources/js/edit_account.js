var username,password,email,firstName,lastName,city,postcode,street,
    homeNo,phoneNo,enabled,roles,oldRoles;

function init() {
    password = document.getElementById('password');
    email = document.getElementById('email');
    firstName = document.getElementById('first_name');
    lastName = document.getElementById('last_name');
    city = document.getElementById('city');
    postcode = document.getElementById('postcode');
    street = document.getElementById('street');
    homeNo = document.getElementById('home_no');
    phoneNo = document.getElementById('phone_no');
    enabled = document.getElementById('enabled');
    roles = document.getElementById('roles');
    roles.value = oldRoles.toString();
}

function updateAccount() {
    var json = JSON.stringify({
        username: username,
        password: password.value,
        email: email.value,
        firstName: firstName.value,
        lastName: lastName.value,
        city: city.value,
        postcode: postcode.value,
        street: street.value,
        homeNo: homeNo.value,
        phoneNo: phoneNo.value,
        enabled: enabled.checked,
        roles: roles.value.replace(/\s/g,'').split(",").filter(function(s) {return s.length != 0})
    });
    genericRequest("PUT", "/account", json,
        "Account updated succesfully.",
        "/admin/accounts");
}

function deleteAccount() {
    genericRequest("DELETE", "/account/" + username, null,
        "Account deleted succesfully.",
        "/admin/accounts");
}