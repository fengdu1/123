package com.jboa.service;

import java.util.Date;
import java.util.Map;

import org.pagination.Pagination;

import com.jboa.pojo.ClaimVoucher;
import com.jboa.pojo.Employee;

public interface ClaimVoucherService {

	public Map<String, String> getStatusByPosition(String nameen);

	public Pagination<ClaimVoucher> getClaimVoucherList(Integer pageIndex, Integer pageSize, Employee employee, ClaimVoucher model,
			Date beginTime, Date endTime);

	public ClaimVoucher getClaimVoucherById(Integer id, boolean result);

	public void editClaimVoucher(ClaimVoucher claimVoucher);

	public void removeClaimVoucherById(Integer id);

	public void addClaimVoucher(ClaimVoucher claimVoucher);

	public void submitClaimVoucher(Integer id);

}
