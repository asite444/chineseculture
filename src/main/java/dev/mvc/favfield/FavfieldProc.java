package dev.mvc.favfield;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component("dev.mvc.favfield.FavfieldProc")
public class FavfieldProc implements FavfieldProcInter {
  @Autowired
  private FavfieldDAOInter favfieldDAO;
  
  @Override
  public int create(FavfieldVO favfieldVO) {
    int cnt;
    cnt=this.favfieldDAO.create(favfieldVO);
    return cnt;
  }

  @Override
  public List<FavfieldVO> list_all() {
    List<FavfieldVO> list = null;
    list = this.favfieldDAO.list_all();
    return list;
  }

  @Override
  public List<FavfieldVO> list_by_favgrpno(int favgrpno) {
    List<FavfieldVO> list=this.favfieldDAO.list_by_favgrpno(favgrpno);
    return list;
  }

  @Override
  public List<Favfieldgrp_FavfieldVO> list_all_join() {
    List<Favfieldgrp_FavfieldVO> list=this.favfieldDAO.list_all_join();
    return list;
  }

  @Override
  public FavfieldVO read(int favno) {
    FavfieldVO favfieldVO = null;
    favfieldVO = this.favfieldDAO.read(favno);
    return favfieldVO;
  }

  @Override
  public int update(FavfieldVO favfieldVO) {
    int cnt = 0;
    cnt = this.favfieldDAO.update(favfieldVO);
    return cnt;
  }

  @Override
  public int delete(int favno) {
    int cnt=0;
    cnt=this.favfieldDAO.delete(favno);
     return cnt;
  }



}
