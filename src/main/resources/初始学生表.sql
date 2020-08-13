-- 如果test数据库不存在，就创建test数据库：
CREATE DATABASE IF NOT EXISTS studentAdmi;

-- 切换到test数据库
USE studentAdmi;
-- 创建students表
CREATE TABLE students (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
	sex CHAR NOT NULL,
	place VARCHAR(100) NOT NULL,
	code BIGINT NOT NULL,
	dept VARCHAR(100) NOT NULL ,
	class_name VARCHAR(100) NOT NULL , 
    UNIQUE INDEX(code),
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入students记录
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("张三", "男", "广西", 123456, "养猪", "兽医9901");
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("王五", "女", "王者峡谷", 12345678, "电竞", "国服第一电竞俱乐部");
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("小红", "女", "百色", 22222, "商外", "英语");
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("李四", "男", "迪拜", 1234567, "捡垃圾", "垃圾交易9902班");
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("小明", "男", "南宁", 11111, "计算机", "计算机视觉1089");
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("小张", "男", "北京", 64321, "药学", "药学1089");
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("小丽", "女", "广东", 764321, "心理学", "心理健康1909");
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("小赵", "男", "新疆", 8764321, "管理", "管理学1089");
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("小倩", "女", "北京", 98765, "药学", "药学1089");
INSERT INTO students (name, sex, place, code, dept, class_name ) VALUES ("小静", "女", "南宁", 987654, "计算机", "计算机视觉1089");



-- OK:
SELECT 'ok' as 'result:';

