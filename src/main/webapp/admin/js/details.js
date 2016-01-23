
var clientGrid;

function init() {
    setTimeout(loadProducts, 2000);
}

function loadProducts() {
    $.ajax({
        url: 'shop/product',
        success: function(data) {
            loadProductsRequestHandler(data);
        }
    });
}

function loadProductsRequestHandler(data) {
    var products = data.getElementsByTagName("products/product");
    var tmp1;
    var tmp2;
    for (var i = 0; i < products.length; i++) {
        if (i == 0) {
            tmp1 =
                "<a href='shop/product/" + products[i].getAttribute("productId") + "'>" + products[i].getAttribute("productId")
                    + "</a>" +
                    products[i].getAttribute("category") + " </br> " +
                    products[i].getAttribute("subCategory") + " </br> " +
                    products[i].getAttribute("name") + " </br> " +
                    products[i].getAttribute("price") + " </br> " +
                    products[i].getAttribute("info") + " </br> " +
                    products[i].getAttribute("sourceUrl");
        }
        else {
            tmp2 = "<a href='shop/product/" + products[i].getAttribute("productId") + "'>" + products[i].getAttribute("productId")
                + "</a>" +
                products[i].getAttribute("category") + " </br> " +
                products[i].getAttribute("subCategory") + " </br> " +
                products[i].getAttribute("name") + " </br> " +
                products[i].getAttribute("price") + " </br> " +
                products[i].getAttribute("info") + " </br> " +
                products[i].getAttribute("sourceUrl");
        }

    }
    document.getElementById("p1").innerHTML = tmp1;
    document.getElementById("p2").innerHTML = tmp2;
}