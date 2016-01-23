<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
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

    <title>Pay Hours</title>

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
    </div>
    <div class="main-content" style="margin-left: 60px;">

        <div class="row">
            <div class="col-md-1">
            </div>
            <div class="col-md-12">
                <div class="widget widget-blue">
                    <div class="widget-title">
                        <div class="widget-controls">
                            <a href="#" class="widget-control widget-control-full-screen" data-toggle="tooltip" data-placement="top" title="" data-original-title="Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                            <a href="#" data-toggle="dropdown" class="widget-control widget-control-settings"><i class="fa fa-cog"></i></a>
                            <div class="dropdown" data-toggle="tooltip" data-placement="top" title="" data-original-title="Settings">
                                <ul class="dropdown-menu dropdown-menu-small" role="menu" aria-labelledby="dropdownMenu1">
                                    <li class="dropdown-header">Set Widget Color</li>
                                    <li><a data-widget-color="blue" class="set-widget-color" href="#">Blue</a></li>
                                    <li><a data-widget-color="red" class="set-widget-color" href="#">Red</a></li>
                                    <li><a data-widget-color="green" class="set-widget-color" href="#">Green</a></li>
                                    <li><a data-widget-color="orange" class="set-widget-color" href="#">Orange</a></li>
                                    <li><a data-widget-color="purple" class="set-widget-color" href="#">Purple</a></li>
                                </ul>
                            </div>
                            <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                            <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                        </div>
                        <h3>Missing Items</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">
                            <div>
                                <div  style="width:100%; height:100px;background-color:#ffffff;" id="referencesGridbox"> </div>
                                <br/>
                                <div class="btn btn-primary btn-sm" id="AddNewReferenceInfoBtn" data-toggle="modal" data-target="#createNewReference"><i class="fa fa-user"></i>Save</div>
                                <div class="btn btn-success btn-sm" id="ReferenceInfoEditBtn" data-toggle="modal" data-target="#editReference"><i class="fa fa-edit"></i>Cancel</div>
                                <a href="references/print" target="_blank" class="btn btn-primary btn-sm"><i class="fa fa-print"></i>Print</a>
                            </div>

                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
    <div class="page-footer">
        Â© 2013 Saturn Admin Template.
    </div>
</div>


<!--Modal for add new reference-->

<div class="modal fade" id="createNewReference" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">
                        <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                    </div>
                    <h3><i class="fa fa-ok-circle"></i> Create New Reference</h3>
                </div>
                <div class="widget-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-7">
                                <label>Name</label>

                                <form:select path="dropDown1" id="clientName" onchange="getClientContacts()" class="form-control">
                                    <form:options items="${dropDown1}" />
                                </form:select>

                            </div>

                        </div>
                        <br/>
                        <div class="row">


                            <div class="col-md-4">
                                <label>Contact</label>

                                <input type="text" class="form-control" id="contactName" list="contactNames" placeholder="Contact Name" onchange="getPhonesNo()">

                                <datalist id="contactNames">

                                </datalist>

                            </div>


                        </div>


                        <br/>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input type="text" id="phone" class="form-control " placeholder="phone">
                                </div>

                            </div>

                        </div>

                        <div class="row">
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label>Zip</label>
                                    <input type="text" id="zip" class="form-control " placeholder="zip">
                                </div>

                            </div>

                        </div>
                        <input type="hidden" id="userName" value=${pageContext.request.userPrincipal.name} >
                        </br>
                        <button type="submit" class="btn btn-primary" id="saveReferenceBtn" onclick="saveReference()" ><i class="fa fa-save"></i>Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>Close</button>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<!--Modal for edit reference-->

<div class="modal fade" id="editReference" tabindex="-1" role="dialog" aria-labelledby="modalFormStyle1Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="widget widget-blue">
                <div class="widget-title">
                    <div class="widget-controls">
                        <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                        <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
                    </div>
                    <h3><i class="fa fa-ok-circle"></i> Edit Reference</h3>
                </div>
                <div class="widget-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-7">
                                <label>Name</label>

                                <form:select path="dropDown1" onchange="getClientContactsER()" id="clientNameER" class="form-control">
                                    <form:options items="${dropDown1}" />
                                </form:select>

                            </div>

                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-7">
                                <label>Contact</label>
                                <input type="text" class="form-control" id="contactNameER"   list="contactNamesER" onchange="getPhonesNoER()"  placeholder="Contact Name" >

                                <datalist id="contactNamesER">

                                </datalist>
                            </div>

                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input type="text" id="phoneER" class="form-control" placeholder="phone">
                                </div>

                            </div>

                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Zip</label>
                                    <input type="text" id="zipER" class="form-control" placeholder="zip">
                                </div>
                            </div>

                        </div>
                        </br>
                        <button type="submit" class="btn btn-primary" id="editReferenceBtn" onclick="editReference()" ><i class="fa fa-save"></i>Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i>Close</button>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<script>


</script>

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
<script src="js/missingItem.js"></script>

<!-- @include _footer--!>