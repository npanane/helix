<div class="page-header">
    <div class="header-links hidden-xs">
        <div class="top-search-w pull-right">
            <input type="text" class="top-search" placeholder="Search"/>
        </div>
        <div class="dropdown hidden-sm hidden-xs">
            <a href="#" data-toggle="dropdown" class="header-link"><i class="fa fa-cog"></i> Contacts & Channels</a>

            <ul class="dropdown-menu dropdown-inbar">
                <li><a href="client"><i class="fa fa-cog"></i> Client</a></li>
                <li><a href="vendor"><i class="fa fa-cog"></i> Vendor</a></li>
                <li><a href="addressBook"><i class="fa fa-cog"></i> Address Book</a></li>
            </ul>
        </div>
        <div class="dropdown hidden-sm hidden-xs">
            <a href="#" data-toggle="dropdown" class="header-link"><i class="fa fa-cog"></i> Data Entry</a>

            <ul class="dropdown-menu dropdown-inbar">
                <li><a href="drive"><i class="fa fa-cog"></i> Drives</a></li>
                <li><a href="campaign"><i class="fa fa-cog"></i> Campaign</a></li>
                <li><a href="accountBook"><i class="fa fa-cog"></i> Accounting Books</a></li>
                <li><a href="banking"><i class="fa fa-cog"></i> Banking</a></li>
                <li><a href="todayDeposit"><i class="fa fa-cog"></i> Today's Deposits</a></li>
            </ul>
        </div>
        <div class="dropdown hidden-sm hidden-xs">
            <a href="#" data-toggle="dropdown" class="header-link"><i class="fa fa-cog"></i> Sales</a>

            <ul class="dropdown-menu dropdown-inbar">
                <li><a href="lead"><i class="fa fa-cog"></i> Lead</a></li>
                <li><a href="references"><i class="fa fa-cog"></i> References</a></li>
            </ul>
        </div>
        <div class="dropdown hidden-sm hidden-xs">
            <a href="#" data-toggle="dropdown" class="header-link"><i class="fa fa-cog"></i> Spread Sheets</a>
            <ul class="dropdown-menu dropdown-inbar">
                <li><a href="poBoxPickUps"><i class="fa fa-cog"></i> PO Box PickUps</a></li>
                <li><a href="poBoxReShips"><i class="fa fa-cog"></i> PO Box ReSlip</a></li>
                <li><a href="missingItem"><i class="fa fa-cog"></i> Missing Items</a></li>
            </ul>
        </div>
        <div class="dropdown hidden-sm hidden-xs">
            <a href="#" data-toggle="dropdown" class="header-link"><i class="fa fa-cog"></i> Other</a>
            <ul class="dropdown-menu dropdown-inbar">
                <li><a href="payHours"><i class="fa fa-cog"></i> Pay Hours</a></li>
                <li><a href="suggestions"><i class="fa fa-cog"></i> Suggestions</a></li>
            </ul>
        </div>
        <div class="dropdown">
            <a href="#" class="header-link clearfix" data-toggle="dropdown">
                <div class="avatar">
                    <img src="assets/images/avatar-small.jpg" alt="">
                </div>
                <div class="user-name-w">
                    ${pageContext.request.userPrincipal.name}  <i class="fa fa-caret-down"></i>
                </div>
            </a>
            <ul class="dropdown-menu dropdown-inbar">
                <li><a href="#"><span class="label label-warning">2</span> <i class="fa fa-envelope"></i> Messages</a></li>
                <li><a href="#"><span class="label label-warning">4</span> <i class="fa fa-users"></i> To Do</a></li>
                <li><a href="#"><i class="fa fa-cog"></i> Account Settings</a></li>
                <li><a href="logout"><i class="fa fa-power-off"></i> Logout</a></li>
            </ul>
        </div>
    </div>
    <a class="current logo hidden-xs" href="index.html"><i class="fa fa-rocket"></i></a>
    <a class="menu-toggler" href="#"><i class="fa fa-bars"></i></a>
    <h1>${pageHeader}</h1>
</div>