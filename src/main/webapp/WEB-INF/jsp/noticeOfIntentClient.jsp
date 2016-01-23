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
            <h3><i class="fa fa-ok-circle"></i>Notice Of Intent</h3>
        </div>
        <div class="widget-content">
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Registration Number</label>
                        <input type="text" id="regNum" class="form-control input-sm" placeholder="Registration Number">
                    </div>
                </div>
                <div class="col-md-3">
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" id="NOIemail" class="form-control input-sm" placeholder="Email">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Activity</label>
                        <input type="text" id="activity" class="form-control input-sm" placeholder="Activity">
                    </div>
                </div>
                <div class="col-md-3">
                </div>
                <div class="col-md-3">
                    <label>Beginning Date</label>
                    <div class="input-group">
                        <input type="text" id="beginningDate" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd" class="form-control input-datepicker">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <label class="checkbox">
                        <input type="checkbox" id="NOItelemarketing" value="1"> Telemarketing
                    </label>
                </div>
                <div class="col-md-4">
                </div>
                <div class="col-md-3">
                    <label>Ending Date</label>
                    <div class="input-group">
                        <input type="text" id="endingDate" placeholder="yyyy-mm-dd" data-date-format="yyyy-mm-dd" class="form-control input-datepicker">
                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                    </div>
                </div>
                <div>
                    <input type="hidden" id="IdNoticeOfIntent">
                </div>
            </div>
            <br/>
            <button id="NOIInfoEditBtn" class="btn btn-primary btn-sm" onclick="editNOIInfo()"><i class="fa fa-edit"></i>Edit</button>
            <button id="NOIInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshNOIInfo()"><i class="fa fa-refresh"></i>Refresh</button>
            <button id="NOIInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveNOIInfo()"><i class="fa fa-save"></i>Save</button>
            <span class="notice-of-intent-client-msg" style="color:red;"></span>
        </div>
    </div>
</div>

</div>
