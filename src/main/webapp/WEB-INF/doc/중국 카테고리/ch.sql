
/**********************************/
/* Table Name: 중국카테고리 */
/**********************************/
DROP TABLE ch
CREATE TABLE ch(
		chno                          	INT			NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '중국카테고리번호',
		chgrpno                       		INTEGER(10)		 NOT NULL COMMENT '중국그룹 번호',
		chtitle                       		VARCHAR(100)	 NOT NULL COMMENT '카테고리제목',
		chex                          		VARCHAR(100)	NOT NULL COMMENT '카테고리내용',
		rdate                         		DATETIME		 NOT NULL COMMENT '카테고리 등록일',
  FOREIGN KEY (chgrpno) REFERENCES chinesegrp (chgrpno)
);

-- now()
SELECT NOW();
-- Create, 등록
INSERT INTO ch(chgrpno,chtitle,chex,rdate)
VALUES(1,'마라탕','입을 마비시키는 맛', NOW());
INSERT INTO ch(chgrpno,chtitle,chex,rdate)
VALUES(1,'양꼬치','맛있는 양꼬치', NOW());

    SELECT chno,chgrpno,chtitle,rdate
    FROM ch
    ORDER BY chno ASC;
    
  SELECT chno,chgrpno,chtitle,chex,rdate
  FROM ch
  WHERE chgrpno = 1
  ORDER BY chno ASC;
  
  SELECT r.chgrpno as r_chgrpno,r.chfield AS r_chfield,
           c.chno, c.chgrpno,c.chtitle, c.chex, c.rdate
  FROM chinesegrp r, ch c
  WHERE r.chgrpno = c.chno
  ORDER BY chgrpno ASC, chno ASC;