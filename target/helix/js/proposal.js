/**
 * Created by nuwan.n.bandara on 8/13/2014.
 */

var hotListGrid;
var contactGrid;
var noteGrid;
var proposalGrid;

jQuery(document).ready(function() {
    populateHotListGrid();
    $('#deleteHotListBtn').attr('disabled', true);
    $('#leadInfoEditBtn').attr('disabled', true);
    $('#leadInfoCancelBtn').attr('disabled', true);
    $('#leadInfoSaveBtn').attr('disabled', true);
    $('#phoneInfoEditBtn').attr('disabled', true);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#AddressInfoEditBtn').attr('disabled', true);
    $('#AddressInfoCancelBtn').attr('disabled', true);
    $('#AddressInfoSaveBtn').attr('disabled', true);
    $('#AddNewContactInfoBtn').attr('disabled', true);
    $('#ContactInfoEditBtn').attr('disabled', true);
    $('#ContactInfoDeleteBtn').attr('disabled', true);
    $('#addNewNoteBtn').attr('disabled', true);
    $('#noteEditBtn').attr('disabled', true);
    $('#noteDeleteBtn').attr('disabled', true);
    $('#addNewProposalBtn').attr('disabled', true);
    $('#proposalEditBtn').attr('disabled', true);
    $('#proposalDeleteBtn').attr('disabled', true);
    $('#ContractInfoEditBtn').attr('disabled', true);
    $('#ContractInfoCancelBtn').attr('disabled', true);
    $('#ContractInfoSaveBtn').attr('disabled', true);
    disableControls(true);
    disablePhoneInfoControls(true);
    disableMAControls(true);
    disableCIControls(true);
    initializeContactGrid();
    initializeNoteGrid();
    initializeProposalGrid();
});

function initializeHotListGrid() {
    var window_size = $(window).height();
    document.getElementById('hotListGridBox').style.height = (window_size - 180) + "px";
    hotListGrid = new dhtmlXGridObject('hotListGridBox');
    hotListGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    hotListGrid.setHeader("Proposal List");
    hotListGrid.setInitWidths("*");
    hotListGrid.setColAlign("left");
    hotListGrid.setColTypes("ro");
    hotListGrid.setColSorting("str");
    hotListGrid.setSkin("dhx_skyblue");
    hotListGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    hotListGrid.init();
    hotListGrid.attachEvent("onRowSelect", doOnRowSelected);
}

function populateHotListGrid() {
    initializeHotListGrid();
    $.ajax({type:'GET',
        url: 'proposal/load',
        success: function(data) {
            populateHotListGridRequestHandler(data);
        }
    });
}

function populateHotListGridRequestHandler(data) {
    var lead = data.getElementsByTagName("clients/client");
    if (lead == null || lead.length < 1) {
        lead = data.getElementsByTagName("client");
    }
    for (var i = 0; i < lead.length; i++) {
        hotListGrid.addRow(lead[i].getAttribute("clientId"),
            [lead[i].getAttribute("name")], -1);
    }
}


function doOnRowSelected(rowId,columnIndex) {
    disableControls(true);
    refreshLeadInfo();
    refreshPhoneInfo();
    refreshAddressInfo();
    refreshContactInfo();
    refreshNoteInfo();
    refreshProposalInfo();
    refreshContractInfo();
    $('#deleteHotListBtn').attr('disabled', false);
    $('#leadInfoEditBtn').attr('disabled', false);
    $('#leadInfoCancelBtn').attr('disabled', true);
    $('#leadInfoSaveBtn').attr('disabled', true);
    $('#phoneInfoEditBtn').attr('disabled', false);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#AddressInfoEditBtn').attr('disabled', false);
    $('#AddressInfoCancelBtn').attr('disabled', true);
    $('#AddressInfoSaveBtn').attr('disabled', true);
    $('#AddNewContactInfoBtn').attr('disabled', false);
    $('#ContactInfoEditBtn').attr('disabled', true);
    $('#ContactInfoDeleteBtn').attr('disabled', true);
    $('#addNewNoteBtn').attr('disabled', false);
    $('#noteEditBtn').attr('disabled', true);
    $('#noteDeleteBtn').attr('disabled', true);
    $('#addNewProposalBtn').attr('disabled', false);
    $('#proposalEditBtn').attr('disabled', true);
    $('#proposalDeleteBtn').attr('disabled', true);
    $('#ContractInfoEditBtn').attr('disabled', false);
    $('#ContractInfoCancelBtn').attr('disabled', true);
    $('#ContractInfoSaveBtn').attr('disabled', true);
    getLeadInformation(rowId);
    getPhoneDetails(rowId);
    getAddressDetails(rowId);
    populateContactGrid(rowId);
    populateNoteGrid(rowId);
    populateProposalGrid(rowId);
    getContractInfo(rowId);

}

function getLeadInformation(rowId){

    $.ajax({
        url: 'proposal/loadLeadInformation',
        data: ({leadId:rowId}),
        success: function(data) {
            displayLeadInformation(data);
        }
    });

}

function displayLeadInformation(data) {

    var leadInfo = data.getElementsByTagName("details");
    if (leadInfo != null || leadInfo.length > 0) {
        document.getElementById("name").value=leadInfo[0].getAttribute("name");
        document.getElementById("acronym").value=leadInfo[0].getAttribute("acronym");
        document.getElementById("website").value=leadInfo[0].getAttribute("website");
        document.getElementById("rep").value=leadInfo[0].getAttribute("rep");
        if (leadInfo[0].getAttribute("forProfit") == "true") {
            document.getElementById("forProfit").checked = true;}
        document.getElementById("status").value=leadInfo[0].getAttribute("status");
        document.getElementById("jurisdiction").value=leadInfo[0].getAttribute("jurisdiction");
        document.getElementById("friendsOf").value=leadInfo[0].getAttribute("friendsOf");
        document.getElementById("campaignId").value=leadInfo[0].getAttribute("campaignId");
        document.getElementById("baseForm").value=leadInfo[0].getAttribute("baseForm");
    }

}


function getPhoneDetails(rowId){

    $.ajax({
        url: 'proposal/loadPhoneInformation',
        data: ({leadId:rowId}),
        success: function(data) {
            displayPhoneDetails(data);
        }
    });

}

function displayPhoneDetails(data) {

    var phone = data.getElementsByTagName("details");
    if (phone != null || phone.length > 0) {
        document.getElementById("main").value=phone[0].getAttribute("main");
        document.getElementById("fax").value=phone[0].getAttribute("fax");
        document.getElementById("dispatch").value=phone[0].getAttribute("dispatch");
    }

}


function getAddressDetails(rowId){

    $.ajax({
        url: 'proposal/loadMailingAddressInfo',
        data: ({leadId:rowId}),
        success: function(data) {
            displayMailingAddressDetails(data);
        }
    });

}

function displayMailingAddressDetails(data) {

    var address = data.getElementsByTagName("address/details");
    if (address == null || address.length < 1) {
        address = data.getElementsByTagName("details");
    }

    document.getElementById("city").value=address[0].getAttribute("city");
    document.getElementById("zip").value=address[0].getAttribute("zip");
    document.getElementById("address").value=address[0].getAttribute("address");
    document.getElementById("state").value=address[0].getAttribute("state");
}

/**
 *  Contact grid
 */
function initializeContactGrid() {
    contactGrid = new dhtmlXGridObject('contactGridbox');
    contactGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    contactGrid.setHeader("Name,Title,Phone,Home,Mobile,Other,Fax,Email");
    contactGrid.setInitWidths("165,100,80,80,80,80,80,*");
    contactGrid.setColAlign("left,left,left,left,left,left,left,left");
    contactGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
    contactGrid.setColSorting("str,str,str,str,str,str,str,str");
    contactGrid.setSkin("dhx_skyblue");
    contactGrid.init();
    contactGrid.attachEvent("onRowSelect", SelectedContactsGrid);

}

function populateContactGrid(rowId) {
    initializeContactGrid();
    $.ajax({
        url: 'proposal/loadContacts',
        data: "rowId=" +rowId,
        success: function(data) {
            populateContactGridRequestHandler(data);
        }
    });
}

function populateContactGridRequestHandler(data) {
    var contacts = data.getElementsByTagName("Contacts/contact");
    if (contacts == null || contacts.length < 1) {
        contacts = data.getElementsByTagName("contact");
    }
    for (var i = 0; i < contacts.length; i++) {
        contactGrid.addRow(contacts[i].getAttribute("contactId"),
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
function SelectedContactsGrid(rowId) {
    $('#ContactInfoEditBtn').attr('disabled', false);
    $('#ContactInfoDeleteBtn').attr('disabled', false);

    getSelectedClientContact(rowId);
}

function getSelectedClientContact(rowId) {

    $.ajax({
        url: 'proposal/loadSelectedContact',
        data: "rowId=" +rowId,
        success: function(data) {
            populateSCContactGridRequestHandler(data);
        }
    });
}

function populateSCContactGridRequestHandler(data) {

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


/**
 * note grid
 */
function initializeNoteGrid() {
    noteGrid = new dhtmlXGridObject('noteGridbox');
    noteGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    noteGrid.setHeader("Date,By,Note,Alert,createDate");
    noteGrid.setInitWidths("165,200,*,0,0");
    noteGrid.setColAlign("left,left,left,left,left");
    noteGrid.setColTypes("ro,ro,ro,ro,ro");
    noteGrid.setColSorting("str,str,str,str,str");
    noteGrid.setSkin("dhx_skyblue");
    noteGrid.init();
    noteGrid.attachEvent("onRowSelect",SelectedNoteGrid);

}

function populateNoteGrid(rowId) {
    initializeNoteGrid();
    $.ajax({
        url: 'proposal/loadNotes',
        data: "rowId=" +rowId,
        success: function(data) {
            populateNoteGridRequestHandler(data);
        }
    });
}

function populateNoteGridRequestHandler(data) {
    var notes = data.getElementsByTagName("notes/note");
    if (notes == null || notes.length < 1) {
        notes = data.getElementsByTagName("note");
    }
    for (var i = 0; i < notes.length; i++) {
        noteGrid.addRow(notes[i].getAttribute("noteId"),
            [notes[i].getAttribute("date"),
                notes[i].getAttribute("by"),
                notes[i].getAttribute("note"),
                notes[i].getAttribute("alert"),
                notes[i].getAttribute("createDate")], -1);
    }
}


function SelectedNoteGrid(rowId,columnIndex) {
    $('#noteEditBtn').attr('disabled', false);
    $('#noteDeleteBtn').attr('disabled', false);
    document.getElementById("editAlarmDate").value = noteGrid.cells(rowId, 0).getValue();
    document.getElementById("editNote").value = noteGrid.cells(rowId, 2).getValue();
    if (noteGrid.cells(rowId, 3).getValue() == "true") {
        document.getElementById("editAlarm").checked = true;
    }
    document.getElementById("editDateCreated").value = noteGrid.cells(rowId, 4).getValue();
}




/**
 * proposal grid
 */
function initializeProposalGrid() {
    proposalGrid = new dhtmlXGridObject('proposalGridbox');
    proposalGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    proposalGrid.setHeader("Date,Residents,Businesses,Projected Profit");
    proposalGrid.setInitWidths("165,200,200,*");
    proposalGrid.setColAlign("left,left,left,left");
    proposalGrid.setColTypes("ro,ro,ro,ro");
    proposalGrid.setColSorting("str,str,str,str");
    proposalGrid.setSkin("dhx_skyblue");
    proposalGrid.init();
    proposalGrid.attachEvent("onRowSelect",SelectedProposalGrid);

}

function populateProposalGrid(rowId) {
    initializeProposalGrid();
    $.ajax({
        url: 'proposal/loadProposals',
        data: "rowId=" +rowId,
        success: function(data) {
            populateProposalGridRequestHandler(data);
        }
    });
}

function populateProposalGridRequestHandler(data) {
    var proposals = data.getElementsByTagName("proposals/proposal");
    if (proposals == null || proposals.length < 1) {
        proposals = data.getElementsByTagName("proposal");
    }
    for (var i = 0; i < proposals.length; i++) {
        proposalGrid.addRow(proposals[i].getAttribute("proposalId"),
            [proposals[i].getAttribute("date"),
                proposals[i].getAttribute("residents"),
                proposals[i].getAttribute("businesses"),
                proposals[i].getAttribute("projectedProfit")], -1);
    }
}


function SelectedProposalGrid(rowId,columnIndex) {
    $('#proposalEditBtn').attr('disabled', false);
    $('#proposalDeleteBtn').attr('disabled', false);
    document.getElementById("EPDate").value = proposalGrid.cells(rowId, 0).getValue();
    document.getElementById("EPResidents").value = proposalGrid.cells(rowId, 1).getValue();
    document.getElementById("EPBusinesses").value = proposalGrid.cells(rowId, 2).getValue();
    document.getElementById("EPProjectedProfit").value = proposalGrid.cells(rowId, 3).getValue();

}


function getContractInfo(rowId) {

    $.ajax({
        url: 'proposal/loadContractInfo',
        data: "rowId=" +rowId,
        success: function(data) {
            populateContractRequestHandler(data);
        }
    });
}

function populateContractRequestHandler(data) {

    var contract =  data.getElementsByTagName("details");
    document.getElementById("commencementDate").value = contract[0].getAttribute("commencementDate");
    document.getElementById("terminationDate").value = contract[0].getAttribute("terminationDate");
    document.getElementById("terminationBuffer").value = contract[0].getAttribute("terminationBuffer");
    document.getElementById("clientPre").value = contract[0].getAttribute("clientPre");
    document.getElementById("renewPeriod").value = contract[0].getAttribute("renewPeriod");
    if (contract[0].getAttribute("charity") == "true") {
        document.getElementById("charity").checked = true;}
    document.getElementById("renewType").value = contract[0].getAttribute("renewType");
    if (contract[0].getAttribute("telemarketing") == "true") {
        document.getElementById("telemarketing").checked = true;}

}

function createNew(){

    refreshLeadInfo();
    refreshPhoneInfo();
    refreshAddressInfo();
    refreshContactInfo();
    refreshNoteInfo();
    refreshProposalInfo();
    refreshContractInfo();
    disableControls(false);
    disablePhoneInfoControls(true);
    disableMAControls(true);
    disableCIControls(true);
    populateHotListGrid();
    initializeContactGrid();
    initializeNoteGrid();
    initializeProposalGrid();
    $('#deleteHotListBtn').attr('disabled', true);
    $('#leadInfoEditBtn').attr('disabled', false);
    $('#leadInfoCancelBtn').attr('disabled', false);
    $('#leadInfoSaveBtn').attr('disabled', false);
    $('#phoneInfoEditBtn').attr('disabled', true);
    $('#phoneInfoCancelBtn').attr('disabled', true);
    $('#phoneInfoSaveBtn').attr('disabled', true);
    $('#AddressInfoEditBtn').attr('disabled', true);
    $('#AddressInfoCancelBtn').attr('disabled', true);
    $('#AddressInfoSaveBtn').attr('disabled', true);
    $('#AddNewContactInfoBtn').attr('disabled', true);
    $('#ContactInfoEditBtn').attr('disabled', true);
    $('#ContactInfoDeleteBtn').attr('disabled', true);
    $('#addNewNoteBtn').attr('disabled', true);
    $('#noteEditBtn').attr('disabled', true);
    $('#noteDeleteBtn').attr('disabled', true);
    $('#addNewProposalBtn').attr('disabled', true);
    $('#proposalEditBtn').attr('disabled', true);
    $('#proposalDeleteBtn').attr('disabled', true);
    $('#ContractInfoEditBtn').attr('disabled', true);
    $('#ContractInfoCancelBtn').attr('disabled', true);
    $('#ContractInfoSaveBtn').attr('disabled', true);


}



function deleteLeadInfo() {
    if(confirm("Do you want to delete this lead Information?")) {
        $.ajax({
            url: 'proposal/deleteLeadInfo',
            data: ({rowId : hotListGrid.getSelectedRowId()}),
            success: function(id) {
                populateHotListGrid();
            }
        });
    }
}



function editLeadInfo(){
    disableControls(false);
    $('#leadInfoCancelBtn').attr('disabled', false);
    $('#leadInfoSaveBtn').attr('disabled', false);

}

function saveLeadInfo() {

    var name=document.getElementById("name").value;
    var acronym=document.getElementById("acronym").value;
    var website=document.getElementById("website").value;
    var rep=document.getElementById("rep").value;
    var forProfit=document.getElementById("forProfit").checked;
    var status=document.getElementById("status").value;
    var jurisdiction=document.getElementById("jurisdiction").value;
    var friendsOf=document.getElementById("friendsOf").value;
    var baseForm=document.getElementById("baseForm").value;
    var campaignId=document.getElementById("campaignId").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'proposal/updateLeadInfo',
        data: ({name:name,acronym:acronym,website:website,rep:rep,forProfit:forProfit,
            status:status,jurisdiction:jurisdiction,friendsOf:friendsOf,baseForm:baseForm,
            campaignId:campaignId,userName:userName,
            idLead:hotListGrid.getSelectedRowId()}),
        mimeType:"text/html; charset=UTF-8",
        success: function(result) {
            populateHotListGrid();
            var myTimer = window.setTimeout(function() {
                hotListGrid.selectRowById(result,false,true,true);
            }, 500);

        }
    });

    $('#deleteHotListBtn').attr('disabled', false);
    $('#leadInfoEditBtn').attr('disabled', false);
    $('#leadInfoCancelBtn').attr('disabled', true);
    $('#leadInfoSaveBtn').attr('disabled', true);

}






function editPhoneInfo(){
    disablePhoneInfoControls(false);
    $('#phoneInfoCancelBtn').attr('disabled', false);
    $('#phoneInfoSaveBtn').attr('disabled', false);

}


function savePhoneInfo() {

    var main=document.getElementById("main").value;
    var fax=document.getElementById("fax").value;
    var dispatch=document.getElementById("dispatch").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'proposal/updatePhoneInfo',
        data: ({main:main,fax:fax,dispatch:dispatch,userName:userName,
            idLead:hotListGrid.getSelectedRowId()}),
        success: function(result) {
         }
    });

    sleep(1000);
    doOnRowSelected(hotListGrid.getSelectedRowId());
}


function editAddressInfo(){
    disableMAControls(false);
    $('#AddressInfoCancelBtn').attr('disabled', false);
    $('#AddressInfoSaveBtn').attr('disabled', false);

}


function saveMailingAddressInfo() {

    var address=document.getElementById("address").value;
    var city=document.getElementById("city").value;
    var state=document.getElementById("state").value;
    var zip=document.getElementById("zip").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'proposal/updateMailingAddressInfo',
        data: ({address:address,city:city,state:state,zip:zip,userName:userName,
            idLead:hotListGrid.getSelectedRowId()}),
        success: function(result) {
        }
    });

    sleep(1000);
    doOnRowSelected(hotListGrid.getSelectedRowId());
}


function deleteContactInfo() {
    if(confirm("Do you want to delete this contact details?")) {
        $.ajax({
            url: 'proposal/deleteContactInfo',
            data: ({rowId :contactGrid.getSelectedRowId()}),
            success: function(id) {
            }
        });
        sleep(1000);
        doOnRowSelected(hotListGrid.getSelectedRowId());
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
        url: 'proposal/addNewContactInfo',
        data: ({firstName:CCfirstName,lastName:CClastName,title:CCtitle,CCMain:CCMain,CCHome:CCHome,
            CCMobile:CCMobile,CCOther:CCOther,CCFax:CCFax,CCAltEmail:CCAltEmail,
            CCEmail:CCEmail,CCNotes:CCNotes,userName:userName,rowID:hotListGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(hotListGrid.getSelectedRowId());
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
        url: 'proposal/updateContactInfo',
        data: ({firstName:CCfirstName,lastName:CClastName,title:CCtitle,CCMain:CCMain,CCHome:CCHome,
            CCMobile:CCMobile,CCOther:CCOther,CCEmail:CCEmail,CCAltEmail:CCAltEmail,CCFax:CCFax,
            CCNotes:CCNotes,rowID:hotListGrid.getSelectedRowId(),userName:userName,
            contactId:contactGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(hotListGrid.getSelectedRowId());
}


function addNewNote() {
    var dateCreated=document.getElementById("dateCreated").value;
    var alarmDate=document.getElementById("alarmDate").value;
    var note=document.getElementById("note").value;
    var alarm=document.getElementById("alarm").checked;
    var userName=document.getElementById("userName").value;
    $.ajax({
        url: 'proposal/addNewNote',
        data: ({dateCreated:dateCreated,alarmDate:alarmDate,note:note,alarm:alarm,userName:userName,
            rowId:hotListGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(hotListGrid.getSelectedRowId());
}

function saveNote() {
    var dateCreated=document.getElementById("editDateCreated").value;
    var alarmDate=document.getElementById("editAlarmDate").value;
    var note=document.getElementById("editNote").value;
    var alarm=document.getElementById("editAlarm").checked;
    var userName=document.getElementById("editUserName").value;


    $.ajax({
        url: 'proposal/updateNote',
        data: ({dateCreated:dateCreated,alarmDate:alarmDate,note:note,alarm:alarm,userName:userName,
            rowId:hotListGrid.getSelectedRowId(),noteId:noteGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(hotListGrid.getSelectedRowId());
}

function deleteNote() {
    if(confirm("Do you want to delete this note ?")) {
        $.ajax({
            url: 'proposal/deleteNote',
            data: ({rowId :noteGrid.getSelectedRowId()}),
            success: function(id) {
            }
        });
        doOnRowSelected(hotListGrid.getSelectedRowId());
    }
}



function addNewProposalInfo() {
    var date=document.getElementById("NPDate").value;
    var residents=document.getElementById("NPResidents").value;
    var businesses=document.getElementById("NPBusinesses").value;
    var projectedProfit=document.getElementById("NPProjectedProfit").checked;
    var userName=document.getElementById("userName").value;
    $.ajax({
        url: 'proposal/addNewProposal',
        data: ({date:date,residents:residents,businesses:businesses,projectedProfit:projectedProfit,userName:userName,
            rowId:hotListGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(hotListGrid.getSelectedRowId());
}

function editProposalInfo() {
    var date=document.getElementById("NPDate").value;
    var residents=document.getElementById("NPResidents").value;
    var businesses=document.getElementById("NPBusinesses").value;
    var projectedProfit=document.getElementById("NPProjectedProfit").checked;
    var userName=document.getElementById("userName").value;


    $.ajax({
        url: 'proposal/updateProposal',
        data: ({date:date,residents:residents,businesses:businesses,projectedProfit:projectedProfit,userName:userName,
            rowId:hotListGrid.getSelectedRowId(),proposalId:proposalGrid.getSelectedRowId()}),
        success: function(id) {
        }
    });
    sleep(1000);
    doOnRowSelected(hotListGrid.getSelectedRowId());
}

function deleteProposal() {
    if(confirm("Do you want to delete this proposal ?")) {
        $.ajax({
            url: 'proposal/deleteProposal',
            data: ({rowId :proposalGrid.getSelectedRowId()}),
            success: function(id) {
            }
        });
        doOnRowSelected(hotListGrid.getSelectedRowId());
    }
}



function editContractInfo(){
    disableCIControls(false);
    $('#ContractInfoCancelBtn').attr('disabled', false);
    $('#ContractInfoSaveBtn').attr('disabled', false);

}


function saveContractInfo() {

    var commencementDate=document.getElementById("commencementDate").value;
    var terminationDate=document.getElementById("terminationDate").value;
    var terminationBuffer=document.getElementById("terminationBuffer").value;
    var clientPre=document.getElementById("clientPre").value;
    var renewPeriod=document.getElementById("renewPeriod").value;
    var charity=document.getElementById("charity").checked;
    var renewType=document.getElementById("renewType").value;
    var telemarketing=document.getElementById("telemarketing").checked;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'proposal/updateContractInfo',
        data: ({commencementDate:commencementDate,terminationDate:terminationDate,terminationBuffer:terminationBuffer,
            clientPre:clientPre,renewPeriod:renewPeriod,charity:charity,renewType:renewType,
            telemarketing:telemarketing,userName:userName,
            idLead:hotListGrid.getSelectedRowId()}),
        success: function(result) {
        }
    });

    sleep(1000);
    doOnRowSelected(hotListGrid.getSelectedRowId());
}



function disableControls(value) {
    $("#name").attr("disabled", value);
    $("#acronym").attr("disabled", value);
    $("#website").attr("disabled", value);
    $("#rep").attr("disabled", value);
    $("#forProfit").attr("disabled", value);
    $("#status").attr("disabled", value);
    $("#jurisdiction").attr("disabled", value);
    $("#friendsOf").attr("disabled", value);
    $("#baseForm").attr("disabled", value);
}

function disablePhoneInfoControls(value){

    $("#main").attr("disabled", value);
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

    $("#commencementDate").attr("disabled", value);
    $("#terminationDate").attr("disabled", value);
    $("#terminationBuffer").attr("disabled", value);
    $("#clientPre").attr("disabled", value);
    $("#renewPeriod").attr("disabled", value);
    $("#charity").attr("disabled", value);
    $("#renewType").attr("disabled", value);
    $("#telemarketing").attr("disabled", value);
}


function refreshLeadInfo() {
    document.getElementById("name").value ="";
    document.getElementById("acronym").value ="";
    document.getElementById("website").value ="";
    document.getElementById("rep").selectedIndex="0";
    document.getElementById("forProfit").checked =false;
    document.getElementById("status").selectedIndex="0";
    document.getElementById("jurisdiction").value ="";
    document.getElementById("friendsOf").value ="";
    document.getElementById("baseForm").value ="";

}

function refreshPhoneInfo() {
    document.getElementById("main").value ="";
    document.getElementById("fax").value ="";
    document.getElementById("dispatch").value ="";

}


function refreshAddressInfo() {
    document.getElementById("address").value ="";
    document.getElementById("city").value ="";
    document.getElementById("state").value ="";
    document.getElementById("zip").value ="";

}

function refreshContactInfo() {
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

function refreshNoteInfo() {
    document.getElementById("note").value ="";
    document.getElementById("dateCreated").value ="";
    document.getElementById("alarm").checked=false;
    document.getElementById("alarmDate").value ="";
}

function refreshProposalInfo() {
    document.getElementById("NPDate").value ="";
    document.getElementById("NPResidents").value ="";
    document.getElementById("NPBusinesses").value="";
    document.getElementById("NPProjectedProfit").value ="";
}


function refreshContractInfo() {
    document.getElementById("commencementDate").value ="";
    document.getElementById("terminationDate").value ="";
    document.getElementById("terminationBuffer").value="";
    document.getElementById("clientPre").value ="";
    document.getElementById("renewPeriod").selectedIndex="0";
    document.getElementById("charity").checked =false;
    document.getElementById("renewType").selectedIndex="0";
    document.getElementById("telemarketing").checked =false;
}


$(function() {
    $('#addNewContatctInfoBtn2').click(function() {
        $('#createNewContact').modal('hide');
    });
});

$(function() {
    $('#editContatctInfoBtn2').click(function() {
        $('#editContact').modal('hide');
    });
});

$(function() {
    $('#AddNewNoteBtn2').click(function() {
        $('#createNotesModal').modal('hide');
    });
});


$(function() {
    $('#editNoteBtn2').click(function() {
        $('#editNotesModal').modal('hide');
    });
});

$(function() {
    $('#addNewProposalInfoBtn2').click(function() {
        $('#createProposalModal').modal('hide');
    });
});


$(function() {
    $('#editProposalInfoBtn2').click(function() {
        $('#editProposalModal').modal('hide');
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