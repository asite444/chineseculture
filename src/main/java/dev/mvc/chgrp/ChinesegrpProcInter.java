package dev.mvc.chgrp;

import java.util.List;

public interface ChinesegrpProcInter {
  /**
   * 등록
   * @param chinesegrpVO
   * @return 등록된 레코드 갯수
   */
  public int create(ChinesegrpVO chinesegrpVO);
  /**
   * 출력 순서별 목록
   * @return
   */
  public List<ChinesegrpVO> list_chinesegrpno_asc();
  /**
   * 조회, 수정폼
   * @param chgrpno 카테고리 그룹 번호, PK
   * @return
   */
  public ChinesegrpVO read(int chgrpno);
 
  /**
   * 수정 처리
   * @param chinesegrpVO
   * @return 처리된 레코드 갯수
   */
  public int update(ChinesegrpVO chinesegrpVO);
  
  /**
   * 삭제 처리
   * @param chgrpno
   * @return 처리된 레코드 갯수
   */
  public int delete(int chgrpno);
  
  /**
   * 출력 순서 상향
   * @param chgrpno
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_up(int chgrpno);
 
  /**
   * 출력 순서 하향
   * @param chgrpno
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_down(int chgrpno); 
  
  /**
   * 출력 순서별 목록
   * @return
   */
  public List<ChinesegrpVO> list_seqno_asc();
  

}
