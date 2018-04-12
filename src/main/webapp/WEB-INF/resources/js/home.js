var container, cartIndicator, logged_in;

function init() {
    cartIndicator = document.getElementById('cartIndicator');
    container = document.getElementById('container');
    callbackRequest('GET', '/product', generateItems);
}

function updateCartIndicator() {
    cartIndicator.textContent =
        "CART(" + Object.keys(cart['cartItems']).length + ")";
}

function postCartItem(product, quantity) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', "/cart", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if(this.readyState == 4) {
            if(this.status == 200) {
                getCart(updateCartIndicator);
            } else {
                alert(this.response);
            }
        }
    };
    xhr.send('productId=' + product + '&quantity=' + quantity);
}

function generateItems(products) {
    var prods = JSON.parse(products);
    container.innerHTML = "";
    for(var i = 0 ; i < prods.length ; i++) {
        if(prods[i]['active']) {
            addProductElement(prods[i]);
        }
    }
}

function addProductElement(product) {
    var productElement = document.createElement('div');
    productElement.className = 'product';

    var image = document.createElement('img');
    image.className = 'prodImg';
    image.src = 'resources/images/' + product['imageName'];
    productElement.appendChild(image);

    var name = document.createElement('div');
    name.className = "name";
    name.innerText = product['name'];
    productElement.appendChild(name);

    var price = document.createElement('div');
    price.className = "price";
    price.innerText = format2Decimal(product['price'], "$");
    productElement.appendChild(price);

    var quantityInput = document.createElement('input');
    quantityInput.type = 'number';
    quantityInput.className = 'prodInput';
    quantityInput.min = 1;
    quantityInput.value = 1;
    productElement.appendChild(quantityInput);

    var cartBtn = document.createElement('button');
    cartBtn.className = 'prodBtn';
    cartBtn.innerHTML = 'add to cart';
    cartBtn.onclick = function() {
        if(logged_in) {
            postCartItem(product['productId'],quantityInput.value)
        } else {
            window.location = "/login";
        }
    };
    productElement.appendChild(cartBtn);

    container.appendChild(productElement);
}