/**
 * Created by nuwan.n.bandara on 9/17/2014.
 */

var springPOBoxGrid;
var fallPOBoxGrid;
jQuery(document).ready(function() {
    populateSpringPOBoxGrid();
    populateFallPOBoxGrid();
});

function initializeSpringPOBoxGrid() {
    var window_size = $(window).height();
    document.getElementById('springPOBoxGridbox').style.height = (window_size - 150) + "px";
    springPOBoxGrid = new dhtmlXGridObject('springPOBoxGridbox');
    springPOBoxGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    springPOBoxGrid.setHeader("Client,Box#,Phone,Contact,Picked Up,Notes");
    springPOBoxGrid.setInitWidths("*,200,100,100,100,*");
    springPOBoxGrid.setColAlign("left,left,left,left,left,left");
    springPOBoxGrid.setColTypes("ro,ro,ed,ed,ed,ed");
    springPOBoxGrid.setColSorting("str,str,str,str,str,str");
    springPOBoxGrid.init();
    springPOBoxGrid.setSkin("dhx_skyblue");
    springPOBoxGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    springPOBoxGrid.attachEvent("onEditCell", doOnEditCell);
}

function populateSpringPOBoxGrid() {
    initializeSpringPOBoxGrid();
    $.ajax({type:'GET',
        url: 'poBoxReShips/loadSpringPOBoxInfo',
        success: function(data) {
            populateSpringPOBoxGridRequestHandler(data);
        }
    });
}

function populateSpringPOBoxGridRequestHandler(data) {
    var clients = data.getElementsByTagName("clients/client");
    if (clients == null || clients.length < 1) {
        clients = data.getElementsByTagName("client");
    }
    for (var i = 0; i < clients.length; i++) {
        springPOBoxGrid.addRow(clients[i].getAttribute("posOfficeId"),
            [clients[i].getAttribute("client"),
             clients[i].getAttribute("box"),
             clients[i].getAttribute("phone"),
             clients[i].getAttribute("contact"),
             clients[i].getAttribute("pickedUp"),
             clients[i].getAttribute("notes")], -1);
    }
}

function doOnEditCell(stage,rowId,cellIndex,newValue,oldValue){
    if ((stage==2)&&(newValue!=oldValue)){

        if(cellIndex==2){
            var phoneNo=newValue;
            var userName=document.getElementById("userName").value;
            var rowId=rowId;

            $.ajax({
                url: 'poBoxReShips/editSPhoneNumber',
                data: ({phoneNo:phoneNo,userName:userName,rowId:rowId}),
                success: function(id) {
                }
            });
            sleep(1000);
            populateSpringPOBoxGrid();

        }
        if(cellIndex==3){
            var contactName=newValue;
            var userName=document.getElementById("userName").value;
            var rowId=rowId;

            $.ajax({
                url: 'poBoxReShips/editSContactName',
                data: ({contactName:contactName,userName:userName,rowId:rowId}),
                success: function(id) {
                }
            });
            sleep(1000);
            populateSpringPOBoxGrid();

        }
        if(cellIndex==4){
            var date=newValue;
            var userName=document.getElementById("userName").value;
            var rowId=rowId;

            $.ajax({
                url: 'poBoxReShips/editSDate',
                data: ({date:date,userName:userName,rowId:rowId}),
                success: function(id) {
                }
            });
            sleep(1000);
            populateSpringPOBoxGrid();

        }
        if(cellIndex==5){

            var note=newValue;
            var userName=document.getElementById("userName").value;
            var rowId=rowId;

            $.ajax({
                url: 'poBoxReShips/editSNote',
                data: ({note:note,userName:userName,rowId:rowId}),
                success: function(id) {
                }
            });
            sleep(1000);
            populateSpringPOBoxGrid();
        }


        return true;
    }
    return true;
}




function initializeFallPOBoxGrid() {
    var window_size = $(window).height();
    document.getElementById('fallPOBoxGridbox').style.height = (window_size - 150) + "px";
    fallPOBoxGrid = new dhtmlXGridObject('fallPOBoxGridbox');
    fallPOBoxGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    fallPOBoxGrid.setHeader("Client,Box#,Phone,Contact,Picked Up,Notes");
    fallPOBoxGrid.setInitWidths("*,200,100,100,100,*");
    fallPOBoxGrid.setColAlign("left,left,left,left,left,left");
    fallPOBoxGrid.setColTypes("ro,ro,ed,ed,ed,ed");
    fallPOBoxGrid.setColSorting("str,str,str,str,str,str");
    fallPOBoxGrid.init();
    fallPOBoxGrid.setSkin("dhx_skyblue");
    fallPOBoxGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    fallPOBoxGrid.attachEvent("onEditCell", doOnEditCell2);
}

function populateFallPOBoxGrid() {
    initializeFallPOBoxGrid();
    $.ajax({type:'GET',
        url: 'poBoxReShips/loadFallPOBoxInfo',
        success: function(data) {
            populateFallPOBoxGridRequestHandler(data);
        }
    });
}

function populateFallPOBoxGridRequestHandler(data) {
    var clients = data.getElementsByTagName("clients/client");
    if (clients == null || clients.length < 1) {
        clients = data.getElementsByTagName("client");
    }
    for (var i = 0; i < clients.length; i++) {
        fallPOBoxGrid.addRow(clients[i].getAttribute("posOfficeId"),
            [clients[i].getAttribute("client"),
             clients[i].getAttribute("box"),
             clients[i].getAttribute("phone"),
             clients[i].getAttribute("contact"),
             clients[i].getAttribute("pickedUp"),
             clients[i].getAttribute("notes")], -1);
    }
}

function doOnEditCell2(stage,rowId,cellIndex,newValue,oldValue){
    if ((stage==2)&&(newValue!=oldValue)){

        if(cellIndex==2){
            var phoneNo=newValue;
            var userName=document.getElementById("userName").value;
            var rowId=rowId;

            $.ajax({
                url: 'poBoxReShips/editFPhoneNumber',
                data: ({phoneNo:phoneNo,userName:userName,rowId:rowId}),
                success: function(id) {
                }
            });
            sleep(1000);
            populateSpringPOBoxGrid();

        }
        if(cellIndex==3){
            var contactName=newValue;
            var userName=document.getElementById("userName").value;
            var rowId=rowId;

            $.ajax({
                url: 'poBoxReShips/editFContactName',
                data: ({contactName:contactName,userName:userName,rowId:rowId}),
                success: function(id) {
                }
            });
            sleep(1000);
            populateSpringPOBoxGrid();

        }
        if(cellIndex==4){
            var date=newValue;
            var userName=document.getElementById("userName").value;
            var rowId=rowId;

            $.ajax({
                url: 'poBoxReShips/editFDate',
                data: ({date:date,userName:userName,rowId:rowId}),
                success: function(id) {
                }
            });
            sleep(1000);
            populateSpringPOBoxGrid();

        }
        if(cellIndex==5){

            var note=newValue;
            var userName=document.getElementById("userName").value;
            var rowId=rowId;

            $.ajax({
                url: 'poBoxReShips/editFNote',
                data: ({note:note,userName:userName,rowId:rowId}),
                success: function(id) {
                }
            });
            sleep(1000);
            populateSpringPOBoxGrid();
        }


        return true;
    }
    return true;
}


function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }

}