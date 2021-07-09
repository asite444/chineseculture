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
$(function (){
  $('#btn_update_cancle').on('click',cancel);
  $('#btn_delete_cancle').on('click',cancel);

} );
function cancel() {
  $('#panel_create').css("display","");  
  $('#panel_update').css("display","none"); 
  $('#panel_delete').css("display","none"); 
  }
 function read_update_ajax(chgrpno) {
   $('#panel_update').css("display",""); //show,숨겨진 태크 출력
   $('#panel_create').css("display","none");  //hide,태그를 숨김
   $('#panel_delete').css("display","none"); //show,숨겨진 태크 출력
   // console.log('-> categrpno:' + categrpno);
   var params = "";
   // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
   params = 'chgrpno=' + chgrpno; // 공백이 값으로 있으면 안됨.
   $.ajax(
     {
       url: '/chinesegrp/read_ajax.do',
       type: 'get',  // get, post
       cache: false, // 응답 결과 임시 저장 취소
       async: true,  // true: 비동기 통신
       dataType: 'json', // 응답 형식: json, html, xml...
       data: params,      // 데이터
       success: function(rdata) { // 응답이 온경우, Spring에서 하나의 객체를 전달한 경우 통문자열

         var  chgrpno=rdata.chgrpno;
         var topic = rdata.topic;
         var seqno =rdata.seqno;
         var chfield =rdata.chfield;
         var rdate =rdata.rdate;
       
         var frm_update = $('#frm_update');
         $('#chgrpno', frm_update).val(chgrpno);
         $('#topic', frm_update).val(topic);
         $('#chfield', frm_update).val(chfield);
         $('#seqno', frm_update).val(seqno);
         $('#rdate', frm_update).val(rdate);

       },
       // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
       error: function(request, status, error) { // callback 함수
         console.log(error);
       }
     }
   );  //  $.ajax END

   // $('#span_animation').css('text-align', 'center');
   // $('#span_animation').html("<img src='/contents/images/ani04.gif' style='width: 8%;'>");
   // $('#span_animation').show(); // 숨겨진 태그의 출력
 } 

 function read_delete_ajax(chgrpno) {
   $('#panel_update').css("display","none"); 
   $('#panel_create').css("display","none");  //hide,태그를 숨김
   $('#panel_delete').css("display",""); //show,숨겨진 태크 출력
   // console.log('-> categrpno:' + categrpno);
   var params = "";
   // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
   params = 'chgrpno=' + chgrpno; // 공백이 값으로 있으면 안됨.
   $.ajax(
     {
       url: '/chinesegrp/read_ajax.do',
       type: 'get',  // get, post
       cache: false, // 응답 결과 임시 저장 취소
       async: true,  // true: 비동기 통신
       dataType: 'json', // 응답 형식: json, html, xml...
       data: params,      // 데이터
       success: function(rdata) { // 응답이 온경우, Spring에서 하나의 객체를 전달한 경우 통문자열

         var  chgrpno=rdata.chgrpno;
         var topic = rdata.topic;
         var seqno =rdata.seqno;
         var chfield =rdata.chfield;
         var rdate =rdata.rdate;
         var count_by_chgrpno=parseInt(rdata.count_by_chgrpno);
         console.log('count_by_chgrpno:'+count_by_chgrpno);
         var frm_delete = $('#frm_delete');
         $('#chgrpno', frm_delete).val(chgrpno);
         $('#frm_delete_topic').html(topic);
         $('#frm_delete_chfield').html(chfield);
         $('#frm_delete_seqno').html(seqno);

         if (count_by_chgrpno > 0 ) { // 자식 레코드가 있다면
           $('#frm_delete_count_by_chgrpno').show(); // 자식레코드 보여줌
           $('#frm_delete_count_by_chgrpno_panel').html('관련자료 : ' + count_by_chgrpno +'건'); 
           $('#a_list_by_chgrpno').attr('href', '../ch/list_by_chgrpno.do?chgrpno=' + chgrpno);
         } else { // 자식 레코드가 없는 경우
           $('#frm_delete_count_by_chgrpno').hide();  // 자식레코드 숨김
         } //else end
       },
       // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
       error: function(request, status, error) { // callback 함수
         console.log(error);
       }
     }
   );  //  $.ajax END

   // $('#span_animation').css('text-align', 'center');
   // $('#span_animation').html("<img src='/contents/images/ani04.gif' style='width: 8%;'>");
   // $('#span_animation').show(); // 숨겨진 태그의 출력
 } 
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>주제</DIV>

<DIV class='content_body'>
 <!-- 신규 등록 -->
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <label>주제</label>
      <input type='text' name='topic'  id='topic'  value='' required="required" style='width: 10%;'
                 autofocus="autofocus">
  
    <label>세부 주제</label>
      <input type='text' name='chfield'  id='chfield'  value='' required="required" style='width: 25%;' >
                 
      <label>순서</label>
      <input type='number' name='seqno' id='seqno'  value='1' required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
      <button type="submit" id='submit'>등록</button>
      <button type="button" id='btn_update_cancle'>취소</button>
    </FORM>
  </DIV>
   
    <!-- 수정 -->
  <DIV id='panel_update' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center; display:none;'>
    <FORM name='frm_update' id='frm_update' method='POST' action='./update.do'>
     <input type='hidden' name='chgrpno' id='chgrpno' value=''>
      <label>주제</label>
      <input type='text' name='topic'  id='topic'  value='' required="required" style='width: 10%;'
                 autofocus="autofocus">
  
    <label>세부 주제</label>
      <input type='text' name='chfield'  id='chfield'  value='' required="required" style='width: 25%;' >
                 
      <label>순서</label>
      <input type='number' name='seqno' id='seqno'  value='1' required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
      <button type="submit" id='submit'>저장</button>
      <button type="button" onclick="cancel();">취소</button>
    </FORM>
  </DIV>
  
        <%-- 삭제 --%>
  <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center; display:none;'>
    <div class="msg_warning">카테고리 그룹을 삭제하면 복구 할 수 없습니다.</div>
    <FORM name='frm_delete' id='frm_delete' method='POST' action='./delete.do'>
      <input type='hidden' name='chgrpno' id='chgrpno' value='${chinesegrpVO.chgrpno }'>
        
      <label>주제 이름</label>:<span id='frm_delete_topic'></span>
      <label>세부 주제 이름</label>: <span id='frm_delete_chfield'></span> 
      <label>순서</label>: <span id='frm_delete_seqno'></span> 
      
           <%-- 자식 레코드 갯수 출력 --%>
      <div id='frm_delete_count_by_chgrpno' style='margin:10px auto;
               color:#FF0000; font-weight: bold; display: none;'>
        
        <%--건수 누적되는 문제 SPAN id 값 주기  --%>       
       <span id='frm_delete_count_by_chgrpno_panel'></span>
       
       <%-- id를 부여하여 chgrpno를 전달 --%>
       『<A id='a_list_by_chgrpno' href="../ch/list_by_chgrpno.do?chgrpno=${chgrpno }">관련 자료 삭제하기</A>』
      </div>
       
      <button type="submit" id='submit'>삭제</button>
      <button type="button" id='btn_delete_cancle'>취소</button>
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
         <TD class="td_bs_left"><a href="http://localhost:9091/chinese/list_by_chgrpno.do?chgrpno=${chgrpno } ">${chinesegrpVO.chfield }</a></TD>
        <TD class="td_bs">${chinesegrpVO.rdate.substring(0, 10) }</TD>
        
        
        <TD class="td_bs">
         <%--  <A href="./read_update.do?chgrpno=${chgrpno }" title="수정"><span class="glyphicon glyphicon-pencil"></span></A> --%>
          <A href="javascript:read_update_ajax(${chgrpno })" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          
          <%-- <A href="./read_delete.do?chgrpno=${chgrpno }" title="삭제"><span class="glyphicon glyphicon-trash"></span></A> --%>
          <A href="javascript:read_delete_ajax(${chgrpno })" title="삭제"><span class="glyphicon glyphicon-trash"></span></A>
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
   