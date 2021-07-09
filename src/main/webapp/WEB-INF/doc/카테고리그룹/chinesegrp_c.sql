/**********************************/
/* Table Name: 중국카테고리그룹 */
/**********************************/
DROP TABLE chinesegrp
CREATE TABLE chinesegrp(
		chgrpno                       		INT			NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '중국그룹 번호',
		topic                           VARCHAR(50)   NOT NULL COMMENT '주제',
		chfield                         VARCHAR(50)   NOT NULL COMMENT '세부주제',
		seqno                MEDIUMINT  NOT NULL COMMENT '출력 순서',
		rdate                         		 DATETIME  NOT NULL COMMENT '등록일'
); COMMENT='중국카테고리그룹';

-- now()
SELECT NOW();

-- Create, 등록
INSERT INTO chinesegrp(topic,chfield,seqno,rdate)
VALUES('생활','생활방식',1,  NOW());
INSERT INTO chinesegrp(topic,chfield,seqno,rdate)
VALUES('식문화','음식',2,  NOW());
INSERT INTO chinesegrp(topic,chfield,seqno,rdate)
VALUES('기초회화','인사법',3, NOW());
INSERT INTO chinesegrp(topic,chfield,seqno,rdate)
VALUES('역사','대한민국임시정부청사',4,  NOW());
INSERT INTO chinesegrp(topic,chfield,seqno,rdate)
VALUES('역사','두 영웅의 의거',5,  NOW());
INSERT INTO chinesegrp(topic,chfield,seqno,rdate)
VALUES('동북공정','고구려사',6, NOW());
INSERT INTO chinesegrp(topic,chfield,seqno,rdate)
VALUES('동북공정','김치,한복은 한국문화',7,NOW());
-- List, 목록
SELECT * FROM chinesegrp
ORDER BY chgrpno  ASC;

SELECT chgrpno,chfield,topic,seqno,rdate
FROM chinesegrp
ORDER BY chgrpno  ASC;

-- Read, 조회
SELECT chgrpno,chfield,topic,seqno,rdate
FROM chinesegrp
ORDER BY chgrpno  = 1;

-- 조회 + 수정폼 + 삭제폼
SELECT chgrpno,chfield,topic,seqno,rdate
FROM chinesegrp
WHERE chgrpno = 1;

--삭제처리
DELETE FROM chinesegrp
WHERE chgrpno = 1;


-- Update, 수정, PK는 일반적으로 update 불가능, 컬럼의 특징을 파악후 변경여부 결정,
-- WHERE 절에 PK 컬럼 명시
UPDATE chinesegrp
SET chfield='문화', visible='N'
WHERE chgrpno =8;

UPDATE chinesegrp
SET topic='명절', visible='Y'
WHERE chgrpno=8;

-- Delete, 삭제
DELETE FROM chinesegrp
WHERE chgrpno=8;

SELECT * FROM chinesegrp;


-- 출력 순서에따른 전체 목록
SELECT chgrpno,topic,chfield,seqno,rdate
FROM chinesegrp
ORDER BY seqno ASC;

-- 출력 순서 올림(상향), 10 ▷ 1
UPDATE chinesegrp
SET seqno = seqno - 1
WHERE chgrpno=1;
-- 출력순서 내림(하향), 1 ▷ 10
UPDATE chinesegrp
SET seqno = seqno + 1
WHERE chgrpno=1;

-- seqno 정렬
SELECT chgrpno,topic,chfield,seqno,rdate
FROM chinesegrp
ORDER BY seqno ASC;