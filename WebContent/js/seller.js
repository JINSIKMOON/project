/**
 * @author thepluto#hotmail.com
 * @since 2023-11-02
 *
 * <p>DESCRIPTION: 다음 우편번호 찾기(https://postcode.map.daum.net/guide)</p>
 * <p>IMPORTANT:</p>
 */
function execDaumPostcode() {
	
	var width = 500; //팝업의 너비
	var height = 600; //팝업의 높이
	
	new daum.Postcode({
		width: width,
		height: height,
		oncomplete: function(data) {
			// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var roadAddr = data.roadAddress;		// 도로명 주소 변수
			var extraRoadAddr = '';					// 참고 항목 변수
			
			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				extraRoadAddr += data.bname;
			}
			
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if(data.buildingName !== '' && data.apartment === 'Y'){
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			
			// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if(extraRoadAddr !== ''){
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}
			
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById("corp_postcode").value		= data.zonecode;
			document.getElementById("corp_addr1").value			= roadAddr;
			document.getElementById("jibunAddress").value		= data.jibunAddress;
			
			// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
			if(roadAddr !== ''){
				document.getElementById("extraAddress").value = extraRoadAddr;
			}
			else {
				document.getElementById("extraAddress").value = '';
			}
			
			var guideTextBox = document.getElementById("guide");
			// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
			if(data.autoRoadAddress) {
				var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
				guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
				guideTextBox.style.display = 'block';
			
			}
			else if(data.autoJibunAddress) {
				var expJibunAddr = data.autoJibunAddress;
				guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
				guideTextBox.style.display = 'block';
			}
			else {
				guideTextBox.innerHTML = '';
				guideTextBox.style.display = 'none';
			}
			}
		}).open({
			left: (window.screen.width / 2) - (width / 2),
			top: (window.screen.height / 2) - (height / 2)
	});
}

function checkModify() {
	var frmMain = document.getElementById("frmMain");
	frmMain.action = "/seller/sellers/modifyProc.web";
	frmMain.submit();
}
	
/**
 * @author thepluto#hotmail.com
 * @since 2023-10-30
 *
 * <p>DESCRIPTION: 가입 정보 확인</p>
 * <p>IMPORTANT:</p>
 */
/*
function checkRegister() {
	
	arrNoID = ["system", "root", "administator"];
	
	if (document.getElementById("corp_nm").value == "") {
		alert("기업명을 입력하세요!");
		document.getElementById("corp_nm").focus();
	}
	else if (document.getElementById("passwd").value == document.getElementById("passwd_").value) {
		
		var exist = false;
		
		// 사용할 수 없는 아이디 확인
		for (loop = 0; loop < arrNoID.length; loop++) {
			if (document.getElementById("id").value.indexOf(arrNoID[loop]) >= 0) exist = true;
		}
		
		// 가입된 아이디 중복 확인
		if (document.getElementById("checkIdResult").value == true) {
			alert("아이디 중복 확인을 하십시오.");
			return;
		}
		
		if (exist) {
			alert("사용이 불가능한 아이디입니다!");
		}
		else {
			
			document.getElementById("corp_num").value =	document.getElementById("corp_num1").value
				+ "-" + document.getElementById("corp_num2").value
				+ "-" + document.getElementById("corp_num3").value;
			
			// location.href = "/";
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/seller/sellers/writeProc.web";
			frmMain.submit();
		}
	}
	else {
		alert("암호를 확인하세요!");
	}
}
*/

/**
 * @author thepluto#hotmail.com
 * @since 2023-10-30
 *
 * <p>DESCRIPTION: 로그인 정보 확인</p>
 * <p>IMPORTANT:</p>
 */
function checkLogin() {
	
	// var id		= "thepluto";
	// var passwd	= "12345678";
	
	/*
	if (document.getElementById("id").value.length < 8) {
		alert("아이디는 8자 이상 입력하세요!");
		document.getElementById("id").focus();
	}
	else
	*/
	if (document.getElementById("passwd").value.length < 8) {
		alert("암호는 8자 이상 입력하세요!");
		document.getElementById("passwd").focus();
	}
	else {
		var frmMain = document.getElementById("frmMain");
		frmMain.action = "/seller/login/loginProc.web";
		frmMain.submit();
		/*
		// alert(typeof passwd);
		if (document.getElementById("id").value == id && document.getElementById("passwd").value == passwd) {
			// location.href = "/login";
			var frmMain = document.getElementById("frmMain");
			frmMain.action = "/login";
			frmMain.submit();
		}
		else {
			alert("아이디와 암호를 확인하세요!");
		}
		*/
	}
}

/* 에러(Error) */
var MSG_ERR_COMMON="에러가 발생하였습니다. 잠시 후에 이용하여 주십시오!";
var MSG_ERR_ID="사용하실 수 없는 아이디입니다!";
var MSG_ERR_ID_PASSWD="아이디와 암호를 확인하세요!";
var MSG_ERR_WRITE="등록에 실패하였습니다. 잠시 후에 이용하여 주십시오!";
var MSG_ERR_MODIFY="수정에 실패하였습니다. 잠시 후에 이용하여 주십시오!";
var MSG_ERR_REMOVE="삭제에 실패하였습니다. 잠시 후에 이용하여 주십시오!";

/* 문의(Ask) */
var MSG_ASK_CNL="취소 하시겠습니까?";
var MSG_ASK_RMV="삭제 하시겠습니까?";

/* 입력(Input) */
var MSG_INP_SCH="검색어을 입력하세요.";
var MSG_INP_CTG="카테고리를 선택하세요.";
var MSG_INP_ID="사용하실 아이디를 입력하세요.";

/* 성공(Success) */
var MSG_SCC_WRITE="등록되었습니다.";
var MSG_SCC_MODIFY="수정되었습니다.";
var MSG_SCC_REMOVE="삭제되었습니다.";

var MSG_SCC_ID="사용 가능한 아이디입니다.";