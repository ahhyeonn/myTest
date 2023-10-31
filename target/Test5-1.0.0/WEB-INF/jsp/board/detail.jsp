<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 상세보기</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<style type="text/css">
a{
 text-decoration: auto;
}
</style>
<%@ include file="/WEB-INF/jsp/member/menu.jsp" %>
</head>
<body>
<% String memId = (String)session.getAttribute("memId"); %>
    <br />
    <h1 class="text-center">게시글 상세보기</h1>
    <br />
    <br />
    <div class="container">
    	<form action="updateTest.do" id="viewForm" method="post" encType="multiplart/form-data">
            <table class="table table-bordered">
                <tbody>
                	<input type="hidden" name="memId" id="memId" value="<%=memId %>" />
                    <tr>
                        <th>글번호</th>
                        <td><input name="boardNo" type="text" value="${vo.boardNo}"
                            class="form-control" readonly /></td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td><input type="text" value="${vo.title}"
                            name="title" class="form-control" readonly /></td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td><textarea readonly name="content" class="form-control"
                                style="height: 200px;">${vo.content}</textarea></td>
 
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: right;">
                            <button id="btn_previous" type="button" class="btn btn-secondary me-1 mb-1">목록</button>
                            <button id="btn_modify" type="button" class="btn btn-success me-1 mb-1">수정</button>
<%--                             <a href="/update?no=${vo.boardNo}" role="button" class="btn btn-success me-1 mb-1">수정2</a> --%>
                            <button id="btn_delete" type="button" class="btn btn-danger me-1 mb-1">삭제</button>
                        </td>
                    </tr>
                </tbody>
            </table>
    	</form>
    </div>

    
    
<!-- ///////////// 댓글 시작 /////////////-->

<!-- 댓글 출력 시작 -->
	<div class="container">
		[댓글 목록]
		<div id="commentArea" class="list-group">
<%-- 			<c:forEach items="${comment}" var="result"> --%>
<%-- <%-- 				<input id="commentUserId" type="hidden" value="${result.memId}" /> --%> 
<%-- 					<div class="col-md-2 py-2">${result.memId}</div> --%>
<%-- 					<div class="col-md-7 py-2">${result.cmntContent}</div> --%>
<!-- 					<div class="col-md-3 py-2 text-right"> -->
<%-- 						<fmt:formatDate value="${result.cmntDate}"  pattern="yyyy-MM-dd HH:mm" /> --%>
<!-- 					</div> -->
<%-- 				</c:forEach> --%>
				
            <c:forEach items="${comment}" var="result" varStatus="stat" >
            <hr/>
            <input type="hidden" id="boardNo" value="${cmntVo.cmntNo}" />
            	<div>
            		<p></p>
					<p>댓글 내용: ${result.cmntContent}</p>
					<p>작성자: ${result.memId}</p>
					<p>작성일시: <fmt:formatDate value="${result.cmntDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
            	</div>
            	
            	
            </c:forEach>
           

		</div>
	</div>


<!-- 댓글 출력 끝 -->

<!-- 댓글 작성 시작-->
    <div class="container">
    	<form method="post" id="cmntWrite" action="/insertCmnt.do">
			<div id="commentDiv" style="margin-top: 30px;" >
				<div class="mb-3 d-flex align-items-center">
					<input type="hidden" id="cmntWriter" value="<%=memId%>" />
					<input type="hidden" id="boardNo" value="${vo.boardNo}" />
					<input type="hidden" id="cmntNo" value="${cmnt.cmntNo}" />
					<textarea id="cmntContent" class="form-control me-1" type="text" placeholder="댓글을 입력해 주세요" aria-label="default input example" style="width: 95%;" ></textarea>
				  <button type="button" id="cmntBtn" class="btn btn-primary">저장</button>
				</div>
			</div>
    	</form>
	</div>
<!-- 댓글 작성 끝-->


<!-- //////////// 댓글 끝 /////////////// -->
</body>




<script type="text/javascript">
//     $(document).on('click', '#btn_modify', function(e) {
//         if (confirm("정말 수정하시겠습니까 ?") == true) {
//             $("#viewForm").submit();
//         } else {
//             return;
//         }
//     });
    
    // 게시글 삭제
    $(document).on('click', '#btn_delete', function(e) {
        var boardNo = ${vo.boardNo};
        if (confirm("정말 삭제하시겠습니까 ?") == true) {
            $("#viewForm").attr("action", "deleteTest.do?boardNo="+boardNo);
            $("#viewForm").submit();
//             console.log("삭제");
        } else {
            return;
        }
    });
    
    //수정 클릭 시 update로 이동
    $("#btn_modify").click(function() {
    	window.location.href = "/update.do?boardNo=" + ${vo.boardNo};
 
    });
    
 
    //목록 클릭 시 list로 이동
    $("#btn_previous").click(function previous() {
        $(location).attr('href', 'list.do');
 
    });
    
    
    
    //댓글 작성
//     $(document).ready(function(){
//     });
        //listReply(); // **댓글 목록 불러오기
//         listReply2(); // ** json 리턴방식
        
//         // ** 댓글 쓰기 버튼 클릭 이벤트 (ajax로 처리)
//         $("#cmntBtn").click(function(){
// 	    	console.log("댓글!!!!!!!!!")
	    	
//             var cmntContent = $("#cmntContent").val();
// 	    	console.log("댓글 내용 : " + cmntContent);
	    	
// 	    	var memId = $("#memId").val();
// 	    	console.log("댓글 작성자 : " + memId);
	    	
//             var boardNo = "${vo.boardNo}"
// 	    	console.log("글번호: " + boardNo);
            
//             data = {"cmntContent":cmntContent,"memId":memId,"boardNo":boardNo};
//             console.log(data);
            
//             $.ajax({       
//                 url: "/insertCmnt.do",
//                 contentType:"application/json; charset=UTF-8",
//                 data: JSON.stringify(data),
//                 type: "post",
//                 dataType: "text",
//                 success: function(){
//                     alert("댓글이 등록되었습니다.");
// //                     listReply2();
//                 }
//             });
//           }
//          )
    
	    
	 //댓글작성
	$("#cmntBtn").on("click", function() {
		console.log("댓글 올라가세용");
		
		var cmntContent = $("#cmntContent").val();
		console.log("댓글내용 : "+ cmntContent);
		
		//console.log(userName);
		
		var memId =$("#memId").val();
		console.log("작성자 : " + memId)
		
		var boardNo = $("input[name='boardNo']").val();
		console.log("글번호 : " + boardNo );
		
// 		var cmntNo = $("#cmntNo").val();
// 		console.log("댓글번호 : " + cmntNo);
	
		var data = {
				"cmntContent":cmntContent
				,"memId":memId
				,"boardNo":boardNo
// 				,"cmntNo":cmntNo
				};
		console.log(data);
		  
		$.ajax({
			 url:"<c:url value='/'/>insertCmnt.do" ,
			 //url:"/insertCmnt.ajax" ,
			 //contentType:"application/json; charset=UTF-8",
			 //data:JSON.stringify(data),
			 data:data,
			 dataType:"JSON",
			 type:"POST",
			 beforeSend: function( xhr ) {
				 console.log(data);
				},
	         success:function(data){
	        	 $("#cmntContent").val("");
	        	 alert("댓글 작성 성공");
	        	 reloadDivArea();
	         },
	         error:function(request,status,error){
	             //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	            }
	
		  });
		  
		});
    
    
    
</script>
</html>









