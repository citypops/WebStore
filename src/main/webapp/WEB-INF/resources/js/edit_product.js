var productId, prodName, price, taxAmount, active, imageName, oldProductId;

function init() {
    productId = document.getElementById('productId');
    prodName = document.getElementById('prodName');
    price = document.getElementById('price');
    taxAmount = document.getElementById('taxAmount');
    active = document.getElementById('active');
    imageName = document.getElementById('imageName');
}

function productRequest(method, url, okMessage) {
    var json = JSON.stringify({
        productId: productId.value,
        name: prodName.value,
        price: price.value,
        taxAmount: taxAmount.value,
        active: active.checked,
        imageName: imageName.value
    });
    genericRequest(method, url, json, okMessage, "/admin/products");
}

function addProduct() {
    productRequest("POST", "/product", "Product added succesfully.");
}

function updateProduct() {
    productRequest("PUT", "/product/" + oldProductId, "Product updated succesfully.")
}

function deleteProduct() {
    genericRequest("DELETE", "/product/" + oldProductId, null,
        "Product deleted succesfully.",
        "/admin/products");
}