package dev.mvc.contents;

import org.springframework.web.multipart.MultipartFile;

/*
    contentsno                        INT    NOT NULL  PRIMARY KEY AUTO_INCREMENT COMMENT'컨텐츠 번호',
    chno                            INT     NOT NULL COMMENT'카테고리 번호',
    adminno                            INT     NOT NULL COMMENT'관리자 번호',
    title                               VARCHAR(100) NOT NULL COMMENT'제목',
    content                               VARCHAR(5000) NOT NULL COMMENT'내용',
    lecturetime                            INT     NOT NULL COMMENT'강의 시간',
    recom                            INT  NULL COMMENT'추천수',
    cnt                                   INT  NULL COMMENT'조회수',
    tklevel                              VARCHAR(100)  NULL COMMENT'난이도',
    passwd                               VARCHAR(15) NULL COMMENT'패스워드',
    word                               VARCHAR(300) NOT NULL COMMENT'검색어',
    rdate                             DATETIME     NOT NULL COMMENT '등록일',
    file1                             VARCHAR(100) NOT NULL COMMENT'메인 이미지',
    file1saved                                VARCHAR(100) NOT NULL COMMENT'실제 저장된 이미지',
    thumb1                               VARCHAR(100) NOT NULL COMMENT'메인 이미지 Preview',
    size1                               INT NOT NULL COMMENT'메인 이미지 크기',
    
    mp4                                VARCHAR(100)            NULL COMMENT'강의 영상',
    mp4saved                          VARCHAR(100)          NULL COMMENT'실제 저장된 영상',
    thumbmp4                          VARCHAR(100)        NULL COMMENT'강의 영상 Preview',
    sizemp4                               INT                     NULL COMMENT'강의 영상 크기',
*/
public class ContentsVO {
  /** 컨텐츠 번호 */
  private int contentsno;
  /** 관리자 번호 */
  private int adminno;
  /** 즐겨찾기 번호*/
  private int chno;
  /** 제목 */
  private String title = "";
  /** 내용 */
  private String content = "";
  /** 강의시간 */
  private int lecturetime=0;
  /** 난이도*/
  private String tklevel = "";
  /** 추천수 */
  private int recom;
  /** 조회수 */
  private int cnt = 0;
  /** 댓글수 */
  private int replycnt = 0;
  /** 패스워드 */
  private String passwd = "";
  /** 검색어 */
  private String word = "";
  /** 등록 날짜 */
  private String rdate = "";
  
  /** 메인 이미지 */
  private String file1 = "";
  /** 실제 저장된 메인 이미지 */
  private String file1saved = "";  
  /** 메인 이미지 preview */
  private String thumb1 = "";
  /** 메인 이미지 크기 */
  private long size1;
  private MultipartFile file1MF;
  
  /**
   * 이미지 파일 크기 단위 출력
   */
  private String size1_label;
  
  /** 강의 영상 */
  private String mp4;                                         
  /** 실제 저장된 영상  */
  private String mp4saved;                   
  /**강의 영상 Preview */
  private String thumbmp4;                  
  /**강의 영상 크기 */
  private int sizemp4;
  private MultipartFile mp4MF;
  

  
  /**
   * 동영상 파일 크기 단위 출력
   */
  private String mp4_label;
  
  public int getContentsno() {
    return contentsno;
  }
  public void setContentsno(int contentsno) {
    this.contentsno = contentsno;
  }
  public int getAdminno() {
    return adminno;
  }
  public void setAdminno(int adminno) {
    this.adminno = adminno;
  }
  public int getChno() {
    return chno;
  }
  public void setChno(int chno) {
    this.chno = chno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  
  public int getLecturetime() {
    return lecturetime;
  }
  public void setLecturetime(int lecturetime) {
    this.lecturetime = lecturetime;
  }
  public String getTklevel() {
    return tklevel;
  }
  public void setTklevel(String tklevel) {
    this.tklevel = tklevel;
  }
  
  public int getRecom() {
    return recom;
  }
  public void setRecom(int recom) {
    this.recom = recom;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public int getReplycnt() {
    return replycnt;
  }
  public void setReplycnt(int replycnt) {
    this.replycnt = replycnt;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public String getFile1saved() {
    return file1saved;
  }
  public void setFile1saved(String file1saved) {
    this.file1saved = file1saved;
  }
  public String getThumb1() {
    return thumb1;
  }
  public void setThumb1(String thumb1) {
    this.thumb1 = thumb1;
  }
  public long getSize1() {
    return size1;
  }
  public void setSize1(long size1) {
    this.size1 = size1;
  }
  /** 
  이미지 MultipartFile 
  <input type='file' class="form-control" name='file1MF' id='file1MF' 
                   value='' placeholder="파일 선택">
  */
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public String getSize1_label() {
    return size1_label;
  }
  public void setSize1_label(String size1_label) {
    this.size1_label = size1_label;
  }
  //-----mp4관련 메소드----------
  public String getMp4() {
    return mp4;
  }
  public void setMp4(String mp4) {
    this.mp4 = mp4;
  }
  public String getMp4saved() {
    return mp4saved;
  }
  public void setMp4saved(String mp4saved) {
    this.mp4saved = mp4saved;
  }
  public String getThumbmp4() {
    return thumbmp4;
  }
  public void setThumbmp4(String thumbmp4) {
    this.thumbmp4 = thumbmp4;
  }
  public int getSizemp4() {
    return sizemp4;
  }
  public void setSizemp4(int sizemp4) {
    this.sizemp4 = sizemp4;
  }
  public MultipartFile getMp4MF() {
    return mp4MF;
  }
  public void setMp4MF(MultipartFile mp4mf) {
    mp4MF = mp4mf;
  }
  public String getMp4_label() {
    return mp4_label;
  }
  public void setMp4_label(String mp4_label) {
    this.mp4_label = mp4_label;
  }
  //----------------------------------------
  
}
