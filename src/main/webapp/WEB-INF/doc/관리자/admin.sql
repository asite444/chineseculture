/**********************************/
/* Table Name: 관리자 */
/**********************************/
CREATE TABLE admin(
		adminno                       		INT		 NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '관리자',
		id                            		VARCHAR(20)		NOT NULL COMMENT '아이디'
);

INSERT INTO admin(adminno,id)
VALUES(1,'admin1');
INSERT INTO admin(adminno,id)
VALUES(2,'admin2');
INSERT INTO admin(adminno,id)
VALUES(3,'admin3');