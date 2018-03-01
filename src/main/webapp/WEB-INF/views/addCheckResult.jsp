<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title>审批报销单</title>
		<link href="<s:url value="/assets/bootstrapvalidator-0.5.3-dist/css/bootstrapValidator.min.css" />" rel="stylesheet">
	</head>
	<body>
		<%-- 插件JS --%>
		<content tag="plugin.js">
			<script src="<s:url value="/assets/bootstrapvalidator-0.5.3-dist/js/bootstrapValidator.min.js" />"></script>
		</content>
		
		<%-- 页面自定义JS --%>
		<content tag="page.js">
			<script src="<s:url value="/assets/claim/addCheckResult.js" />"></script>
		</content>
		
		<%-- 路径导航 --%>
		<content tag="breadcrumb">
		  	<li>报销单管理</li>
			<li><a href="<s:url namespace="/claimVoucher" action="claimVoucherList"><s:param name="pageIndex" value="pageIndex" /><s:param name="pageSize" value="pageSize" /></s:url>">查看报销单</a></li>
			<li class="active">审批报销单</li>
		</content>
		
		<div class="panel-body">
			<h3>基本信息</h3>
			<div class="row">
	  			<div class="col-md-3">
	  				<label>　　编号：</label>
	  				<s:property value="claimVoucher.id" />
	  			</div>
	  			<div class="col-md-3">
	  				<label>　填写人：</label>
	  				<s:property value="claimVoucher.creator.name" />
	  			</div>
	  			<div class="col-md-3">
	  				<label>　　部门：</label>
	  				<s:property value="claimVoucher.creator.department.name" />
	  			</div>
	  			<div class="col-md-3">
	  				<label>　　职位：</label>
	  				<s:property value="claimVoucher.creator.position.namecn" />
	  			</div>
			</div>
	  		<div class="row">
	  			<div class="col-md-3">
	  				<label>　总金额：</label>
	  				<s:property value="claimVoucher.totalAccount" />
	  			</div>
	  			<div class="col-md-3">
	  				<label>填报时间：</label>
	  				<s:date name="claimVoucher.createTime" format="yyyy-MM-dd HH:mm" />
	  			</div>
	  			<div class="col-md-3">
	  				<label>　　状态：</label>
	  				<s:property value="claimVoucher.status" />
	  			</div>
	  			<div class="col-md-3">
	  				<label>待处理人：</label>
	  				<s:if test="claimVoucher.nextDeal.name == null">
	  					<s:property value="#session.loginEmployee.name" />
	  				</s:if>
	  				<s:else>
	  					<s:property value="claimVoucher.nextDeal.name" />
	  				</s:else>
	  			</div>
	  		</div>
	  		<hr>
	  		<div class="row">
	  			<div class="col-md-3">
	  				<label>项目类别</label>
	  			</div>
	  			<div class="col-md-2">
	  				<label>项目金额</label>
	  			</div>
	  			<div class="col-md-7">
	  				<label>费用说明</label>
	  			</div>
	  		</div>
	  		<s:iterator value="claimVoucher.claimVoucherDetails">
		  		<div class="row">
		  			<div class="col-md-3">
		  				<s:property value="item" />
		  			</div>
		  			<div class="col-md-2">
		  				<s:property value="account" />
		  			</div>
		  			<div class="col-md-7">
		  				<s:property value="description" />
		  			</div>
		  		</div>
	  		</s:iterator>
	  		<s:iterator value="claimVoucher.checkResults">
	  			<hr>
	  			<div class="row">
		  			<div class="col-md-3">
		  				<s:property value="checker.name" />(<s:property value="checker.position.namecn" />)
		  			</div>
		  			<div class="col-md-2">
		  				<s:date name="checkTime" format="yyyy-MM-dd HH:mm" />
		  			</div>
		  			<div class="col-md-7">
		  				审批：<span class="text-danger"><s:property value="result" /></span>
		  			</div>
	  			</div>
	  			<div class="row">
		  			<div class="col-md-12">
		  				<label>审批意见：</label>
		  				<s:property value="comment" />
		  			</div>
	  			</div>
	  		</s:iterator>
	  		<hr>
	  		<div class="row">
		  		<div class="col-md-12">
		  			<form action="<s:url action="addCheckResult"/>" method="post">
		  				<div class="form-group">
		  					<label class="lable-control" for="comment">审批意见：</label>
		  					<textarea rows="3" class="form-control none-resize" id="comment" name="comment"></textarea>
		  				</div>
		  				<input type="hidden" name="claimVoucher.id" value="<s:property value="claimVoucher.id" />">
		  				<input type="hidden" name="pageIndex" value="<s:property value="pageIndex" />">
		  				<input type="hidden" name="pageSize" value="<s:property value="pageSize" />">
		  				<input type="hidden" name="result">
		  				<s:if test="#session.loginEmployee.position.nameen == 'manager' || #session.loginEmployee.position.nameen == 'generalmanager'">
			  				<button data="通过" type="button" class="btn btn-primary">审批通过</button>
			  				<button data="拒绝" type="button" class="btn btn-default">审批拒绝</button>
			  				<button data="打回" type="button" class="btn btn-default">打回</button>
		  				</s:if>
		  				<s:elseif test="#session.loginEmployee.position.nameen == 'cashier'">
		  					<button data="付款" type="button" class="btn btn-primary">审批通过</button>
		  				</s:elseif>
		  			</form>
		  		</div>
	  		</div>
		</div>
	</body>
</html>