CREATE SCHEMA `todolist` DEFAULT CHARACTER SET utf8;

CREATE TABLE `todolist`.`task` (
`id` INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(25) NOT NULL,
`create_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`description` VARCHAR(250) NOT NULL,
`is_done` BIT(1) DEFAULT false);

INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task1', 'task description');
INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task2', 'task description');
INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task3', 'task description');
INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task4', 'task description');
INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task5', 'task description');
INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task6', 'task description');
INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task7', 'task description');
INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task8', 'task description');
INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task9', 'task description');
INSERT INTO `todolist`.`task` (`name`, `description`) VALUES ('task10', 'task description');