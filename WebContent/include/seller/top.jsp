<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<style>
	li{display:inline;}
</style>
<div id="page">
	<div class="container">
		<div id="masthead" class="site-header">
			<div class="site-branding">
			<ul style="text-align:right; margin: 5px; border-bottom: 1px solid #808080">
				<% 
				// 세션에서 로그인 정보(ID)를 가져옴
				String id = (String) session.getAttribute("ID");
				// 로그인 상태라면 로그아웃 링크 생성
				if (id != null && !id.isEmpty()) { %>
					<li><a href="/seller/product/list.web">[상품]</a></li>
					<li><a href="/seller/sale/list.web">[판매]</a></li>
					<li><a href="/seller/sales/list.web">[매출]</a></li>
					<li><a href="/common/board/list.web?cd_bbs_type=1">[공지사항]</a></li>
					<li><a href="/common/board/list.web?cd_bbs_type=2">[자주찾는 질문]</a></li>
					<li><a href="/common/board/list.web?cd_bbs_type=3">[질의응답]</a></li>
					<li><a href="/seller/login/logout.web">[로그아웃]</a></li>
				<% } else { // 로그인 상태가 아니라면 로그인 링크 생성 %>
						[경고]허가 받지 않은 사용자가 접근할 경우 법적 책임이 발생합니다!
				    <!-- <li><a href="/seller/login/loginForm.web">[로그인]</a></li> --> 
				<% } %>
			</ul>
			<h1 class="site-title"><a href="/seller/product/list.web" rel="home">해피핀핀</a></h1>
			<h2 class="site-description">Happy FinFin</h2>
			</div>
		</div>
	</div>
</div>