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
		padding-top: 0em;
	}
	</style>
	<%@ include file="/include/front/header.jsp" %>
	<script>
		function goPage(value) {
			
			var frmMain = document.getElementById("frmMain");
			
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action = "/front/myPage/reviewMng.web";
			frmMain.submit();
		}
		
		$(function(){
		    $('#datepicker').datepicker();
		});
		
		/* function OrderFilter.setPeriod(value) {
		    var frmMain = document.getElementById("frmMain");
		    frmMain.currentPage.setAttribute("value", 1); // 페이지를 1로 초기화
		    switch (value) {
		        case '1week':
		            // 1주일 데이터를 불러오는 로직을 여기에 추가
		            frmMain.dt_fr_input.value = calculateStartDate(7); // 7일 전의 날짜를 시작일로 설정
		            frmMain.dt_to_input.value = getCurrentDate(); // 현재 날짜를 종료일로 설정
		            break;
		        case '1month':
		            // 1개월 데이터를 불러오는 로직을 여기에 추가
		            frmMain.dt_fr_input.value = calculateStartDate(30); // 30일 전의 날짜를 시작일로 설정
		            frmMain.dt_to_input.value = getCurrentDate(); // 현재 날짜를 종료일로 설정
		            break;
		        case '3month':
		            // 3개월 데이터를 불러오는 로직을 여기에 추가
		            frmMain.dt_fr_input.value = calculateStartDate(90); // 90일 전의 날짜를 시작일로 설정
		            frmMain.dt_to_input.value = getCurrentDate(); // 현재 날짜를 종료일로 설정
		            break;
		        case '1year':
		            // 1년 데이터를 불러오는 로직을 여기에 추가
		            frmMain.dt_fr_input.value = calculateStartDate(365); // 1년 전의 날짜를 시작일로 설정
		            frmMain.dt_to_input.value = getCurrentDate(); // 현재 날짜를 종료일로 설정
		            break;
		        default:
		            // 기본적으로는 아무 동작도 하지 않음
		            break;
		    }
		    frmMain.action = "/front/myPage/reviewMng.web";
		    frmMain.submit();
		}

		// n일 전의 날짜를 반환하는 함수
		function calculateStartDate(n) {
		    var d = new Date();
		    d.setDate(d.getDate() - n);
		    var year = d.getFullYear();
		    var month = ("0" + (d.getMonth() + 1)).slice(-2);
		    var day = ("0" + d.getDate()).slice(-2);
		    return year + "-" + month + "-" + day;
		}

		// 현재 날짜를 반환하는 함수
		function getCurrentDate() {
		    var d = new Date();
		    var year = d.getFullYear();
		    var month = ("0" + (d.getMonth() + 1)).slice(-2);
		    var day = ("0" + d.getDate()).slice(-2);
		    return year + "-" + month + "-" + day;
		} */
	</script>
</head>
<body>
<div class="container">
<form id="frmMain" method="POST" action="/front/myPage/reviewMng.web">
	<input type="hidden" name="currentPage" value="${paging.currentPage}" />
	<input type="hidden" name="seq_sle" id="seq_sle"value="${saleDto.seq_sle}" />
	<input type="hidden" name="seq_cst" id="seq_cst" value="${saleEvalDto.seq_cst}" />
	<input type="hidden" name="score"	id="score"	value="${saleEvalDto.score}" />
	<input type="hidden" name="review"	id="review"		value="${saleEvalDto.review}" />
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<div id="content" class="site-content">
		<nav>
			<%@ include file="/include/front/myPage/lnb.jsp" %>
		</nav>
		<!-- #secondary -->
			<div id="primary" class="content-area column two-thirds">
			<h1 class="entry-title">내가 작성한 리뷰</h1>
			<hr>
			<!-- <ul>
				<li><input type="button" value="1주일" data-period="1week" onClick="OrderFilter.setPeriod('1week');"></li>
				<li><input type="button" value="1개월" data-period="1month" onClick="OrderFilter.setPeriod('1month');"></li>
				<li><input type="button" value="3개월" data-period="3month" onClick="OrderFilter.setPeriod('3month');"></li>
				<li><input type="button" value="최근1년" data-period="1year" onClick="OrderFilter.setPeriod('1year');">&nbsp;&nbsp;
				<li><input type="date" class="order-filter-period__input" name="dt_fr_input" value="" placeholder="-" onchange="checkDateFormat(this);"> ~ 
				<li><input type="date" class="order-filter-period__input" name="dt_to_input" value="" placeholder="-" onchange="checkDateFormat(this);"></li>
				<li><input type="button" value="조회" onclick="onSubmit();"/></li>
			</ul> -->
			
			<div class="brdInfo">Total: ${paging.totalLine}[${paging.currentPage}/${paging.totalPage} 페이지]</div>
			<table style="width:1000px;">
			<thead>
					<tr>
						<th style="width: 5%">NO</th>
						<th>상품명</th>
						<th>별점</th>
						<th>리뷰</th>
						<th>등록일</th>
					</tr>
				</thead>
				<div id="reviews">
					<div id="comments">
							 <c:choose>
									<c:when test="${empty review}">
											<td colspan="5">
													등록된 리뷰가 없습니다.
											</td>
											</c:when>
												<c:otherwise>
													<c:forEach items="${review}" var="list">
													<tr>
													<td>
													${list.rnum}
													</td>
													<td>
													<a href= "/front/sale/detail.web?seq_sle=${list.seq_sle}">${list.sle_nm}</a>
													</td>
													<td>
													<c:forEach begin="1" end="5" var="starIndex">
															<c:choose>
															<c:when test="${starIndex le list.score}">
																<i class="fa fa-star fa-lg" style="color: pink;"></i>
															</c:when>
															<c:otherwise>
																<i class="fa fa-star fa-lg" style="color: gray;"></i>
															</c:otherwise>
															</c:choose>
													</c:forEach>
													</td>
													<td>
													${list.review}
													</td>
													<td>
													${list.dt_reg}
													</td>
												</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose> 
							</div>
						</div>
					</tbody>
				</table>
				<div style="text-align:center !important; margin: 10px">
				<plutozoneUtilTag:page styleID="admin_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
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