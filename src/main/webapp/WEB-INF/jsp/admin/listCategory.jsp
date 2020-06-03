<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function(){
        $("#add_btn").click(function () {
            if (checkEmpty("name", "分类名称") && checkEmpty("categoryPic", "分类图片")) {
                $.ajax({
                    url: "${contextPath}/admin_category",
                    type: "POST",
                    cache: false,
                    data: new FormData($("#addForm")[0]),//获取表单数据
                    processData: false,// 不处理数据
                    contentType: false, // 不设置内容类型
                    success: function (resultInfo) {
                        // alert(resultInfo.msg);
                        location.href = "${contextPath}/admin_category_list?pn=" + ${pageInfo.pages + 1};
                    }
                });
            }
            return false;
        });

    });

</script>

<title>分类管理</title>

<div class="workingArea">
    <h1 class="label label-info" >分类管理</h1>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>分类名称</th>
                <th>属性管理</th>
                <th>产品管理</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="c">

                <tr>
                    <td>${c.id}</td>
                    <td><img height="40px" src="${contextPath}/img/category/${c.id}.jpg?<%=UUID.randomUUID()%>>"></td>
                    <td>${c.name}</td>

                    <td><a href="${contextPath}/admin_property_list?cid=${c.id}"><span class="glyphicon glyphicon-th-list"></span></a></td>
                    <td><a href="${contextPath}/admin_product_list?cid=${c.id}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
                    <td><a href="${contextPath}/admin_category/${c.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a deleteLink="true" url="admin_category" del_name="${c.name}" data="${c.id}" href="javascript:void(0)"><span class="   glyphicon glyphicon-trash"></span></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增分类</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="#" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input  id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>分类圖片</td>
                        <td>
                            <input id="categoryPic" accept="image/*" type="file" name="image" />
                        </td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="button" class="btn btn-success" id="add_btn">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</div>

<%@include file="../include/admin/adminFooter.jsp"%>