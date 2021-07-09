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
 
<DIV class='title_line'>카테고리 그룹 >${chinesegrpVO.chfield } 삭제</DIV>

<DIV class='content_body'>
  <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <div class="msg_warning">카테고리 그룹을 삭제하면 복구 할 수 없습니다.</div>
    <FORM name='frm_delete' id='frm_delete' method='POST' action='./delete.do'>
      <input type='hidden' name='chgrpno' id='chgrpno' value='${chinesegrpVO.chgrpno }'>
        
      <label>주제 이름</label>: ${chinesegrpVO.topic }  
      <label>세부 주제 이름</label>: ${chinesegrpVO.chfield }  
      <label>순서</label>: ${chinesegrpVO.seqno }   
      
       
      <button type="submit" id='submit'>삭제</button>
      <button type="button" onclick="location.href='./list.do'">취소</button>
    </FORM>
  </DIV>
   
  <TABLE class='table table-striped'>
    <colgroup>
      <col style='width: 10%;'/>
      <col style='width: 10%;'/>
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>    
      <col style='width: 20%;'/>
    </colgroup>
   
    <thead>  
    <TR>
       <TH class="th_bs">순서</TH>
      <TH class="th_bs">주제</TH>
       <TH class="th_bs">세부 주제</TH>
      <TH class="th_bs">등록일</TH>
      <TH class="th_bs">기타</TH>
    </TR>
    </thead>
    
   <tbody>
    <c:forEach var="chinesegrpVO" items="${list}">
      <c:set var="chgrpno" value="${chinesegrpVO.chgrpno }" />
      <TR>
        <TD class="td_bs">${chinesegrpVO.seqno }</TD>
        <TD class="td_bs_left">${chinesegrpVO.topic }</TD>
         <TD class="td_bs_left">${chinesegrpVO.chfield }</TD>
        <TD class="td_bs">${chinesegrpVO.rdate.substring(0, 10) }</TD>
        
        <TD class="td_bs">
          <A href="./read_update.do?chgrpno=${chgrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          <A href="./read_delete.do?chgrpno=${chgrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
          <A href="./update_seqno_up.do?chgrpno=${chgrpno }" title="우선순위 상향"><span class="glyphicon glyphicon-arrow-up"></span></A>
          <A href="./update_seqno_down.do?chgrpno=${chgrpno }" title="우선순위 하향"><span class="glyphicon glyphicon-arrow-down"></span></A>         
         </TD>   
  
      </TR>   
    </c:forEach> 
    </tbody>
   
  </TABLE>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>
 