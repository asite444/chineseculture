<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>ChineseCulture</title>
 <!--http://localhost:9091/favfield/create.do 실행하기  -->
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
 
<DIV class='title_line'><A href="../favfieldgrp/list.do" class='title_link'>주제</A> > 등록</DIV>
 
 
 <DIV class='content_body' >
   <FORM name='frm' method='POST' action='./create.do' class="form-horizontal">
   <div class="form-group">
       <label class="control-label col-md-4">그룹번호</label>
       <div class="col-md-8">
         <input type='text' name='favgrpno' value='' required="required"  placeholder="그룹번호(1~7)"  
                     class="form-control" style='width: 50%;'>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-4">제목 이름</label>
       <div class="col-md-8">
         <input type='text' name='favtitle' value='' required="required"  placeholder="제목 이름"  
                    autofocus="autofocus" class="form-control" style='width: 50%;'>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-4">설명</label>
       <div class="col-md-8">
         <input type='text' name='favex' value='' required="required"  placeholder="설명"  
                     class="form-control" style='width: 50%;'>
       </div>
    </div>
    <div class="content_body_bottom" style="padding-right: 20%;">
      <button type="submit" onclick="/create_msg.do"  class="btn">등록</button>
      <button type="button" onclick="location.href='./list_all.do'" class="btn">목록</button>
    </div>
  </FORM> 
 </DIV>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>