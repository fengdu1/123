/**
 * 
 */
package com.jboa.dao.impl; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jboa.dao.BaseDao;
import com.jboa.dao.ClaimVoucherDao;
import com.jboa.pojo.ClaimVoucher;
import com.jboa.pojo.Department;
import com.jboa.pojo.Employee;
import com.jboa.pojo.Position;

/**
 * 报销单数据访问的mysql实现类
 */
//@Repository("claimVoucherDao")
public class ClaimVoucherDaoImpl extends BaseDao implements ClaimVoucherDao {
	private static final Logger LOG = LogManager.getLogger(ClaimVoucherDaoImpl.class);

	@Override
	public Integer countByStaff(ClaimVoucher claimVoucher, Date beginTime, Date endTime) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder
			.append("select")
			.append(" count(1)")
			.append(" from biz_claim_voucher")
			.append(" where createSn = ?");
		params.add(claimVoucher.getCreator().getSn());
		if (ObjectUtils.anyNotNull(beginTime)) {
			builder.append(" and createTime >= '").append(DateFormatUtils.format(beginTime, "yyyy-MM-dd 00:00:00")).append("'");
		}
		if (ObjectUtils.anyNotNull(endTime)) {
			builder.append(" and createTime <= '").append(DateFormatUtils.format(endTime, "yyyy-MM-dd 23:59:59")).append("'");
		}
		if (StringUtils.isNotEmpty(claimVoucher.getStatus())) {
			builder.append(" and status = ?");
			params.add(claimVoucher.getStatus());
		}
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().queryForObject(builder.toString(), Integer.class, params.toArray());
	}

	@Override
	public Integer countByManager(ClaimVoucher claimVoucher, Date beginTime, Date endTime) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder
		.append("select")
		.append(" count(1)")
		.append(" from biz_claim_voucher where createSn in (")
		.append(" select sn from sys_employee where departmentId = ?")
		.append(" ) and status != '新创建'");
		
		params.add(claimVoucher.getNextDeal().getDepartment().getId());
		
		if (ObjectUtils.anyNotNull(beginTime)) {
			builder.append(" and createTime >= '").append(DateFormatUtils.format(beginTime, "yyyy-MM-dd 00:00:00")).append("'");
		}
		if (ObjectUtils.anyNotNull(endTime)) {
			builder.append(" and createTime <= '").append(DateFormatUtils.format(endTime, "yyyy-MM-dd 23:59:59")).append("'");
		}
		if (StringUtils.isNotEmpty(claimVoucher.getStatus())) {
			builder.append(" and status = ?");
			params.add(claimVoucher.getStatus());
		}
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().queryForObject(builder.toString(), Integer.class, params.toArray());
	}

	@Override
	public Integer countByGeneralManager(ClaimVoucher claimVoucher, Date beginTime, Date endTime) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder
		.append("select")
		.append(" count(1)")
		.append(" from biz_claim_voucher")
		.append(" where status != '新创建'");
		
		if (ObjectUtils.anyNotNull(beginTime)) {
			builder.append(" and createTime >= '").append(DateFormatUtils.format(beginTime, "yyyy-MM-dd 00:00:00")).append("'");
		}
		if (ObjectUtils.anyNotNull(endTime)) {
			builder.append(" and createTime <= '").append(DateFormatUtils.format(endTime, "yyyy-MM-dd 23:59:59")).append("'");
		}
		if (StringUtils.isNotEmpty(claimVoucher.getStatus())) {
			builder.append(" and status = ?");
			params.add(claimVoucher.getStatus());
		}
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().queryForObject(builder.toString(), Integer.class, params.toArray());
	}

	@Override
	public Integer countByCashier(ClaimVoucher claimVoucher, Date beginTime, Date endTime) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder
		.append("select")
		.append(" count(1)")
		.append(" from biz_claim_voucher")
		.append(" where 1 = 1");
		
//		params.add(claimVoucher.getNextDeal().getSn());
		
		if (ObjectUtils.anyNotNull(beginTime)) {
			builder.append(" and createTime >= '").append(DateFormatUtils.format(beginTime, "yyyy-MM-dd 00:00:00")).append("'");
		}
		if (ObjectUtils.anyNotNull(endTime)) {
			builder.append(" and createTime <= '").append(DateFormatUtils.format(endTime, "yyyy-MM-dd 23:59:59")).append("'");
		}
		if (StringUtils.isNotEmpty(claimVoucher.getStatus())) {
			builder.append(" and status = ?");
			params.add(claimVoucher.getStatus());
		} else {
			builder.append(" and status in ('已审批', '已付款')");
		}
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().queryForObject(builder.toString(), Integer.class, params.toArray());
	}

	@Override
	public List<ClaimVoucher> listByStaff(Integer begin, Integer size, ClaimVoucher claimVoucher, Date beginTime, Date endTime) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder
			.append("select")
			.append(" id, createTime, totalAccount, status, nextDealSn,")
			.append(" (select name from sys_employee where sn = nextDealSn) nextDealName")
			.append(" from biz_claim_voucher")
			.append(" where createSn = ?");
		params.add(claimVoucher.getCreator().getSn());
		if (ObjectUtils.anyNotNull(beginTime)) {
			builder.append(" and createTime >= '").append(DateFormatUtils.format(beginTime, "yyyy-MM-dd 00:00:00")).append("'");
		}
		if (ObjectUtils.anyNotNull(endTime)) {
			builder.append(" and createTime <= '").append(DateFormatUtils.format(endTime, "yyyy-MM-dd 23:59:59")).append("'");
		}
		if (StringUtils.isNotEmpty(claimVoucher.getStatus())) {
			builder.append(" and status = ? order by createTime desc");
			params.add(claimVoucher.getStatus());
		} else {
			builder.append(" order by field(status, '新创建', '已提交', '待审批', '已打回', '已审批', '已付款', '已终止'), createTime desc");
		}
		builder.append(" limit ?, ?");
		params.add(begin);
		params.add(size);
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().query(builder.toString(), new RowMapper<ClaimVoucher>() {
			@Override
			public ClaimVoucher mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClaimVoucher cv = new ClaimVoucher();
				
				cv.setId(rs.getInt("id"));
				cv.setCreateTime(rs.getTimestamp("createTime"));
				cv.setTotalAccount(rs.getDouble("totalAccount"));
				cv.setStatus(rs.getString("status"));
				
				cv.setCreator(claimVoucher.getCreator());
				
				Employee nextDeal = new Employee();
				nextDeal.setSn(rs.getString("nextDealSn"));
				nextDeal.setName(rs.getString("nextDealName"));
				cv.setNextDeal(nextDeal);
				
				return cv;
			}
		}, params.toArray());
	}

	@Override
	public List<ClaimVoucher> listByManager(Integer begin, Integer size, ClaimVoucher claimVoucher, Date beginTime, Date endTime) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder
			.append("select")
			.append(" id, createTime, totalAccount, status,")
			.append(" createSn, (select name from sys_employee where sn = createSn) createName,")
			.append(" nextDealSn, (select name from sys_employee where sn = nextDealSn) nextDealName")
			.append(" from biz_claim_voucher where createSn in (")
			.append(" select sn from sys_employee where departmentId = ?")
			.append(" ) and status != '新创建'");
		
		params.add(claimVoucher.getNextDeal().getDepartment().getId());
		
		if (ObjectUtils.anyNotNull(beginTime)) {
			builder.append(" and createTime >= '").append(DateFormatUtils.format(beginTime, "yyyy-MM-dd 00:00:00")).append("'");
		}
		if (ObjectUtils.anyNotNull(endTime)) {
			builder.append(" and createTime <= '").append(DateFormatUtils.format(endTime, "yyyy-MM-dd 23:59:59")).append("'");
		}
		if (StringUtils.isNotEmpty(claimVoucher.getStatus())) {
			builder.append(" and status = ? order by createTime desc");
			params.add(claimVoucher.getStatus());
		} else {
			builder.append(" order by field(status, '已提交', '待审批', '已打回', '已审批', '已付款', '已终止'), createTime desc");
		}
		builder.append(" limit ?, ?");
		params.add(begin);
		params.add(size);
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().query(builder.toString(), new RowMapper<ClaimVoucher>() {
			@Override
			public ClaimVoucher mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClaimVoucher cv = new ClaimVoucher();
				
				cv.setId(rs.getInt("id"));
				cv.setCreateTime(rs.getTimestamp("createTime"));
				cv.setTotalAccount(rs.getDouble("totalAccount"));
				cv.setStatus(rs.getString("status"));
				
				Employee creator = new Employee();
				creator.setSn(rs.getString("createSn"));
				creator.setName(rs.getString("createName"));
				cv.setCreator(creator);
				
				Employee nextDeal = new Employee();
				nextDeal.setSn(rs.getString("nextDealSn"));
				nextDeal.setName(rs.getString("nextDealName"));
				cv.setNextDeal(nextDeal);
				
				return cv;
			}
		}, params.toArray());
	}

	@Override
	public List<ClaimVoucher> listByGeneralManager(Integer begin, Integer size, ClaimVoucher claimVoucher, Date beginTime, Date endTime) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder
		.append("select")
		.append(" id, createTime, totalAccount, status,")
		.append(" createSn, (select name from sys_employee where sn = createSn) createName,")
		.append(" nextDealSn, (select name from sys_employee where sn = nextDealSn) nextDealName")
		.append(" from biz_claim_voucher")
		.append(" where status != '新创建'");
		
		if (ObjectUtils.anyNotNull(beginTime)) {
			builder.append(" and createTime >= '").append(DateFormatUtils.format(beginTime, "yyyy-MM-dd 00:00:00")).append("'");
		}
		if (ObjectUtils.anyNotNull(endTime)) {
			builder.append(" and createTime <= '").append(DateFormatUtils.format(endTime, "yyyy-MM-dd 23:59:59")).append("'");
		}
		if (StringUtils.isNotEmpty(claimVoucher.getStatus())) {
			builder.append(" and status = ? order by createTime desc");
			params.add(claimVoucher.getStatus());
		} else {
			builder.append(" order by field(status, '已提交', '待审批', '已打回', '已审批', '已付款', '已终止'), createTime desc");
		}
		builder.append(" limit ?, ?");
		params.add(begin);
		params.add(size);
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().query(builder.toString(), new RowMapper<ClaimVoucher>() {
			@Override
			public ClaimVoucher mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClaimVoucher cv = new ClaimVoucher();
				
				cv.setId(rs.getInt("id"));
				cv.setCreateTime(rs.getTimestamp("createTime"));
				cv.setTotalAccount(rs.getDouble("totalAccount"));
				cv.setStatus(rs.getString("status"));
				
				Employee creator = new Employee();
				creator.setSn(rs.getString("createSn"));
				creator.setName(rs.getString("createName"));
				cv.setCreator(creator);
				
				Employee nextDeal = new Employee();
				nextDeal.setSn(rs.getString("nextDealSn"));
				nextDeal.setName(rs.getString("nextDealName"));
				cv.setNextDeal(nextDeal);
				
				return cv;
			}
		}, params.toArray());
	}

	@Override
	public List<ClaimVoucher> listByCashier(Integer begin, Integer size, ClaimVoucher claimVoucher, Date beginTime, Date endTime) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder
			.append("select")
			.append(" cv.id, cv.createTime, cv.totalAccount, cv.status,")
			.append(" createSn, creator.name createName,")
			.append(" nextDealSn, nextDeal.name nextDealName")
			.append(" from biz_claim_voucher cv")
			.append(" inner join sys_employee creator on creator.sn = cv.createSn")
			.append(" left join sys_employee nextDeal on nextDeal.sn = cv.nextDealSn")
			.append(" where cv.status in ('已审批', '已付款')");
		
//		params.add(claimVoucher.getNextDeal().getSn());
		
		if (ObjectUtils.anyNotNull(beginTime)) {
			builder.append(" and cv.createTime >= '").append(DateFormatUtils.format(beginTime, "yyyy-MM-dd 00:00:00")).append("'");
		}
		if (ObjectUtils.anyNotNull(endTime)) {
			builder.append(" and cv.createTime <= '").append(DateFormatUtils.format(endTime, "yyyy-MM-dd 23:59:59")).append("'");
		}
		if (StringUtils.isNotEmpty(claimVoucher.getStatus())) {
			builder.append(" and cv.status = ? order by cv.createTime desc");
			params.add(claimVoucher.getStatus());
		} else {
			builder.append(" or cv.status in ('已审批', '已付款') order by field(cv.status, '已审批', '已付款'), cv.createTime desc");
		}
		builder.append(" limit ?, ?");
		params.add(begin);
		params.add(size);
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().query(builder.toString(), new RowMapper<ClaimVoucher>() {
			@Override
			public ClaimVoucher mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClaimVoucher cv = new ClaimVoucher();
				
				cv.setId(rs.getInt("id"));
				cv.setCreateTime(rs.getTimestamp("createTime"));
				cv.setTotalAccount(rs.getDouble("totalAccount"));
				cv.setStatus(rs.getString("status"));
				
				Employee creator = new Employee();
				creator.setSn(rs.getString("createSn"));
				creator.setName(rs.getString("createName"));
				cv.setCreator(creator);
				
				Employee nextDeal = new Employee();
				nextDeal.setSn(rs.getString("nextDealSn"));
				nextDeal.setName(rs.getString("nextDealName"));
				cv.setNextDeal(nextDeal);
				
				return cv;
			}
		}, params.toArray());
	}
	
	@Override
	public int save(ClaimVoucher claimVoucher) {
		String sql = "insert into biz_claim_voucher values(null, ?, ?, ?, ?, ?, ?, null)";
		List<Object> params = new ArrayList<>();
		params.add(ObjectUtils.anyNotNull(claimVoucher.getNextDeal()) 
				? claimVoucher.getNextDeal().getSn() 
				: null);
		params.add(claimVoucher.getCreator().getSn());
		params.add(claimVoucher.getCreateTime());
		params.add(claimVoucher.getEvent());
		params.add(claimVoucher.getTotalAccount());
		params.add(claimVoucher.getStatus());
		
		LOG.debug("T-SQL：" + sql);
		LOG.debug("Params：" + params);
		
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		
		int row = getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, ObjectUtils.anyNotNull(claimVoucher.getNextDeal()) 
						? claimVoucher.getNextDeal().getSn() 
						: null);
				pstmt.setString(2, claimVoucher.getCreator().getSn());
				pstmt.setTimestamp(3, new Timestamp(claimVoucher.getCreateTime().getTime()));
				pstmt.setString(4, claimVoucher.getEvent());
				pstmt.setDouble(5, claimVoucher.getTotalAccount());
				pstmt.setString(6, claimVoucher.getStatus());
				return pstmt;
			}
		}, generatedKeyHolder);
		
		claimVoucher.setId(generatedKeyHolder.getKey().intValue());
		
		return row;
	}
	
	@Override
	public ClaimVoucher get(Integer id) {
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		builder.append("select")
		.append(" c.id, c.totalAccount, c.createTime, c.status, c.event,")
		.append(" c.createSn, e1.name createName,")
		.append(" d.id departmentId, d.name departmentName,")
		.append(" p.id positionId, p.namecn, p.nameen,")
		.append(" c.nextDealSn, e2.name nextDealName")
		.append(" from biz_claim_voucher c")
		.append(" inner join sys_employee e1 on e1.sn = c.createSn")
		.append(" left join sys_employee e2 on e2.sn = c.nextDealSn")
		.append(" inner join sys_position p on p.id = e1.positionId")
		.append(" inner join sys_department d on d.id = e1.departmentId")
		.append(" where c.id = ?");
		
		params.add(id);
		
		LOG.debug("T-SQL：" + builder.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().queryForObject(builder.toString(), new RowMapper<ClaimVoucher>() {
			@Override
			public ClaimVoucher mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClaimVoucher claimVoucher = new ClaimVoucher();
				
				claimVoucher.setId(rs.getInt("id"));
				claimVoucher.setCreateTime(rs.getTimestamp("createTime"));
				claimVoucher.setTotalAccount(rs.getDouble("totalAccount"));
				claimVoucher.setStatus(rs.getString("status"));
				claimVoucher.setEvent(rs.getString("event"));
				
				Employee creator = new Employee();
				creator.setSn(rs.getString("createSn"));
				creator.setName(rs.getString("createName"));
				claimVoucher.setCreator(creator);
				
				Department department = new Department();
				department.setId(rs.getInt("departmentId"));
				department.setName(rs.getString("departmentName"));
				creator.setDepartment(department);
				
				Position position = new Position();
				position.setId(rs.getInt("positionId"));
				position.setNamecn(rs.getString("namecn"));
				position.setNameen(rs.getString("nameen"));
				creator.setPosition(position);
				
				Employee nextDeal = new Employee();
				nextDeal.setSn(rs.getString("nextDealSn"));
				nextDeal.setName(rs.getString("nextDealName"));
				claimVoucher.setNextDeal(nextDeal);
				
				return claimVoucher;
			}
		}, params.toArray());
	}
	
	@Override
	public int update(ClaimVoucher claimVoucher) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update biz_claim_voucher set")
			.append(" nextDealSn = ?,")
			.append(" modifyTime = ?,")
			.append(" event = ?,")
			.append(" totalAccount = ?,")
			.append(" status = ?")
			.append(" where id = ?");
		List<Object> params = new ArrayList<>();
		params.add(ObjectUtils.anyNotNull(claimVoucher.getNextDeal()) 
				? claimVoucher.getNextDeal().getSn() 
				: null);
		params.add(claimVoucher.getModifyTime());
		params.add(claimVoucher.getEvent());
		params.add(claimVoucher.getTotalAccount());
		params.add(claimVoucher.getStatus());
		params.add(claimVoucher.getId());
		
		LOG.debug("T-SQL：" + buffer.toString());
		LOG.debug("Params：" + params);
		
		return getJdbcTemplate().update(buffer.toString(), params.toArray());
	}
	
	@Override
	public int update(Integer id) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("update biz_claim_voucher set")
			.append(" status = '已提交'")
			.append(" where id = ?");
		
		LOG.debug("T-SQL：" + buffer.toString());
		LOG.debug("Params：" + id);
		
		return getJdbcTemplate().update(buffer.toString(), id);
	}
	
	@Override
	public int remove(Integer id) {
		String sql = "delete from biz_claim_voucher where id = ?";
		
		LOG.debug("T-SQL：" + sql);
		LOG.debug("Params：" + id);
		
		return getJdbcTemplate().update(sql, id);
	}
}
