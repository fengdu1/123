use jboa;

insert into sys_department (id, name) values (1, '人事部');
insert into sys_department (id, name) values (2, '平台研发部');
insert into sys_department (id, name) values (3, '产品设计部');
insert into sys_department (id, name) values (4, '财务部');
insert into sys_department (id, name) values (5, '总裁办公室');

insert into sys_position (id, namecn, nameen) values (1, '员工', 'staff');
insert into sys_position (id, namecn, nameen) values (2, '部门经理', 'manager');
insert into sys_position (id, namecn, nameen) values (3, '总经理', 'generalmanager');
insert into sys_position (id, namecn, nameen) values (4, '财务', 'cashier');

insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('017', 1, 1, '李小伟', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('001', 1, 2, '张平', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('002', 2, 2, '叶宁', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('003', 3, 2, '李伟', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('004', 4, 4, '王小明', '123', '离职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('005', 1, 3, '林风', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('006', 1, 3, '张大明', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('011', 1, 1, '李大伟', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('007', 1, 1, '李大伟', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('008', 3, 5, '张总', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('009', 4, 4, '李峰', '123', '在职');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('018', 1, 1, '李大伟', '123', '在职');

insert into biz_claim_voucher value(null,'001','001','2017-06-11','交通补',200,'新创建',null);
insert into biz_claim_voucher value(null,'001','001','2017-06-11','餐补',300,'已审批',null);
insert into biz_claim_voucher value(null,'001','001','2017-06-12','礼品补',600,'已终止',null);
insert into biz_claim_voucher value(null,'001','001','2017-06-12','餐补',300,'已付款',null);
insert into biz_claim_voucher value(null,'003','004','2017-05-12','交通补',150,'已付款',null);
insert into biz_claim_voucher value(null,'004','005','2017-07-10','礼品补',500,'已提交',null);

insert into biz_claim_voucher_detail value(null,1,'市内交通费',200,'打车');
insert into biz_claim_voucher_detail value(null,2,'餐饮费',300,'请客户吃饭');
insert into biz_claim_voucher_detail value(null,3,'城际交通费',150,'乘大巴');
insert into biz_claim_voucher_detail value(null,4,'礼品费',500,'为客户买礼品');
insert into biz_claim_voucher_detail value(null,5,'餐饮费',300,'请客户吃饭');
insert into biz_claim_voucher_detail value(null,6,'礼品费',600,'为客户买礼品');

insert into biz_check_result value(null,3,'2017-06-11','002','通过','无意见');
insert into biz_check_result value(null,4,'2017-06-12','003','拒绝','无意见');
insert into biz_check_result value(null,5,'2017-06-12','002','通过','无意见');
insert into biz_check_result value(null,1,'2017-07-10','003','通过','无意见');
insert into biz_check_result value(null,2,'2017-05-12','008','拒绝','无意见');
commit;