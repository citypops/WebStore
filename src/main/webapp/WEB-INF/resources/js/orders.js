var container, pOrders, pWaiting, pRealised;

function init() {
    container = document.getElementById('container');
    pOrders = document.getElementById('pOrders');
    pWaiting = document.getElementById('pWaiting');
    pRealised = document.getElementById('pRealised');
    callbackRequest('GET', '/order', generateOrderElements);
}

function generateOrderElements(orders) {
    container.innerHTML = "";
    var realised = 0;
    var waiting = 0;
    var ords = JSON.parse(orders);
    var length = ords.length;
    for(var i = 0 ; i < length ; i++) {
        addOrderElement(ords[i]);
        if(ords[i]['state']) {
            realised++;
        } else {
            waiting++;
        }
    }
    pOrders.textContent = length;
    pWaiting.textContent = waiting;
    pRealised.textContent = realised;
}

function addOrderElement(order) {
    var d = document.createElement('div');
    d.className = 'cartElement';
    d.onclick = function() {
        window.location = "/admin/orders/" + order['orderId'];
    };

    var orderId = document.createElement('div');
    orderId.className = 'cartSubElement';
    orderId.style.cssText = "width: 50%";
    orderId.textContent = order['orderId'];
    d.appendChild(orderId);

    var username = document.createElement('div');
    username.className = 'cartSubElement';
    username.style.cssText = "width: 20%";
    username.textContent = order['username'];
    d.appendChild(username);

    var created = document.createElement('div');
    created.className = 'cartSubElement';
    created.style.cssText = "width: 20%";
    created.textContent = formatTimestamp(order['created']);
    d.appendChild(created);

    var state = document.createElement('div');
    state.className = 'cartSubElement';
    state.style.cssText = "width: 10%;text-align: center";
    state.textContent = formatOrderState(order['state']);
    d.appendChild(state);

    container.appendChild(d);
}

function formatTimestamp(timestamp) {
    var date = new Date(timestamp - (new Date).getTimezoneOffset() * 60000);
    return date.toISOString().replace(/z|t/gi,' ').split('.')[0];
}

function formatOrderState(bool) {
    if(bool) {
        return "Realised";
    } else {
        return "Waiting"
    }
}