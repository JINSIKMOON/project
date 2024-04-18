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
			
			$(function() {
				$("#checkId").click(function() {
					
					var value = document.getElementById("id").value;
					
					if (value == null || value == "") {
						alert(MSG_INP_ID);
						return;
					}
					
					var pattern = /^[a-zA-Z0-9]{8,16}$/;
					
					if (!pattern.test(value)) {
						alert(MSG_ERR_MIX);
						return;
					}
					
					$.ajax({
						type:"post",
						async:false, 
						url:"/front/customer/exist.json",
						// url:"/front/customer/listing.json",
						contentType: "application/json; charset=utf-8",
						data:"{\"id\":\"" + value + "\"}",
						success:function(data, textStatus) {
							//alert(data);
							if (data == true) {
								alert(MSG_ERR_ID);
							}
							else {
								alert(MSG_SCC_ID);
								document.getElementById("checkIdResult").value = "false";
							}
						},
		 				error:function(data, textStatus) {
							alert(MSG_ERR_COMMON);ㅣ
						}
					});	
				});
			});
	</script>
</head>
<body class="home page page-template page-template-template-portfolio page-template-template-portfolio-php">
<form id="frmMain" method="POST" encType="UTF-8">
<input type="hidden" id="checkIdResult" value="true" />
<div id="page">
	<div class="container">
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<br>
	<section class="content">
		<nav></nav>
		<article>
			<div style="margin: 20px; text-align: center">(*) 표시는 필수 입력 사항입니다.</div>
			<table class="type_02" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th style="width:200px;">아이디(<span style="color: red;">*</span>)</th>
					<td>
						<input type="text" id="id" name="id" oninput="validateId()" /> <input type="button" value="중복 확인" id="checkId">
						 <div id="idMessage" class="error-message"></div>
					</td>
				</tr>
				<tr>
				<!-- input type password로 변경해야함 -->
					<th>비밀번호(<span style="color: red;">*</span>)</th>
					<td>
						<input type="password" id="passwd" name="passwd" oninput="validatePassword()" placeholder="비밀번호">
						<div id="result"></div>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인(<span style="color: red;">*</span>)</th>
				<td>
					<input type="password" id="pw2" oninput="validatePassword()" placeholder="비밀번호를 한번 더 입력해주세요.">
					<div id="result2"></div>
				</td>
				</tr>
					<tr>
					<th>이름(<span style="color: red;">*</span>)</th>
					<td>
						<input type="text" id="cst_nm" name="cst_nm" />
					</td>
				</tr>
				<tr>
					<th>휴대전화(<span style="color: red;">*</span>)</th>
					<td>
						<input type="text" id="phone" name="phone" />
					</td>
				</tr>
				<tr>
					<th>주소(<span style="color: red;">*</span>)</th>
					<td>
						<input type="text" id="postcode" name="postcode" size="5" />
						
						<label for="addr1">도로명</label>
						<input type="text"		id="addr1"			name="addr1" size="40" />
						<input type="hidden"	id="roadAddr"		name="roadAddr" />
						
						<span id="guide" style="color:#999; display:none"></span>
						
						<label for="addr2">상세</label>
						<input type="text"		id="addr2"			name="addr2" size="20" placeholder="상세 주소" >
						<input type="hidden"	id="extraAddress"	name="extraAddress" />
						
						<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
					</td>
				</tr>
				<tr>
					<th>이메일(<span style="color: red;">*</span>)</th>
					<td>
						<input type="text" id="cst_email" name="cst_email" />
					</td>
				</tr>
				<tr>
					<th>SMS 수신 여부(*)</th>
					<td>
						SMS <input type="checkbox" name="flg_sms" value="Y" />
					</td>
				</tr>
			</table>
			<ul style="width: 500px; margin: 0 auto; text-align: center; margin-bottom: 10px;">
			<a href="/index.jsp">취소</a>
			<input type="button" value="회원가입" onclick="javascript: checkRegister();" />
			</ul>
		</article>
		<aside></aside>
	</section>
		</div>
	</div>
	</form>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>