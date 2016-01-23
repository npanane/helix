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

    <title>SiNEP - Campaigns</title>
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
            <div style="padding: 10px">
                    <form:select path="clientDdl" id="client" class="form-control" onchange="loadCampaigns()">
                        <form:options items="${clientDdl}" />
                    </form:select>
            </div>
            <!--<h4 class="text-center" style="color:#ffffff ">Campaign List</h4>-->
            <div style="padding: 10px">
             <div  style="width:100%;height:475px;background-color:#2c3e50;" id="campaignGridbox"> </div>
            </div>
            <br/>
            <div align="right" style="padding: 10px">
                <button class="btn btn-primary btn-sm" onclick="addCampaign()">Create New </button>
                <button id="deleteCampaignBtn" class="btn btn-danger btn-sm" onclick="deleteCampaign()"><i class="fa fa-trash-o"></i>Delete</button>
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
                        <h3><i class="fa fa-ok-circle"></i>Campaign Information</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">
                            <div class="col-md-3">
                                <label>Drive</label>
                                    <form:select path="dropDown1" id="drive" class="form-control">
                                        <form:options items="${dropDown1}" />
                                    </form:select>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Year</label>
                                    <input type="text" id="year" class="form-control input-sm" placeholder="Year">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Form #</label>
                                    <input type="text" id="formNo" class="form-control input-sm" placeholder="Form #">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <label>Type</label>


                                <form:select path="dropDown2" id="type" class="form-control">
                                    <form:options items="${dropDown2}" />
                                </form:select>

                            </div>
                            <input type="hidden" id="userName" value=${pageContext.request.userPrincipal.name} >

                        </div>
                        <br/>
                        <button id="CampaignInfoEditBtn" class="btn btn-primary btn-sm" onclick="editCampaignInfo()"><i class="fa fa-edit"></i>Edit</button>
                        <button id="CampaignInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshCampaignInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                        <button id="CampaignInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveCampaignInfo()"><i class="fa fa-save"></i>Save</button>
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
                        <h3><i class="fa fa-ok-circle"></i>Other Information</h3>
                    </div>
                    <div class="widget-content">
                            <div class="row">
                                <div class="col-md-3">
                                    <h4 class="text-info">Items</h4>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="photo" value="true"> Photo (Received)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="signature" value="true"> Signature (Received)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="badge" value="true"> Badge (Received)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="causes" value="true"> Causes (Received)
                                    </label>

                                </div>
                                <div class="col-md-3">
                                    <h4 class="text-info">Documents</h4>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="letter" value="true"> Letter (Finished)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="rd" value="true"> RD (Finished)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="crm" value="true"> CRM (Finished)
                                    </label>


                                </div>
                                <div class="col-md-3">
                                    <h4 class="text-info">Client Proofs</h4>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="CPLetter" value="true"> Letter (Approved)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="CPrd" value="true"> RD (Approved)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="CPBadge" value="true"> Badge (Approved)
                                    </label>

                                </div>
                                <div class="col-md-3">
                                    <h4 class="text-info">Final Proofs</h4>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="FPLetter" value="true"> Letter (Approved)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="FPrd" value="true"> RD (Approved)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="FPSticker" value="true"> Sticker (Approved)
                                    </label>
                                    <br/>
                                    <label class="checkbox">
                                        <input type="checkbox" id="FPcrm" value="true"> CRM (Approved)
                                    </label>

                                </div>

                            </div>
                        <br/>
                        <button id="OtherInfoEditBtn" class="btn btn-primary btn-sm" onclick="editOtherInfo()"><i class="fa fa-edit"></i>Edit</button>
                        <button id="OtherInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshOtherInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                        <button id="OtherInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveOtherInfo()"><i class="fa fa-save"></i>Save</button>
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
                            <h3><i class="fa fa-ok-circle"></i>Qty. Information</h3>
                        </div>
                        <div class="widget-content">
                            <div class="row">
                                <div class="col-md-3">
                                    <br/> <br/>
                                    <label class="align-right">Amount Mailed :</label>
                                    <br/> <br/>
                                    <label class="align-right">Amount Returned :</label>
                                    <br/> <br/>
                                    <label class="align-right">Percentage Returned :</label>
                                </div>


                                <div class="col-md-3">
                                    <h4 class="text-info">Business</h4>
                                    <div class="form-group">

                                        <input type="text" id="BAmountMailed" class="form-control input-sm" placeholder="Amount Mailed">
                                    </div>
                                    <div class="form-group">

                                        <input type="text" id="BAmountReturned" class="form-control input-sm" placeholder="Amount Returned">
                                    </div>
                                    <div class="form-group">

                                        <input type="text" id="BPercentageReturned" class="form-control input-sm" placeholder="Percentage Returned">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <h4 class="text-info">Resident</h4>
                                    <div class="form-group">

                                        <input type="text" id="RAmountMailed" class="form-control input-sm" placeholder="Amount Mailed">
                                    </div>
                                    <div class="form-group">

                                        <input type="text" id="RAmountReturned" class="form-control input-sm" placeholder="Amount Returned">
                                    </div>
                                    <div class="form-group">

                                        <input type="text" id="RPercentageReturned" class="form-control input-sm" placeholder="Percentage Returned">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <h4 class="text-info">Totals</h4>
                                    <div class="form-group">

                                        <input type="text" id="TAmountMailed" class="form-control input-sm" placeholder="Amount Mailed">
                                    </div>
                                    <div class="form-group">

                                        <input type="text" id="TAmountReturned" class="form-control input-sm" placeholder="Amount Returned">
                                    </div>
                                    <div class="form-group">

                                        <input type="text" id="TPercentageReturned" class="form-control input-sm" placeholder="Percentage Returned">
                                    </div>
                                </div>

                            </div>
                            <br/>
                            <button id="QtyInfoEditBtn" class="btn btn-primary btn-sm" onclick="editQtyInfo()"><i class="fa fa-edit"></i>Edit</button>
                            <button id="QtyInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshQtyInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                            <button id="QtyInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveQtyInfo()"><i class="fa fa-save"></i>Save</button>
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
                        <h3><i class="fa fa-ok-circle"></i>Cost Information</h3>
                    </div>
                    <div class="widget-content">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Letters</label>
                                    <input type="text" id="letters" class="form-control input-sm" placeholder="Letters">
                                </div>
                                <div class="form-group">
                                    <label>Data</label>
                                    <input type="text" id="data" class="form-control input-sm" placeholder="Data">
                                </div>
                                <div class="form-group">
                                    <label>PO Box</label>
                                    <input type="text" id="POBox" class="form-control input-sm" placeholder="PO Box">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Decals</label>
                                    <input type="text" id="decals" class="form-control input-sm" placeholder="decals">
                                </div>
                                <div class="form-group">
                                    <label>Production</label>
                                    <input type="text" id="production" class="form-control input-sm" placeholder="production">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Envelopes</label>
                                    <input type="text" id="envelopes" class="form-control input-sm" placeholder="Envelopes">
                                </div>
                                <div class="form-group">
                                    <label>Artwork</label>
                                    <input type="text" id="artWork" class="form-control input-sm" placeholder="Artwork">
                                </div>
                                <br/>
                                <label class="checkbox">
                                    <input type="checkbox" id="NonProfitBulkRate" value="true"> Non-Profit Bulk Rate
                                </label>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Postage</label>
                                    <input type="text" id="postage" class="form-control input-sm" placeholder="Postage">
                                </div>
                                <div class="form-group">
                                    <label>Pins</label>
                                    <input type="text" id="pins" class="form-control input-sm" placeholder="Pins">
                                </div>

                            </div>

                        </div>
                        <br/>
                        <button id="CostInfoEditBtn" class="btn btn-primary btn-sm" onclick="editCostInfo()"><i class="fa fa-edit"></i>Edit</button>
                        <button id="CostInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshCostInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                        <button id="CostInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveCostInfo()"><i class="fa fa-save"></i>Save</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <a href="campaign/print" target="_blank" type="button" class="btn btn-action btn-success btn-sm"><i class="fa fa-print"></i> Print</a>
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
<script src="js/campaign.js"></script>

<input id="selectedClientId" name="selectedClientId" type="hidden" value="${selectedClientId}"/>
<!-- @include _footer-->
</body>
</html>