<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html lang="zh-cn">
	<head>
		<title>查看报销单</title>
	</head>
	<body>
	
	<content tag="breadcrumb">
			<li>报销单管理</li>
			<li class="active">报销单详情</li>
	</content>
	
	<div class="panel-body">
		<h4>基本信息</h4>
		<div class="baseList">
			<ul class="list-inline">
				<li class="col-md-3"><b>编　号：</b><s:property value="#request.claimVoucher.id"/></li>
				<li class="col-md-3"><b>填&nbsp;写&nbsp;人：</b><s:property value="#request.claimVoucher.creator.name"/></li>
				<li class="col-md-3"><b>　部　门：</b><s:property value="#request.claimVoucher.creator.department.name"/></li>
				<li class="col-md-3"><b>职　　位：</b><s:property value="#request.claimVoucher.creator.position.namecn"/></li>
				<li class="col-md-3"><b>总金额：</b><s:property value="#request.claimVoucher.totalAccount"/></li>
				<li class="col-md-3"><b>填报时间：</b><s:date name="#request.claimVoucher.createTime" format="yyyy-MM-dd HH:mm" /></li>
				<li class="col-md-3"><b>　状　态：</b><s:property value="#request.claimVoucher.status"/></li>
				<li><b>待处理人：</b><s:property value="#request.claimVoucher.nextDeal.name"/></li>
			</ul>
			
			<ul class="list-inline">
				<li class="col-md-3"><b>项目类别</b></li>
				<li class="col-md-3"><b>项目金额</b></li>
				<li><b>费用说明</b></li>
			</ul>
			<s:iterator value="#request.claimVoucher.claimVoucherDetails">
				<ul class="list-inline">
					<li class="col-md-3"><s:property value="item"/></li>
					<li class="col-md-3">¥<s:property value="account"/></li>
					<li><s:property value="description"/></li>
				</ul>
			</s:iterator>
			
			<s:iterator value="#request.claimVoucher.checkResults">
				<ul class="list-inline">
					<li class="col-md-4"><b><s:property value="checker.name"/></b>(<s:property value="checker.position.namecn"/>)</li>
					<li class="col-md-4"><s:date name="checkTime" format="yyyy-MM-dd HH:mm" /></li>
					<li class="col-md-4"><b>审批：</b><span style="color:#F00;"><s:property value="result"/></span></li>
					<li><b>审批意见：</b><s:property value="comment"/></li>
				</ul>
			</s:iterator>
			
			<input type="button" class="btn btn-primary" onclick="javascript:history.back(-1);" value="返回">
		</div>
	</div>
</body>
</html>