/**********************************/
/* Table Name: 즐겨찾기 내용 */
/**********************************/
DROP TABLE favfield
CREATE TABLE favfield(
		favno                         		INT			NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '즐겨찾기 번호',
		favgrpno                      		INTEGER(10)		 NOT NULL COMMENT '즐겨찾기 그룹번호',
		favtitle                      		VARCHAR(100)		 NOT NULL COMMENT '즐겨찾기 제목',
		favex                         		VARCHAR(100)		 NOT NULL COMMENT '즐겨찾기 설명',
		rdate                         		DATETIME		 NOT NULL COMMENT '즐겨찾기 등록일',
  FOREIGN KEY (favgrpno) REFERENCES favfieldgrp (favgrpno)
);

-- now()
SELECT NOW();
-- Create, 등록
INSERT INTO favfield(favgrpno,favtitle,favex,rdate)
VALUES(1,'마라탕','입을 마비시키는 맛', NOW());
INSERT INTO favfield(favgrpno,favtitle,favex,rdate)
VALUES(1,'양꼬치','맛있는 양꼬치', NOW());

--목록
 SELECT favno,favgrpno,favtitle,favex,rdate
 FROM favfield
 ORDER BY favno ASC
 --조회
   SELECT favno,favgrpno,favtitle,favex,rdate
   FROM favfield
   WHERE favno=1;
 --수정
   UPDATE favfield
   SET favtitle='마라탕',favex='입을 마비시키는 맛',favgrpno=1
   WHERE favno = 1;
 -- categrpno 별 목록
	SELECT favno,favgrpno,favtitle,favex,rdate
	FROM favfield
	WHERE favgrpno = 1
	ORDER BY favno ASC;
-- favfieldgrp + favfield join, 연결 목록
	SELECT r.favgrpno as r_favgrpno,r.favgrptitle AS r_favgrptitle,
	   c.favno, c.favgrpno,c.favtitle, c.favex, c.rdate
	FROM favfieldgrp r, favfield c
	WHERE r.favgrpno = c.favgrpno
	ORDER BY favgrpno ASC, favno ASC;