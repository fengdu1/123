<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html lang="zh-cn">
	<head>
		<title>添加报销单</title>
	</head>
	<body>
	
	<%-- 插件JS --%>
	<content tag="plugin.js">
		<script src="<s:url value="/assets/handlebars-v3.0.1.js" />"></script>
		<script src="<s:url value="/assets/moment-2.10.6-dist/js/moment-with-locales.min.js" />"></script>
		<script src="<s:url value="/assets/jqueryvalidation-1.16.0-dist/jquery.validate.min.js" />"></script>
	</content>
	
	<%-- 自定义JS --%>
	<content tag="page.js">
		<script src="<s:url value="/assets/claim/addClaimVoucher.js" />"></script>
	</content>
	
	<content tag="breadcrumb">
			<li>报销单管理</li>
			<li class="active">添加报销单</li>
	</content>
	
	<%-- 报销单详情模板 --%>
	<script id="detail" type="text/x-handlebars-template">
   			<div class="form-group">
				<div class="col-md-3">
					<select name="item" class="form-control">
						<option value="0">--请选择--</option>
						<option value="礼品费">礼品费</option>
						<option value="办公费">办公费</option>
						<option value="住宿费">住宿费</option>
						<option value="餐饮费">餐饮费</option>
						<option value="交际餐费">交际餐费</option>
						<option value="城际交通费">城际交通费</option>
						<option value="市内交通费">市内交通费</option>
					</select>
   				</div>
   				<div class="col-md-3">
   					<input type="text" name="account" class="form-control" placeholder="请输入项目金额">
   				</div>
   				<div class="col-md-4">
   					<input type="text" name="description" class="form-control" placeholder="请输入费用说明">
   				</div>
   				<div class="col-md-2">
   					<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
   				</div>
   			</div>
	</script>
	
	<div class="panel-body">
	  		<div class="row">
	  			<div class="col-md-3 col-md-offset-2">
	  				<label>填写人：</label>
	  				<s:property value="#session.loginEmployee.name" />
	  			</div>
	  			<div class="col-md-3">
	  				<label>部门：</label>
	  				<s:property value="#session.loginEmployee.department.name" />
	  			</div>
	  			<div class="col-md-4">
	  				<label>职位：</label>
	  				<s:property value="#session.loginEmployee.position.namecn" />
	  			</div>
	  		</div>
	  		<hr>
	  		<div class="row">
	  			<div class="col-md-3"><label>项目类别</label></div>
   				<div class="col-md-3"><label>项目金额</label></div>
   				<div class="col-md-4"><label>费用说明</label></div>
   				<div><label>操作</label> <a id="btnAddClaimVoucherDetail" href="javascript:;"><span class="glyphicon glyphicon-plus"></span></a></div>
	  		</div>
	   		<form class="form-horizontal" action="<s:url action="doAddClaimVoucher" />" method="post">
				<div class="form-group">
						<div class="col-md-3">
							<select name="item" class="form-control">
								<option value="0">--请选择--</option>
								<option value="礼品费">礼品费</option>
								<option value="办公费">办公费</option>
								<option value="住宿费">住宿费</option>
								<option value="餐饮费">餐饮费</option>
								<option value="交际餐费">交际餐费</option>
								<option value="城际交通费">城际交通费</option>
								<option value="市内交通费">市内交通费</option>
							</select>
		   				</div>
		   				<div class="col-md-3">
		   					<input type="text" name="account" class="form-control" placeholder="请输入项目金额">
		   				</div>
		   				<div class="col-md-4">
		   					<input type="text" name="description" class="form-control" placeholder="请输入费用说明">
		   				</div>
		   				<div class="col-md-2">
		   					<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
		   				</div>
					</div>
		   			<div class="form-group">
		   				<label for="event" class="col-md-1 form-label">事由：</label>
		   				<div class="col-md-11">
		   					<textarea id="event" name="event" class="form-control none-resize" placeholder="请输入报销事由"></textarea>
		   				</div>
		   			</div>
		   			<div class="form-group">
		   				<div class="text-center">
		   					<input type="hidden" name="status">
		   					<a data="新创建" class="btn btn-default">保存</a>
		   					<a data="已提交" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 提交</a>
	   					</div>
		   			</div>
	   		</form>
	  	</div>
</body>
</html>