CREATE TABLE `state_wise_data` (
  `id` bigint(20) NOT NULL auto_increment,
  `cured_cases` bigint(20) DEFAULT NULL,
  `data_date` datetime(6) DEFAULT NULL,
  `state_name` varchar(255) DEFAULT NULL,
  `total_cases` bigint(20) DEFAULT NULL,
  `total_death` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;
commit;

CREATE TABLE `state` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;


commit;

insert into State values(1,'Andhra Pradesh');
insert into State values(2,'Andaman and Nicobar Islands');
insert into State values(3,'Bihar');
insert into State values(4,'Chandigarh');
insert into State values(5,'Chhattisgarh');
insert into State values(6,'Delhi');
insert into State values(7,'Goa');
insert into State values(8,'Gujarat');
insert into State values(9,'Haryana');
insert into State values(10,'Himachal Pradesh');
insert into State values(11,'Jammu and Kashmir');
insert into State values(12,'Karnataka');
insert into State values(13,'Kerala');
insert into State values(14,'Ladakh');
insert into State values(15,'Madhya Pradesh');
insert into State values(16,'Maharashtra');
insert into State values(17,'Manipur');
insert into State values(18,'Mizoram');
insert into State values(19,'Odisha');
insert into State values(20,'Puducherry');
insert into State values(21,'Punjab');
insert into State values(22,'Rajasthan');
insert into State values(23,'Telengana');
insert into State values(24,'Uttarakhand');
insert into State values(25,'Uttar Pradesh');
insert into State values(26,'West Bengal');
insert into State values(27,'Assam');
insert into State values(28,'Jharkhand');
insert into State values(29,'Arunachal Pradesh');
insert into State values(30,'Tripura');
insert into State values(31,'Nagaland');
insert into State values(32,'Meghalaya');
insert into state values(33,'INDIA All States');

commit;


