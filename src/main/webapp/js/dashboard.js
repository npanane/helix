/**
 * dashboard.js
 */
var eventGrid;
var pendingDataEntryGrid;

/*
 *
 */
jQuery(document).ready(function() {
    toggleSection();
    //populateEventGrid();
    //populatePendingDataEntryGrid();
});

function toggleSection() {
    jQuery(".client-content").show();
    //toggle the componenet with class msg_body
    jQuery(".client-heading").click(function() {
        jQuery(this).next(".client-content").slideToggle(500);
    });
}

function initializeEventGrid() {
    eventGrid = new dhtmlXGridObject('gridbox');
    eventGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    eventGrid.setHeader("Client, Event");
    eventGrid.setInitWidths("250,405");
    eventGrid.setColAlign("left,left");
    eventGrid.setColTypes("ro,ro");
    eventGrid.setColSorting("str,str");
    eventGrid.setSkin("dhx_skyblue");
    eventGrid.init();
}

function populateEventGrid() {
    initializeEventGrid();
    $.ajax({
        url: 'dashboard/load',
        success: function(data) {
            populateClientGridRequestHandler(data);
        }
    });
}

function populateClientGridRequestHandler(data) {
    var events = data.getElementsByTagName("events/event");
    if (events == null || events.length < 1) {
        events = data.getElementsByTagName("event");
    }
    for (var i = 0; i < events.length; i++) {
        eventGrid.addRow(events[i].getAttribute("eventId"),
            [events[i].getAttribute("clientName"),
                events[i].getAttribute("note")], -1);
    }
}

/*  Pending data entry */
function initializePendingDataEntryGrid() {
    pendingDataEntryGrid = new dhtmlXGridObject('pendingDataEntryGridbox');
    pendingDataEntryGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    pendingDataEntryGrid.setHeader("DE Level, Credit Card, Add Donation, Priority, Founder's Circle, Removals, Amount, No. Remain");
    pendingDataEntryGrid.setInitWidths("65,75,90,70,100,75,100,80");
    pendingDataEntryGrid.setColAlign("left,left,left,left,left,left,left,left");
    pendingDataEntryGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro");
    pendingDataEntryGrid.setColSorting("str,str");
    pendingDataEntryGrid.setSkin("dhx_skyblue");
    pendingDataEntryGrid.init();
}

function populatePendingDataEntryGrid() {
    initializePendingDataEntryGrid();
    $.ajax({
        url: 'dashboard/loadPendingDataEntries',
        success: function(data) {
            populatePendingDataEntryGridRequestHandler(data);
        }
    });
}

function populatePendingDataEntryGridRequestHandler(data) {
    var pendingDataEntries = data.getElementsByTagName("pendingDataEntries/pendingDataEntry");
    if (pendingDataEntries == null || pendingDataEntries.length < 1) {
        pendingDataEntries = data.getElementsByTagName("pendingDataEntry");
    }
    for (var i = 0; i < pendingDataEntries.length; i++) {
        pendingDataEntryGrid.addRow(pendingDataEntries[i].getAttribute("id"),
            [pendingDataEntries[i].getAttribute("deLevel"),
                pendingDataEntries[i].getAttribute("creditCard"),
                pendingDataEntries[i].getAttribute("addDonation"),
                pendingDataEntries[i].getAttribute("priority"),
                pendingDataEntries[i].getAttribute("foundersCircle"),
                pendingDataEntries[i].getAttribute("removals"),
                pendingDataEntries[i].getAttribute("amount"),
                pendingDataEntries[i].getAttribute("noRemains")], -1);
    }
}