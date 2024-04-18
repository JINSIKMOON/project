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
	<script src="https://developers.kakao.com/js/kakao.js"></script>
	<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
	<script> // 아이디저장 스크립트 
		$(document).ready(function(){
			var savedId = getCookie("userId");
			var idInput = $("#id");
			var idSaveCheckbox = $("#idSaveCheckbox");
		
			if (savedId) { 
				idInput.val(savedId);
				idSaveCheckbox.prop("checked", true); 
			}
		
			idSaveCheckbox.change(function(){
				if (idSaveCheckbox.is(":checked")) {
					setCookie("userId", idInput.val(), 30);
				} else {
					deleteCookie("userId");
				}
			});
		
			idInput.keyup(function(){
				if (idSaveCheckbox.is(":checked")) {
					setCookie("userId", idInput.val(), 30);
				}
			});
		});
	
		function setCookie(cookieName, value, exdays) {
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + exdays);
			var cookieValue = encodeURIComponent(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
			document.cookie = cookieName + "=" + cookieValue + "; path=/";
		}
	
		function deleteCookie(cookieName) {
			var expireDate = new Date();
			expireDate.setDate(expireDate.getDate() - 1);
			document.cookie = cookieName + "=; expires=" + expireDate.toUTCString() + "; path=/";
		}
	
		function getCookie(cookieName) {
			cookieName = cookieName + '=';
			var cookieData = document.cookie;
			var start = cookieData.indexOf(cookieName);
			var cookieValue = '';
			if (start !== -1) {
				start += cookieName.length;
				var end = cookieData.indexOf(';', start);
				if (end === -1)
					end = cookieData.length;
				cookieValue = decodeURIComponent(cookieData.substring(start, end));
			}
			return cookieValue;
		}
	
		function setCookie(cookieName, value, exdays) {
			var exdate = new Date();
			exdate.setDate(exdate.getDate() + exdays);
			var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
			document.cookie = cookieName + "=" + cookieValue;
		}
	
		function deleteCookie(cookieName) {
			var expireDate = new Date();
			expireDate.setDate(expireDate.getDate() - 1);
			document.cookie = cookieName + "=; expires=" + expireDate.toGMTString();
		}
	
		function getCookie(cookieName) {
			cookieName = cookieName + '=';
			var cookieData = document.cookie;
			var start = cookieData.indexOf(cookieName);
			var cookieValue = '';
			if (start !== -1) {
				start += cookieName.length;
				var end = cookieData.indexOf(';', start);
				if (end === -1)
					end = cookieData.length;
				cookieValue = cookieData.substring(start, end);
			}
			return unescape(cookieValue);
		}
	</script>
	<script> //네이버 로그인
		var naverLogin = new naver.LoginWithNaverId(
				{
					clientId: "K6ndNhHkpQIM2IuV52wZ",
					callbackUrl: "http://127.0.0.1:8080/front/login/callback.web",
					isPopup: false,
					callbackHandle: true
					/* callback 페이지가 분리되었을 경우에 callback 페이지에서는 callback처리를 해줄수 있도록 설정합니다. */
				}
			);
	
			/* (3) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
			naverLogin.init();
	
			/* (4) Callback의 처리. 정상적으로 Callback 처리가 완료될 경우 main page로 redirect(또는 Popup close) */
			window.addEventListener('load', function () {
				naverLogin.getLoginStatus(function (status) {
					if (status) {
						/* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
						var email = naverLogin.user.getEmail();
						console.log(email); // 사용자 이메일 정보를 받을수 있습니다.
						console.log(naverLogin.user); //사용자 정보를 받을수 있습니다.
						if( email == undefined || email == null) {
							alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
							/* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
							naverLogin.reprompt();
							return;
						}
	
						window.location.replace("http://" + window.location.hostname + ( (location.port === null) ? "" : ":" + location.port) + "/front/login/callback.web");
					
					}
					else {
						console.log("callback 처리에 실패하였습니다.");
					}
				});
			});
	
		var testPopUp;
		function openPopUp() {
		    testPopUp= window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
		}
		function closePopUp(){
		    testPopUp.close();
		}
		
		function naverLogout() {
			openPopUp();
			setTimeout(function() {
				closePopUp();
				}, 1000);
			
			
		}

	</script>
	<!-- 카카오 스크립트 -->
	<script>
		Kakao.init('a4d215bfc0e33f94d284f81cf4ce9b9c'); //발급받은 키 중 javascript키를 사용해준다.
		console.log(Kakao.isInitialized()); // sdk초기화여부판단
		//카카오로그인
		function kakaoLogin() {
			Kakao.Auth.login({
				success: function (response) {
				Kakao.API.request({
				url: '/v2/user/me',
				success: function (response) {
					console.log(response)
				},
				fail: function (error) {
					console.log(error)
				},
				})
			},
				fail: function (error) {
					console.log(error)
			},
			})
		}
		//카카오로그아웃  
		function kakaoLogout() {
			if (Kakao.Auth.getAccessToken()) {
				Kakao.API.request({
				url: '/v1/user/unlink',
				success: function (response) {
					console.log(response)
				},
				fail: function (error) {
					console.log(error)
				},
			})
				Kakao.Auth.setAccessToken(undefined)
			}
		}
	</script>
	<script>
		//카카오 api 키 등록
		Kakao.init('a4d215bfc0e33f94d284f81cf4ce9b9c');
		function kakao_login() {
			Kakao.Auth.login({
				success: function(authObj) {
					$.ajax({
						type: 'POST',
						url: `/login/kakao`,
						contentType: "application/json",
						data: JSON.stringify({'token':authObj['access_token']}),
						success: function (response) {
							localStorage.setItem("token", response['token']);
							localStorage.setItem("username", response['username']);
							location.href = '/';
						}
					})
				},
				fail: function(err) {
					alert(JSON.stringify(err))
				},
			})
		}
	</script>
</head>
<body class="home page page-template page-template-template-portfolio page-template-template-portfolio-php">
<div id="page">
	</div>
	<div class="container">
<form id="frmMain" method="POST" encType="UTF-8">
<input type="hidden" id="checkIdResult" value="true" />
	<header>
		<%@ include file="/include/front/top.jsp" %>
	</header>
	<br><br><br>
	<section class="content">
		<nav></nav>
		<article>
	<table class="type_02" style=" width: 300px; margin-left: auto; margin-right: auto;" >
					<tr>
						<th>아이디</th>
						<td><input id="id" name="id" type="text" style="ime-mode: disabled;" required maxlength="16" autocomplete="off" tabindex="1" /></td>
					</tr>
					<tr>
						<th>암호</th>
						<td><input id="passwd" name="passwd" type="password" style="ime-mode: disabled;" required maxlength="16" autocomplete="off" tabindex="2" onkeydown="if (event.keyCode==13) checkLogin();"/></td>
					</tr>
				</table>
				<div style="width: 500px; margin: 0 auto; text-align: center;">
				<input type="checkbox" id="idSaveCheckbox" name="idSave">
				<label for="idSaveCheckbox">아이디 저장</label><br>
				<input type="button" tabindex="3" value="로그인" onclick="javascript:checkLogin();"/>
				<a href="/front/customer/agreeForm.web" class="btnBasic"> 회원가입 </a>
				<br/><br/>
				<ul style="width: 500px; margin: 0 auto; text-align: center;">
					<li>
						<!--
						<a href="/front/login/findId.web">아이디찾기</a>
						<a href="/front/login/findPw.web">비밀번호찾기</a>
						-->
						<a href="/front/customer/findId.web">아이디/비밀번호 찾기</a>
					</li>
				</ul>
				<br>
				<div class="snsArea">
						<a href="/front/login/naverloginForm.web"><img src="https://img.echosting.cafe24.com/skin/base_ko_KR/member/btn_naver_login.gif" alt="네이버 로그인" id="naverIdLogin_loginButton"/></a>
						<a href="javascript:kakaoLogin();"><img src="https://img.echosting.cafe24.com/skin/base_ko_KR/member/btn_kakao_login.gif" width ="176" height = "30"></a>
						<ul>
							<li onclick="kakaoLogout();">
							<a href="javascript:void(0)">
								<span>네이버 로그아웃</span>
							</a>
							</li>
							<a href="javascript:void(0)">
								<span>카카오 로그아웃</span>
							</a>
						</ul>
				 </div>
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