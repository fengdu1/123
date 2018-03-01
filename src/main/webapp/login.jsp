<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>JBOA管理系统-登录</title>
		<link href="<s:url value="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<s:url value="/assets/bootstrapvalidator-0.5.3-dist/css/bootstrapValidator.min.css" />" rel="stylesheet">
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-offset-4 col-md-4">
					<div class="page-header">
						<h1 class="text-center">JBOA管理系统<small>用户登录</small></h1>
					</div>
				</div>
			</div>
			
			<form class="form-horizontal" action="<s:url action="login" />" method="post">
				<div class="form-group">
					<label for="sn" class="control-label col-md-4">工号：</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="sn" name="sn" placeholder="请输入工号" value="<s:property value="sn" />">
						<s:if test="fieldErrors.sn.size() > 0">
							<small><s:property value="fieldErrors.sn[0]" /></small>
						</s:if>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="control-label col-md-4">密码：</label>
					<div class="col-md-4">
						<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" value="<s:property value="password" />">
						<s:if test="fieldErrors.password.size() > 0">
							<small><s:property value="fieldErrors.password[0]" /></small>
						</s:if>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-4">
						<input type="submit" class="btn btn-primary btn-block" value="登录"/>
					</div>
				</div>
			</form>
		</div>
		<script src="<s:url value="/assets/jquery-1.12.4.min.js" />"></script>
		<script src="<s:url value="/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
		<script src="<s:url value="/assets/bootstrapvalidator-0.5.3-dist/js/bootstrapValidator.min.js" />"></script>
		<script src="<s:url value="/assets/login.js" />"></script>
	</body>
</html>