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
 
<DIV class='title_line'><A href="../favfieldgrp/list.do" class='title_link'>주제</A> > ${favfieldgrpVO.favgrptitle }
</DIV>

<DIV class='content_body'>
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <label>즐겨찾기 그룹 번호</label>
      <input type='hidden' name='favgrpno' value='${param.favgrpno }'  required="required" style='width: 3%;' >
      ${param.favgrpno }
      <label>주제</label>
      <input type='text' name='favtitle' value='' required="required" style='width: 10%;'
                 autofocus="autofocus">
                 <label>내용</label>
      <input type='text' name='favex' value='' required="required" style='width: 40%;'
                 >
      <button type="submit" id='submit'>등록</button>
      <button type="button" onclick="cancel();">취소</button>
    </FORM>
  </DIV>
   
    
  <TABLE class='table table-striped'>
    <colgroup>
      <col style='width: 5%;'/>
    <col style='width: 5%;'/>
      <col style='width: 20%;'/>
      <col style='width: 40%;'/>
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
    </colgroup>
   
    <thead>  
    <TR>
    <TH class="th_bs">번호</TH>
     <TH class="th_bs">그룹<br>번호</TH>
      <TH class="th_bs">주제</TH>
      <TH class="th_bs">내용</TH>
      <TH class="th_bs">등록일</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
    <tbody>
    <c:forEach var="favfieldVO" items="${list}">
 <c:set var="favno" value="${favfieldVO.favno }" />
      <c:set var="favgrpno" value="${favfieldVO.favgrpno }" />
      <TR>
      <TD class="td_bs_left">${favfieldVO.favno }</TD>
      <TD class="td_bs_left">${favfieldVO.favgrpno }</TD>
         <TD class="td_bs_left">${favfieldVO.favtitle }</TD>
         <TD class="td_bs_left"> <A href="../contents/list_by_favno.do?favno=${favfieldVO.favno } ">${favfieldVO.favex }</A></TD>
        <TD class="td_bs">${favfieldVO.rdate.substring(0, 10) }</TD>
        
        
        <TD class="td_bs">
          <A href="./read_update.do?favno=${favno }&favgrpno=${favgrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          <A href="./read_delete.do?favno=${favno }&favgrpno=${favgrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A> 
        </TD>   
      </TR>   
    </c:forEach> 
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>