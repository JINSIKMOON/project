<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/front.js"></script>
	<script src="/js/jquery-3.7.1.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		$(function(){
			$("#emailDomainSelect").on("change", function(){
				$("#emailDomainValue").attr("value", $(this).val());
			});
		});
		
		$(function(){
			$("#num1, #num2, #num3").on("keypress", keyEventFnc);
			
			function keyEventFnc(event) {
				
				if (event.key >= 0 && event.key <= 9) {
					return true;
				}
				return false;
			}
		});
		
	</script>
</head>
<body>
<div class="container">
<form id="frmMain" method="POST" action="/front/customer/modifyProc.web">
	<input type="hidden" name="before_flg_sms"		value="${customerDto.flg_sms}" />
	<input type="hidden" name="before_flg_email"	value="${customerDto.flg_user_agreement}" />
	<input type="hidden" name="before_flg_email"	value="${customerDto.flg_personal_data}" />
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<section class="content">
		<nav></nav>
		<article>
			<h1 style="display: block; width: 100%; text-align: center">개인정보변경</h1><br/>
			<div style="margin: 20px; text-align: center">(*) 표시는 필수 입력 사항입니다.</div>
			<table class="type_02" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th style="width:200px;">아이디</th>
					<td>
						${customerDto.id}
					</td>
				</tr>
				<tr>
					<th>성명</th>
					<td>
						${customerDto.cst_nm}
					</td>
				</tr>
				<tr>
					<th>연락처(*)</th>
					<td>
						<input type="text" id="phone" name="phone" value="${customerDto.phone}" />
					</td>
				</tr>
				<tr>
					<th>주소(*)</th>
					<td>
						<input type="text" id="postcode" name="postcode" size="5" value="${customerDto.postcode}" />
						
						<label for="addr1">도로명</label>
						<input type="text"		id="addr1"			name="addr1" size="40" value="${customerDto.addr1}" />
						<input type="hidden"	id="roadAddr"		name="roadAddr" />
						
						<span id="guide" style="color:#999; display:none"></span>
						
						<label for="addr2">상세</label>
						<input type="text"		id="addr2"			name="addr2" size="20" value="${customerDto.addr2}" >
						<input type="hidden"	id="extraAddress"	name="extraAddress" />
						
						<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
					</td>
				</tr>
				<tr>
					<th>이메일(*)</th>
					<td>
						<input type="text" id="cst_email" name="cst_email" value="${customerDto.cst_email}" />
					</td>
				</tr>
				<tr>
					<th>마케팅 수신 동의(*)</th>
					<td>
						SMS <input type="checkbox" name="flg_sms"<c:if test="${customerDto.flg_sms == 'Y'}"> checked</c:if> value="Y" />
					</td>
				</tr>
			</table>
			<div style="text-align: center; margin-bottom: 50px;">
				<input type="submit" value="수정" />
			</div>
		</article>
		<aside></aside>
	</section>
</form>
</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>