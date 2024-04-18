<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<%
	// 관리자(1), 판매자(2), 구매자(3) 
	int cd_usr_type_lnb = 0;
	try {cd_usr_type_lnb = Integer.parseInt((String)session.getAttribute("CD_USR_TYPE"));}
	catch (Exception e) {}
	%>
	<% if (cd_usr_type_lnb == 2) { %>
		<%@ include file="/include/seller/header.jsp" %>
	<% } else { %>
		<%@ include file="/include/front/header.jsp" %>
	<% } %>
	<script type="text/javascript" src="/js/package/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/js/package/tinymce.js"></script>
	<script>
		// HTML Editor
		tinymce.init({selector:'textarea'});
	
		$(function(){
			$('#datepicker').datepicker();
		})
	</script>
</head>
<body>
<div ="page">
<div class="container">
<form method="POST" action="/common/board/replyProc.web">
<input type="hidden" name="seq_bbs_parent" value="${boardDto.seq_bbs_parent}" />
	<header>
		<% if (cd_usr_type_lnb == 2) { %>
			<%@ include file="/include/seller/top.jsp" %>
		<% } else { %>
			<%@ include file="/include/front/top.jsp" %>
		<% } %>
	</header>
	<section class="content">
		<article>
			<table class="type_02" style="margin-left: auto; margin-right: auto;">
				<tr>
					<th>내용</th>
					<td><textarea name="contents" id="contents" style="width: 650px;height:200px;" maxlength="1000"></textarea></td>
				</tr>
			</table>
			<div style="text-align:center !important; margin: 10px">
				<input type="submit" value="등록" />
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