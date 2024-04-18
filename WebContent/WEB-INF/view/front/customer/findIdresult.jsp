<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<%@ include file="/include/front/header.jsp" %>
</head>
<body>
<form id="frmMain" method="POST" action="/front/customer/findIdresult.web">
<input type="hidden" name="seq_cst" id="seq_cst" />
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<br><br><br>
		<section class="content">
			<table class="type_02" style="width: 900px; margin-left: auto; margin-right: auto;">
				<tbody>
				<c:choose>
					<c:when test="${empty customerDto}">
							<td style="color: black; font-weight: bold; font-size: 20px;">입력하신 정보로 가입 된 회원 아이디는 존재하지 않습니다.</td>
					</c:when>
					<c:otherwise>
					<tr>
						<td colspan="2" style="color: blue; font-weight: bold; font-size: 20px;">회원님의 아이디를 확인해 주세요</td>
					</tr>
					<tr>
						<td>아이디: ${maskedId}</td>
						<td>가입일: ${customerDto.dt_reg}</td>
					</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
	<br>
		</section>
			<div class="button-container" style="margin-bottom: 50px; text-align: center">
				<a href="/front/login/loginForm.web"><input type="button" value="로그인하기" /></a>
				<a href="/front/customer/findPw.web"><input type="button" value="비밀번호 찾기" /></a>
			</div>
		<footer>
			<%@ include file="/include/footer.jsp" %>
		</footer>
</form>
</body>
</html>
