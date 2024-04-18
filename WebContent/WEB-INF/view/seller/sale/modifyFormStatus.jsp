<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/seller.js"></script>
	<script src="/js/jquery-3.7.1.min.js"></script>
	<script>
		function modifyProcStatus() {
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/seller/sale/modifyProcStatus.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<div id = "page">
<div class="container">
<form id="frmMain" method="POST">
	<input type="hidden" name="seq_buy_mst" value="${statusMstDto.seq_buy_mst}" />
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<section class="content">
		<nav>
			<%@ include file="/include/seller/sale/lnb.jsp" %>
		</nav>
		<article>
			<table class="type_02" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th style="width:200px;">구매 일시</th>
					<td>
						${statusMstDto.dt_reg}
					</td>
				</tr>
				<tr>
					<th>구매자</th>
					<td>
						<plutozoneUtilTag:masking text="${statusMstDto.cst_nm}" letter="*" count="1" mode="left" />
					</td>
				</tr>
				<tr>
					<th>구매명</th>
					<td>
						${statusMstDto.buy_info}
					</td>
				</tr>
				<tr>
					<th>결제 상태</th>
					<td>
						<select name="cd_state_pay">
							<option value="N"<c:if test="${statusMstDto.cd_state_pay == 'N'}"> selected</c:if>>결제 전</option>
							<option value="Y"<c:if test="${statusMstDto.cd_state_pay == 'Y'}"> selected</c:if>>결제 완료</option>
							<option value="C"<c:if test="${statusMstDto.cd_state_pay == 'C'}"> selected</c:if>>결제 취소</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>배송 상태</th>
					<td>
						<select name="cd_state_delivery">
							<option value="P"<c:if test="${statusMstDto.cd_state_delivery == 'P'}"> selected</c:if>>준비중</option>
							<option value="D"<c:if test="${statusMstDto.cd_state_delivery == 'D'}"> selected</c:if>>배송중</option>
							<option value="Y"<c:if test="${statusMstDto.cd_state_delivery == 'Y'}"> selected</c:if>>배송 완료</option>
						</select>
					</td>
				</tr>
			</table>
			<input type="button" value="수정" onclick="javascript:modifyProcStatus();"/>
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