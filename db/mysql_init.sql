use jboa;

insert into sys_department (id, name) values (1, '���²�');
insert into sys_department (id, name) values (2, 'ƽ̨�з���');
insert into sys_department (id, name) values (3, '��Ʒ��Ʋ�');
insert into sys_department (id, name) values (4, '����');
insert into sys_department (id, name) values (5, '�ܲð칫��');

insert into sys_position (id, namecn, nameen) values (1, 'Ա��', 'staff');
insert into sys_position (id, namecn, nameen) values (2, '���ž���', 'manager');
insert into sys_position (id, namecn, nameen) values (3, '�ܾ���', 'generalmanager');
insert into sys_position (id, namecn, nameen) values (4, '����', 'cashier');

insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('017', 1, 1, '��Сΰ', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('001', 1, 2, '��ƽ', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('002', 2, 2, 'Ҷ��', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('003', 3, 2, '��ΰ', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('004', 4, 4, '��С��', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('005', 1, 3, '�ַ�', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('006', 1, 3, '�Ŵ���', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('011', 1, 1, '���ΰ', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('007', 1, 1, '���ΰ', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('008', 3, 5, '����', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('009', 4, 4, '���', '123', '��ְ');
insert into sys_employee (sn, positionId, departmentId, name, password, status)
values ('018', 1, 1, '���ΰ', '123', '��ְ');

insert into biz_claim_voucher value(null,'001','001','2017-06-11','��ͨ��',200,'�´���',null);
insert into biz_claim_voucher value(null,'001','001','2017-06-11','�Ͳ�',300,'������',null);
insert into biz_claim_voucher value(null,'001','001','2017-06-12','��Ʒ��',600,'����ֹ',null);
insert into biz_claim_voucher value(null,'001','001','2017-06-12','�Ͳ�',300,'�Ѹ���',null);
insert into biz_claim_voucher value(null,'003','004','2017-05-12','��ͨ��',150,'�Ѹ���',null);
insert into biz_claim_voucher value(null,'004','005','2017-07-10','��Ʒ��',500,'���ύ',null);

insert into biz_claim_voucher_detail value(null,1,'���ڽ�ͨ��',200,'��');
insert into biz_claim_voucher_detail value(null,2,'������',300,'��ͻ��Է�');
insert into biz_claim_voucher_detail value(null,3,'�Ǽʽ�ͨ��',150,'�˴��');
insert into biz_claim_voucher_detail value(null,4,'��Ʒ��',500,'Ϊ�ͻ�����Ʒ');
insert into biz_claim_voucher_detail value(null,5,'������',300,'��ͻ��Է�');
insert into biz_claim_voucher_detail value(null,6,'��Ʒ��',600,'Ϊ�ͻ�����Ʒ');

insert into biz_check_result value(null,3,'2017-06-11','002','ͨ��','�����');
insert into biz_check_result value(null,4,'2017-06-12','003','�ܾ�','�����');
insert into biz_check_result value(null,5,'2017-06-12','002','ͨ��','�����');
insert into biz_check_result value(null,1,'2017-07-10','003','ͨ��','�����');
insert into biz_check_result value(null,2,'2017-05-12','008','�ܾ�','�����');
commit;