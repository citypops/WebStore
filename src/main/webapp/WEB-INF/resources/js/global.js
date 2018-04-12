var cart;

function callbackRequest(method, url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(this.readyState == 4) {
            if(this.status == 200) {
                callback(xhr.responseText);
            } else {
                alert(this.response);
            }
        }
    };
    xhr.open(method, url, true);
    xhr.send();
}

function genericRequest(method, url, json, okMessage, location) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function() {
        if(this.readyState == 4) {
            if(this.status == 200) {
                if(okMessage instanceof Function) {
                    alert(okMessage(this.response));
                } else {
                    alert(okMessage);
                }
                window.location = location;
            } else {
                alert(this.responseText);
            }
        }
    };
    xhr.send(json);
}

function format2Decimal(value, prefix) {
    var result = value.toFixed(2);
    if(prefix != undefined) result = prefix + result;
    return result;
}