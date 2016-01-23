/**
 * Created by nuwan.n.bandara on 6/3/2014.
 */
var addressGrid;

jQuery(document).ready(function() {
    populateAddressBookGrid();
    $('#deleteAddress').attr('disabled', true);
    $('#addressInfoEditBtn').attr('disabled', true);
    $('#addressInfoCancelBtn').attr('disabled', true);
    $('#addressInfoSaveBtn').attr('disabled', true);
    $('#phoneInfoEditBtn').attr('disabled', true);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#noteInfoEditBtn').attr('disabled', true);
    $('#noteInfoCancelBtn').attr('disabled', true);
    $('#noteInfoSaveBtn').attr('disabled', true);
    disableControls(true);
    disablePIControls(true);
    disableNoteControls(true);
});

function initializeAddressBookGrid() {
    var window_size = $(window).height();
    document.getElementById('addressBookGridbox').style.height = (window_size - 150) + "px";
    addressGrid = new dhtmlXGridObject('addressBookGridbox');
    addressGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    addressGrid.setHeader("Name");
    addressGrid.setInitWidths("*");
    addressGrid.setColAlign("left");
    addressGrid.setColTypes("ro");
    addressGrid.setColSorting("str");
    addressGrid.init();
    addressGrid.setSkin("dhx_skyblue");
    addressGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    addressGrid.attachEvent("onRowSelect", doOnRowSelected);
}

function populateAddressBookGrid() {
    initializeAddressBookGrid();
    $.ajax({type:'GET',
        url: 'addressBook/load',
         success: function(data) {
            populateAddressBookGridRequestHandler(data);
        }
    });
}

function populateAddressBookGridRequestHandler(data) {
    var addresses = data.getElementsByTagName("personalContacts/personalContact");
    if (addresses == null || addresses.length < 1) {
        addresses = data.getElementsByTagName("personalContact");
    }
    for (var i = 0; i < addresses.length; i++) {
        addressGrid.addRow(addresses[i].getAttribute("personalContactId"),
            [addresses[i].getAttribute("name")], -1);
    }
}

function doOnRowSelected(rowId,columnIndex) {
    disableControls(true);
    disablePIControls(true);
    disableNoteControls(true);
    refreshAddressInfo();
    refreshPhoneInfo();
    refreshNoteInfo();
    $('#deleteAddress').attr('disabled', false);
    $('#addressInfoEditBtn').attr('disabled', false);
    $('#addressInfoCancelBtn').attr('disabled', true);
    $('#addressInfoSaveBtn').attr('disabled', true);
    $('#phoneInfoEditBtn').attr('disabled', false);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#noteInfoEditBtn').attr('disabled', false);
    $('#noteInfoCancelBtn').attr('disabled', true);
    $('#noteInfoSaveBtn').attr('disabled', true);
    getAddressDetails(rowId);
    getContactDetails(rowId);
    getNoteDetails(rowId);
}

function getAddressDetails(rowId){

    $.ajax({
        url: 'addressBook/loadAddressDetails',
        data: ({personalContactId:rowId}),
        success: function(data) {
            displayAddressDetails(data);
        }
    });

}

function displayAddressDetails(data) {

    var address = data.getElementsByTagName("details");
    if (address == null || address.length < 1) {
        address = data.getElementsByTagName("contact/details");
    }
   document.getElementById("name").value=address[0].getAttribute("name");
   document.getElementById("contactPerson").value=address[0].getAttribute("contactPerson");
   document.getElementById("address1").value=address[0].getAttribute("address1");
   document.getElementById("address2").value=address[0].getAttribute("address2");
   document.getElementById("city").value=address[0].getAttribute("city");
   document.getElementById("state").value=address[0].getAttribute("state");
   document.getElementById("zip").value=address[0].getAttribute("zip");
   document.getElementById("website").value=address[0].getAttribute("website");
   document.getElementById("email").value=address[0].getAttribute("email");

}

function addNewAddress(){
    populateAddressBookGrid();
    disableControls(false);
    disablePIControls(true);
    refreshAddressInfo();
    refreshPhoneInfo();
    $('#deleteAddress').attr('disabled', true);
    $('#addressInfoEditBtn').attr('disabled', true);
    $('#addressInfoCancelBtn').attr('disabled', false);
    $('#addressInfoSaveBtn').attr('disabled', false);
    $('#phoneInfoEditBtn').attr('disabled', true);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);


}

function getContactDetails(rowId){

    $.ajax({
        url: 'addressBook/loadContactDetails',
        data: ({personalContactId:rowId}),
        success: function(data) {
            displayContactDetails(data);
        }
    });

}

function displayContactDetails(data) {

    var contact = data.getElementsByTagName("details");
    if (contact == null || contact.length < 1) {
        contact = data.getElementsByTagName("contact/details");
    }
    document.getElementById("cell").value=contact[0].getAttribute("cell");
    document.getElementById("home").value=contact[0].getAttribute("home");
    document.getElementById("fax").value=contact[0].getAttribute("fax");

}


function getNoteDetails(rowId){

    $.ajax({
        url: 'addressBook/loadNoteDetails',
        data: ({personalContactId:rowId}),
        success: function(data) {
            displayNoteDetails(data);
        }
    });

}

function displayNoteDetails(data) {

    var note = data.getElementsByTagName("details");
    if (note == null || note.length < 1) {
        note = data.getElementsByTagName("note/details");
    }
    document.getElementById("note").value=note[0].getAttribute("note");


}



function addNewAddress(){
    populateAddressBookGrid();
    disableControls(false);
    disablePIControls(true);
    disableNoteControls(true);
    refreshAddressInfo();
    refreshPhoneInfo();
    refreshNoteInfo();
    $('#deleteAddress').attr('disabled', true);
    $('#addressInfoEditBtn').attr('disabled', true);
    $('#addressInfoCancelBtn').attr('disabled', false);
    $('#addressInfoSaveBtn').attr('disabled', false);
    $('#phoneInfoEditBtn').attr('disabled', true);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#noteInfoEditBtn').attr('disabled', true);
    $('#noteInfoCancelBtn').attr('disabled', true);
    $('#noteInfoSaveBtn').attr('disabled', true);

}

function deleteAddress() {
    if(confirm("Do you want to delete this address?")) {
        $.ajax({
            url: 'addressBook/deleteAddress',
            data: ({rowId : addressGrid.getSelectedRowId()}),
            success: function(id) {
            }
        });
        sleep(1000);
        populateAddressBookGrid();

    }
}

function editAddressInfo() {
    disableControls(false);
    $('#addressInfoCancelBtn').attr('disabled', false);
    $('#addressInfoSaveBtn').attr('disabled', false);
}

function saveAddressInfo(){
    var name=document.getElementById("name").value;
    var contactPerson=document.getElementById("contactPerson").value;
    var address1=document.getElementById("address1").value;
    var address2=document.getElementById("address2").value;
    var city=document.getElementById("city").value;
    var state=document.getElementById("state").value;
    var zip=document.getElementById("zip").value;
    var website=document.getElementById("website").value;
    var email=document.getElementById("email").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'addressBook/updateAddress',
        data: ({name:name,contactPerson:contactPerson,address1:address1,address2:address2,city:city,
            state:state,zip:zip,website:website,email:email,userName:userName,
            rowId:addressGrid.getSelectedRowId()}),
        mimeType:"text/html; charset=UTF-8",
        success: function(result) {
            populateAddressBookGrid();
            var myTimer = window.setTimeout(function() {
                addressGrid.selectRowById(result,false,true,true);
            }, 500);
        }
    });
}

function editPhoneInfo() {
    disablePIControls(false);
    $('#phoneInfoCancelBtn').attr('disabled', false);
    $('#phoneInfoSaveBtn').attr('disabled', false);
}

function savePhoneInfo(){
    var cell=document.getElementById("cell").value;
    var home=document.getElementById("home").value;
    var fax=document.getElementById("fax").value;
    var userName=document.getElementById("userName").value;
    $.ajax({
        url: 'addressBook/savePhoneInfo',
        data: ({cell:cell,home:home,fax:fax,userName:userName,rowId:addressGrid.getSelectedRowId()}),
        success: function(id) {

        }
    });
    sleep(1000);
    doOnRowSelected(addressGrid.getSelectedRowId());

}


function editNoteInfo() {
    disableNoteControls(false);
    $('#noteInfoCancelBtn').attr('disabled', false);
    $('#noteInfoSaveBtn').attr('disabled', false);
}

function saveNoteInfo(){
    var note=document.getElementById("note").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'addressBook/saveNoteInfo',
        data: ({note:note,userName:userName,rowId:addressGrid.getSelectedRowId()}),
        success: function(id) {

        }
    });
    sleep(1000);
    doOnRowSelected(addressGrid.getSelectedRowId());

}

function disableControls(value) {
    $("#name").attr("disabled", value);
    $("#address1").attr("disabled", value);
    $("#address2").attr("disabled", value);
    $("#city").attr("disabled", value);
    $("#state").attr("disabled", value);
    $("#zip").attr("disabled", value);
    $("#website").attr("disabled", value);
    $("#email").attr("disabled", value);
    $("#contactPerson").attr("disabled", value);
}
function disablePIControls(value) {

    $("#cell").attr("disabled", value);
    $("#home").attr("disabled", value);
    $("#fax").attr("disabled", value);
}

function disableNoteControls(value){

    $("#note").attr("disabled", value);
}

function refreshAddressInfo() {
    $("#name").val("");
    $("#address1").val("");
    $("#address2").val("");
    $("#city").val("");
    $("#state").val("");
    $("#zip").val("");
    $("#website").val("");
    $("#email").val("");
    $("#contactPerson").val("");
}

function refreshPhoneInfo() {
    $("#cell").val("");
    $("#home").val("");
    $("#fax").val("");
}

function refreshNoteInfo() {
    $("#note").val("");

}
function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }

}