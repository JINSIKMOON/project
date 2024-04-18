<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/front/myPage/view.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<style>
	#primary {
		margin-left: -180px; /* 왼쪽으로 30px 이동 */
	}	
	</style>
	<!-- <script>
		function cancel() {
			if (confirm(MSG_ASK_CNL)) {
				alert("TODO: 배송이 완료된 경우 취소 불가 처리할 것 + 취소 로직 추가할 것");
				return;
				var frmMain = document.getElementById("frmMain");
				frmMain.action = "/front/buy/cancel.web";
				frmMain.submit();
			}
		}
	</script> -->
	<script>
	function openReviewForm(value) {
		var leftOffset = window.screenLeft != undefined ? window.screenLeft : window.screenX;
		var topOffset = window.screenTop != undefined ? window.screenTop : window.screenY;
		
		var leftPosition = leftOffset + 500 * window.devicePixelRatio;
		var topPosition = topOffset + 100 * window.devicePixelRatio; 
		
		var frmMain = document.getElementById("frmMain");
		
		var popup = window.open("/front/myPage/reviewForm.web?seq_sle=" + value, "reviewForm", "width=490,height=700,left=" + leftPosition + ",top=" + topPosition);
		
		if (!popup || popup.closed || typeof popup.closed === 'undefined') {
			alert("팝업이 차단되었습니다. 팝업 차단을 해제해주세요.");
		}
	}
	</script>
</head>
<body>
<div class="container">
<form id="frmMain" method="POST" encType="UTF-8">
	<input type="hidden" name="seq_buy_mst" value="${list[0].seq_buy_mst}" />
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
			<table style="width:900px;">
				<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>이미지</th>
						<th>상품명</th>
						<th>가격</th>
						<th>수량</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan="5">등록된 상품이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
					<c:forEach items="${list}" var="list"  varStatus="status">
					<tr>
						<td>
							${status.count}
						</td>
						<td>
						<img src="/image/sale${list.img}" alt="" width="80" height="80">
						</td>
						<td>
							${list.sle_nm}
						</td>
						<td>
							<fmt:formatNumber value="${list.price}" type="number" />
						</td>
						<td>
							<fmt:formatNumber value="${list.count}" type="number" />
						</td>
						<td>
						<a href="/front/sale/detail.web?seq_sle=${list.seq_sle}" ><input type ="button" value="재구매" /></a>
						</td>
						<td>
						<input type ="button" value="리뷰작성" onclick="openReviewForm(${list.seq_sle})"/>
						</td>
					</tr>
					</c:forEach>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			</div>
		</div>
	</div>
</form>
	</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>