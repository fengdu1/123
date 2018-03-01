package com.jboa.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.pagination.DefaultPagination;
import org.pagination.Pagination;import org.pagination.QueryHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jboa.dao.CheckResultDao;
import com.jboa.dao.ClaimVoucherDao;
import com.jboa.dao.ClaimVoucherDetailDao;
import com.jboa.dao.EmployeeDao;
import com.jboa.pojo.ClaimVoucher;
import com.jboa.pojo.ClaimVoucherDetail;
import com.jboa.pojo.Employee;
import com.jboa.service.ClaimVoucherService;
import com.jboa.util.BusinessConstants;

@Service("claimVoucherService")
public class ClaimVoucherServiceImpl implements ClaimVoucherService {

	@Autowired
	private ClaimVoucherDao claimVoucherDao;

	@Autowired
	private ClaimVoucherDetailDao claimVoucherDetailDao;
	
	@Autowired
	private CheckResultDao checkResultDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Map<String, String> getStatusByPosition(String nameen) {
		HashMap<String, String> statusMap = new LinkedHashMap<>();
		statusMap.put(null, "全部");
		switch (nameen) {
			case BusinessConstants.EMPLOYEE_STAFF:
				statusMap.put(BusinessConstants.NEW_CREATE, BusinessConstants.NEW_CREATE);
				statusMap.put(BusinessConstants.ALREADY_COMMIT, BusinessConstants.ALREADY_COMMIT);
				statusMap.put(BusinessConstants.WAIT_EXAMINE, BusinessConstants.WAIT_EXAMINE);
				statusMap.put(BusinessConstants.ALREADY_RETURN, BusinessConstants.ALREADY_RETURN);
				statusMap.put(BusinessConstants.ALREADY_EXAMINE, BusinessConstants.ALREADY_EXAMINE);
				statusMap.put(BusinessConstants.ALREADY_PAYMENT, BusinessConstants.ALREADY_PAYMENT);
				statusMap.put(BusinessConstants.ALREADY_TERMINATION, BusinessConstants.ALREADY_TERMINATION);
				break;
				/* 报销单状态，可取值为：新创建、已提交、待审批、已打回、已审批、已付款、已终止 */
			case BusinessConstants.EMPLOYEE_MANAGER:
			case BusinessConstants.EMPLOYEE_GENERAL_MANAGER:
				statusMap.put(BusinessConstants.ALREADY_COMMIT, BusinessConstants.ALREADY_COMMIT);
				statusMap.put(BusinessConstants.WAIT_EXAMINE, BusinessConstants.WAIT_EXAMINE);
				statusMap.put(BusinessConstants.ALREADY_RETURN, BusinessConstants.ALREADY_RETURN);
				statusMap.put(BusinessConstants.ALREADY_EXAMINE, BusinessConstants.ALREADY_EXAMINE);
				statusMap.put(BusinessConstants.ALREADY_PAYMENT, BusinessConstants.ALREADY_PAYMENT);
				statusMap.put(BusinessConstants.ALREADY_TERMINATION, BusinessConstants.ALREADY_TERMINATION);
				break;
			default:
				statusMap.put(BusinessConstants.ALREADY_EXAMINE, BusinessConstants.ALREADY_EXAMINE);
				statusMap.put(BusinessConstants.ALREADY_PAYMENT, BusinessConstants.ALREADY_PAYMENT);
				break;
		}
		return statusMap;
	}
	@Override
	public Pagination<ClaimVoucher> getClaimVoucherList(Integer pageIndex, Integer pageSize, Employee employee,
			ClaimVoucher claimVoucher, Date beginTime, Date endTime) {
		Validate.notNull(employee, "查询报销单的用户对象不能为空！");
		Validate.notNull(employee.getPosition(), "查询报销单的用户职位不能为空！");
		Validate.notNull(employee, "查询条件不能为空！");
		String nameen = employee.getPosition().getNameen();
		if(BusinessConstants.EMPLOYEE_STAFF.equals(nameen)){
			claimVoucher.setCreator(employee);
		}else{
			claimVoucher.setNextDeal(employee);
		}
		return new DefaultPagination<>(pageIndex, pageSize, new QueryHander<ClaimVoucher>(){

			@Override
			public Integer getTotalLines() {
				switch (nameen) {
					case BusinessConstants.EMPLOYEE_STAFF:
						return claimVoucherDao.countByStaff(claimVoucher, beginTime, endTime);
					case BusinessConstants.EMPLOYEE_MANAGER:
						return claimVoucherDao.countByManager(claimVoucher, beginTime, endTime);
					case BusinessConstants.EMPLOYEE_GENERAL_MANAGER:
						return claimVoucherDao.countByGeneralManager(claimVoucher, beginTime, endTime);
					default:
						return claimVoucherDao.countByCashier(claimVoucher, beginTime, endTime);
				}
			}

			@Override
			public List<ClaimVoucher> getList(Integer pageIndex, Integer pageSize) {
				Integer begin = (pageIndex-1) * pageSize;
				switch (nameen) {
					case BusinessConstants.EMPLOYEE_STAFF:
						return claimVoucherDao.listByStaff(begin, pageSize, claimVoucher, beginTime, endTime);
					case BusinessConstants.EMPLOYEE_MANAGER:
						return claimVoucherDao.listByManager(begin, pageSize, claimVoucher, beginTime, endTime);
					case BusinessConstants.EMPLOYEE_GENERAL_MANAGER:
						return claimVoucherDao.listByGeneralManager(begin, pageSize, claimVoucher, beginTime, endTime);
					default:
						return claimVoucherDao.listByCashier(begin, pageSize, claimVoucher, beginTime, endTime);
				}
			}
			
		});
	}
	@Override
	public ClaimVoucher getClaimVoucherById(Integer id, boolean result) {
		ClaimVoucher claimVoucher = claimVoucherDao.get(id);
		claimVoucher.setClaimVoucherDetails(claimVoucherDetailDao.listByClaimVoucherId(id));
		if(result){
			claimVoucher.setCheckResults(checkResultDao.listByClaimVoucherId(id));
		}
		return claimVoucher;
	}
	@Override
	public void editClaimVoucher(ClaimVoucher claimVoucher) {
		claimVoucher.setModifyTime(new Date());
		claimVoucher.setTotalAccount(claimVoucher.calcTotalAccount());
		
		if(claimVoucher.getStatus().equals(BusinessConstants.ALREADY_COMMIT)){
			claimVoucher.setNextDeal(employeeDao.getManagerByStaff(claimVoucher.getCreator().getSn()));
		}else{
			claimVoucher.setNextDeal(claimVoucher.getCreator());
		}
		
		List<ClaimVoucherDetail> oldDetails = claimVoucherDetailDao.listByClaimVoucherId(claimVoucher.getId());
		List<ClaimVoucherDetail> newDetails = claimVoucher.getClaimVoucherDetails();
		
		claimVoucherDao.update(claimVoucher);
		
		for (ClaimVoucherDetail oldDetail : oldDetails) {
			if (!newDetails.contains(oldDetail)) {//记得重写hashCode和equals方法
				claimVoucherDetailDao.remove(oldDetail.getId());
			}
		}
		for (ClaimVoucherDetail newDetail : newDetails) {
			if (oldDetails.contains(newDetail)) {
				claimVoucherDetailDao.update(newDetail);
			}
		}
		newDetails.removeAll(oldDetails);
		for (ClaimVoucherDetail newDetail : newDetails) {
			newDetail.setClaimVoucher(claimVoucher);
			claimVoucherDetailDao.save(newDetail);
		}
	}
	
	@Override
	public void removeClaimVoucherById(Integer id) {
			//删除报销单详情信息
			claimVoucherDetailDao.remove(id);
			//删除报销单审查结果信息
			checkResultDao.remove(id);
			//删除报销单信息
			claimVoucherDao.remove(id);
	}
	@Override
	public void addClaimVoucher(ClaimVoucher claimVoucher) {
		claimVoucher.setCreateTime(new Date());
		claimVoucher.setTotalAccount(claimVoucher.calcTotalAccount());
		//claimVoucher.setNextDeal(employeeDao.getManagerByStaff(claimVoucher.getCreator().getSn()));
		
		claimVoucherDao.save(claimVoucher);
		
		for (ClaimVoucherDetail detail : claimVoucher.getClaimVoucherDetails()) {
			detail.setClaimVoucher(claimVoucher);
			claimVoucherDetailDao.save(detail);
		}
	}
	
	@Override
	public void submitClaimVoucher(Integer id) {
		ClaimVoucher claimVoucher = claimVoucherDao.get(id);
		if(claimVoucher.getStatus().equals(BusinessConstants.NEW_CREATE)){
			claimVoucher.setNextDeal(employeeDao.getManagerByStaff(claimVoucher.getCreator().getSn()));
			claimVoucher.setCreateTime(new Date());
			claimVoucher.setStatus(BusinessConstants.ALREADY_COMMIT);
			claimVoucherDao.update(claimVoucher);
		}
	}
	
}
