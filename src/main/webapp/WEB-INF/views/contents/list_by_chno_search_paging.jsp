<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
  content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" />
<title>ChineseCulture</title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script type="text/javascript">
  
</script>

</head>

<body>
  <jsp:include page="../menu/top.jsp" />

  <DIV class='title_line'>
    <A href="../chinesegrp/list.do" class='title_link'>주제</A> > <A
      href="../chinese/list_by_chgrpno.do?chgrpno=${chinesegrpVO.chgrpno }"
      class='title_link'>${chinesegrpVO.chfield }</A> > <A
      href="./list_by_chno_search_paging.do?chno=${chVO.chno }" class='title_link'>${chVO.chtitle }</A>
  </DIV>

  <DIV class='content_body'>
    <DIV style="text-align: left;">
      <ASIDE class="aside_right">
        <A
          href="./create.do?chno=${chVO.chno }&chgrpno=${chVO.chgrpno }">등록</A>
        <span class='menu_divide'>│</span> <A
          href="javascript:location.reload();">새로고침</A> <span
          class='menu_divide'>│</span> <A
          href="./list_by_chno_grid_search_paging.do?chno=${chVO.chno }">갤러리형</A>
      </ASIDE>
    </DIV>
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
            onclick="location.href='./list_by_chno_search_paging.do?chno=${chVO.chno}&now_page=${param.now_page }&word='">검색
            취소</button>
        </c:if>
      </form>
    </DIV>

    <DIV class='menu_line'></DIV>

    <table class="table table-striped" style='width: 100%;'>
          <colgroup>
            <col style="width: 10%;"></col>
            <col style="width: 40%;"></col>
            <col style="width: 10%;"></col>
            <col style="width: 10%;"></col>
            <col style="width: 5%;"></col>
            <col style="width: 15%;"></col>
            <col style="width: 5%;"></col>
          </colgroup>
          <%-- table 컬럼 --%>
          <thead>
            <tr>
              <th style='text-align: center;'>메인 이미지</th>
              <th style='text-align: center;'>주제</th>
              <th style='text-align: center;'>난이도</th>
              <th style='text-align: center;'>강의시간</th>
              <th style='text-align: center;'>메모</th>
              <th style='text-align: center;'>학습자료</th>
              <th style='text-align: center;'>기타</th>
            </tr>

          </thead>

          <%-- table 내용 --%>
          <tbody>
            <c:forEach var="contentsVO" items="${list }">
              <c:set var="contentsno" value="${contentsVO.contentsno }" />
              <c:set var="thumb1" value="${contentsVO.thumb1 }" />

              <tr>
                <td style='vertical-align: middle; text-align: center;'>
                  <c:choose>
                    <c:when
                      test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
                      <%-- /static/contents/storage/ --%>
                      <a href="./read.do?contentsno=${contentsno}&now_page=${param.now_page }"><IMG
                        src="/contents/storage/${thumb1 }"
                        style="width: 120px; height: 80px;"></a>
                    </c:when>
                    <c:otherwise>
                      <!-- 이미지가 아닌 일반 파일 -->
                ${contentsVO.file1}
              </c:otherwise>
                  </c:choose>
                </td>
                <td style='vertical-align: middle;'><a
                  href="./read.do?contentsno=${contentsno }&now_page=${param.now_page }"><strong>${contentsVO.title }</strong>
                </a></td>
                <td style='vertical-align: middle; text-align: center;'>
                  ${contentsVO.tklevel }</td>
                <td style='vertical-align: middle; text-align: center;'>
                  ${contentsVO.lecturetime }분</td>
                <td style='vertical-align: middle; text-align: center;'>
                <A
                  href="#"><span
                    class="glyphicon glyphicon-list-alt"></span></A>
                </td>
                <td style='vertical-align: middle; text-align: center;'>
                      <A
                  href="#"><span
                    class="glyphicon glyphicon-save-file"></span></A>
                </td>
                <td style='vertical-align: middle; text-align: center;'>
              
                  <A
                  href="./update_text.do?contentsno=${contentsno}&now_page=${param.now_page }"><span
                    class="glyphicon glyphicon-pencil"></span></A> <A
                  href="./delete.do?contentsno=${contentsno}&now_page=${param.now_page }"><span
                    class="glyphicon glyphicon-trash"></span></A>
                </td>
              </tr>
            </c:forEach>

          </tbody>
    </table>
    <DIV class='bottom_menu'>${paging }</DIV>
  </DIV>


  <jsp:include page="../menu/bottom.jsp" />
</body>

</html>
