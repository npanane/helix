<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Product Manager</title>
    <jsp:include page="includes.jsp"></jsp:include>
    <script src="admin/js/product.js"></script>

    <style type="text/css">
        .header-logo img {
            margin-left: 20px;
        }
    </style>

</head>
<body onload="init();">
<form:form method="post" action="product" commandName="product" enctype="multipart/form-data">
    <div class="header" style ="z-index: 1">
        <div class="header">
            <div class="header-logo">
                <img alt="Google" src="../../web/images/logo.png">
            </div>
            <div class="nav-bar">
                <div class="nav-content">
                    <ul>
                        <li class="active"><a href="#">Master Data</a>
                            <ul>
                                <div class="menu-section">
                                    <li><a href="product">Books & Reference</a></li>
                                    <li><a href="#">Business</a></li>
                                    <li><a href="#">Comics</a></li>
                                    <li><a href="#">Communication</a></li>
                                    <li><a href="#">Education</a></li>
                                    <li><a href="#">Entertainment</a></li>
                                    <li><a href="#">Finance</a></li>
                                    <li><a href="#">Health & Fitness</a></li>
                                    <li><a href="#">Live Wallpaper</a></li>
                                </div>
                                <div class="menu-section">
                                    <li class="menu-header"><a href="#">Books & Reference</a></li>
                                    <li><a href="#">Business</a></li>
                                    <li><a href="#">Comics</a></li>
                                    <li><a href="#">Communication</a></li>
                                    <li><a href="#">Education</a></li>
                                    <li><a href="#">Entertainment</a></li>
                                    <li><a href="#">Finance</a></li>
                                    <li><a href="#">Health & Fitness</a></li>
                                    <li><a href="#">Lifestyle</a></li>
                                </div>
                            </ul>
                        </li>
                        <li><a href="#">Send Money</a></li>
                        <li><a href="#">Shop in Stores</a></li>
                        <li><a href="#">Buy Online</a></li>
                        <li><a href="#">Stay Safe</a></li>
                        <li><a href="/logout">Sign Out</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="form-box-container" style="padding-top:100px;">
        <h2>Product Manager</h2>
        <div id="viewClientFieldset" class="sub-container-box" style="height: 308px">
            <div id="gridbox" style="width:99%;height:275px;background-color:white;"></div>
            <div class="grid-button-container">
                <input type="button" id="addBtn" value="Add" class="formButton" onclick="add()"/>
                <input type="button" id="editBtn" value="Edit" class="formButton" onclick="edit()"/>
                <input type="button" id="deleteBtn" value="Delete" class="formButton" onclick="deletecClient()"/>
            </div>
        </div>
        <div>&nbsp;</div>
        <div class="sub-container-box">
            <form:hidden path="productId"></form:hidden>
            <form:hidden path="info"></form:hidden>
            <div id="errorSummary" class="ui-state-error ui-corner-all" style="padding: 0 .2em; display: none;">
                <p><form:errors path="*"></form:errors></p>
            </div>
            <!-- Column 1 -->
            <div style="float: left;width: 49%" class="sub-container-box">
                <div>
                    <div class="label">Category</div>
                    <div>
                        <form:select path="categoryId">
                            <form:options items="${categories}" />
                        </form:select>
                    </div>
                </div>
                <div>
                    <div class="label">Sub Category</div>
                    <div>
                        <form:select path="subCategoryId">
                            <form:options items="${subCategories}" />
                        </form:select>
                    </div>
                </div>
                <div>
                    <div class="label">Name</div>
                    <div><form:input path="name" size="100"></form:input></div>
                </div>
                <div>
                    <div class="label">Price</div>
                    <div><form:input path="price"></form:input></div>
                </div>
                <div>
                    <div class="label">Source</div>
                    <div>
                        <form:input path="sourceUrl"></form:input>
                    </div>
                </div>
            </div>
            <!-- Column 2 -->
            <div style="float: left;width: 49%">
                <div class="sub-container-box">
                    <input id="addFile" type="button" value="Add File" />
                    <table id="fileTable">
                        <tr>
                            <td><input name="files[0]" type="file" /></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div>
                <div class="label">&nbsp;</div>
				<span>
					<textarea class="ckeditor" name="editor1" id="editor1"></textarea>
				</span>
            </div>
            <div class="grid-button-container">
                <input type="button" id="clearBtn" value="Clear" class="formButton" onclick="clearControls()"/>
                <input type="submit" id="submitBtn" value="Submit" class="formButton" onclick="return submitOnClick()" />
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
