insert into developer (id, name)values(1,'Developer1');
insert into developer (id, name)values(2,'Developer2');

insert into project (id, name, developer_id)values(1,'Osiedle Potok',2);

insert into district (id, name, city)values(1,'Mokotów','Warszawa');
insert into district (id, name, city)values(2,'Centrum','Warszawa');
insert into district (id, name, city)values(3,'Żoliborz','Warszawa');

insert into subdivision (id,name,city,district_id)values(1,'Osiedle Potok','Warszawa',3);
insert into subdivision (id,name,city,district_id)values(2,'Os. Milosna','Warszawa',2);

insert into preference (id,name)values(1,'Piętro');
insert into preference (id,name)values(2,'Sklepy');
insert into preference (id,name)values(3,'Cena za metr');
insert into preference (id,name)values(4,'Metraż');
insert into preference (id,name)values(5,'Miejsce Parkingowe');
insert into preference (id,name)values(6,'Galerie Handlowe');
insert into preference (id,name)values(7,'Park');
insert into preference (id,name)values(8,'District');
insert into preference (id,name)values(9,'Ilość Pokoii');
insert into preference (id,name)values(10,'Metraż Działki');

insert into customer (id, service_type, sales_type,service_state,firstname,lastname,email,phone,secondname,pesel)values(1,null,'Wynajem','W toku','Adrian','Radej','aradej006@gmail.com',000000000,'Mateusz',111111111);
insert into customer (id, service_type, sales_type,service_state,firstname,lastname,email,phone,secondname,pesel)values(2,null,'Kupno','Zakończono','Bernadetta','Wąsiewicz','asdad@gmail.com',111111111,null,222222222);

insert into employee (id,position,firstname, lastname,email,phone,secondname,pesel)values(1,'Konsultant','Jan','Kowalski','asdawd@gmail.com',222222222,'Mateusz',333333333);
insert into employee (id,position,firstname, lastname,email,phone,secondname,pesel)values(2,'Menadżer','Adam','Nowak','asdawd@gmail.com',333333333,'Piotr',444444444);

insert into building (id,city,street,building_Number,post_code,district, flor_Quantity, type, project_id)values(1,'Warszawa','Marzanny','5','02-649','Mokotów','4','Nieremontowany',null);
insert into building (id,city,street,building_Number,post_code,district, flor_Quantity, type, project_id)values(2,'Warszawa','Mickiewicza','72','00-111','Żoliborz','18','Nowy',null);
insert into building (id,city,street,building_Number,post_code,district, flor_Quantity, type, project_id)values(3,'Warszawa','Emili Plater','5','00-123','Centrum','1','Do remontu',null);
insert into building (id,city,street,building_Number,post_code,district, flor_Quantity, type, project_id)values(4,'Warszawa','Aleja Jana Pawla','72','01-456','Żoliborz','10','W budowie',1);

insert into segment (id,area,price_Per_Meter,building_id,employee_id,flor, rooms_quantity)values(1,45.22,'7500',1,1,3,3);
insert into segment (id,area,price_Per_Meter,building_id,employee_id,flor, rooms_quantity)values(2,74.23,'6999',1,1,2,2);
insert into segment (id,area,price_Per_Meter,building_id,employee_id,flor, rooms_quantity)values(3,20.54,'6500',1,2,4,4);

insert into flat (id,area,flor,price_per_meter,condition,building_id, employee_id, rooms_quantity)values(1,45.84,11,'7894','Wyremontowany',2,1,2);
insert into flat (id,area,flor,price_per_meter,condition,building_id, employee_id, rooms_quantity)values(2,78.46,13,'4862','Do remontu',2,1,3);
insert into flat (id,area,flor,price_per_meter,condition,building_id, employee_id, rooms_quantity)values(3,108.56,15,'9999','Apartament',2,2,4);

insert into house (id,land_area, area, price_per_meter, state,employee_id, garage, playground, building_id,rooms_quantity)values(1,195.23,80.23,'15000','Nowy',1,'Tak','Nie',3,3);
insert into house (id,land_area, area, price_per_meter, state,employee_id, garage, playground, building_id,rooms_quantity)values(2,100,50.23,'13000','Do remontu',2,'Nie','Nie',3,3);
insert into house (id,land_area, area, price_per_meter, state,employee_id, garage, playground, building_id,rooms_quantity)values(3,400.23,120.23,'14500','Nowy',2,'Tak','Tak',3,3);

insert into agreement (id, employee_id,customer_id,flat_id,house_id,type,segment_id) values(1,1,1,null,null,'Zakonczona',1);
insert into agreement (id, employee_id,customer_id,flat_id,house_id,type,segment_id) values(2,2,2,null,2,'Do podpisania',null);

insert into offer (id, employee_id,flat_id,house_id,type,segment_id)values(1,1,1,null,'Zainteresowany',null);
insert into offer (id, employee_id,flat_id,house_id,type,segment_id)values(2,2,null,2,'Nieodpowiednie',null);

insert into offer_Status (id,status,customer_id,offer_id)values(1,'W toku',1,1);
insert into offer_Status (id,status,customer_id,offer_id)values(2,'Zakończono',2,2);

insert into value_customer_text (id,customer_id,preference_id,value)values(2,2,5,'Nie');
insert into value_customer_number (id,customer_id,preference_id,value)values(5,2,1,3);
insert into value_customer_number (id,customer_id,preference_id,value)values(6,2,1,13);
insert into value_customer_number (id,customer_id,preference_id,value)values(7,2,3,3000.00);
insert into value_customer_number (id,customer_id,preference_id,value)values(8,2,3,16000.00);
insert into value_customer_number (id,customer_id,preference_id,value)values(9,2,4,40.0);
insert into value_customer_number (id,customer_id,preference_id,value)values(10,2,4,200.0);
insert into value_customer_text (id,customer_id,preference_id,value)values(5,2,8,'Mokotów');
insert into value_customer_text (id,customer_id,preference_id,value)values(6,2,8,'Centrum');
insert into value_customer_text (id,customer_id,preference_id,value)values(7,2,8,'Żoliborz');
insert into value_customer_number (id,customer_id,preference_id,value)values(14,2,10,20.0);
insert into value_customer_number (id,customer_id,preference_id,value)values(15,2,10,200.0);

insert into value_customer_text (id,customer_id,preference_id,value)values(16,5,2,'Nie');
insert into value_customer_number (id,customer_id,preference_id,value)values(17,1,1,10);
insert into value_customer_number (id,customer_id,preference_id,value)values(18,1,1,12);
insert into value_customer_number (id,customer_id,preference_id,value)values(19,1,3,5000.0);
insert into value_customer_number (id,customer_id,preference_id,value)values(20,1,3,12000.0);
insert into value_customer_number (id,customer_id,preference_id,value)values(21,1,4,40.0);
insert into value_customer_number (id,customer_id,preference_id,value)values(22,1,4,200.0);
insert into value_customer_text (id,customer_id,preference_id,value)values(23,1,8,'Mokotów');
insert into value_customer_text (id,customer_id,preference_id,value)values(24,1,8,'Żoliborz');
insert into value_customer_number (id,customer_id,preference_id,value)values(26,1,10,20.0);
insert into value_customer_number (id,customer_id,preference_id,value)values(127,1,10,200.0);


insert into value_district_text (id,district_id,preference_id,value)values(1,1,6,'Galeria Mokotów');
insert into value_district_text (id,district_id,preference_id,value)values(2,1,6,'BlueCity');

insert into value_subdivision_text (id,subdivision_id,preference_id,value)values(1,2,2,'Nie');
insert into value_subdivision_text (id,subdivision_id,preference_id,value)values(2,1,7,'Tak');
insert into value_subdivision_number (id,subdivision_id,preference_id,value)values(3,1,2,15);
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;
select hibernate_sequence.nextval from dual;