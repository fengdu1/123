drop database if exists jboa;
create database jboa character set utf8;

use jboa;

/* ���ű� */
create table sys_department 
(
	/* ����ID */
	id int primary key auto_increment ,
	/* �������� */
	name varchar(50) not null
) engine = InnoDB;

/* ְλ�� */
create table sys_position
(
	/* ְλID */
	id int primary key auto_increment ,
	/* ְλ�������� */
	namecn varchar(50) not null ,
	/* ְλӢ������ */
  	nameen varchar(50) not null
) engine = InnoDB;

/* Ա���� */
create table sys_employee
(
	/* Ա����� */
	sn varchar(50) primary key not null ,
	/* ְλ��ţ�ְλ��sys_position��id����� */
	positionId int not null ,
	/* ���ű�ţ����ű�sys_deparment��id����� */
	departmentId int not null ,
	/* Ա������ */
	name varchar(50) not null ,
	/* Ա������ */
	password varchar(50) not null ,
	/* Ա��״̬����ȡֵΪ����ְ����ְ */
	status varchar(20) not null
) engine = InnoDB;

alter table sys_employee	/* ���Լ����Ա��ְλ */
	add constraint FK_sys_employee__positionId foreign key(positionId) references sys_position(id);
alter table sys_employee	/* ���Լ����Ա������ */
	add constraint FK_sys_employee__departmentId foreign key(departmentId) references sys_department(id);
alter table sys_employee	/* ���Լ����Ա��״̬ */
	add constraint CK_sys_employee__status check(status in('��ְ', '��ְ'));

/* �������� */
create table biz_claim_voucher
(
	/* ������ID */
	id int primary key auto_increment ,
	/* ��һ�������˱�ţ�Ա����sys_employee��sn����� */
  	nextDealSn varchar(50) ,
	/* �����������˱�ţ�Ա����sys_employee��sn����� */
  	createSn varchar(50) not null ,
	/* ����������ʱ�� */
  	createTime datetime not null ,
	/* �������� */
	event varchar(255) not null ,
	/* �����ܽ�� */
	totalAccount double not null ,
	/* ������״̬����ȡֵΪ���´��������ύ�����������Ѵ�ء����������Ѹ������ֹ */
	status varchar(20) not null ,
	/* ����������޸�ʱ�� */
	modifyTime datetime
) engine = InnoDB;

alter table biz_claim_voucher	/* ���Լ������������һ������� */
	add constraint FK_biz_claim_voucher__nextDealSn foreign key(nextDealSn) references sys_employee(sn);
alter table biz_claim_voucher	/* ���Լ���������������� */
	add constraint FK_biz_claim_voucher__createSn foreign key(createSn) references sys_employee(sn);
alter table biz_claim_voucher	/* ���Լ����������״̬ */
	add constraint CK_biz_claim_voucher__status check(status in('�´���', '���ύ', '������', '�Ѵ��', '������', '�Ѹ���', '����ֹ'));
alter table biz_claim_voucher	/* ���Լ��������������޸�ʱ�������Ϊ�գ���һ��Ҫ���ڱ���������ʱ�� */
	add constraint CK_biz_claim_voucher__modifyTime check(modifyTime is null or modifyTime > createTime);
alter table biz_claim_voucher	/* ���Լ������������һ������������Ϊ�գ���һ�����ܺͱ�������������ͬһ���� */
	add constraint CK_biz_claim_voucher__nextDealSn check(nextDealSn is null or nextDealSn <> createSn);

/* ������� */
create table biz_check_result
(
	/* �����ID */
	id int primary key auto_increment ,
	/* ��������ţ���������biz_claim_voucher��id����� */
	claimId int not null ,
	/* ���ʱ�� */
	checkTime datetime not null ,
	/* ����˱�ţ�Ա����sys_employee��sn����� */
	checkerSn varchar(50) not null ,
	/* ���������ȡֵΪ��ͨ������ء��ܾ������� */
	result varchar(50) not null ,
	/* ������� */
	comment varchar(500) not null
) engine = InnoDB;

alter table biz_check_result	/* ���Լ���������ű���������˽�� */
	add constraint FK_biz_check_result__claimId foreign key(claimId) references biz_claim_voucher(id);
alter table biz_check_result	/* ���Լ������˭���ı����� */
	add constraint FK_biz_check_result__checkerSn foreign key(checkerSn) references sys_employee(sn);
alter table biz_check_result	/* ���Լ��������������� */
	add constraint CK_biz_check_result__result check(result in('ͨ��', '���', '�ܾ�', '����'));

/* ��������ϸ�� */
create table biz_claim_voucher_detail
(
	/* ��������ϸID */
  	id int primary key auto_increment ,
	/* ��������ţ���������biz_claim_voucher��id����� */
	mainId int not null ,
	/* ��ϸ�����ƣ���ȡֵΪ����Ʒ�ѡ��칫�ѡ�ס�޷ѡ������ѡ����ʲͷѡ��Ǽʽ�ͨ�ѡ����ڽ�ͨ�� */
	item varchar(20) not null ,
	/* ��ϸ���� */
  	account double not null ,
	/* ��ϸ���� */
  	description varchar(200) not null
) engine = InnoDB;
insert into biz_claim_voucher_detail value(null,3,'������',300,'��ͻ��Է�');
alter table biz_claim_voucher_detail	/* ���Լ���������ű���������ϸ */
	add constraint FK_biz_claim_voucher_detail__mainId foreign key(mainId) references biz_claim_voucher(id);
alter table biz_claim_voucher_detail	/* ���Լ����������ϸ�� */
	add constraint CK_biz_claim_voucher_detail__item check(item in('��Ʒ��', '�칫��', 'ס�޷�', '������', '���ʲͷ�', '�Ǽʽ�ͨ��', '���ڽ�ͨ��'));
alter table biz_claim_voucher_detail	/* ���Լ����������ϸ���������0 */
	add constraint CK_biz_claim_voucher_detail__account check(account > 0);

/* �������¶�ͳ�Ʊ� */
create table biz_claim_voucher_statistics
(
	/* �������¶�ͳ��ID */
	id int primary key auto_increment ,
	/* �¶�ͳ���ܽ�� */
	totalMoney double not null ,
	/* ��ͳ�Ƶ���� */
	year int not null ,
	/* ��ͳ�Ƶ��·� */
	month int not null ,
	/* ��ͳ�ƵĲ��ű�ţ����ű�sys_deparment��id����� */
	departmentId int not null ,
	/* ͳ��ʱ�� */
	modifyTime datetime not null
) engine = InnoDB;

alter table biz_claim_voucher_statistics	/* ���Լ�������ĸ����ŵı������¶�ͳ�� */
	add constraint FK_biz_claim_voucher_statistics__departmentId foreign key(departmentId) references sys_department(id);
alter table biz_claim_voucher_statistics	/* ���Լ�����¶�ͳ�Ʊ������������0 */
	add constraint CK_biz_claim_voucher_statistics__totalMoney check(totalMoney > 0);
alter table biz_claim_voucher_statistics	/* ���Լ�����¶������������20��ͷ��������λ������ */
	add constraint CK_biz_claim_voucher_statistics__year check(year like '20[0-9][0-9]');
alter table biz_claim_voucher_statistics	/* ���Լ�����¶ȱ������1��12֮�� */
	add constraint CK_biz_claim_voucher_statistics__month check(month between 1 and 12);

/* ���������ͳ�Ʊ� */
create table biz_claim_vouyear_statistics
(
	/* ���������ͳ��ID */
	id int primary key auto_increment ,
	/* ���ͳ���ܽ�� */
	totalMoney double not null ,
	/* ��ͳ�Ƶ���� */
	year int not null ,
	/* ��ͳ�ƵĲ��ű�ţ����ű�sys_deparment��id����� */
	departmentId int not null ,
	/* ͳ��ʱ�� */
	modifyTime datetime not null
) engine = InnoDB;

alter table biz_claim_vouyear_statistics	/* ���Լ�������ĸ����ŵı��������ͳ�� */
	add constraint FK_biz_claim_vouyear_statistics__departmentId foreign key(departmentId) references sys_department(id);
alter table biz_claim_vouyear_statistics	/* ���Լ�������ͳ�Ʊ������������0 */
	add constraint CK_biz_claim_vouyear_statistics__totalMoney check(totalMoney > 0);
alter table biz_claim_vouyear_statistics	/* ���Լ������ȱ�����20��ͷ��������λ������ */
	add constraint CK_biz_claim_vouyear_statistics__year check(year like '20[0-9][0-9]');

/* ��ٱ� */
create table biz_leave
(
	/* ���ID */
	id int primary key auto_increment ,
	/* ����˱�ţ�Ա����sys_employee��sn����� */
	employeeSn varchar(50) not null ,
	/* ��ٿ�ʼʱ�� */
	startTime datetime not null ,
	/* ��ٽ���ʱ�� */
	endTime datetime not null ,
	/* �����������С֧�ֵ�0.5�� */
	leaveDay double not null ,
	/* ������� */
	reason varchar(500) not null ,
	/* ���״̬����ȡֵΪ�������������������Ѵ�� */
	status varchar(20) not null ,
	/* �ݼ����ͣ���ȡֵΪ���¼١���١����١����١���� */
	leaveType varchar(50) not null ,
	/* ��һ�������˱�ţ�Ա����sys_employee��sn����� */
  	nextDealSn varchar(50) not null ,
	/* ������� */
	approveOpinion varchar(100) ,
	/* ��ٴ���ʱ�� */	
 	createTime datetime not null ,
	/* ����޸�ʱ�� */
	modifyTime datetime
) engine = InnoDB;

alter table biz_leave	/* ���Լ�������ĸ�Ա������ٵ� */
	add constraint FK_biz_leave__employeeSn foreign key(employeeSn) references sys_employee(sn);
alter table biz_leave	/* ���Լ������һ�������ٵ����� */
	add constraint FK_biz_leave__nextDealSn foreign key(nextDealSn) references sys_employee(sn);
alter table biz_leave	/* ���Լ������ʼ�ݼ�ʱ�䲻��С�ڽ����ݼ�ʱ�䣬Ҳ����С�ڴ���ʱ�䣬���������ݼٺ󲹼��� */
	add constraint CK_biz_leave__startTime check(startTime < endTime and startTime > createTime);
alter table biz_leave	/* ���Լ�����ݼ���������Ϊ0.5 */
	add constraint CK_biz_leave__leaveDay check(leaveDay >= 0.5);
alter table biz_leave	/* ���Լ�����ݼ�����״̬ */
	add constraint CK_biz_leave__status check(status in('������','������','�Ѵ��'));
alter table biz_leave	/* ���Լ�����ݼ�״̬ */
	add constraint CK_biz_leave__leaveType check(leaveType in('�¼�','���','����','����','���'));
alter table biz_leave	/* ���Լ�������������޸�ʱ�������Ϊ�գ�����С�ڴ������� */
	add constraint CK_biz_leave__modifyTime check(modifyTime is null or modifyTime > createTime);
