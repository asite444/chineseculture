package dev.mvc.contents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ContentsDAOInter {
  /**
   * 등록
   * @param contentsVO
   * @return
   */
  public int create(ContentsVO contentsVO);
  
  /**
   * 특정 카테고리의 등록된 글목록
   * @return
   */
  public List<ContentsVO> list_by_chno(int chno);
  
  /**
   * 카테고리별 검색 목록
   * @param hashMap
   * @return
   */
  public List<ContentsVO> list_by_chno_search(HashMap<String, Object> hashMap);

  /**
   * 카테고리별 검색 레코드 갯수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 검색 + 페이징 목록
   * @param map
   * @return
   */
  public List<ContentsVO> list_by_chno_search_paging(HashMap<String, Object> map);
  
  /**
   * 그리드+검색 + 페이징 목록
   * @param map
   * @return
   */
  public List<ContentsVO> list_by_chno_grid_search_paging(HashMap<String, Object> map);
  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public ContentsVO read(int contentsno);
  
  /**
   * 텍스트 정보 수정
   * @param contentsVO
   * @return
   */
  public int update_text(ContentsVO contentsVO);
  
  /**
   * 파일 정보 수정
   * @param contentsVO
   * @return
   */
  public int update_file(ContentsVO contentsVO);
 
  /**
   * 특정 그룹에 속한 레코드 갯수 산출
   * @param chno
   * @return
   */
  public int count_by_chno(int chno);
  
  /**
   * 삭제
   * @param contentsno
   * @return
   */
  public int delete(int contentsno);
  
  /**
   * 다수의 chnos를 전달하여 contents 레코드 삭제
   * @param chnos
   * @return
   */
  public int delete_contents_by_all_chno(Map<String, Object> chnos);
}
