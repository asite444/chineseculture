package dev.mvc.favfield;

import java.util.List;

public interface FavfieldDAOInter {
  /**
   * 등록
   * 
   * @param favfieldVO
   * @return 등록된 레코드 갯수
   */
  public int create(FavfieldVO favfieldVO);

  /**
   * 목록
   * 
   * @return
   */
  public List<FavfieldVO> list_all();

  /**
   * favgrpno별 목록
   * 
   * @return
   */
  public List<FavfieldVO> list_by_favgrpno(int favgrpno);

  /**
   * favfieldgrp + favfield join, 연결 목록
   * 
   * @return
   */
  public List<Favfieldgrp_FavfieldVO> list_all_join();

  /**
   * 조회, 수정폼
   * 
   * @param - 카테고리 그룹 번호, PK
   * @return
   */
  public FavfieldVO read(int favno);

  /**
   * 수정 처리
   * 
   * @param favfieldVO
   * @return 처리된 레코드 갯수
   */
  public int update(FavfieldVO favfieldVO);

  /**
   * 삭제 처리
   * @param favno
   * @return 처리된 레코드 갯수
   */
  public int delete(int favno);
}
