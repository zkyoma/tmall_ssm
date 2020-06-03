<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function(){
    	$("#img_single_btn").click(function () {
			if(checkEmpty("filepathSingle","图片文件")){
				$.ajax({
					url:"${contextPath}/admin_productImage",
					type:"POST",
					processData:false,// 不处理数据
					contentType:false, // 不设置内容类型
					data:new FormData($("#addFormSingle")[0]),
					success:function (resultInfo) {
						if(resultInfo.flag){
							location.reload();
						}
					}

				});
			}
		});

		$("#img_detail_btn").click(function () {
			if(checkEmpty("filepathDetail","图片文件")){
				$.ajax({
					url:"${contextPath}/admin_productImage",
					type:"POST",
					processData:false,// 不处理数据
					contentType:false, // 不设置内容类型
					data:new FormData($("#addFormDetail")[0]),
					success:function (resultInfo) {
						if(resultInfo.flag){
							location.reload();
						}
					}

				});
			}
		});
	});
</script>

<title>产品图片管理</title>


<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="admin_category_list">所有分类</a></li>
		<li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
		<li class="active">${p.name}</li>
		<li class="active">产品图片管理</li>
	</ol>

	<table class="addPictureTable" align="center">
		<tr>
			<td class="addPictureTableTD">
				<div>
					<div class="panel panel-warning addPictureDiv">
						<div class="panel-heading">新增产品<b class="text-primary"> 单个 </b>图片</div>
						<div class="panel-body">
							<form method="post" id="addFormSingle" action="#" enctype="multipart/form-data">
								<table class="addTable">
									<tr>
										<td>请选择本地图片 尺寸400X400 为佳</td>
									</tr>
									<tr>
										<td>
											<input id="filepathSingle" type="file" name="image" />
										</td>
									</tr>
									<tr class="submitTR">
										<td align="center">
											<input type="hidden" name="type" value="type_single" />
											<input type="hidden" name="pid" value="${p.id}" />
											<button type="button" id="img_single_btn" class="btn btn-success">提 交</button>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<table class="table table-striped table-bordered table-hover  table-condensed">
						<thead>
						<tr class="success">
							<th>ID</th>
							<th>产品单个图片缩略图</th>
							<th>删除</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${p.productSingleImages}" var="pi">
							<tr>
								<td>${pi.id}</td>
								<td>
									<a title="点击查看原图" href="${contextPath}/img/productSingle/${pi.id}.jpg"><img height="50px" src="img/productSingle/${pi.id}.jpg"></a>
								</td>
								<td>
									<a deleteLink="true" data="${pi.id}" del_name="图片${pi.id}" url="admin_productImage" href="javascript:void(0)">
										<span class=" 	glyphicon glyphicon-trash"></span>
									</a>
								</td>

							</tr>
						</c:forEach>
						</tbody>
					</table>

				</div>
			</td>
			<td class="addPictureTableTD">
				<div>

					<div class="panel panel-warning addPictureDiv">
						<div class="panel-heading">新增产品<b class="text-primary"> 详情 </b>图片</div>
						<div class="panel-body">
							<form method="post" id="addFormDetail" action="#" enctype="multipart/form-data">
								<table class="addTable">
									<tr>
										<td>请选择本地图片 宽度790  为佳</td>
									</tr>
									<tr>
										<td>
											<input id="filepathDetail"  type="file" name="image" />
										</td>
									</tr>
									<tr class="submitTR">
										<td align="center">
											<input type="hidden" name="type" value="type_detail" />
											<input type="hidden" name="pid" value="${p.id}" />
											<button type="button" id="img_detail_btn" class="btn btn-success">提 交</button>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<table class="table table-striped table-bordered table-hover  table-condensed">
						<thead>
						<tr class="success">
							<th>ID</th>
							<th>产品详情图片缩略图</th>
							<th>删除</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${p.productDetailImages}" var="pi">
							<tr>
								<td>${pi.id}</td>
								<td>
									<a title="点击查看原图" href="${contextPath}/img/productDetail/${pi.id}.jpg"><img height="50px" src="img/productDetail/${pi.id}.jpg"></a>
								</td>
								<td>
									<a deleteLink="true" data="${pi.id}" del_name="图片${pi.id}" url="admin_productImage" href="javascript:void(0)">
										<span class=" 	glyphicon glyphicon-trash"></span>
									</a>
								</td>

							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
	</table>





</div>

<%@include file="../include/admin/adminFooter.jsp"%>
