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
$(function() {
  $('#btn_update_cancel').on('click', cancel);
  $('#btn_delete_cancle').on('click',cancel);
});

function cancel() {
  $('#panel_create').css("display","");  
  $('#panel_update').css("display","none");
  $('#panel_delete').css("display","none");  
}

// 수정폼
function read_update_ajax(chno) {
  $('#panel_create').css("display","none"); // hide, 태그를 숨김 
  $('#panel_update').css("display",""); // show, 숨겨진 태그 출력 
  
  //console.log( 'chno=' + chno);
  var params = "";
  // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
  params = 'chno=' + chno; // 공백이 값으로 있으면 안됨.

  $.ajax(
    {
      url: '/chinese/read_ajax.do',
      type: 'get',  // get, post
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
      data: params,      // 데이터
      success: function(rdata) { // 응답이 온경우, Spring에서 하나의 객체를 전달한 경우 통문자열
        // {"categrpno":1,"visible":"Y","seqno":1,"rdate":"2021-04-08 17:01:28","name":"문화"}
        var chno = rdata.chno;
        var  chgrpno=rdata.chgrpno;
        var chtitle=rdata.chtitle;
        var chex=rdata.chex;
        var rdate = rdata.rdate;

        var frm_update = $('#frm_update');
        $('#chno', frm_update).val(chno);
        $('#chgrpno', frm_update).val(chgrpno);
        $('#chtitle', frm_update).val(chtitle);
        $('#chex', frm_update).val(chex);
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
//삭제폼
function read_delete_ajax(chno) {
  $('#panel_update').css("display","none"); 
  $('#panel_create').css("display","none");  //hide,태그를 숨김
  $('#panel_delete').css("display",""); //show,숨겨진 태크 출력
  // console.log('-> categrpno:' + categrpno);
  var params = "";
  // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
  params = 'chno=' + chno; // 공백이 값으로 있으면 안됨.
  $.ajax(
    {
      url: '/chinese/read_ajax.do',
      type: 'get',  // get, post
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
      data: params,      // 데이터
      success: function(rdata) { // 응답이 온경우, Spring에서 하나의 객체를 전달한 경우 통문자열

        var chno = rdata.chno;
        var  chgrpno=rdata.chgrpno;
        var chtitle=rdata.chtitle;
        var chex=rdata.chex;
        var rdate = rdata.rdate;
        var count_by_chno=parseInt(rdata.count_by_chno);
        console.log('count_by_chno:'+count_by_chno);
        var frm_delete = $('#frm_delete');
        $('#chno', frm_delete).val(chno);
        $('#chgrpno', frm_delete).val(chgrpno);
        $('#frm_delete_chtitle').html(chtitle);
        $('#frm_delete_chex').html(chex);


        if (count_by_chno > 0 ) { // 자식 레코드가 있다면
          $('#frm_delete_count_by_chno').show(); // 자식레코드 보여줌
          $('#frm_delete_count_by_chno_panel').html('관련자료 : ' + count_by_chno +'건'); 
          $('#a_list_by_chno').attr('href', '../contents/list_by_chno_search_paging.do?chno=' + chno);
        } else { // 자식 레코드가 없는 경우
          $('#frm_delete_count_by_chno').hide();  // 자식레코드 숨김
        } //else end
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        console.log(error);
      }
    }
  );  //  $.ajax END
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


<!-- 신규 등록 -->
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_create' id='frm_create' method='POST' action='./create.do'>
      <label> 그룹 번호</label>
      <input type='hidden' name='chgrpno' value='${param.chgrpno }'  required="required" style='width: 3%;' >
      ${param.chgrpno }
      <label>주제</label>
      <input type='text' name='chtitle'  id='chtitle'  value='' required="required" style='width: 10%;'
                 autofocus="autofocus">
                 <label>내용</label>
      <input type='text' name='chex' id='chex'  value='' required="required" style='width: 40%;'
                 >
      <button type="submit" id='submit'>등록</button>
      <button type="button" onclick="cancel();">취소</button>
    </FORM>
  </DIV>
  <%--수정--%> 
<DIV id='panel_update' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center ;display:none;'>
    <FORM name='frm_update' id='frm_update' method='POST' action='./update.do'>
      <input type='hidden' name='chno' id='chno' value='${chVO.chno }'>
          <label>그룹 번호</label>
      <input type='number' name='chgrpno'   id='chgrpno' required="required" style='width: 3%;'
           min='1'  autofocus="autofocus">
      <label>주제</label>
      <input type='text' name='chtitle' id='chtitle' required="required" style='width: 10%;'
                 >
                 <label>내용</label>
      <input type='text' name='chex' id='chex'  required="required" style='width: 40%;'
                 >
      <button type="submit" id='submit'>저장</button>
      <button type="button" onclick="cancel();">취소</button>
    </FORM>
  </DIV>
  
    <DIV id='panel_delete' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;display:none;'>
  <div class="msg_warning">카테고리를 삭제하면 복구 할 수 없습니다.</div>
    <FORM name='frm_delete' id='frm_delete' method='POST' action='./delete.do'>
      <input type='hidden' name='chno' id='chno' value='${chVO.chno }'>
      <input type='hidden' name='chgrpno' id='chgrpno' value='${param.chgrpno }'>
          <label>주제 </label>: <span id='frm_delete_chtitle'></span> 
          <label>내용 </label>:<span id='frm_delete_chex'></span> 
          
                 <%-- 자식 레코드 갯수 출력 --%>
      <div id='frm_delete_count_by_chno' style='margin:10px auto;
               color:#FF0000; font-weight: bold; display: none;'>
        
        <%--건수 누적되는 문제 SPAN id 값 주기  --%>       
       <span id='frm_delete_count_by_chno_panel'></span>
       
       <%-- id를 부여하여 chgrpno를 전달 --%>
       『<A id='a_list_by_chno' href="../ch/list_by_chno.do?chno=${chgrpno }">관련 자료 삭제하기</A>』
      </div>
      <button type="submit" id='submit'>삭제</button>
      <button type="button" id='btn_delete_cancle'>취소</button>
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
          <A href="javascript:read_update_ajax(${chno })" title="수정"><span class="glyphicon glyphicon-pencil"></span></A>
          <A href="javascript:read_delete_ajax(${chno })"  title="삭제"><span class="glyphicon glyphicon-trash"></span></A> 
        </TD>   
      </TR>   
    </c:forEach> 
    </tbody>
   
  </TABLE>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>


