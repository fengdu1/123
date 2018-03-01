drop database if exists jboa;
create database jboa character set utf8;

use jboa;

/* 部门表 */
create table sys_department 
(
	/* 部门ID */
	id int primary key auto_increment ,
	/* 部门名称 */
	name varchar(50) not null
) engine = InnoDB;

/* 职位表 */
create table sys_position
(
	/* 职位ID */
	id int primary key auto_increment ,
	/* 职位中文名称 */
	namecn varchar(50) not null ,
	/* 职位英文名称 */
  	nameen varchar(50) not null
) engine = InnoDB;

/* 员工表 */
create table sys_employee
(
	/* 员工编号 */
	sn varchar(50) primary key not null ,
	/* 职位编号，职位表sys_position的id列外键 */
	positionId int not null ,
	/* 部门编号，部门表sys_deparment的id列外键 */
	departmentId int not null ,
	/* 员工姓名 */
	name varchar(50) not null ,
	/* 员工密码 */
	password varchar(50) not null ,
	/* 员工状态，可取值为：在职、离职 */
	status varchar(20) not null
) engine = InnoDB;

alter table sys_employee	/* 外键约束，员工职位 */
	add constraint FK_sys_employee__positionId foreign key(positionId) references sys_position(id);
alter table sys_employee	/* 外键约束，员工部门 */
	add constraint FK_sys_employee__departmentId foreign key(departmentId) references sys_department(id);
alter table sys_employee	/* 检查约束，员工状态 */
	add constraint CK_sys_employee__status check(status in('在职', '离职'));

/* 报销单表 */
create table biz_claim_voucher
(
	/* 报销单ID */
	id int primary key auto_increment ,
	/* 下一个处理人编号，员工表sys_employee的sn列外键 */
  	nextDealSn varchar(50) ,
	/* 报销单创建人编号，员工表sys_employee的sn列外键 */
  	createSn varchar(50) not null ,
	/* 报销单创建时间 */
  	createTime datetime not null ,
	/* 报销事由 */
	event varchar(255) not null ,
	/* 报销总金额 */
	totalAccount double not null ,
	/* 报销单状态，可取值为：新创建、已提交、待审批、已打回、已审批、已付款、已终止 */
	status varchar(20) not null ,
	/* 报销单最后修改时间 */
	modifyTime datetime
) engine = InnoDB;

alter table biz_claim_voucher	/* 外键约束，报销单下一个审核人 */
	add constraint FK_biz_claim_voucher__nextDealSn foreign key(nextDealSn) references sys_employee(sn);
alter table biz_claim_voucher	/* 外键约束，报销单创建人 */
	add constraint FK_biz_claim_voucher__createSn foreign key(createSn) references sys_employee(sn);
alter table biz_claim_voucher	/* 检查约束，报销单状态 */
	add constraint CK_biz_claim_voucher__status check(status in('新创建', '已提交', '待审批', '已打回', '已审批', '已付款', '已终止'));
alter table biz_claim_voucher	/* 检查约束，报销单最后修改时间如果不为空，则一定要大于报销单创建时间 */
	add constraint CK_biz_claim_voucher__modifyTime check(modifyTime is null or modifyTime > createTime);
alter table biz_claim_voucher	/* 检查约束，报销单下一个审核人如果不为空，则一定不能和报销单创建人是同一个人 */
	add constraint CK_biz_claim_voucher__nextDealSn check(nextDealSn is null or nextDealSn <> createSn);

/* 审查结果表 */
create table biz_check_result
(
	/* 审查结果ID */
	id int primary key auto_increment ,
	/* 报销单编号，报销单表biz_claim_voucher的id列外键 */
	claimId int not null ,
	/* 审查时间 */
	checkTime datetime not null ,
	/* 审查人编号，员工表sys_employee的sn列外键 */
	checkerSn varchar(50) not null ,
	/* 审查结果，可取值为：通过、打回、拒绝、付款 */
	result varchar(50) not null ,
	/* 审批意见 */
	comment varchar(500) not null
) engine = InnoDB;

alter table biz_check_result	/* 外键约束，是哪张报销单的审核结果 */
	add constraint FK_biz_check_result__claimId foreign key(claimId) references biz_claim_voucher(id);
alter table biz_check_result	/* 外键约束，是谁审查的报销单 */
	add constraint FK_biz_check_result__checkerSn foreign key(checkerSn) references sys_employee(sn);
alter table biz_check_result	/* 检查约束，报销单审查结果 */
	add constraint CK_biz_check_result__result check(result in('通过', '打回', '拒绝', '付款'));

/* 报销单明细表 */
create table biz_claim_voucher_detail
(
	/* 报销单明细ID */
  	id int primary key auto_increment ,
	/* 报销单编号，报销单表biz_claim_voucher的id列外键 */
	mainId int not null ,
	/* 明细项名称，可取值为：礼品费、办公费、住宿费、餐饮费、交际餐费、城际交通费、市内交通费 */
	item varchar(20) not null ,
	/* 明细费用 */
  	account double not null ,
	/* 明细描述 */
  	description varchar(200) not null
) engine = InnoDB;
insert into biz_claim_voucher_detail value(null,3,'餐饮费',300,'请客户吃饭');
alter table biz_claim_voucher_detail	/* 外键约束，是哪张报销单的明细 */
	add constraint FK_biz_claim_voucher_detail__mainId foreign key(mainId) references biz_claim_voucher(id);
alter table biz_claim_voucher_detail	/* 检查约束，报销明细项 */
	add constraint CK_biz_claim_voucher_detail__item check(item in('礼品费', '办公费', '住宿费', '餐饮费', '交际餐费', '城际交通费', '市内交通费'));
alter table biz_claim_voucher_detail	/* 检查约束，报销明细金额必须大于0 */
	add constraint CK_biz_claim_voucher_detail__account check(account > 0);

/* 报销单月度统计表 */
create table biz_claim_voucher_statistics
(
	/* 报销单月度统计ID */
	id int primary key auto_increment ,
	/* 月度统计总金额 */
	totalMoney double not null ,
	/* 被统计的年份 */
	year int not null ,
	/* 被统计的月份 */
	month int not null ,
	/* 被统计的部门编号，部门表sys_deparment的id列外键 */
	departmentId int not null ,
	/* 统计时间 */
	modifyTime datetime not null
) engine = InnoDB;

alter table biz_claim_voucher_statistics	/* 外键约束，是哪个部门的报销单月度统计 */
	add constraint FK_biz_claim_voucher_statistics__departmentId foreign key(departmentId) references sys_department(id);
alter table biz_claim_voucher_statistics	/* 检查约束，月度统计报销金额必须大于0 */
	add constraint CK_biz_claim_voucher_statistics__totalMoney check(totalMoney > 0);
alter table biz_claim_voucher_statistics	/* 检查约束，月度所属年必须是20开头，后面两位是数字 */
	add constraint CK_biz_claim_voucher_statistics__year check(year like '20[0-9][0-9]');
alter table biz_claim_voucher_statistics	/* 检查约束，月度必须介于1到12之间 */
	add constraint CK_biz_claim_voucher_statistics__month check(month between 1 and 12);

/* 报销单年度统计表 */
create table biz_claim_vouyear_statistics
(
	/* 报销单年度统计ID */
	id int primary key auto_increment ,
	/* 年度统计总金额 */
	totalMoney double not null ,
	/* 被统计的年份 */
	year int not null ,
	/* 被统计的部门编号，部门表sys_deparment的id列外键 */
	departmentId int not null ,
	/* 统计时间 */
	modifyTime datetime not null
) engine = InnoDB;

alter table biz_claim_vouyear_statistics	/* 外键约束，是哪个部门的报销单年度统计 */
	add constraint FK_biz_claim_vouyear_statistics__departmentId foreign key(departmentId) references sys_department(id);
alter table biz_claim_vouyear_statistics	/* 检查约束，年度统计报销金额必须大于0 */
	add constraint CK_biz_claim_vouyear_statistics__totalMoney check(totalMoney > 0);
alter table biz_claim_vouyear_statistics	/* 检查约束，年度必须是20开头，后面两位是数字 */
	add constraint CK_biz_claim_vouyear_statistics__year check(year like '20[0-9][0-9]');

/* 请假表 */
create table biz_leave
(
	/* 请假ID */
	id int primary key auto_increment ,
	/* 请假人编号，员工表sys_employee的sn列外键 */
	employeeSn varchar(50) not null ,
	/* 请假开始时间 */
	startTime datetime not null ,
	/* 请假结束时间 */
	endTime datetime not null ,
	/* 请假天数，最小支持到0.5天 */
	leaveDay double not null ,
	/* 请假事由 */
	reason varchar(500) not null ,
	/* 请假状态，可取值为：待审批、已审批、已打回 */
	status varchar(20) not null ,
	/* 休假类型，可取值为：事假、婚假、产假、病假、年假 */
	leaveType varchar(50) not null ,
	/* 下一个处理人编号，员工表sys_employee的sn列外键 */
  	nextDealSn varchar(50) not null ,
	/* 审批意见 */
	approveOpinion varchar(100) ,
	/* 请假创建时间 */	
 	createTime datetime not null ,
	/* 最后修改时间 */
	modifyTime datetime
) engine = InnoDB;

alter table biz_leave	/* 外键约束，是哪个员工的请假单 */
	add constraint FK_biz_leave__employeeSn foreign key(employeeSn) references sys_employee(sn);
alter table biz_leave	/* 外键约束，下一个审核请假单的人 */
	add constraint FK_biz_leave__nextDealSn foreign key(nextDealSn) references sys_employee(sn);
alter table biz_leave	/* 检查约束，开始休假时间不能小于结束休假时间，也不能小于创建时间，即不能先休假后补假条 */
	add constraint CK_biz_leave__startTime check(startTime < endTime and startTime > createTime);
alter table biz_leave	/* 检查约束，休假天数至少为0.5 */
	add constraint CK_biz_leave__leaveDay check(leaveDay >= 0.5);
alter table biz_leave	/* 检查约束，休假审批状态 */
	add constraint CK_biz_leave__status check(status in('待审批','已审批','已打回'));
alter table biz_leave	/* 检查约束，休假状态 */
	add constraint CK_biz_leave__leaveType check(leaveType in('事假','婚假','产假','病假','年假'));
alter table biz_leave	/* 检查约束，请假条最后修改时间如果不为空，则不能小于创建创建 */
	add constraint CK_biz_leave__modifyTime check(modifyTime is null or modifyTime > createTime);
