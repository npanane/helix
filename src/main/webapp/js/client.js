/**
 * client.js
 */
var clientGrid;
var ClientContactGrid;
var noteGrid;
var instructionGrid;
var clientUploadGrid;
var newsGrid;

// workaround
var firstEverSelectedId;

/*
 *
 */
jQuery(document).ready(function() {

    initialState();
});

function initialState(){
    $('#editBtn').attr('disabled', true);
    $('#deleteBtn').attr('disabled', true);
    $('#cancelBtn').attr('disabled', true);
    $('#saveBtn').attr('disabled', true);
    $('#phoneInfoEditBtn').attr('disabled', true);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#AddNewContactInfoBtn').attr('disabled', true);
    $('#ContactInfoEditBtn').attr('disabled', true);
    $('#ContactInfoDeleteBtn').attr('disabled', true);
    $('#AddressInfoEditBtn').attr('disabled', true);
    $('#AddressInfoCancelBtn').attr('disabled', true);
    $('#AddressInfoSaveBtn').attr('disabled', true);
    $('#campaignInfoEditBtn').attr('disabled', true);
    $('#campaignInfoCancelBtn').attr('disabled', true);
    $('#campaignInfoSaveBtn').attr('disabled', true);
    $('#postOfficeInfoEditBtn').attr('disabled', true);
    $('#postOfficeInfoCancelBtn').attr('disabled', true);
    $('#postOfficeInfoSaveBtn').attr('disabled', true);
    $('#mailConInfoEditBtn').attr('disabled', true);
    $('#mailConInfoCancelBtn').attr('disabled', true);
    $('#mailConInfoSaveBtn').attr('disabled', true);
    $('#NOIInfoEditBtn').attr('disabled', true);
    $('#NOIInfoCancelBtn').attr('disabled', true);
    $('#NOIInfoSaveBtn').attr('disabled', true);
    $('#addNewNoteBtn').attr('disabled', true);
    $('#noteEditBtn').attr('disabled', true);
    $('#noteDeleteBtn').attr('disabled', true);
    $('#addNewInstructionBtn').attr('disabled', true);
    $('#instructionEditBtn').attr('disabled', true);
    $('#instructionDeleteBtn').attr('disabled', true);
    $('#uploadFilebtn').attr('disabled', true);
    $('#uploadFileEditBtn').attr('disabled', true);
    $('#uploadFileDeleteBtn').attr('disabled', true);
    disableControls(true);
    disablePIControls(true);
    disableMAControls(true);
    disableCIControls(true);
    disablePOControls(true);
    disableMCIControls(true);
    disableNOIControls(true);
    refresh();
    refreshPhoneInfo();
    refreshAddressInfo();
    refreshCampaignInfo();
    refreshPostOfficeInfo();
    refreshMailConInfo();
    refreshNOIInfo();
    refreshNoteInfo();
    refreshClientContactInfo();
    refreshInstructionInfo();
    populateClientGrid();
    initializeContactGrid();
    initializeNoteGrid();
    initializeNewsGrid();
    initializeInstructionGrid();
    initializeClientUploadGrid();
}

/**
 * client grid
 */
function initializeClientGrid() {
    var window_size = $(window).height();
    document.getElementById('gridbox').style.height = (window_size - 125) + "px";
    clientGrid = new dhtmlXGridObject('gridbox');
    clientGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    clientGrid.setHeader("Name,Jurisdiction,acronym,idClientType,Client Type");
    clientGrid.setInitWidths("290,0,0,0,0");
    clientGrid.setColAlign("left,left,left,left,left");
    clientGrid.setColTypes("ro,ro,ro,ro,ro");
    clientGrid.setColSorting("str,str,str");
    clientGrid.setSkin("dhx_skyblue");
    clientGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    clientGrid.init();
    clientGrid.customGroupFormat=function(name,count){
        return (name == "0") ? "None" : name + " (" + count + ")";
    }
    clientGrid.groupBy(4);
    clientGrid.attachEvent("onRowSelect", doOnRowSelected);
}

function doOnRowSelected(rowId) {
    firstEverSelectedId = rowId; // todo quick fix
    refresh();
    refreshPhoneInfo();
    refreshAddressInfo();
    refreshCampaignInfo();
    refreshPostOfficeInfo();
    refreshMailConInfo();
    refreshNOIInfo();
    refreshNoteInfo();
    refreshClientContactInfo();
    refreshInstructionInfo();
    disableControls(true);
    disablePIControls(true);
    disableMAControls(true);
    disableCIControls(true);
    disablePOControls(true);
    disableMCIControls(true);
    disableNOIControls(true);
    document.getElementById("name").value = clientGrid.cells(rowId, 0).getValue();
    document.getElementById("jurisdiction").value = clientGrid.cells(rowId, 1).getValue();
    document.getElementById("acronym").value = clientGrid.cells(rowId,2).getValue();
    document.getElementById("clientTypes").value = clientGrid.cells(rowId,3).getValue();
    $('#editBtn').attr('disabled', false);
    $('#deleteBtn').attr('disabled', false);
    $('#cancelBtn').attr('disabled', true);
    $('#saveBtn').attr('disabled', true);
    $('#phoneInfoEditBtn').attr('disabled', false);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#AddNewContactInfoBtn').attr('disabled', false);
    $('#ContactInfoEditBtn').attr('disabled', true);
    $('#ContactInfoDeleteBtn').attr('disabled', true);
    $('#AddressInfoEditBtn').attr('disabled', false);
    $('#AddressInfoCancelBtn').attr('disabled', true);
    $('#AddressInfoSaveBtn').attr('disabled', true);
    $('#campaignInfoEditBtn').attr('disabled', false);
    $('#campaignInfoCancelBtn').attr('disabled', true);
    $('#campaignInfoSaveBtn').attr('disabled', true);
    $('#postOfficeInfoEditBtn').attr('disabled', false);
    $('#postOfficeInfoCancelBtn').attr('disabled', true);
    $('#postOfficeInfoSaveBtn').attr('disabled', true);
    $('#mailConInfoEditBtn').attr('disabled', false);
    $('#mailConInfoCancelBtn').attr('disabled', true);
    $('#mailConInfoSaveBtn').attr('disabled', true);
    $('#NOIInfoEditBtn').attr('disabled', false);
    $('#NOIInfoCancelBtn').attr('disabled', true);
    $('#NOIInfoSaveBtn').attr('disabled', true);
    $('#addNewNoteBtn').attr('disabled', false);
    $('#noteEditBtn').attr('disabled', true);
    $('#noteDeleteBtn').attr('disabled', true);
    $('#addNewInstructionBtn').attr('disabled', false);
    $('#instructionEditBtn').attr('disabled', true);
    $('#instructionDeleteBtn').attr('disabled', true);
    $('#uploadFilebtn').attr('disabled', false);
    $('#uploadFileEditBtn').attr('disabled', true);
    $('#uploadFileDeleteBtn').attr('disabled', true);


    getContactsDetails(rowId);
    getAddressDetails(rowId);
    getCampaignDetails(rowId);
    getPostOfficeDetails(rowId);
    getNoticeOfIntentDetails(rowId);
    getMailContractDetails(rowId);
    populateContactGrid(rowId);
    populateNoteGrid(rowId);
    //populateNewsGrid(rowId);
    populateInstructionGrid(rowId);
    populateClientUploadGrid(rowId);
    codeAddress(mapAddress);

}

function populateClientGrid() {
    initializeClientGrid();
    $.ajax({
        url: 'client/load',
        success: function(data) {
            populateClientGridRequestHandler(data);
        }
    });

}

function populateClientGridRequestHandler(data) {
    var clients = data.getElementsByTagName("clients/client");
    if (clients == null || clients.length < 1) {
        clients = data.getElementsByTagName("client");
    }
    for (var i = 0; i < clients.length; i++) {
        clientGrid.addRow(clients[i].getAttribute("clientId"),
            [clients[i].getAttribute("name"),
                clients[i].getAttribute("jurisdiction"),
                clients[i].getAttribute("acronym"),
                clients[i].getAttribute("idClientType"),
                clients[i].getAttribute("clientType")], -1);
    }
}
function getContactsDetails(rowId){

    $.ajax({
        url: 'client/loadClientContacts',
        data: ({ClientId:rowId}),
        success: function(data) {
            displayClientContacts(data);
        }
    });

}

function displayClientContacts(data) {

    var contact = data.getElementsByTagName("details");
    if (contact == null || contact.length < 1) {
        contact = data.getElementsByTagName("details");
    }
    document.getElementById("mainPhone").value=contact[0].getAttribute("main");
    document.getElementById("dispatch").value=contact[0].getAttribute("dispatch");
    document.getElementById("fax").value=contact[0].getAttribute("fax");
    document.getElementById("IdContact").value=contact[0].getAttribute("IdContact");

}

function getAddressDetails(rowId){

    $.ajax({
        url: 'client/loadClientAddress',
        data: ({ClientId:rowId}),
        success: function(data) {
            displayClientAddressDetails(data);
        }
    });

}

var mapAddress;

function displayClientAddressDetails(data) {

    var address = data.getElementsByTagName("details");
    if (address == null || address.length < 1) {
        address = data.getElementsByTagName("details");
    }
    document.getElementById("IdAddress").value=address[0].getAttribute("IdAddress");
    document.getElementById("city").value=address[0].getAttribute("city");
    document.getElementById("zip").value=address[0].getAttribute("zip");
    document.getElementById("address").value=address[0].getAttribute("address1");
    document.getElementById("state").value=address[0].getAttribute("state");
    mapAddress = address[0].getAttribute("address1") + ", " + address[0].getAttribute("city") + ", "
        + address[0].getAttribute("state") + ", " + address[0].getAttribute("zip");
}
function getCampaignDetails(rowId){

    $.ajax({
        url: 'client/loadCampaignDetails',
        data: ({ClientId:rowId}),
        success: function(data) {
            displayCampaignDetails(data);
        }
    });

}

function displayCampaignDetails(data) {

    var campaign = data.getElementsByTagName("details");
    if(campaign.length>0) {
        document.getElementById("IdCampaign").value = campaign[0].getAttribute("IdCampaign");
        document.getElementById("rank").value = campaign[0].getAttribute("rank");
        document.getElementById("firstName").value = campaign[0].getAttribute("firstName");
        document.getElementById("lastName").value = campaign[0].getAttribute("lastName");
        document.getElementById("title").value = campaign[0].getAttribute("title");
        document.getElementById("phone").value = campaign[0].getAttribute("phone");

        if (campaign[0].getAttribute("NEPVoiceMail") == "true") {
            document.getElementById("NEPVoicemail").checked = true;
        }

        document.getElementById("MCaddress").value = campaign[0].getAttribute("address1");
        document.getElementById("MCcity").value = campaign[0].getAttribute("city");
        document.getElementById("MCstate").value = campaign[0].getAttribute("state");
        document.getElementById("MCzip").value = campaign[0].getAttribute("zip");
        document.getElementById("taxID").value = campaign[0].getAttribute("taxID");
        document.getElementById("friendsOf").value = campaign[0].getAttribute("friendOf");
        document.getElementById("season").value = campaign[0].getAttribute("season");
        document.getElementById("clientWebsite").value = campaign[0].getAttribute("clientWebsite");
        document.getElementById("baseForm").value = campaign[0].getAttribute("baseFormNo");
        document.getElementById("donationWebsite").value = campaign[0].getAttribute("donationWebsite");
        document.getElementById("letterType").value = campaign[0].getAttribute("letterType");
        document.getElementById("pictureTitle").value = campaign[0].getAttribute("pictureTitle");
        document.getElementById("client").value = campaign[0].getAttribute("clientPercent");
        document.getElementById("bankAcct").value = campaign[0].getAttribute("bankAcc");
        document.getElementById("location").value = campaign[0].getAttribute("locationNo");
        if (campaign[0].getAttribute("taxDeductible") == "true") {
            document.getElementById("taxDeductible").checked = true;
        }
        document.getElementById("campaignContactId").value = campaign[0].getAttribute("IdContact");
        document.getElementById("campaignAddressId").value = campaign[0].getAttribute("IdAddress");

    }


}

function getPostOfficeDetails(rowId){

    $.ajax({
        url: 'client/loadPostOfficeDetails',
        data: ({ClientId:rowId}),
        success: function(data) {
            displayPostOfficeDetails(data);
        }
    });

}

function displayPostOfficeDetails(data) {

    var postOffice = data.getElementsByTagName("details");
    if(postOffice.length>0) {
        document.getElementById("pickUpMethod").value = postOffice[0].getAttribute("postPickUpId");
        document.getElementById("lastCalled").value = postOffice[0].getAttribute("lastCall");
        document.getElementById("lastPickUp").value = postOffice[0].getAttribute("lastPickup");
        document.getElementById("LastPaidOn").value = postOffice[0].getAttribute("lastPaid");
        document.getElementById("expirationDate").value = postOffice[0].getAttribute("dateExpiration");
        document.getElementById("renewalPeriod").value = postOffice[0].getAttribute("renewalPeriod");

        if (postOffice[0].getAttribute("flHideAlert") == "true") {
            document.getElementById("tempHideAlert").checked = true;
        }

        document.getElementById("boxSize").value = postOffice[0].getAttribute("boxSize");
        document.getElementById("renewalCost").value = postOffice[0].getAttribute("renewalCost");
        document.getElementById("ghost").value = postOffice[0].getAttribute("ghost");
        document.getElementById("notes").value = postOffice[0].getAttribute("notes");
        document.getElementById("postOfficeAddress").value = postOffice[0].getAttribute("address");
        document.getElementById("POcity").value = postOffice[0].getAttribute("city");
        document.getElementById("POstate").value = postOffice[0].getAttribute("POstate");
        document.getElementById("POzip").value = postOffice[0].getAttribute("zip");
        document.getElementById("postOfficePhone").value = postOffice[0].getAttribute("postOfficePhone");
        document.getElementById("contact1").value = postOffice[0].getAttribute("contact1");
        document.getElementById("postOfficeFax").value = postOffice[0].getAttribute("postOfficeFax");
        document.getElementById("contact2").value = postOffice[0].getAttribute("contact2");
        document.getElementById("IdPostOffice").value = postOffice[0].getAttribute("IdPostOffice");
        document.getElementById("postAddressId").value = postOffice[0].getAttribute("postAddressId");
        document.getElementById("postContactId").value = postOffice[0].getAttribute("postContactId");
        document.getElementById("postPickUpId").value = postOffice[0].getAttribute("postPickUpId");

    }
}

function getMailContractDetails(rowId){

    $.ajax({
        url: 'client/loadMailContractDetails',
        data: ({ClientId:rowId}),
        success: function(data) {
            displayMailContractDetails(data);
        }
    });

}

function displayMailContractDetails(data) {

    var MailContract = data.getElementsByTagName("details");
    if (MailContract == null || MailContract.length < 1) {
        MailContract = data.getElementsByTagName("details");
    }
    document.getElementById("commencementDate").value=MailContract[0].getAttribute("commencementDate");
    document.getElementById("terminationDate").value=MailContract[0].getAttribute("terminationDate");
    document.getElementById("terminationBuff").value=MailContract[0].getAttribute("terminationBuff");
    document.getElementById("renewPeriod").value=MailContract[0].getAttribute("renewPeriod");
    document.getElementById("renewType").value=MailContract[0].getAttribute("renewType");
    document.getElementById("cancellationDate").value=MailContract[0].getAttribute("cancellationDate");
    if (MailContract[0].getAttribute("charity") == "true") {
        document.getElementById("charity").checked = true;}
    if (MailContract[0].getAttribute("telemarketing") == "true") {
        document.getElementById("telemarketing").checked = true;}
    if (MailContract[0].getAttribute("hideContractAlerts") == "true") {
        document.getElementById("hideContractAlerts").checked = true;}
    if (MailContract[0].getAttribute("cancelLetterReceived") == "true") {
        document.getElementById("cancellationLetterReceived").checked = true; }

}

function getNoticeOfIntentDetails(rowId){

    $.ajax({
        url: 'client/loadNoticeOfIntentDetails',
        data: ({ClientId:rowId}),
        success: function(data) {
            displayNoticeOfIntentDetails(data);
        }
    });

}

function displayNoticeOfIntentDetails(data) {

    var notice = data.getElementsByTagName("details");
    if (notice == null || notice.length < 1) {
        notice = data.getElementsByTagName("details");
    }
    if (notice.length > 0) {
        document.getElementById("IdNoticeOfIntent").value=notice[0].getAttribute("IdNoticeOfIntent");
        document.getElementById("regNum").value=notice[0].getAttribute("regNum");
        document.getElementById("NOIemail").value=notice[0].getAttribute("email");
        document.getElementById("activity").value=notice[0].getAttribute("activity");
        document.getElementById("beginningDate").value=notice[0].getAttribute("beginningDate");
        if (notice[0].getAttribute("NOItelemarketing") == "true") {
            document.getElementById("NOItelemarketing").checked = true;
        }
        document.getElementById("endingDate").value=notice[0].getAttribute("endingDate");
    }
}

function addNewClient(){

    refresh();
    refreshPhoneInfo();
    refreshAddressInfo();
    refreshCampaignInfo();
    refreshPostOfficeInfo();
    refreshMailConInfo();
    refreshNOIInfo();
    refreshNoteInfo();
    refreshClientContactInfo();
    refreshInstructionInfo();
    disableControls(false);
    disablePIControls(true);
    disableMAControls(true);
    disableCIControls(true);
    disablePOControls(true);
    disableMCIControls(true);
    disableNOIControls(true);
    populateClientGrid();
    initializeContactGrid();
    initializeNoteGrid();
    initializeNewsGrid();
    initializeInstructionGrid();
    initializeClientUploadGrid();
    $('#editBtn').attr('disabled', false);
    $('#cancelBtn').attr('disabled', false);
    $('#saveBtn').attr('disabled', false);
    $('#phoneInfoEditBtn').attr('disabled', true);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#AddNewContactInfoBtn').attr('disabled', true);
    $('#ContactInfoEditBtn').attr('disabled', true);
    $('#ContactInfoDeleteBtn').attr('disabled', true);
    $('#AddressInfoEditBtn').attr('disabled', true);
    $('#AddressInfoCancelBtn').attr('disabled', true);
    $('#AddressInfoSaveBtn').attr('disabled', true);
    $('#campaignInfoEditBtn').attr('disabled', true);
    $('#campaignInfoCancelBtn').attr('disabled', true);
    $('#campaignInfoSaveBtn').attr('disabled', true);
    $('#postOfficeInfoEditBtn').attr('disabled', true);
    $('#postOfficeInfoCancelBtn').attr('disabled', true);
    $('#postOfficeInfoSaveBtn').attr('disabled', true);
    $('#mailConInfoEditBtn').attr('disabled', true);
    $('#mailConInfoCancelBtn').attr('disabled', true);
    $('#mailConInfoSaveBtn').attr('disabled', true);
    $('#NOIInfoEditBtn').attr('disabled', true);
    $('#NOIInfoCancelBtn').attr('disabled', true);
    $('#NOIInfoSaveBtn').attr('disabled', true);
    $('#addNewNoteBtn').attr('disabled', true);
    $('#noteEditBtn').attr('disabled', true);
    $('#noteDeleteBtn').attr('disabled', true);
    $('#addNewInstructionBtn').attr('disabled', true);
    $('#instructionEditBtn').attr('disabled', true);
    $('#instructionDeleteBtn').attr('disabled', true);
    $('#uploadFilebtn').attr('disabled', true);
    $('#uploadFileEditBtn').attr('disabled', true);
    $('#uploadFileDeleteBtn').attr('disabled', true);
}


function deleteClient() {
    if(confirm("Do you want to delete this client?")) {
        $.ajax({
            url: 'client/deleteClient',
            data: ({rowId : clientGrid.getSelectedRowId()}),
            success: function(id) {

                initialState();
            }
        });
    }
}

function saveClient() {
    var name = document.getElementById("name").value;
    var acronym = document.getElementById("acronym").value;
    var jurisdiction = document.getElementById("jurisdiction").value;
    var userName = document.getElementById("userName").value;
    var idClientType = document.getElementById("clientTypes").value;

    $.ajax({
        url: 'client/saveOrUpdateClient',
        data: ({ClientName:name, coClient:acronym, jurisdiction:jurisdiction, userName:userName,
            idClient:clientGrid.getSelectedRowId(), idClientType:idClientType}),
        mimeType:"text/html; charset=UTF-8",
        success: function(result) {
            populateClientGrid();
            var myTimer = window.setTimeout(function() {
                clientGrid.selectRowById(result,false,true,true);
            }, 500);

        }
    });
    $('#editBtn').attr('disabled', true);
    $('#deleteBtn').attr('disabled', true);
    $('#cancelBtn').attr('disabled', true);
    $('#saveBtn').attr('disabled', true);
}


function savePhoneInfo() {
    var mainPhone = document.getElementById("mainPhone").value;
    var fax = document.getElementById("fax").value;
    var dispatch = document.getElementById("dispatch").value;
    var IdContact = (document.getElementById("IdContact").value != null
    && document.getElementById("IdContact").value != "" ) ? document.getElementById("IdContact").value : 0;
    var userName = document.getElementById("userName").value;
    $.ajax({
        url: 'client/saveOrUpdatePhoneNumbers',
        data: ({clientId:clientGrid.getSelectedRowId(), mainPhone:mainPhone, fax:fax, dispatch:dispatch,
            userName:userName, IdContact:IdContact}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(clientGrid.getSelectedRowId());
}

function addNewContactInfo() {

    var CCrank=document.getElementById("CCrank").value;
    var CCfirstName=document.getElementById("CCfirstName").value;
    var CClastName=document.getElementById("CClastName").value;
    var CCtitle=document.getElementById("CCtitle").value;
    var CCAddress=document.getElementById("CCAddress").value;
    var CCCity=document.getElementById("CCCity").value;
    var CCState=document.getElementById("CCState").value;
    var CCZip=document.getElementById("CCZip").value;
    var CCMain=document.getElementById("CCMain").value;
    var CCHome=document.getElementById("CCHome").value;
    var CCMobile=document.getElementById("CCMobile").value;
    var CCEmailCheck=document.getElementById("CCEmailCheck").checked;
    var CCOther=document.getElementById("CCOther").value;
    var CCFaxCheck=document.getElementById("CCFaxCheck").checked;
    var CCFax=document.getElementById("CCFax").value;
    var CCMailCheck=document.getElementById("CCMailCheck").checked;
    var CCEmail=document.getElementById("CCEmail").value;
    var CCAltEmail=document.getElementById("CCAltEmail").value;
    var CCNotes=document.getElementById("CCNotes").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'client/saveContact',
        data: ({rank:CCrank,firstName:CCfirstName,lastName:CClastName,title:CCtitle,CCAddress:CCAddress,
            CCCity:CCCity,CCState:CCState,CCZip:CCZip,CCMain:CCMain,CCHome:CCHome,
            CCMobile:CCMobile,CCEmailCheck:CCEmailCheck,CCOther:CCOther,CCFaxCheck:CCFaxCheck,CCFax:CCFax,
            CCMailCheck:CCMailCheck,CCEmail:CCEmail,CCAltEmail:CCAltEmail,CCNotes:CCNotes,userName:userName,
            rowID:clientGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    populateContactGrid(clientGrid.getSelectedRowId());
    //doOnRowSelected(clientGrid.getSelectedRowId());
}

function saveContactInfo() {
    var CCrank=document.getElementById("editCCrank").value;
    var CCfirstName=document.getElementById("editCCfirstName").value;
    var CClastName=document.getElementById("editCClastName").value;
    var CCtitle=document.getElementById("editCCtitle").value;
    var CCAddress=document.getElementById("editCCAddress").value;
    var CCCity=document.getElementById("editCCCity").value;
    var CCState=document.getElementById("editCCState").value;
    var CCZip=document.getElementById("editCCZip").value;
    var CCMain=document.getElementById("editCCMain").value;
    var CCHome=document.getElementById("editCCHome").value;
    var CCMobile=document.getElementById("editCCMobile").value;
    var CCEmailCheck=document.getElementById("editCCEmailCheck").checked;
    var CCOther=document.getElementById("editCCOther").value;
    var CCFaxCheck=document.getElementById("editCCFaxCheck").checked;
    var CCFax=document.getElementById("editCCFax").value;
    var CCMailCheck=document.getElementById("editCCMailCheck").checked;
    var CCEmail=document.getElementById("editCCEmail").value;
    var CCAltEmail=document.getElementById("editCCAltEmail").value;
    var CCNotes=document.getElementById("editCCNotes").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'client/saveOrUpdateContact',
        data: ({rank:CCrank,firstName:CCfirstName,lastName:CClastName,title:CCtitle,CCAddress:CCAddress,
            CCCity:CCCity,CCState:CCState,CCZip:CCZip,CCMain:CCMain,CCHome:CCHome,
            CCMobile:CCMobile,CCEmailCheck:CCEmailCheck,CCOther:CCOther,CCFaxCheck:CCFaxCheck,CCFax:CCFax,
            CCMailCheck:CCMailCheck,CCEmail:CCEmail,CCAltEmail:CCAltEmail,CCNotes:CCNotes,userName:userName,
            rowID:clientGrid.getSelectedRowId(),clientContactId:ClientContactGrid.getSelectedRowId()}),
        success: function(id) {
            populateContactGrid(null);
        }
    });
}

function deleteContactInfo() {
    if(confirm("Do you want to delete this contact details?")) {
        $.ajax({
            url: 'client/deleteContact',
            data: ({rowId :ClientContactGrid.getSelectedRowId()}),
            success: function(id) {
                ClientContactGrid.deleteRow(ClientContactGrid.getSelectedRowId());
            }
        });

    }
}

function saveClientAddressInfo() {
    var address=document.getElementById("address").value;
    var city=document.getElementById("city").value;
    var state=document.getElementById("state").value;
    var zip=document.getElementById("zip").value;
    var IdAddress=document.getElementById("IdAddress").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'client/updateAddressInfo',
        data: ({address1:address,city:city,state:state,zip:zip,IdAddress:IdAddress,userName:userName,
            clientId:clientGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(clientGrid.getSelectedRowId());
}

function saveClientCampaignInfo() {
    var clientId = clientGrid.getSelectedRowId();
    var IdCampaign = (document.getElementById("IdCampaign").value != "") ? document.getElementById("IdCampaign").value
        : 0;
    var rank = document.getElementById("rank").value;
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var title = document.getElementById("title").value;
    var phone = document.getElementById("phone").value;
    var NEPVoicemail = document.getElementById("NEPVoicemail").checked;
    var MCaddress = document.getElementById("MCaddress").value;
    var MCcity = document.getElementById("MCcity").value;
    var MCstate = document.getElementById("MCstate").value;
    var MCzip = document.getElementById("MCzip").value;
    var taxID = document.getElementById("taxID").value;
    var friendsOf = document.getElementById("friendsOf").value;
    var season = document.getElementById("season").value;
    var clientWebsite = document.getElementById("clientWebsite").value;
    var baseForm = document.getElementById("baseForm").value;
    var donationWebsite = document.getElementById("donationWebsite").value;
    var letterType = document.getElementById("letterType").value;
    var pictureTitle = document.getElementById("pictureTitle").value;
    var client = document.getElementById("client").value;
    var bankAcct = document.getElementById("bankAcct").value;
    var location = document.getElementById("location").value;
    var taxDeductible = document.getElementById("taxDeductible").checked;
    var campaignContactId = (document.getElementById("campaignContactId").value == null
    || document.getElementById("campaignContactId").value == "")
        ? 0 : document.getElementById("campaignContactId").value;
    var campaignAddressId = (document.getElementById("campaignAddressId").value == null
    || document.getElementById("campaignAddressId").value == "")
        ? 0 : document.getElementById("campaignAddressId").value ;
    var userName = document.getElementById("userName").value;

    $.ajax({
        url: 'client/updateCampaignInfo',
        data: ({clientId:clientId, IdCampaign:IdCampaign, rank:rank, firstName:firstName, lastName:lastName, title:title,
            phone:phone, MCaddress:MCaddress, MCcity:MCcity, MCstate:MCstate, MCzip:MCzip,
            taxID:taxID, friendsOf:friendsOf, season:season, clientWebsite:clientWebsite, baseForm:baseForm,
            donationWebsite:donationWebsite, letterType:letterType, pictureTitle:pictureTitle, client:client,
            bankAcct:bankAcct, location:location, taxDeductible:taxDeductible, NEPVoicemail:NEPVoicemail,
            campaignContactId:campaignContactId, campaignAddressId:campaignAddressId, userName:userName}),
        success: function(id) {
            successNotificationRequestHandler('span.master-ampaign-err', "Saved.", id);
        },
        error: function(id) {
            errorNotificationRequestHandler('span.master-ampaign-err', id);
        }
    });

    sleep(1000);
    doOnRowSelected(clientGrid.getSelectedRowId());
}

function successNotificationRequestHandler(id, txt, data) {
    $(id).css({ "color": 'green'});
    $(id).text(txt);
    setTimeout(function() {
        $(id).html(" ").animate({ opacity: 1, top: "-10px" }, 'slow');
    }, 5000);
}

function errorNotificationRequestHandler(id, data) {
    var err = data.status + ": " + $.parseXML(data.responseText).getElementsByTagName("error")[0].getAttribute("message");
    $(id).css({ "color": 'red'});
    $(id).text(err);
    setTimeout(function() {
        $(id).html(" ").animate({ opacity: 1, top: "-10px" }, 'slow');
    }, 5000);
}


function savePostOfficeInfo() {
    var lastCalled=document.getElementById("lastCalled").value;
    var lastPickUp=document.getElementById("lastPickUp").value;
    var lastPaidOn=document.getElementById("LastPaidOn").value;
    var expirationDate=document.getElementById("expirationDate").value;
    var renewalPeriod=document.getElementById("renewalPeriod").value;
    var boxSize=document.getElementById("boxSize").value;
    var renewalCost=document.getElementById("renewalCost").value;
    var ghost=document.getElementById("ghost").value;
    var notes=document.getElementById("notes").value;
    var tempHideAlert=document.getElementById("tempHideAlert").checked;
    var postOfficeAddress=document.getElementById("postOfficeAddress").value;
    var POcity=document.getElementById("POcity").value;
    var POstate=document.getElementById("POstate").value;
    var POzip=document.getElementById("POzip").value;
    var postOfficePhone=document.getElementById("postOfficePhone").value;
    var contact1=document.getElementById("contact1").value;
    var postOfficeFax=document.getElementById("postOfficeFax").value;
    var contact2=document.getElementById("contact2").value;
    var IdPostOffice=document.getElementById("IdPostOffice").value;
    var postAddressId=document.getElementById("postAddressId").value;
    var postContactId=document.getElementById("postContactId").value;
    var postPickUpId=document.getElementById("pickUpMethod").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'client/updatePostOfficeInfo',
        data: ({lastCalled:lastCalled,lastPickUp:lastPickUp,lastPaidOn:lastPaidOn,
            expirationDate:expirationDate,renewalPeriod:renewalPeriod,
            boxSize:boxSize,renewalCost:renewalCost,ghost:ghost,notes:notes,tempHideAlert:tempHideAlert,
            postOfficeAddress:postOfficeAddress,
            POcity:POcity,POstate:POstate,POzip:POzip,postOfficePhone:postOfficePhone,
            contact1:contact1,postOfficeFax:postOfficeFax,contact2:contact2,userName:userName,
            postAddressId:postAddressId,postContactId:postContactId,postPickUpId:postPickUpId,IdPostOffice:IdPostOffice,
            clientId:clientGrid.getSelectedRowId()}),
        success: function(id) {
            successNotificationRequestHandler('span.post-office-client-err', " Saved.", id);
        }
    });
}

function saveMailConInfo() {
    var commencementDate=document.getElementById("commencementDate").value;
    var terminationDate=document.getElementById("terminationDate").value;
    var terminationBuff=document.getElementById("terminationBuff").value;
    var charity=document.getElementById("charity").checked;
    var renewPeriod=document.getElementById("renewPeriod").value;
    var telemarketing=document.getElementById("telemarketing").checked;
    var renewType = (document.getElementById("renewType").value != null && document.getElementById("renewType").value != "")
        ? document.getElementById("renewType").value : "0";
    var hideContractAlerts=document.getElementById("hideContractAlerts").checked;
    var cancelLetterReceived=document.getElementById("cancellationLetterReceived").checked;
    var cancellationDate = (document.getElementById("cancellationDate").value == "") ?
        null : document.getElementById("cancellationDate").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'client/updateMailContractInfo',
        data: ({commencementDate:commencementDate,terminationDate:terminationDate,terminationBuff:terminationBuff,
            charity:charity,renewPeriod:renewPeriod,telemarketing:telemarketing,renewType:renewType,
            hideContractAlerts:hideContractAlerts,cancelLetterReceived:cancelLetterReceived,userName:userName,
            cancellationDate:cancellationDate,clientId:clientGrid.getSelectedRowId()}),
        success: function(data) {
            successNotificationRequestHandler('span.main-contact-client-err', " Saved.", data);
        }
    });
}


function saveNOIInfo() {
    var regNum=document.getElementById("regNum").value;
    var NOIemial=document.getElementById("NOIemail").value;
    var activity=document.getElementById("activity").value;
    var beginningDate=document.getElementById("beginningDate").value;
    var NOItelemarketing=document.getElementById("NOItelemarketing").checked;
    var endingDate=document.getElementById("endingDate").value;
    var IdNoticeOfIntent=document.getElementById("IdNoticeOfIntent").value;
    var userName=document.getElementById("userName").value;
    $.ajax({
        url: 'client/updateNOIInfo',
        data: ({regNum:regNum,NOIemial:NOIemial,activity:activity,beginningDate:beginningDate,
            NOItelemarketing:NOItelemarketing,endingDate:endingDate,userName:userName,
            IdNoticeOfIntent:IdNoticeOfIntent,clientId:clientGrid.getSelectedRowId()}),
        success: function(data) {
            successNotificationRequestHandler('span.notice-of-intent-client-msg', " Saved.", data);
        }
    });
}


function addNewNote() {
    var dateCreated=document.getElementById("dateCreated").value;
    var alarmDate=document.getElementById("alarmDate").value;
    var note=document.getElementById("note").value;
    var alarm=document.getElementById("alarm").checked;
    var userName=document.getElementById("userName").value;
    $.ajax({
        url: 'client/saveOrUpdateNote',
        data: ({dateCreated:dateCreated,alarmDate:alarmDate,note:note,alarm:alarm,userName:userName,
            clientId:clientGrid.getSelectedRowId(),noteId:0}),
        success: function(id) {
            populateNoteGrid(clientGrid.getSelectedRowId());
        }
    });
}

function saveNote() {
    var dateCreated=document.getElementById("editDateCreated").value;
    var alarmDate=document.getElementById("editAlarmDate").value;
    var note=document.getElementById("editNote").value;
    var alarm=document.getElementById("editAlarm").checked;
    var userName=document.getElementById("editUserName").value;
    $.ajax({
        url: 'client/saveOrUpdateNote',
        data: ({dateCreated:dateCreated,alarmDate:alarmDate,note:note,alarm:alarm,userName:userName,
            clientId:clientGrid.getSelectedRowId(),noteId:noteGrid.getSelectedRowId()}),
        success: function(id) {
            populateNoteGrid(clientGrid.getSelectedRowId());
        }
    });
}

function deleteNote() {
    if(confirm("Do you want to delete this note ?")) {
        $.ajax({
            url: 'client/deleteNote',
            data: ({rowId :noteGrid.getSelectedRowId()}),
            success: function(id) {
            }
        });
        doOnRowSelected(clientGrid.getSelectedRowId());
    }
}

function addNewInstruction() {
    var instruction=document.getElementById("instruction").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'client/saveOrUpdateInstruction',
        data: ({instruction:instruction,userName:userName,clientId:clientGrid.getSelectedRowId(),instructionId:0}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(clientGrid.getSelectedRowId());
}

function saveInstruction() {
    var instruction=document.getElementById("editInstruction").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'client/saveOrUpdateInstruction',
        data: ({instruction:instruction,userName:userName,clientId:clientGrid.getSelectedRowId(),
            instructionId:instructionGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(clientGrid.getSelectedRowId());
}

function deleteInstruction() {
    if(confirm("Do you want to delete this instruction ?")) {
        $.ajax({
            url: 'client/deleteInstruction',
            data: ({rowId :instructionGrid.getSelectedRowId()}),
            success: function(id) {
            }
        });
        doOnRowSelected(clientGrid.getSelectedRowId());
    }
}
function deleteUploadFile() {
    if(confirm("Do you want to delete this upload file ?")) {
        $.ajax({
            url: 'client/deleteUploadFile',
            data: ({rowId :clientUploadGrid.getSelectedRowId()}),
            success: function(id) {
            }
        });
        doOnRowSelected(clientGrid.getSelectedRowId());
    }
}



function edit() {
    disableControls(false);
    //document.getElementById("clientCode").disabled = true; // Override
    //document.getElementById("name").focus();
    $('#cancelBtn').attr('disabled', false);
    $('#saveBtn').attr('disabled', false);

}

function editPhoneInfo() {
    disablePIControls(false);
    $('#phoneInfoCancelBtn').attr('disabled', false);
    $('#phoneInfoSaveBtn').attr('disabled', false);
}

function editAddressInfo() {
    disableMAControls(false);
    $('#AddressInfoCancelBtn').attr('disabled', false);
    $('#AddressInfoSaveBtn').attr('disabled', false);
}
function editCampaignInfo() {
    disableCIControls(false);
    $('#campaignInfoCancelBtn').attr('disabled', false);
    $('#campaignInfoSaveBtn').attr('disabled', false);
}

function editPostOfficeInfo() {
    disablePOControls(false);
    $('#postOfficeInfoCancelBtn').attr('disabled', false);
    $('#postOfficeInfoSaveBtn').attr('disabled', false);
}

function editMailConInfo() {
    disableMCIControls(false);
    $('#mailConInfoCancelBtn').attr('disabled', false);
    $('#mailConInfoSaveBtn').attr('disabled', false);
}

function editNOIInfo() {
    disableNOIControls(false);
    $('#NOIInfoCancelBtn').attr('disabled', false);
    $('#NOIInfoSaveBtn').attr('disabled', false);
}

function refresh() {

    document.getElementById("name").value ="";
    document.getElementById("acronym").value ="";
    document.getElementById("jurisdiction").value ="";
}
function refreshPhoneInfo() {
    document.getElementById("mainPhone").value ="";
    document.getElementById("fax").value ="";
    document.getElementById("dispatch").value ="";
}
function refreshAddressInfo() {
    document.getElementById("address").value ="";
    document.getElementById("city").value ="";
    document.getElementById("state").value ="";
    document.getElementById("zip").value ="";

}

function refreshCampaignInfo() {
    document.getElementById("rank").value ="";
    document.getElementById("firstName").value ="";
    document.getElementById("lastName").value ="";
    document.getElementById("title").value ="";
    document.getElementById("phone").value ="";
    document.getElementById("NEPVoicemail").checked =false;
    document.getElementById("MCaddress").value ="";
    document.getElementById("MCcity").value ="";
    document.getElementById("MCstate").value ="";
    document.getElementById("MCzip").value ="";
    document.getElementById("taxID").value ="";
    document.getElementById("friendsOf").value ="";
    document.getElementById("season").selectedIndex="0";
    document.getElementById("clientWebsite").value ="";
    document.getElementById("baseForm").value ="";
    document.getElementById("donationWebsite").value ="";
    document.getElementById("letterType").value ="";
    document.getElementById("pictureTitle").value ="";
    document.getElementById("client").value ="";
    document.getElementById("bankAcct").value ="";
    document.getElementById("location").value ="";
    document.getElementById("taxDeductible").checked =false;
    document.getElementById("IdCampaign").value ="";
    document.getElementById("campaignAddressId").value="";
    document.getElementById("campaignContactId").value="";

}

function refreshPostOfficeInfo() {
    document.getElementById("pickUpMethod").selectedIndex="0";
    document.getElementById("lastCalled").value ="";
    document.getElementById("lastPickUp").value ="";
    document.getElementById("LastPaidOn").value ="";
    document.getElementById("expirationDate").value ="";
    document.getElementById("renewalPeriod").selectedIndex="0";
    document.getElementById("boxSize").value ="";
    document.getElementById("renewalCost").value ="";
    document.getElementById("ghost").value ="";
    document.getElementById("notes").value ="";
    document.getElementById("tempHideAlert").checked =false;
    document.getElementById("postOfficeAddress").value ="";
    document.getElementById("POcity").value ="";
    document.getElementById("POstate").value ="";
    document.getElementById("POzip").value ="";
    document.getElementById("postOfficePhone").value ="";
    document.getElementById("contact1").value ="";
    document.getElementById("postOfficeFax").value ="";
    document.getElementById("contact2").value ="";
    document.getElementById("IdPostOffice").value ="";
    document.getElementById("postAddressId").value ="";
    document.getElementById("postContactId").value ="";
    document.getElementById("postPickUpId").value ="";
}


function refreshMailConInfo() {
    document.getElementById("commencementDate").value ="";
    document.getElementById("terminationDate").value ="";
    document.getElementById("terminationBuff").value ="";
    document.getElementById("charity").checked=false;
    document.getElementById("renewPeriod").selectedIndex="0";
    document.getElementById("telemarketing").checked=false;
    document.getElementById("renewType").selectedIndex="0";
    document.getElementById("hideContractAlerts").checked=false;
    document.getElementById("cancellationLetterReceived").checked=false;
    document.getElementById("cancellationDate").value ="";
}

function refreshNOIInfo() {
    document.getElementById("regNum").value ="";
    document.getElementById("NOIemail").value ="";
    document.getElementById("activity").value ="";
    document.getElementById("beginningDate").value ="";
    document.getElementById("NOItelemarketing").checked=false;
    document.getElementById("endingDate").value ="";
}


function refreshNoteInfo() {
    document.getElementById("note").value ="";
    document.getElementById("dateCreated").value ="";
    document.getElementById("alarm").checked=false;
    document.getElementById("alarmDate").value ="";
}


function refreshInstructionInfo() {
    document.getElementById("instruction").value ="";

}

function refreshClientContactInfo() {
    document.getElementById("CCrank").value ="";
    document.getElementById("CCfirstName").value ="";
    document.getElementById("CClastName").value ="";
    document.getElementById("CCtitle").value ="";
    document.getElementById("CCAddress").value ="";
    document.getElementById("CCCity").value ="";
    document.getElementById("CCState").value ="";
    document.getElementById("CCZip").value ="";
    document.getElementById("CCMain").value ="";
    document.getElementById("CCHome").value ="";
    document.getElementById("CCMobile").value ="";
    document.getElementById("CCOther").value ="";
    document.getElementById("CCFax").value ="";
    document.getElementById("CCEmail").value ="";
    document.getElementById("CCAltEmail").value ="";
    document.getElementById("CCNotes").value ="";

    document.getElementById("CCEmailCheck").checked=false;
    document.getElementById("CCFaxCheck").checked=false;
    document.getElementById("CCMailCheck").checked=false;


}

function clearControls() {
    $("#name").val("");
    $("#acronym").val("");
    $("#jurisdiction").val("");
    $("#mainPhone").val("");
    $("#workPhone").val("");
    $("#mobilePhone").val("");
}

function disableControls(value) {
    $("#name").attr("disabled", value);
    $("#acronym").attr("disabled", value);
    $("#jurisdiction").attr("disabled", value);
}
function disablePIControls(value) {

    $("#mainPhone").attr("disabled", value);
    $("#fax").attr("disabled", value);
    $("#dispatch").attr("disabled", value);
}

function disableMAControls(value) {

    $("#address").attr("disabled", value);
    $("#city").attr("disabled", value);
    $("#state").attr("disabled", value);
    $("#zip").attr("disabled", value);
}
function disableCIControls(value) {

    $("#rank").attr("disabled", value);
    $("#firstName").attr("disabled", value);
    $("#lastName").attr("disabled", value);
    $("#title").attr("disabled", value);
    $("#phone").attr("disabled", value);
    $("#NEPVoicemail").attr("disabled", value);
    $("#MCaddress").attr("disabled", value);
    $("#MCcity").attr("disabled", value);
    $("#MCstate").attr("disabled", value);
    $("#MCzip").attr("disabled", value);
    $("#taxID").attr("disabled", value);
    $("#friendsOf").attr("disabled", value);
    $("#season").attr("disabled", value);
    $("#clientWebsite").attr("disabled", value);
    $("#baseForm").attr("disabled", value);
    $("#donationWebsite").attr("disabled", value);
    $("#letterType").attr("disabled", value);
    $("#pictureTitle").attr("disabled", value);
    $("#client").attr("disabled", value);
    $("#bankAcct").attr("disabled", value);
    $("#location").attr("disabled", value);
    $("#taxDeductible").attr("disabled", value);

}

function disablePOControls(value) {

    $("#pickUpMethod").attr("disabled", value);
    $("#lastCalled").attr("disabled", value);
    $("#lastPickUp").attr("disabled", value);
    $("#LastPaidOn").attr("disabled", value);
    $("#expirationDate").attr("disabled", value);
    $("#renewalPeriod").attr("disabled", value);
    $("#boxSize").attr("disabled", value);
    $("#renewalCost").attr("disabled", value);
    $("#ghost").attr("disabled", value);
    $("#notes").attr("disabled", value);
    $("#tempHideAlert").attr("disabled", value);
    $("#postOfficeAddress").attr("disabled", value);
    $("#POcity").attr("disabled", value);
    $("#POstate").attr("disabled", value);
    $("#POzip").attr("disabled", value);
    $("#postOfficePhone").attr("disabled", value);
    $("#contact1").attr("disabled", value);
    $("#postOfficeFax").attr("disabled", value);
    $("#contact2").attr("disabled", value);


}

function disableMCIControls(value) {

    $("#commencementDate").attr("disabled", value);
    $("#terminationDate").attr("disabled", value);
    $("#terminationBuff").attr("disabled", value);
    $("#charity").attr("disabled", value);
    $("#renewPeriod").attr("disabled", value);
    $("#telemarketing").attr("disabled", value);
    $("#renewType").attr("disabled", value);
    $("#hideContractAlerts").attr("disabled", value);
    $("#cancellationLetterReceived").attr("disabled", value);
    $("#cancellationDate").attr("disabled", value);
}

function disableNOIControls(value) {
    $("#IdNoticeOfIntent").attr("disabled", value);
    $("#regNum").attr("disabled", value);
    $("#NOIemail").attr("disabled", value);
    $("#activity").attr("disabled", value);
    $("#beginningDate").attr("disabled", value);
    $("#NOItelemarketing").attr("disabled", value);
    $("#endingDate").attr("disabled", value);

}

/**
 * Client Contact grid
 */
function initializeContactGrid() {
    ClientContactGrid = new dhtmlXGridObject('contactGridbox');
    ClientContactGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    ClientContactGrid.setHeader("isPast,Name,Title,Phone,Home,Mobile,Other,Fax,Email");
    ClientContactGrid.setInitWidths("0,165,100,80,80,80,80,80,*");
    ClientContactGrid.setColAlign("left,left,left,left,left,left,left,left,left");
    ClientContactGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro");
    ClientContactGrid.setColSorting("str,str,str,str,str,str,str,str");
    ClientContactGrid.setSkin("dhx_skyblue");
    ClientContactGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;"
        + "font-size: 13px;background-color: #3498db; font-family: sans-serif;"
        + "-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    ClientContactGrid.setRowTextStyle("font-size: 8px");
    ClientContactGrid.enableAutoHeight(true);
    ClientContactGrid.init();
    ClientContactGrid.groupBy(0);
    ClientContactGrid.attachEvent("onRowSelect", SelectedContactsGrid);

}

function populateContactGrid(rowId) {
    if (rowId == null || rowId == "") {
        rowId = clientGrid.getSelectedRowId();
    }
    $.ajax({
        url: 'client/loadContacts',
        data: "rowId=" +rowId,
        success: function(data) {
            populateContactGridRequestHandler(data);
        }
    });
}

function populateContactGridRequestHandler(data) {
    if (ClientContactGrid != null) {
        ClientContactGrid.clearAll();
        ClientContactGrid.groupBy(0);
    }
    var contacts = data.getElementsByTagName("clientContacts/contact");
    if (contacts == null || contacts.length < 1) {
        contacts = data.getElementsByTagName("contact");
    }
    for (var i = 0; i < contacts.length; i++) {
        ClientContactGrid.addRow(contacts[i].getAttribute("clientContactId"),
            [contacts[i].getAttribute("isPast"),
                contacts[i].getAttribute("name"),
                contacts[i].getAttribute("title"),
                contacts[i].getAttribute("phone"),
                contacts[i].getAttribute("home"),
                contacts[i].getAttribute("mobile"),
                contacts[i].getAttribute("other"),
                contacts[i].getAttribute("fax"),
                contacts[i].getAttribute("email")], -1);
    }
}
function SelectedContactsGrid(rowId) {
    $('#ContactInfoEditBtn').attr('disabled', false);
    $('#ContactInfoDeleteBtn').attr('disabled', false);

    getSelectedClientContact(rowId);


}

function getSelectedClientContact(rowId) {

    $.ajax({
        url: 'client/loadSelectedClientContact',
        data: "rowId=" +rowId,
        success: function(data) {
            populateSCContactGridRequestHandler(data);
        }
    });
}

function populateSCContactGridRequestHandler(data) {
    var contact =  data.getElementsByTagName("contact");

    document.getElementById("editCCrank").value = contact[0].getAttribute("rank");
    document.getElementById("editCCfirstName").value = contact[0].getAttribute("firstName");
    document.getElementById("editCClastName").value = contact[0].getAttribute("lastName");
    document.getElementById("editCCtitle").value = contact[0].getAttribute("title");
    document.getElementById("editCCAddress").value = contact[0].getAttribute("address");
    document.getElementById("editCCCity").value = contact[0].getAttribute("city");
    document.getElementById("editCCState").value = contact[0].getAttribute("state");
    document.getElementById("editCCZip").value = contact[0].getAttribute("zip");
    document.getElementById("editCCMain").value = contact[0].getAttribute("main");
    document.getElementById("editCCHome").value = contact[0].getAttribute("home");
    document.getElementById("editCCMobile").value = contact[0].getAttribute("mobile");
    document.getElementById("editCCOther").value = contact[0].getAttribute("other");
    document.getElementById("editCCFax").value = contact[0].getAttribute("fax");
    document.getElementById("editCCEmail").value = contact[0].getAttribute("email");
    document.getElementById("editCCAltEmail").value = contact[0].getAttribute("alertEmail");
    document.getElementById("editCCNotes").value = contact[0].getAttribute("note");

    if (contact[0].getAttribute("EmailCheck") == "true") {
        document.getElementById("editCCEmailCheck").checked = true;}
    if (contact[0].getAttribute("FaxCheck") == "true") {
        document.getElementById("editCCFaxCheck").checked = true;}
    if (contact[0].getAttribute("MailCheck") == "true") {
        document.getElementById("editCCMailCheck").checked = true;}

}


/**
 * note grid
 */
function initializeNoteGrid() {
    noteGrid = new dhtmlXGridObject('noteGridbox');
    noteGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    noteGrid.setHeader("Date, By, Note");
    noteGrid.setInitWidths("165,200,*");
    noteGrid.setColAlign("left,left,left");
    noteGrid.setColTypes("ro,ro,ro");
    noteGrid.setColSorting("str,str,str");
    noteGrid.setSkin("dhx_skyblue");
    noteGrid.enableAutoHeight(true);
    noteGrid.init();
    noteGrid.attachEvent("onRowSelect",SelectedNoteGrid);

}

function populateNoteGrid(rowId) {
    $.ajax({
        url: 'client/loadNotes',
        data: "rowId=" +rowId,
        success: function(data) {
            populateNoteGridRequestHandler(data);
        }
    });
}

function populateNoteGridRequestHandler(data) {
    if (noteGrid != null) {
        noteGrid.clearAll();
    }
    var notes = data.getElementsByTagName("notes/note");
    if (notes == null || notes.length < 1) {
        notes = data.getElementsByTagName("note");
    }
    for (var i = 0; i < notes.length; i++) {
        noteGrid.addRow(notes[i].getAttribute("noteId"),
            [notes[i].getAttribute("date"),
                notes[i].getAttribute("by"),
                notes[i].getAttribute("note")], -1);
    }
}

function SelectedNoteGrid(rowId,columnIndex) {
    $('#noteEditBtn').attr('disabled', false);
    $('#noteDeleteBtn').attr('disabled', false);
    document.getElementById("editAlarmDate").value = noteGrid.cells(rowId, 0).getValue();
    document.getElementById("editNote").value = noteGrid.cells(rowId, 2).getValue();
    /*
     if (noteGrid.cells(rowId, 3).getValue() == "true") {
     document.getElementById("editAlarm").checked = true;
     }
     document.getElementById("editDateCreated").value = noteGrid.cells(rowId, 4).getValue();
     */
}

/*
 * News Grid
 */
/**
 * note grid
 */
function initializeNewsGrid() {
    newsGrid = new dhtmlXGridObject('newsGridbox');
    newsGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    newsGrid.setHeader("Top Stories - Google News");
    newsGrid.setInitWidths("*");
    newsGrid.setColAlign("left");
    newsGrid.setColTypes("ro");
    newsGrid.setColSorting("str");
    newsGrid.setSkin("dhx_skyblue");
    newsGrid.enableMultiline(true);
    newsGrid.enableAutoHeight(true);
    newsGrid.init();
}

function populateNewsGrid(rowId) {
    $.ajax({
        url: 'client/loadNews',
        data: "rowId=" +rowId,
        success: function(data) {
            populateNewsGridRequestHandler(data);
        }
    });
}

function populateNewsGridRequestHandler(data) {
    if (newsGrid != null) {
        newsGrid.clearAll();
    }
    var news = data.getElementsByTagName("news");
    if (news == null || news.length < 1) {
        news = data.getElementsByTagName("news");
    }
    for (var i = 0; i < news.length; i++) {
        newsGrid.addRow(news[i].getAttribute("newsId"),
            ["<i class='fa fa-envelope'></i> " + news[i].getAttribute("newsItem")
            + "<a href='" + news[i].getAttribute("url")
            + "' target='_blank'>more</a>"], -1);
    }
}
/**
 * Instruction grid
 */
function initializeInstructionGrid() {
    instructionGrid = new dhtmlXGridObject('instructionGridbox');
    instructionGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    instructionGrid.setHeader("Instruction");
    instructionGrid.setInitWidths("*");
    instructionGrid.setColAlign("left");
    instructionGrid.setColTypes("ro");
    instructionGrid.setColSorting("str");
    instructionGrid.setSkin("dhx_skyblue");
    instructionGrid.enableAutoHeight(true);
    instructionGrid.init();
    instructionGrid.attachEvent("onRowSelect",SelectedInstructionGrid);

}

function populateInstructionGrid(rowId) {
    $.ajax({
        url: 'client/loadInstruction',
        data: "rowId=" +rowId,
        success: function(data) {
            populateInstructionGridRequestHandler(data);
        }
    });
}

function populateInstructionGridRequestHandler(data) {
    if (instructionGrid != null) {
        instructionGrid.clearAll();
    }
    var instructions = data.getElementsByTagName("instructions/instruction");
    if (instructions == null || instructions.length < 1) {
        instructions = data.getElementsByTagName("instruction");
    }
    for (var i = 0; i < instructions.length; i++) {
        instructionGrid.addRow(instructions[i].getAttribute("instructionId"),
            [instructions[i].getAttribute("instruction")], -1);
    }
}

function SelectedInstructionGrid(rowId,columnIndex) {
    $('#instructionEditBtn').attr('disabled', false);
    $('#instructionDeleteBtn').attr('disabled', false);
    document.getElementById("editInstruction").value = instructionGrid.cells(rowId, 0).getValue();
}


/**
 * Client Upload grid
 */
function initializeClientUploadGrid() {
    clientUploadGrid = new dhtmlXGridObject('clientUploadGridbox');
    clientUploadGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    clientUploadGrid.setHeader("Date,By,File Name");
    clientUploadGrid.setInitWidths("100,100,*");
    clientUploadGrid.setColAlign("left,left,left");
    clientUploadGrid.setColTypes("ro,ro,ro");
    clientUploadGrid.setColSorting("str,str,str");
    clientUploadGrid.setSkin("dhx_skyblue");
    clientUploadGrid.init();
    clientUploadGrid.attachEvent("onRowSelect", SelectedClientUploadGrid);

}

function populateClientUploadGrid(rowId) {
    initializeClientUploadGrid();
    $.ajax({
        url: 'client/loadUploadFiles',
        data: "rowId=" +rowId,
        success: function(data) {
            populateClientUploadGridRequestHandler(data);
        }
    });
}

function populateClientUploadGridRequestHandler(data) {
    var items = data.getElementsByTagName("uploadItems/uploadItem");
    if (items == null || items.length < 1) {
        items = data.getElementsByTagName("uploadItem");
    }
    for (var i = 0; i < items.length; i++) {
        clientUploadGrid.addRow(items[i].getAttribute("idClientupload"),
            [items[i].getAttribute("date"),
                items[i].getAttribute("by"),
                items[i].getAttribute("fileName")], -1);
    }
}

function SelectedClientUploadGrid(rowId,columnIndex) {

    $('#uploadFileEditBtn').attr('disabled', false);
    $('#uploadFileDeleteBtn').attr('disabled', false);

}

function uploadFile(){
    var file = new FormData(document.getElementById('uploadForm'));

    $.ajax({
        url: 'client/uploadFile',
        data:file,
        dataType: 'text',
        processData: false,
        contentType: false,
        type: 'POST',
        success: function(data){
            saveUpLoadDetails(data);
        },
        error : function(e) {
            alert('Error: ' + e);
        }
    });

}

function saveUpLoadDetails(data){
    var absolutePath=data;
    var userName=document.getElementById("UNfileUpload").value;
    $.ajax({
        url: 'client/saveFileUploadDetails',
        data: ({absolutePath:absolutePath,userName:userName,clientId:clientGrid.getSelectedRowId()}),
        success: function(result) {
            populateClientUploadGrid(clientGrid.getSelectedRowId());
        }
    });
}


$(function() {
    $('#addNewInstructionBtn2').click(function() {
        $('#createInstructionModal').modal('hide');
    });
});

$(function() {
    $('#AddNewNoteBtn2').click(function() {
        $('#createNotesModal').modal('hide');
    });
});

$(function() {
    $('#addNewContatctInfoBtn2').click(function() {
        $('#createNewContacts').modal('hide');
    });
});

$(function() {
    $('#editNoteBtn2').click(function() {
        $('#editNotesModal').modal('hide');
    });
});

$(function() {
    $('#editInstructionBtn2').click(function() {
        $('#editInstructionModal').modal('hide');
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

/*
 * tem fix
 */
function retryMap() {
    getAddressDetails(firstEverSelectedId);
    codeAddress(mapAddress);
}