/**********************************/
/* Table Name: 컨텐츠-중국 관련 정보 */
/**********************************/
DROP TABLE contents
CREATE TABLE contents(
		contentsno                    		INT		 NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT'컨텐츠 번호',
		chno                         		INT	    NOT NULL COMMENT'즐겨찾기 번호',
		adminno                       	   INT		 NOT NULL COMMENT'관리자 번호',
		title                               VARCHAR(100) NOT NULL COMMENT'제목',
		content                               VARCHAR(5000) NOT NULL COMMENT'내용',
		lecturetime                            INT		 NOT NULL COMMENT'강의 시간',
		recom                            INT  NULL COMMENT'추천수',
		cnt                                   INT  NULL COMMENT'조회수',
		tklevel                              VARCHAR(100)  NULL COMMENT'난이도',
		rvarea                              VARCHAR(100)  NOT NULL COMMENT'관련분야',
		passwd                               VARCHAR(15) NULL COMMENT'패스워드',
		word                               VARCHAR(300) NOT NULL COMMENT'검색어',
		rdate                         		DATETIME		 NOT NULL COMMENT '등록일',
		file1                             VARCHAR(100) NOT NULL COMMENT'메인 이미지',
		file1saved                                VARCHAR(100) NOT NULL COMMENT'실제 저장된 이미지',
		thumb1                               VARCHAR(100) NOT NULL COMMENT'메인 이미지 Preview',
		size1                               INT NOT NULL COMMENT'메인 이미지 크기',
		
  FOREIGN KEY (chno) REFERENCES ch (chno),
  FOREIGN KEY (adminno) REFERENCES admin (adminno)
);
INSERT INTO contents(contentsno, adminno, chno, title, content,passwd, word,
                                 file1, file1saved, thumb1, size1, rdate)
VALUES(contentsno, 1, 1, '마라탕', '입을 마비시키는 맛','123', '탕', 
            'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000, NOW());
            
INSERT INTO contents(contentsno, adminno, chno, title, content,passwd, word,rvarea,
                                 file1, file1saved, thumb1, size1, rdate)
VALUES(contentsno, 2, 1, '위챗', '중국에서도 우링나라라라랄라','123', '탕', 'IT',
            'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000, NOW());
            
INSERT INTO contents(contentsno, adminno, chno, title, content,passwd, word,tklevel,
                                 file1, file1saved, thumb1, size1, rdate)
VALUES(contentsno, 2, 1, '你好', '我叫林东炫','123', '탕', '下',
            'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000, NOW());
            
INSERT INTO contents(contentsno, adminno, chno, title, content,passwd, word,tklevel,rvarea,
                                 file1, file1saved, thumb1, size1, rdate)
VALUES(contentsno, 2, 1, '我喜欢...', '我喜欢学汉语。','123', '탕', '下','좋아하는것 말하기',
            'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000, NOW());
-- favno별 목록
SELECT contentsno, adminno, chno, title, content, passwd, word,tklevel,rvarea,
       file1, file1saved, thumb1, size1, rdate 
FROM contents
WHERE chno=1
ORDER BY contentsno ASC;

  SELECT contentsno, adminno, chno, title, content, passwd, word,tklevel,rvarea,
              file1, file1saved, thumb1, size1, rdate 
    FROM contents

--검색 기능
SELECT contentsno, adminno, chno, title, content, passwd, word,tklevel,rvarea,
       file1, file1saved, thumb1, size1, rdate 
FROM contents
WHERE chno=1 AND (title LIKE '%중국%' OR content LIKE '%중국%' OR word LIKE '%중국%' or rvarea LIKE '%중국%' or tklevel LIKE '%중국%')
ORDER BY contentsno ASC
LIMIT 0,2;

--조회
SELECT contentsno, adminno, chno, title, content, passwd, word,tklevel,rvarea,
       file1, file1saved, thumb1, size1, rdate 
FROM contents
WHERE contentsno =1;

-- 수정
UPDATE contents
SET title='마라탕',content='마이따!'
WHERE contentsno=7;

-- 특정 그룹에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM contents 
WHERE chno=1;

-- 특정 관리자에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM contents 
WHERE adminno=1;