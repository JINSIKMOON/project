<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/seller.js"></script>
	<script src="/js/jquery-3.7.1.min.js"></script>
	<script>
		function remove() {
			if (confirm(MSG_ASK_RMV)) {
				var frmMain = document.getElementById("frmMain");
				frmMain.action = "/seller/product/remove.web";
				frmMain.submit();
			}
		}
	</script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" action="/seller/product/modifyForm.web">
	<input type="hidden" name="seq_prd" value="${productDto.seq_prd}" />
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<section class="content">
		<article>
			<table class="type_02" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th width="10%">상품명</th>
					<td>
						${productDto.prd_nm}
					</td>
				</tr>
				<tr>
					<th>상품 설명</th>
					<td>
						${productDto.desces}
					</td>
				</tr>
				<tr>
					<th>상품 원가</th>
					<td>
						<fmt:formatNumber value="${productDto.price_cost}" type="number" />
					</td>
				</tr>
				<tr>
					<th>등록 일시</th>
					<td>
						${productDto.dt_reg}
					</td>
				</tr>
				<tr>
					<th>수정 일시</th>
					<td>
						${productDto.dt_upt}
					</td>
				</tr>
			</table>
			<div style="text-align:center !important; margin: 10px">
				<a href="javascript:remove();" class="btnBasic"> 삭제 </a>
			 	<input type="submit" value="수정" />
			 	<a href="/seller/product/list.web" class="btnBasic"> 목록 </a>
			 </div>
		</article>
		<aside></aside>
	</section>
	</form>
</div>
</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>