var state, elementClass, orderItems, orderId, itemsdiv, container, pProducts,
    pNettoSum, pBruttoSum, modal;

function init() {
    itemsdiv = document.getElementById('itemsdiv');
    container = document.getElementById('container');
    pProducts = document.getElementById('pProducts');
    pNettoSum = document.getElementById('pNettoSum');
    pBruttoSum = document.getElementById('pBruttoSum');
    modal = document.getElementById('modal');
    window.onclick = function(event) {
        if(event.target == modal) {
            modal.style.display = "none";
        }
    };
    if(state) {
        elementClass = "nonClickableCartElement";
    } else {
        elementClass = "cartElement";
    }
    generateOrderItemElements(orderItems);
}

function generateOrderItemElements(items) {
    container.innerHTML = "";
    var nettoSum = 0;
    var bruttoSum = 0;
    var length = items.length;
    for(var i = 0 ; i < length ; i++) {
        addOrderItemElement(items[i]);
        nettoSum += items[i]['nettoSum'];
        bruttoSum += items[i]['bruttoSum'];
    }
    if(!state) {
        var addButton = document.createElement('div');
        addButton.className = 'cartElement';
        addButton.style.cssText =
            "line-height: 50px; vertical-align: middle; text-align: center";
        addButton.textContent = "+ Add Order Item";
        addButton.onclick = function() {
            showModalAdd();
        };
        itemsdiv.appendChild(addButton);
    }

    pProducts.textContent = length;
    pNettoSum.textContent = format2Decimal(nettoSum, "$");
    pBruttoSum.textContent = format2Decimal(bruttoSum, "$");
}

function addOrderItemElement(item) {
    var d = document.createElement('div');
    d.className = elementClass;
    if(!state) d.onclick = function() {
        showModalEditor(item)
    };

    var productId = document.createElement('div');
    productId.className = 'cartSubElement';
    productId.style.cssText = "width: 40%";
    productId.textContent = item['productId'];
    d.appendChild(productId);

    var qty = document.createElement('div');
    qty.className = 'cartSubElement';
    qty.style.cssText = "width: 12%;text-align: center";
    qty.textContent = item['quantity'];
    d.appendChild(qty);

    var price = document.createElement('div');
    price.className = 'cartSubElement';
    price.style.cssText = "width: 12%;text-align: center";
    price.textContent = format2Decimal(item['soldPrice'], "$");
    d.appendChild(price);

    var tax = document.createElement('div');
    tax.className = 'cartSubElement';
    tax.style.cssText = "width: 12%;text-align: center";
    tax.textContent = format2Decimal(item['soldTax']);
    d.appendChild(tax);

    var netto = document.createElement('div');
    netto.className = 'cartSubElement';
    netto.style.cssText = "width: 12%;text-align: right";
    netto.textContent = format2Decimal(item['nettoSum'], "$");
    d.appendChild(netto);

    var brutto = document.createElement('div');
    brutto.className = 'cartSubElement';
    brutto.style.cssText = "width: 12%;text-align: right";
    brutto.textContent = format2Decimal(item['bruttoSum'], "$");
    d.appendChild(brutto);

    container.appendChild(d);
}

function showModalEditor(item) {
    modal.style.display = "block";
    var modalContent = modal.childNodes[1];
    modalContent.innerHTML = "";

    var productId = document.createElement('div');
    productId.className = 'modalElement';
    productId.style.cssText = "width: 40%";
    productId.textContent = item['productId'];
    modalContent.appendChild(productId);

    var qty = document.createElement('input');
    qty.type = "number";
    qty.min = 1;
    qty.className = 'modalElement';
    qty.style.cssText = "width: 10%;text-align: center";
    qty.value = item['quantity'];
    modalContent.appendChild(qty);

    var update = document.createElement('button');
    update.className = 'modalElement';
    update.style.cssText = "width: 20%";
    update.textContent = "Update Quantity";
    update.onclick = function() {
        var data = "orderId=" + orderId + "&productId=" + item['productId'] + "&quantity=" + qty.value;
        itemRequest("PUT", data);
    };
    modalContent.appendChild(update);

    var remove = document.createElement('button');
    remove.className = 'modalElement';
    remove.style.cssText = "width: 20%";
    remove.textContent = "Remove this item";
    remove.onclick = function() {
        var data = "orderId=" + orderId + "&productId=" + item['productId'];
        itemRequest("DELETE", data);
    };
    modalContent.appendChild(remove);

    var cancel = document.createElement('button');
    cancel.className = 'modalElement';
    cancel.style.cssText = "width: 10%";
    cancel.textContent = "Cancel";
    cancel.onclick = function() { modal.style.display = "none" };
    modalContent.appendChild(cancel);
}

function showModalAdd() {
    modal.style.display = "block";
    var modalContent = modal.childNodes[1];
    modalContent.innerHTML = "";

    var text = document.createElement("div");
    text.className = "modalElement";
    text.textContent = "Product ID:";
    text.style.cssText = "width: 15%";
    modalContent.appendChild(text);

    var productId = document.createElement('input');
    productId.className = 'modalElement';
    productId.style.cssText = "width: 45%";
    modalContent.appendChild(productId);

    var qty = document.createElement('input');
    qty.type = "number";
    qty.min = 1;
    qty.className = 'modalElement';
    qty.style.cssText = "width: 10%;text-align: center";
    qty.value = 1;
    modalContent.appendChild(qty);

    var add = document.createElement('button');
    add.className = 'modalElement';
    add.style.cssText = "width: 20%";
    add.textContent = "Add Product";
    add.onclick = function() {
        var method = "POST";
        var length = orderItems.length;
        var quantity = parseInt(qty.value);
        for(var i = 0 ; i < length ; i++) {
            if(orderItems[i]['productId'].toUpperCase() == productId.value.toUpperCase()) {
                method = "PUT";
                quantity += orderItems[i]['quantity'];
                break;
            }
        }
        var data = "orderId=" + orderId + "&productId=" + productId.value + "&quantity=" + quantity;
        itemRequest(method, data);
    };
    modalContent.appendChild(add);

    var cancel = document.createElement('button');
    cancel.className = 'modalElement';
    cancel.style.cssText = "width: 20%";
    cancel.textContent = "Cancel";
    cancel.onclick = function() { modal.style.display = "none" };
    modalContent.appendChild(cancel);
}

function itemRequest(method, data) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, "/order/item", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if(this.readyState == 4) {
            if(this.status == 200) {
                window.location = "/admin/orders/" + orderId;
            } else {
                alert(this.responseText);
            }
        }
    };
    xhr.send(data);
}

function deleteOrder() {
    genericRequest("DELETE", "/order/" + orderId, null,
        "Order deleted successfully.",
        "/admin/orders");
}

function realiseOrder() {
    genericRequest("GET", "/order/realise/" + orderId, null,
        "Order realised successfully.",
        "/admin/orders")
}