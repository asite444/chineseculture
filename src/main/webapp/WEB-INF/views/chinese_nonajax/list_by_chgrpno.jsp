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
<%-- categrpno가 같은 모든 레코드 삭제 --%>
function delete_by_chgrpno(chgrpno) {
  var f = $('#frm_delete_by_chgrpno');
  f.attr('action', './delete_by_chgrpno.do');
  $('#chgrpno', f).val(chgrpno)
  f.submit();
} 

// 다수의 chno를 전달하여 contents 레코드 삭제
function delete_contents_by_all_chno() {
  
  
  var f = $('#frm_delete_by_chgrpno');
  f.attr('action', '../contents/delete_contents_by_all_chno.do');
  var chnos = $('#chnos', f).val();
  
  chnos = chnos.substr(0, chnos.length-1);
  console.log('-> chnos: ' + chnos);
  console.log('-> action: ' + f.attr('action'));

  $('#chnos').val(chnos);

  f.submit();
}
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'><A href="../chinesegrp/list.do" class='title_link'>주제</A> > ${chinesegrpVO.chfield }
</DIV>
<ASIDE class="aside_right">
    <form name='frm_delete_by_chgrpno' id='frm_delete_by_chgrpno' action='' method='post'>
      <input type='hidden' name='chgrpno' id='chgrpno' value=''>
      <input type='hidden' name='chnos' id='chnos' value=''>
      <A href="javascript: delete_contents_by_all_chno()">모든 카테고리의 관련 자료 삭제</A>
      <span class='menu_divide' >│</span>
      <A href="javascript: delete_by_chgrpno(${param.chgrpno})">모든 카테고리 삭제</A>
    </form>
 </ASIDE> 
<DIV class='content_body'>



  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <label>즐겨찾기 그룹 번호</label>
      <input type='hidden' name='chgrpno' value='${param.chgrpno }'  required="required" style='width: 3%;' >
      ${param.chgrpno }
      <label>주제</label>
      <input type='text' name='chtitle' value='' required="required" style='width: 10%;'
                 autofocus="autofocus">
                 <label>내용</label>
      <input type='text' name='chex' value='' required="required" style='width: 40%;'
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
    <c:forEach var="chVO" items="${list}">
 <c:set var="chno" value="${chVO.chno }" />
      <c:set var="chgrpno" value="${chVO.chgrpno }" />
      
      <script type="text/javascript">
        var chnos = $('#chnos').val();
        chnos = chnos + "${chno},";  // 1,2,3,5
        $('#chnos').val(chnos); // EL -> jquery -> HTML
      </script>
      <TR>
      <TD class="td_bs_left">${chVO.chno }</TD>
      <TD class="td_bs_left">${chVO.chgrpno }</TD>
         <TD class="td_bs_left">${chVO.chtitle }</TD>
         <TD class="td_bs_left"> <A href="../contents/list_by_chno_search_paging.do?chno=${chVO.chno }&now_page=1 ">${chVO.chex }</A></TD>
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


