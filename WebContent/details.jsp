<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/front.js"></script>
	<script>
	
	// 최소 주문 수량 확인
	function checkMinQuantity() {
		var productCount = parseInt(document.getElementsByName("count")[0].value); // input 요소에서 상품 개수 가져오기
		if (productCount < 1) {
			alert("최소 주문 수량은 1개 입니다.");
			document.getElementsByName("count")[0].value = 1; // 최소 수량인 1로 변경
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
		var productCount = parseInt(document.getElementsByName("count")[0].value); // input 요소에서 상품 개수 가져오기
		var totalPrice = calculatePrice(productCount);
		document.getElementById("totalPrice").innerText = totalPrice.toLocaleString() + "원"; // 새로운 가격으로 업데이트
	}

</script>
</head>
<body class="single single-product woocommerce woocommerce-page">
<form id="frmMain" method="POST" action="/front/buy/orderView.web">
	
	
	<input type="text" name="buyList[0].seq_sle"	id="seq_sle"		value="1" />
	<input type="text" name="buyList[0].sle_nm"	id="sle_nm"			value="상품명-1" />
	<input type="number" name="buyList[0].count" 	value="1" />
	
	<input type="text" name="buyList[1].seq_sle"	id="seq_sle"		value="10" />
	<input type="text" name="buyList[1].sle_nm"	id="sle_nm"			value="상품명-10" />
	<input type="number" name="buyList[1].count" 	value="3" />
	
	
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
						<nav class="woocommerce-breadcrumb" itemprop="breadcrumb"><a href="#">Home</a> / <a href="#">Clothing</a> / Beige Jacket</nav>
						<div itemscope itemtype="http://schema.org/Product" class="product">
							<div class="images">
								<a href="" itemprop="image" class="woocommerce-main-image zoom" title="" data-rel="prettyPhoto">								
								<img src="${saleDto.img}" alt=""></img></a>
							</div>
							<div style="border-bottom: 2px solid #111; margin-top: 20px; width: 577px; margin-left: auto;"></div>
							<div class="summary entry-summary">
								<h2>${saleDto.sle_nm}</h2>
								<div class="woocommerce-product-rating" itemprop="aggregateRating" itemscope itemtype="http://schema.org/AggregateRating">
									<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i>
									<a href="#reviews" class="woocommerce-review-link" rel="nofollow">(<span itemprop="reviewCount" class="count">2</span> customer reviews)</a>
								</div>
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
											
										</div>
									</p>
								</div>
								<div style="border-bottom: 2px solid #111; margin-top:20px;"></div>
								<p>
									<h4 style="display: inline; margin-right: 47px; color: #555; vertical-align: 1px;">총상품금액</h4>
									<h3 id="totalPrice" style="display: inline; color: #333; font-weight: 300;"><fmt:formatNumber value="${saleDto.price_sale}" pattern="#,###원"/></h3>
								</p>
								<form class="cart" method="post" enctype='multipart/form-data'>								
									<a href="/front/cart/main.web?seq_sle=${saleDto.seq_sle}">
									<input id="setCartBtn" class="single_add_to_cart_button button alt" type="button" value="장바구니 담기" />
									</a><input type="submit" class="single_add_to_cart_button button alt" value="바로구매" />
									<button type="submit" class="single_add_to_cart_button button alt">좋아요</button>
								</form>
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
											<ol class="commentlist">
												<li itemprop="review" itemscope itemtype="http://schema.org/Review" class="comment">
												<div id="comment-3" class="comment_container">
														<div class="comment-text">	
														<p class="meta">
															<strong itemprop="author">${sale_evalDto.seq_cst}</strong> &ndash; <time itemprop="datePublished" datetime="2013-06-07T15:54:25+00:00">${sale_evalDto.dt_reg}</time>
														</p>
														<div itemprop="description" class="description">
															<p>
																${sale_evalDto.review}
															</p>
														</div>
													</div>
												</div>
												</li>
<<<<<<< HEAD
												<!-- #comment-## -->
=======
>>>>>>> branch 'main' of http://192.168.0.57/all/kr.co.himedia.sn.ecommerce5th.venus.git
											</ol>
										</div>
										<div class="clear">
										</div>
									</div>
								</div>
							</div>
							<div class="related products">
								<h2>Related Products</h2>
								<ul class="products">
									<li class="first product">
									<a href="shop-single.jsp">
									<span class="onsale">Sale!</span>									
									<img src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/09/10175658/j4-520x520.jpg" alt=""></img>
									<h3>Beige Fedora</h3>
									<span class="price"><del><span class="amount">$35.00</span></del><ins><span class="amount">&#36;14.00</span></ins></span>
									</a>
									<a href="#" class="button">Add to cart</a>
									</li>
									
									<li class="product">
									<a href="shop-single.jsp">								
									<img src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/09/10175658/j6-520x779-520x600.jpg" alt=""></img>
									<h3>Male Jeans</h3>
									<span class="price"><span class="amount">$35.00</span></span>
									</a>
									<a href="#" class="button">Add to cart</a>
									</li>
									
									<li class="product">
									<a href="shop-single.jsp">								
									<img src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/09/10175658/j7-520x780-520x600.jpg" alt=""></img>
									<h3>Slim Jeans</h3>
									<span class="price"><span class="amount">$20.00</span></span>
									</a>
									<a href="#" class="button">Add to cart</a>
									</li>
									
									
									<li class="product last">
									<a href="shop-single.jsp">									
									<img src="http://s3.amazonaws.com/caymandemo/wp-content/uploads/sites/10/2015/09/10175658/j3-320x320.jpg" alt=""></img>
									<h3>Summer Blouse</h3>
									<span class="price"><span class="amount">$35.00</span></span>
									</a>
									<a href="#" class="button">Add to cart</a>
									</li>
								</ul>
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