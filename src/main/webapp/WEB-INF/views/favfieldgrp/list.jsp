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

<DIV class='title_line'>주제</DIV>

<DIV class='content_body'>
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
     
      <label>주제</label>
      <input type='text' name='favgrptitle' value='' required="required" style='width: 10%;'
                 autofocus="autofocus">
      <button type="submit" id='submit'>등록</button>
      <button type="button" onclick="cancel();">취소</button>
    </FORM>
  </DIV>
   
    
  <TABLE class='table table-striped'>
    <colgroup>
     <col style='width: 10%;'/>
      <col style='width: 50%;'/>
      <col style='width: 20%;'/>
      <col style='width: 20%;'/>
    </colgroup>
   
    <thead>  
    <TR>
    <TH class="th_bs">즐겨찾기 그룹 번호</TH>
      <TH class="th_bs">주제</TH>
      <TH class="th_bs">등록일</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
    <tbody>
    <c:forEach var="favfieldgrpVO" items="${list}">
      <c:set var="favgrpno" value="${favfieldgrpVO.favgrpno }" />
      <TR>
      <TD class="td_bs_left">${favfieldgrpVO.favgrpno }</TD>
         <TD class="td_bs_left"> <A href="../favfield/list_by_favgrpno.do?favgrpno=${favgrpno }">${favfieldgrpVO.favgrptitle }</A></TD>
        <TD class="td_bs">${favfieldgrpVO.rdate.substring(0, 10) }</TD>
        
        
        <TD class="td_bs">
          <A href="./read_update.do?favgrpno=${favgrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          <A href="./read_delete.do?favgrpno=${favgrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A> 
        </TD>   
      </TR>   
    </c:forEach> 
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
   