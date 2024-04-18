<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/front.js"></script>
	<script type="text/javascript">
	
	// 최소 주문 수량 확인
	function checkMinQuantity() {
		var productCount = parseInt(document.getElementsByName("buyList[0].count")[0].value); // input 요소에서 상품 개수 가져오기
		if (productCount < 1) {
			alert("최소 주문 수량은 1개 입니다.");
			document.getElementsByName("buyList[0].count")[0].value = 1; // 최소 수량인 1로 변경
			updateTotalPrice(); // 총 가격 업데이트
		}
	}
	
	// 예시: 상품 개수에 따라 가격 계산
	function calculatePrice(productCount) {
		var pricePerItem = ${saleDto.price_sale}; // 각 상품의 가격
		return productCount * pricePerItem; // 상품 개수와 가격을 곱하여 총 가격 계산
	}

	// 상품 개수가 변경될 때 호출되는 함수
	function updateTotalPrice() {
		var productCount = parseInt(document.getElementsByName("buyList[0].count")[0].value); // input 요소에서 상품 개수 가져오기
		var totalPrice = calculatePrice(productCount);
		document.getElementById("totalPrice").innerText = totalPrice.toLocaleString() + "원"; // 새로운 가격으로 업데이트
	}


	function setBasket() {
			var seq_sle	= document.getElementById("seq_sle").value;
			var sle_nm	= document.getElementById("sle_nm").value;
			var price	= document.getElementById("price").value;
			var count	= document.getElementById("count").value;
			var img		= document.getElementById("img").src;
			
			if(count < 1){
				alert("상품을 1개 이상 입력해주세요!");
			} else {
				var item = seq_sle + "|" + sle_nm + "|" + price + "|" + count + "|" + img;
				insertBasket(item);
				
				if(confirm("장바구니에 상품이 담겼습니다. \n장바구니로 이동하시겠습니까?")) {
					location.href = "/front/cart/main.web";
				}
			}
		}
	function likeProc(seq_sle) {
	    // form 요소 생성
	    var form = document.createElement('form');
	    form.method = 'POST';
	    form.action = '/front/myPage/likeProc.web';
	    // 숨은 input 요소 추가
	    var input = document.createElement('input');
	    input.type = 'hidden';
	    input.name = 'seq_sle';
	    input.value = seq_sle;
	    form.appendChild(input);

	    // form을 body에 추가하여 제출
	    document.body.appendChild(form);
	    form.submit();
	}
	
</script>
</head>
<body class="single single-product woocommerce woocommerce-page">
<form id="frmMain" method="POST" action="/front/buy/orderView.web">
	<input type="hidden" id="setCart"		value="true" />
<input type="hidden" id="setCart"		value="true" />
	<input type="hidden" name="buyList[0].seq_sle"		id="seq_sle"		value="${saleDto.seq_sle}" />
	<input type="hidden" name="seq_prd"					id="seq_prd"		value="${saleDto.seq_prd}" />
	<input type="hidden" name="flgMobile"				id="flgMobile"		value="${flgMobile}" />
	<input type="hidden" name="score"					id="score"			value="${saleEvalDto.score}" />
	<input type="hidden" name="review"					id="review"			value="${saleEvalDto.review}" />
	<input type="hidden" name="buyList[0].sle_nm"		id="sle_nm"			value="${saleDto.sle_nm}" />
	<input type="hidden" name="buyList[0].price"		id="price"			value="${saleDto.price_sale}" />
	<input type="hidden" name="buyList[0].img"			id="_img"			value="/image/sale${saleDto.img}" />
	<input type="hidden" name="score"					id="score"			value="${sale_evalDto.score}" />
	<input type="hidden" name="review"					id="review"			value="${sale_evalDto.review}" />
<div id="page">
	<div class="container">
		<header>
		<%@ include file = "/include/front/top.jsp" %>
		</header>
		<!-- #masthead -->
		<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main" role="main">
				<div id="container">
					<div id="content" role="main">
						<div itemscope itemtype="http://schema.org/Product" class="product">
							<div class="images">
								<a href="" itemprop="image" class="woocommerce-main-image zoom" title="" data-rel="prettyPhoto">								
								<img name="buyList[0].img" id="img" src="/image/sale${saleDto.img}" alt=""></img></a>
							</div>
							<div style="border-bottom: 2px solid #111; margin-top: 20px; width: 577px; margin-left: auto;"></div>
							<div class="summary entry-summary">
								<h2>${saleDto.sle_nm}</h2>
								<c:forEach begin="1" end="5" var="starIndex">
							<c:choose>
							<c:when test="${starIndex le requestScope.saleEvalDto.average}">
								<i class="fa fa-star fa-lg" style="color: pink;"></i>
							</c:when>
							<c:otherwise>
								<i class="fa fa-star fa-lg" style="color: gray;"></i>
							</c:otherwise>
							</c:choose>
							</c:forEach>
								customer(${saleEvalDto.customer}Review)
								<div style="border-bottom: 1px solid #ccc; margin-top:20px;"></div>
								<div itemprop="offers" itemscope itemtype="http://schema.org/Offer">
									<p class="price">
										<h4 style="display: inline; margin-right: 30px; color: #555; vertical-align: 7px;">판매가격</h4>
										<h3 style="display: inline; font-size: 2.5em; font-weight: 1.5; "><fmt:formatNumber value="${saleDto.price_sale}" pattern="#,###원"/></h3>
									</p>
								</div>
								<div style="border-bottom: 1px solid #ccc; margin-top:20px;"></div>
								<div itemprop="description">
									<p>
										<h4 style="display: inline; margin-right: 47px; color: #555; vertical-align: 1px;">배송비</h4>
										<h3 style="display: inline; color: #999; font-weight: 300;"><fmt:formatNumber value="3000" pattern="#,###원"/>(50,000이상 구매시 무료배송)</h3>
									</p>
								</div>
								<div style="border-bottom: 1px solid #ccc; margin-top:20px;"></div>
								<div itemprop="description">
									<p>
										<h4 style="display: inline; margin-right: 47px; color: #555; vertical-align: 1px; vertical-align: -3px;">수량</h4>
										<div class="count" style="display: inline;">
											<input type="number" step="1" min="1" max="" id="count" name="buyList[0].count" value="1" title="Qty" class="input-text qty text" size="4" onchange="updateTotalPrice()" onblur="checkMinQuantity()"/>
										</div>
									</p>
								</div>
								<div style="border-bottom: 2px solid #111; margin-top:20px;"></div>
								<p>
									<h4 style="display: inline; margin-right: 47px; color: #555; vertical-align: 1px;">총상품금액</h4>
									<h3 id="totalPrice" style="display: inline; color: #333; font-weight: 300;"><fmt:formatNumber value="${saleDto.price_sale}" pattern="#,###원"/></h3>
								</p>
									<input type="button" class="single_add_to_cart_button button alt" value="장바구니" onclick="javascript:setBasket();"/>
									<input type="submit" class="single_add_to_cart_button button alt" value="바로구매" />
									<input type="button" class="single_add_to_cart_button button alt" onclick="likeProc(${saleDto.seq_sle});" value="좋아요" />
								
								<div style="border-bottom: 2px solid #333; margin-top:30px;"></div>
							</div>
							<!-- .summary -->
							<div class="woocommerce-tabs wc-tabs-wrapper">
									<div class="panel entry-content wc-tab" id="tab-description">
									<h2>Product Description</h2>
									<p>
										${saleDto.desces}
									</p>
								</div>
								<div class="panel entry-content wc-tab" id="tab-reviews">
								
									<div id="reviews">
										<div id="comments">
											<h2>REVIEW</h2>
											<c:choose>
												<c:when test="${empty list}">
													<td colspan="6">
														등록된리뷰가 없습니다.
													</td>
												</c:when>
											<c:otherwise>
											<c:forEach items="${list}" var="list">
											<ol class="commentlist">
												<li itemprop="review" itemscope itemtype="http://schema.org/Review" class="comment">
												<div id="comment-3" class="comment_container">
														<div class="comment-text">	
														<p class="meta">
															<strong itemprop="author">${list.id}</strong> &ndash; <time itemprop="datePublished" datetime="YYYY-MM-DD">${list.dt_reg}</time>
															&ndash; 
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
														</p>
														<div itemprop="description" class="description">
															<p>
																${list.review}
															</p>
														</div>
													</div>
												</div>
												</li>
											</ol>
												</c:forEach>
												</c:otherwise>
												</c:choose>
												<!-- #comment-## -->
											</ol>
										</div>
										<div class="clear">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</main>
				<!-- #main -->
			</div>
			<!-- #primary -->
		</div>
		<!-- #content -->
	</div>
	<!-- .container -->
	<footer>
		<%@ include file = "/include/footer.jsp" %>
	</footer>
	</div>
	</form>
</body>
</html>