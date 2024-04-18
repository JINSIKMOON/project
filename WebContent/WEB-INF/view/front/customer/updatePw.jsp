<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script type="text/javascript" src="/js/front.js"></script>
</head>
<body>
<form id="frmMain" method="POST">
	<header>
	    <%@ include file="/include/front/top.jsp" %>
	</header>
	<section class="content">
		<article style="margin-top:50px; text-align: center">
				<h1 style="display: block; width: 100%; text-align: center">비밀번호 찾기</h1><br/>
				<a href="/front/customer/findId.web">아이디찾기</a>
				<a href="/front/customer/findPw.web">비밀번호찾기</a>
				
				<br/><br/>
				<table class="type_02" style="width: 350px; margin-left: auto; margin-right: auto;">
					<tr>
						<th>새로운 비밀번호(<span style="color: red;">*</span>)</th>
						<td>
							<input type="password" id="passwd" name="newPassword" oninput="updatePassword" placeholder="새로운 비밀번호">
							<div id="result"></div>
						</td>
					</tr>
					<tr>
						<th>비밀번호 재확인(<span style="color: red;">*</span>)</th>
						<td>
							<input type="password" id="pw2" oninput="updatePassword" placeholder="비밀번호를 한번 더 입력해주세요.">
							<div id="result2"></div>
						</td>
					</tr>
				</table>
				<input type="button" value="확인" onclick="javascript: updatePassword(event)" />
			</article>
		<aside></aside>
	</section>
</form>
<br>
<footer>
	<%@ include file="/include/footer.jsp" %>
</footer>
</body>
</html>
