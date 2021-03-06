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

    <title>Responsive Admin template based on Bootstrap 3</title>

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

<div class="alert alert-warning alert-dismissable bottom-margin">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true"></button>
    <i class="fa fa-exclamation-circle"></i> <strong>Welcome!</strong> to Helix
</div>

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
        <h3><i class="fa fa-table"></i>Current Events</h3>
    </div>
    <div class="widget-content">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th></th>
                    <th>Client</th>
                    <th>Note</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td></td>
                    <td>Demo Client Title</td>
                    <td>Mario Speedwagon</td>
                </tr>
                <tr>
                    <td></td>
                    <td>Demo Client Title</td>
                    <td>Petey Cruiser</td>
                </tr>
                <tr>
                    <td></td>
                    <td>Demo Client Title</td>
                    <td>Anna Sthesia</td>
                </tr>
                <tr class="success">
                    <td></td>
                    <td>Demo Client Title</td>
                    <td>Paul Molive</td>
                </tr>
                <tr>
                    <td></td>
                    <td>Demo Client Title</td>
                    <td>Anna Mull</td>
                </tr>
                <tr>
                    <td></td>
                    <td>Demo Client Title</td>
                    <td>Gail Forcewind</td>
                </tr>
                <tr>
                    <td></td>
                    <td>Demo Client Title</td>
                    <td>Paige Turner</td>
                </tr>
                <tr class="warning">
                    <td></td>
                    <td>Demo Client Title</td>
                    <td>Bob Frapples</td>
                </tr>
                <tr>
                    <td></td>
                    <td>Demo Client Title</td>
                    <td>Walter Melon</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

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
        <h3><i class="fa fa-table"></i>Pending Data Entry</h3>
    </div>
    <div class="widget-content">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>DE Level</th>
                    <th>Credit Cards</th>
                    <th>Add Donation</th>
                    <th>Priority</th>
                    <th>Founder's Circle</th>
                    <th>Removals</th>
                    <th>$ Amount</th>
                    <th>Num Remain</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>1.00</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>1.00</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>1.00</td>
                    <td>1</td>
                </tr>
                <tr class="success">
                    <td>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>1.00</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>1.00</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>1.00</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>1.00</td>
                    <td>1</td>
                </tr>
                <tr class="warning">
                    <td>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>1.00</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>1.00</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td colspan='6' style="text-align:right">Totals</td>
                    <td>1.00</td>
                    <td>1</td>
                <tr>
                </tbody>
            </table>
        </div>
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

<script>
    mygrid = new dhtmlXGridObject('sub-sidebar-wrapper');
    mygrid.setImagePath("../../assets/images/imgs/");
    mygrid.setHeader("Vendor List");
    mygrid.setInitWidths("395");
    mygrid.setColAlign("left");
    mygrid.init();
    mygrid.enableAutoHeight(true);
    mygrid.setSkin("dhx_skyblue");
</script>

<script src='assets/js/template_js/dashboard.js'></script>

<!-- @include _footer--!>
