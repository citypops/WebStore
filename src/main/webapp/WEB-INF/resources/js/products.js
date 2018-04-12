var container, pProducts, pActive, pInactive;

function init() {
    container = document.getElementById('container');
    pProducts = document.getElementById('pProducts');
    pActive = document.getElementById('pActive');
    pInactive = document.getElementById('pInactive');
    callbackRequest('GET','/product', generateProductElements);
}

function generateProductElements(products) {
    container.innerHTML = "";
    var active = 0;
    var inactive = 0;
    var prods = JSON.parse(products);
    var length = prods.length;
    for(var i = 0 ; i < length ; i++) {
        addProductElement(prods[i]);
        if(prods[i]['active']) {
            active++;
        } else {
            inactive++;
        }
    }
    pProducts.textContent = length;
    pActive.textContent = active;
    pInactive.textContent = inactive;
}

function addProductElement(product) {
    var d = document.createElement('div');
    d.className = 'cartElement';
    d.onclick = function() {
        window.location = "/admin/products/" + product['productId'];
    };

    var name = document.createElement('div');
    name.className = 'cartSubElement';
    name.style.cssText = "width: 30%";
    name.textContent = product['name'];
    d.appendChild(name);

    var prodId = document.createElement('div');
    prodId.className = 'cartSubElement';
    prodId.style.cssText = "width: 25%";
    prodId.textContent = product['productId'];
    d.appendChild(prodId);

    var price = document.createElement('div');
    price.className = 'cartSubElement';
    price.style.cssText = "width: 10%;text-align: center";
    price.textContent = format2Decimal(product['price'], "$");
    d.appendChild(price);

    var tax = document.createElement('div');
    tax.className = 'cartSubElement';
    tax.style.cssText = "width: 10%;text-align: center";
    tax.textContent = format2Decimal(product['taxAmount']);
    d.appendChild(tax);

    var image = document.createElement('div');
    image.className = 'cartSubElement';
    image.style.cssText = "width: 15%";
    image.textContent = product['imageName'];
    d.appendChild(image);

    var active = document.createElement('div');
    active.className = 'cartSubElement';
    active.style.cssText = "width: 10%; text-align: center";
    active.textContent = product['active'];
    d.appendChild(active);

    container.appendChild(d);
}