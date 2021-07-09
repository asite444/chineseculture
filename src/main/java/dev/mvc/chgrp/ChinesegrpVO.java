package dev.mvc.chgrp;

/**********************************/
/* Table Name: 중국카테고리그룹 */
/**********************************/
//DROP TABLE chinesegrfavfieldgrpp
//CREATE TABLE chinesegrp(
//   chgrpno                           INT     NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '중국그룹 번호',
//    topic                           VARCHAR(50)   NOT NULL COMMENT '주제',
//    chfield                         VARCHAR(50)   NOT NULL COMMENT '세부주제',
//    seqno                MEDIUMINT  NOT NULL COMMENT '출력 순서',
//    rdate                              DATETIME  NOT NULL COMMENT '등록일'
//); COMMENT='중국카테고리그룹';

public class ChinesegrpVO {
  /** 중국그룹번호 */
  private int chgrpno;
  /** 주제 */
  private String topic;
  /**세부 주제 */
  private String chfield;
  /** 출력 순서 */
  private int seqno;
  /** 등록일 */
  private String rdate;
  ChinesegrpVO(){
    
  }


  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public int getChgrpno() {
    return chgrpno;
  }
  public void setChgrpno(int chgrpno) {
    this.chgrpno = chgrpno;
  }
  public String getTopic() {
    return topic;
  }
  public void setTopic(String topic) {
    this.topic = topic;
  }
  
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getChfield() {
    return chfield;
  }
  public void setChfield(String chfield) {
    this.chfield = chfield;
  }

}
