<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"					uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"					uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c"					uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/seller.js"></script>
	<script src="/js/jquery-3.7.1.min.js"></script>
	<script>
		function goPage(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action = "/seller/sale/listStatus.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" action="/seller/sale/listStatus.web">
	<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<section class="content">
		<nav>
			<%@ include file="/include/seller/sale/lnb.jsp" %>
		</nav>
		<article>
			<div class="brdSearchArea" style="text-align:right !important;">
				<select name="searchKey">
					<option value="buy_info"<c:if test="${paging.searchKey == 'buy_info'}"> selected</c:if>>구매명</option>
					<option value="sle_nm"<c:if test="${paging.searchKey eq 'sle_nm'}"> selected</c:if>>상품명</option>
				</select>
				<input type="text" name="searchWord" value="${paging.searchWord}" /> <input type="submit" value="검색"/>
			</div>
			<div class="brdInfo">Total: ${paging.totalLine}[${paging.currentPage}/${paging.totalPage} 페이지]</div>
			<table class="type_01" style="margin-left: auto; margin-right: auto;">
				<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>구매 일시</th>
						<th>구매자</th>
						<th>구매명</th>
						<th>총 가격(수량)</th>
						<th>결제 상태</th>
						<th>배송 상태</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="6">구매한 상품이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach items="${list}" var="list">
						<c:set var="trColor" value="${list.rnum % 2}"/>
						<tr<c:if test="${list.cd_state_delivery != 'Y'}"> style="color:red"</c:if> bgcolor="#<c:if test="${trColor == 1}">FFFFFF</c:if><c:if test="${trColor != 1}">F8F8F8</c:if>">
							<td>
								${list.rnum}
							</td>
							<td>
								${list.dt_reg}
							</td>
							<td>
								<plutozoneUtilTag:masking text="${list.cst_nm}" letter="*" count="1" />
							</td>
							<td style="text-align: left">
								<a href="/seller/sale/modifyFormStatus.web?seq_buy_mst=${list.seq_buy_mst}">
									 <plutozoneUtilTag:substring text="${fn:escapeXml(list.buy_info)}" length="30" /> 
								</a>
							</td>
							<td class="txtRight">
								<fmt:formatNumber value="${list.buy_t_price}" type="number" />(<fmt:formatNumber value="${list.buy_t_count}" type="number" />)
							</td>
							<td>
								${list.state_pay_nm}
							</td>
							<td>
								${list.state_delivery_nm}
							</td>
						</tr>
						</c:forEach>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br />
			<plutozoneUtilTag:page styleID="admin_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
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