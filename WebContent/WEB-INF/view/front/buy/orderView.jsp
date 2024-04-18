<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/front/buy/writeForm.jsp" %>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"		uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script src='/js/toggle.js'></script>
	<script type="text/javascript">
		function addTotalPriceToFormData() {
			var totalPrice = document.getElementById("totalPrice").innerText;
			document.getElementById("totalPriceInput").value = totalPrice; // hidden input에 totalPrice 값을 설정
		}
		<c:if test="${flgMobile == 'Y'}">
			$(document).ready(function() {
				$("#btnPay").on("click", function() {
					addTotalPriceToFormData();
					$.ajax({
						url: "/front/payup/orderProcess.json",
						type: "POST",
						dataType: "json",
						data: $("#frmMain").serialize(),
						success: function(data) {
							$("input[name=ordr_idxx]").val(data.ordr_idxx);
							$("input[name=good_name]").val(data.good_name);
							$("input[name=good_mny]").val(data.good_mny);
							$("input[name=buyr_name]").val(data.buyr_name);
							$("input[name=site_cd]").val(data.site_cd);
							
							//alert(data.pay_url);
							// $("form[name=form]").action.val(data.pay_url);
							//alert(data.Ret_URL);
							$("input[name=Ret_URL]").val(data.Ret_URL);
							//alert(data.approval_key);
							$("input[name=approval_key]").val(data.approval_key);
							//alert(data.AppUrl);
							$("input[name=AppUrl]").val(data.AppUrl);
							
							document.form.submit();
							//alert("submit");
						},
						error: function(data) {
							alert(MSG_ERR_PAY);
						}
					});
				});
			});
		</c:if>
		
		<c:if test="${flgMobile != 'Y'}">
			$(document).ready(function() {
				$("#btnPay").on("click", function() {
				//$("#btn_pay").on("click", function() {
					// addTotalPriceToFormData();
					$.ajax({
						url: "/front/payup/orderProcess.json",
						type: "POST",
						dataType: "json",
						data: $("#frmMain").serialize(),
						//data: $("#payForm").serialize(),
						success: function(data) {
							//console.log(data);
							$("input[name=ordr_idxx]").val(data.ordr_idxx);
							$("input[name=good_name]").val(data.good_name);
							$("input[name=good_mny]").val(data.good_mny);
							$("input[name=buyr_name]").val(data.buyr_name);
							$("input[name=site_cd]").val(data.site_cd);
							
							jsf__pay();
						},
						error: function(data) {
							alert(MSG_ERR_PAY);
						}
					});
				});
			});
		</c:if>
		
		/*
		[2025-02-08][pluto@himedia.co.kr][DELETE: 결제 연동으로 이전]
		function writeProc() {
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/front/buy/writeProc.web";
			frmMain.submit();
		}
		*/
	</script>

</head>
<body>
<form id="frmMain" method="POST">
<%
/*
	<input type="hidden" name="seq_sle"		id="seq_sle"			value="${saleDto.seq_sle}" />
	<input type="hidden" name="seq_prd"		id="seq_prd"			value="${saleDto.seq_prd}" />
	<input type="hidden" name="sle_nm"		id="sle_nm"				value="${saleDto.seq_sle}" />
	<input type="hidden" name="img"			id="_img"				value="${saleDto.img}" />
	<input type="hidden" name="price"		id="price"				value="${saleDto.price_sale}" />
	<input type="hidden" name="flgMobile"	id="flgMobile"			value="${flgMobile}" />
	<input type="hidden" name="totalPrice"	id="totalPriceInput"  />
	
*/
%>
	<div class="container">
		<header>
			<%@ include file="/include/front/top.jsp" %>
		</header>
		<br>
		<section class="content">
			<nav></nav>
			<article id="post-37" class="post-37 page type-page status-publish hentry">
			<div id="titleArea" class="titleArea">
				주문/결제
			</div>
					<div class="wpcmsdev-toggle">
						<h3 class="toggle-title"><a href="#toggle-what-is-lore-ipsum"><i class="fa fa-plus icon-for-inactive"></i><i class="fa fa-minus icon-for-active"></i>주문정보</a></h3>
						<div id="toggle-what-is-lore-ipsum" class="toggle-content">
							<table class="type_02" style="margin-left: auto; margin-right: auto;">
					<tr>
						<th>이름</th>
						<td>
							${customerDto.cst_nm}
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							${customerDto.phone}
						</td>
					</tr>
					<tr>
						<th>배송지 정보</th>
						<td>
						우편번호 : ${customerDto.postcode}
						<br>
						<label for="addr1">도로명 주소 : </label>
						${customerDto.addr1}
						<input type="hidden"	id="roadAddr"		name="roadAddr" />
						<br>
						<span id="guide" style="color:#999; display:none"></span>
						
						<label for="addr2">상세 주소 : </label>
						${customerDto.addr2}
						<input type="hidden"	id="extraAddress"	name="extraAddress" />
						
						
					</td>
					</tr>
					<tr>
					</tr>
					</table>
						</div>
						</div>
				<div class="wpcmsdev-toggle">
						<h3 class="toggle-title"><a href="#toggle-what-is-lore-ipsum"><i class="fa fa-plus icon-for-inactive"></i><i class="fa fa-minus icon-for-active"></i>상품정보</a></h3>
						<div id="toggle-what-is-lore-ipsum" class="toggle-content">
						<table class="type_01" style="margin-left: auto; margin-right: auto;">
							<thead>
								<tr>
									<th style="width: 5%">NO</th>
									<th>판매 이미지</th>
									<th>판매명</th>
									<th>가격</th>
									<th>수량</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty listBuyDtlDto}">
										<tr>
											<td colspan="3">선택된 상품이 없습니다!</td>
										</tr>
									</c:when>
									<c:otherwise>
									<c:forEach items="${listBuyDtlDto}" var="list" varStatus="status">
										<tr>
											<td>
												${status.count}
												<input type="hidden" name="buyList[<c:out value="${status.index}"/>].seq_sle" value="${list.seq_sle}" />
											</td>
											<td>
												<a href="" itemprop="image" class="woocommerce-main-image zoom" title="" data-rel="prettyPhoto">
												<img src="${list.img}" alt="" width="80" height="80"></a>
											</td>
											<td>
												${list.sle_nm}
												<input type="hidden" name="buyList[<c:out value="${status.index}"/>].sle_nm" value="${list.sle_nm}" />
											</td>
											<td>
												<a id="price"><fmt:formatNumber value="${list.price}" pattern="#,###원"/></a>
												<input type="hidden" name="buyList[<c:out value="${status.index}"/>].price" value="${list.price}" />
											</td>
											<td>
												${list.count}
												<input type="hidden" name="buyList[<c:out value="${status.index}"/>].count" id="count" size="3" value="${list.count}">
											</td>
										</tr>
										<c:set var="subtotal" value="${list.price * list.count}" />
										<c:set var="totalPrice" value="${totalPrice + subtotal}" />
									</c:forEach>
								<tr>
									<td colspan="3">
										결제할 가격 (상품가격 + 배송비)
									</td>
									<td>
									<c:choose>
										<c:when test="${totalPrice <= 50000}">
										<c:set var="totalPrice" value="${totalPrice + 3000}" />
										</c:when>
									</c:choose>
										<h3 id="totalPrice" style="display: inline; color: #333; font-weight: 300;"><fmt:formatNumber value="${totalPrice}" pattern="#,###원"/></h3>
									</td>
								</tr>
								</c:otherwise>
								</c:choose>
							</tbody>
						</table>
				</div>
				</div>
				<ul style="width: 500px; margin: 0 auto; text-align: center; margin-bottom: 10px;">
					<li><a href="/front/main/main.web">취소</a></li>
					<li><input id="btnPay" type="button" value="결제하기" /></li>
				</ul>
			</article>
			<div class="clearfix">	</div>
			<aside></aside>
		</section>
	</div>
</form>
<footer>
	<%@ include file="/include/footer.jsp" %>
</footer>
<c:if test="${flgMobile == 'Y'}">
	<script type="text/javascript" src="https://testpay.kcp.co.kr/plugin/payplus_web.jsp"></script>
	<form name="form" action="https://testsmpay.kcp.co.kr/pay/mobileGW.kcp" method="post" accept-charset="utf-8">
		<input type="hidden" name="ordr_idxx" value="">
		<input type="hidden" name="good_name" value="">
		<input type="hidden" name="good_mny" value="">
		<input type="hidden" name="buyr_name" value="">
		<input type="hidden" name="site_cd" value="">
		
		<input type="hidden" name="Ret_URL" value="">
		<input type="hidden" name="approval_key" value="">
		
		<input type="hidden" name="req_tx" value="pay">
		<input type="hidden" name="pay_method" value="CARD">
		<input type="hidden" name="currency" value="410">
		<input type="hidden" name="escw_used" value="N">
		<input type="hidden" name="AppUrl" value="testApp://testURL">
	</form>
</c:if>

<c:if test="${flgMobile != 'Y'}">
	<script type="text/javascript">
		function m_Completepayment(FormOrJson, closeEvent) {
			var frm = document.kcp_order_info;
			GetField(frm, FormOrJson);
			//console.log(frm);
			if (frm.res_cd.value == "0000") {
				frm.action = "/front/payup/payProcess.web";
				frm.submit();
				closeEvent();
			}
			else {
				alert("[" + frm.res_cd.value + "] " + frm.res_msg.value);
				closeEvent();
			}
		}
		
		function jsf__pay() {
			try {
				var form = document.kcp_order_info;
				KCP_Pay_Execute(form);
			}
			catch (e) {	}
		}
	</script>
	<script type="text/javascript" src="https://testpay.kcp.co.kr/plugin/payplus_web.jsp"></script>
	<form name="kcp_order_info" method="post" accept-charset="utf-8">
		<input type="hidden" name="ordr_idxx" value="">
		<input type="hidden" name="good_name" value="">
		<input type="hidden" name="good_mny" value="">
		<input type="hidden" name="buyr_name" value="">
		<input type="hidden" name="site_cd" value="">
		
		<input type="hidden" name="req_tx" value="pay">
		<input type="hidden" name="pay_method" value="100000000000">
		<input type="hidden" name="site_name" value="payup" />
		<!-- <input type="hidden" name="kakaopay_direct" value="Y"> -->
		
		<input type="hidden" name="res_cd" value="" />
		<input type="hidden" name="res_msg" value="" />
		<input type="hidden" name="enc_info" value="" />
		<input type="hidden" name="enc_data" value="" />
		<input type="hidden" name="ret_pay_method" value="" />
		<input type="hidden" name="tran_cd" value="" />
		<input type="hidden" name="use_pay_method" value="" />
		<input type="hidden" name="buyr_mail" value="">
		<input type="hidden" name="ordr_chk" value="" />
		<input type="hidden" name="good_expr" value="0">
		<input type="hidden" name="module_type" value="01" />
		<input type="hidden" name="currency" value="WON" />
	</form>
</c:if>
</body>
</html>