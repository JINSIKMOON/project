<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%
	// 관리자(1), 판매자(2), 구매자(3) 
	int cd_usr_type_lnb = 0;
	try {cd_usr_type_lnb = Integer.parseInt((String)session.getAttribute("CD_USR_TYPE"));}
	catch (Exception e) {}
	%>
	<% if (cd_usr_type_lnb == 2) { %>
		<%@ include file="/include/seller/header.jsp" %>
	<% } else { %>
		<%@ include file="/include/front/header.jsp" %>
	<% } %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
		// HTML Editor
		tinymce.init({selector:'textarea'});
	
		function download() {
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/common/download.web";
			frmMain.target = "frmBlank";
			frmMain.submit();
		}
	
		$(function(){
			$('#datepicker').datepicker();
		})
		
		function viewImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var file = input.files[0];
        if (file.type.match('image.*')) { // 이미지 파일인 경우
            reader.onload = function (e) {
                $("#preview").attr("src", e.target.result);
            }
            reader.readAsDataURL(file);
        } else { // 이미지 파일이 아닌 경우
            $("#preview").attr("src", "/image/filedown.png"); // 이미지 프리뷰 초기화
            $("#preview").attr("alt", "[일반 파일입니다.]");
        }
    }
}
		  // 폼이 제출될 때 실행되는 함수
	    function validateForm() {
	        // 제목과 내용 입력값 가져오기
	        var title = $('input[name="title"]').val();
	        var contents = $('textarea[name="contents"]').val();

	        // 제목이 빈칸이거나 내용이 빈 경우
	        if (title.trim() === '' || contents.trim() === '') {
	            alert('제목과 내용을 모두 입력해주세요.');
	            return false; // 폼 서브밋 중단
	        }
	        return true; // 제출 가능
	    }
	</script>
</head>
<body>
<div id ="page">
<div class="container">
<form id="frmMain" method="POST" action="/common/board/modifyProc.web" enctype="multipart/form-data"  onsubmit="return validateForm();">
<input type="hidden" name="seq_bbs" 		value="${boardDto.seq_bbs}" />
<input type="hidden" name="cd_bbs_type" 	value="${boardDto.cd_bbs_type}" />
	<header>
 		<% if (cd_usr_type_lnb == 2) { %>
			<%@ include file="/include/seller/top.jsp" %>
		<% } else { %>
			<%@ include file="/include/front/top.jsp" %>
		<% } %>
	</header>
	<section class="content">
		<article>
			<table class="type_02" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th style="width: 15%">제목</th>
					<td>
						<input type="text" name="title" value="${boardDto.title}" style="width: 80%;" />
						<c:if test="${boardDto.cd_bbs_type == 1}">
							 <input type="checkbox" name="flg_top"<c:if test="${boardDto.flg_top == 'Y'}"> checked</c:if> value="Y" /> 최상위
						</c:if>
					</td>
				</tr>
				<c:if test="${boardDto.cd_bbs_type == 2}">
				<tr>
					<th>분류</th>
					<td>
						<select name="cd_bbs_tab">
							<option value="1"<c:if test="${boardDto.cd_bbs_tab == 1}"> selected</c:if>>상품</option>
							<option value="2"<c:if test="${boardDto.cd_bbs_tab == 2}"> selected</c:if>>구매</option>
							<option value="3"<c:if test="${boardDto.cd_bbs_tab == 3}"> selected</c:if>>결제</option>
						</select>
					</td>
				</tr>
				</c:if>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="contents" id="contents" style="width:650px; height:200px;" maxlength="1000">${boardDto.contents}</textarea>
					</td>
				</tr>
				<c:if test="${boardDto.file_save != null && boardDto.file_save != ''}">
				<tr >
					<th>파일</th>
					<td> 
						<img id="preview" src="/upload/${boardDto.file_save}" width="200" height="200" />
						<input type="file" name="fileName" onchange="viewImage(this);"/>
				    <input type="hidden" name="fileName" value="${boardDto.file_save}"/>
				
					</td>
				</tr> 
				</c:if>
				<%-- <tr>
					<th>파일</th>
					<td>
						<a href="javascript:download()" title="${boardDto.file_orig}"> [다운로드] </a>
					</td>
				</tr>  --%>
				<tr>
					<th>등록 일시</th>
					<td>
						${boardDto.dt_reg}
					</td>
				</tr>
				<tr>
					<th>수정 일시</th>
					<td>
						${boardDto.dt_upt}
					</td>
				</tr>
			</table>
			<input type="submit" value="수정" />			
			&nbsp;<a href="/common/board/list.web?cd_bbs_type=${boardDto.cd_bbs_type}" class="btnBasic"> 목록 </a>
		</article>
		<aside></aside>
	</section>
	</form>
</div>
</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
<iframe name="frmBlank" id="frmBlank" width="0" height="0"></iframe>
</body>
</html>