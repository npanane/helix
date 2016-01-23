<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="row">
<div class="col-md-12">
    <div class="widget widget-blue">
        <div class="widget-title">
            <div class="widget-controls">
                <a href="#" class="widget-control widget-control-full-screen widget-control-show-when-full" data-toggle="tooltip" data-placement="left" title="" data-original-title="Exit Full Screen"><i class="fa fa-expand"></i></a>
                <a href="#" class="widget-control widget-control-refresh" data-toggle="tooltip" data-placement="top" title="" data-original-title="Refresh"><i class="fa fa-refresh"></i></a>
                <a href="#" class="widget-control widget-control-minimize" data-toggle="tooltip" data-placement="top" title="" data-original-title="Minimize"><i class="fa fa-minus-circle"></i></a>
            </div>
            <h3><i class="fa fa-ok-circle"></i>Post Office Information</h3>
        </div>
        <div class="widget-content">
            <div class="row">
                <div class="col-md-2">
                    <label>PickUp Method</label>

                        <select class="form-control" id="pickUpMethod">
                            <option value="1">Drivable</option>
                            <option value="2">ReShip</option>
                            <option value="3">Client Delivered</option>

                        </select>

                </div>
                <div class="col-md-2"></div>
                <div class="col-md-3">
                    <label>Last Called</label>
                    <div class="input-group">
                        <input type="text" id="lastCalled" placeholder="mm-dd-yyyy" data-date-format="mm-dd-yyyy"  class="form-control input-datepicker">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <label>Last PickUp</label>
                    <div class="input-group">
                        <input type="text" id="lastPickUp" placeholder="mm-dd-yyyy" data-date-format="mm-dd-yyyy" class="form-control input-datepicker">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>

            </div>
            <br/>
            <div class="row">
                <div class="col-md-3">
                        <label>Last Paid On</label>
                        <div class="input-group">
                            <input type="text" id="LastPaidOn" placeholder="mm-dd-yyyy" data-date-format="mm-dd-yyyy" class="form-control input-datepicker">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-3">
                        <label>Expiration Date</label>
                        <div class="input-group">
                            <input type="text" id="expirationDate" placeholder="mm-dd-yyyy" data-date-format="mm-dd-yyyy" class="form-control input-datepicker">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-2">
                        <label>Renewal Period</label>

                        <select class="form-control" id="renewalPeriod">
                            <option value="0">-- Select --</option>
                            <option value="6">6 Months</option>
                            <option value="12">12 Months</option>
                        </select>

                </div>
            </div>
            <br/>
            <div class="row">
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Box Size</label>
                        <input type="text" id="boxSize" class="form-control input-sm" placeholder="Box Size">
                    </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Renewal Cost</label>
                        <input type="text" id="renewalCost" class="form-control input-sm" placeholder="Renewal Cost">
                    </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Ghost #</label>
                        <input type="text" id="ghost" class="form-control input-sm" placeholder="Ghost #">
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-md-7">
                    <div class="form-group">
                        <label>Notes</label>
                        <input type="text" id="notes" class="form-control input-sm" placeholder="Notes">
                    </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <br/>
                    <label class="checkbox">
                        <input type="checkbox" id="tempHideAlert" value="1"> Temp Hide Alert
                    </label>
                </div>

            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Post Office Address</label>
                        <input type="text" id="postOfficeAddress" class="form-control input-sm" placeholder="Post Office Address">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>City</label>
                        <input type="text" id="POcity" class="form-control input-sm" placeholder="City">
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label>State</label>
                        <!--<input type="text" id="POstate" class="form-control input-sm" placeholder="State">-->
                        <form:select path="mailingAddStateDdl" id="POstate" class="form-control">
                            <form:options items="${mailingAddStateDdl}" />
                        </form:select>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Zip</label>
                        <input type="text" id="POzip" class="form-control input-sm" placeholder="Zip">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Post Office Phone</label>
                        <input type="text" id="postOfficePhone" class="form-control input-sm" placeholder="Post Office Phone">
                    </div>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Contact 1</label>
                        <input type="text" id="contact1" class="form-control input-sm" placeholder="Contact1">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Post Office Fax</label>
                        <input type="text" id="postOfficeFax" class="form-control input-sm" placeholder="Post Office Fax">
                    </div>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Contact 2</label>
                        <input type="text" id="contact2" class="form-control input-sm" placeholder="Contact2">
                    </div>
                </div>
                <div>
                    <input type="hidden" id="IdPostOffice">
                    <input type="hidden" id="postAddressId">
                    <input type="hidden" id="postContactId">
                    <input type="hidden" id="postPickUpId">
                </div>


            </div>
            <button id="postOfficeInfoEditBtn" class="btn btn-primary btn-sm" onclick="editPostOfficeInfo()"><i class="fa fa-edit"></i>Edit</button>
            <button id="postOfficeInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshPostOfficeInfo()"><i class="fa fa-refresh"></i>Refresh</button>
            <button id="postOfficeInfoSaveBtn" class="btn btn-success btn-sm" onclick="savePostOfficeInfo()"><i class="fa fa-save"></i>Save</button>
            <span class="post-office-client-err" style="color:red;"></span>
        </div>
    </div>
</div>

</div>
