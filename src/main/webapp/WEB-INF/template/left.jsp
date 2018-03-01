<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="panel-group text-center" id="accordion">
	<div class="panel panel-default">
	    <div class="panel-heading" id="headingOne">
	      <h4 class="panel-title">
	        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">报销单管理</a>
	      </h4>
	    </div>
	    <div id="collapseOne" class="panel-collapse collapse<s:if test="#session._status == 'claim'"> in</s:if>">
	    	<div class="panel-body">
				<ul class="item">
				  <li><a href="<s:url action="claimVoucherList"><s:param name="_status" value="'claim'" /></s:url>">查看报销单</a></li>
				  <li>&nbsp;</li>
				  <li><a href="<s:url action="addClaimVoucher"><s:param name="_status" value="'claim'" /></s:url>">添加报销单</a></li>
				</ul>
	    	</div>
		</div>
	</div>
  <div class="panel panel-default">
  		<div class="panel-heading" id="headingTwo">
      		<h4 class="panel-title">
        		<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">请假管理</a>
      		</h4>
    	</div>
    	<div id="collapseTwo" class="panel-collapse collapse">
      		<div class="panel-body">
				<ul class="item">
				  <li><a href="">查看请假</a></li>
				  <li>&nbsp;</li>
				  <li><a href="">添加请假</a></li>
				</ul>
      		</div>
    	</div>
  </div>
  <div class="panel panel-default">
    	<div class="panel-heading" id="headingThree">
      	<h4 class="panel-title">
        	<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree">统计报表</a>
      	</h4>
    	</div>
    	<div id="collapseThree" class="panel-collapse collapse">
      		<div class="panel-body">
				<ul class="item">
				  <li><a href="">月度统计信息</a></li>
				  <li><a href="">年度统计信息</a></li>
				</ul>
      		</div>
    	</div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseFour">信息中心</a>
      </h4>
    </div>
    <div id="collapseFour" class="panel-collapse collapse">
      <div class="panel-body">
      	body4
      </div>
    </div>
  </div>
</div>