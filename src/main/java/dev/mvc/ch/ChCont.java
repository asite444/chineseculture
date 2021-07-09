package dev.mvc.ch;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.chgrp.ChinesegrpProcInter;
import dev.mvc.chgrp.ChinesegrpVO;
import dev.mvc.contents.ContentsProcInter;

@Controller
public class ChCont {
  @Autowired
  @Qualifier("dev.mvc.ch.ChProc")
  private ChProcInter chProc;
  @Autowired
  @Qualifier("dev.mvc.chgrp.ChinesegrpProc")
  private ChinesegrpProcInter chinesegrpProc;
  
    @Autowired
   @Qualifier("dev.mvc.contents.ContentsProc") 
    private ContentsProcInter contentsProc;
   
  
  // http://localhost:9091/chinese/create.do
  /**
   * 등록 폼
   * 
   * @return
   */
  @RequestMapping(value = "/chinese/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/chinese/create");
    return mav; // forward
  }

  // forward의 중복발생을 방지하기 위함
  /**
   * 새로고침을 방지
   * 
   * @return
   */
  @RequestMapping(value = "/chinese/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward

    return mav; // forward
  }

  /**
   * 등록 처리
   * 
   * @param chVO
   * @return
   */

  @RequestMapping(value = "/chinese/create.do", method = RequestMethod.POST)
  public ModelAndView create(ChVO chVO) {

    ModelAndView mav = new ModelAndView();

    int cnt = this.chProc.create(chVO);// 등록 처리
    mav.setViewName("redirect:/chinese/msg.do"); 
    //cnt=0;
    if(cnt==1) {
    mav.addObject("chgrpno", chVO.getChgrpno());
    mav.setViewName("redirect:/chinese/list_by_chgrpno.do"); // /webapp/WEB-INF/views/favfield/create_msg.jsp
    }else {
      mav.addObject("code", "create");
      mav.addObject("chgrpno", chVO.getChgrpno());
      mav.setViewName("/chinese/error_msg");
    }
    return mav; // forward
  }

  /**
   * 목록
   * 
   * @return
   */
  // http://localhost:9091/chinese/list_all.do
  @RequestMapping(value = "/chinese/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();

    // 등록 순서별 출력
    List<ChVO> list = this.chProc.list_all();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/chinese/list_all"); // /webapp/WEB-INF/views/favfieldgrp/list_all.jsp
    return mav;
  }

  /*  *//**
         * 카테고리 그룹별 전체 목록 http://localhost:9091/chinese/list_by_chgrpno.do?chgrpno=1
         * 
         * @return
         *//*
            * @RequestMapping(value = "/chinese/list_by_chgrpno.do", method =
            * RequestMethod.GET) public ModelAndView list_by_chgrpno(int chgrpno) {
            * ModelAndView mav = new ModelAndView();
            * 
            * List<ChVO> list = this.chProc.list_by_chgrpno(chgrpno); mav.addObject("list",
            * list); // request.setAttribute("list", list);
            * 
            * ChinesegrpVO chinesegrpVO = chinesegrpProc.read(chgrpno);// 카테고리 그룹 정보
            * mav.addObject("chinesegrpVO", chinesegrpVO);
            * 
            * mav.setViewName("/chinese/list_by_chgrpno"); return mav; }
            */

  /**
   * 카테고리 그룹별 전체 목록 http://localhost:9091/chinese/list_by_chgrpno.do?chgrpno=1
   * ajax 기반
   * @return
   */
  @RequestMapping(value = "/chinese/list_by_chgrpno.do", method = RequestMethod.GET)
  public ModelAndView list_by_chgrpno_ajax(int chgrpno) {
    ModelAndView mav = new ModelAndView();

    List<ChVO> list = this.chProc.list_by_chgrpno(chgrpno);
    mav.addObject("list", list); // request.setAttribute("list", list);

    ChinesegrpVO chinesegrpVO = chinesegrpProc.read(chgrpno);// 카테고리 그룹 정보
    mav.addObject("chinesegrpVO", chinesegrpVO);

    mav.setViewName("/chinese/list_by_chgrpno_ajax");
    return mav;
  }

  /**
   * chinesegrp +ch join, 연결 목록 http://localhost:9091/chinese/list_all_join.do
   * 
   * @return
   */
  @RequestMapping(value = "/chinese/list_all_join.do", method = RequestMethod.GET)
  public ModelAndView list_all_join() {
    ModelAndView mav = new ModelAndView();

    List<Chinesegrp_ChVO> list = this.chProc.list_all_join();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/chinese/list_all_join");
    return mav;
  }

//http://localhost:9091/chinese/read_update.do
  /**
   * 조회 + 수정폼
   * 
   * @param favfieldVO 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value = "/chinese/read_update.do", method = RequestMethod.GET)
  public ModelAndView read_update(int chno, int chgrpno) {

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/chinese/read_update"); // /WEB-INF/views/favfield/read_update.jsp

    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chgrpno);// 카테고리 그룹 정보
    mav.addObject("chinesegrpVO", chinesegrpVO);

    ChVO chVO = this.chProc.read(chno);
    mav.addObject("chVO", chVO); // request 객체에 저장

    List<ChVO> list = this.chProc.list_by_chgrpno(chgrpno);
    mav.addObject("list", list); // request 객체에 저장

    return mav; // forward
  }
  

  /**
   * 조회 + 수정폼 + Ajax, , VO에서 각각의 필드를 JSON으로 변환하는경우
   * http://localhost:9091/chinese/read_ajax.do?chno=3
   * {"categrpno":1,"visible":"Y","seqno":1,"rdate":"2021-04-08 17:01:28","name":"문화"}
   * @param categrpno 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value="/chinese/read_ajax.do", 
                              method=RequestMethod.GET )
  @ResponseBody
  public String read_ajax(int chno) {
    ChVO chVO = this.chProc.read(chno);
    
    JSONObject json = new JSONObject();
    json.put("chno", chVO.getChno());
    json.put("chgrpno", chVO.getChgrpno());
    json.put("chtitle", chVO.getChtitle());
    json.put("chex", chVO.getChex());
    json.put("rdate", chVO.getRdate());
    
    //자식 레코드의 갯수 추가 
    int count_by_chno=this.contentsProc.count_by_chno(chno);
    json.put("count_by_chno", count_by_chno);
    
    return json.toString();

  }
  /**
   * 수정 처리
   * 
   * @param favfieldVO
   * @return
   */
  @RequestMapping(value = "/chinese/update.do", method = RequestMethod.POST)
  public ModelAndView update(ChVO chVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.chProc.update(chVO);
    //cnt=0;
    if(cnt==1) {
    mav.addObject("cnt", cnt);
    mav.addObject("chgrpno", chVO.getChgrpno());
    mav.addObject("chtitle", chVO.getChtitle());
    mav.addObject("chex", chVO.getChex());
    mav.addObject("url", "/chinese/update_msg");
    mav.setViewName("redirect:/chinese/list_by_chgrpno.do"); // update_msg.jsp
    }
    else {
      mav.addObject("code", "update"); // request에 저장
      mav.addObject("chgrpno", chVO.getChgrpno());
      mav.setViewName("/chinese/error_msg");
    }
    return mav;
  }

// http://localhost:9091/favfield/read_delete.do
  /**
   * 조회 + 삭제폼
   * 
   * @param favfieldVO 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value = "/chinese/read_delete.do", method = RequestMethod.GET)
  public ModelAndView read_delete(int chno, int chgrpno) {

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/chinese/read_delete"); // /WEB-INF/views/favfield/read_update.jsp

    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chgrpno);// 카테고리 그룹 정보
    mav.addObject("chinesegrpVO", chinesegrpVO);

    ChVO chVO = this.chProc.read(chno);
    mav.addObject("chVO", chVO); // request 객체에 저장

    List<ChVO> list = this.chProc.list_by_chgrpno(chgrpno);
    mav.addObject("list", list); // request 객체에 저장

    return mav; // forward
  }

  /**
   * 삭제 처리
   * 
   * @param favfieldVO
   * @return
   */
  @RequestMapping(value = "/chinese/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int chno, int chgrpno) {
    ModelAndView mav = new ModelAndView();
    ChVO chVO = this.chProc.read(chno);
    int cnt = this.chProc.delete(chno);
    //cnt=0;
    if(cnt==1) {
    mav.addObject("cnt", cnt);
    mav.addObject("chno", chVO.getChno());
    mav.addObject("chgrpno", chVO.getChgrpno());
    mav.addObject("chtitle", chVO.getChtitle());
    mav.addObject("chex", chVO.getChex());
    mav.addObject("url", "/chinese/delete_msg");
    mav.setViewName("redirect:/chinese/list_by_chgrpno.do"); // update_msg.jsp
    }else {
      mav.addObject("code", "delete");
      mav.addObject("chgrpno", chgrpno);
      mav.setViewName("/chinese/error_msg"); // delete_msg.jsp
    }
    return mav;
  }

  /**
   * chgrpno가 같은 모든 레코드 삭제
   * 
   * @param chVO
   * @return
   */
  @RequestMapping(value = "/chinese/delete_by_chgrpno.do", method = RequestMethod.POST)
  public ModelAndView delete_by_chgrpno(int chgrpno) {
    ModelAndView mav = new ModelAndView();
    int cnt = this.chProc.delete_by_chgrpno(chgrpno);

    mav.addObject("chgrpno", chgrpno);

    mav.setViewName("redirect:/chinese/list_by_chgrpno.do"); // 새로고침 문제 해결, request 초기화

    return mav;
  }
  
  
}
