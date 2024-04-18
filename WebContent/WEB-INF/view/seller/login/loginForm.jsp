<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/seller.js"></script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" encType="UTF-8">
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<section class="content">
		<nav></nav>
		<article>
				<table class="type_02" style="width: 300px; margin-left: auto; margin-right: auto;">
					<tr>
						<th>아이디</th>
						<td><input value="sellers" id="id" name="id" type="text" style="ime-mode: disabled;" required maxlength="16" autocomplete="off" tabindex="1" /></td>
					</tr>
					<tr>
						<th>암호</th>
						<td><input value="123456789!a" id="passwd" name="passwd" type="password" style="ime-mode: disabled;" required maxlength="16" autocomplete="off" tabindex="2" onkeydown="if (event.keyCode==13) checkLogin();"/></d>
					</tr>
				</table>
				<div style="text-align:center !important; margin: 10px">
					<input type="button" tabindex="3" value="로그인" onclick="javascript:checkLogin();"/>
				</div>
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