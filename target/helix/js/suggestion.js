var suggestionGrid;

jQuery(document).ready(function() {
    populateSuggestionGrid();

});

function initializeSuggestionGrid() {
    var window_size = $(window).height();
    document.getElementById('suggestionGridbox').style.height = (window_size - 150) + "px";
    suggestionGrid = new dhtmlXGridObject('suggestionGridbox');
    //suggestionGrid.setImagePath("/dhtmlxGrid/codebase/imgs/");
    suggestionGrid.setImagePath("assets/images/imgs/");
    suggestionGrid.setHeader("Date,By,Suggestion,Completed");
    suggestionGrid.setInitWidths("100,100,*,100");
    suggestionGrid.setColAlign("left,left,left,left");
    suggestionGrid.setColTypes("ro,ro,ro,ch");
    suggestionGrid.setColSorting("str,str,str,str");
    suggestionGrid.init();
    suggestionGrid.setSkin("dhx_skyblue");
    suggestionGrid.setStyle("font-family: 'Roboto Condensed', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;background-color: #3498db;font-family: sans-serif;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%; ", "", "", "");
    suggestionGrid.attachEvent("onCheck",doOnCheckBoxSelected);

}

function doOnCheckBoxSelected(rID, cInd, state){
    var state=state;
    var userName=document.getElementById("userName").value;
    var rowId=rID;

    $.ajax({
        url: 'suggestions/editSuggestion',
        data: ({userName:userName,state:state,rowId:rID}),
        success: function(id) {
        }
    });
    sleep(1000);
    populateSuggestionGrid();

}


function populateSuggestionGrid() {
    initializeSuggestionGrid();
    $.ajax({type:'GET',
        url: 'suggestions/loadSuggestions',
        success: function(data) {
            populateSuggestionGridRequestHandler(data);
        }
    });
}

function populateSuggestionGridRequestHandler(data) {
    var suggestions = data.getElementsByTagName("suggestions/suggestion");
    if (suggestions == null || suggestions.length < 1) {
        suggestions = data.getElementsByTagName("suggestion");
    }
    for (var i = 0; i < suggestions.length; i++) {
        suggestionGrid.addRow(suggestions[i].getAttribute("suggestionId"),
            [suggestions[i].getAttribute("date"),
             suggestions[i].getAttribute("by"),
             suggestions[i].getAttribute("suggestion"),
             suggestions[i].getAttribute("completed")], -1);
    }
}



function submit(){

    var suggestion=document.getElementById("suggestion").value;
    var userName=document.getElementById("userName").value;

    $.ajax({
        url: 'suggestions/saveSuggestion',
        data: ({userName:userName,suggestion:suggestion}),
        success: function(id) {
        }
    });
    sleep(1000);
    populateSuggestionGrid();
}


function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }

}