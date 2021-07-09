<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<DIV class='container_main' style='width: 80%;'> 
  <%-- 화면 상단 메뉴 --%>
  <DIV class='top_img'>
    <DIV class='top_menu_label'>chinesesculture 0.1 중국의 다양한 문화</DIV>
    <NAV class='top_menu'>
      <span style='padding-left:0.5%;'></span>
      <A class='menu_link'  href='/' ><IMG src="/images/home.png" style='width: 18px;'> HOME</A><span class='top_menu_sep'> </span>
      <c:choose>
        <c:when test="${sessionScope.id == null}"> <%-- 로그인 안 한 경 우 --%>
          <A class='menu_link'  href='/member/login.do' >Login</A><span class='top_menu_sep'> </span>
        </c:when>
        <c:otherwise>
          ${sessionScope.id } <A class='menu_link'  href='/member/logout.do' >Logout</A><span class='top_menu_sep'> </span>
        </c:otherwise>
      </c:choose>     
      <A class='menu_link'  href='http://localhost:9091/chinesegrp/list.do' >중국문화</A><span class='top_menu_sep'> </span>
      <A class='menu_link'  href='http://localhost:9091/favfieldgrp/list.do' >list</A><span class='top_menu_sep'> </span>    
      <A class='menu_link'  href='http://localhost:9091/favfield/list_all.do' >list_all</A><span class='top_menu_sep'> </span> 
      <A class='menu_link'  href='http://localhost:9091/favfield/list_all_join.do' >list_all_join</A><span class='top_menu_sep'> </span> 
      <A class='aside_right'  href='http://localhost:9091/member/create.do'  >회원가입</A><span class='top_menu_sep'> </span> 
      
 
    </NAV>
    
  </DIV>
  
  <%-- 내용 --%> 
  <DIV class='content'>