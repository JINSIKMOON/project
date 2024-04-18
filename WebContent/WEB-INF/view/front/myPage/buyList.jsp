<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html>
<head>
	<style>
	#primary {
		margin-left: -180px; /* 왼쪽으로 30px 이동 */
	}	
	</style>
	<%@ include file="/include/front/header.jsp" %>
	<script>
		function goView(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.seq_buy_mst.setAttribute("value", value);
			frmMain.action = "/front/myPage/view.web";
			frmMain.submit();
		}
		
		function goPage(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action = "/front/myPage/buyList.web";
			frmMain.submit();
		}
	</script>
</head>
<body>
<div class="container">
<form id="frmMain" method="POST" action="/front/myPage/buyList.web">
	<input type="hidden" name="seq_buy_mst" id="seq_buy_mst" />
	<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<div id="content" class="site-content">
		<nav>
			<%@ include file="/include/front/myPage/lnb.jsp" %>
		</nav>
		<!-- #secondary -->
			<div id="primary" class="content-area column two-thirds">
			<div class="column column-width-one-third">
			<div class="brdInfo">Total: ${paging.totalLine}[${paging.currentPage}/${paging.totalPage} 페이지]</div>
			<table style="width:950px;">
			<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>구매명</th>
						<th>총 가격(수량)</th>
						<th>결제상태</th>
						<th>배송상태</th>
						<th>구매일</th>
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
						<tr style="cursor: pointer;" onclick="javascript:goView(${list.seq_buy_mst});">
							<td>
								${list.rnum}
							</td>
							<td style="text-align: left" >
								${list.buy_info}
							</td>
							<td>
								<fmt:formatNumber value="${list.buy_t_price}" type="number" />(<fmt:formatNumber value="${list.buy_t_count}" type="number" />)
							</td>
							<td>
								${list.state_pay_nm}
							</td>
							<td>
								${list.state_delivery_nm}
							</td>
							<td>
								${list.dt_reg}
							</td>
						</tr>
						</c:forEach>
					</c:otherwise>
					</c:choose>
				</tbody>
				</table>
				<div style="text-align:center !important; margin: 10px">
				<plutozoneUtilTag:page styleID="admin_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
				</div>
			</div>
		</div>
	</div>
</form>
	</div>
	<footer>
		<%@ include file = "/include/footer.jsp" %>
	</footer>
</body>
</html>