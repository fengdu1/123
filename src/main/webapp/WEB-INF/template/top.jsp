<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="page-header">
			<h1 class="text-center">JBOA管理系统</h1>
			</div>
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
				    <div class="navbar-header">
			    		<a class="navbar-brand" href="<s:url action="home" />">JBOA</a>
			    	</div>
				 	<div class="collapse navbar-collapse">
	      				<ul class="nav navbar-nav">
	      					<li<s:if test="#session._status == 'home' || #session._status == null"> class="active"  style="font-weight:bold"</s:if>><a href="<s:url action="home"><s:param name="_status" value="'home'" /></s:url>">首页</a></li>
							<li<s:if test="#session._status == 'claim'"> class="active" style="font-weight:bold"</s:if>><a href="<s:url action="claimVoucherList"><s:param name="_status" value="'claim'" /></s:url>">报销单</a></li>
	      					<li><a href="<s:url namespace="/" action="home" />">请假</a></li>
	      					<li><a href="<s:url namespace="/" action="home" />">统计</a></li>
	      					<li><a href="<s:url namespace="/" action="home" />">信息</a></li>
	      				</ul>
	      				<ul class="nav navbar-nav navbar-right">
	      					<li><a href="<s:url namespace="/" action="logout" />">注销</a></li>
	      				</ul>
	      			</div>
      			</div>
			</nav>