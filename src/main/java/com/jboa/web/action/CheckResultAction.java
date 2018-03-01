package com.jboa.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.jboa.pojo.CheckResult;
import com.jboa.pojo.ClaimVoucher;
import com.jboa.pojo.Employee;
import com.jboa.service.CheckResultService;
import com.jboa.service.ClaimVoucherService;
import com.jboa.web.util.Constants;
import com.jboa.web.util.WebUtil;

public class CheckResultAction extends BaseAction<CheckResult> {

	private static final long serialVersionUID = 3993335760420152461L;

	@Autowired
	private ClaimVoucherService claimVoucherService;
	
	@Autowired
	private CheckResultService checkResultService;
	
	@Action(value="toAddCheckResult",results={
			@Result(location="/WEB-INF/views/addCheckResult.jsp")
	})
	public String toAddCheckResult(){
		CheckResult checkResult = getModel();
		ClaimVoucher claimVoucher = claimVoucherService.getClaimVoucherById(checkResult.getClaimVoucher().getId(), true);
		checkResult.setClaimVoucher(claimVoucher);
		return SUCCESS;
	}
	
	@Action(value="addCheckResult",results={
			@Result(type="redirectAction",location="claimVoucherList"),
			@Result(name="input",location="/WEB-INF/views/addCheckResult.jsp")
	})
	public String addCheckResult() {
		Employee employee = WebUtil.getSession(Constants.LOGIN_EMPLOYEE);
		CheckResult checkResult = getModel();
		checkResult.setChecker(employee);
		checkResultService.addCheckResult(checkResult);
		return SUCCESS;
	}
}
