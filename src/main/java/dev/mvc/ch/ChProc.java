package dev.mvc.ch;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.ch.ChProc")
public class ChProc implements ChProcInter {
  @Autowired
  private ChDAOInter  chDAO;
  @Override
  public int create(ChVO chVO) {
    int cnt;
    cnt=this.chDAO.create(chVO);
    return cnt;
  }
  @Override
  public List<ChVO> list_all() {
    List<ChVO> list=null;
    list=this.chDAO.list_all();
    return list;
  }
  @Override
  public List<ChVO> list_by_chgrpno(int chgrpno) {
    List<ChVO> list=this.chDAO.list_by_chgrpno(chgrpno);
    return list;
  }
  @Override
  public List<Chinesegrp_ChVO> list_all_join() {
    List<Chinesegrp_ChVO> list=this.chDAO.list_all_join();
    return list;
  }
  @Override
  public ChVO read(int chno) {
    ChVO chVO=null;
    chVO=this.chDAO.read(chno);
    return chVO;
  }
  @Override
  public int update(ChVO chVO) {
    int cnt = 0;
    cnt =this.chDAO.update(chVO);
    return cnt;
  }
  @Override
  public int delete(int chno) {
    int cnt = 0;
    cnt =this.chDAO.delete(chno);
    return cnt;
  }
  @Override
  public int count_by_chgrpno(int chgrpno) {
    int cnt=this.chDAO.count_by_chgrpno(chgrpno);
    return cnt;
  }
  @Override
  public int delete_by_chgrpno(int chgrpno) {
    int cnt=this.chDAO.delete_by_chgrpno(chgrpno);
    return cnt;
  }

}
