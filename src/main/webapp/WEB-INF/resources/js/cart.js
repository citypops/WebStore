var cartContainer, pProducts, pNettoSum, pBruttoSum, modal;

function initCart() {
    pProducts = document.getElementById('pProducts');
    pNettoSum = document.getElementById('pNettoSum');
    pBruttoSum = document.getElementById('pBruttoSum');
    cartContainer = document.getElementById('cartContainer');
    modal = document.getElementById('modal');
    window.onclick = function(event) {
        if(event.target == modal) {
            modal.style.display = "none";
        }
    };
    getCart(populateCart);
}

function getCart(doMore) {
    callbackRequest("GET","/cart", function(response) {
        cart = JSON.parse(response);
        if(doMore != undefined) {
            doMore();
        }
    });
}

function processOrder() {
    genericRequest("GET", "order/process", null,
        function(response) {
        return "Your order was processed successfully and its ID is: " + response },
        "/");
}

function cleanCart() {
    callbackRequest("DELETE","/cart", function() {
       getCart(populateCart);
    });
}

function removeCartItem(productId) {
    callbackRequest("DELETE", "/cart/" + productId, function() {
        getCart(populateCart);
    });
}

function updateCartItem(productId, quantity) {
    callbackRequest("PUT", "/cart/" + productId + "/" + quantity, function() {
        getCart(populateCart);
    });
}

function populateCart() {
    cartContainer.innerHTML = "";
    var vals = Object.values(cart['cartItems']);
    var length = vals.length;
    if(length == 0) {
        var d = document.createElement('div');
        d.className = 'nonClickableCartElement';
        d.style.cssText =
            "line-height: 50px; vertical-align: middle; text-align: center";
        d.textContent = "There are no products in the cart.";
        cartContainer.appendChild(d);
    } else {
        for(var i = 0 ; i < length ; i++) {
            addItemElement(vals[i]);
        }
    }
    pProducts.textContent = length;
    pNettoSum.textContent = format2Decimal(cart['nettoSum'], "$");
    pBruttoSum.textContent = format2Decimal(cart['bruttoSum'], "$");
}

function addItemElement(cartItem) {
    var d = document.createElement('div');
    d.className = 'cartElement';
    d.onclick = function() {
        showModalEditor(cartItem);
    };

    var name = document.createElement('div');
    name.className = 'cartSubElement';
    name.style.cssText = "width: 30%";
    name.textContent = cartItem.product['name'];
    d.appendChild(name);

    var prodId = document.createElement('div');
    prodId.className = 'cartSubElement';
    prodId.style.cssText = "width: 20%";
    prodId.textContent = cartItem.product['productId'];
    d.appendChild(prodId);

    var quantity = document.createElement('div');
    quantity.className = 'cartSubElement';
    quantity.style.cssText = "width: 10%;text-align: center";
    quantity.textContent = cartItem['quantity'];
    d.appendChild(quantity);

    var price = document.createElement('div');
    price.className = 'cartSubElement';
    price.style.cssText = "width: 10%;text-align: center";
    price.textContent = format2Decimal(cartItem.product['price'], "$");
    d.appendChild(price);

    var tax = document.createElement('div');
    tax.className = 'cartSubElement';
    tax.style.cssText = "width: 10%;text-align: center";
    tax.textContent = format2Decimal(cartItem.product['taxAmount']);
    d.appendChild(tax);

    var netto = document.createElement('div');
    netto.className = 'cartSubElement';
    netto.style.cssText = "width: 10%;text-align: right";
    netto.textContent = format2Decimal(cartItem['nettoSum'], "$");
    d.appendChild(netto);

    var brutto = document.createElement('div');
    brutto.className = 'cartSubElement';
    brutto.style.cssText = "width: 10%;text-align: right";
    brutto.textContent = format2Decimal(cartItem['bruttoSum'], "$");
    d.appendChild(brutto);

    cartContainer.appendChild(d);
}

function showModalEditor(item) {
    modal.style.display = "block";
    var modalContent = modal.childNodes[1];
    modalContent.innerHTML = "";

    var productId = document.createElement('div');
    productId.className = 'modalElement';
    productId.style.cssText = "width: 40%";
    productId.textContent = item.product['name'];
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
        updateCartItem(item.product['productId'], qty.value);
        modal.style.display = "none";
    };
    modalContent.appendChild(update);

    var remove = document.createElement('button');
    remove.className = 'modalElement';
    remove.style.cssText = "width: 20%";
    remove.textContent = "Remove this item";
    remove.onclick = function() {
        removeCartItem(item.product['productId']);
        modal.style.display = "none";
    };
    modalContent.appendChild(remove);

    var cancel = document.createElement('button');
    cancel.className = 'modalElement';
    cancel.style.cssText = "width: 10%";
    cancel.textContent = "Cancel";
    cancel.onclick = function() { modal.style.display = "none" };
    modalContent.appendChild(cancel);
}