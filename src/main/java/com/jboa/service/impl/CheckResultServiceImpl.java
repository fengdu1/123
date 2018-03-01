package com.jboa.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jboa.dao.CheckResultDao;
import com.jboa.dao.ClaimVoucherDao;
import com.jboa.dao.EmployeeDao;
import com.jboa.pojo.CheckResult;
import com.jboa.pojo.ClaimVoucher;
import com.jboa.pojo.Employee;
import com.jboa.pojo.Position;
import com.jboa.service.CheckResultService;
import com.jboa.util.BusinessConstants;

@Service("checkResultService")
public class CheckResultServiceImpl implements CheckResultService {

	@Resource
	private CheckResultDao checkResultDao;
	
	@Resource
	private EmployeeDao employeeDao;
	
	@Resource
	private ClaimVoucherDao claimVoucherDao;
	
	@Override
	public void addCheckResult(CheckResult checkResult) {
		ClaimVoucher claimVoucher = claimVoucherDao.get(checkResult.getClaimVoucher().getId());
		Employee checker = checkResult.getChecker();
		Position position = checker.getPosition();
		
		String status = null;
		Employee nextDeal = null;
		switch (checkResult.getResult()) {
			case BusinessConstants.CHECK_PASS:
				if(claimVoucher.getTotalAccount()>=5000){
					if(BusinessConstants.EMPLOYEE_MANAGER.equals(position.getNameen())){
						status="待审批";
						nextDeal=employeeDao.getGeneralManager(checker.getDepartment().getId());
					}else if(BusinessConstants.EMPLOYEE_GENERAL_MANAGER.equals(position.getNameen())){
						status="已审批";
						nextDeal=employeeDao.getCashier();
					}
				}else {
					status="已审批";
					nextDeal=employeeDao.getCashier();
				}
				break;
			case BusinessConstants.CHECK_BACK:
				status="已打回";
				nextDeal=claimVoucher.getCreator();
				break;
			case BusinessConstants.CHECK_REFUSE:
				status="已终止";
				break;
			case BusinessConstants.CHECK_PAYMENT:
				if(BusinessConstants.EMPLOYEE_CASHIER.equals(position.getNameen())){
					status="已付款";
				}
				break;
			default:
				throw new IllegalArgumentException("不支持的报销单状态：" + checkResult.getResult());
			}
		
		claimVoucher.setStatus(status);
		claimVoucher.setModifyTime(new Date());
		claimVoucher.setNextDeal(nextDeal);
		claimVoucherDao.update(claimVoucher);
		checkResult.setCheckTime(new Date());
		checkResultDao.save(checkResult);
	}
}
