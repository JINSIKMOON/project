<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/front.js"></script>
	<!-- <script>
		$(document).ready(function() {
		    // "Add to cart" 버튼 클릭 시 실행되는 함수
		    $(".single_add_to_cart_button").click(function(event) {
		        event.preventDefault(); // 기본 이벤트 동작 방지
		
		        // 선택한 상품 정보 수집
		        var productName = $(".product_title").text().trim(); // 상품명
		        var productPrice = $(".price .amount").text().trim(); // 가격
		        var productImage = $(".images img").attr("src"); // 이미지
		        var productQuantity = $("input[name='quantity']").val(); // 수량
		
		        // AJAX 요청을 통해 장바구니에 상품 추가
		        $.ajax({
		            type: "POST",
		            url: "/front/cart/main.web",
		            data: {
		                productName: productName,
		                productPrice: productPrice,
		                productImage: productImage,
		                productQuantity: productQuantity
		            },
		            success: function(response) {
		                // 장바구니에 상품이 성공적으로 추가되었을 때 처리
		                alert("상품이 장바구니에 추가되었습니다!");
		            },
		            error: function(xhr, status, error) {
		                // AJAX 요청 중 오류가 발생했을 때 처리
		                console.error(error);
		                alert("상품을 장바구니에 추가하는 중 오류가 발생했습니다.");
		            }
		        });
		    });
		});
	 </script> -->
</head>
<body class="single single-product woocommerce woocommerce-page">
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
								<img src="${saledto.img}" alt=""></img></a>
							</div>
							<div class="summary entry-summary">
								<h1 itemprop="name" class="product_title entry-title">${saleDto.sle_nm}</h1>
								<div class="woocommerce-product-rating" itemprop="aggregateRating" itemscope itemtype="http://schema.org/AggregateRating">
									<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i>
									<a href="#reviews" class="woocommerce-review-link" rel="nofollow">(<span itemprop="reviewCount" class="count">2</span> customer reviews)</a>
								</div>
								<div itemprop="offers" itemscope itemtype="http://schema.org/Offer">
									<p class="price">
										<span class="amount">$35.00</span>
									</p>
									<meta itemprop="price" content="35"/>
									<meta itemprop="priceCurrency" content="USD"/>
									<link itemprop="availability" href="http://schema.org/InStock"/>
								</div>
								<div itemprop="description">
									<p>
										Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.
									</p>
								</div>
								<form class="cart" method="post" enctype='multipart/form-data'>
									<div class="quantity">
										<input type="number" step="1" min="1" max="" name="quantity" value="1" title="Qty" class="input-text qty text" size="4"/>
									</div>									
									<button type="submit" class="single_add_to_cart_button button alt">Add to cart</button>
								</form>
								<div class="product_meta">
									<span class="posted_in">Categories: 
									<a href="#" rel="tag">Clothing</a>, 
									<a href="#" rel="tag">Hoodies</a>
									</span>
								</div>
							</div>
							<!-- .summary -->
							<div class="woocommerce-tabs wc-tabs-wrapper">
									<div class="panel entry-content wc-tab" id="tab-description">
									<h2>Product Description</h2>
									<p>
										Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.
									</p>
								</div>
								<div class="panel entry-content wc-tab" id="tab-reviews">
									<div id="reviews">
										<div id="comments">
											<h2>2 Reviews for Beige Jacket</h2>
											<ol class="commentlist">
												<li itemprop="review" itemscope itemtype="http://schema.org/Review" class="comment">
												<div id="comment-3" class="comment_container">
													<img alt='' src='http://0.gravatar.com/avatar/c7cab278a651f438795c2a9ebf02b5ae?s=60&#038;d=mm&#038;r=g' srcset='http://0.gravatar.com/avatar/c7cab278a651f438795c2a9ebf02b5ae?s=120&amp;d=mm&amp;r=g 2x' class='avatar avatar-60 photo' height='60' width='60'/>
													<div class="comment-text">	
														<p class="meta">
															<strong itemprop="author">Steve</strong> &ndash; <time itemprop="datePublished" datetime="2013-06-07T15:54:25+00:00">June 7, 2013</time>:
														</p>
														<div itemprop="description" class="description">
															<p>
																I like the logo but not the color.
															</p>
														</div>
													</div>
												</div>
												</li>
												<!-- #comment-## -->
												<li itemprop="review" itemscope itemtype="http://schema.org/Review" class="comment">
												<div id="comment-4" class="comment_container">
													<img alt='' src='http://2.gravatar.com/avatar/59c82b1d2c60537f900fb191b3cb611b?s=60&#038;d=mm&#038;r=g' srcset='http://2.gravatar.com/avatar/59c82b1d2c60537f900fb191b3cb611b?s=120&amp;d=mm&amp;r=g 2x' class='avatar avatar-60 photo' height='60' width='60'/>
													<div class="comment-text">													
														<p class="meta">
															<strong itemprop="author">Maria</strong> &ndash; <time itemprop="datePublished" datetime="2013-06-07T15:54:25+00:00">June 7, 2013</time>:
														</p>
														<div itemprop="description" class="description">
															<p>
																Three letters, one word: WOO!
															</p>
														</div>
													</div>
												</div>
												</li>
												<!-- #comment-## -->
											</ol>
										</div>
										<div id="review_form_wrapper">
											<div id="review_form">
												<div id="respond" class="comment-respond">
													<h3 style="margin-bottom:10px;" id="reply-title" class="comment-reply-title">Add a review <small><a rel="nofollow" id="cancel-comment-reply-link" href="/demo-moschino/product/woo-logo-2/#respond" style="display:none;">Cancel reply</a></small></h3>
													<form action="#" method="post" id="commentform" class="comment-form" novalidate>
														<p class="comment-form-rating">
															<label for="rating">Your Rating</label>
															<select name="rating" id="rating">
																<option value="">Rate&hellip;</option>
																<option value="5">Perfect</option>
																<option value="4">Good</option>
																<option value="3">Average</option>
																<option value="2">Not that bad</option>
																<option value="1">Very Poor</option>
															</select>
														</p>
														<p class="comment-form-comment">
															<label for="comment">Your Review</label><textarea id="comment" name="comment" cols="45" rows="8" aria-required="true"></textarea>
														</p>
														<p class="comment-form-author">
															<label for="author">Name <span class="required">*</span></label><input id="author" name="author" type="text" value="" size="30" aria-required="true"/>
														</p>
														<p class="comment-form-email">
															<label for="email">Email <span class="required">*</span></label><input id="email" name="email" type="text" value="" size="30" aria-required="true"/>
														</p>
														<p class="form-submit">
															<input name="submit" type="submit" id="submit" class="submit" value="Submit"/><input type='hidden' name='comment_post_ID' value='60' id='comment_post_ID'/>															
														</p>
													</form>
												</div>
												<!-- #respond -->
											</div>
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
	<div>
	<h3>상품 후기</h3>
	<hr/>
	<ul>
		<li>게시물이 없습니다.</li>
	</ul>
	<hr/>
	<a href=""><input  type="button" value="상품후기쓰기"></a>
	<a href=""><input type="button" value="모두보기"></a>
	</div>
	<footer>
		<%@ include file = "/include/footer.jsp" %>
	</footer>
</body>
</html>