package dev.mvc.favfieldgrp;

/**********************************/
/* Table Name: 즐겨찾기그룹 */
/**********************************/
//CREATE TABLE favfieldgrp(
//    favgrpno                          INT     NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '즐겨찾기그룹번호',
//    favgrptitle                       VARCHAR(100)     NOT NULL COMMENT '즐겨찾기 그룹 제목',
//    rdate                             DATETIME     NOT NULL COMMENT '즐겨찾기 그룹 등록일'
//);COMMENT='즐겨찾기그룹';
public class FavfieldgrpVO {

  /** 즐겨찾기 그룹 번호 */
  private int favgrpno;
  /** 즐겨찾기 그룹 제목 */
  private String favgrptitle;
  /** 즐겨찾기 그룹 등록일*/
  private String rdate;
  
  public FavfieldgrpVO (){
    
  }
  public int getFavgrpno() {
    return favgrpno;
  }
  public void setFavgrpno(int favgrpno) {
    this.favgrpno = favgrpno;
  }
  public String getFavgrptitle() {
    return favgrptitle;
  }
  public void setFavgrptitle(String favgrptitle) {
    this.favgrptitle = favgrptitle;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
}
