package dev.mvc.member;

/*
memberno INT NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT'회원 번호', -- 회원 번호, 레코드를 구분하는 컬럼 
id             VARCHAR(20)    NOT NULL  COMMENT'아이디', -- 아이디, 중복 안됨, 레코드를 구분 
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
*/
public class MemberVO {
/**회원 번호*/
private int memberno;
/**아이디*/
private String id;
/** 패스워드 */
private String passwd = "";
/** 관리자 성명 */
private String mname;
/**별명*/
private String nickname;
/** 전화 번호 */
private String tel;
/**이메일*/
private String email;
/** 우편 번호 */
private String zipcode = "";
/** 주소 1 */
private String address1 = "";
/** 주소 2 */
private String address2 = "";
/** 가입일 */
private String mdate = "";
/** 등급 */
private int grade = 0;

public int getMemberno() {
  return memberno;
}
public void setMemberno(int memberno) {
  this.memberno = memberno;
}
public String getId() {
  return id;
}
public void setId(String id) {
  this.id = id;
}
public String getPasswd() {
  return passwd;
}
public void setPasswd(String passwd) {
  this.passwd = passwd;
}
public String getMname() {
  return mname;
}
public void setMname(String mname) {
  this.mname = mname;
}
public String getNickname() {
  return nickname;
}
public void setNickname(String nickname) {
  this.nickname = nickname;
}
public String getTel() {
  return tel;
}
public void setTel(String tel) {
  this.tel = tel;
}
public String getEmail() {
  return email;
}
public void setEmail(String email) {
  this.email = email;
}
public String getZipcode() {
  return zipcode;
}
public void setZipcode(String zipcode) {
  this.zipcode = zipcode;
}
public String getAddress1() {
  return address1;
}
public void setAddress1(String address1) {
  this.address1 = address1;
}
public String getAddress2() {
  return address2;
}
public void setAddress2(String address2) {
  this.address2 = address2;
}
public String getMdate() {
  return mdate;
}
public void setMdate(String mdate) {
  this.mdate = mdate;
}
public int getGrade() {
  return grade;
}
public void setGrade(int grade) {
  this.grade = grade;
}


}
