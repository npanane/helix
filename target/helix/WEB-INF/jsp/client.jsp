<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel='stylesheet' href='assets/css/plugins/fullcalendar.css'>
    <link rel='stylesheet' href='assets/css/plugins/datatables/datatables.css'>
    <link rel='stylesheet' href='assets/css/plugins/datatables/bootstrap.datatables.css'>
    <link rel='stylesheet' href='assets/css/plugins/chosen.css'>
    <link rel='stylesheet' href='assets/css/plugins/jquery.timepicker.css'>
    <link rel='stylesheet' href='assets/css/plugins/daterangepicker-bs3.css'>
    <link rel='stylesheet' href='assets/css/plugins/colpick.css'>
    <link rel='stylesheet' href='assets/css/plugins/dropzone.css'>
    <link rel='stylesheet' href='assets/css/plugins/jquery.handsontable.full.css'>
    <link rel='stylesheet' href='assets/css/plugins/jscrollpane.css'>
    <link rel='stylesheet' href='assets/css/plugins/jquery.pnotify.default.css'>
    <link rel='stylesheet' href='assets/css/plugins/jquery.pnotify.default.icons.css'>
    <link rel='stylesheet' href='assets/css/app.css'>
    <link rel='stylesheet' href='css/client.css'>
    <!-- Grid Styling -->
    <link rel="stylesheet" type="text/css" href="/dhtmlxGrid/codebase/dhtmlxgrid.css">
    <link rel="stylesheet" type="text/css" href="assets/css/grid/dhtmlxgrid_dhx_skyblue.css">


    <link href='http://fonts.googleapis.com/css?family=Roboto:100,300,400,700|Roboto+Condensed:300,400,700' rel='stylesheet' type='text/css'>

    <link href="assets/favicon.ico" rel="shortcut icon">
    <link href="assets/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    @javascript html5shiv respond.min
    <![endif]-->

    <title>Clients</title>
    <!-- Left Grid Style -->
    <style>
        div.gridbox_dhx_skyblue table.obj tr td {
            font-family: sans-serif;
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
            font-size: 14px;
            border-width: 0px 0px 0px 0px;
            padding-right: 4px;
            padding-left: 4px;
            padding-bottom: 1px;
            padding-top: 1px;
        }

        div.gridbox_dhx_skyblue .odd_dhx_skyblue {
            /*background-color: white;*/
        }

        .sub-sidebar-wrapper {
            width: 310px;
        }

        .datepicker{z-index:1151;}

    </style>

    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
    <script>
        var geocoder;
        var map;
        function initialize() {
            geocoder = new google.maps.Geocoder();
            var latlng = new google.maps.LatLng(-34.397, 150.644);
            var mapOptions = {
                zoom: 8,
                center: latlng
            }
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
        }

        function codeAddress(address) {
            initialize();
            //var address = document.getElementById('address').value;
            geocoder.geocode( { 'address': address}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    map.setCenter(results[0].geometry.location);
                    var marker = new google.maps.Marker({
                        map: map,
                        position: results[0].geometry.location
                    });
                } else {
                    retryMap();
                    //alert('Address were not able to locate in the map.' +
                    //        '\nGeocode was not successful for the following reason: ' + status);
                }
            });
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>

</head>

<body class="glossed">
<div class="all-wrapper fixed-header left-menu">
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="side">
        <div class="sidebar-wrapper">
            <ul>
                <li class='current'>
                    <a class='current' href="dashboard" data-toggle="tooltip" data-placement="right" title="" data-original-title="Dashboard">
                        <i class="fa fa-home"></i>
                    </a>
                </li>
                <li>
                    <a href="client" data-toggle="tooltip" data-placement="right" title="" data-original-title="Contacts & Channels">
                        <i class="fa fa-file-text-o"></i>
                    </a>
                </li>
                <li>
                    <a href="drive" data-toggle="tooltip" data-placement="right" title="" data-original-title="Data Entry">
                        <span class="badge"></span>
                        <i class="fa fa-code-fork"></i>
                    </a>
                </li>
                <li>
                    <a href="lead" data-toggle="tooltip" data-placement="right" title="" data-original-title="Sales">
                        <i class="fa fa-bar-chart-o"></i>
                    </a>
                </li>
            </ul>
        </div>
        <div class="sub-sidebar-wrapper">
            <div style="padding-top: 5px; padding-left: 5px" >
                <div  style="width:300px; background-color:#2c3e50;" id="gridbox"> </div>
            </div>
            <br/>
            <div align="right" style="padding-left: 5px; padding-right: 5px">
                <button id="addNewClientBtn" class="btn btn-primary btn-sm" onclick="addNewClient()"><i class="fa fa-edit"></i>Add New Client</button>
                <button id="deleteBtn" class="btn btn-danger btn-sm" onclick="deleteClient()"><i class="fa fa-trash-o"></i>Delete</button>
            </div>
        </div>

    </div>
    <div class="main-content">
        <div class="row">
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>Client Information</h3>
                    </div>
                    <div class="widget-content">
                        <form  role="form">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input type="text" id="name" class="form-control input-sm" placeholder="Name">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Acronym</label>
                                        <input type="text" id="acronym" class="form-control input-sm" placeholder="Acronym">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Jurisdiction</label>
                                        <input type="text" id="jurisdiction" class="form-control input-sm" placeholder="Jurisdiction">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Client Type</label>
                                        <form:select path="clientTypeDdl" id="clientTypes" class="form-control">
                                            <form:options items="${clientTypeDdl}" />
                                        </form:select>
                                    </div>
                                </div>
                            </div>

                        </form>
                        <button id="editBtn" class="btn btn-primary btn-sm" onclick="edit()"><i class="fa fa-edit"></i>Edit</button>
                        <button id="cancelBtn" class="btn btn-default btn-sm" onclick="refresh()"><i class="fa fa-refresh"></i>Refresh</button>
                        <button id="saveBtn" class="btn btn-success btn-sm" onclick="saveClient()"><i class="fa fa-save"></i>Save</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>Phone Numbers</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Main Phone</label>
                                    <input type="text" id="mainPhone" class="form-control input-sm" placeholder="Main Phone">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Fax</label>
                                    <input type="text" id="fax" class="form-control input-sm" placeholder="Fax">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Dispatch</label>
                                    <input type="text" id="dispatch" class="form-control input-sm" placeholder="Dispatch">
                                </div>
                            </div>
                            <div>
                                <input type="hidden" id="IdContact" class="form-control input-sm" >

                            </div>
                        </div>
                        <button id="phoneInfoEditBtn" class="btn btn-primary btn-sm" onclick="editPhoneInfo()"><i class="fa fa-edit"></i>Edit</button>
                        <button id="phoneInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshPhoneInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                        <button id="phoneInfoSaveBtn" class="btn btn-success btn-sm" onclick="savePhoneInfo()"><i class="fa fa-save"></i>Save</button>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh" onclick="populateContactGrid(null)"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>Contacts</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">

                            <div style="padding: 10px" >
                                <div  style="width:100%; height:100%;background-color:#ffffff;" id="contactGridbox"> </div>
                                <br/>
                                <div class="btn btn-primary btn-sm" id="AddNewContactInfoBtn" data-toggle="modal" data-target="#createNewContacts"><i class="fa fa-user"></i>Add New Contact</div>
                                <div class="btn btn-success btn-sm" id="ContactInfoEditBtn" data-toggle="modal" data-target="#editContacts"><i class="fa fa-edit"></i>Edit</div>
                                <button id="ContactInfoDeleteBtn" class="btn btn-danger btn-sm" onclick="deleteContactInfo()"><i class="fa fa-trash-o"></i>Delete</button>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>Client Mailing Address</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Address</label>
                                    <input type="text" id="address" class="form-control input-sm" placeholder="Address">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>City</label>
                                    <input type="text" id="city" class="form-control input-sm" placeholder="City">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>State</label>
                                    <!--<input type="text" id="state" class="form-control input-sm" placeholder="State">-->
                                    <form:select path="mailingAddStateDdl" id="state" class="form-control">
                                        <form:options items="${mailingAddStateDdl}" />
                                    </form:select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Zip</label>
                                    <input type="text" id="zip" class="form-control input-sm" placeholder="Zip">
                                </div>
                            </div>
                            <div>
                                <input type="hidden" id="IdAddress" class="form-control input-sm" >

                            </div>
                        </div>

                        <button id="AddressInfoEditBtn" class="btn btn-primary btn-sm" onclick="editAddressInfo()"><i class="fa fa-edit"></i>Edit</button>
                        <button id="AddressInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshAddressInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                        <button id="AddressInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveClientAddressInfo()"><i class="fa fa-trash-o"></i>Save</button>
                    </div>
                </div>
            </div>

        </div>


        <jsp:include page="masterCampaigInfoClient.jsp"></jsp:include>
        <jsp:include page="postOfficeInfoClient.jsp"></jsp:include>
        <jsp:include page="mailContractInfoClient.jsp"></jsp:include>
        <jsp:include page="noticeOfIntentClient.jsp"></jsp:include>

        <div class="row">
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>Notes</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">

                            <div style="padding: 10px" >
                                <div  style="width:100%; height:100%;background-color:#ffffff;" id="noteGridbox"> </div>
                                <br/>
                                <div class="btn btn-primary btn-sm" id="addNewNoteBtn" data-toggle="modal" data-target="#createNotesModal"><i class="fa fa-user"></i>Create New Note</div>
                                <div class="btn btn-success btn-sm" id="noteEditBtn" data-toggle="modal" data-target="#editNotesModal"><i class="fa fa-edit"></i>Edit</div>
                                <button id="noteDeleteBtn" class="btn btn-danger btn-sm" onclick="deleteNote()"><i class="fa fa-trash-o"></i>Delete</button>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>Selection Instructions</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">

                            <div style="padding: 10px" >
                                <div  style="width:100%; height:100%;background-color:#ffffff;" id="instructionGridbox"> </div>
                                <br/>
                                <div class="btn btn-primary btn-sm" id="addNewInstructionBtn" data-toggle="modal" data-target="#createInstructionModal"><i class="fa fa-user"></i>Create New Instruction</div>
                                <div class="btn btn-success btn-sm" id="instructionEditBtn" data-toggle="modal" data-target="#editInstructionModal"><i class="fa fa-edit"></i>Edit</div>
                                <button id="instructionDeleteBtn" class="btn btn-danger btn-sm" onclick="deleteInstruction()"><i class="fa fa-trash-o"></i>Delete</button>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>Upload Files</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">

                            <div style="padding: 10px" >

                                <div  style="width:100%; height:200px;background-color:#ffffff;" id="clientUploadGridbox"> </div>
                                <br/>
                                <div class="col-md-4">
                                    <form id="uploadForm" enctype="multipart/form-data" >
                                        <div>
                                            <div class="form-group">
                                                <input type="file" id="file" name="file">
                                            </div>
                                        </div>
                                        <input type="hidden" name="userName" id="UNfileUpload" value=${pageContext.request.userPrincipal.name} >
                                    </form>
                                </div>
                                <div class="col-md-4">
                                    <button class="btn btn-primary btn-sm" id="uploadFilebtn" onclick="uploadFile()"><i class="fa fa-upload"></i> Upload</button>
                                    <button id="uploadFileDeleteBtn" class="btn btn-danger btn-sm" onclick="deleteUploadFile()"><i class="fa fa-trash-o"></i>Delete</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>Map</h3>
                    </div>
                    <div class="widget-content no-padding">
                        <div style="height: 350px;" id="map-canvas"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>Current News</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">

                            <div style="padding: 10px" >
                                <div  style="width:100%; height:100%;background-color:#ffffff;" id="newsGridbox"> </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <br/>
        <div class="row">
            <div class="col-md-12">
                <a href="client/print" target="_blank" class="btn btn-action btn-success btn-sm" ><i class="fa fa-print"></i>Print</a>

            </div>
        </div>
    </div>
</div>
<div class="page-footer">
    Â© 2016 Helix
</div>
<!--Modal for creating new client contacts -->
<div class="modal fade" id="createNewContacts" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">
                        <!-- <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a> -->
                        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                    </div>
                    <h3><i class="fa fa-ok-circle"></i> Create New Contact</h3>
                </div>
                <div class="widget-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Rank</label>
                                    <input id="CCrank" type="text"  class="form-control" placeholder="Rank">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input id="CCfirstName" type="text"  class="form-control" placeholder="First Name">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input id="CClastName" type="text"  class="form-control" placeholder="Last Name">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Title</label>
                                    <input id="CCtitle" type="text"  class="form-control" placeholder="Title">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Address</label>
                                    <input id="CCAddress" type="text"  class="form-control" placeholder="Address">
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>City</label>
                                    <input id="CCCity" type="text"  class="form-control" placeholder="city">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>State</label>
                                    <!-- <input id="CCState" type="text"  class="form-control" placeholder="state"> -->
                                    <form:select path="stateDdl" id="CCState" class="form-control">
                                        <form:options items="${stateDdl}" />
                                    </form:select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Zip</label>
                                    <input id="CCZip" type="text"  class="form-control" placeholder="zip">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Main Phone</label>
                                    <input id="CCMain" type="text"  class="form-control" placeholder="Main Phone">
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Home Phone</label>
                                    <input id="CCHome" type="text"  class="form-control" placeholder="Home Phone">
                                </div>
                            </div>
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <br/><br/>
                                    <label>Monthly Statements:</label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Mobile Phone</label>
                                    <input id="CCMobile" type="text"  class="form-control" placeholder="Mobile Phone">
                                </div>
                            </div>
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="CCEmailCheck" value="true"> Email
                                    </label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Other Phone</label>
                                    <input id="CCOther" type="text"  class="form-control" placeholder="Other Phone">
                                </div>
                            </div>
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="CCFaxCheck" value="true"> Fax
                                    </label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Fax</label>
                                    <input id="CCFax" type="text"  class="form-control" placeholder="Fax">
                                </div>
                            </div>
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="CCMailCheck" value="true"> Mail
                                    </label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input id="CCEmail" type="text"  class="form-control" placeholder="Email">
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Alt Email</label>
                                    <input id="CCAltEmail" type="text"  class="form-control" placeholder="Email">
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Notes</label>
                                    <textarea class="form-control" id="CCNotes" rows="3"></textarea>
                                </div>
                            </div>

                        </div>
                        <button type="submit" class="btn btn-primary" id="addNewContatctInfoBtn2" onclick="addNewContactInfo()" ><i class="fa fa-save"></i>Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>Close</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!--Modal for creating instructions-->

<div class="modal fade" id="createInstructionModal" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">
                        <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                    </div>
                    <h3><i class="fa fa-ok-circle"></i> Create New Instruction</h3>
                </div>
                <div class="widget-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Instruction</label>
                                    <textarea class="form-control" id="instruction" rows="4"></textarea>
                                </div>
                            </div>

                        </div>


                        <button type="submit" class="btn btn-primary" id="addNewInstructionBtn2" onclick="addNewInstruction()" ><i class="fa fa-save"></i>Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>Close</button>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Modal for creating notes-->
<div class="modal fade" id="createNotesModal" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">
                        <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                    </div>
                    <h3><i class="fa fa-ok-circle"></i> Create New Note</h3>
                </div>
                <div class="widget-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-4">
                                <label>Date Created</label>
                                <div class="input-group">
                                    <input type="text" id="dateCreated" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd"  class="form-control input-datepicker">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="alarm" value="true"> Alarm
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label>Alarm Date</label>
                                <div class="input-group">
                                    <input type="text" id="alarmDate" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd"  class="form-control input-datepicker">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </div>

                            </div>

                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <label>Note</label>
                                <div class="input-group">
                                    <textarea class="form-control" id="note" rows="4" cols="80"></textarea>
                                </div>
                            </div>

                        </div>
                        <br/>
                        <div>
                            <input type="hidden" id="userName" value=${pageContext.request.userPrincipal.name}>
                        </div>
                        <button type="submit" class="btn btn-primary" id="AddNewNoteBtn2" onclick="addNewNote()" ><i class="fa fa-save"></i>Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Modal for edit client contacts -->
<div class="modal fade" id="editContacts" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">
                        <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                    </div>
                    <h3><i class="fa fa-ok-circle"></i> Edit Contact</h3>
                </div>
                <div class="widget-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Rank</label>
                                    <input id="editCCrank" type="text"  class="form-control" placeholder="Rank">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input id="editCCfirstName" type="text"  class="form-control" placeholder="First Name">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input id="editCClastName" type="text"  class="form-control" placeholder="Last Name">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Title</label>
                                    <input id="editCCtitle" type="text"  class="form-control" placeholder="Title">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Address</label>
                                    <input id="editCCAddress" type="text"  class="form-control" placeholder="Address">
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>City</label>
                                    <input id="editCCCity" type="text"  class="form-control" placeholder="city">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>State</label>
                                    <!--<input id="editCCState" type="text"  class="form-control" placeholder="state">-->
                                    <form:select path="stateDdl" id="editCCState" class="form-control">
                                        <form:options items="${stateDdl}" />
                                    </form:select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Zip</label>
                                    <input id="editCCZip" type="text"  class="form-control" placeholder="zip">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Main Phone</label>
                                    <input id="editCCMain" type="text"  class="form-control" placeholder="Main Phone">
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Home Phone</label>
                                    <input id="editCCHome" type="text"  class="form-control" placeholder="Home Phone">
                                </div>
                            </div>
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <br/><br/>
                                    <label>Monthly Statements:</label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Mobile Phone</label>
                                    <input id="editCCMobile" type="text"  class="form-control" placeholder="Mobile Phone">
                                </div>
                            </div>
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="editCCEmailCheck" value="true"> Email
                                    </label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Other Phone</label>
                                    <input id="editCCOther" type="text"  class="form-control" placeholder="Other Phone">
                                </div>
                            </div>
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="editCCFaxCheck" value="true"> Fax
                                    </label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Fax</label>
                                    <input id="editCCFax" type="text"  class="form-control" placeholder="Fax">
                                </div>
                            </div>
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="editCCMailCheck" value="true"> Mail
                                    </label>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input id="editCCEmail" type="text"  class="form-control" placeholder="Email">
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Alt Email</label>
                                    <input id="editCCAltEmail" type="text"  class="form-control" placeholder="Email">
                                </div>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Notes</label>
                                    <textarea class="form-control" id="editCCNotes" rows="3"></textarea>
                                </div>
                            </div>

                        </div>
                        <button type="submit" class="btn btn-primary" id="editContatctInfoBtn2" onclick="saveContactInfo()" ><i class="fa fa-save"></i>Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>Close</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Modal for edit notes-->
<div class="modal fade" id="editNotesModal" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">
                        <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                    </div>
                    <h3><i class="fa fa-ok-circle"></i> Edit Note</h3>
                </div>
                <div class="widget-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-4">
                                <label>Date Created</label>
                                <div class="input-group">
                                    <input type="text" id="editDateCreated" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd"  class="form-control input-datepicker">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="editAlarm" value="true"> Alarm
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <label>Alarm Date</label>
                                <div class="input-group">
                                    <input type="text" id="editAlarmDate" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd"  class="form-control input-datepicker">
                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                </div>
                            </div>

                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Note</label>
                                    <textarea class="form-control" id="editNote" rows="4" cols="60"></textarea>
                                </div>
                            </div>

                        </div>
                        <div>
                            <input type="hidden" id="editUserName" value=${pageContext.request.userPrincipal.name}>
                        </div>
                        </br>
                        <button type="submit" class="btn btn-primary" id="editNoteBtn2" onclick="saveNote()" ><i class="fa fa-save"></i>Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--Modal for edit instructions-->
<div class="modal fade" id="editInstructionModal" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">
                        <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                    </div>
                    <h3><i class="fa fa-ok-circle"></i> Edit Instruction</h3>
                </div>
                <div class="widget-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Instruction</label>
                                    <textarea class="form-control" id="editInstruction" rows="4"></textarea>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary" id="editInstructionBtn2" onclick="saveInstruction()" ><i class="fa fa-save"></i>Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script src='assets/js/plugins/jquery.pnotify.js'></script>
<script src='assets/js/plugins/jquery.sparkline.min.js'></script>
<script src='assets/js/plugins/mwheelIntent.js'></script>
<script src='assets/js/plugins/mousewheel.js'></script>
<script src='assets/js/bootstrap/tab.js'></script>
<script src='assets/js/bootstrap/dropdown.js'></script>
<script src='assets/js/bootstrap/tooltip.js'></script>
<script src='assets/js/bootstrap/collapse.js'></script>
<script src='assets/js/bootstrap/scrollspy.js'></script>
<script src='assets/js/plugins/bootstrap-datepicker.js'></script>
<script src='assets/js/bootstrap/transition.js'></script>
<script src='assets/js/plugins/jquery.knob.js'></script>
<script src='assets/js/plugins/jquery.flot.min.js'></script>
<script src='assets/js/plugins/fullcalendar.js'></script>
<script src='assets/js/plugins/datatables/datatables.min.js'></script>
<script src='assets/js/plugins/chosen.jquery.min.js'></script>
<script src='assets/js/plugins/jquery.timepicker.min.js'></script>
<script src='assets/js/plugins/daterangepicker.js'></script>
<script src='assets/js/plugins/colpick.js'></script>
<script src='assets/js/plugins/moment.min.js'></script>
<script src='assets/js/plugins/datatables/bootstrap.datatables.js'></script>
<script src='assets/js/bootstrap/modal.js'></script>
<script src='assets/js/plugins/raphael-min.js'></script>
<script src='assets/js/plugins/morris-0.4.3.min.js'></script>
<script src='assets/js/plugins/justgage.1.0.1.min.js'></script>
<script src='assets/js/plugins/jquery.maskedinput.min.js'></script>
<script src='assets/js/plugins/jquery.maskmoney.js'></script>
<script src='assets/js/plugins/summernote.js'></script>
<script src='assets/js/plugins/dropzone-amd-module.js'></script>
<script src='assets/js/plugins/jquery.validate.min.js'></script>
<script src='assets/js/plugins/jquery.bootstrap.wizard.min.js'></script>
<script src='assets/js/plugins/jscrollpane.min.js'></script>
<script src='assets/js/application.js'></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<!-- Grid Scripts -->
<script src="/dhtmlxGrid/sources/dhtmlxCommon/codebase/dhtmlxcommon.js"></script>
<script src="/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
<!--<script src="assets/js/grid/dhtmlxgridcell.js"></script>-->
<script src="js/client.js"></script>
<!--<script src='assets/js/template_js/gmaps.js'></script>-->
</body>
</html>
