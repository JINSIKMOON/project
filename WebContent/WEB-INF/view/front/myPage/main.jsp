<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<style>
	li{display:inline;}
	
	.contents-box {
		width: 770px;
		height: 200px;
		padding: 40px 0 90px;
		text-align: center;
		margin: 0 auto;
		border-bottom: 2px solid #eee;
		margin-bottom: 100px;
	}
	.contents-box li {
		list-style: none; 
		display: inline-block;
		margin-right: 80px;
	}

	.contents-box li div {
		margin-top: 5px;
		font-size:40px;
	}
	
	input[type="button"] {
		float: right;
		margin-left: 10px;
		}
		
	#primary {
		margin-left: -130px; /* 왼쪽으로 30px 이동 */
	}
	.site-content {
	margin-left: 315px;
	margin-right: 315px;
	overflow: hidden;
	min-height: 280px;
}
    .woocommerce ul.products li.product {
	transition:all 0.3s;
	width: 80%;
}
.woocommerce .products ul:after,
.woocommerce .products ul:before,
.woocommerce ul.products:after,
.woocommerce ul.products:before {
    content: " ";
    display: none;
}
</style>
</head>
<body class="archive post-type-archive post-type-archive-product woocommerce woocommerce-page">
<form id="frmMain" method="POST" >
		<header>
			<%@ include file = "/include/front/top.jsp" %>
		</header>
		<div id="content" class="site-content">
			<%@ include file = "/include/front/myPage/lnb.jsp" %>
		<!-- #secondary -->
			<div id="primary" class="content-area column two-thirds">
				<h1 class="entry-title"><strong>${customerDto.cst_nm}</strong>님은 해피핀핀 member 회원입니다.</h1>
			<hr>
				<main>
					<p><strong>나의 주문처리 현황</strong><span>(최근 <em>3개월</em> 기준)</span><a href ="/front/myPage/buyList.web"><input type="button" value="취소교환반품 내역"/></a></p> 
			<hr>
				<div class="column column-width-one-third">
					<div class="contents-box">
						<ul>
							<li><a href="/front/myPage/buyList.web"><span style="font-size: larger;">입금전</span><div>0</div></a></li>
							<li><span style="font-size: larger;">배송준비중</span><div>0</div></li>
							<li><span style="font-size: larger;">배송중</span><div>0</div></li>
							<li><span style="font-size: larger;">배송완료</span><div>0</div></li>
						</ul>
						</div>
					</div>
				</main>
				<br>
				<h1 class="entry-title"><strong>${customerDto.cst_nm}</strong>님을 위한 추천상품</h1><br>
					<ul class="products">
						<c:choose>
							<c:when test="${empty main}">
							</c:when>
						<c:otherwise>
							<c:forEach items="${main}" var="list" varStatus="status" begin="0" end="3">
								<li class="first product">
								<a href="/front/sale/detail.web?seq_sle=${list.seq_sle}" id="list_img" >
								<img src="/image/sale${list.img}" alt="">
								<h3>${list.sle_nm}</h3>
								<span class="price"><span class="amount"><fmt:formatNumber value="${list.price_sale}" pattern="#,###원"/></span></span>
								</a>
								</li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					</ul>	
				</div>
			</div>
		</form>
			<footer>
				<%@ include file = "/include/footer.jsp" %>
			</footer>
	</body>
</html>