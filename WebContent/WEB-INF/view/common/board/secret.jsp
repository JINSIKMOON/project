<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
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
	<script>
		function goPage(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action = "/common/board/list.web";
			frmMain.submit();
		}
		// 페이지가 로드되었을 때 실행되는 함수
		window.onload = function() {
		    // id_in 클래스명을 가진 모든 input 요소를 가져옴
		    var idInputs = document.querySelectorAll('.id_in');
		    
		    // 각 input 요소에 대해 처리
		    idInputs.forEach(function(input) {
		        // input 요소의 값을 가져옴
		        var idValue = input.value;
		        
		        // 뒤의 네 자리를 '*'로 대체하여 새로운 문자열 생성
		        var maskedIdValue = maskId(idValue);
		        
		        // id_out 클래스명을 가진 요소를 찾아서 그 안에 새로운 값을 삽입
		        var idOutput = input.nextElementSibling;
		        if (idOutput && idOutput.classList.contains('id_out')) {
		            idOutput.textContent = maskedIdValue;
		        }
		    });
		};

		// 뒤의 네 자리를 '*'로 대체하는 함수
		function maskId(id) {
		    if (id.length <= 4) return id; // id가 4자리 이하인 경우 그대로 반환
		    
		    var visiblePart = id.slice(0, -4); // 마지막 네 자리를 제외한 부분
		    var maskedPart = '****'; // 마지막 네 자리를 '*'로 대체한 부분
		    
		    return visiblePart + maskedPart;
		}
		
		</script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" action="/common/board/secret.web">
	<header>
		<% if (cd_usr_type_lnb == 2) { %>
			<%@ include file="/include/seller/top.jsp" %>
		<% } else { %>
			<%@ include file="/include/front/top.jsp" %>
		<% } %>
	</header>
	<section class="content">
		<article>
		<input type="hidden" name="seq_bbs" value="${boardDto.seq_bbs}">
		<input type="hidden" name="cd_bbs_type" value="3">
		<div style="text-align: center;">
			<h2 style="text-align: center;">비밀글입니다.</h2> <br>비밀번호를 입력해 주세요.<br><br><input type="password" name="secret" value="">
			<input type="submit" value="확인">
		</div>
		<br><br>
		</article>
	</section>
	</form>
</div>
</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>