<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="${contentsVO.title }" />
<c:set var="content" value="${contentsVO.content }" />
<c:set var="word" value="${contentsVO.word }" />
<c:set var="tklevel" value="${contentsVO.tklevel }" />
<c:set var="passwd" value="${contentsVO.passwd }" />
<c:set var="lecturetime" value="${contentsVO.lecturetime }" />
<c:set var="contentsno" value="${contentsVO.contentsno }" />
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>ChineseCulture</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
  $(function(){
 
  });
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>
  <A href="../chgrp/list.do" class='title_link'>주제</A> > 
  ${chinesegrpVO.chfield } > ${chVO.chtitle } >
  ${title } 수정
</DIV>

<DIV class='content_body'>
 <DIV style="text-align: left;">
        <ASIDE class="aside_right">
    <A href="./create.do?chno=${chVO.chno }&chgrpno=${chVO.chgrpno }">등록</A>
        <span class='menu_divide'>│</span> <A
          href="javascript:location.reload();">새로고침</A> <span
          class='menu_divide'>│</span> <A
          href="./list_by_chno_search_paging.do?chno=${chVO.chno }">리스트형</A>
          <span class='menu_divide' >│</span>
    <A href="./update_text.do?contentsno=${contentsno}&now_page=${param.now_page }">수정</A>
    <span class='menu_divide' >│</span>
    <A href="./update_file.do?contentsno=${contentsno}&now_page=${param.now_page }">파일 수정</A>  
  </ASIDE> 
    </DIV>
    <DIV style="text-align: right; clear: both;">
       <DIV style="text-align: right; clear: both;">
      <form name='frm' id='frm' method='get'
        action='./list_by_chno_search_paging.do'>
        <input type='hidden' name='chno' value='${chVO.chno }'>
        <c:choose>
          <c:when test="${param.word != '' }">
            <%-- 검색하는 경우 --%>
            <input type='text' name='word' id='word'
              value='${param.word }' style='width: 20%;'>
          </c:when>
          <c:otherwise>
            <%-- 검색하지 않는 경우 --%>
            <input type='text' name='word' id='word' value=''
              style='width: 20%;'>
          </c:otherwise>
        </c:choose>
        <button type='submit'>검색</button>
        <c:if test="${param.word.length() > 0 }">
          <button type='button'
            onclick="location.href='./list_by_chno_search_paging.do?chno=${chVO.chno}&word='">검색
            취소</button>
        </c:if>
      </form>
    </DIV>
    </DIV>
  <DIV class='menu_line'></DIV>
  
  <FORM name='frm' method='POST' action='./update_text.do' class="form-horizontal"
             >
     <input type='hidden' name='now_page' value='${param.now_page }'>
     <input type='hidden' name='contentsno' value='${contentsno }'>
    <%-- <input type="hidden" name="chgrpno" value="${chinesegrpVO.chgrpno }"> --%> 
    <input type="hidden" name="chno" value="${chVO.chno }">
    <input type="hidden" name="adminno" value="1"> <%-- 관리자 개발후 변경 필요 --%>

    <div class="form-group">
       <label class="control-label col-md-2">제목</label>
       <div class="col-md-10">
         <input type='text' name='title' value='${title }' required="required" 
                   autofocus="autofocus" class="form-control" style='width: 100%;'>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">설명</label>
       <div class="col-md-10">
         <textarea name='content'  required="required" class="form-control" rows="12" style='width: 100%;'>${content }</textarea>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">검색어</label>
       <div class="col-md-10">
         <input type='text' name='word' value=' ${word }'  required="required" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>  
   <c:choose>
    <c:when test="${chinesegrpVO.chgrpno == 3 }">
    <div class="form-group">
       <label class="control-label col-md-2">난이도</label>
       <div class="col-md-10">
         <input type='text' name='tklevel' value='${tklevel }' required="required" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>   
     
        <div class="form-group">
       <label class="control-label col-md-2">강의 시간</label>
       <div class="col-md-10">
         <input type='text' name='lecturetime' value='${lecturetime } '  required="required" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>   
    </c:when>
     </c:choose>
    <div class="form-group">
       <label class="control-label col-md-2">패스워드</label>
       <div class="col-md-10">
         <input type='password' name='passwd' value='${passwd }' required="required" 
                    class="form-control" style='width: 50%;'>
       </div>
    </div>   
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">저장</button>
      <button type="button" onclick="location.href='./list_by_chno_search_paging.do?chno=${chVO.chno }&now_page=${param.now_page } '" class="btn btn-primary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>