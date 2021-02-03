<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<script type="text/javascript"
   src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<%-- ${ pageContext.request.contextPath } --> was에서 구동하는 root를 말하는거임 --%>
<script type="text/javascript">
   $(function() {
      // 최근 등록된 공지글 3개 자동 출력되게 함
            $.ajax({
               url : "${ pageContext.request.contextPath }/ntop3.do", /* 절대경로 */
               type : "post",
               dataType : "json",
               success : function(data) {
                  console.log("success : " + data);

                  // object ==> string
                  var jsonStr = JSON.stringify(data);
                  // string ==> json
                  var json = JSON.parse(jsonStr);

                  var values = "";
                  for ( var i in json.list) {
                     values += "<tr><td>"
                           + json.list[i].nid
                           + "</td><td><a href='${ pageContext.request.contextPath }/ndetail.do?nid="
                           + json.list[i].nid
                           + "'>"
                           + decodeURIComponent(json.list[i].ntitle)
                                 .replace(/\+/gi, " ") /* 디코딩하면 공백이 +로 되기때문에  " " 공백으로 바꿔준다*/
                           + "</a></td><td>"
                           + json.list[i].n_create_date + "</td></tr>";
                  } //for in

                  $("#new_notice").html($("#new_notice").html() + values);
               },
               error : function(jqXHR, textstatus, errorthrown) {
                  console.log("error : " + jqxHHR + ", " + textstatus
                        + ", " + errorthrown);
               }
            });
   }); // jquery document ready

   //조회수 많은 인기 게시글출력되게 함

         $.ajax({
            url : "${ pageContext.request.contextPath }/btop3.do", /* 절대경로 */
            type : "post",
            dataType : "json",
            success : function(data) {
               console.log("success : " + data);

               // object ==> string
               var jsonStr = JSON.stringify(data);
               // string ==> json
               var json = JSON.parse(jsonStr);

               var values = "";
               for ( var i in json.list) {
                  values += "<tr><td>"
                        + json.list[i].bid
                        + "</td><td><a href='${ pageContext.request.contextPath }/bdetail.do?bid="
                        + json.list[i].bid
                        + "'>"
                        + decodeURIComponent(json.list[i].btitle)
                              .replace(/\+/gi, " ") /* 디코딩하면 공백이 +로 되기때문에  " " 공백으로 바꿔준다*/
                        + "</a></td><td>" + json.list[i].bcount
                        + "</td></tr>";
               } //for in

               $("#top_board").html($("#top_board").html() + values);
            },
            error : function(jqXHR, textstatus, errorthrown) {
               console.log("error : " + jqxHHR + ", " + textstatus + ", "
                     + errorthrown);
            }
         });
</script>
</head>
<body>
   <c:import url="common/menubar.jsp" />
   <hr style="clear: both;">
   <!-- float되있는거 해제 -->
   <section>
      <%-- 최근 공지글 3개 자동 조회 출력 : ajax 사용 --%>
      <div
         style="float: left; border: 1px solid navy; padding: 5px; margin: 5px;">
         <h4>새로운 공지글</h4>
         <table id="new_notice" border="1" cellspacing="0">
            <tr><th>번호</th><th>제목</th><th>날짜</th>
            </tr>
         </table>
      </div>
      <%-- 인기 게시글 3개 자동 조회 출력 : ajax 사용 --%>
      <div style="border: 1px solid navy; padding: 5px; margin: 5px;">
         <h4>인기 게시글</h4>
         <table id="top_board" border="1" cellspacing="0">
            <tr><th>번호</th><th>제목</th><th>조회수</th>
            </tr>
         </table>
      </div>
   </section>
<br style="clear:both;">

</body>
</html>