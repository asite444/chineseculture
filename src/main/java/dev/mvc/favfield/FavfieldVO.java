package dev.mvc.favfield;

/**********************************/
/* Table Name: 즐겨찾기 내용 */
/**********************************/
//CREATE TABLE favfield(
//    favno                             INT     NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '즐겨찾기 번호',
//    favgrpno                          INTEGER(10)    NOT NULL COMMENT '즐겨찾기 그룹번호',
//    favtitle                          VARCHAR(100)     NOT NULL COMMENT '즐겨찾기 제목',
//    favex                             VARCHAR(100)     NOT NULL COMMENT '즐겨찾기 설명',
//    rdate                             DATETIME     NOT NULL COMMENT '즐겨찾기 등록일',
//  FOREIGN KEY (favgrpno) REFERENCES favfieldgrp (favgrpno)
//);
public class FavfieldVO {
  /**즐겨찾기 번호 */
  private int favno;
  /**즐겨찾기 그룹번호 */
  private int favgrpno;
  /**즐겨찾기 제목 */
  private String favtitle;
  /**즐겨찾기 설명 */
  private String favex;
  /**즐겨찾기 등록일 */
  private String rdate;

  public int getFavno() {
    return favno;
  }

  public void setFavno(int favno) {
    this.favno = favno;
  }

  public int getFavgrpno() {
    return favgrpno;
  }

  public void setFavgrpno(int favgrpno) {
    this.favgrpno = favgrpno;
  }

  public String getFavtitle() {
    return favtitle;
  }

  public void setFavtitle(String favtitle) {
    this.favtitle = favtitle;
  }

  public String getFavex() {
    return favex;
  }

  public void setFavex(String favex) {
    this.favex = favex;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
}
