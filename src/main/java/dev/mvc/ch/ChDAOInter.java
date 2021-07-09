package dev.mvc.ch;

import java.util.List;




public interface ChDAOInter {
  /**
   * 등록
   * 
   * @param chVO
   * @return 등록된 레코드 갯수
   */
  public int create(ChVO chVO);

  /**
   * 목록
   * 
   * @return
   */
  public List<ChVO> list_all();
  
  /**
   * chgrpno별 목록
   * 
   * @return
   */
  public List<ChVO> list_by_chgrpno(int chgrpno);
  
  /**
   * chinesegrp + ch join, 연결 목록
   * 
   * @return
   */
  public List<Chinesegrp_ChVO> list_all_join();
 
  /**
   * 조회, 수정폼
   * 
   * @param - 카테고리 그룹 번호, PK
   * @return
   */
  public ChVO read(int chno);

  /**
   * 수정 처리
   * 
   * @param chVO
   * @return 처리된 레코드 갯수
   */
  public int update(ChVO chVO);
  
  /**
   * 삭제 처리
   * @param chno
   * @return 처리된 레코드 갯수
   */
  public int delete(int chno);
  
  /**
   * 특정 그룹에 속한 레코드 갯수 산출
   * @param chgrpno
   * @return
   */
  public int count_by_chgrpno(int chgrpno);
  
  /**
   * categrpno가 같은 모든 레코드 삭제
   * @param chgrpno
   * @return
   */
  public int delete_by_chgrpno(int chgrpno);

}
