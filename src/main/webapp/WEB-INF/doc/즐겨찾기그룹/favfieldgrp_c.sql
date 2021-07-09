
/**********************************/
/* Table Name: 즐겨찾기그룹 */
/**********************************/
DROP TABLE favfieldgrp;
CREATE TABLE favfieldgrp(
		favgrpno                      		INT			NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '즐겨찾기그룹번호',
		favgrptitle                   		VARCHAR(100)		 NOT NULL COMMENT '즐겨찾기 그룹 제목',
		rdate                         		DATETIME		 NOT NULL COMMENT '즐겨찾기 그룹 등록일'
);COMMENT='즐겨찾기그룹';

-- now()
SELECT NOW();
-- Create, 등록
INSERT INTO favfieldgrp(favgrptitle,rdate)
VALUES('음식', NOW());
INSERT INTO favfieldgrp(favgrptitle,rdate)
VALUES('생활방식', NOW());
--favgrpno 정렬 
SELECT favgrpno,favgrptitle,rdate
FROM favfieldgrp
ORDER BY favgrpno ASC
--favfieldgrp 삭제
DELETE FROM favfieldgrp
WHERE favgrpno=#{favgrpno}

