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

    <title>SiNEP - Drives</title>
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



    </style>
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
        <h4 class="text-center" style="color:#ffffff ">Drive List</h4>
        <div style="padding: 10px">
         <div  style="width:100%;height:475px;background-color:#2c3e50;" id="driveGridbox"> </div>
        </div>
        <br/>
        <div align="right" style="padding: 10px">
            <button class="btn btn-primary btn-sm" onclick="addDrive()">Create New </button>
            <button id="deleteDriveBtn" class="btn btn-danger btn-sm" onclick="deleteDrive()"><i class="fa fa-trash-o"></i>Delete</button>
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
                    <h3><i class="fa fa-ok-circle"></i>Drive Information</h3>
                </div>
                <div class="widget-content">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Description</label>
                                    <input type="text" id="description" class="form-control input-sm" placeholder="Description">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <label>Month</label>

                                <select class="form-control" id="month">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select>

                            </div>
                            <div class="col-md-2">
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Year</label>
                                    <input type="text" id="year" class="form-control input-sm" placeholder="yyyy">



                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Total Amount</label>
                                    <input type="text" id="totalAmount" class="form-control input-sm" placeholder="Total Amount">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Total Count</label>
                                    <input type="text" id="totalCount" class="form-control input-sm" placeholder="Total Count">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Total CC Amount</label>
                                    <input type="text" id="totalCCAmount" class="form-control input-sm" placeholder="Total CC Amount">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Average Amount</label>
                                    <input type="text" id="averageAmount" class="form-control input-sm" placeholder="Average Amount">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Response %</label>
                                    <input type="text" id="Response" class="form-control input-sm" placeholder="Response %">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    </br>
                                    <label class="checkbox-inline">
                                        <input type="checkbox" id="inProcess" >&nbsp; &nbsp; In Process
                                    </label>
                                </div>
                            </div>

                            <input type="hidden" id="userName" value=${pageContext.request.userPrincipal.name} >
                        </div>

                    <br/>
                    <button id="DriveInfoEditBtn" class="btn btn-primary btn-sm" onclick="editDriveInfo()"><i class="fa fa-edit"></i>Edit</button>
                    <button id="DriveInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshDriveInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                    <button id="DriveInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveOrUpdateDriveInfo()"><i class="fa fa-save"></i>Save</button>
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
                    <h3><i class="fa fa-ok-circle"></i>Campaign List</h3>
                </div>
                <div class="widget-content">
                    <div class="row">

                        <div style="padding: 10px" >
                            <div  style="width:100%; height:200px;background-color:#ffffff;" id="CampaignGridbox"> </div>
                            <br/>
                            <div class="btn btn-primary btn-sm" id="AddNewCampaignInfoBtn" data-toggle="modal" data-target="#createNewCampaignInfo"><i class="fa fa-user"></i>Add New Campaign</div>
                            <div class="btn btn-success btn-sm" id="CampaignInfoSaveBtn" data-toggle="modal" data-target="#editCampaignInfo"><i class="fa fa-edit"></i>Edit</div>
                            <button id="CampaignInfoDeleteBtn" class="btn btn-danger btn-sm" onclick="deleteCampaignInfo()"><i class="fa fa-save"></i>Delete</button>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <a href="drive/print" target="_blank" class="btn btn-action btn-success btn-sm"><i class="fa fa-print"></i> Print</a>
        </div>
    </div>

</div>
<div class="page-footer">
    Â© 2013 Saturn Admin Template.
</div>
</div>


<%--
<!--Modal for creating new vendor contacts -->

<div class="modal fade" id="createNewCampaignInfo" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">
                        <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                    </div>
                    <h3><i class="fa fa-ok-circle"></i> Create New Campaign</h3>
                </div>
                <div class="widget-content">
                    <div class="modal-body">
                        <div class="row">

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Edit</label>
                                    <input id="CCfirstName" type="text"  class="form-control" placeholder="First Name">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input id="CClastName" type="text"  class="form-control" placeholder="Last Name">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Title</label>
                                    <input id="CCtitle" type="text"  class="form-control" placeholder="Title">
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
                            <div class="col-md-1">
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Home Phone</label>
                                    <input id="CCHome" type="text"  class="form-control" placeholder="Home Phone">
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
                            <div class="col-md-1">
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Other Phone</label>
                                    <input id="CCOther" type="text"  class="form-control" placeholder="Other Phone">
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

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input id="CCEmail" type="text"  class="form-control" placeholder="Email">
                                </div>
                            </div>
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


<!--Modal for edit vendor contacts -->

<div class="modal fade" id="editCampaignInfo" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
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

                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input id="editCCfirstName" type="text"  class="form-control" placeholder="First Name">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input id="editCClastName" type="text"  class="form-control" placeholder="Last Name">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>Title</label>
                                    <input id="editCCtitle" type="text"  class="form-control" placeholder="Title">
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

                            <div class="col-md-1">
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Home Phone</label>
                                    <input id="editCCHome" type="text"  class="form-control" placeholder="Home Phone">
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
                            <div class="col-md-1">
                            </div>
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label>Other Phone</label>
                                    <input id="editCCOther" type="text"  class="form-control" placeholder="Other Phone">
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

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Email</label>
                                    <input id="editCCEmail" type="text"  class="form-control" placeholder="Email">
                                </div>
                            </div>
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
                        <button type="submit" class="btn btn-primary" id="editContatctInfoBtn2" onclick="editContactInfo()" ><i class="fa fa-save"></i>Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>Close</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>--%>

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
<script src="js/drive.js"></script>


<script src='assets/js/template_js/dashboard.js'></script>

<input type="hidden" id="selectedClientId" class="form-control input-sm" placeholder="Description">

<!-- @include _footer-->
</body>
</html>