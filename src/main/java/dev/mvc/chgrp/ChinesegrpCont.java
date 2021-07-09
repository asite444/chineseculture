package dev.mvc.chgrp;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.ch.ChProcInter;

@Controller
public class ChinesegrpCont {
  @Autowired
  @Qualifier("dev.mvc.chgrp.ChinesegrpProc")
  private ChinesegrpProcInter chinesegrpProc;
  
  @Autowired
  @Qualifier("dev.mvc.ch.ChProc")
  private ChProcInter chProc;

 
  public ChinesegrpCont() {
    System.out.println("-> ChinesegrpCont created.");
  }
  // forward의 중복발생을 방지하기 위함
  /**
   * 새로고침을 방지
   * 
   * @return
   */
  @RequestMapping(value = "/chinesegrp/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward

    return mav; // forward
  }
  // http://localhost:9091/chinesegrp/create.do
  /**
   * 등록 폼
   * 
   * @return
   */

  @RequestMapping(value = "/chinesegrp/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/chinesegrp/create"); // webapp/WEB-INF/views/chinesegrp/create.jsp

    return mav; // forward
  }

  // http://localhost:9091/chinesegrp/create.do
  /**
   * 등록 처리
   * 
   * @param chinesegrpVO
   * @return
   */
  @RequestMapping(value = "/chinesegrp/create.do", method = RequestMethod.POST)
  public ModelAndView create(ChinesegrpVO chinesegrpVO) { // categrpVO 자동 생성, Form -> VO

    ModelAndView mav = new ModelAndView();

    int cnt = this.chinesegrpProc.create(chinesegrpVO); // 등록 처리
    //mav.addObject("cnt", cnt); // request에 저장, request.setAttribute("cnt", cnt)
    //mav.addObject("url", "/chinesegrp/create_msg");
    mav.setViewName("redirect:/chinesegrp/msg.do"); // /webapp/WEB-INF/views/chinesegrp/create_msg.jsp
    if(cnt==1) {
      mav.setViewName("redirect:/chinesegrp/list.do");
    }else {
      mav.addObject("code", "create"); // request에 저장, request.setAttribute("cnt", cnt),따라서 EL문->${}사용 가능하다.
      mav.setViewName("/chinesegrp/error_msg");
    }
    return mav; // forward
  }

  //http://localhost:9091/chinesegrp/list.do
//  @RequestMapping(value = "/chinesegrp/list.do", method = RequestMethod.GET)
//  public ModelAndView list() {
//    ModelAndView mav = new ModelAndView();
//    
//    // 등록 순서별 출력    
//    //List<ChinesegrpVO> list = this.chinesegrpProc.list_chinesegrpno_asc();
//    // 출력 순서별 출력
//    List<ChinesegrpVO> list = this.chinesegrpProc.list_seqno_asc();
//    mav.addObject("list", list); // request.setAttribute("list", list);
//
//    mav.setViewName("/chinesegrp/list"); // /webapp/WEB-INF/views/chinesegrp/list.jsp
//    return mav;
//  }
  
  /** Ajax 기반 목록,url은 변경없음
   * http://localhost:9091/chinesegrp/list.do
   * 목록
   * @return
   */
  @RequestMapping(value = "/chinesegrp/list.do", method = RequestMethod.GET)
  public ModelAndView list_ajax() {
    ModelAndView mav = new ModelAndView();
    
    // 출력 순서별 출력
    List<ChinesegrpVO> list = this.chinesegrpProc.list_seqno_asc();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/chinesegrp/list_ajax"); // /webapp/WEB-INF/views/chinesegrp/list.jsp
    return mav;
  }

//http://localhost:9091/chinesegrp/read_update.do?chgrpno=1
  /**
  * 조회 + 수정폼
   * 
   * @param chinesegrpVO 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value = "/chinesegrp/read_update.do", method = RequestMethod.GET)
  public ModelAndView read_update(int chgrpno) {

   ModelAndView mav = new ModelAndView();

   ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chgrpno);
   mav.addObject("chinesegrpVO", chinesegrpVO); // request 객체에 저장

    List<ChinesegrpVO> list = this.chinesegrpProc.list_chinesegrpno_asc();
    mav.addObject("list", list); // request 객체에 저장

   mav.setViewName("/chinesegrp/read_update"); // /WEB-INF/views/chinesegrp/read_update.jsp
   return mav; // forward
  }

  /**http://localhost:9091/chinesegrp/read_ajax.do?chgrpno=1
   * 조회 + 수정폼/삭제폼+ Ajax
   * 
   * @param chinesegrpVO 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value = "/chinesegrp/read_ajax.do", method = RequestMethod.GET)
  @ResponseBody
  public String read_ajax(int chgrpno) {
    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chgrpno);
    
    JSONObject json = new JSONObject();
   json.put("chgrpno", chinesegrpVO.getChgrpno());
   json.put("topic", chinesegrpVO.getTopic());
   json.put("chfield", chinesegrpVO.getChfield());
   json.put("seqno", chinesegrpVO.getSeqno());
   json.put("rdate", chinesegrpVO.getRdate());
   
   //자식 레코드의 갯수 추가 
   int count_by_chgrpno=this.chProc.count_by_chgrpno(chgrpno);
   json.put("count_by_chgrpno", count_by_chgrpno);
   
    return json.toString();
  }
// http://localhost:9091/chinesegrp/update.do
  /**
   * 수정 처리
   * 
   * @param chinesegrpVO
   * @return
   */
  @RequestMapping(value = "/chinesegrp/update.do", method = RequestMethod.POST)
  public ModelAndView update(ChinesegrpVO chinesegrpVO) {


    ModelAndView mav = new ModelAndView();

    int cnt = this.chinesegrpProc.update(chinesegrpVO);
    //mav.addObject("cnt", cnt); // request에 저장
    //cnt=0;
    if(cnt==1) {
      mav.setViewName("redirect:/chinesegrp/list.do");
    }else {
      mav.addObject("code", "update"); // request에 저장
      mav.setViewName("/chinesegrp/error_msg");
    }
    
    //mav.setViewName("/chinesegrp/update_msg"); // update_msg.jsp

    return mav;
  }
  
  // http://localhost:9091/chinesegrp/read_delete.do
  /**
   * 조회 + 삭제폼
   * @param chgrpno 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value="/chinesegrp/read_delete.do", method=RequestMethod.GET )
  public ModelAndView read_delete(int chgrpno) {
    ModelAndView mav = new ModelAndView();
    
    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chgrpno); // 삭제할 자료 읽기
    mav.addObject("chinesegrpVO", chinesegrpVO);  // request 객체에 저장
    
    List<ChinesegrpVO> list = this.chinesegrpProc.list_chinesegrpno_asc();
    mav.addObject("list", list);  // request 객체에 저장

    mav.setViewName("/chinesegrp/read_delete"); // read_delete.jsp
    return mav;
  }

 //http://localhost:9091/chinesegrp/delete.do
  /**
   * 삭제
   * 
   * @param chgrpno 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value = "/chinesegrp/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int chgrpno) {
    ModelAndView mav = new ModelAndView();

    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chgrpno); // 삭제 정보
    mav.addObject("chinesegrpVO", chinesegrpVO); // request 객체에 저장

    int cnt = this.chinesegrpProc.delete(chgrpno); // 삭제 처리
    mav.addObject("cnt", cnt); // request 객체에 저장

    if(cnt==1) {
      mav.setViewName("redirect:/chinesegrp/list.do");
    }else {
      mav.addObject("code", "delete");
      mav.setViewName("/chinesegrp/error_msg"); // delete_msg.jsp
    }
    
    
    
    //mav.setViewName("/chinesegrp/delete_msg"); // delete_msg.jsp

    return mav;
  }
  
  // http://localhost:9091/chinesegrp/update_seqno_up.do?chgrpno=1
  // http://localhost:9091/chinesegrp/update_seqno_up.do?chgrpno=1000
  /**
   * 우선순위 상향 up 10 ▷ 1
   * @param chgrpno 카테고리 번호
   * @return
   */
  @RequestMapping(value="/chinesegrp/update_seqno_up.do", 
                              method=RequestMethod.GET )
  public ModelAndView update_seqno_up(int chgrpno) {
    ModelAndView mav = new ModelAndView();
    
    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chgrpno); // 카테고리 그룹 정보
    mav.addObject("chinesegrpVO", chinesegrpVO);  // request 객체에 저장
    
    int cnt = this.chinesegrpProc.update_seqno_up(chgrpno);  // 우선 순위 상향 처리
    mav.addObject("cnt", cnt);  // request 객체에 저장
    
    //mav.setViewName("/chinesegrp/update_seqno_up_msg"); // update_seqno_up_msg.jsp
    mav.setViewName("redirect:/chinesegrp/list.do"); 
    return mav;
  }  
  
  // http://localhost:9091/chinesegrp/update_seqno_down.do?chgrpno=1
  // http://localhost:9091/chinesegrp/update_seqno_down.do?chgrpno=1000
  /**
   * 우선순위 하향 up 1 ▷ 10
   * @param chgrpno 카테고리 번호
   * @return
   */
  @RequestMapping(value="/chinesegrp/update_seqno_down.do", 
                              method=RequestMethod.GET )
  public ModelAndView update_seqno_down(int chgrpno) {
    ModelAndView mav = new ModelAndView();
    
    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chgrpno); // 카테고리 그룹 정보
    mav.addObject("chinesegrpVO", chinesegrpVO);  // request 객체에 저장
    
    int cnt = this.chinesegrpProc.update_seqno_down(chgrpno);
    mav.addObject("cnt", cnt);  // request 객체에 저장

    //mav.setViewName("/chinesegrp/update_seqno_down_msg"); // update_seqno_down_msg.jsp
    mav.setViewName("redirect:/chinesegrp/list.do"); 
    return mav;
  }  
  
}