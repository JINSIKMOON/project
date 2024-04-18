<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script type="text/javascript" src="/js/front.js"></script>
	<script>
    
    function deleteCookie2nd(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= ; path=/; expires="
				+ expireDate.toGMTString();
	}
	
	window.onload = function() {
		
		var items		= getCookie2nd("productBasket");
		if (typeof items != "undefined" && items != null && items != "") {
			var itemArray	= items.split("@@@");
			
			var totalCount = 0;
			
			var table = document.getElementById("productBasket");
			var cell_length = table.rows[0].cells.length; // 테이블 컬럼 수
			
			if (itemArray.length > 0) table.deleteRow(-1);
			
			for (loop = 0; loop < itemArray.length; loop++) {
				
				var item = itemArray[loop].split("|");
				new_row = table.insertRow();
				
				for(let i = 0; i < cell_length; i++) {
					const new_cell = new_row.insertCell(i);
					let temp_html = '';
					if(i === 0) {
							temp_html = '<td><input type="hidden" name="buyList['+loop+'].seq_sle" value="'+ item[0] + '" />' + (loop + 1) + '</td>';
					} else if(i === 1) {
							temp_html = '<td class="name-pr"><input type="hidden" name="buyList['+loop+'].sle_nm" value="'+ item[1] + '" /><a href="/front/sale/detail.web?seq_sle=' + item[0] + '">' + item[1] + '</a></td>';
					} else if(i === 2) {
							temp_html = '<td class="price-pr"><input type="hidden" name="buyList['+loop+'].price" value="'+ item[2] + '" />' + (item[2].replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")) + '원</td>';
					} else if(i === 3){
							temp_html = '<td class="thumbnail-img"><input type="hidden" name="buyList['+loop+'].img" value="' + item[4] + '" /><a href="/front/sale/detail.web?seq_sle=' + item[0] + '"><img class="img-fluid" style="height: 100px" src="' + item[4] + '" alt="" /></a></td>';
					} else if(i === 4){
							temp_html = '<td><p><input type="hidden" name="buyList['+loop+'].count" value="'+ item[3] + '"/><input type="number" name="count" min="0" step="1" class="c-input-text qty text" value="' + item[3] + '" onchange="updateCart()"></p></td>';
					} else if(i === 5){
							temp_html = '<td><a href="javascript:removeRow('+loop+')"><img style="margin-top:8px; width:20px; heigth:20px;" src="/image/removeRow.png" /></a></td>';
					}
					new_cell.insertAdjacentHTML('afterbegin', temp_html);
				}
				
				totalCount += item[2] * item[3];
			}
			
			document.getElementById("totalCount").innerHTML = totalCount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + "원";
		}
	}
	
	function removeRow(index) {
		
	    var items = getCookie2nd("productBasket");
	    var itemArray = items.split("@@@"); // '@@@'로 각 상품을 나눔
	    var expire = null;
	    
	    if (itemArray.length == 1) {
	        deleteCookie2nd("productBasket");
	    } else {
	        itemArray.splice(index, 1);
	        var updatedItems = itemArray.join("@@@"); // 다시 합쳐서 문자열로 만듦
	        setCookie2nd("productBasket", updatedItems, expire);
	    }
	    
	    location.href = "/front/cart/main.web";
	}
	
	function updateCart() {
		// 변경 하고자 하는 count값 저장
		var arrCount = document.getElementsByName("count");
		var totalCount = 0;
		
		// 기존 장바구니 정보
		var items		= getCookie2nd("productBasket");
		var itemArray	= items.split("@@@");
		
		// 기존 장바구니 삭제
		deleteCookie2nd("productBasket")
		
		// 저장한 값 개수 만큼 루프
		for (i = 0; i < arrCount.length; i++) {
			
			// i 번째 값 선언
			var count = (document.getElementsByName("count")[i].value);
			
			var item = itemArray[i].split("|");
			
			var table = document.getElementById("productBasket");
			new_row = table.insertRow();
			
			totalCount += item[2] * count;
			
			// 갯수 변경
			var item = item[0] + "|" + item[1] + "|" + item[2] + "|" + count + "|" + item[4];
			insertBasket(item);
		}
		
		document.getElementById("totalCount").innerHTML = totalCount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + "원";
	}
	
	function continueShopping() {
        window.location.href = "/front/main/main.web";
    }
	
	</script>
<style>
td, th {
    text-align: center;
    padding: 8px;
    vertical-align: middle; /* 텍스트의 세로 중앙 정렬 */
}
.c-input-text.qty {
    width: 40px; /* 너비 설정 */
    height: 25px; /* 높이 설정 */
    border-radius: 5px; /* 둥근 모서리 설정 */
    text-align: center;
}
</style>
</head>
<body>
<div class="container">
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<br><br><br>
	<section class="content">
		<article>
			<form id="frmMain" method="post" action="/front/buy/orderView.web">
	<div class="cart-box-main">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="table-main table-responsive">
						<table id="productBasket" class="table">
								<tr>
									<th style="text-align: center;">No</th>
									<th style="text-align: center;">상품명</th>
									<th style="text-align: center;">가격</th>
									<th style="text-align: center;">이미지</th>
									<th style="text-align: center;">수량</th>
									<th style="text-align: center;">삭제</th>
								</tr>
								<tr>
						 			<td colspan="6">
										장바구니에 저장된 정보가 없습니다!
									</td>
								</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="row my-5">
					<div class="order-box">
						<hr class="my-1">
						<div class="d-flex gr-total">
							<h5>총 금액</h5>
							<div class="ml-auto h5" id="totalCount"> 0 원</div>
						</div>
						<hr> </div>
			</div>
		</div>
	</div>
			<br/><br/>
		<input type="submit" value="주문하기">
		<input type="button" value="계속 쇼핑하기" onclick="continueShopping()">
		</article>
		<br/><br/><br/>
		<aside></aside>
	</section>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
	</form>
</div>
</body>
</html>