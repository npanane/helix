/**
 * Created by nuwan.n.bandara on 6/11/2014.
 */

var campaignGrid;

jQuery(document).ready(function() {
    if (document.getElementById("selectedClientId") != undefined
        && document.getElementById("selectedClientId").value != null
        && document.getElementById("selectedClientId").value != "") {
        $("#client").val(document.getElementById("selectedClientId").value);
        populateCampaignGrid(document.getElementById("selectedClientId").value);
    }
    disableControls(true);
    disableOtherControls(true);
    disableQtyControls(true);
    disableQtyControls2(true);
    disableCostControls(true);
    $('#deleteCampaignBtn').attr('disabled', true);
    $('#CampaignInfoEditBtn').attr('disabled', true);
    $('#CampaignInfoCancelBtn').attr('disabled', true);
    $('#CampaignInfoSaveBtn').attr('disabled', true);
    $('#OtherInfoEditBtn').attr('disabled', true);
    $('#OtherInfoSaveBtn').attr('disabled', true);
    $('#OtherInfoCancelBtn').attr('disabled', true);
    $('#QtyInfoEditBtn').attr('disabled', true);
    $('#QtyInfoCancelBtn').attr('disabled', true);
    $('#QtyInfoSaveBtn').attr('disabled', true);
    $('#CostInfoEditBtn').attr('disabled', true);
    $('#CostInfoCancelBtn').attr('disabled', true);
    $('#CostInfoSaveBtn').attr('disabled', true);
});

function loadCampaigns() {
    populateCampaignGrid($("#client").val());
}

function initializeCampaignGrid() {
    var window_size = $(window).height();
    document.getElementById('campaignGridbox').style.height = (window_size - 175) + "px";
    campaignGrid = new dhtmlXGridObject('campaignGridbox');
    campaignGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    campaignGrid.setHeader("Campaign Name");
    campaignGrid.setInitWidths("*");
    campaignGrid.setColAlign("left");
    campaignGrid.setColTypes("ro");
    campaignGrid.setColSorting("str");
    campaignGrid.setSkin("dhx_skyblue");
    campaignGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    campaignGrid.init();
    campaignGrid.attachEvent("onRowSelect", doOnRowSelected);
}

function populateCampaignGrid(clientId) {
    initializeCampaignGrid();
    $.ajax({type:'GET',
        url: 'campaign/load',
        data: ({clientId: clientId}),
        success: function(data) {
            populateCampaignGridRequestHandler(data);
        }
    });
}

function populateCampaignGridRequestHandler(data) {
    var campaigns = data.getElementsByTagName("campaigns/campaign");
    if (campaigns == null || campaigns.length < 1) {
        campaigns = data.getElementsByTagName("campaign");
    }
    for (var i = 0; i < campaigns.length; i++) {
        campaignGrid.addRow(campaigns[i].getAttribute("campaignId"),
            [campaigns[i].getAttribute("name")], -1);
    }
}


function doOnRowSelected(rowId,columnIndex) {
    disableControls(true);
    disableOtherControls(true);
    disableQtyControls(true);
    disableQtyControls2(true);
    disableCostControls(true);
    refreshCampaignInfo();
    refreshOtherInfo();
    refreshQtyInfo();
    refreshCostInfo();
    $('#deleteCampaignBtn').attr('disabled', false);
    $('#CampaignInfoEditBtn').attr('disabled', false);
    $('#CampaignInfoCancelBtn').attr('disabled', true);
    $('#CampaignInfoSaveBtn').attr('disabled', true);
    $('#OtherInfoEditBtn').attr('disabled', false);
    $('#OtherInfoCancelBtn').attr('disabled', true);
    $('#OtherInfoSaveeBtn').attr('disabled', true);
    $('#QtyInfoEditBtn').attr('disabled', false);
    $('#QtyInfoCancelBtn').attr('disabled', true);
    $('#QtyInfoSaveBtn').attr('disabled', true);
    $('#CostInfoEditBtn').attr('disabled', false);
    $('#CostInfoCancelBtn').attr('disabled', true);
    $('#CostInfoSaveBtn').attr('disabled', true);

    getCampaignInformation(rowId);
    getOtherInformation(rowId);
    getQtyInformation(rowId);
    getCostInformation(rowId);
}

function getCampaignInformation(rowId){

    $.ajax({
        url: 'campaign/loadCampaignInformation',
        data: ({campaignId:rowId}),
        success: function(data) {
            displayCampaignInformation(data);
        }
    });

}

function displayCampaignInformation(data) {

    var CampaignInfo = data.getElementsByTagName("details");
    if (CampaignInfo != null || CampaignInfo.length > 0) {
        document.getElementById("drive").value=CampaignInfo[0].getAttribute("drive");
        document.getElementById("year").value=CampaignInfo[0].getAttribute("year");
        document.getElementById("formNo").value=CampaignInfo[0].getAttribute("formNo");
        document.getElementById("type").value=CampaignInfo[0].getAttribute("type");
    }

}


function getOtherInformation(rowId){

    $.ajax({
        url: 'campaign/loadOtherInformation',
        data: ({campaignId:rowId}),
        success: function(data) {
            displayOtherInformation(data);
        }
    });

}

function displayOtherInformation(data) {

    var OtherInfo = data.getElementsByTagName("details");
    if (OtherInfo != null || OtherInfo.length > 0) {

        if (OtherInfo[0].getAttribute("photo") == "true") {
            document.getElementById("photo").checked = true;}
        if (OtherInfo[0].getAttribute("signature") == "true") {
            document.getElementById("signature").checked = true;}
        if (OtherInfo[0].getAttribute("badge") == "true") {
            document.getElementById("badge").checked = true;}
        if (OtherInfo[0].getAttribute("causes") == "true") {
            document.getElementById("causes").checked = true;}

        if (OtherInfo[0].getAttribute("letter") == "true") {
            document.getElementById("letter").checked = true;}
        if (OtherInfo[0].getAttribute("rd") == "true") {
            document.getElementById("rd").checked = true;}
        if (OtherInfo[0].getAttribute("crm") == "true") {
            document.getElementById("crm").checked = true;}

        if (OtherInfo[0].getAttribute("CPLetter") == "true") {
            document.getElementById("CPLetter").checked = true;}
        if (OtherInfo[0].getAttribute("CPrd") == "true") {
            document.getElementById("CPrd").checked = true;}
        if (OtherInfo[0].getAttribute("CPBadge") == "true") {
            document.getElementById("CPBadge").checked = true;}

        if (OtherInfo[0].getAttribute("FPLetter") == "true") {
            document.getElementById("FPLetter").checked = true;}
        if (OtherInfo[0].getAttribute("FPrd") == "true") {
            document.getElementById("FPrd").checked = true;}
        if (OtherInfo[0].getAttribute("FPSticker") == "true") {
            document.getElementById("FPSticker").checked = true;}
        if (OtherInfo[0].getAttribute("FPcrm") == "true") {
            document.getElementById("FPcrm").checked = true;}

    }

}

function getQtyInformation(rowId){

    $.ajax({
        url: 'campaign/loadQtyInformation',
        data: ({campaignId:rowId}),
        success: function(data) {
            displayQtyInformation(data);
        }
    });

}

function displayQtyInformation(data) {

    var QtyInfo = data.getElementsByTagName("details");
    if (QtyInfo != null || QtyInfo.length > 0) {
        document.getElementById("BAmountMailed").value=QtyInfo[0].getAttribute("BAmountMailed");
        document.getElementById("BAmountReturned").value=QtyInfo[0].getAttribute("BAmountReturned");
        document.getElementById("BPercentageReturned").value=QtyInfo[0].getAttribute("BPercentageReturned");
        document.getElementById("RAmountMailed").value=QtyInfo[0].getAttribute("RAmountMailed");
        document.getElementById("RAmountReturned").value=QtyInfo[0].getAttribute("RAmountReturned");
        document.getElementById("RPercentageReturned").value=QtyInfo[0].getAttribute("RPercentageReturned");
        document.getElementById("TAmountMailed").value=QtyInfo[0].getAttribute("TAmountMailed");
        document.getElementById("TAmountReturned").value=QtyInfo[0].getAttribute("TAmountReturned");
        document.getElementById("TPercentageReturned").value=QtyInfo[0].getAttribute("TPercentageReturned");
    }

}


function getCostInformation(rowId){

    $.ajax({
        url: 'campaign/loadCostInformation',
        data: ({campaignId:rowId}),
        success: function(data) {
            displayCostInformation(data);
        }
    });

}

function displayCostInformation(data) {

    var CostInfo = data.getElementsByTagName("details");
    if (CostInfo != null || CostInfo.length > 0) {
        document.getElementById("letters").value=CostInfo[0].getAttribute("letters");
        document.getElementById("data").value=CostInfo[0].getAttribute("data");
        document.getElementById("POBox").value=CostInfo[0].getAttribute("POBox");
        document.getElementById("decals").value=CostInfo[0].getAttribute("decals");
        document.getElementById("production").value=CostInfo[0].getAttribute("production");
        document.getElementById("envelopes").value=CostInfo[0].getAttribute("envelopes");
        document.getElementById("artWork").value=CostInfo[0].getAttribute("artWork");
        if (CostInfo[0].getAttribute("NonProfitBulkRate") == "true") {
            document.getElementById("NonProfitBulkRate").checked = true;}
        document.getElementById("postage").value=CostInfo[0].getAttribute("postage");
        document.getElementById("pins").value=CostInfo[0].getAttribute("pins");
    }

}

function addCampaign() {
    disableControls(false);
    disableOtherControls(true);
    disableQtyControls(true);
    disableQtyControls2(true);
    disableCostControls(true);
    refreshCampaignInfo();
    refreshOtherInfo();
    refreshQtyInfo();
    refreshCostInfo();
    $('#deleteCampaignBtn').attr('disabled', true);
    $('#CampaignInfoEditBtn').attr('disabled', false);
    $('#CampaignInfoCancelBtn').attr('disabled', false);
    $('#CampaignInfoSaveBtn').attr('disabled', false);
    $('#OtherInfoEditBtn').attr('disabled', true);
    $('#OtherInfoCancelBtn').attr('disabled', true);
    $('#OtherInfoSaveeBtn').attr('disabled', true);
    $('#QtyInfoEditBtn').attr('disabled', true);
    $('#QtyInfoCancelBtn').attr('disabled', true);
    $('#QtyInfoSaveBtn').attr('disabled', true);
    $('#CostInfoEditBtn').attr('disabled', true);
    $('#CostInfoCancelBtn').attr('disabled', true);
    $('#CostInfoSaveBtn').attr('disabled', true);

    populateCampaignGrid();

}

function deleteCampaign() {
    if(confirm("Do you want to delete this campaign Information?")) {
        $.ajax({
            url: 'campaign/deleteCampaignInfo',
            data: ({rowId : campaignGrid.getSelectedRowId()}),
            success: function(id) {
                populateCampaignGrid();
            }
        });
    }
}

function saveCampaignInfo() {

    var drive=document.getElementById("drive").value;
    var year=document.getElementById("year").value;
    var formNo=document.getElementById("formNo").value;
    var type=document.getElementById("type").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'campaign/updateCampaignInfo',
        data: ({drive:drive,year:year,formNo:formNo,type:type,
            userName:userName,rowId:campaignGrid.getSelectedRowId()}),
        mimeType:"text/html; charset=UTF-8",
        success: function(result) {
            populateCampaignGrid();
            var myTimer = window.setTimeout(function() {
                campaignGrid.selectRowById(result,false,true,true);
            }, 500);

        }
    });
    $('#deleteCampaignBtn').attr('disabled', false);
    $('#CampaignInfoEditBtn').attr('disabled', false);
    $('#CampaignInfoCancelBtn').attr('disabled', true);
    $('#CampaignInfoSaveBtn').attr('disabled', true);

}


function saveOtherInfo() {

    var photo=document.getElementById("photo").checked;
    var signature=document.getElementById("signature").checked;
    var badge=document.getElementById("badge").checked;
    var causes=document.getElementById("causes").checked;
    var letter=document.getElementById("letter").checked;
    var rd=document.getElementById("rd").checked;
    var crm=document.getElementById("crm").checked;
    var CPLetter=document.getElementById("CPLetter").checked;
    var CPrd=document.getElementById("CPrd").checked;
    var CPBadge=document.getElementById("CPBadge").checked;
    var FPLetter=document.getElementById("FPLetter").checked;
    var FPrd=document.getElementById("FPrd").checked;
    var FPSticker=document.getElementById("FPSticker").checked;
    var FPcrm=document.getElementById("FPcrm").checked;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'campaign/updateOtherInfo',
        data: ({photo:photo,signature:signature,badge:badge,causes:causes,letter:letter,rd:rd,
            crm:crm,CPLetter:CPLetter,CPrd:CPrd,CPBadge:CPBadge,FPLetter:FPLetter,FPrd:FPrd,FPSticker:FPSticker,
            FPcrm:FPcrm,userName:userName,
            rowId:campaignGrid.getSelectedRowId()}),
        success: function(result) {
        }
    });

    sleep(1000);
    doOnRowSelected(campaignGrid.getSelectedRowId());
}


function saveQtyInfo() {

    var BAmountMailed=document.getElementById("BAmountMailed").value;
    var BAmountReturned=document.getElementById("BAmountReturned").value;
    var BPercentageReturned=document.getElementById("BPercentageReturned").value;
    var RAmountMailed=document.getElementById("RAmountMailed").value;
    var RAmountReturned=document.getElementById("RAmountReturned").value;
    var RPercentageReturned=document.getElementById("RPercentageReturned").value;
    var TAmountMailed=document.getElementById("TAmountMailed").value;
    var TAmountReturned=document.getElementById("TAmountReturned").value;
    var TPercentageReturned=document.getElementById("TPercentageReturned").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'campaign/updateQtyInfo',
        data: ({BAmountMailed:BAmountMailed,BAmountReturned:BAmountReturned,BPercentageReturned:BPercentageReturned,
            RAmountMailed:RAmountMailed,RAmountReturned:RAmountReturned,RPercentageReturned:RPercentageReturned,
            TAmountMailed:TAmountMailed,TAmountReturned:TAmountReturned,TPercentageReturned:TPercentageReturned,
            userName:userName,rowId:campaignGrid.getSelectedRowId()}),
        success: function(result) {
        }
    });

    sleep(1000);
    doOnRowSelected(campaignGrid.getSelectedRowId());
}


function saveCostInfo() {

    var letters=document.getElementById("letters").value;
    var data=document.getElementById("data").value;
    var POBox=document.getElementById("POBox").value;
    var decals=document.getElementById("decals").value;
    var production=document.getElementById("production").value;
    var envelopes=document.getElementById("envelopes").value;
    var artWork=document.getElementById("artWork").value;
    var NonProfitBulkRate=document.getElementById("NonProfitBulkRate").checked;
    var postage=document.getElementById("postage").value;
    var pins=document.getElementById("pins").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'campaign/updateCostInfo',
        data: ({letters:letters,data:data,POBox:POBox,
            decals:decals,production:production,envelopes:envelopes,
            artWork:artWork,NonProfitBulkRate:NonProfitBulkRate,postage:postage,pins:pins,
            userName:userName,rowId:campaignGrid.getSelectedRowId()}),
        success: function(result) {
        }
    });

    sleep(1000);
    doOnRowSelected(campaignGrid.getSelectedRowId());
}


function editCampaignInfo(){
    disableControls(false);
    $('#CampaignInfoCancelBtn').attr('disabled', false);
    $('#CampaignInfoSaveBtn').attr('disabled', false);

}

function editOtherInfo(){
    disableOtherControls(false);
    $('#OtherInfoSaveBtn').attr('disabled', false);
    $('#OtherInfoCancelBtn').attr('disabled', false);
}

function editQtyInfo(){
    disableQtyControls(true);
    disableQtyControls2(false);
    $('#QtyInfoCancelBtn').attr('disabled', false);
    $('#QtyInfoSaveBtn').attr('disabled', false);

}

function editCostInfo(){
    disableCostControls(false);
    $('#CostInfoCancelBtn').attr('disabled', false);
    $('#CostInfoSaveBtn').attr('disabled', false);

}

function disableControls(value) {
    $("#drive").attr("disabled", value);
    $("#year").attr("disabled", value);
    $("#formNo").attr("disabled", value);
    $("#type").attr("disabled", value);
}
function disableOtherControls(value) {
    $("#photo").attr("disabled", value);
    $("#signature").attr("disabled", value);
    $("#badge").attr("disabled", value);
    $("#causes").attr("disabled", value);
    $("#letter").attr("disabled", value);
    $("#rd").attr("disabled", value);
    $("#crm").attr("disabled", value);
    $("#CPLetter").attr("disabled", value);
    $("#CPrd").attr("disabled", value);
    $("#CPBadge").attr("disabled", value);
    $("#FPLetter").attr("disabled", value);
    $("#FPrd").attr("disabled", value);
    $("#FPSticker").attr("disabled", value);
    $("#FPcrm").attr("disabled", value);
}

function disableQtyControls(value) {
    $("#BAmountReturned").attr("disabled", value);
    $("#BPercentageReturned").attr("disabled", value);
    $("#RAmountReturned").attr("disabled", value);
    $("#RPercentageReturned").attr("disabled", value);
    $("#TAmountMailed").attr("disabled", value);
    $("#TAmountReturned").attr("disabled", value);
    $("#TPercentageReturned").attr("disabled", value);

}

function disableQtyControls2(value) {
    $("#BAmountMailed").attr("disabled", value);
    $("#RAmountMailed").attr("disabled", value);
}



function disableCostControls(value) {
    $("#letters").attr("disabled", value);
    $("#data").attr("disabled", value);
    $("#POBox").attr("disabled", value);
    $("#decals").attr("disabled", value);
    $("#production").attr("disabled", value);
    $("#envelopes").attr("disabled", value);
    $("#artWork").attr("disabled", value);
    $("#NonProfitBulkRate").attr("disabled", value);
    $("#postage").attr("disabled", value);
    $("#pins").attr("disabled", value);
}

function refreshCampaignInfo() {
    document.getElementById("drive").selectedIndex="0";
    document.getElementById("year").value ="";
    document.getElementById("formNo").value ="";
    document.getElementById("type").selectedIndex="0";
}

function refreshOtherInfo() {
    document.getElementById("photo").checked =false;
    document.getElementById("signature").checked =false;
    document.getElementById("badge").checked =false;
    document.getElementById("causes").checked =false;
    document.getElementById("letter").checked =false;
    document.getElementById("rd").checked =false;
    document.getElementById("crm").checked =false;
    document.getElementById("CPLetter").checked =false;
    document.getElementById("CPrd").checked =false;
    document.getElementById("CPBadge").checked =false;
    document.getElementById("FPLetter").checked =false;
    document.getElementById("FPrd").checked =false;
    document.getElementById("FPSticker").checked =false;
    document.getElementById("FPcrm").checked =false;

}

function refreshQtyInfo() {

    document.getElementById("BAmountMailed").value ="";
    document.getElementById("BAmountReturned").value ="";
    document.getElementById("BPercentageReturned").value ="";
    document.getElementById("RAmountMailed").value ="";
    document.getElementById("RAmountReturned").value ="";
    document.getElementById("RPercentageReturned").value ="";
    document.getElementById("TAmountMailed").value ="";
    document.getElementById("TAmountReturned").value ="";
    document.getElementById("TPercentageReturned").value ="";

}

function refreshCostInfo() {

    document.getElementById("letters").value ="";
    document.getElementById("data").value ="";
    document.getElementById("POBox").value ="";
    document.getElementById("decals").value ="";
    document.getElementById("production").value ="";
    document.getElementById("envelopes").value ="";
    document.getElementById("artWork").value ="";
    document.getElementById("NonProfitBulkRate").checked =false;
    document.getElementById("postage").value ="";
    document.getElementById("pins").value ="";

}

function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
}