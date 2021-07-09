package dev.mvc.favfieldgrp;

import java.util.List;



public interface FavfieldgrpDAOInter {
  /**
   * 등록
   * @param favfieldgrpVO
   * @return 등록된 레코드 갯수
   */
  public int create(FavfieldgrpVO favfieldgrpVO);
  
  /**
   * 목록
   * @return
   */
  public List<FavfieldgrpVO> list_favgrpno_asc();
  
  /**
   * 조회, 수정폼
   * @param - 카테고리 그룹 번호, PK
   * @return
   */
  public FavfieldgrpVO read(int favgrpno);
  
  /**
   * 수정 처리
   * @param favfieldgrpVO
   * @return 처리된 레코드 갯수
   */
  public int update(FavfieldgrpVO favfieldgrpVO);
  
  /**
   * 삭제 처리
   * @param favgrpno
   * @return 처리된 레코드 갯수
   */
  public int delete(int favgrpno);
}
