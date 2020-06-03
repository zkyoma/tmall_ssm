<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
	$(function () {
		$("#pro_upt_btn").click(function () {
			if(checkEmpty("name", $("#name").val())){
				alert($("#editForm").serialize());
				$.ajax({
					url:"${contextPath}/admin_property",
					type:"PUT",
					data:$("#editForm").serialize(),
					success:function (resultInfo) {
						if(resultInfo.flag){
							location.href = "${contextPath}/admin_property_list?cid=" + resultInfo.data.cid;
						}
					}
				});
			}
		});
	});
</script>

<title>编辑属性</title>

<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin_category_list">所有分类</a></li>
		<li><a href="${contextPath}/admin_property_list?cid=${p.category.id}">${p.category.name}</a></li>
		<li class="active">编辑属性</li>
	</ol>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑属性</div>
		<div class="panel-body">
			<form method="post" id="editForm" action="#">
				<table class="editTable">
					<tr>
						<td>属性名称</td>
						<td><input id="name" name="name" value="${p.name}"
								   type="text" class="form-control"></td>
					</tr>
					<tr class="submitTR">
						<td colspan="2" align="center">
							<input type="hidden" name="id" value="${p.id}">
							<input type="hidden" name="cid" value="${p.category.id}">
							<button type="button" id="pro_upt_btn" class="btn btn-success">提 交</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>