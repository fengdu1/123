package com.jboa.web.action;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.pojo.ClaimVoucher;
import com.jboa.pojo.Employee;
import com.jboa.service.ClaimVoucherService;
import com.jboa.web.util.Constants;
import com.jboa.web.util.WebUtil;

@Controller
@ParentPackage("default")
@Scope("prototype")
public class ClaimVoucherAction extends BaseAction<ClaimVoucher> {

	private static final long serialVersionUID = -7264274256436149085L;

	@Autowired
	private ClaimVoucherService claimVoucherService;
	
	private Date beginTime;
	
	private Date endTime;
	
	//导航到报销单页面，并分页查询报销单
	@Action(value="claimVoucherList",results={
			@Result(location="/WEB-INF/views/claimVoucherList.jsp")
	})
	public String claimVoucherList(){
		Employee employee = WebUtil.getSession(Constants.LOGIN_EMPLOYEE);
		WebUtil.setRequest("claimVoucherPagination", claimVoucherService.getClaimVoucherList(getPageIndex(), getPageSize(), employee, getModel(), getBeginTime(), getEndTime()));
		String nameen = employee.getPosition().getNameen();
		WebUtil.setRequest("statusMap",claimVoucherService.getStatusByPosition(nameen));
		
		return SUCCESS;
	}
	
	//导航到报销单详情页面
	@Action(value="claimVoucherDetail",results={
			@Result(location="/WEB-INF/views/claimVoucherDetail.jsp")
	})
	public String claimVoucherDetail(){
		ClaimVoucher claimVoucher = claimVoucherService.getClaimVoucherById(getModel().getId(), true);
		WebUtil.setRequest("claimVoucher", claimVoucher);
		return SUCCESS;
	}
	
	//导航到添加报销单页面
	@Action(value="addClaimVoucher", results={
			@Result(location="/WEB-INF/views/claimVoucherAdd.jsp")
	})
	public String addClaimVoucher() {
		return SUCCESS;
	}
	
	//添加报销单
	@Action(value="doAddClaimVoucher", results={
			@Result(type="redirectAction",location="claimVoucherList"),
			@Result(name="input", location="/WEB-INF/views/claimVoucherAdd.jsp")
	})
	public String doAddClaimVoucher(){
		ClaimVoucher claimVoucher = getModel();
		claimVoucher.setCreator(WebUtil.getSession(Constants.LOGIN_EMPLOYEE));
		claimVoucher.setNextDeal(WebUtil.getSession(Constants.LOGIN_EMPLOYEE));
		claimVoucherService.addClaimVoucher(claimVoucher);
		return SUCCESS;
	}
	
	//导航到修改报销单页面
	@Action(value="claimVoucherEdit",results={
			@Result(location="/WEB-INF/views/claimVoucherEdit.jsp")
	})
	public String claimVoucherEdit() throws Exception{
		ClaimVoucher claimVoucher = getModel();
		ClaimVoucher currClaimVoucher = claimVoucherService.getClaimVoucherById(getModel().getId(), false);
		BeanUtils.copyProperties(claimVoucher, currClaimVoucher);
		return SUCCESS;
	}
	
	//修改报销单
	@Action(value="doClaimVoucherEdit",results={
			@Result(type="redirectAction",location="claimVoucherList"),
			@Result(name="input",location="/WEB-INF/views/claimVoucherEdit.jsp")
	})
	public String doClaimVoucherEdit(){
		ClaimVoucher claimVoucher = getModel();
		claimVoucher.setCreator(WebUtil.getSession(Constants.LOGIN_EMPLOYEE));
		claimVoucherService.editClaimVoucher(claimVoucher);
		return SUCCESS;
	}
	
	//删除报销单
	@Action(value="removeClaimVoucher",results={
			@Result(type="redirectAction",location="claimVoucherList")
	})
	public String removeClaimVoucher(){
		claimVoucherService.removeClaimVoucherById(getModel().getId());
		return SUCCESS;
	}
	
	//提交报销单
	@Action(value="submitClaimVoucher", results={
		@Result(type="redirectAction", location="claimVoucherList")
	})
	public String submitClaimVoucher() throws Exception {
		claimVoucherService.submitClaimVoucher(getModel().getId());
		return SUCCESS;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
