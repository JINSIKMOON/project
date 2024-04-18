<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<script type="text/javascript" src="/js/package/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/package/tinymce.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
		// HTML Editor
		tinymce.init({selector:'textarea'});
	
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
		// 문서가 완전히 로드되면 실행됩니다.
		$(document).ready(function() {
		    // 체크박스 요소를 선택합니다.
		    var checkbox = document.querySelector('.secretbutton');
		    // 텍스트박스 요소를 선택합니다.
		    var textbox = document.querySelector('.secret');

		    // 텍스트박스의 초기 표시 여부 설정
		    textbox.style.display = checkbox.checked ? 'inline-block' : 'none';

		    // 체크박스의 변경 이벤트에 대한 리스너를 추가합니다.
		    checkbox.addEventListener('change', function() {
		        // 체크박스가 체크되어 있는지 확인합니다.
		        if (checkbox.checked) {
		            // 텍스트박스를 보이게 설정합니다.
		            textbox.style.display = 'inline-block';
		        } else {
		            // 체크박스가 체크되어 있지 않으면 텍스트박스를 숨깁니다.
		            textbox.style.display = 'none';
		        }
		    });
		});
		 $(document).ready(function() {
		        // 폼 서브밋 이벤트 핸들러를 추가합니다.
		        $('form').submit(function(event) {
		            // 제목과 내용 입력값 가져오기
		            var title = $('input[name="title"]').val();
		            var contents = $('textarea[name="contents"]').val();

		            // 제목이 빈칸이거나 내용이 빈 경우
		            if (title.trim() === '' || contents.trim() === '') {
		                alert('제목과 내용을 모두 입력해주세요.');
		                event.preventDefault(); // 폼 서브밋 중단
		                return;
		            }
		        });
		    });
	</script>
	<style>
	li{display:inline;}
	</style>
</head>
<body>
<div id="page">
<div class="container">
<form method="POST" action="/common/board/writeProc.web" enctype="multipart/form-data">
<input type="hidden" name="cd_bbs_type" value="${cd_bbs_type}" />
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
						<input type="text" name="title" style="width: 80%;" />
						<c:if test="${cd_bbs_type == 1}">
							 <input type="checkbox" name="flg_top" value="Y" /> 최상위
						</c:if>
						<c:if test="${cd_bbs_type == 3}">
							 <input type="checkbox" class="secretbutton" /> 비밀글
							 <input type="text" name="secret" class="secret" style="width: 100px;" />
						</c:if>
					</td>
				</tr>
				<%-- <c:if test="${cd_bbs_type == 2}">
				<tr>
					<th>분류</th>
					<td>
						<select name="cd_bbs_tab">
							<option value="1">상품</option>
							<option value="2">구매</option>
							<option value="3">결제</option>
						</select>
					</td>
				</tr>
				</c:if> --%>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="contents" id="contents" style="width: 650px;height:200px;" maxlength="1000"></textarea>
					</td>
				</tr>
				<!-- <tr>
					<th>이미지</th>
					<td>
						<img id="preview" src="/image/noimg.gif" width="200" height="200" />
						<input type="file" name="fileName1" onchange="viewImage(this);" />
					</td>
				</tr> -->
				<tr>
					<th>파일</th>
					<td>
						<img id="preview" src="/image/noimg.gif" width="200" height="200" />
						<input type="file" name="fileName" onchange="viewImage(this);"/>
						<!--
						<input type="file" name="fileName" onchange="viewImage(this);" />
						<img id="preview" src="/image/noImage.jpg" width="200" height="200" />
						-->
					</td>
				</tr>
			</table>
			<div style="text-align:center !important; margin: 10px">
				<input type="submit" value="등록" />
					&nbsp;<a href="/common/board/list.web?cd_bbs_type=${cd_bbs_type}" class="btnBasic"> <input type="button" value="목록" /> </a>
			</div>
			<br>
		</article>
		<aside></aside>
	</section>
</form>
</div>
</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>