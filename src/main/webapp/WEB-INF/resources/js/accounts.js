var container, pAccounts, pEnabled, pDisabled;

function init() {
    container = document.getElementById('container');
    pAccounts = document.getElementById('pAccounts');
    pEnabled = document.getElementById('pEnabled');
    pDisabled = document.getElementById('pDisabled');
    callbackRequest('GET', '/account', generateAccountElements);
}

function generateAccountElements(accounts) {
    container.innerHTML = "";
    var enabled = 0;
    var disabled = 0;
    var acnts = JSON.parse(accounts);
    var length = acnts.length;
    for(var i = 0 ; i < length ; i++) {
        addAccountElement(acnts[i]);
        if(acnts[i]['enabled']) {
            enabled++;
        } else {
            disabled++;
        }
    }
    pAccounts.textContent = length;
    pEnabled.textContent = enabled;
    pDisabled.textContent = disabled;
}

function addAccountElement(account) {
    var d = document.createElement('div');
    d.className = 'cartElement';
    d.onclick = function() {
        window.location = "/admin/accounts/" + account['username'];
    };

    var username = document.createElement('div');
    username.className = 'cartSubElement';
    username.style.cssText = "width: 30%";
    username.textContent = account['username'];
    d.appendChild(username);

    var email = document.createElement('div');
    email.className = 'cartSubElement';
    email.style.cssText = "width: 30%";
    email.textContent = account['email'];
    d.appendChild(email);

    var roles = document.createElement('div');
    roles.className = 'cartSubElement';
    roles.style.cssText = "width: 30%";
    roles.textContent = account['roles'].toString();
    d.appendChild(roles);

    var enabled = document.createElement('div');
    enabled.className = 'cartSubElement';
    enabled.style.cssText = "width: 10%;text-align: center";
    enabled.textContent = account['enabled'];
    d.appendChild(enabled);

    container.appendChild(d);
}