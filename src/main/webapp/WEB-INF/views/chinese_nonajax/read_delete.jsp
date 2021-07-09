<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>ChineseCulture</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>주제 > ${chVO.chtitle } 조회(삭제)</DIV>

<DIV class='content_body'>
  <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
  <div class="msg_warning">카테고리 그룹을 삭제하면 복구 할 수 없습니다.</div>
    <FORM name='frm_delete' id='frm_delete' method='POST' action='./delete.do'>
      <input type='hidden' name='chno' id='chno' value='${chVO.chno }'>
      <input type='hidden' name='chgrpno' id='chgrpno' value='${param.chgrpno }'>
          <label>주제 </label>: ${chVO.chtitle }  
          <label>내용 </label>: ${chVO.chex }  
      <button type="submit" id='submit'>삭제</button>
      <button type="button" onclick="location.href='./list_by_chgrpno.do?chgrpno=${chVO.chgrpno }'">취소</button>
    </FORM>
  </DIV>
   
<TABLE class='table table-striped'>
    <colgroup>
    <col style='width: 10%;'/>
      <col style='width: 20%;'/>
      <col style='width: 40%;'/>
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
    </colgroup>
   
    <thead>  
    <TR>
     <TH class="th_bs">번호</TH>
      <TH class="th_bs">주제</TH>
      <TH class="th_bs">내용</TH>
      <TH class="th_bs">등록일</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
    <tbody>
    <c:forEach var="chVO" items="${list}">
     <c:set var="chno" value="${chVO.chno }" />
      <c:set var="chgrpno" value="${chVO.chgrpno }" />
      <TR>
      <TD class="td_bs_left">${chVO.chgrpno }</TD>
         <TD class="td_bs_left">${chVO.chtitle }</TD>
         <TD class="td_bs_left">${chVO.chex }</TD>
        <TD class="td_bs">${chVO.rdate.substring(0, 10) }</TD>
        
        
        <TD class="td_bs">
          <A href="./read_update.do?chno=${chno }&chgrpno=${chgrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          <A href="./read_delete.do?chno=${chno }&chgrpno=${chgrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A> 
        </TD>   
      </TR>   
    </c:forEach> 
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
 