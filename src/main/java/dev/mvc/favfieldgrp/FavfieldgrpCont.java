package dev.mvc.favfieldgrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;






@Controller
public class FavfieldgrpCont {
  @Autowired
  @Qualifier("dev.mvc.favfieldgrp.FavfieldgrpProc")
  private FavfieldgrpProcInter favfieldgrpProc;

  
  public FavfieldgrpCont(){
    System.out.println("-> FavfieldgrpCont created.");
  }
   
  // forward의 중복발생을 방지하기 위함
  /**
   * 새로고침을 방지
   * 
   * @return
   */
  @RequestMapping(value = "/favfieldgrp/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward

    return mav; // forward
  }
  // http://localhost:9091/favfieldgrp/create.do
  /**
   * 등록 폼
   * 
   * @return
   */
  @RequestMapping(value = "/favfieldgrp/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/favfieldgrp/create"); // webapp/WEB-INF/views/favfieldgrp/create.jsp
    return mav; // forward
  }
  
  // http://localhost:9091/favfieldgrp/create.do
  /**
   * 등록 처리
   * 
   * @param favfieldgrpVO
   * @return
   */
  @RequestMapping(value = "/favfieldgrp/create.do", method = RequestMethod.POST)
  public ModelAndView create(FavfieldgrpVO favfieldgrpVO) { 

    ModelAndView mav = new ModelAndView();

    int cnt = this.favfieldgrpProc.create(favfieldgrpVO);// 등록 처리
    mav.addObject("cnt", cnt); // request에 저장, request.setAttribute("cnt", cnt)
    mav.addObject("url", "/favfieldgrp/create_msg");
    mav.setViewName("redirect:/favfieldgrp/msg.do"); // /webapp/WEB-INF/views/favfieldgrp/create_msg.jsp

    return mav; // forward
  }
  /**
   * 목록
   * @return
   */
  //http://localhost:9091/favfieldgrp/list.do
  @RequestMapping(value = "/favfieldgrp/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    // 등록 순서별 출력    
    List<FavfieldgrpVO> list = this.favfieldgrpProc.list_favgrpno_asc();
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/favfieldgrp/list"); // /webapp/WEB-INF/views/favfieldgrp/list.jsp
    return mav;
  }
  
//http://localhost:9091/favfieldgrp/read_update.do
  /**
   * 조회 + 수정폼
   * 
   * @param favfieldgrpVO 조회할 카테고리 번호
   * @return
   */
  @RequestMapping(value = "/favfieldgrp/read_update.do", method = RequestMethod.GET)
  public ModelAndView read_update(int favgrpno) {

    ModelAndView mav = new ModelAndView();

    FavfieldgrpVO favfieldgrpVO = this.favfieldgrpProc.read(favgrpno);
    mav.addObject("favfieldgrpVO", favfieldgrpVO); // request 객체에 저장

    List<FavfieldgrpVO> list = this.favfieldgrpProc.list_favgrpno_asc();
    mav.addObject("list", list); // request 객체에 저장

    mav.setViewName("/favfieldgrp/read_update"); // /WEB-INF/views/favfieldgrp/read_update.jsp
    return mav; // forward
  }
  
//http://localhost:9091/favfieldgrp/update.do
 /**
  * 수정 처리
  * 
  * @param favfieldgrpVO
  * @return
  */
 @RequestMapping(value = "/favfieldgrp/update.do", method = RequestMethod.POST)
 public ModelAndView update(FavfieldgrpVO favfieldgrpVO) {


   ModelAndView mav = new ModelAndView();

   int cnt = this.favfieldgrpProc.update(favfieldgrpVO);
   mav.addObject("cnt", cnt); // request에 저장

   mav.setViewName("/favfieldgrp/update_msg"); // update_msg.jsp

   return mav;
 }
 
 // http://localhost:9091/favfieldgrp/read_delete.do
 /**
  * 조회 + 삭제폼
  * @param favgrpno 조회할 카테고리 번호
  * @return
  */
 @RequestMapping(value="/favfieldgrp/read_delete.do", method=RequestMethod.GET )
 public ModelAndView read_delete(int favgrpno) {
   ModelAndView mav = new ModelAndView();
   
   FavfieldgrpVO favfieldgrpVO = this.favfieldgrpProc.read(favgrpno); // 삭제할 자료 읽기
   mav.addObject("favfieldgrpVO", favfieldgrpVO);  // request 객체에 저장
   
   List<FavfieldgrpVO> list = this.favfieldgrpProc.list_favgrpno_asc();
   mav.addObject("list", list);  // request 객체에 저장

   mav.setViewName("/favfieldgrp/read_delete"); // read_delete.jsp
   return mav;
 }
 
 //http://localhost:9091/favfieldgrp/delete.do
 /**
  * 삭제
  * 
  * @param favgrpno 조회할 카테고리 번호
  * @return
  */
 @RequestMapping(value = "/favfieldgrp/delete.do", method = RequestMethod.POST)
 public ModelAndView delete(int favgrpno) {
   ModelAndView mav = new ModelAndView();

   FavfieldgrpVO favfieldgrpVO = this.favfieldgrpProc.read(favgrpno);// 삭제 정보
   mav.addObject("favfieldgrpVO", favfieldgrpVO); // request 객체에 저장

   int cnt = this.favfieldgrpProc.delete(favgrpno); // 삭제 처리
   mav.addObject("cnt", cnt); // request 객체에 저장

   mav.setViewName("/favfieldgrp/delete_msg"); // delete_msg.jsp

   return mav;
 }
}
