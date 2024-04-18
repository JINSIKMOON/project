<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script>
			function addToCart(seq_img, prd_nm, price, count) {
			    // TB_CART_DTL 테이블과 tbody 요소 가져오기
			    var table = document.getElementById("TB_CART_DTL");
			    var tbody = document.getElementById("cartBody");
			
			    // 이미지 경로 가져오기
			    var seq_img = ""; // 테이블의 해당 열 값으로 설정해야 함
			
			    // 새로운 행(row) 추가
			    var newRow = tbody.insertRow();
			
			    // 새로운 셀(cell) 추가
			    var cell1 = newRow.insertCell(0); // 이미지
			    var cell2 = newRow.insertCell(1); // 상품명
			    var cell3 = newRow.insertCell(2); // 가격
			    var cell4 = newRow.insertCell(3); // 개수
			
			    // 셀에 데이터 추가
			    cell1.innerHTML = '<img src="' + seq_img + '" alt="상품 이미지" width="50">';
			    cell2.textContent = prd_nm;
			    cell3.textContent = price;
			    cell4.textContent = count;
			
			    // 장바구니에 추가되었다는 알림 표시
			    alert("장바구니에 추가되었습니다");
			}
			
			// 예시: 버튼 클릭 시 addToCart 함수 호출
			document.getElementById('addToCartButton').addEventListener('click', function() {
			    addToCart('상품명', 1000, 2);
			});
	</script>
</head>
<body>
<div>
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
		<!-- #masthead -->
		<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main" role="main">
				<p class="woocommerce-result-count">
					 Showing 1–8 of 12 results
				</p>
				<form class="woocommerce-ordering" method="get">
					<select name="orderby" class="orderby">
						<option value="menu_order" selected="selected">정렬방식</option>
						<option value="popularity">인기순</option>
						<option value="rating">Sort by average rating</option>
						<option value="date">신상품</option>
						<option value="price">낮은가격</option>
						<option value="price-desc">높은가격</option>
					</select>
				</form>
				<ul class="products">
				
					<li class="first product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Cool Fedora</h3>
					<span class="price"><span class="amount">$34.00</span></span>
					</a><button onclick="addToCart">Add to cart</button></a>
					</li>
					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Beige Blouse</h3>
					<span class="price"><span class="amount">$66.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Black Jacket</h3>
					<span class="price"><span class="amount">$125.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="last product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Brown Jacket</h3>
					<span class="price"><span class="amount">$28.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="first product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Gray Blouse</h3>
					<span class="price"><span class="amount">$15.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Male Bag</h3>
					<span class="price"><span class="amount">$16.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Hugo Jeans</h3>
					<span class="price"><span class="amount">$36.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="last product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Male T-Shirt</h3>
					<span class="price"><span class="amount">$49.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="first product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Cool Fedora</h3>
					<span class="price"><span class="amount">$34.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>

					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Beige Blouse</h3>
					<span class="price"><span class="amount">$66.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Black Jacket</h3>
					<span class="price"><span class="amount">$125.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="last product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Brown Jacket</h3>
					<span class="price"><span class="amount">$28.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="first product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Gray Blouse</h3>
					<span class="price"><span class="amount">$15.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Male Bag</h3>
					<span class="price"><span class="amount">$16.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Hugo Jeans</h3>
					<span class="price"><span class="amount">$36.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="last product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Male T-Shirt</h3>
					<span class="price"><span class="amount">$49.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="first product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Gray Blouse</h3>
					<span class="price"><span class="amount">$15.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Male Bag</h3>
					<span class="price"><span class="amount">$16.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Hugo Jeans</h3>
					<span class="price"><span class="amount">$36.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
					<li class="last product">
					<a href="/front/product/detail.web">
					<img src="https://zoonimal.co.kr/web/product/big/202312/f171a8bbd00e8cce8792625cd07407c1.jpg" alt="">
					<h3>Male T-Shirt</h3>
					<span class="price"><span class="amount">$49.00</span></span>
					</a><a href="/#none" class="button">Add to cart</a>
					</li>
					
				</ul>
				<nav class="woocommerce-pagination">
				<ul class="page-numbers">
					<li><span class="page-numbers current">1</span></li>
					<li><a class="page-numbers" href="#">2</a></li>
					<li><a class="next page-numbers" href="#">→</a></li>
				</ul>
				</nav>
				</main>
				<!-- #main -->
			</div>
			<!-- #primary -->
		</div>
		<!-- #content -->
	</div>
	<footer>
<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>