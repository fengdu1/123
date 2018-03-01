/**
 * 
 */
package com.jboa.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jboa.dao.BaseDao;
import com.jboa.dao.ClaimVoucherDetailDao;
import com.jboa.pojo.ClaimVoucherDetail;
import com.mysql.cj.fabric.xmlrpc.base.Param;

/**
 * 报销单详情数据访问的mysql实现类
 */
//@Repository("claimVoucherDetailDao")
public class ClaimVoucherDetailDaoImpl extends BaseDao implements ClaimVoucherDetailDao {
	private static final Logger LOG = LogManager.getLogger(ClaimVoucherDetailDaoImpl.class);
	
	@Override
	public void save(ClaimVoucherDetail detail) {
		String sql = "insert into biz_claim_voucher_detail values(null, ?, ?, ?, ?)";
		List<Object> params = new ArrayList<>();
		params.add(detail.getClaimVoucher().getId());
		params.add(detail.getItem());
		params.add(detail.getAccount());
		params.add(detail.getDescription());
		
		LOG.debug("T-SQL：" + sql);
		LOG.debug("Params：" + params);
		
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, detail.getClaimVoucher().getId());
				pstmt.setString(2, detail.getItem());
				pstmt.setDouble(3, detail.getAccount());
				pstmt.setString(4, detail.getDescription());
				return pstmt;
			}
		}, generatedKeyHolder);
		detail.setId(generatedKeyHolder.getKey().intValue());
	}
	
	@Override
	public List<ClaimVoucherDetail> listByClaimVoucherId(Integer claimVoucherId) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder.append("select")
		.append(" id, item, account, description")
		.append(" from biz_claim_voucher_detail")
		.append(" where mainId = ?")
		.append(" order by account desc");
		
		params.add(claimVoucherId);
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().query(builder.toString(), new RowMapper<ClaimVoucherDetail>() {
			@Override
			public ClaimVoucherDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClaimVoucherDetail claimVoucherDetail = new ClaimVoucherDetail();
				
				claimVoucherDetail.setId(rs.getInt("id"));
				claimVoucherDetail.setItem(rs.getString("item"));
				claimVoucherDetail.setAccount(rs.getDouble("account"));
				claimVoucherDetail.setDescription(rs.getString("description"));
				
				return claimVoucherDetail;
			}
		}, params.toArray());
	}
	
	@Override
	public int update(ClaimVoucherDetail detail) {
		String sql = "update biz_claim_voucher_detail set item = ?, account = ?, description = ? where id = ?";
		List<Object> params = new ArrayList<>();
		params.add(detail.getItem());
		params.add(detail.getAccount());
		params.add(detail.getDescription());
		params.add(detail.getId());
		
		LOG.debug("T-SQL：" + sql);
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().update(sql, params.toArray());
	}
	
	@Override
	public int remove(Integer id) {
		String sql = "delete from biz_claim_voucher_detail where mainId = ?";
		
		LOG.debug("T-SQL：" + sql);
		LOG.debug("Params：" + id);
		
		return getJdbcTemplate().update(sql, id);
	}
}
