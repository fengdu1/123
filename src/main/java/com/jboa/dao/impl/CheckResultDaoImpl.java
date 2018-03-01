/**
 * 
 */
package com.jboa.dao.impl;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jboa.dao.BaseDao;
import com.jboa.dao.CheckResultDao;
import com.jboa.pojo.CheckResult;
import com.jboa.pojo.Employee;
import com.jboa.pojo.Position;

/**
 * 报销单审查结果数据访问的mysql实现类
 */
//@Repository("checkResultDao")
public class CheckResultDaoImpl extends BaseDao implements CheckResultDao {
	private static final Logger LOG = LogManager.getLogger(CheckResultDaoImpl.class);
	
	@Override
	public List<CheckResult> listByClaimVoucherId(Integer claimVoucherId) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder.append("select")
		.append(" r.id, r.checkTime, r.result, r.comment,")
		.append(" r.checkerSn, e.name,")
		.append(" p.id positionId, p.namecn, p.nameen")
		.append(" from biz_check_result r")
		.append(" inner join sys_employee e on e.sn = r.checkerSn")
		.append(" inner join sys_position p on p.id = e.positionId")
		.append(" where r.claimId = ?")
		.append(" order by checkTime asc");
		
		params.add(claimVoucherId);
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().query(builder.toString(), new RowMapper<CheckResult>() {
			@Override
			public CheckResult mapRow(ResultSet rs, int rowNum) throws SQLException {
				CheckResult checkResult = new CheckResult();
				
				checkResult.setId(rs.getInt("id"));
				checkResult.setCheckTime(rs.getTimestamp("checkTime"));
				checkResult.setResult(rs.getString("result"));
				checkResult.setComment(rs.getString("comment"));
				
				Employee checker = new Employee();
				checker.setSn(rs.getString("checkerSn"));
				checker.setName(rs.getString("name"));
				checkResult.setChecker(checker);
				
				Position position = new Position();
				position.setId(rs.getInt("positionId"));
				position.setNamecn(rs.getString("namecn"));
				position.setNameen(rs.getString("nameen"));
				checker.setPosition(position);
				
				return checkResult;
			}
		}, params.toArray());
	}
	
	@Override
	public int save(CheckResult checkResult) {
		String sql = "insert into biz_check_result values(null, ?, ?, ?, ?, ?)";
		List<Object> params = new ArrayList<>();
		params.add(checkResult.getClaimVoucher().getId());
		params.add(checkResult.getCheckTime());
		params.add(checkResult.getChecker().getSn());
		params.add(checkResult.getResult());
		params.add(checkResult.getComment());
		
		LOG.debug("T-SQL：" + sql);
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().update(sql, params.toArray());
	}
	
	@Override
	public int remove(Integer id) {
		String sql = "delete from biz_check_result where claimId = ?";
		
		LOG.debug("T-SQL：" + sql);
		LOG.debug("Params：" + id);
		
		return getJdbcTemplate().update(sql, id);
	}
}
