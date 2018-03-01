<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>JBOA管理系统-<decorator:title default="页面"/></title>
		<link href="<s:url value="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<s:url value="/assets/layout.css" />" rel="stylesheet">
		<decorator:head/>
	</head>
	<body>
		<div class="container">
			<page:applyDecorator name="top" />
			<div class="row">
				<div class="col-md-2">
					<page:applyDecorator name="left" />
				</div>
				<div class="col-md-10">
					<ol class="breadcrumb">
						<decorator:getProperty property="page.breadcrumb" />
					</ol>
					<div class="panel panel-default">
						<decorator:body />
					</div>
				</div>
			</div>
			
			<page:applyDecorator name="footer" />
		</div>
		<script src="<s:url value="/assets/jquery-1.12.4.min.js" />"></script>
		<script src="<s:url value="/assets/bootstrap-3.3.7-dist/js/bootstrap.min.js" />"></script>
		<decorator:getProperty property="page.plugin.js" />
		<decorator:getProperty property="page.page.js" />
	</body>
</html>