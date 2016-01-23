
var clientGrid;

function init() {
    disableControls(false);
    populateProductGrid();
    //$('#clearBtn').attr('disabled', true);
    //$('#submitBtn').attr('disabled', true);
    //$('#editBtn').attr('disabled', true);
    //$('#deleteBtn').attr('disabled', true);
    // On error
    if (document.getElementById("client.errors") != null) {
        document.getElementById("errorSummary").style.display = "";
        disableControls(false);
        $("#clientCode").attr("disabled", true); // Overrider
        $('#clearBtn').attr('disabled', false);
        $('#submitBtn').attr('disabled', false);
    }

}

function initializeClientGrid() {
    clientGrid = new dhtmlXGridObject('gridbox');
    clientGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    clientGrid.setHeader("Product Id,Category,Sub Category,Name,Price,Info,Source URL");
    clientGrid.setInitWidths("80,150,150,150,100,*,100");
    clientGrid.setColAlign("left,left,left,left,left,left,left");
    clientGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
    clientGrid.setColSorting("int,str,str,str,str,str,str");
    clientGrid.setSkin("dhx_skyblue");
    clientGrid.init();
    clientGrid.attachEvent("onRowSelect", doOnRowSelected);
}

function doOnRowSelected(rowId,columnIndex) {
    disableControls(true);
    $("#productId").attr("disabled", false);
    document.getElementById("productId").value = rowId;
    document.getElementById("categoryId").value = clientGrid.getUserData(rowId, "categoryId");
    document.getElementById("subCategoryId").value = clientGrid.getUserData(rowId, "subCategoryId");
    document.getElementById("name").value = clientGrid.cells(rowId, 3).getValue();
    document.getElementById("price").value = clientGrid.cells(rowId, 4).getValue();
    CKEDITOR.instances.editor1.setData(clientGrid.cells(rowId, 5).getValue());
    document.getElementById("sourceUrl").value = clientGrid.cells(rowId, 6).getValue();
    $('#editBtn').attr('disabled', false);
    $('#deleteBtn').attr('disabled', false);
    document.getElementById("errorSummary").style.display = "none";
}

function populateProductGrid() {
    initializeClientGrid();
    $.ajax({
        url: 'product/load',
        success: function(data) {
            populateClientGridRequestHandler(data);
        }
    });
}

function populateClientGridRequestHandler(data) {
    var products = data.getElementsByTagName("products/product");
    if (products == null || products.length < 1) {
        products = data.getElementsByTagName("product");
    }
    for (var i = 0; i < products.length; i++) {
        clientGrid.addRow(products[i].getAttribute("productId"),
            [products[i].getAttribute("productId"),
                products[i].getAttribute("category"),
                products[i].getAttribute("subCategory"),
                products[i].getAttribute("name"),
                products[i].getAttribute("price"),
                products[i].getAttribute("info"),
                products[i].getAttribute("sourceUrl")], -1);
        clientGrid.setUserData(products[i].getAttribute("productId"), "categoryId", products[i].getAttribute("categoryId"));
        clientGrid.setUserData(products[i].getAttribute("productId"), "subCategoryId", products[i].getAttribute("subCategoryId"));
    }
    //clearControls();
}

function add() {
    disableControls(false);
    document.getElementById("productId").value = 0;
    clearControls();
    //document.getElementById("clientCode").focus();
    $('#clearBtn').attr('disabled', false);
    $('#submitBtn').attr('disabled', false);
}

function edit() {
    disableControls(false);
    //document.getElementById("clientCode").disabled = true; // Override
    //document.getElementById("name").focus();
    $('#clearBtn').attr('disabled', false);
    $('#submitBtn').attr('disabled', false);
}

function deletecClient() {
    if(confirm("Do you want to delete this product?")) {
        $.ajax({
            url: 'product/delete',
            data: ({id : clientGrid.getSelectedRowId()}),
            success: function(id) {
                populateProductGrid();
            }
        });
    }
}

function clearControls() {
    $("#categoryId").val("");
    $("#subCategoryId").val("");
    $("#name").val("");
    $("#price").val("");
    CKEDITOR.instances.editor1.setData("");
    $("#sourceUrl").val("");
}

function disableControls(value) {
    $("#categoryId").attr("disabled", value);
    $("#subCategoryId").attr("disabled", value);
    $("#name").attr("disabled", value);
    $("#price").attr("disabled", value);
    $("#editor1").attr("disabled", value);
    $("#sourceUrl").attr("disabled", value);
}

function submitOnClick() {
    $("#info").val(CKEDITOR.instances.editor1.getData());
    /*
     disableControls(false);
     if ($("#clientId").val() == "0") {
     if ($("#clientCodeValidateMsg").attr("src") == "images/wrong.gif") {
     alert("Please check the client code");
     document.getElementById("clientCode").focus();
     return false;
     }
     }
     */
}

/*function sameBillingAddOnClick(obj) {
 if(obj.checked) {
 $("#billingAddress").val($("#address").val());
 $("#billingCity").val($("#city").val());
 $("#billingState").val($("#state").val());
 $("#billingZipCode").val($("#zipCode").val());
 $("#billingAddress").attr("disabled", true);
 $("#billingCity").attr("disabled", true);
 $("#billingState").attr("disabled", true);
 $("#billingZipCode").attr("disabled", true);
 }
 else {
 $("#billingAddress").val("");
 $("#billingCity").val("");
 $("#billingState").val("");
 $("#billingZipCode").val("");
 $("#billingAddress").attr("disabled", false);
 $("#billingCity").attr("disabled", false);
 $("#billingState").attr("disabled", false);
 $("#billingZipCode").attr("disabled", false);
 }
 }*/

/*function chickClientCodeAvailability() {
 $.ajax({
 url: 'client/chickClientCodeAvailability',
 data: ({clientCode : $("#clientCode").val()}),
 success: function(data) {
 var message = data.getElementsByTagName("response");
 if (message[0].getAttribute("isTrue") == "true") {
 $("#clientCodeValidateMsg").attr({
 src: "images/wrong.gif",
 title: $("#clientCode").val() + " is not available"
 });
 }
 else {
 $("#clientCodeValidateMsg").attr({
 src: "images/ok.gif",
 title: $("#clientCode").val() + " is available"
 });
 }
 }
 });
 }*/

$(document).ready(function() {
    //add more file components if Add is clicked
    $('#addFile').click(function() {
        var fileIndex = $('#fileTable tr').children().length - 1;
        $('#fileTable').append(
            '<tr><td>'+
                '   <input type="file" name="files['+ ++fileIndex +']" />'+
                '</td></tr>');
    });

});
