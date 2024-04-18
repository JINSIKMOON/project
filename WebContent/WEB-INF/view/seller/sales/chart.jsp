<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
	<%@ include file="/include/seller/header.jsp" %>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/seller.js"></script>
	<script src="/js/jquery-3.7.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script>
		function onSubmit() {
			var frmMain = document.getElementById("frmMain");
			frmMain.submit();
		}
	</script>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <script type="text/javascript">
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var salesData = [
            ['월', '매출']
            // 이곳에 JSP에서 생성된 매출 데이터를 추가합니다.
            // 예: ['1 월', 1000], ['2 월', 2000], 등
        ];

        <c:forEach items="${list}" var="list">
            salesData.push(['${list.month} 월', ${list.sumSale}]);
        </c:forEach>

        var data = google.visualization.arrayToDataTable(salesData);

        var options = {
            title: '월별 매출',
            hAxis: {titleTextStyle: {color: '#333'}},
            vAxis: {minValue: 0}
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
</script>
</head>
<body>
<div id="page">
<div class="container">
<form id="frmMain" method="POST" action="/seller/sales/chart.web">
	<header>
		<%@ include file="/include/seller/top.jsp" %>
	</header>
	<section class="content">
		<nav>
			<%@ include file="/include/seller/sales/lnb.jsp" %>
		</nav>
		<div style="text-align:right; padding-right: 65px">
				<select name="year" onchange="onSubmit();">
					<option value="2022"<c:if test="${salesDto.year == 2022}"> selected</c:if>>2022년</option>
					<option value="2023"<c:if test="${salesDto.year == 2023}"> selected</c:if>>2023년</option>
					<option value="2024"<c:if test="${salesDto.year == 2024}"> selected</c:if>>2024년</option>
				</select>
			</div>
		  <div id="chart_div" style="width: 100%; height: 500px;"></div>
	</section>
	</form>
</div>
</div>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</body>
</html>