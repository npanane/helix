var referencesGrid;
var ContactNamesListER;
var ContactNamesList;

jQuery(document).ready(function() {
    populateReferenceGrid();
    $('#ReferenceInfoEditBtn').attr('disabled', true);
    $('#ReferenceInfoDeleteBtn').attr('disabled', true);
    getClientContacts();

});

function initializeReferenceGrid() {
    var window_size = $(window).height();
    document.getElementById('referencesGridbox').style.height = (window_size - 200) + "px";
    referencesGrid = new dhtmlXGridObject('referencesGridbox');
    referencesGrid.setImagePath("assets/images/imgs/");
    referencesGrid.setHeader("Client, Photo, Signature, Badge, Causes, Articles, By-Laws, IRS Ltr, Contact, Phone, Tax ID, PO Box, City, State, Zip, Ghost#");
    referencesGrid.setInitWidths("*,70,73,70,70,70,70,70,70,70,70,70,70,70,70,70");
    referencesGrid.setColAlign("left,left,left,left");
    referencesGrid.setColTypes("ro,ch,ch,ch,ch,ch,ch,ch,ch,ch,ch,ch,ch,ch,ch,ch,ch");
    referencesGrid.setColSorting("na,str,str,str");
    referencesGrid.init();
    referencesGrid.setSkin("dhx_skyblue");
    referencesGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    referencesGrid.attachEvent("onRowSelect", doOnRowSelected);

}

function populateReferenceGrid() {
    initializeReferenceGrid();
    $.ajax({type:'GET',
        url: 'missingItem/loadMissingItems',
        success: function(data) {
            populateMissingItemGridRequestHandler(data);
        }
    });
}

function populateMissingItemGridRequestHandler(data) {
    var missingItems = data.getElementsByTagName("missingItems/missingItem");
    if (missingItems == null || missingItems.length <= 1) {
        missingItems = data.getElementsByTagName("missingItem");
    }
    for (var i = 0; i < missingItems.length; i++) {
        referencesGrid.addRow(missingItems[i].getAttribute("clientId"),
            [missingItems[i].getAttribute("clientName"),
                missingItems[i].getAttribute("flPhoto"),
                missingItems[i].getAttribute("flSignature"),
                missingItems[i].getAttribute("flBadge"),
                missingItems[i].getAttribute("flCauses"),
                missingItems[i].getAttribute("articles"),
                missingItems[i].getAttribute("byLaw"),
                missingItems[i].getAttribute("irsLtrz"),
                missingItems[i].getAttribute("contact"),
                missingItems[i].getAttribute("phone"),
                missingItems[i].getAttribute("taxId"),
                missingItems[i].getAttribute("poBox"),
                missingItems[i].getAttribute("city"),
                missingItems[i].getAttribute("state"),
                missingItems[i].getAttribute("zip"),
                missingItems[i].getAttribute("ghostNo")
            ], -1);
    }
}

function doOnRowSelected(rowId,columnIndex) {

    $('#ReferenceInfoEditBtn').attr('disabled', false);
    $('#ReferenceInfoDeleteBtn').attr('disabled', false);
    getReferenceInformation(rowId);

}

function getReferenceInformation(rowId){

    $.ajax({
        url: 'references/getReferenceInformation',
        data: ({rowId:rowId}),
        success: function(data) {
            displayReferenceInformation(data);
        }
    });

}

function displayReferenceInformation(data) {

    var referenceInfo = data.getElementsByTagName("details");
    if (referenceInfo != null || referenceInfo.length > 0) {
        document.getElementById("clientNameER").value=referenceInfo[0].getAttribute("Name");
        document.getElementById("contactNameER").value=referenceInfo[0].getAttribute("Contact");
        document.getElementById("phoneER").value=referenceInfo[0].getAttribute("phoneNumber");
        document.getElementById("zipER").value=referenceInfo[0].getAttribute("zip");

    }
    getClientContactsER();

}



function getClientContacts(){


    var clientId=document.getElementById("clientName").value;
    $.ajax({
        url: 'references/getClientContacts',
        data: ({clientId:clientId}),
        success: function(data) {
            showContactName(data);
        }
    });

}

function getClientContactsER(){


    var clientId=document.getElementById("clientNameER").value;
    $.ajax({
        url: 'references/getClientContacts',
        data: ({clientId:clientId}),
        success: function(data) {
            showContactNameER(data);
        }
    });

}


function showContactName(data) {

    ContactNamesList = data.getElementsByTagName("contactName");
    if (ContactNamesList != null || ContactNamesList.length > 0) {
        var contactNamesArray = new Array();
        for (var i = 0; i < ContactNamesList.length; i++) {
            contactNamesArray[i] = ContactNamesList[i].getAttribute("Name");
        }
        var options = '';

        for (var i = 0; i < contactNamesArray.length; i++)
            options += '<option value="' + contactNamesArray[i] + '" />';

        document.getElementById('contactNames').innerHTML = options;

    }


}

function showContactNameER(data) {

    ContactNamesListER = data.getElementsByTagName("contactName");
    if (ContactNamesListER != null || ContactNamesListER.length > 0) {
        var contactNamesArray = new Array();
        for (var i = 0; i < ContactNamesListER.length; i++) {
            contactNamesArray[i] = ContactNamesListER[i].getAttribute("Name");
        }
        var options = '';

        for (var i = 0; i < contactNamesArray.length; i++)
            options += '<option value="' + contactNamesArray[i] + '" />';

        document.getElementById('contactNamesER').innerHTML = options;
    }
}

function getPhonesNo(){

    document.getElementById("phone").value="";
    var contactName=document.getElementById("contactName").value;

    if (ContactNamesList != null || ContactNamesList.length > 0) {

        for (var i = 0; i < ContactNamesList.length; i++) {
            if(contactName == ContactNamesList[i].getAttribute("Name")){
                document.getElementById("phone").value =ContactNamesList[i].getAttribute("phoneNumber");
            }
        }
    }


}

function getPhonesNoER(){

    document.getElementById("phoneER").value="";
    var contactName=document.getElementById("contactNameER").value;

    if (ContactNamesListER != null || ContactNamesListER.length > 0) {

        for (var i = 0; i < ContactNamesListER.length; i++) {
            if(contactName == ContactNamesListER[i].getAttribute("Name")){
                document.getElementById("phoneER").value =ContactNamesListER[i].getAttribute("phoneNumber");
            }
        }
    }


}


function saveReference(){

    var clientID=document.getElementById("clientName").value;
    var contactName=document.getElementById("contactName").value;
    var phone=document.getElementById("phone").value;
    var zip=document.getElementById("zip").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'references/saveReference',
        data: ({clientID:clientID,contactName:contactName,phone:phone,zip:zip,userName:userName}),
        success: function(id) {
        }
    });
    sleep(1000);
    populateReferenceGrid();
    refreshReference();
}



function editReference(){

    var clientID=document.getElementById("clientNameER").value;
    var contactName=document.getElementById("contactNameER").value;
    var phone=document.getElementById("phoneER").value;
    var zip=document.getElementById("zipER").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'references/updateReference',
        data: ({clientID:clientID,contactName:contactName,phone:phone,zip:zip,userName:userName,
            rowId:referencesGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    populateReferenceGrid();
}

function deleteReferenceInfo() {
    if(confirm("Do you want to delete this reference Information?")) {
        $.ajax({
            url: 'references/deleteReference',
            data: ({rowId : referencesGrid.getSelectedRowId()}),
            success: function(id) {
                populateReferenceGrid();
            }
        });
    }
}

function refreshReference() {

    document.getElementById("clientName").selectedIndex="0";
    getClientContacts();
    document.getElementById("phone").value ="";
    document.getElementById("zip").value ="";

}


$(function() {
    $('#saveReferenceBtn').click(function() {
        $('#createNewReference').modal('hide');
    });
});

$(function() {
    $('#editReferenceBtn').click(function() {
        $('#editReference').modal('hide');
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



