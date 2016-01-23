<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Contact Manager</title>
    <jsp:include page="includes.jsp"></jsp:include>
    <script src="js/contact.js"></script>
    <link rel="stylesheet" type="text/css" href="css/contact.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css">

    <style>
        div.gridbox_dhx_skyblue table.hdr td {
            font-family: 'Open Sans', arial;
            font-size: 12px;
            color: black;
            text-align: left;
        }

        div.gridbox_dhx_skyblue table.obj tr td {
            font-family: 'Open Sans', arial;
            font-size: 12px;
            border-width: 0px 0px 0px 0px;
            padding-right: 4px;
            padding-left: 4px;
        }
    </style>

</head>
<body>
<form:form method="post" action="contact" commandName="contact" enctype="multipart/form-data">
    <div id="header" class="header" style ="z-index: 1">
        <div class="header">
            <div class="header-logo" style="height:41px;">
                <span style="margin-left:10px; font:16px/25px 'Open Sans',arial,sans-serif; color:#003B81; font-size:20px;">H E L I X System</span>
                <!--<img style="height:41px;" alt="Helix" src="web/images/logo.png">-->
            </div>
            <div class="nav-bar">
                <div class="nav-content">
                    <ul>
                        <li class="active"><a href="#">Master Data</a>
                            <ul>
                                <div class="menu-section">
                                    <li class="menu-header"><a href="#">Address Book</a></li>
                                    <li><a href="contact">Contacts</a></li>
                                    <li><a href="client">Clients</a></li>
                                    <li><a href="vendor">Vendors</a></li>
                                    <li><a href="staff">Staff</a></li>
                                </div>
                                <div class="menu-section">
                                    <li class="menu-header"><a href="#">Campaign</a></li>
                                    <li><a href="#">Drives</a></li>
                                    <li><a href="#">Campaigns</a></li>
                                    <li><a href="#">Express Mail</a></li>
                                    <li><a href="#">&nbsp;</a></li>
                                </div>
                                <div class="menu-section">
                                    <li class="menu-header"><a href="#">Donations</a></li>
                                    <li><a href="#">Auto Donations</a></li>
                                    <li><a href="#">Client Donations</a></li>
                                    <li><a href="#">Global Donations</a></li>
                                    <!--<li><a href="previousDonatorSearch">Previous Donator Search</a></li>-->
                                </div>
                                <div class="menu-section">
                                    <li class="menu-header"><a href="#">Removals</a></li>
                                    <li><a href="#">Auto Removals</a></li>
                                    <li><a href="#">Client Removals</a></li>
                                    <li><a href="#">Global Removals</a></li>
                                </div>
                                <div class="menu-section">
                                    <li class="menu-header"><a href="#">Accounting</a></li>
                                    <li><a href="#">Books</a></li>
                                    <li><a href="#">Banking</a></li>
                                    <li><a href="#">Deposit Slip</a></li>
                                    <li><a href="#">Today's Deposits</a></li>
                                </div>
                                <div class="menu-section">
                                    <li class="menu-header"><a href="#">Accounting Utilities</a></li>
                                    <li><a href="#">Book Creator</a></li>
                                    <li><a href="#">Client Invoice Creator</a></li>
                                    <li><a href="#">Shipping & Correspndence</a></li>
                                    <li><a href="#">Split Profits</a></li>
                                </div>
                            </ul>
                        </li>
                        <li><a href="#">Sales</a></li>
                        <li><a href="#">Reports</a></li>
                        <li><a href="#">Spreadsheets</a></li>
                        <li><a href="#">Utilities</a></li>
                        <li><a href="#">Help</a></li>
                        <li><a href="logout">Sign Out</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div id = "bodyContent" class="form-box-container" style="padding-top:100px;">
        <h2><b>Manage Address Book</b></h2>
        <div style="width:20%; float: left">
            <div id="gridbox" style="width:99%;height:100%;background-color:white;"></div>
        </div>
        <div style="width:80%; float: left;">
            <div class="toggle-layer">
                <p class="search-heading">Search</p>
                <div class="search-content">
                    <div style="margin-bottom: 5px;">
                        <span class="search-txt">First Name</span><input type="text" id="fName">
                        <span class="search-txt">Last Name</span><input type="text" id="lName">
                        <span class="search-txt">Phone No.</span><input type="text" id="phoneNo">
                        <input type="button" id="search" value="Search">
                    </div>
                </div>

                <p class="client-heading">Contact Info</p>
                <div class="client-content">
                    <form:hidden path="contactId"></form:hidden>
                    <div id="errorSummary" class="ui-state-error ui-corner-all" style="padding: 0 .2em; display: none;">
                        <p><form:errors path="*"></form:errors></p>
                    </div>
                    <div style="float:left; width:100%;">
                        <div style="width: 33%; float: left;">
                            <div class="label">First Name</div>
                            <div><form:input path="firstName" size="40"></form:input></div>
                        </div>
                        <div style="width: 33%; float: left">
                            <div class="label">Last Name</div>
                            <div><form:input path="lastName" size="40"></form:input></div>
                        </div>
                        <div style="width: 33%; float: left">
                            <div class="label">Title</div>
                            <div><form:input path="title" size="40"></form:input></div>
                        </div>
                        <div style="width: 33%; float: left">
                            <div class="label">Primary Email</div>
                            <div><form:input path="primaryEmail" size="40"></form:input></div>
                        </div>
                        <div style="width: 33%; float: left">
                            <div class="label">Secondary Email</div>
                            <div><form:input path="secondaryEmail" size="40"></form:input></div>
                        </div>
                        <div style="width: 70%; float: left">
                            <div class="label">Notes</div>
                            <div><form:textarea path="note" cols="3" style="margin-left: 2px; margin-right: 2px; width: 571px;"></form:textarea></div>
                        </div>
                    </div>
                    <div>&nbsp;</div>
                </div>
                <p class="phone-heading">Phone</p>
                <div class="client-content">
                    <div style="float:left; width:100%;">
                        <div style="width: 33%; float: left;">
                            <div class="label">Office</div>
                            <div><form:input path="officePhoneNo" size="40"></form:input></div>
                        </div>
                        <div style="width: 33%; float: left">
                            <div class="label">Home</div>
                            <div><form:input path="homePhoneNo" size="40"></form:input></div>
                        </div>
                        <div style="width: 33%; float: left">
                            <div class="label">Mobile</div>
                            <div><form:input path="mobileNo" size="40"></form:input></div>
                        </div>
                        <div style="width: 33%; float: left">
                            <div class="label">Main</div>
                            <div><form:input path="mainPhoneNo" size="40"></form:input></div>
                        </div>
                        <div style="width: 33%; float: left">
                            <div class="label">Work Fax</div>
                            <div><form:input path="workFaxNo" size="40"></form:input></div>
                        </div>
                        <div style="width: 33%; float: left">
                            <div class="label">Home Fax</div>
                            <div><form:input path="homeFaxNo" size="40"></form:input></div>
                        </div>
                    </div>
                    <div class="grid-button-container" style="width: 100%">
                        <input type="button" id="associateBtn" value="Associate" class="formButton" onclick="associateOnClick()"/>
                        <input type="submit" id="submitBtn" value="Save" class="formButton" onclick="return submitOnClick()" />
                        <input type="button" id="clearBtn" value="Clear" class="formButton" onclick=""/>
                    </div>
                </div>


                <p class="address-heading">Address</p>
                <div class="address-content">
                    <div id="addressGridbox" style="width:99%;height:150px;background-color:white;"></div>
                    <div class="grid-button-container">
                        <input type="button" id="addAddressBtn" value="Add" class="formButton" onclick="addAddress()"/>
                        <input type="button" id="saveAddressBtn" value="Save" class="formButton" onclick="saveAddress()"/>
                        <input type="button" id="editAddressBtn" value="Edit" class="formButton" onclick="editAddress()"/>
                        <input type="button" id="deleteAddressBtn" value="Delete" class="formButton" onclick="deletecAddress()"/>
                    </div>
                </div>
            </div>
        </div>


    </div>
</form:form>
</body>
<script>
    /* Lead menu */
    $('.active').hover(function () {
        if($('.active ul').css('display') == 'none'){
            $('.active ul').fadeIn();
        }
        else{
            $('.active ul').fadeOut();
        }
    })
</script>
</html>
