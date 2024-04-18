<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script type="text/javascript" src="/js/front.js"></script>
	<style>
		li { display:inline; }
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
			font-size: 40px;
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
	</style>
	<script>
	window.onload = function() {
	    var maxItems = 10; // 최대 가져올 항목 수
	    var items = getCookie3nd("recent"); // 쿠키에서 최근 본 상품 정보 가져오기
	    var table = document.getElementById("recent"); // 테이블 가져오기
	    var noRecentMessage = document.getElementById("noRecentMessage"); // 최근 본 상품이 없는 경우 메시지 가져오기
	    
	    if (typeof items !== "undefined" && items !== null && items !== "") {
	        var itemArray = items.split("@@@"); // @@@를 기준으로 상품 정보 분할
	        var itemCount = itemArray.length;

	        // 항목이 최대 항목 수보다 많은 경우
	        if (itemCount > maxItems) {
	            // 오래된 항목 제거 후 최신 항목만 유지
	            itemArray = itemArray.slice(itemCount - maxItems);
	        }

	        // 테이블 초기화
	        table.innerHTML = '';

	        // 상품을 테이블에 추가
	        for (var loop = itemArray.length - 1; loop >= 0; loop--) {
	            var item = itemArray[loop].split("|"); // |를 기준으로 상품 정보 분할
	            var newRow = table.insertRow(); // 새로운 행을 테이블에 추가
		        
	            // 각 셀에 해당하는 정보 삽입
	            var cell1 = newRow.insertCell(0); // 상품명과 링크
	            cell1.innerHTML = '<td><a href="/front/sale/detail.web?seq_sle=' + item[0] + '">' + item[1] + '</a></td>';
	            
	            var cell2 = newRow.insertCell(1); // 이미지
	            cell2.innerHTML = '<td><img class="img-fluid" style="height: 100px" src="' + item[2] + '" alt="" /></td>';
	        }
	    }
	}
	</script>
</head>
<body class="blog">
	<form id="frmMain" method="POST" >
		<header>
		    <%@ include file = "/include/front/top.jsp" %>
		</header>
		<div id="content" class="site-content">
			<%@ include file = "/include/front/myPage/lnb.jsp" %>
			<div id="primary" class="content-area column two-thirds">
				<h1 class="entry-title">최근 본 상품</h1>(최대 10개)<br>
				<table id="recent" class="table" style="width:950px;">
					<tr>
						<th style="text-align: center;">상품명</th>
						<th style="text-align: center;">이미지</th>
					</tr>
					<tr id="noRecentMessage" style="display: none;">
						<td colspan="4">최근 본 상품이 없습니다!</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<footer>
	    <%@ include file = "/include/footer.jsp" %>
	</footer>
</body>
</html>
