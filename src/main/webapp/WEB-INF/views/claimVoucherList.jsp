<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html lang="zh-cn">
	<head>
		<title>报销单列表</title>
		<link href="<s:url value="/assets/bootstrapdatetimepicker-4.17.47-dist/css/bootstrap-datetimepicker.min.css" />" rel="stylesheet">
	</head>
	<body>
		<%--插件JS --%>
		<content tag="plugin.js">
			<script src="<s:url value="/assets/moment-2.10.6-dist/js/moment-with-locales.min.js" />"></script>
			<script src="<s:url value="/assets/bootstrapdatetimepicker-4.17.47-dist/js/bootstrap-datetimepicker.min.js" />"></script>
		</content>
		<%--自定义JS --%>
		<content tag="page.js">
			<script src="<s:url value="/assets/claim/claimVoucherList.js" />"></script>
		</content>
		
		<content tag="breadcrumb">
			<li>报销单管理</li>
		  	<li class="active">查看报销单</li>
		</content>
		
	  	<div class="panel-body">
	   		<form class="form-inline" action="<s:url action="claimVoucherList" />" method="post">
	   			<div class="form-group">
	   				<label for="status">报销单状态：</label>
	   				<select id="status" name="status" class="form-control">
	   					<s:iterator value="#request.statusMap">          
	   						<option value="<s:property value="key" />" <s:if test="key==status"> selected</s:if>><s:property value="value" /></option>
	   					</s:iterator>
	   				</select>
	   			</div>
	   			<div class="form-group">
	   				<label for="beginTime">开始时间：</label>
	   				<input type="text" id="beginTime" name="beginTime" class="form-control" value="<s:date name="beginTime" format="yyyy-MM-dd HH:mm" />">
	   			</div>
	   			<div class="form-group">
	   				<label for="endTime">结束时间：</label>
	   				<input type="text" id="endTime" name="endTime" class="form-control" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm" />">
	   			</div>
	   			<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询</button>
	   		</form>
		  	<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>编号</th>
							<th>填报日期</th>
							<th>填报人</th>
							<th>总金额</th>
							<th>状态</th>
							<th>待处理人</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:if test="#request.claimVoucherPagination.totalLines > 0">
							<s:iterator value="#request.claimVoucherPagination.list" var="claimVoucher">
								<tr>
									<td><s:property value="id" /></td>
									<td><s:date name="createTime" format="yyyy-MM-dd HH:mm" /></td>
									<td><s:property value="creator.name" /></td>
									<td><s:property value="totalAccount" /></td>
									<td><s:property value="status" /></td>
									<td><s:property value="nextDeal.name" /></td>
									<td>
										<%-- <s:if test="#claimVoucher.creator.sn eq #session.loginEmployee.sn">
											<s:if test="#claimVoucher.status eq '新创建' or claimVoucher.status eq '已打回'">
												<a href="<s:url action="claimVoucherEdit"><s:param name="id" value="id"></s:param> </s:url>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
											</s:if>
										</s:if>
										<s:if test="#claimVoucher.nextDeal.sn eq #session.loginEmployee.sn}">
											<s:if test="(#session.loginEmployee.position.nameen eq 'manager' and #claimVoucher.status eq '已提交')
											   or (#session.loginEmployee.position.nameen eq 'generalmanager' and #claimVoucher.status eq '待审批') 
											   or (#session.loginEmployee.position.nameen eq 'cashier' and #claimVoucher.status eq '已审批')">
											 		<a href="<s:url action="toAddCheckResult"><s:param name="id" value="id"></s:param> </s:url>"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
											 </s:if>
										 </s:if>
										 <s:if test="#session.loginEmployee.position.nameen eq 'staff' and (#claimVoucher.status eq '新创建' or #claimVoucher.status eq '已打回' or #claimVoucher.status eq '已终止')">
										 	<a href="<s:url action="removeClaimVoucher"><s:param name="id" value="id"></s:param> </s:url>"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
										 </s:if> --%>
										 <s:if test="status == '新创建'">
											<a href="<s:url action="claimVoucherEdit"><s:param name="id" value="id"></s:param> </s:url>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
											<a href="<s:url action="removeClaimVoucher"><s:param name="id" value="id"></s:param> </s:url>"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
										</s:if>
										<s:elseif test="status == '已提交' && #session.loginEmployee.position.nameen == 'manager'">
											<a href="<s:url action="toAddCheckResult"><s:param name="claimVoucher.id" value="id" /><s:param name="pageIndex" value="pageIndex" /><s:param name="pageSize" value="pageSize" /></s:url>"><span class="glyphicon glyphicon-pencil"></span></a>
										</s:elseif>
										<s:elseif test="status == '待审批' && #session.loginEmployee.position.nameen == 'generalmanager'">
											<a href="<s:url action="toAddCheckResult"><s:param name="claimVoucher.id" value="id" /><s:param name="pageIndex" value="pageIndex" /><s:param name="pageSize" value="pageSize" /></s:url>"><span class="glyphicon glyphicon-pencil"></span></a>
										</s:elseif>
										<s:elseif test="status == '已审批' && #session.loginEmployee.position.nameen == 'cashier'">
											<a href="<s:url action="toAddCheckResult"><s:param name="claimVoucher.id" value="id" /><s:param name="pageIndex" value="pageIndex" /><s:param name="pageSize" value="pageSize" /></s:url>"><span class="glyphicon glyphicon-pencil"></span></a>
										</s:elseif>
										<s:elseif test="status == '已打回'">
											<s:if test="#session.loginEmployee.position.nameen == 'staff'">
												<a href="<s:url action="claimVoucherEdit"><s:param name="id" value="id"></s:param> </s:url>"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
												<a href="<s:url action="removeClaimVoucher"><s:param name="id" value="id"></s:param> </s:url>"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
											</s:if>
										</s:elseif>
										<s:elseif test="status == '已终止'">
											<s:if test="#session.loginEmployee.position.nameen == 'staff'">
												<a href="<s:url action="removeClaimVoucher"><s:param name="id" value="id"></s:param> </s:url>"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
											</s:if>
										</s:elseif>
										<a href="<s:url action="claimVoucherDetail"><s:param name="id" value="id"><s:param name="pageIndex" value="#pagination.pageIndex" /><s:param name="pageSize" value="#pagination.pageSize" /></s:param> </s:url>"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
									</td>
								</tr>
							</s:iterator>
							<%-- 
								<tr>
									<td colspan="7" class="text-center"><span>共<s:property value="#request.claimVoucherPagination.totalLines" />条记录,<s:property value="#request.claimVoucherPagination.pageIndex" />/<s:property value="#request.claimVoucherPagination.totalPages" />页</span>
									<s:if test="#request.claimVoucherPagination.isFirst">首页 | 上一页 | </s:if>
									<s:else>
										<a href='<s:url action="claimVoucherList"><s:param name="pageIndex" value="1"/><s:param name="pageSize" value="#request.claimVoucherPagination.pageSize"/></s:url>'>首页 |</a>
										<a href='<s:url action="claimVoucherList"><s:param name="pageIndex" value="#request.claimVoucherPagination.pageIndex - 1"/><s:param name="pageSize" value="#request.claimVoucherPagination.pageSize"/></s:url>'>上一页 |</a>
									</s:else>
									<s:if test="#request.claimVoucherPagination.isLast">下一页 | 尾页</s:if>
									<s:else>
										<a href='<s:url action="claimVoucherList"><s:param name="pageIndex" value="#request.claimVoucherPagination.pageIndex + 1"/><s:param name="pageSize" value="#request.claimVoucherPagination.pageSize"/></s:url>'>下一页 |</a>
										<a href='<s:url action="claimVoucherList"><s:param name="pageIndex" value="#request.claimVoucherPagination.totalPages"/><s:param name="pageSize" value="#request.claimVoucherPagination.pageSize"/></s:url>'>尾页</a>
									</s:else>
								</tr>
							 --%>
						</s:if>
						<s:else>
							<tr>
								<td colspan="7" class="text-center">没有更多报销单数据!</td>
							</tr>
						</s:else>
					</tbody>
				</table>
			</div>
		</div>
	
		<s:if test="#request.claimVoucherPagination.totalLines > 0">
			<div class="text-right">
					<ul class="pagination">
						<s:if test="#request.claimVoucherPagination.isFirst">
							<li class="disabled">
								<span aria-hidden="true">&laquo;</span>
							</li>
						</s:if>
						<s:else>
							<li>
								<a pageIndex="<s:property value='#request.claimVoucherPagination.pageIndex - 1' />" pageSize="<s:property value='#request.claimVoucherPagination.pageSize'/>" href="javascript:;"><span aria-hidden="true">&laquo;</span></a>
							</li>
						</s:else>
						<s:iterator begin="1" end="#request.claimVoucherPagination.totalPages" var="i">
							<li>
								<a pageIndex="<s:property value='i'/>" pageSize="<s:property value='#request.claimVoucherPagination.pageSize'/>" href="javascript:;"><s:property value="i"/></a>
							</li>
						</s:iterator>
						<s:if test="#request.claimVoucherPagination.isLast">
							<li class="disabled">
								<span aria-hidden="true">&raquo;</span>
							</li>
						</s:if>
						<s:else>
							<li>
								<a pageIndex="<s:property value='#request.claimVoucherPagination.pageIndex + 1' />" pageSize="<s:property value='#request.claimVoucherPagination.pageSize'/>" href="javascript:;"><span aria-hidden="true">&raquo;</span></a>
							</li>
						</s:else>
					</ul>
			</div>
		</s:if>
	</body>
</html>