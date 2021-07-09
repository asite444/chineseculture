-- member 삭제전에 FK가 선언된 blog 테이블 먼저 삭제합니다.
DROP TABLE qna;
DROP TABLE reply;
DROP TABLE member;
-- 제약 조건과 함께 삭제(제약 조건이 있어도 삭제됨, 권장하지 않음.)
DROP TABLE member CASCADE CONSTRAINTS; 
 
CREATE TABLE member (
  memberno INT NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT'회원 번호', -- 회원 번호, 레코드를 구분하는 컬럼 
  id             VARCHAR(20)  	NOT NULL  COMMENT'아이디', -- 아이디, 중복 안됨, 레코드를 구분 
  passwd      VARCHAR(60)   NOT NULL COMMENT'패스워드' , -- 패스워드, 영숫자 조합
  mname      VARCHAR(30)   NOT NULL COMMENT '성명', -- 성명, 한글 10자 저장 가능
  nickname      VARCHAR(50)   NOT NULL COMMENT '별명',
  tel            VARCHAR(14)   NOT NULL COMMENT '전화번호', -- 전화번호
  email          VARCHAR(30)   NOT NULL COMMENT '이메일',    
  zipcode     VARCHAR(5)        NULL COMMENT '우편번호', -- 우편번호, 12345
  address1    VARCHAR(200)       NULL COMMENT '주소1', -- 주소 1
  address2    VARCHAR(100)       NULL COMMENT '주소2', -- 주소 2
  mdate       DATETIME             NOT NULL COMMENT '가입일', -- 가입일    
  grade        INT     NOT NULL COMMENT '등급'  -- 등급(1~10: 관리자, 11~20: 회원, 비회원: 30~39, 정지 회원: 40~49, 탈퇴 회원: 99)
  
 
);


INSERT INTO member(memberno, id, passwd, mname,nickname, tel,email, zipcode,
                               address1, address2, mdate, grade)
VALUES (memberno, 'qnaadmin', '1234', '질문답변관리자','관리자닷', '000-0000-0000','admin@gmail.com', '12345',
             '서울시 종로구', '관철동', NOW(), 1);
 INSERT INTO member(memberno, id, passwd, mname,nickname, tel,email, zipcode,
                              address1, address2, mdate, grade)
VALUES (memberno, 'user1', '1234', '이완용','매국노', '000-0000-0000','admin@gmail.com', '12345',
             '서울시 종로구', '관철동', NOW(), 6);
  INSERT INTO member(memberno, id, passwd, mname,nickname, tel,email, zipcode,
                              address1, address2, mdate, grade)
VALUES (memberno, 'user2', '1234', '안중근','독립군', '000-0000-0000','admin@gmail.com', '12345',
             '서울시 종로구', '관철동', NOW(), 1);
 
1. 등록
 
1) id 중복 확인(null 값을 가지고 있으면 count에서 제외됨)
SELECT COUNT(id)
FROM member
WHERE id='user1';

SELECT COUNT(id) as cnt
FROM member
WHERE id='user3';
 
 cnt
 ---
   0   ← 중복 되지 않음.
   
2) 등록
-- 회원 관리용 계정, Q/A 용 계정

-- 개인 회원 테스트 계정

-- 부서별(그룹별) 공유 회원 기준

COMMIT;

 
2. 목록
- 검색을 하지 않는 경우, 전체 목록 출력
 
SELECT memberno, id, passwd, mname,nickname, tel,email, zipcode,
                               address1, address2, mdate, grade
FROM member
ORDER BY memberno ASC;
     
     
3. 조회
 
1) user1 사원 정보 보기
SELECT memberno, id, passwd, mname,nickname, tel,email, zipcode,
                               address1, address2, mdate, grade
FROM member
WHERE memberno = 1;

SELECT memberno, id, passwd, mname,nickname, tel,email, zipcode,
                               address1, address2, mdate, grade
FROM member
WHERE id = 'user1';
 
    
4. 수정
UPDATE member 
SET mname='아로미', tel='111-1111-1111', zipcode='00000',
      address1='경기도', address2='파주시', grade=14
WHERE memberno=1;

COMMIT;

 
5. 삭제
1) 모두 삭제
DELETE FROM member;
 
2) 특정 회원 삭제
DELETE FROM member
WHERE memberno=15;

COMMIT;

 
6. 패스워드 변경
1) 패스워드 검사
SELECT COUNT(memberno) as cnt
FROM member
WHERE memberno=1 AND passwd='1234';
 
2) 패스워드 수정
UPDATE member
SET passwd='0000'
WHERE memberno=1;

COMMIT;
 
 
7. 로그인
SELECT COUNT(memberno) as cnt
FROM member
WHERE id='user1' AND passwd='1234';
 cnt
 ---
   0pydb