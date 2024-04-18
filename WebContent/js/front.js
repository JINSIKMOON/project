

function setCookie3nd(cookieName, value, days) {
	// 설정 일수만큼 현재시간에 만료값으로 지정
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + 2);
	
	var cookieValue = escape(value) + "; path=/" + ((2 == null) ? '' : '; expires=' + exdate.toUTCString());
	document.cookie = cookieName + '=' + cookieValue;
	
	//alert(getCookie3nd("recent"));
	
}

function getCookie3nd(cookieName) {
	var x, y;
	var val = document.cookie.split(';');

	for (var i = 0; i < val.length; i++) {
		x = val[i].substr(0, val[i].indexOf('='));
		y = val[i].substr(val[i].indexOf('=') + 1);
		// 앞과 뒤의 공백 제거하기
		x = x.replace(/^\s+|\s+$/g, '');
		
		if (x == cookieName) {
			// unescape로 디코딩 후 값 리턴
			return unescape(y);
		}
	}
}

function insertRecent(item) {
	
	// 이미 저장된 값을 쿠키에서 가져오기
	var items = getCookie3nd("recent");
	
	// 쿠키값을 저장할 기간(일), null일 경우는 브라우저 닫을 시 삭제됨
	var expire = null;
	
	if (items) {
		/*alert(String.fromCharCode([15]));*/
		setCookie3nd("recent", items + "@@@" + item, expire);
	}
	else {
		setCookie3nd("recent", item, expire);
	}
}

//비밀번호 재설정
function updatePassword(event) {
    var passwd = document.getElementById("passwd").value;
    var pw2 = document.getElementById("pw2").value;

    if (passwd !== pw2) {
        alert("비밀번호가 일치하지 않습니다.");
        event.preventDefault();
        return false;
    }

    var passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$/;
    if (!passwordRegex.test(passwd)) {
        alert("비밀번호는 최소 8자에서 16자까지, 영문자, 숫자 및 특수 문자를 1개 이상 포함해야 합니다.");
        event.preventDefault();
        return false;
    }
    
    	var frmMain = document.getElementById("frmMain");
		frmMain.action = "/front/customer/updatePwresult.web";
		frmMain.submit();

}

//필수 약관 체크
function checkAgree() {
	var flg_user_agreement = document.getElementById("flg_user_agreement");
	var flg_personal_data = document.getElementById("flg_personal_data");

	if (!flg_user_agreement.checked || !flg_personal_data.checked) {
		alert("필수이용 약관을 동의해 주세요.");
		return false;
	}
	return true;
}
 
 function validateId() {
	var idInput = document.getElementById('id');
		idInput.addEventListener('input', function () {
	var idValue = idInput.value;
	var idRegex = /^[a-zA-Z0-9]{8,16}$/;
	var message = document.getElementById('idMessage');
		if (idRegex.test(idValue)) {
			message.innerHTML = ''; 
		} else {
			message.innerHTML = '8자 이상 16자 이하의 영문 혹은 영문과 숫자를 조합';
				message.style.color = 'red';
		}
	});
}

//비밀번호 조합조건
function validatePassword() {
	var passwd = document.getElementById('passwd').value;
	var pw2 = document.getElementById('pw2').value;
	var resultDiv = document.getElementById('result');
	var resultDiv2 = document.getElementById('result2');

	var passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$/;

	if (passwordRegex.test(passwd)) {
		if (passwd === pw2) {
			resultDiv2.innerHTML = '비밀번호 일치';
			resultDiv2.style.color = 'green';
			resultDiv.innerHTML = '';
			resultDiv.style.color = '';
		
		} else {
			resultDiv2.innerHTML = '동일한 비밀번호를 입력 해주세요.';
			resultDiv2.style.color = 'red';
			resultDiv.innerHTML = '';
			resultDiv.style.color = '';
		}
		} else {
			resultDiv.innerHTML = '비밀번호는 최소 8자에서 16자까지, 영문자, 숫자 및 특수 문자를1개 이상 포함해야 합니다.';
			resultDiv.style.color = 'red';
		}

	// 자동으로 유효성 검사
		document.getElementById('passwd').addEventListener('input', validatePassword);
		document.getElementById('pw2').addEventListener('input', validatePassword);
}
/**
 * @author pluto#plutozone.com
 * @since 2024-01-24
 *
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
// 쿠키 저장하기 
// setCookie => saveid함수에서 넘겨준 시간이 현재시간과 비교해서 쿠키를 생성하고 지워주는 역할
function setCookie(cookieName, value, exdays) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + exdays);
	var cookieValue = escape(value)
			+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
	document.cookie = cookieName + "=" + cookieValue;
}

// 쿠키 삭제
function deleteCookie(cookieName) {
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expires="
			+ expireDate.toGMTString();
}

// 쿠키 가져오기
function getCookie(cookieName) {
	cookieName = cookieName + '=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cookieName);
	var cookieValue = '';
	if (start != -1) { // 쿠키가 존재하면
		start += cookieName.length;
		var end = cookieData.indexOf(';', start);
		if (end == -1) // 쿠키 값의 마지막 위치 인덱스 번호 설정 
			end = cookieData.length;
			console.log("end위치  : " + end);
		cookieValue = cookieData.substring(start, end);
	}
	return unescape(cookieValue);
}

// 장바구니 쿠키저장
function setCookie2nd(cookieName, value, days) {
	// 설정 일수만큼 현재시간에 만료값으로 지정
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + days);
	
	var cookieValue = escape(value) + "; path=/" + ((days == null) ? '' : '; expires=' + exdate.toUTCString());
	document.cookie = cookieName + '=' + cookieValue;
}

// 장바구니 쿠키 가져오기
function getCookie2nd(cookieName) {
	var x, y;
	var val = document.cookie.split(';');

	for (var i = 0; i < val.length; i++) {
		x = val[i].substr(0, val[i].indexOf('='));
		y = val[i].substr(val[i].indexOf('=') + 1);
		// 앞과 뒤의 공백 제거하기
		x = x.replace(/^\s+|\s+$/g, '');
		
		if (x == cookieName) {
			// unescape로 디코딩 후 값 리턴
			return unescape(y);
		}
	}
}

// 장바구니
function insertBasket(item) {
	
	// 이미 저장된 값을 쿠키에서 가져오기
	var items = getCookie2nd("productBasket");
	
	// 쿠키값을 저장할 기간(일), null일 경우는 브라우저 닫을 시 삭제됨
	var expire = null;
	
	if (items) {
		
		/*alert(String.fromCharCode([15]));*/
		setCookie2nd("productBasket", items + "@@@" + item, expire);
		//alert(itemArray.length);
	}
	else {
		setCookie2nd("productBasket", item, expire);
	}
	//alert("[저장된 정보]\n" + items);
}

/**
 * @author pluto#plutozone.com
 * @since 2024-01-24
 *
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
function updateBasket() {
}

/**
 * @author pluto#plutozone.com
 * @since 2024-01-24
 *
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
function selectBasket() {
}

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
			document.getElementById("postcode").value		= data.zonecode;
			document.getElementById("roadAddr").value		= roadAddr;
			document.getElementById("addr1").value			= data.jibunAddress;
			
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

/**
 * @author thepluto#hotmail.com
 * @since 2023-10-30
 *
 * <p>DESCRIPTION: 가입 정보 확인</p>
 * <p>IMPORTANT:</p>
 */
//유효성 확인 및 회원가입
function checkRegister() {
	var id = document.getElementById("id").value;
	var passwd = document.getElementById("passwd").value;
	var pw2 = document.getElementById("pw2").value;
	var cst_nm = document.getElementById("cst_nm").value;
	var phone = document.getElementById("phone").value;
	var postcode = document.getElementById("postcode").value;
	var addr1 = document.getElementById("addr1").value;
	var cst_email = document.getElementById("cst_email").value;
	
	if (id.trim() == "" || passwd.trim() == "" || pw2.trim() == "" || cst_nm.trim() == "" || phone.trim() == "" || postcode.trim() == "" || addr1.trim() == "" || cst_email.trim() == "") {
		alert("필수 입력 사항을 모두 작성해주세요.");
		return false;
	}
	
	// 가입된 아이디 중복 확인
	if (document.getElementById("checkIdResult").value == "true") {
		alert("아이디 중복 확인을 하십시오.");
		return false;
	}
	
	if (passwd !== pw2) {
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
	
	var passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$/;
		if (!passwordRegex.test(passwd)) {
		alert("비밀번호는 최소 8자에서 16자까지, 영문자, 숫자 및 특수 문자를 1개 이상 포함해야 합니다.");
		return false;
	}
	
	var emailRegex = /^[a-zA-Z0-9._%+-]+[@]{1}[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		if (!emailRegex.test(cst_email)) {
		alert("유효한 이메일 주소를 입력하세요.");
		return false;
	}

	var frmMain = document.getElementById("frmMain");
		frmMain.action = "/front/customer/writeProc.web";
		frmMain.submit();
}

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
	
	if (document.getElementById("id").value.length < 8) {
		alert("아이디는 8자 이상 입력하세요!");
		document.getElementById("id").focus();
	}
	else if (document.getElementById("passwd").value.length < 8) {
		alert("암호는 8자 이상 입력하세요!");
		document.getElementById("passwd").focus();
	}
	else {
		var frmMain = document.getElementById("frmMain");
		frmMain.action = "/front/login/loginProc.web";
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
var MSG_ERR_PAY="결제에 실패하였습니다. 잠시 후에 이용하여 주십시오!";
var MSG_ERR_MIX="8자 이상 16자 이하의 영문 혹은 영문과 숫자를 조합!";

var MSG_ERR_PRD="선택된 상품이 없습니다!";
var MSG_ERR_BUY="구매에 실패하였습니다. 잠시 후에 이용하여 주십시오!";


/* 문의(ASK) */
var MSG_ASK_CNL="취소 하시겠습니까?";
var MSG_ASK_MVE="이동 하시겠습니까?";


/* 안내(Introducation) */
var MSG_INT_SCH="검색어을 입력하세요.";


/* 성공(SCC) */
var MSG_SCC_WRITE="등록되었습니다.";
var MSG_SCC_MODIFY="수정되었습니다.";
var MSG_SCC_REMOVE="삭제되었습니다.";
var MSG_SCC_BUY="구매(정상 결제 포함)해 주셔서 감사합니다.";
var MSG_SCC_ID="사용 가능한 아이디입니다.";
// var MSG_SCC_BUY="구매에 성공하였습니다. 결제 페이지로 이동합니다.";