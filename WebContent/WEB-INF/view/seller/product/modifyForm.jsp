<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/seller.js"></script>
	<script src="/js/jquery-3.7.1.min.js"></script>
	<script>
		$(function(){
			$('#datepicker').datepicker();
		})
		
		function writeProc() {
			
			// 제출 전에 ","를 모두 제거
			document.getElementById("price_cost").value = document.getElementById("price_cost").value.replaceAll(",", "");
			
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/seller/product/writeProc.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST">
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<section class="content">
		<article>
			<table class="type_02" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th style="width:200px;">상품명</th>
					<td>
						<input type="text" name="prd_nm" style="width: 300px" />
					</td>
				</tr>
				<tr>
					<th>상품 설명</th>
					<td>
						<textarea name="desces" rows="10" cols="65" maxlength="1000"></textarea>
					</td>
				</tr>
				<tr>
					<th>상품 원가</th>
					<td>
						<input type="text" id="price_cost" name="price_cost" style="width:100px; text-align:right" onkeyup="commaValue(this);" />
					</td>
				</tr>
				<!--
				<tr>
					<th>판매 시작일</th>
					<td>
						<input type="text" id="datepicker" />
					</td>
				</tr>
				-->
			</table>
			<div style="text-align:center !important; margin: 10px">
				<input type="button" value="수정" onclick="javascript:writeProc();"/>
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