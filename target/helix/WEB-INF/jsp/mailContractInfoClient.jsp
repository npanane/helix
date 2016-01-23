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
                <h3><i class="fa fa-ok-circle"></i>Mail Contract Information</h3>
            </div>
            <div class="widget-content">
                <div class="row">
                    <div class="col-md-3">
                        <label>Commencement Date</label>
                        <div class="input-group">
                            <input type="text" id="commencementDate" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd"  class="form-control input-datepicker">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <label>Termination Date</label>
                        <div class="input-group">
                            <input type="text" id="terminationDate" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd"  class="form-control input-datepicker">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Termination Buffer(days)</label>
                            <input type="text" id="terminationBuff" class="form-control input-sm" placeholder="Termination Buffer(days)">
                            <p id="terminationBuffVali"></p>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <br/>
                        <label class="checkbox">
                            <input type="checkbox" id="charity" value="1"> Charity
                        </label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                    <label>Renew Period</label>

                    <select class="form-control" id="renewPeriod">
                        <option value="0">-- Select --</option>
                        <option value="1">None</option>
                        <option value="2">1 Year</option>
                        <option value="3">2 Years</option>
                        <option value="4">3 Years</option>
                        <option value="5">4 Years</option>
                        <option value="6">5 Years</option>
                    </select>

                    </div>
                    <div class="col-md-3"></div>
                    <div class="col-md-3">
                        <br/>
                        <label class="checkbox">
                            <input type="checkbox" id="telemarketing" value="1"> Telemarketing
                        </label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2">
                        <label>Renew Type</label>
                        <form:select path="renewTypeDdl" id="renewType" class="form-control">
                            <form:options items="${renewTypeDdl}" />
                        </form:select>
                    </div>
                    <div class="col-md-3"></div>
                    <div class="col-md-3">
                        <br/>
                        <label class="checkbox">
                            <input type="checkbox" id="hideContractAlerts" value="1"> Hide Contract Alerts
                        </label>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-md-3">
                        <label class="checkbox">
                            <input type="checkbox" id="cancellationLetterReceived" value="1"> Cancellation Letter Received
                        </label>
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <label>Cancellation Date</label>
                        <div class="input-group">
                            <input type="text" id="cancellationDate"  placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd"  class="form-control input-datepicker">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        </div>
                    </div>
                </div>
                <br/>
                <button id="mailConInfoEditBtn" class="btn btn-primary btn-sm" onclick="editMailConInfo()"><i class="fa fa-edit"></i>Edit</button>
                <button id="mailConInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshMailConInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                <button id="mailConInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveMailConInfo()"><i class="fa fa-save"></i>Save</button>
                <span class="main-contact-client-err" style="color:red;"></span>
            </div>
        </div>
    </div>

</div>
