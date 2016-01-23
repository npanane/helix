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


    <!-- Hammer reload -->
    <script>
        setInterval(function(){
            try {
                if(typeof ws != 'undefined' && ws.readyState == 1){return true;}
                ws = new WebSocket('ws://'+(location.host || 'localhost').split(':')[0]+':35353')
                ws.onopen = function(){ws.onclose = function(){document.location.reload()}}
                ws.onmessage = function(){
                    var links = document.getElementsByTagName('link');
                    for (var i = 0; i < links.length;i++) {
                        var link = links[i];
                        if (link.rel === 'stylesheet' && !link.href.match(/typekit/)) {
                            href = link.href.replace(/((&|\?)hammer=)[^&]+/,'');
                            link.href = href + (href.indexOf('?')>=0?'&':'?') + 'hammer='+(new Date().valueOf());
                        }
                    }
                }
            }catch(e){}
        }, 1000)
    </script>
    <!-- /Hammer reload -->


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
    <link rel='stylesheet' href='css/addressBook.css'>
    <!-- Grid Styling -->
    <link rel="stylesheet" type="text/css" href="assets/css/grid/dhtmlxgrid.css">
    <link rel="stylesheet" type="text/css" href="assets/css/grid/dhtmlxgrid_dhx_skyblue.css">


    <link href='http://fonts.googleapis.com/css?family=Roboto:100,300,400,700|Roboto+Condensed:300,400,700' rel='stylesheet' type='text/css'>

    <link href="assets/favicon.ico" rel="shortcut icon">
    <link href="assets/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    @javascript html5shiv respond.min
    <![endif]-->

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

    <title>Address Book</title>

</head>

<body class="glossed">
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-42863888-4', 'pinsupreme.com');
    ga('send', 'pageview');

</script>
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
                <!--
                <li>
                    <a href="table.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Tables">
                        <i class="fa fa-th"></i>
                    </a>
                </li>
                <li>
                    <a href="grid.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Layouts">
                        <i class="fa fa-font"></i>
                    </a>
                </li>
                <li>
                    <a href="calendar.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Calendar">
                        <span class="badge">5</span>
                        <i class="fa fa-calendar"></i>
                    </a>
                </li>
                <li>
                    <a href="maps.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Maps">
                        <i class="fa fa-map-marker"></i>
                    </a>
                </li>
                <li>
                    <a href="page_search.html" data-toggle="tooltip" data-placement="right" title="" data-original-title="Extra Pages">
                        <i class="fa fa-trophy"></i>
                    </a>
                </li>
                -->
            </ul>
        </div>
        <div class="sub-sidebar-wrapper">
            <br/>
            <div style="padding: 10px">
               <div style="width:296px;height:475px;background-color:#2c3e50;" id="addressBookGridbox"> </div>
            </div>
            <br/>
            <div align="right" style="padding: 10px">
              <button class="btn btn-primary btn-sm" onclick="addNewAddress()" >Add New Address</button>
              <button id="deleteAddress" class="btn btn-danger btn-sm" onclick="deleteAddress()" ><i class="fa fa-trash-o"></i>Delete</button>
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
                        <h3><i class="fa fa-ok-circle"></i>Contact Information</h3>
                    </div>
                    <div class="widget-content">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Name</label>
                                        <input type="text" id="name" class="form-control input-sm" placeholder="Name">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                      <label>Contact Person</label>
                                      <input type="text" id="contactPerson" class="form-control input-sm" placeholder="Contact Person">
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Address 1</label>
                                        <input type="text" id="address1" class="form-control input-sm" placeholder="Address One">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Address 2</label>
                                        <input type="text" id="address2" class="form-control input-sm" placeholder="Address two">
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
                                        <input type="text" id="state" class="form-control input-sm" placeholder="State">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label>Zip</label>
                                        <input type="text" id="zip" class="form-control input-sm" placeholder="Zip">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Website</label>
                                        <input type="text" id="website" class="form-control input-sm" placeholder="Web Site">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input type="text" id="email" class="form-control input-sm" placeholder="Email">
                                    </div>
                                </div>
                                <input type="hidden" id="userName" value=${pageContext.request.userPrincipal.name} >
                            </div>
                        <button id="addressInfoEditBtn" class="btn btn-primary btn-sm" onclick="editAddressInfo()"><i class="fa fa-edit"></i>Edit</button>
                        <button id="addressInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshAddressInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                        <button id="addressInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveAddressInfo()"><i class="fa fa-save"></i>Save</button>
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
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Cell</label>
                                        <input type="text" id="cell" class="form-control input-sm" placeholder="Primary">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Home</label>
                                        <input type="text" id="home" class="form-control input-sm" placeholder="Home">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label>Fax</label>
                                        <input type="text" id="fax" class="form-control input-sm" placeholder="Fax">
                                    </div>
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
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3><i class="fa fa-ok-circle"></i>General Notes</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label>Note</label>

                                    <textarea class="form-control" id="note" rows="5" cols="150" placeholder="Primary" ></textarea>
                                </div>
                            </div>

                        </div>
                        <button id="noteInfoEditBtn" class="btn btn-primary btn-sm" onclick="editNoteInfo()"><i class="fa fa-edit"></i>Edit</button>
                        <button id="noteInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshNoteInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                        <button id="noteInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveNoteInfo()"><i class="fa fa-save"></i>Save</button>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-md-12">
                <a href="addressBook/print"   target="_blank" class="btn btn-action btn-success btn-sm"><i class="fa fa-print"></i> Print</a>
            </div>
        </div>

    </div>
    <div class="page-footer">
        Â© 2013 Saturn Admin Template.
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

<!-- Grid Scripts -->
<script src="assets/js/grid/dhtmlxcommon.js"></script>
<script src="assets/js/grid/dhtmlxgrid.js"></script>
<script src="assets/js/grid/dhtmlxgridcell.js"></script>
<script src="js/addressBook.js"></script>


<script src='assets/js/template_js/dashboard.js'></script>

<!-- @include _footer