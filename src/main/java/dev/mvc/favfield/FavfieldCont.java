package dev.mvc.favfield;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.favfieldgrp.FavfieldgrpProc;
import dev.mvc.favfieldgrp.FavfieldgrpProcInter;
import dev.mvc.favfieldgrp.FavfieldgrpVO;

@Controller
public class FavfieldCont {
  @Autowired
  @Qualifier("dev.mvc.favfield.FavfieldProc")
  private FavfieldProcInter favfieldProc;

  @Autowired
  @Qualifier("dev.mvc.favfieldgrp.FavfieldgrpProc")
  private FavfieldgrpProcInter favfieldgrpProc;

  public FavfieldCont() {
    System.out.println("-> FavfieldCont created.");
  }

  // http://localhost:9091/favfield/create.do
  /**
   * 등록 폼
   * 
   * @return
   */
  @RequestMapping(value = "/favfield/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/favfield/create"); // webapp/WEB-INF/views/favfield/create.jsp
    return mav; // forward
  }

  // forward의 중복발생을 방지하기 위함
  /**
   * 새로고침을 방지
   * 
   * @return
   */
  @RequestMapping(value = "/favfield/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward

    return mav; // forward
  }

  // http://localhost:9091/favfield/create.do
  /**
   * 등록 처리
   * 
   * @param favfieldVO
   * @return
   */

  @RequestMapping(value = "/favfield/create.do", method = RequestMethod.POST)
  public ModelAndView create(FavfieldVO favfieldVO) {

    ModelAndView mav = new ModelAndView();

    int cnt = this.favfieldProc.create(favfieldVO);// 등록 처리
    mav.addObject("cnt", cnt); // request에 저장, request.setAttribute("cnt", cnt)
    mav.addObject("favgrpno", favfieldVO.getFavgrpno());
    mav.addObject("favtitle", favfieldVO.getFavtitle());
    mav.addObject("favex", favfieldVO.getFavex());
    mav.addObject("url", "/favfield/create_msg");
    mav.setViewName("redirect:/favfield/msg.do"); // /webapp/WEB-INF/views/favfield/create_msg.jsp

    return mav; // forward
  }

  /**
   * 목록
   * 
   * @return
   */
  // http://localhost:9091/favfield/list_all.do
  @RequestMapping(value = "/favfield/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();

    // 등록 순서별 출력
    List<FavfieldVO> list = this.favfieldProc.list_all();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/favfield/list_all"); // /webapp/WEB-INF/views/favfieldgrp/list_all.jsp
    return mav;
  }

  /**
   * 카테고리 그룹별 전체 목록 http://localhost:9091/favfield/list_by_favgrpno.do?favgrpno=1
   * 
   * @return
   */
  @RequestMapping(value = "/favfield/list_by_favgrpno.do", method = RequestMethod.GET)
  public ModelAndView list_by_favgrpno(int favgrpno) {
    ModelAndView mav = new ModelAndView();

    List<FavfieldVO> list = this.favfieldProc.list_by_favgrpno(favgrpno);
    mav.addObject("list", list); // request.setAttribute("list", list);

    FavfieldgrpVO favfieldgrpVO = favfieldgrpProc.read(favgrpno);// 카테고리 그룹 정보
    mav.addObject("favfieldgrpVO", favfieldgrpVO);

    mav.setViewName("/favfield/list_by_favgrpno"); // /favfield/list_by_favgrpno.jsp
    return mav;
  }

  /**
   * favfieldgrp + favfield join, 연결 목록
   * http://localhost:9091/favfield/list_all_join.do
   * 
   * @return
   */
  @RequestMapping(value = "/favfield/list_all_join.do", method = RequestMethod.GET)
  public ModelAndView list_all_join() {
    ModelAndView mav = new ModelAndView();

    List<Favfieldgrp_FavfieldVO> list = this.favfieldProc.list_all_join();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/favfield/list_all_join"); // /favfield/list_all_join.jsp
    return mav;
  }

  // http://localhost:9091/favfield/read_update.do
  /**
   * 조회 + 수정폼
   * 
   * @param favfieldVO 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value = "/favfield/read_update.do", method = RequestMethod.GET)
  public ModelAndView read_update(int favno, int favgrpno) {

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/favfield/read_update"); // /WEB-INF/views/favfield/read_update.jsp

    FavfieldgrpVO favfieldgrpVO = this.favfieldgrpProc.read(favgrpno);// 카테고리 그룹 정보
    mav.addObject("favfieldgrpVO", favfieldgrpVO);

    FavfieldVO favfieldVO = this.favfieldProc.read(favno);
    mav.addObject("favfieldVO", favfieldVO); // request 객체에 저장

    List<FavfieldVO> list = this.favfieldProc.list_by_favgrpno(favgrpno);
    mav.addObject("list", list); // request 객체에 저장

    return mav; // forward
  }

  // http://localhost:9091/favfield/update.do
  /**
   * 수정 처리
   * 
   * @param favfieldVO
   * @return
   */
  @RequestMapping(value = "/favfield/update.do", method = RequestMethod.POST)
  public ModelAndView update(FavfieldVO favfieldVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = this.favfieldProc.update(favfieldVO);
    mav.addObject("cnt", cnt); 
    mav.addObject("favgrpno", favfieldVO.getFavgrpno());
    mav.addObject("favtitle", favfieldVO.getFavtitle());
    mav.addObject("favex", favfieldVO.getFavex());
    mav.addObject("url", "/favfield/update_msg");
    mav.setViewName("redirect:/favfield/msg.do"); // update_msg.jsp
    return mav;
  }

  // http://localhost:9091/favfield/read_delete.do
  /**
   * 조회 + 삭제폼
   * 
   * @param favfieldVO 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value = "/favfield/read_delete.do", method = RequestMethod.GET)
  public ModelAndView read_delete(int favno, int favgrpno) {

    ModelAndView mav = new ModelAndView();
    mav.setViewName("/favfield/read_delete"); // /WEB-INF/views/favfield/read_update.jsp

    FavfieldgrpVO favfieldgrpVO = this.favfieldgrpProc.read(favgrpno);// 카테고리 그룹 정보
    mav.addObject("favfieldgrpVO", favfieldgrpVO);

    FavfieldVO favfieldVO = this.favfieldProc.read(favno);
    mav.addObject("favfieldVO", favfieldVO); // request 객체에 저장

    List<FavfieldVO> list = this.favfieldProc.list_by_favgrpno(favgrpno);
    mav.addObject("list", list); // request 객체에 저장

    return mav; // forward
  }

 
  /**
   * 삭제 처리
   * 
   * @param favfieldVO
   * @return
   */
  @RequestMapping(value = "/favfield/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int favno,int favgrpno) {
    ModelAndView mav = new ModelAndView();
    FavfieldVO favfieldVO=this.favfieldProc.read(favno);
    int cnt = this.favfieldProc.delete(favno);
    mav.addObject("cnt", cnt); //
    // request에 저장 mav.addObject("favgrpno", favfieldVO.getFavgrpno());
    mav.addObject("favno", favfieldVO.getFavno());
    mav.addObject("favgrpno", favfieldVO.getFavgrpno());
    mav.addObject("favtitle", favfieldVO.getFavtitle());
    mav.addObject("favex", favfieldVO.getFavex());
    mav.addObject("url", "/favfield/delete_msg");
    mav.setViewName("redirect:/favfield/msg.do"); // update_msg.jsp
    return mav;
  }
}
