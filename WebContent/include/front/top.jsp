<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<div id="page">
	<div class="container">
		<div id="masthead" class="site-header">
		<div class="site-branding">
			<ul style="text-align:right;">
				<% 
				// 세션에서 로그인 정보(ID)를 가져옴
				String id = (String) session.getAttribute("ID");
				
				// 로그인 상태라면 로그아웃 링크 생성
				if (id != null && !id.isEmpty()) { %>
				       <li><a href="/front/login/logout.web"><img src="/image/logout.jpg" width="25" height="25"></a></li>
				<% } else { // 로그인 상태가 아니라면 로그인 링크 생성 %>
				    <li><a href="/front/login/loginForm.web"><img src="/image/login.jpg" width="25" height="25"></a></li>
				<% } %>
				<li><a href="/front/myPage/main.web"><img src="/image/mypage.jpg" width="30" height="30"></a></li>
				<li><a href="/front/cart/main.web"><img src="/image/cart.jpg" width="30" height="30"></a></li>
			</ul>
			<h1 class="site-title"><a href="/front/main/main.web" rel="home"><img src="/image/logo.jpg" width="100" height="100"><br><div style="margin-bottom:5px; margin-top:4px;font-size:45px;">해피핀핀</div></a></h1>
		</div>
		<nav id="site-navigation" class="main-navigation">
		<button class="menu-toggle">Menu</button>
		<%-- <a class="skip-link screen-reader-text" href="#content">Skip to content</a>
		[...............${menus}...................] --%>
					<div class="menu-menu-1-container">
			<ul id="menu-menu-1" class="menu">
				<li><a href="/front/sale/list.web?ctg_no=101">수초 스케이핑/테라리움</a>
					<ul class="sub-menu">
						<li><a href="/front/sale/list.web?ctg_no=10101">수초</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10102">수초 청소 관리용품</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10103">장식/은신처</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10104">아쿠아스케이프 갤러리</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10105">수석</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10106">레이아웃 유목</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10107">바닥재(모래/자갈/산호사)</a></li>
					</ul>
					</li>
				<li><a href="/front/sale/list.web?ctg_no=102">먹이</a>
					<ul class="sub-menu">
						<li><a href="/front/sale/list.web?ctg_no=10201">소형어/치어</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10202">베타/금붕어/거북이</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10203">중/대형어</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10204">시클리드/디스커스</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10205">코리/플레코</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10206">새우/가재/갑각류</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10207">생먹이/냉동먹이</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10208">브랜드별</a></li>
					</ul>
				</li>
				<li><a href="/front/sale/list.web?ctg_no=103">수조/수조받침</a>
					<ul class="sub-menu">
						<li><a href="/front/sale/list.web?ctg_no=10301">받침대/축양장</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10302">누드/디아망수조</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10303">플라스틱어항</a></li>
					</ul>
				</li>
				<li><a href="/front/sale/list.web?ctg_no=104">수질관리</a>
					<ul class="sub-menu">
						<li><a href="/front/sale/list.web?ctg_no=10401">박테리아제</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10402">수질테스트</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10403">치료/예방</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10404">영양제/첨가제</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10405">물갈이제</a></li>
					</ul>
				</li>
				<li><a href="/front/sale/list.web?ctg_no=105">조명</a>
					<ul class="sub-menu">
						<li><a href="/front/sale/list.web?ctg_no=10501">등커버</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10502">스티커등/수중등</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10503">UV관련</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10504">미니등</a></li>
					</ul>
				</li>
				<li><a href="/front/sale/list.web?ctg_no=106">히터/에어/여과용품</a>
					<ul class="sub-menu">
						<li><a href="/front/sale/list.web?ctg_no=10601">여과기/여과재</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10602">에어용품/수중모터</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10603">히터</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10604">기포기/기포기소품</a></li>
					</ul>
				</li>
				<li><a href="/front/sale/list.web?ctg_no=107">청소/관리</a>
					<ul class="sub-menu">
						<li><a href="/front/sale/list.web?ctg_no=10701">생물봉투</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10702">온도계</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10703">산란/부화용품</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10704">볼탑/하우징</a></li>
						<li><a href="/front/sale/list.web?ctg_no=10705">청소용품/수조소품</a></li>
					</ul>
				</li>
				<li><a href="/common/board/list.web?cd_bbs_type=1">커뮤니티</a>
					<ul class="sub-menu">
						<li><a href="/common/board/list.web?cd_bbs_type=1">공지사항</a></li>
						<li><a href="/common/board/list.web?cd_bbs_type=2">자주찾는 질문 FAQ</a></li>
						<li><a href="/common/board/list.web?cd_bbs_type=3">질의응답 Q&A</a></li>
					</ul>
				</li>
			</ul>
		</div>
		</nav>
		</div>
	</div>
</div>