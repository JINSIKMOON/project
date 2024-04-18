<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="plutozoneUtilTag"	uri="/WEB-INF/tld/com.plutozone.util.tld" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/front/header.jsp" %>
<style>
	.fa-star {
		cursor: pointer;
		transition: color 0.3s ease;
	}
	.highlight {
		color: pink !important;
	}
</style>
<script>
function setRating(num) {
	var stars = document.querySelectorAll('.fa-star');
	var message = "";
	
	stars.forEach(function(star, index) {
		star.addEventListener('mouseover', function() {
		  fillStars(index);
		});
		star.addEventListener('mouseleave', function() {
		  fillStars(num - 1);
		});
		star.addEventListener('click', function() {
		  num = index + 1;
		  fillStars(index);
		  showMessage(num);
		});
	});

	function fillStars(fillIndex) {
		stars.forEach(function(star, index) {
			if (index <= fillIndex) {
			 star.classList.add('highlight');
			} else {
			 star.classList.remove('highlight');
			}
		});
	}
	
	function showMessage(rating) {
		switch (rating) {
			case 1:
			 message = "1점(별로예요)";
			 break;
			case 2:
			 message = "2점(그저그래요)";
			 break;
			case 3:
			 message = "3점(괜찮아요)";
			 break;
			case 4:
			 message = "4점(좋아요)";
			 break;
			case 5:
			 message = "5점(최고예요)";
			 break;
			default:
			 message = "";
		}
		document.getElementById('ratingMessage').innerText = message;
	}
	
	fillStars(num - 1);
	showMessage(num);
	}
	
	function countCharacters() {
		var text = document.getElementById('review').value;
		var charCount = text.length;
		document.getElementById('charCount').textContent = charCount + '/1000';
	}

	function cancelReview() {
		var confirmed = confirm("리뷰 작성을 취소 하시겠습니까?");
		if (confirmed) {
			window.close();
		}
	}
	function reviewProc() {
		var frmMain = document.getElementById("frmMain");
		var score = document.querySelector('input[name="score"]:checked');

			if (!score) {
				alert("별점을 선택해주세요.");
				return false; 
			}

		var scoreValue = parseInt(score.value);
			if (isNaN(scoreValue) || scoreValue < 1 || scoreValue > 5) {
				alert("유효하지 않은 별점입니다.");
				return false;
			}

		frmMain.action = "/front/myPage/reviewProc.web";
		frmMain.submit();
	}
</script>
</head>
<header>
		<div>
			<h3 style= text-align:center>리뷰쓰기</h3>
		</div>
		<hr>
</header>
<body>
<div class="container">
<form id="frmMain" method="POST" encType="UTF-8">
	<%-- <input type="hidden" id="id" name="id" value="<%= loggedInId %>"/> --%>
	<input type ="hidden" name="seq_sle" value="${saleDto.seq_sle}" />
	<input type ="hidden" name="seq_cst" value="${customerDto.seq_cst}" />
	
	
	<div>
		<img src="/image/sale${saleDto.img}" alt="" width="70" height="70">
		<span style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap; max-width: 200px;">${saleDto.sle_nm}</span>
	</div>
	<hr>
	<div>
		<h4 style="text-align:center">상품은 만족하셨나요?</h4>
	</div>
		<p style="text-align:center" >
			<input type="radio" id="star1" name="score" value="1" onclick="setRating(1)" onmouseover="highlightStars(1)" onmouseout="highlightStars(0)" style="display: none;" />
			<label for="star1" class="fa fa-star fa-4x"></label>

			<input type="radio" id="star2" name="score" value="2" onclick="setRating(2)" onmouseover="highlightStars(2)" onmouseout="highlightStars(0)" style="display: none;" />
			<label for="star2" class="fa fa-star fa-4x"></label>

			<input type="radio" id="star3" name="score" value="3" onclick="setRating(3)" onmouseover="highlightStars(3)" onmouseout="highlightStars(0)" style="display: none;" />
			<label for="star3" class="fa fa-star fa-4x"></label>

			<input type="radio" id="star4" name="score" value="4" onclick="setRating(4)" onmouseover="highlightStars(4)" onmouseout="highlightStars(0)" style="display: none;"/>
			<label for="star4" class="fa fa-star fa-4x"></label>

			<input type="radio" id="star5" name="score" value="5" onclick="setRating(5)" onmouseover="highlightStars(5)" onmouseout="highlightStars(0)" style="display: none;"/>
			<label for="star5" class="fa fa-star fa-4x"></label>
		</p>
		<p id="ratingMessage" style="text-align:center"></p>
		<hr>
		<div>
			<h4 style="text-align:center">어떤 점이 좋으셨나요?</h4>
		</div>	
			<textarea name="review" id="review" style="width: 385px; height:200px;" maxlength="1000" placeholder="만족도에 대한 후기를 남겨주세요!" oninput="countCharacters()"></textarea>
			<div id="charCount" style="text-align:right">0/1000</div>
			<br>
			<br>
			<div style="text-align: center;">
				<input type="button" value="취소" onclick="cancelReview()" />
				<input type="button" value="등록" onclick="javascript:reviewProc()"/>
			</div>
	</form>
</div>
</body>
</html>