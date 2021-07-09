package dev.mvc.chgrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.chgrp.ChinesegrpProc")
public class ChinesegrpProc implements ChinesegrpProcInter {
  @Autowired
  private ChinesegrpDAOInter chinesegrpDAO;
  
  @Override
  public int create(ChinesegrpVO chinesegrpVO) {
    int cnt;
    cnt=chinesegrpDAO.create(chinesegrpVO);
    return cnt;
  }

  @Override
  public List<ChinesegrpVO> list_chinesegrpno_asc() {
    List<ChinesegrpVO> list = null;
    list = this.chinesegrpDAO.list_chinesegrpno_asc();
    return list;
  }

  @Override
  public ChinesegrpVO read(int chgrpno) {
    ChinesegrpVO chinesegrpVO = null;
    chinesegrpVO = this.chinesegrpDAO.read(chgrpno);
     return chinesegrpVO;
  }

  @Override
  public int update(ChinesegrpVO chinesegrpVO) {
   int cnt=0;
   cnt=this.chinesegrpDAO.update(chinesegrpVO);
    return cnt;
  }

  @Override
  public int delete(int chgrpno) {
    int cnt = 0;
    cnt = this.chinesegrpDAO.delete(chgrpno);
    
    return cnt;
  }

  @Override
  public int update_seqno_up(int chgrpno) {
    int cnt=0;
    cnt=this.chinesegrpDAO.update_seqno_up(chgrpno);
    return cnt;
  }

  @Override
  public int update_seqno_down(int chgrpno) {
    int cnt=0;
    cnt=this.chinesegrpDAO.update_seqno_down(chgrpno);
    return cnt;
  }

  @Override
  public List<ChinesegrpVO> list_seqno_asc() {
    List<ChinesegrpVO> list=null;
    list=this.chinesegrpDAO.list_seqno_asc();
    return list;
  }


  
}
