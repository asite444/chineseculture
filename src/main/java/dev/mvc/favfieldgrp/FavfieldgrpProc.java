package dev.mvc.favfieldgrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.favfieldgrp.FavfieldgrpProc")
public class FavfieldgrpProc implements FavfieldgrpProcInter {

  @Autowired
  private FavfieldgrpDAOInter favfieldgrpDAO;

  @Override
  public int create(FavfieldgrpVO favfieldgrpVO) {
    int cnt;
    cnt = this.favfieldgrpDAO.create(favfieldgrpVO);
    return cnt;
  }

  @Override
  public List<FavfieldgrpVO> list_favgrpno_asc() {
    List<FavfieldgrpVO> list = null;
    list = this.favfieldgrpDAO.list_favgrpno_asc();
    return list;
  }

  @Override
  public FavfieldgrpVO read(int favgrpno) {
    FavfieldgrpVO favfieldgrpVO = null;
    favfieldgrpVO = this.favfieldgrpDAO.read(favgrpno);
    return favfieldgrpVO;
  }

  @Override
  public int update(FavfieldgrpVO favfieldgrpVO) {
    int cnt = 0;
    cnt = this.favfieldgrpDAO.update(favfieldgrpVO);
    return cnt;
  }

  @Override
  public int delete(int favgrpno) {
   int cnt=0;
   cnt=this.favfieldgrpDAO.delete(favgrpno);
    return cnt;
  }

}
