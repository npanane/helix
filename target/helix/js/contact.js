/**
 * client.js
 */
var contactGrid;
var addressGrid;
var phoneGrid;
//var phoneDataProcessor = new dataProcessor("contact/savePhone");
var phoneDataProcessor;

/*
 *
 */
jQuery(document).ready(function() {
    //disableControls(false);
    toggleSection();
    populateContactGrid();
    //initializePhoneGrid();
    initializeAddressGrid();
    //alert(window.opener);
    // On error
    if (document.getElementById("client.errors") != null) {
        document.getElementById("errorSummary").style.display = "";
        //disableControls(false);
        //$('#clearBtn').attr('disabled', false);
        //$('#submitBtn').attr('disabled', false);
    }
    if (window.dialogArguments != undefined) {
        if (window.dialogArguments[0].split("=")[0] == "action"
            && window.dialogArguments[0].split("=")[1] == "associateContact") {
            document.getElementById("header").style.display = "none";
            document.getElementById("search").style.paddingTop = "1px";
        }
    }
    else {
        document.getElementById("associateBtn").style.visibility = "hidden";
    }
});

function toggleSection() {
    jQuery(".search-content").hide();
    jQuery(".client-content").show();
    jQuery(".phone-content").show();
    jQuery(".address-content").show();
    //toggle the componenet with class msg_body
    jQuery(".search-heading").click(function() {
        jQuery(this).next(".search-content").slideToggle(500);
    });
    jQuery(".client-heading").click(function() {
        jQuery(this).next(".client-content").slideToggle(500);
    });
    jQuery(".phone-heading").click(function() {
        jQuery(this).next(".client-content").slideToggle(500);
    });
    jQuery(".address-heading").click(function() {
        jQuery(this).next(".address-content").slideToggle(500);
    });
}

function initializeContactGrid() {
    clientGrid = new dhtmlXGridObject('gridbox');
    clientGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    clientGrid.setHeader("First Name,Last Name");
    clientGrid.setInitWidths("150,*");
    clientGrid.setColAlign("left,left");
    clientGrid.setColTypes("ro,ro");
    clientGrid.setColSorting("str,str");
    clientGrid.setSkin("dhx_skyblue");
    clientGrid.init();
    clientGrid.attachEvent("onRowSelect", doOnRowSelected);
}

function doOnRowSelected(rowId,columnIndex) {
    disableControls(true);
    $("#contactId").attr("disabled", false);
    document.getElementById("contactId").value = rowId;
    document.getElementById("firstName").value = clientGrid.cells(rowId, 0).getValue();
    document.getElementById("lastName").value = clientGrid.cells(rowId, 1).getValue();
    document.getElementById("title").value = clientGrid.cells(rowId, 2).getValue();
    document.getElementById("primaryEmail").value = clientGrid.cells(rowId, 3).getValue();
    document.getElementById("secondaryEmail").value = clientGrid.cells(rowId, 4).getValue();
    document.getElementById("emailStatement").checked = (clientGrid.cells(rowId, 5).getValue() == "1");
    document.getElementById("faxStatement").checked = (clientGrid.cells(rowId, 6).getValue() == "1");
    document.getElementById("mailStatement").checked = (clientGrid.cells(rowId, 7).getValue() == "1");
    document.getElementById("note").value = clientGrid.cells(rowId, 8).getValue();
    $('#editBtn').attr('disabled', false);
    $('#deleteBtn').attr('disabled', false);
    document.getElementById("errorSummary").style.display = "none";
    populateAddressGrid();
    populatePhoneGrid();
}

function populateContactGrid() {
    initializeContactGrid();
    $.ajax({
        url: 'contact/load',
        success: function(data) {
            populateContactGridRequestHandler(data);
        }
    });
}

function populateContactGridRequestHandler(data) {
    var contacts = data.getElementsByTagName("contacts/contact");
    if (contacts == null || contacts.length < 1) {
        contacts = data.getElementsByTagName("contact");
    }
    for (var i = 0; i < contacts.length; i++) {
        clientGrid.addRow(contacts[i].getAttribute("contactId"),
            [contacts[i].getAttribute("firstName"),
                contacts[i].getAttribute("lastName"),
                contacts[i].getAttribute("title"),
                contacts[i].getAttribute("primaryEmail"),
                contacts[i].getAttribute("secondaryEmail"),
                contacts[i].getAttribute("emailStatement"),
                contacts[i].getAttribute("faxStatement"),
                contacts[i].getAttribute("mailStatement"),
                contacts[i].getAttribute("note")], -1);
        //clientGrid.setUserData(clients[i].getAttribute("productId"), "categoryId", clients[i].getAttribute("categoryId"));
    }
    //clearControls();
}

function add() {
    disableControls(false);
    document.getElementById("clietId").value = 0;
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
    if(confirm("Do you want to delete this client?")) {
        $.ajax({
            url: 'client/delete',
            data: ({id : clientGrid.getSelectedRowId()}),
            success: function(id) {
                populateClientGrid();
            }
        });
    }
}

function clearControls() {
    $("#name").val("");
    $("#acronym").val("");
}

function disableControls(value) {
    $("#firstName").attr("disabled", value);
    $("#lastName").attr("disabled", value);
    $("#title").attr("disabled", value);
    $("#primaryEmail").attr("disabled", value);
    $("#secondaryEmail").attr("disabled", value);
    $("#note").attr("disabled", value);
}

function submitOnClick() {
    /*
     $("#info").val(CKEDITOR.instances.editor1.getData());
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

/**
 * Phone grid
 */
function initializePhoneGrid() {
    phoneGrid = new dhtmlXGridObject('phoneGridbox');
    phoneGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    phoneGrid.setHeader("Category,Number");
    phoneGrid.setInitWidths("*,150");
    phoneGrid.setColAlign("left,left");
    phoneGrid.setColTypes("ed,ed");
    phoneGrid.setColSorting("str,str");
    phoneGrid.setSkin("dhx_skyblue");
    phoneGrid.init();
    //phoneDataProcessor.init(phoneGrid);
    //dp.setUpdateMode("off")
    //phoneDataProcessor.sendData();
    //setupPhoneDataProcessor();
}

function populatePhoneGrid() {
    initializePhoneGrid();
    $.ajax({
        url: 'contact/loadPhones',
        success: function(data) {
            populatePhoneGridRequestHandler(data);
        }
    });
}

function populatePhoneGridRequestHandler(data) {
    var phones = data.getElementsByTagName("phones/phone");
    if (phones == null || phones.length < 1) {
        phones = data.getElementsByTagName("phone");
    }
    for (var i = 0; i < phones.length; i++) {
        phoneGrid.addRow(phones[i].getAttribute("phoneId"),
            [phones[i].getAttribute("category"),
                phones[i].getAttribute("number")], -1);
    }
}

function addPhone(){
    var newId = (new Date()).valueOf()
    phoneGrid.addRow(newId, new Array("", ""), -1, phoneGrid.getRowsNum())
}

function savePhone(){
    phoneDataProcessor.sendData();
}

//-----------------------------------------------------
// Setup data processor object for phone data
//-----------------------------------------------------
function setupPhoneDataProcessor() {
    phoneDataProcessor = new dataProcessor("contact/savePhone");
    phoneDataProcessor.enableDebug(false);
    phoneDataProcessor.setUpdateMode("row");
    phoneDataProcessor.enableDataNames(true);
    //phoneDataProcessor.getSyncState();
    //phoneDataProcessor.setVerificator(0, onVerifyPart);
    //phoneDataProcessor.setVerificator(3);
    //phoneDataProcessor.setVerificator(4);
    //phoneDataProcessor.setOnAfterUpdate(afterUpdateHandler);
    //phoneDataProcessor.defineAction("error", onBOMError);
    phoneDataProcessor.init(phoneGrid);

}

/**
 * Address grid
 */
function initializeAddressGrid() {
    addressGrid = new dhtmlXGridObject('addressGridbox');
    addressGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    addressGrid.setHeader("Category,Street,PO Box,City,State,Zip,Country");
    addressGrid.setInitWidths("150,150,150,150,150,150,*");
    addressGrid.setColAlign("left,left,left,left,left,left,left");
    addressGrid.setColTypes("ed,ed,ed,ed,ed,ed,ed");
    addressGrid.setColSorting("int,str,str,str,str,str");
    addressGrid.setSkin("dhx_skyblue");
    addressGrid.init();
}

function populateAddressGrid() {
    initializeAddressGrid();
    $.ajax({
        url: 'contact/loadAddresses',
        success: function(data) {
            populateAddressGridRequestHandler(data);
        }
    });
}

function populateAddressGridRequestHandler(data) {
    var addresses = data.getElementsByTagName("addresses/address");
    if (addresses == null || addresses.length < 1) {
        addresses = data.getElementsByTagName("address");
    }
    for (var i = 0; i < addresses.length; i++) {
        addressGrid.addRow(addresses[i].getAttribute("addressId"),
            [addresses[i].getAttribute("category"),
                addresses[i].getAttribute("street"),
                addresses[i].getAttribute("poBox"),
                addresses[i].getAttribute("city"),
                addresses[i].getAttribute("state"),
                addresses[i].getAttribute("zip"),
                addresses[i].getAttribute("country")], -1);
    }
}

function addAddress(){
    var newId = (new Date()).valueOf()
    addressGrid.addRow(newId, new Array("", "", "", "", "", "", ""), -1, addressGrid.getRowsNum());
    addressGrid.selectRow(addressGrid.getRowIndex(newId), false, false, true);
}

function saveAddress(){
    alert("TODO");
}

function associateOnClick() {
    window.returnValue = clientGrid.getSelectedId();
    window.close();
}