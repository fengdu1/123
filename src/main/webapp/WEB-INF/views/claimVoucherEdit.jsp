<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html lang="zh-cn">
	<head>
		<title>修改报销单</title>
	</head>
	<body>
	<%-- 插件JS --%>
	<content tag="plugin.js">
		<script src="<s:url value="/assets/handlebars-v3.0.1.js" />"></script>
		<script src="<s:url value="/assets/jqueryvalidation-1.16.0-dist/jquery.validate.min.js" />"></script>
		<script src="<s:url value="/assets/moment-2.10.6-dist/js/moment-with-locales.min.js" />"></script>
	</content>
		
	<%-- 页面自定义JS --%>
	<content tag="page.js">
		<script src="<s:url value="/assets/claim/editClaimVoucher.js" />"></script>
	</content>
	
	<%-- 路径导航 --%>
	<content tag="breadcrumb">
	  	<li>报销单管理</li>
	  	<li class="active">修改报销单</li>
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
		<h4>基本信息</h4>
		<div class="baseList">
			<ul class="list-inline">
				<li class="col-md-3"><b>编　号：</b><s:property value="id"/></li>
				<li class="col-md-3"><b>填&nbsp;写&nbsp;人：</b><s:property value="creator.name"/></li>
				<li class="col-md-3"><b>　部　门：</b><s:property value="creator.department.name"/></li>
				<li class="col-md-3"><b>职　　位：</b><s:property value="creator.position.namecn"/></li>
				<li class="col-md-3"><b>总金额：</b><s:property value="totalAccount"/></li>
				<li class="col-md-3"><b>填报时间：</b><s:date name="createTime" format="yyyy-MM-dd HH:mm" /></li>
				<li class="col-md-3"><b>　状　态：</b><s:property value="status"/></li>
				<li><b>待处理人：</b><s:property value="nextDeal.name"/></li>
			</ul>
			
			<ul class="list-inline">
				<li class="col-md-3"><b>项目类别</b></li>
				<li class="col-md-3"><b>项目金额</b></li>
				<li class="col-md-4"><b>费用说明</b></li>
				<li class="col-md-2"><b>操作</b><a id="btnAddClaimVoucherDetail" href=""><span class="glyphicon glyphicon-plus"></span></a></li>
			</ul>
			<form class="form-horizontal" action="<s:url action="doClaimVoucherEdit" />" method="post">
				<s:iterator value="claimVoucherDetails">
					<div class="form-group">
						<div class="col-md-3">
							<input type="hidden" name="id" value="<s:property value="id" />">
							<select name="item" class="form-control">
								<option value="0">--请选择--</option>
								<option value="礼品费"<s:if test="item == '礼品费'"> selected</s:if>>礼品费</option>
								<option value="办公费"<s:if test="item == '办公费'"> selected</s:if>>办公费</option>
								<option value="住宿费"<s:if test="item == '住宿费'"> selected</s:if>>住宿费</option>
								<option value="餐饮费"<s:if test="item == '餐饮费'"> selected</s:if>>餐饮费</option>
								<option value="交际餐费"<s:if test="item == '交际餐费'"> selected</s:if>>交际餐费</option>
								<option value="城际交通费"<s:if test="item == '城际交通费'"> selected</s:if>>城际交通费</option>
								<option value="市内交通费"<s:if test="item == '市内交通费'"> selected</s:if>>市内交通费</option>
							</select>
		   				</div>
		   				<div class="col-md-3">
		   					<input type="text" name="account" class="form-control" placeholder="请输入项目金额" value="<s:property value="account" />">
		   				</div>
		   				<div class="col-md-4">
		   					<input type="text" name="description" class="form-control" placeholder="请输入费用说明" value="<s:property value="description" />">
		   				</div>
		   				<div>
		   					<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
		   				</div>
	   				</div>
				</s:iterator>
				<div class="form-group">
	   				<label for="event" class="form-label col-md-1">事由：</label>
	   				<div class="col-md-10">
	   					<textarea id="event" name="event" class="form-control none-resize" placeholder="请输入报销事由"><s:property value="event" /></textarea>
	   				</div>
	   			</div>
	   			<div class="form-group">
	   				<div class="text-center">
	   					<input type="hidden" name="id" value="<s:property value="id" />">
	   					<input type="hidden" id="status" name="status">
	   					<a data="新创建" class="btn btn-default">保存</a>
	   					<a data="已提交" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 提交</a>
	   				</div>
	   			</div>
			</form>
		</div>
	</div>
</body>
</html>