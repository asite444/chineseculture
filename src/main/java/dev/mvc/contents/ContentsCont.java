package dev.mvc.contents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.ch.ChProcInter;
import dev.mvc.ch.ChVO;
import dev.mvc.chgrp.ChinesegrpProcInter;
import dev.mvc.chgrp.ChinesegrpVO;
//import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ContentsCont {

  @Autowired
  @Qualifier("dev.mvc.ch.ChProc")
  private ChProcInter chProc;

  @Autowired
  @Qualifier("dev.mvc.chgrp.ChinesegrpProc")
  private ChinesegrpProcInter chinesegrpProc;

  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc")
  private ContentsProcInter contentsProc;

  public ContentsCont() {
    System.out.println("-> ContentsCont created.");
  }

  /**
   * 새로고침 방지
   * 
   * @return
   */
  @RequestMapping(value = "/contents/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward

    return mav; // forward
  }

  /**
   * 등록폼 사전 준비된 레코드: 관리자 1번, cateno 1번, categrpno 1번을 사용하는 경우 테스트 URL
   * http://localhost:9091/contents/create.do?adminno=1&chno=1&chgrpno=1
   * 
   * @return
   */
  @RequestMapping(value = "/contents/create.do", method = RequestMethod.GET)
  public ModelAndView create(int chno) {
    ModelAndView mav = new ModelAndView();

    ChVO chVO = this.chProc.read(chno);
    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chVO.getChgrpno());

    mav.addObject("chVO", chVO);
    mav.addObject("chinesegrpVO", chinesegrpVO);
 
    mav.setViewName("/contents/create"); // /webapp/WEB-INF/views/contents/create.jsp
    // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
    // mav.addObject("content", content);

    return mav; // forward
  }

  /**
   * 등록 처리 http://localhost:9091/contents/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/contents/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ContentsVO contentsVO) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = ""; // 원본 파일명 image
    String file1saved = ""; // 저장된 파일명, image
    String thumb1 = ""; // preview image
    
    // 기준 경로 확인
    String user_dir = System.getProperty("user.dir");
    System.out.println("--> User dir: " + user_dir);
    

    // 파일 접근임으로 절대 경로 지정, static 지정
    // 완성된 경로
    // C:/ai8/ws_frame/chineseculture/src/main/resources/static/contents/storage
    String upDir = user_dir + "/src/main/resources/static/contents/storage/"; // 절대 경로

    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF'
    // value='' placeholder="파일 선택">
    MultipartFile mf = contentsVO.getFile1MF();

    file1 = mf.getOriginalFilename(); // 원본 파일명
    long size1 = mf.getSize(); // 파일 크기

    if (size1 > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1saved = Upload.saveFileSpring(mf, upDir);

      if (Tool.isImage(file1saved)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
        thumb1 = Tool.preview(upDir, file1saved, 200, 150);
      }

    }

    contentsVO.setFile1(file1);
    contentsVO.setFile1saved(file1saved);
    contentsVO.setThumb1(thumb1);
    contentsVO.setSize1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.contentsProc.create(contentsVO);
   

    // -------------------------------------------------------------------
    // PK의 return
    // -------------------------------------------------------------------
    // System.out.println("--> contentsno: " + contentsVO.getContentsno());
    // mav.addObject("contentsno", contentsVO.getContentsno()); // redirect
    // parameter 적용
    // -------------------------------------------------------------------

   //    if (cnt == 1) {
   //      cateProc.increaseCnt(contentsVO.getCateno()); // 글수 증가
  //    }
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

    // System.out.println("--> cateno: " + contentsVO.getCateno());
    // redirect시에 hidden tag로 보낸것들이 전달이 안됨으로 request에 다시 저장
    mav.addObject("chno", contentsVO.getChno()); // redirect parameter 적용
    mav.addObject("url", "/contents/create_msg"); // create_msg.jsp, redirect parameter 적용
    mav.addObject("contentsno", contentsVO.getContentsno());
    
    mav.setViewName("redirect:/contents/msg.do");
   
    return mav; // forward
  }
  
  // http://localhost:9091/contents/mp4_create.do?chno=3&contentsno=5
  /**
   * MP4 등록 폼
   * @return
   */
  @RequestMapping(value="/contents/mp4_create.do", 
                              method=RequestMethod.GET )
  public ModelAndView mp4_create(int chno, int contentsno) {
    ModelAndView mav = new ModelAndView();

    ChVO chVO = this.chProc.read(chno);
    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chVO.getChgrpno());

    mav.addObject("chVO", chVO);
    mav.addObject("chinesegrpVO", chinesegrpVO);

    mav.setViewName("/contents/mp4_create"); // /webapp/WEB-INF/views/contents/create.jsp
    // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
    // mav.addObject("content", content);
    return mav;
  }
  
  // http://localhost:9091/contents/mp4_create.do
  /**
   * MP4 등록
   * @param cateno 카테고리 번호
   * @param contentsno 글번호
   * @param youtube 지도 스크립트
   * @return
   */
  @RequestMapping(value="/contents/mp4_create.do", 
                              method=RequestMethod.POST )
  public ModelAndView mp4_create(HttpServletRequest request,
                                                    ContentsVO contentsVO) {
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String mp4 = ""; // mp4 파일명
    String user_dir = System.getProperty("user.dir");
    System.out.println("--> User dir: " + user_dir);
    // 파일 접근임으로 절대 경로 지정, static 지정
    // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage
    String upDir =  user_dir + "/src/main/resources/static/contents/mp4/"; // 절대 경로

    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    MultipartFile mf = contentsVO.getMp4MF();  // 파일 목록
    long fsize = mf.getSize();  // 파일 크기
    if (fsize > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      mp4 = Upload.saveFileSpring(mf, upDir); 
    }    
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("mp4", mp4);
    hashMap.put("contentsno", contentsVO.getContentsno());
    
    
    //this.contentsProc.mp4(hashMap); 
    
    mav.addObject("contentsno", contentsVO.getContentsno());
    
    mav.setViewName("redirect:/contents/read.do"); // /WEB-INF/views/contents/read.jsp
    
    return mav;
  }

  /**
   * 카테고리별 목록 http://localhost:9091/contents/list_by_chno.do?chno=1
   * 
   * @return
   */
  @RequestMapping(value = "/contents/list_by_chno.do", method = RequestMethod.GET)
  public ModelAndView list_by_chno(int chno) {
    ModelAndView mav = new ModelAndView(); // /webapp/contents/list_by_chno.jsp //
    
    ChVO chVO = this.chProc.read(chno);
    mav.addObject("chVO", chVO);

    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chVO.getChgrpno());
    mav.addObject("chinesegrpVO", chinesegrpVO);

    List<ContentsVO> list = this.contentsProc.list_by_chno(chno);
    mav.addObject("list", list);
    mav.setViewName("/contents/list_by_chno");
    return mav; // forward
  }

  /**
   * 목록 + 검색 지원
   * http://localhost:9091/contents/list_by_chno_search.do?chno=1&word=중국
   * 
   * @param chno
   * @param word
   * @return
   */
  @RequestMapping(value = "/contents/list_by_chno_search.do", method = RequestMethod.GET)
  public ModelAndView list_by_chno_search(@RequestParam(value = "chno", defaultValue = "1") int chno,
      @RequestParam(value = "word", defaultValue = "") String word) {

    ModelAndView mav = new ModelAndView();

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("chno", chno); // #{chno}
    map.put("word", word); // #{word}

    // 검색 목록
    List<ContentsVO> list = contentsProc.list_by_chno_search(map);
    mav.addObject("list", list);

    // 검색된 레코드 갯수
    int search_count = contentsProc.search_count(map);
    mav.addObject("search_count", search_count);

    ChVO chVO = chProc.read(chno);
    mav.addObject("chVO", chVO);

    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chVO.getChgrpno());
    mav.addObject("chinesegrpVO", chinesegrpVO);

    mav.setViewName("/contents/list_by_chno_search");

    return mav;
  }

  /**
   * 목록 + 검색 + 페이징 지원
   * http://localhost:9091/contents/list_by_chno_search_paging.do?chno=1&word=&now_page=1
   * 
   * @param cateno
   * @param word
   * @param now_page
   * @return
   */
  @RequestMapping(value = "/contents/list_by_chno_search_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_chno_search_paging(@RequestParam(value = "chno", defaultValue = "1") int chno,
      @RequestParam(value = "word", defaultValue = "") String word,
      @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
    System.out.println("--> now_page: " + now_page);

    ModelAndView mav = new ModelAndView();

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("chno", chno); // #{cateno}
    map.put("word", word); // #{word}
    map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용

    // 검색 목록
    List<ContentsVO> list = contentsProc.list_by_chno_search_paging(map);
    mav.addObject("list", list);

    // 검색된 레코드 갯수
    int search_count = contentsProc.search_count(map);
    mav.addObject("search_count", search_count);

    ChVO chVO = chProc.read(chno);
    mav.addObject("chVO", chVO);

    ChinesegrpVO chinesegrpVO = chinesegrpProc.read(chVO.getChgrpno());
    mav.addObject("chinesegrpVO", chinesegrpVO);

    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * 
     * @param list_file 목록 파일명
     * 
     * @param chno 카테고리번호
     * 
     * @param search_count 검색(전체) 레코드수
     * 
     * @param now_page 현재 페이지
     * 
     * @param word 검색어
     * 
     * @return 페이징 생성 문자열
     */
    String paging = contentsProc.pagingBox("list_by_chno_search_paging.do", chno, search_count, now_page, word);
    mav.addObject("paging", paging);

    mav.addObject("now_page", now_page);

    // /contents/list_by_chno_table_img1_search_paging.jsp
    mav.setViewName("/contents/list_by_chno_search_paging");

    return mav;
  }
  
  /**
   * Grid 형태의 화면 구성 http://localhost:9091/contents/list_by_chno_grid.do
   * 
   * @return
   */
  @RequestMapping(value = "/contents/list_by_chno_grid.do", method = RequestMethod.GET)
  public ModelAndView list_by_chno_grid(int chno) {
    ModelAndView mav = new ModelAndView();
    
    ChVO chVO = this.chProc.read(chno);
    mav.addObject("chVO", chVO);
    
    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chVO.getChgrpno());
    mav.addObject("chinesegrpVO", chinesegrpVO);
    
    List<ContentsVO> list = this.contentsProc.list_by_chno(chno);
    mav.addObject("list", list);

    // 테이블 이미지 기반, /webapp/contents/list_by_chno_grid.jsp
    mav.setViewName("/contents/list_by_chno_grid");

    return mav; // forward
  }
  
  /**
   * 그리드+ 검색 + 페이징 지원
   * http://localhost:9091/contents/list_by_chno_grid_search_paging.do?chno=1&word=&now_page=1
   * 
   * @param cateno
   * @param word
   * @param now_page
   * @return
   */
  @RequestMapping(value = "/contents/list_by_chno_grid_search_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_chno_grid_search_paging(@RequestParam(value = "chno", defaultValue = "1") int chno,
      @RequestParam(value = "word", defaultValue = "") String word,
      @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
    System.out.println("--> now_page: " + now_page);

    ModelAndView mav = new ModelAndView();

    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("chno", chno); // #{cateno}
    map.put("word", word); // #{word}
    map.put("now_page", now_page); // 페이지에 출력할 레코드의 범위를 산출하기위해 사용

    // 검색 목록
    List<ContentsVO> list = contentsProc.list_by_chno_grid_search_paging(map);
    mav.addObject("list", list);

    // 검색된 레코드 갯수
    int search_count = contentsProc.search_count(map);
    mav.addObject("search_count", search_count);

    ChVO chVO = chProc.read(chno);
    mav.addObject("chVO", chVO);

    ChinesegrpVO chinesegrpVO = chinesegrpProc.read(chVO.getChgrpno());
    mav.addObject("chinesegrpVO", chinesegrpVO);

    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * 
     * @param list_file 목록 파일명
     * 
     * @param chno 카테고리번호
     * 
     * @param search_count 검색(전체) 레코드수
     * 
     * @param now_page 현재 페이지
     * 
     * @param word 검색어
     * 
     * @return 페이징 생성 문자열
     */
    String paging = contentsProc.pagingBox("list_by_chno_grid_search_paging.do", chno, search_count, now_page, word);
    mav.addObject("paging", paging);

    mav.addObject("now_page", now_page);

    // /contents/list_by_chno_table_img1_search_paging.jsp
    mav.setViewName("/contents/list_by_chno_grid_search_paging");

    return mav;
  }
  
  
  // http://localhost:9091/contents/read.do
  /**
   * 조회
   * @return
   */
  @RequestMapping(value="/contents/read.do", method=RequestMethod.GET )
  public ModelAndView read(int contentsno,
      @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
    ModelAndView mav = new ModelAndView();

    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);

    ChVO chVO = this.chProc.read(contentsVO.getChno());
    mav.addObject("chVO", chVO); 

    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chVO.getChgrpno());
    mav.addObject("chinesegrpVO", chinesegrpVO); 
    mav.addObject("now_page", now_page);
    mav.setViewName("/contents/read"); // /WEB-INF/views/contents/read.jsp
        
    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9091/contents/update_text.do?contentsno=1
   * 
   * @return
   */
  @RequestMapping(value = "/contents/update_text.do", method = RequestMethod.GET)
  public ModelAndView update_text(int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    ContentsVO contentsVO = this.contentsProc.read_update_text(contentsno);
    ChVO chVO = this.chProc.read(contentsVO.getChno());
    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chVO.getChgrpno());
    
    mav.addObject("contentsVO", contentsVO);
    mav.addObject("chVO", chVO);
    mav.addObject("chinesegrpVO", chinesegrpVO);
    
    mav.setViewName("/contents/update_text"); // /WEB-INF/views/contents/update_text.jsp
    // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
    // mav.addObject("content", content);

    return mav; // forward
  }

  /**
   * 수정 처리
   * http://localhost:9091/contents/update_text.do?contentsno=1
   * 
   * @return
   */
  @RequestMapping(value = "/contents/update_text.do", method = RequestMethod.POST)
  public ModelAndView update_text(ContentsVO contentsVO,
                            @RequestParam(value = "now_page", defaultValue = "1") int now_page) {
    ModelAndView mav = new ModelAndView();
  
    int cnt = this.contentsProc.update_text(contentsVO); // 수정 처리
    
    mav.addObject("contentsno", contentsVO.getContentsno());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/contents/read.do"); 
    System.out.println("update_text cont used");
    return mav; // forward
  }
  
  /**
   * 파일 수정 폼
   * http://localhost:9091/contents/update_file.do?contentsno=35
   * 
   * @return
   */
  @RequestMapping(value = "/contents/update_file.do", method = RequestMethod.GET)
  public ModelAndView update_file(int contentsno) {
    ModelAndView mav = new ModelAndView();
    
    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    ChVO chVO = this.chProc.read(contentsVO.getChno());
    ChinesegrpVO chinesegrpVO = this.chinesegrpProc.read(chVO.getChgrpno());
    mav.addObject("contentsVO", contentsVO);
    mav.addObject("chVO", chVO);
    mav.addObject("chinesegrpVO", chinesegrpVO);
    
    mav.setViewName("/contents/update_file"); // /WEB-INF/views/contents/update_file.jsp

    return mav; // forward
  }
  
  /**
   * 파일 수정 처리 http://localhost:9091/contents/update_file.do
   * 
   * @return
   */
  @RequestMapping(value = "/contents/update_file.do", method = RequestMethod.POST)
  public ModelAndView update_file(HttpServletRequest request, 
                                                    ContentsVO contentsVO, 
                                                    //밑 코드는 수정,삭제등의처리시 now_page가 제대로 전달되도록 하는코드 반드시 해야함
                                                    @RequestParam(value = "now_page", defaultValue = "1")     int now_page) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    ContentsVO vo = contentsProc.read(contentsVO.getContentsno());
//    System.out.println("contentsno: " + vo.getContentsno());
//    System.out.println("file1: " + vo.getFile1());
    
    String file1saved = vo.getFile1saved();
    String thumb1 = vo.getThumb1();
    long size1 = 0;
    boolean sw = false;
    
    // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage/
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, file1saved);  // Folder에서 1건의 파일 삭제
    sw = Tool.deleteFile(upDir, thumb1);     // Folder에서 1건의 파일 삭제
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";          // 원본 파일명 image

    // 완성된 경로 F:/ai8/ws_frame/resort_v1sbm3a/src/main/resources/static/contents/storage/
    // String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로
    
    // 전송 파일이 없어도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택">
    MultipartFile mf = contentsVO.getFile1MF();
    
    file1 = mf.getOriginalFilename(); // 원본 파일명
    size1 = mf.getSize();  // 파일 크기
    
    if (size1 > 0) { // 파일 크기 체크
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1saved = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1saved)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
        thumb1 = Tool.preview(upDir, file1saved, 250, 200); 
      }
      
    }    
    
    contentsVO.setFile1(file1);
    contentsVO.setFile1saved(file1saved);
    contentsVO.setThumb1(thumb1);
    contentsVO.setSize1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    // Call By Reference: 메모리 공유, Hashcode 전달
    int cnt = this.contentsProc.update_file(contentsVO);
    
    mav.addObject("contentsno", contentsVO.getContentsno());
    mav.addObject("now_page", now_page);
    
    mav.setViewName("redirect:/contents/read.do"); 

    return mav; // forward
  }   
  
  /**
   * 삭제 폼
   * @param contentsno
   * @return
   */
  @RequestMapping(value="/contents/delete.do", method=RequestMethod.GET )
  public ModelAndView delete(int contentsno) { 
    ModelAndView mav = new  ModelAndView();
    
    // 삭제할 정보를 조회하여 확인
    ContentsVO contentsVO = this.contentsProc.read(contentsno);
    mav.addObject("contentsVO", contentsVO);     
    
    ChVO chVO=this.chProc.read(contentsVO.getChno());
    mav.addObject("chVO", chVO);
    ChinesegrpVO chinesegrpVO=this.chinesegrpProc.read(chVO.getChgrpno());
    mav.addObject("chinesegrpVO", chinesegrpVO);
    
    mav.setViewName("/contents/delete");  // contents/delete.jsp
    
    return mav; 
  }
  
  /**
   * 삭제 처리 http://localhost:9091/contents/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/contents/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request, int contentsno,
      @RequestParam(value = "now_page", defaultValue = "1")  int now_page,
      int chno,
      String word) {
    ModelAndView mav = new ModelAndView();

    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    ContentsVO vo = contentsProc.read(contentsno);
//    System.out.println("contentsno: " + vo.getContentsno());
//    System.out.println("file1: " + vo.getFile1());
    
    String file1saved = vo.getFile1saved();
    String thumb1 = vo.getThumb1();
    long size1 = 0;
    boolean sw = false;
    
    
    String upDir =  System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/"; // 절대 경로

    sw = Tool.deleteFile(upDir, file1saved);  // Folder에서 1건의 파일 삭제
    sw = Tool.deleteFile(upDir, thumb1);     // Folder에서 1건의 파일 삭제
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    int cnt = this.contentsProc.delete(contentsno); // DBMS 삭제
    if (cnt == 1) {
      // -------------------------------------------------------------------------------------
      // 마지막 페이지의 레코드 삭제시의 페이지 번호 -1 처리
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("chno", chno);
      map.put("word", word);
      // 10번째 레코드를 삭제후
      // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
      // 페이지수를 4 -> 3으로 감소 시켜야함.
      if (contentsProc.search_count(map) % Contents.RECORD_PER_PAGE == 0) {
        now_page = now_page - 1;
        if (now_page < 1) {
          now_page = 1; // 시작 페이지
        }
      }
      // -------------------------------------------------------------------------------------
    }
    
    mav.addObject("now_page", now_page);
    mav.addObject("chno", chno);
    
    mav.setViewName("redirect:/contents/list_by_chno_search_paging.do"); 

    return mav; // forward
  }   
  
  /**
   * 다수의 chno를 전달하여 contents 레코드 삭제
   * @param contentsno
   * @return
   */
  @RequestMapping(value="/contents/delete_contents_by_all_chno.do", method=RequestMethod.POST)
  public ModelAndView delete_contents_by_all_chno(String chnos) { 
    ModelAndView mav = new  ModelAndView();
    
    String[] chnos_array = chnos.split(",");  
    List<Integer> chnos_list = new ArrayList<Integer>();
    
    for(int index=0; index < chnos_array.length; index++) {
      chnos_list.add(Integer.parseInt(chnos_array[index]));
    }
    
    Map<String, Object> chnos_map = new HashMap<String, Object>();
    chnos_map.put("chnos_list", chnos_list);
    
    this.contentsProc.delete_contents_by_all_chno(chnos_map);

    mav.setViewName("redirect:/chinesegrp/list.do"); 
    
    return mav; 
  }
  
   
}
