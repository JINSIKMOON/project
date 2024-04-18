<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<%@ include file="/include/front/header.jsp" %>
	<style>
	
	body {
  margin: 0;
  padding: 10px; /* 바디 내부 여백 설정 (테두리가 바디를 통과하도록 함) */
  box-sizing: border-box;
  width: calc(100% - 20px); /* 바디의 전체 너비를 계산하여 설정 */
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

.center {
    text-align: center;
}

.move-btn.on {
    left: 0;
    opacity: .8;
    transition: opacity .3s .3s,left .5s ease;
}
.move-btn {
    position: fixed;
    z-index: 100;
    left: -30px;
    top: 40%;
    width: 40px;
    opacity: 0;
    transition: opacity .3s,left .5s .14s ease;
}
.move-btn li.on {
    background: url(/_wg/img/_dot/dotB_7.png) no-repeat 50% 50%;
}

.move-btn li {
    display: block;
    position: relative;
    width: 15px;
    height: 15px;
    border-radius: 10px;
    margin: 0 0 0 10px;
    background: url(/_wg/img/_dot/dotG_7.png) no-repeat 50% 50%;
    cursor: pointer;
    transition: all .3s ease;
}

.move-btn li {
    display: block;
    position: relative;
    width: 15px;
    height: 15px;
    border-radius: 10px;
    margin: 0 0 0 10px;
    background: url(/_wg/img/_dot/dotG_7.png) no-repeat 50% 50%;
    cursor: pointer;
    transition: all .3s ease;
}

.move-btn li div {
    position: absolute;
    display: block;
    top: -6px;
    left: -200px;
    max-width: 120px;
    line-height: 26px;
    padding: 0 10px 0 15px;
    border-right: 1px solid rgba(0,0,0,.1);
    background: url(/_wg/_cPack/cp560/img/arrow.gif) no-repeat rgba(255,255,255,.9);
    opacity: 0;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
    cursor: default;
    transition: opacity .3s ease;
}

.topbner input[id*="slide"] {
	display:none;
}
.topbner .slidewrap {
	max-width:1200px;
	margin:0 auto;
	overflow:hidden;
}
.topbner .slidelist {
	white-space:nowrap;
	font-size:0;
}
.topbner .slidelist > li {
	display:inline-block;
	vertical-align:middle;
	width:100%;
	transition:all .5s;
}
.topbner .slidelist > li > a {
	display:block;
	position:relative;
}
.topbner .slidelist > li > a img {
	width:100%
}
.topbner .slidelist label {
	position:absolute;
	z-index:10;
	top:50%;
	transform:translateY(-50%);
	padding:50px;
	cursor:pointer;
}
.topbner .slidelist .left {
	left:30px;
	background:url()center center / 100% no-repeat;
}
.topbner .slidelist .right {
	right:30px;
	background:url()center center;
}
.topbner [id="slide01"]:checked ~ .slidewrap .slidelist > li {transform:translateX(0%);}
.topbner [id="slide02"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-100%);}
.topbner [id="slide03"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-200%);}
.topbner [id="slide04"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-300%);}
.topbner [id="slide05"]:checked ~ .slidewrap .slidelist > li {transform:translateX(-400%);}
</style>
<script src='/js/tabs.js'></script>

	<script>
	
	//슬라이드 인덱스 초기화
	let slideIndex = 0;

	// 자동 슬라이드 함수 정의
	function autoSlide() {
	// 현재 선택된 라디오 버튼 해제
	document.querySelector('input[name="slide"]:checked').checked = false;
	// 다음 슬라이드 인덱스 계산
	slideIndex = (slideIndex + 1) % 5; // 슬라이드 개수에 맞게 수정
	// 해당 인덱스의 라디오 버튼을 체크
	document.getElementById('slide0' + (slideIndex + 1)).checked = true;
	}
	// 자동 슬라이드 시작 (5초 간격으로)
	setInterval(autoSlide, 5000); // 원하는 시간 간격으로 수정 가능
	
</script>

</head>
<body class="archive post-type-archive post-type-archive-product woocommerce woocommerce-page">
<div id="page">
<div class="container">
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<div class="topbner">
		<input type="radio" name="slide" id="slide01" checked>
		<input type="radio" name="slide" id="slide02">
		<input type="radio" name="slide" id="slide03">
		<input type="radio" name="slide" id="slide04">
		<input type="radio" name="slide" id="slide05">
		
		<div class="slidewrap" >
			<ul class="slidelist">
				<li>
					<a>
						<label for="slide05" class="left"></label>
						<img src="/image/mainbanner.jpg">
						<label for="slide02" class="right"></label>
					</a>
				</li>
				<li>
					<a>
						<label for="slide01" class="left"></label>
						<img src="/image/mainbanner2.jpg">
						<label for="slide03" class="right"></label>
					</a>
				</li>
				<li>
					<a>
						<label for="slide02" class="left"></label>
						<img src="/image/mainbanner3.jpg">
						<label for="slide04" class="right"></label>
					</a>
				</li>	
				<li>
					<a>
						<label for="slide03" class="left"></label>
						<img src="/image/mainbanner4.jpg">
						<label for="slide05" class="right"></label>
					</a>
				</li>	
				<li>
					<a>
						<label for="slide04" class="left"></label>
						<img src="/image/mainbanner5.jpg">
						<label for="slide01" class="right"></label>
					</a>
				</li>	
			</ul>
		</div>
	</div>
		<!-- #masthead -->
		<h1 class="center">NEW! ARRIVALS</h1>
		<hr>
		<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main" role="main">
				<form class="woocommerce-ordering" method="get">
				</form>
				
				<ul class="products">
					<c:choose>
						<c:when test="${empty list}">
						</c:when>
						<c:otherwise>
							<c:forEach items="${list}" var="item" varStatus="status" begin="0" end="7">
								<li class="first product">
								<span class="onsale">NEW!</span>
								<a href="/front/sale/detail.web?seq_sle=${item.seq_sle}" id="list_img" >
									<img src="/image/sale${item.img}" alt="">
									<h3>${item.sle_nm}</h3>
									<span class="price">
										<span class="amount"><fmt:formatNumber value="${item.price_sale}" pattern="#,###원"/></span>
									</span>
								</a>
								</li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
				
				<h1 class= "center"> HOT DEAL & EVENT </h1>
				<hr>
				<div id="content" class="site-content">
					<div id="primary" class="content-area column full">
				<form class="woocommerce-ordering" method="get">
				</form>
				
				<ul class="products">
					<c:choose>
						<c:when test="${empty main}">
						</c:when>
					<c:otherwise>
						<c:forEach items="${main}" var="list" varStatus="status" begin="0" end="3">
							<li class="first product">
							<span class="onsale">HOT!</span>
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
				
				<h1 class= "center"> RECOMMENDATION!! </h1>
				<hr>
					<div class="wpcmsdev-tabs">
						<div class="tab">
							<h3 class="tab-title" data-tab-id="101" >수초 스케이핑/테라리움</h3>
							<div id="101" class="tab-content">
								<ul class="products">
				
						<c:choose>
						<c:when test="${empty list1}">
						</c:when>
					<c:otherwise>
						<c:forEach items="${list1}" var="list" varStatus="status" begin="0" end="7">
							<li class="first product">
							<span class="onsale">BEST!</span>
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
				<div class="tab">
					<h3 class="tab-title" data-tab-id="103">수조/수조받침</h3>
						<div id="103" class="tab-content">
						<ul class="products">
				
					<c:choose>
						<c:when test="${empty list2}">
						</c:when>
					<c:otherwise>
						<c:forEach items="${list2}" var="list" varStatus="status" begin="0" end="7">
							<li class="first product">
							<span class="onsale">BEST!</span>
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
				<div class="tab">
				<h3 class="tab-title" data-tab-id="105">조명</h3>
				<div id="105" class="tab-content">
				<ul class="products">
				
					<c:choose>
						<c:when test="${empty list3}">
						</c:when>
					<c:otherwise>
						<c:forEach items="${list3}" var="list" varStatus="status" begin="0" end="7">
							<li class="first product">
							<span class="onsale">BEST!</span>
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
					</div>
				</main>
				<!-- #main -->
			</div>
			<!-- #primary -->
		</div>
	</div>
</div>
	<footer>
	<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>