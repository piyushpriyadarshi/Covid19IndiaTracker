CREATE TABLE `state_wise_data` (
  `id` bigint(20) NOT NULL auto_increment,
  `cured_cases` bigint(20) DEFAULT NULL,
  `data_date` datetime(6) DEFAULT NULL,
  `state_name` varchar(255) DEFAULT NULL,
  `total_cases` bigint(20) DEFAULT NULL,
  `total_death` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

commit;