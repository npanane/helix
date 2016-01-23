/**
 * vendor.js
 */
var vendorGrid;
var vendorContactGrid;
var addressGrid;

/*
 *
 */
jQuery(document).ready(function() {
    initialState();
});

function initialState(){
    $('#deleteVendor').attr('disabled', true);
    $('#VendorInfoEditBtn').attr('disabled', true);
    $('#VendorInfoCancelBtn').attr('disabled', true);
    $('#VendorInfoSaveBtn').attr('disabled', true);
    $('#phoneInfoEditBtn').attr('disabled', true);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#AddNewContactInfoBtn').attr('disabled', true);
    $('#ContactInfoEditBtn').attr('disabled', true);
    $('#ContactInfoDeleteBtn').attr('disabled', true);
    refreshVendorInfo();
    refreshPhoneInfo();
    disableControls(true);
    disablePIControls(true);
    populateVendorGrid();
    initializeContactGrid();



}

function initializeVendorGrid() {
    var window_size = $(window).height();
    document.getElementById('vendorGridbox').style.height = (window_size - 150) + "px";
    vendorGrid = new dhtmlXGridObject('vendorGridbox');
    vendorGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    vendorGrid.setHeader("Vendor Name");
    vendorGrid.setInitWidths("*");
    vendorGrid.setColAlign("left");
    vendorGrid.setColTypes("ro");
    vendorGrid.setColSorting("str");
    vendorGrid.setSkin("dhx_skyblue");
    vendorGrid.attachEvent("onRowSelect", doOnRowSelected);
    vendorGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    vendorGrid.init();

}


function populateVendorGrid() {
    initializeVendorGrid();
    $.ajax({
        url: 'vendor/load',
        success: function(data) {
            populateVendorGridRequestHandler(data);
        }
    });
}

function populateVendorGridRequestHandler(data) {
    var vendors = data.getElementsByTagName("vendors/vendor");
    if (vendors == null || vendors.length < 1) {
        vendors = data.getElementsByTagName("vendor");
    }
    for (var i = 0; i < vendors.length; i++) {
        vendorGrid.addRow(vendors[i].getAttribute("vendorId"),
            [vendors[i].getAttribute("vendorName")], -1);
    }

}

function doOnRowSelected(rowId,columnIndex) {
    refreshVendorInfo();
    refreshPhoneInfo();
    refreshVendorContactInfo();
    disableControls(true);
    disablePIControls(true);
    $('#deleteVendor').attr('disabled', false);
    $('#VendorInfoEditBtn').attr('disabled', false);
    $('#VendorInfoCancelBtn').attr('disabled', true);
    $('#VendorInfoSaveBtn').attr('disabled', true);
    $('#phoneInfoEditBtn').attr('disabled', false);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#AddNewContactInfoBtn').attr('disabled', false);
    $('#ContactInfoEditBtn').attr('disabled', true);
    $('#ContactInfoDeleteBtn').attr('disabled', true);
    getVendorDetails(rowId);
    getVendorPhoneNumbers(rowId);
    populateContactGrid(rowId);
}

function getVendorDetails(rowId){

    $.ajax({
        url: 'vendor/loadVendorDetails',
        data: ({vendorId:rowId}),
        success: function(data) {
            displayVendorDetails(data);
        }
    });

}

function displayVendorDetails(data) {

    var vendor = data.getElementsByTagName("details");
    if (vendor != null || vendor.length > 0) {
        document.getElementById("name").value=vendor[0].getAttribute("vendorName");
        document.getElementById("AccNumber").value=vendor[0].getAttribute("accountNo");
        document.getElementById("address1").value=vendor[0].getAttribute("address1");
        document.getElementById("type").value=vendor[0].getAttribute("type");
        document.getElementById("address2").value=vendor[0].getAttribute("address2");
        document.getElementById("city").value=vendor[0].getAttribute("city");
        document.getElementById("state").value=vendor[0].getAttribute("state");
        document.getElementById("zip").value=vendor[0].getAttribute("zip");
        document.getElementById("website").value=vendor[0].getAttribute("website");

    }

}

function getVendorPhoneNumbers(rowId){

    $.ajax({
        url: 'vendor/loadVendorPhoneDetails',
        data: ({vendorId:rowId}),
        success: function(data) {
            displayVendorPhoneDetails(data);
        }
    });

}

function displayVendorPhoneDetails(data) {

    var vendor = data.getElementsByTagName("phoneDetails");
    if (vendor != null || vendor.length > 0) {
        document.getElementById("mainPhone").value=vendor[0].getAttribute("mainPhone");
        document.getElementById("fax").value=vendor[0].getAttribute("fax");
    }

}



function addVendor() {
    refreshVendorInfo();
    refreshPhoneInfo();
    disableControls(false);
    disablePIControls(true);
    $('#deleteVendor').attr('disabled', true);
    $('#VendorInfoEditBtn').attr('disabled', true);
    $('#VendorInfoCancelBtn').attr('disabled', false);
    $('#VendorInfoSaveBtn').attr('disabled', false);
    $('#phoneInfoEditBtn').attr('disabled', true);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#AddNewContactInfoBtn').attr('disabled', true);
    $('#ContactInfoEditBtn').attr('disabled', true);
    $('#ContactInfoDeleteBtn').attr('disabled', true);
    populateVendorGrid();
    initializeContactGrid();
}

function deleteVendor() {
    if(confirm("Do you want to delete this vendor?")) {
        $.ajax({
            url: 'vendor/deleteVendor',
            data: ({rowId : vendorGrid.getSelectedRowId()}),
            success: function(id) {
                initialState();
            }
        });
    }
}

function saveVendorInfo(){
    var name=document.getElementById("name").value;
    var accNumber=document.getElementById("AccNumber").value;
    var address1=document.getElementById("address1").value;
    var type=document.getElementById("type").value;
    var address2=document.getElementById("address2").value;
    var city=document.getElementById("city").value;
    var state=document.getElementById("state").value;
    var zip=document.getElementById("zip").value;
    var website=document.getElementById("website").value;
    var userName=document.getElementById("userName").value;
    $.ajax({
        url: 'vendor/updateVendor',
        data: ({name:name,accNumber:accNumber,address1:address1,address2:address2,type:type,city:city,
            state:state,zip:zip,website:website,userName:userName,vendorId:vendorGrid.getSelectedRowId()}),
        mimeType:"text/html; charset=UTF-8",
        success: function(result) {
            populateVendorGrid();
            var myTimer = window.setTimeout(function() {
                vendorGrid.selectRowById(result,false,true,true);
            }, 500);
        }
    });


}

function editVendorInfo() {
    disableControls(false);
    $('#VendorInfoCancelBtn').attr('disabled', false);
    $('#VendorInfoSaveBtn').attr('disabled', false);
}



function editPhoneInfo() {
    disablePIControls(false);
    $('#phoneInfoCancelBtn').attr('disabled', false);
    $('#phoneInfoSaveBtn').attr('disabled', false);
}

function savePhoneInfo(){
    var mainPhone=document.getElementById("mainPhone").value;
    var fax=document.getElementById("fax").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'vendor/savePhoneInfo',
        data: ({mainPhone:mainPhone,fax:fax,userName:userName,vendorId:vendorGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(vendorGrid.getSelectedRowId());

}

function refreshVendorInfo() {
    $("#name").val("");
    $("#AccNumber").val("");
    $("#address1").val("");
    $("#type").val("");
    $("#address2").val("");
    $("#city").val("");
    $("#state").val("");
    $("#zip").val("");
    $("#website").val("");

}

function refreshPhoneInfo() {
    $("#mainPhone").val("");
    $("#fax").val("");
}

function refreshVendorContactInfo() {
    document.getElementById("CCfirstName").value ="";
    document.getElementById("CClastName").value ="";
    document.getElementById("CCtitle").value ="";
    document.getElementById("CCMain").value ="";
    document.getElementById("CCHome").value ="";
    document.getElementById("CCMobile").value ="";
    document.getElementById("CCOther").value ="";
    document.getElementById("CCFax").value ="";
    document.getElementById("CCEmail").value ="";
    document.getElementById("CCAltEmail").value ="";
    document.getElementById("CCNotes").value ="";
}

function disableControls(value) {
    $("#name").attr("disabled", value);
    $("#AccNumber").attr("disabled", value);
    $("#address1").attr("disabled", value);
    $("#type").attr("disabled", value);
    $("#address2").attr("disabled", value);
    $("#city").attr("disabled", value);
    $("#state").attr("disabled", value);
    $("#zip").attr("disabled", value);
    $("#website").attr("disabled", value);
}
function disablePIControls(value) {

    $("#mainPhone").attr("disabled", value);
    $("#fax").attr("disabled", value);
}

function addAddress(){
    var newId = (new Date()).valueOf()
    addressGrid.addRow(newId, new Array("", "", "", "", "", "", ""), -1, addressGrid.getRowsNum());
    addressGrid.selectRow(addressGrid.getRowIndex(newId), false, false, true);
}



/**
 * Contact grid
 */
function initializeContactGrid() {
    vendorContactGrid = new dhtmlXGridObject('vendorContactGridbox');
    vendorContactGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    vendorContactGrid.setHeader("Name,Title,Phone,Home,Mobile,Other,Fax,Email");
    vendorContactGrid.setInitWidths("*,100,80,80,80,80,80,*");
    vendorContactGrid.setColAlign("left,left,left,left,left,left,left,left");
    vendorContactGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
    vendorContactGrid.setColSorting("str,str,str,str,str,str,str,str");
    vendorContactGrid.setSkin("dhx_skyblue");
    vendorContactGrid.attachEvent("onRowSelect", doOnRowSelectedContactGrid);
    vendorContactGrid.enableAutoHeight(true);
    vendorContactGrid.init();


}

function populateContactGrid(rowId) {
    $.ajax({
        url: 'vendor/loadContacts',
        data: "rowId=" +rowId,
        success: function(data) {
            populateContactGridRequestHandler(data);
        }
    });
}

function populateContactGridRequestHandler(data) {
    if (vendorContactGrid != null) {
        vendorContactGrid.clearAll();
    }
    var contacts = data.getElementsByTagName("contacts/contact");
    if (contacts == null || contacts.length < 1) {
        contacts = data.getElementsByTagName("contact");
    }
    for (var i = 0; i < contacts.length; i++) {
        vendorContactGrid.addRow(contacts[i].getAttribute("clientId"),
            [contacts[i].getAttribute("name"),
                contacts[i].getAttribute("title"),
                contacts[i].getAttribute("phone"),
                contacts[i].getAttribute("home"),
                contacts[i].getAttribute("mobile"),
                contacts[i].getAttribute("other"),
                contacts[i].getAttribute("fax"),
                contacts[i].getAttribute("email")], -1);
    }

}

function doOnRowSelectedContactGrid(rowId)
{
    $('#ContactInfoEditBtn').attr('disabled', false);
    $('#ContactInfoDeleteBtn').attr('disabled', false);
    getSelectedVendorContact(rowId);
}


function getSelectedVendorContact(rowId) {

    $.ajax({
        url: 'vendor/loadSelectedVendorContact',
        data: "rowId=" +rowId,
        success: function(data) {
            populateSVContactGridRequestHandler(data);
        }
    });
}

function populateSVContactGridRequestHandler(data) {
    var contact =  data.getElementsByTagName("contact");
    document.getElementById("editCCfirstName").value = contact[0].getAttribute("firstName");
    document.getElementById("editCClastName").value = contact[0].getAttribute("lastName");
    document.getElementById("editCCtitle").value = contact[0].getAttribute("title");
    document.getElementById("editCCMain").value = contact[0].getAttribute("main");
    document.getElementById("editCCHome").value = contact[0].getAttribute("home");
    document.getElementById("editCCMobile").value = contact[0].getAttribute("mobile");
    document.getElementById("editCCOther").value = contact[0].getAttribute("other");
    document.getElementById("editCCFax").value = contact[0].getAttribute("fax");
    document.getElementById("editCCEmail").value = contact[0].getAttribute("email");
    document.getElementById("editCCAltEmail").value = contact[0].getAttribute("alertEmail");
    document.getElementById("editCCNotes").value = contact[0].getAttribute("note");


}

function deleteContactInfo() {
    if(confirm("Do you want to delete this Contact Information?")) {
        $.ajax({
            url: 'vendor/deleteContactInfo',
            data: ({rowId : vendorContactGrid.getSelectedRowId()}),
            success: function(id) {
                doOnRowSelected(vendorGrid.getSelectedRowId());
            }
        });
    }
}

function addNewContactInfo() {

    var CCfirstName=document.getElementById("CCfirstName").value;
    var CClastName=document.getElementById("CClastName").value;
    var CCtitle=document.getElementById("CCtitle").value;
    var CCMain=document.getElementById("CCMain").value;
    var CCHome=document.getElementById("CCHome").value;
    var CCMobile=document.getElementById("CCMobile").value;
    var CCOther=document.getElementById("CCOther").value;
    var CCFax=document.getElementById("CCFax").value;
    var CCEmail=document.getElementById("CCEmail").value;
    var CCAltEmail=document.getElementById("CCAltEmail").value;
    var CCNotes=document.getElementById("CCNotes").value;
    var userName=document.getElementById("userName").value;
    $.ajax({
        url: 'vendor/addNewContactInfo',
        data: ({firstName:CCfirstName,lastName:CClastName,title:CCtitle,CCMain:CCMain,CCHome:CCHome,
            CCMobile:CCMobile,CCOther:CCOther,CCFax:CCFax,CCAltEmail:CCAltEmail,
            CCEmail:CCEmail,CCNotes:CCNotes,userName:userName,rowID:vendorGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(vendorGrid.getSelectedRowId());
}



function editContactInfo() {
    var CCfirstName=document.getElementById("editCCfirstName").value;
    var CClastName=document.getElementById("editCClastName").value;
    var CCtitle=document.getElementById("editCCtitle").value;
    var CCMain=document.getElementById("editCCMain").value;
    var CCHome=document.getElementById("editCCHome").value;
    var CCMobile=document.getElementById("editCCMobile").value;
    var CCOther=document.getElementById("editCCOther").value;
    var CCFax=document.getElementById("editCCFax").value;
    var CCEmail=document.getElementById("editCCEmail").value;
    var CCAltEmail=document.getElementById("editCCAltEmail").value;
    var CCNotes=document.getElementById("editCCNotes").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'vendor/updateContactInfo',
        data: ({firstName:CCfirstName,lastName:CClastName,title:CCtitle,CCMain:CCMain,CCHome:CCHome,
            CCMobile:CCMobile,CCOther:CCOther,CCEmail:CCEmail,CCAltEmail:CCAltEmail,CCFax:CCFax,
            CCNotes:CCNotes,rowID:vendorGrid.getSelectedRowId(),userName:userName,
            vendorContactId:vendorContactGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(vendorGrid.getSelectedRowId());
}


$(function() {
    $('#addNewContatctInfoBtn2').click(function() {
        $('#createNewContacts').modal('hide');
    });
});

$(function() {
    $('#editContatctInfoBtn2').click(function() {
        $('#editContacts').modal('hide');
    });
});

function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
}