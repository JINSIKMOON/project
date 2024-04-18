<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<style>
	
/* 미디어 쿼리 */
@media screen and (max-width: 768px) {
    .products {
        grid-template-columns: repeat(auto-fill, minmax(45%, 1fr)); /* 화면이 좁아질 때 그리드 아이템의 크기를 설정 */
    }
}

.products {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); /* 그리드 아이템의 크기를 설정 */
    grid-template-rows: auto auto auto auto;
    grid-column-gap: 8%; /* 적당한 간격으로 조절 */
    margin: 0;
}
		.thumbnail {
			background-color: gray;
			margin: 10px;
			width: 230px;
			height: 230px;
			background-size: cover;
			background-repeat: no-repeat;
			background-position: center center
		}
		
		.thumbnail.round {
			border-radius: 10%
		}
		
		.thumbnail.circle {
			border-radius: 100%
		}
	</style>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/front.js"></script>	
	<script src="/js/jquery-3.7.1.min.js"></script>
	<script>		
		function goPage(value) {
		
			var frmMain = document.getElementById("frmMain");
		
			frmMain.currentPage.setAttribute("value", value);
			frmMain.action = "/front/sale/list.web?ctg_no=${paging.ctg_no}";
			frmMain.submit();
		}
		
	function onSubmit() {
			var frmMain = document.getElementById("frmMain");
			frmMain.submit();
		}
	function setBasket(index) {
	    var seq_sle = document.getElementById("seq_sle_" + index).value;
	    var sle_nm = document.getElementById("sle_nm_" + index).value;
	    var price = document.getElementById("price_" + index).value;
	    var count = document.getElementById("count_" + index).value;
	    var img = document.getElementById("img_" + index).src;
	    
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
	

    function setRecent(index) {
        var seq_sle = document.getElementById("seq_sle_" + index).value;
        var sle_nm = document.getElementById("sle_nm_" + index).value;
        var img = document.getElementById("img_" + index).src;
        
        var item = seq_sle + "|" + sle_nm + "|" + img;
        // alert(item);
        insertRecent(item);
        
    }
</script>

</head>
<body>
<form id="frmMain" method="POST" encType="UTF-8">
	<input type="hidden" id="setCart"		value="true" />
	<input type="hidden" name="flgMobile"	id="flgMobile"				value="${flgMobile}" />
	<input type="hidden" name="currentPage" 							value="${paging.currentPage}" />
<%-- 	<input type="hidden" name="buyList[0].seq_sle"		id="seq_sle"	value="${saleDto.seq_sle}" />
	<input type="hidden" name="buyList[0].sle_nm"		id="sle_nm"		value="${saleDto.sle_nm}" />
	<input type="hidden" name="price"					id="price"		value="${saleDto.price_sale}" />
	<input type="hidden" name="seq_prd"					id="seq_prd"	value="${saleDto.seq_prd}" />
	 --%>
<div class="container">
		
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<section class="content">
		<article>
			<div>
			 	<select name="searchKey">
					<option value="sle_nm"<c:if test="${saleDto.searchKey == 'sle_nm'}"> selected</c:if>>판매명</option>
					<option value="sle_nm+desces"<c:if test="${saleDto.searchKey eq 'sle_nm+desces'}"> selected</c:if>>판매명 또는 설명</option>
				</select> 
				<input type="text" name="searchWord" value="${paging.searchWord}" /> <input type="submit" value="검색"/>
			</div>
			<div id="content" class="site-content">
			<div id="primary" class="content-area column full">
				<main id="main" class="site-main" role="main">
				<p class="woocommerce-result-count">
					 Showing 1–8 of 12 results
				</p>
					<select name="searchOption" onchange="onSubmit();">
						<!-- <option value="menu_order">정렬방식</option>
						<option value="popularity">인기순</option> -->
						<option value="dt_reg desc"<c:if test="${pagingDto.searchOption == 'dt_reg desc'}"> selected="selected"</c:if>>등록순</option>
						<option value="price_sale asc"<c:if test="${pagingDto.searchOption == 'price_sale asc'}"> selected="selected"</c:if>>낮은가격순</option>
						<option value="price_sale desc"<c:if test="${pagingDto.searchOption == 'price_sale desc'}"> selected="selected"</c:if>>높은가격순</option>
					</select>
				<ul class="products">
				<c:choose>
					<c:when test="${empty list}">
						<td colspan="6">
							등록된상품이 없습니다.
							<%--  <c:if test="${saleDto.searchWord == '' || saleDto.searchWord == null}">검색어를 입력하세요!</c:if>
							<c:if test="${saleDto.searchWord != '' && saleDto.searchWord != null}">검색된 상품이 없습니다!</c:if>  --%>
						</td>
					</c:when>
				<c:otherwise>
				<c:forEach items="${list}" var="list" varStatus="status">
					<li class="first product">
						<a href="/front/sale/detail.web?seq_sle=${list.seq_sle}" id="list_img" onclick="javascript:setRecent(${status.index});">
							<div class="thumbnail round" style="background-image:url('/image/sale${list.img}')"></div>
							<%-- <input id="seq_sle" type="hidden" value="${list.seq_sle}" />
							<div style="display: none;">
							<img id="img" src="${list.img}">
							</div>
							<input id="price" type="hidden" value="${list.price_sale}" />
							<input id="count" type="hidden" value="1" />
							<input id="sle_nm" type="hidden" value="${list.sle_nm}" /> --%>
							<input id="seq_sle_${status.index}" type="hidden" value="${list.seq_sle}" />
							<div style="display: none;">
							<img id="img_${status.index}" src="/image/sale${list.img}">
							</div>
							<input id="price_${status.index}" type="hidden" value="${list.price_sale}" />
							<input id="count_${status.index}" type="hidden" value="1" />
							<input id="sle_nm_${status.index}" type="hidden" value="${list.sle_nm}" />
								<h5>${list.sle_nm}</h5>
								<fmt:formatNumber value="${list.price_sale}" pattern="#,###원"/>
						</a>
						<input type="button" value="장바구니" onclick="javascript:setBasket(${status.index});"/>
					</li>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</ul>
				</main>
				<!-- #main -->
			</div>
			<!-- #primary -->
		</div>
		<div style="text-align:center !important; margin: 10px">
		<plutozoneUtilTag:page styleID="admin_image" currentPage="${paging.currentPage}" linePerPage="${paging.linePerPage}" totalLine="${paging.totalLine}" scriptFunction="goPage" />
		</div>
	</article>
	</section>
	</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
	</form>
</body>
</html>