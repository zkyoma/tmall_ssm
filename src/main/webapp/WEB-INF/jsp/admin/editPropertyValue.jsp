<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<title>编辑产品属性值</title>

<script>
    $(function() {
        $("input.pvValue").change(function(){
            var value = $(this).val();
            var page = "${contextPath}/admin_propertyValue";
            var pvid = $(this).attr("pvid");
            var parentSpan = $(this).parent("span");
            parentSpan.css("border","1px solid yellow");
            $.ajax({
				url:page,
				data:{"value":value,"id":pvid},
				type:"PUT",
				success:function (resultInfo) {
					if (resultInfo.flag) {
						parentSpan.css("border", "1px solid green");
					} else {
						parentSpan.css("border", "1px solid red");
					}
				}
			});
        });
    });
</script>

<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="${contextPath}/admin_category_list">所有分类</a></li>
		<li><a href="${contextPath}/admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
		<li class="active">${p.name}</li>
		<li class="active">编辑产品属性</li>
	</ol>

	<div class="editPVDiv">
		<c:forEach items="${p.propertyValues}" var="pv">
			<div class="eachPV">
				<span class="pvName" >${pv.property.name}</span>
				<span class="pvValue"><input class="pvValue" pvid="${pv.id}" type="text" value="${pv.value}"></span>
			</div>
		</c:forEach>
		<div style="clear:both"></div>
	</div>

</div>

