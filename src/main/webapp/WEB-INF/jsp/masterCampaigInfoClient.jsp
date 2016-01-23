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
                <h3><i class="fa fa-ok-circle"></i>Master Campaign Information</h3>
            </div>
            <div class="widget-content">
                <div class="row">
                    <div class="col-md-1">
                        <div class="form-group">
                            <label>Rank</label>
                            <input type="text" id="rank" class="form-control input-sm" placeholder="Rank">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>First Name</label>
                            <input type="text" id="firstName" class="form-control input-sm" placeholder="First Name">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>Last Name</label>
                            <input type="text" id="lastName" class="form-control input-sm" placeholder="Last Name">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Title</label>
                            <input type="text" id="title" class="form-control input-sm" placeholder="Title">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" id="phone" class="form-control input-sm" placeholder="Phone">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <br/>
                        <label class="checkbox">
                            <input type="checkbox" id="NEPVoicemail" value="true"> NEP Voicemail
                        </label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Address</label>
                            <input type="text" id="MCaddress" class="form-control input-sm" placeholder="Address">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>City</label>
                            <input type="text" id="MCcity" class="form-control input-sm" placeholder="City">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>State</label>
                            <!--<input type="text" id="MCstate" class="form-control input-sm" placeholder="State">-->
                            <form:select path="masterCampaignStateDdl" id="MCstate" class="form-control">
                                <form:options items="${masterCampaignStateDdl}" />
                            </form:select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Zip</label>
                            <input type="text" id="MCzip" class="form-control input-sm" placeholder="Zip">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Tax-ID</label>
                            <input type="text" id="taxID" class="form-control input-sm" placeholder="Tax-ID">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Friends Of</label>
                            <input type="text" id="friendsOf" class="form-control input-sm" placeholder="Friends Of">
                        </div>
                    </div>
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Season</label>
                            <select class="form-control" id="season">
                                <option>Spring</option>
                                <option>Summer</option>
                                <option>Fall</option>
                                <option>Winter</option>

                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Client Website</label>
                            <input type="text" id="clientWebsite" class="form-control input-sm" placeholder="Client Website">
                        </div>
                    </div>
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Base Form #</label>
                            <input type="text" id="baseForm" class="form-control input-sm" placeholder="Base Form">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Donation Website</label>
                            <input type="text" id="donationWebsite" class="form-control input-sm" placeholder="Donation Website">
                        </div>
                    </div>
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Letter Type </label>
                            <input type="text" id="letterType" class="form-control input-sm" placeholder="Letter Type">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Picture Title</label>
                            <input type="text" id="pictureTitle" class="form-control input-sm" placeholder="Picture Title">
                        </div>
                    </div>
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Client % </label>
                            <input type="text" id="client" class="form-control input-sm" placeholder="Client %">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Bank Acct.#</label>
                            <input type="text" id="bankAcct" class="form-control input-sm" placeholder="Bank Acct">
                        </div>
                    </div>
                    <div class="col-md-2">
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">

                            <label class="checkbox-inline">
                                <input type="checkbox" id="taxDeductible" value="true">Tax Deductible &nbsp; &nbsp;
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Location.#</label>
                            <input type="text" id="location" class="form-control input-sm" placeholder="Location">
                        </div>
                    </div>
                    <div >
                        <input type="hidden" id="IdCampaign" >
                        <input type="hidden" id="campaignContactId" >
                        <input type="hidden" id="campaignAddressId" >
                    </div>
                </div>

                <button id="campaignInfoEditBtn" class="btn btn-primary btn-sm" onclick="editCampaignInfo()"><i class="fa fa-edit"></i>Edit</button>
                <button id="campaignInfoCancelBtn" class="btn btn-default btn-sm" onclick="refreshCampaignInfo()"><i class="fa fa-refresh"></i>Refresh</button>
                <button id="campaignInfoSaveBtn" class="btn btn-success btn-sm" onclick="saveClientCampaignInfo()"><i class="fa fa-save"></i>Save</button>
                <span class="master-ampaign-err" style="color:red;"></span>
            </div>
        </div>
    </div>

</div>

