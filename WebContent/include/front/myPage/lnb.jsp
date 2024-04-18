<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<style>
    #secondary {
        padding-right: 100px; /* 우측 패딩 값을 조절할 수 있습니다. */
    }
</style>
		<div><a href= "/front/myPage/main.web"><h2 class="subtitle">마이페이지</h2></a></div>
			<hr>
				<div id="secondary" class="column third">
					<div>
						<aside id="recent-posts-2" class="widget widget_recent_entries">
							<ul>
							<div><a href="/front/myPage/buyList.web"><h4>주문내역조회</h4></a></div>
							<div><a href="/front/myPage/inquiryView.web"><h4>1:1 문의<h4></a></div>
							<div><a href="/front/myPage/recentView.web"><h4>최근 본 상품<h4></a></div>
							<div><a href="/front/myPage/likeView.web"><h4>좋아요<h4></a></div>
							<div><a href="/front/myPage/reviewMng.web"><h4>리뷰관리<h4></a></div>
							<div><a href="/front/customer/modifyForm.web"><h4>개인정보 수정<h4></a></div>
							</ul>
						</aside>
					<aside  style="border: 1px solid black; border-radius: 20px; padding: 10px; width: 225px">
					<div><h4>고객센터 문의</h4></div>
					<div class="textwidget">
					 <span style="font-size: 25px;">031 - 736 - 0008</span>
						<p>	평일 오전 10:30 ~ 오후 18:00<br>
							점심 오후 12:00 ~ 오후 13:00<br>
							휴무 명절 당일 제외 무휴<br>
						</p>
						<div style="display: flex; justify-content: center; align-items: center; height: 2vh;">
							<input type="button" value="자주찾는질문" onclick="window.location.href='/common/board/list.web?cd_bbs_type=2';">
						</div>
					</div>
					</aside>
					<br>
				</div>
			</div>
			