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
			function goPage(value) {
				
				var frmMain = document.getElementById("frmMain");
				
				frmMain.currentPage.setAttribute("value", value);
				frmMain.action = "/seller/product/stock.web";
				frmMain.submit();
			}
	</script>	
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" action="/seller/product/stock.web">
	<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<section class="content">
		<nav>
			<%@ include file="/include/seller/product/lnb.jsp" %>
		</nav>
		<article>
			<div class="brdSearchArea" style="text-align:right !important;">
				<select name="searchKey">
					<option value="prd_nm"<c:if test="${paging.searchKey == 'prd_nm'}"> selected</c:if>>상품명</option>
					<option value="desces"<c:if test="${paging.searchKey == 'desces'}"> selected</c:if>>상품 설명</option>
					<option value="prd_nm+desces"<c:if test="${paging.searchKey eq 'prd_nm+desces'}"> selected</c:if>>상품명 또는 상품 설명</option>
				</select>
				<input type="text" name="searchWord" value="${paging.searchWord}" /> <input type="submit" value="검색"/>
			</div>
			<div class="brdInfo">Total: ${paging.totalLine}[${paging.currentPage}/${paging.totalPage} 페이지]</div>
			<table class="type_01" style="margin-left: auto; margin-right: auto;">
				<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>상품명</th>
						<th>재고 수량</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="3">등록된 상품이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach items="${list}" var="list">
						<c:set var="trColor" value="${list.rnum % 2}"/>
						<tr bgcolor="#<c:if test="${trColor == 1}">FFFFFF</c:if><c:if test="${trColor != 1}">F8F8F8</c:if>">
							<td>
								${list.rnum}
							</td>
							<td style="text-align: left">
								<a href="/seller/product/view.web?seq_prd=${list.seq_prd}">${list.prd_nm}</a>
							</td>
							<td>
								${list.count_stock}
							</td>
						</tr>
						</c:forEach>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<div style="text-align:center !important; margin: 10px">
				<plutozoneUtilTag:page styleID="admin_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
				<a href="/seller/product/writeForm.web" class="btnBasic"> 등록 </a>
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