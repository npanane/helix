/**
 * Created by nuwan.n.bandara on 6/11/2014.
 */

var driveGrid;
var campaignGrid;

jQuery(document).ready(function() {
    populateDriveGrid();
    initializeCampaignGrid();
    disableControls(true);
    $('#deleteDriveBtn').attr('disabled', true);
    $('#DriveInfoEditBtn').attr('disabled', true);
    $('#DriveInfoCancelBtn').attr('disabled', true);
    $('#DriveInfoSaveBtn').attr('disabled', true);
    $('#AddNewCampaignInfoBtn').attr('disabled', true);
    $('#CampaignInfoSaveBtn').attr('disabled', true);
    $('#CampaignInfoDeleteBtn').attr('disabled', true);

});

function initializeDriveGrid() {
    var window_size = $(window).height();
    document.getElementById('driveGridbox').style.height = (window_size - 175) + "px";
    driveGrid = new dhtmlXGridObject('driveGridbox');
    driveGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    driveGrid.setHeader("Name");
    driveGrid.setInitWidths("*");
    driveGrid.setColAlign("left");
    driveGrid.setColTypes("ro");
    driveGrid.setColSorting("str");
    driveGrid.setSkin("dhx_skyblue");
    driveGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    driveGrid.init();
    driveGrid.attachEvent("onRowSelect", doOnRowSelected);
}

function populateDriveGrid() {
    initializeDriveGrid();
    $.ajax({type:'GET',
        url: 'drive/load',
        success: function(data) {
            populateDriveGridRequestHandler(data);
        }
    });
}

function populateDriveGridRequestHandler(data) {
    var drives = data.getElementsByTagName("drives/drive");
    if (drives == null || drives.length < 1) {
        drives = data.getElementsByTagName("drive");
    }
    for (var i = 0; i < drives.length; i++) {
        driveGrid.addRow(drives[i].getAttribute("driveId"),
            [drives[i].getAttribute("name")], -1);
    }
}

function doOnRowSelected(rowId,columnIndex) {
    disableControls(true);
    refreshDriveInfo();

    $('#deleteDriveBtn').attr('disabled', false);
    $('#DriveInfoEditBtn').attr('disabled', false);
    $('#DriveInfoCancelBtn').attr('disabled', true);
    $('#DriveInfoSaveBtn').attr('disabled', true);
    $('#AddNewCampaignInfoBtn').attr('disabled', false);
    $('#CampaignInfoSaveBtn').attr('disabled', true);
    $('#CampaignInfoDeleteBtn').attr('disabled', true);
    getDriveInformation(rowId);
    populateCampaignGrid(rowId);
}


function getDriveInformation(rowId){

    $.ajax({
        url: 'drive/loadDriveInformation',
        data: ({driveId:rowId}),
        success: function(data) {
            displayDriveInformation(data);
        }
    });

}

function displayDriveInformation(data) {

    var drive = data.getElementsByTagName("details");
    if (drive != null || drive.length > 0) {
        document.getElementById("description").value=drive[0].getAttribute("description");
        document.getElementById("year").value=drive[0].getAttribute("year");
        document.getElementById("month").value=drive[0].getAttribute("month");
        if (drive[0].getAttribute("inProcess") == "true") {
            document.getElementById("inProcess").checked = true;}
    }

}

function initializeCampaignGrid() {
    campaignGrid = new dhtmlXGridObject('CampaignGridbox');
    campaignGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    campaignGrid.setHeader("Edit,Client,Year,Type,Amount,CC Amount,Count,Average $,Response %");
    campaignGrid.setInitWidths("80,*,80,80,80,80,80,80,80");
    campaignGrid.setColAlign("left,left,left,left,left,left,left,left,left");
    campaignGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro");
    campaignGrid.setColSorting("str,str,str,str,str,str,str,str,str");
    campaignGrid.init();
    campaignGrid.setSkin("dhx_skyblue");
    campaignGrid.attachEvent("onRowSelect", doOnRowSelectedCampaignGrid);

}

function populateCampaignGrid(rowId) {
   initializeCampaignGrid()
    $.ajax({type:'GET',
        url: 'drive/loadCampaignList',
        data: "driveId="+rowId,
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
            [campaigns[i].getAttribute("edit"),
             campaigns[i].getAttribute("client"),
             campaigns[i].getAttribute("year"),
             campaigns[i].getAttribute("type"),
             campaigns[i].getAttribute("amount"),
             campaigns[i].getAttribute("ccAmount"),
             campaigns[i].getAttribute("count"),
             campaigns[i].getAttribute("average"),
             campaigns[i].getAttribute("response")],-1);
   }
}

function doOnRowSelectedCampaignGrid(rowId,columnIndex) {
    $('#CampaignInfoSaveBtn').attr('disabled', false);
    $('#CampaignInfoDeleteBtn').attr('disabled', false);
}


function addDrive() {
    populateDriveGrid();
    initializeCampaignGrid();
    $("#description").attr("disabled", false);
    $("#month").attr("disabled", false);
    $("#year").attr("disabled", false);
    $("#totalAmount").attr("disabled", true);
    $("#totalCount").attr("disabled", true);
    $("#totalCCAmount").attr("disabled", true);
    $("#averageAmount").attr("disabled", true);
    $("#Response").attr("disabled", true);
    $("#inProcess").attr("disabled", true);
    refreshDriveInfo();
    $('#deleteDriveBtn').attr('disabled', true);
    $('#DriveInfoEditBtn').attr('disabled', false);
    $('#DriveInfoCancelBtn').attr('disabled', false);
    $('#DriveInfoSaveBtn').attr('disabled', false);
    $('#AddNewCampaignInfoBtn').attr('disabled', true);
    $('#CampaignInfoSaveBtn').attr('disabled', true);
    $('#CampaignInfoDeleteBtn').attr('disabled', true);

}

function deleteDrive() {
    if(confirm("Do you want to delete this drive details?")) {
        $.ajax({
            url: 'drive/deleteDrive',
            data: ({rowId : driveGrid.getSelectedRowId()}),
            success: function(id) {
                populateDriveGrid();
            }
        });
    }
}



function editDriveInfo() {

    $("#description").attr("disabled", false);
    $("#month").attr("disabled", false);
    $("#year").attr("disabled", false);
    $("#totalAmount").attr("disabled", true);
    $("#totalCount").attr("disabled", true);
    $("#totalCCAmount").attr("disabled", true);
    $("#averageAmount").attr("disabled", true);
    $("#Response").attr("disabled", true);
    $("#inProcess").attr("disabled", true);
    $('#DriveInfoCancelBtn').attr('disabled', false);
    $('#DriveInfoSaveBtn').attr('disabled', false);
}


function saveOrUpdateDriveInfo(){
    var description=document.getElementById("description").value;
    var month=document.getElementById("month").value;
    var year=document.getElementById("year").value;
    var totalAmount=document.getElementById("totalAmount").value;
    var totalCount=document.getElementById("totalCount").value;
    var totalCCAmount=document.getElementById("totalCCAmount").value;
    var averageAmount=document.getElementById("averageAmount").value;
    var Response=document.getElementById("Response").value;
    var inProcess=document.getElementById("inProcess").checked;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'drive/saveOrUpdateDriveInfo',
        data: ({description:description,month:month,year:year,totalAmount:totalAmount,
            totalCount:totalCount,totalCCAmount:totalCCAmount,averageAmount:averageAmount,Response:Response,
            inProcess:inProcess,userName:userName,driveId:driveGrid.getSelectedRowId()}),
        mimeType:"text/html; charset=UTF-8",
        success: function(result) {
            populateDriveGrid();
            var myTimer = window.setTimeout(function() {
                driveGrid.selectRowById(result,false,true,true);
            }, 500);

        }
    });

}

function addNewCampaignInfo() {
    $.ajax({
        url: 'drive/addNewCampaignInfo',
        data: ({client:"client5",year:"2014",driveId:driveGrid.getSelectedRowId()}),
        success: function(id) {
            doOnRowSelected(driveGrid.getSelectedRowId());
        }
    });
}

function deleteCampaignInfo() {
    if(confirm("Do you want to delete this Campaign Information?")) {
        $.ajax({
            url: 'drive/deleteCampaignInfo',
            data: ({rowId : campaignGrid.getSelectedRowId()}),
            success: function(id) {
                doOnRowSelected(driveGrid.getSelectedRowId());
            }
        });
    }
}

function saveCampaignInfo(){
     $.ajax({
        url: 'drive/updateCampaignInfo',
        data: ({client:"Client4",year:"2014",campaignId:campaignGrid.getSelectedRowId(),
            driveId:driveGrid.getSelectedRowId()}),
        success: function(id) {
            doOnRowSelected(driveGrid.getSelectedRowId());

        }
    });

}

function disableControls(value) {
    $("#description").attr("disabled", value);
    $("#month").attr("disabled", value);
    $("#year").attr("disabled", value);
    $("#totalAmount").attr("disabled", value);
    $("#totalCount").attr("disabled", value);
    $("#totalCCAmount").attr("disabled", value);
    $("#averageAmount").attr("disabled", value);
    $("#Response").attr("disabled", value);
    $("#inProcess").attr("disabled", value);
}

function refreshDriveInfo() {
    $("#description").val("");
    document.getElementById("month").selectedIndex="0";
    $("#year").val("");
    $("#totalAmount").val("");
    $("#totalCount").val("");
    $("#totalCCAmount").val("");
    $("#averageAmount").val("");
    $("#Response").val("");
    document.getElementById("inProcess").checked =false;
}


function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
}