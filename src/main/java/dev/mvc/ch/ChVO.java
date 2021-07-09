package dev.mvc.ch;

//chno                            INT     NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT '중국카테고리번호',
//chgrpno                           INTEGER(10)    NOT NULL COMMENT '중국그룹 번호',
//chtitle                           VARCHAR(100)   NOT NULL COMMENT '카테고리제목',
//chex                              VARCHAR(100)  NOT NULL COMMENT '카테고리내용',
//rdate                             DATETIME     NOT NULL COMMENT '카테고리 등록일',

public class ChVO {
  /**즐겨찾기 번호 */
  private int chno;
  /**즐겨찾기 그룹번호 */
  private int chgrpno;
  /**즐겨찾기 제목 */
  private String chtitle;
  /**즐겨찾기 설명 */
  private String chex;
  /**즐겨찾기 등록일 */
  private String rdate;
  public int getChno() {
    return chno;
  }
  public void setChno(int chno) {
    this.chno = chno;
  }
  public int getChgrpno() {
    return chgrpno;
  }
  public void setChgrpno(int chgrpno) {
    this.chgrpno = chgrpno;
  }
  public String getChtitle() {
    return chtitle;
  }
  public void setChtitle(String chtitle) {
    this.chtitle = chtitle;
  }
  public String getChex() {
    return chex;
  }
  public void setChex(String chex) {
    this.chex = chex;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

}
