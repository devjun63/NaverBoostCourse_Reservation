<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다." />
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no" />
<title>네이버 예약</title>
<link href="css/style.css" rel="stylesheet" />
</head>

<body>
	<div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="./mainpage.html" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="./mainpage.html" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<c:choose>
					<c:when test="${sessionScope.reserveEmail != null}">
						<a href="/reservation/myreservation?reservationEmail=${sessionScope.reserveEmail }" class="btn_my"> 
						<span title="예약확인"> ${sessionScope.reserveEmail } </span>
						</a>
					</c:when>
					<c:otherwise>
						<a href="./bookinglogin" class="btn_my"> 
						<span title="예약확인"> 예약확인 </span>
						</a>
					</c:otherwise>
				</c:choose>
			</header>
		</div>
		<div class="ct">
			<div class="ct_wrap">
				<div class="top_title">
					<a href="./detail?id=${displayInfoId }" class="btn_back"
						title="이전 화면으로 이동"> <i class="fn fn-backward1"></i>
					</a>
					<h2>
						<span class="title"></span>
					</h2>
				</div>
				<div class="group_visual">
					<div class="container_visual" style="width: 414px">
						<ul class="visual_img">
							<c:forEach var="productImages" items="${productImages}"
								varStatus="status">
								<c:if test="${status.first eq true}">
									<li class="item" style="width: 414px"><img
										alt="${productImages.productId }" class="img_thumb"
										src="${productImages.saveFileName }" /> <span class="img_bg"></span>
										<div class="preview_txt">
											<h2 class="preview_txt_tit">${displayInfo.productDescription }</h2>
											<em class="preview_txt_dsc">₩ <c:forEach
													var="productPrices" items="${productPrices }"
													varStatus="priceStatus">
													<c:if test="${priceStatus.first eq true}">
													${productPrices.price }
												</c:if>
												</c:forEach> ~
											</em><em class="preview_txt_dsc">${reserveStartDate }~${reserveEndDate },
												잔여티켓 2769매</em>
										</div></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="section_store_details">
					<div class="store_details">
						<h3 class="in_tit"></h3>
						<p class="dsc">
							장소 : ${displayInfo.placeName }<br /> 기간 : ${reserveStartDate }~${reserveEndDate }
						</p>
						<h3 class="in_tit">관람시간</h3>
						<p class="dsc">${displayInfo.openingHours }</p>
						<h3 class="in_tit">요금</h3>
						<p class="dsc">
							<c:forEach var="productPrices" items="${productPrices}"
								varStatus="priceStatus">

								<c:if test="${priceStatus.index eq 0 }">
									성인(만 19~64세) ${productPrices.price }원 <br />
								</c:if>
								<c:if test="${priceStatus.index eq 1 }">
									어린이(만 4~12세) ${productPrices.price }원 <br />
								</c:if>
								<c:if test="${priceStatus.index eq 2 }">
									20인 이상 단체 20% 할인 / 국가유공자, 장애인, 65세 이상 ${productPrices.price }원 <br />
								</c:if>
								<c:if test="${priceStatus.index eq 3 }">
									청소년(만 13~18세) ${productPrices.price }원
									</c:if>
							</c:forEach>
						</p>
					</div>
				</div>
				<div class="section_booking_ticket">
					<div class="ticket_body">
						<c:forEach var="productPrices" items="${productPrices}"
							varStatus="priceStatus">
							<input type="hidden"
								name="prices[${priceStatus.index}].productPriceId"
								value="${productPrices.productPriceId }">
							<div class="qty">
								<div class="count_control">
									<!-- [D] 수량이 최소 값이 일때 ico_minus3, count_control_input에 disabled 각각 추가, 수량이 최대 값일 때는 ico_plus3에 disabled 추가 -->
									<div class="clearfix">
										<button type="button"
											class="btn_plus_minus spr_book2 ico_minus3 disabled"
											title="빼기"></button>
										<input type="tel" class="count_control_input disabled"
											value="0" name="prices[${priceStatus.index}].count" readonly
											title="수량" />
										<button type="button"
											class="btn_plus_minus spr_book2 ico_plus3" title="더하기">
										</button>
									</div>
									<!-- [D] 금액이 0 이상이면 individual_price에 on_color 추가 -->
									<div class="individual_price">
										<span class="total_price">0</span><span class="price_type">원</span>
									</div>
								</div>
								<div class="qty_info_icon">
									<strong class="product_amount"> <span> <c:if
												test="${priceStatus.index eq 0 }">
									성인
									</c:if> <c:if test="${priceStatus.index eq 1 }">
									유아
									</c:if> <c:if test="${priceStatus.index eq 2 }">
									세트1
									</c:if> <c:if test="${priceStatus.index eq 3 }">
									청소년
									</c:if>
									</span>

									</strong> <strong class="product_price"> <span class="price"><fmt:formatNumber
												type="number" maxFractionDigits="3"
												value="${productPrices.price }" /></span> <span class="price_type">원</span>
									</strong> <em class="product_dsc"> <c:set var="price"
											value="${productPrices.price - (productPrices.price * (productPrices.discountRate * 0.01))}" />
										<fmt:formatNumber type="number" maxFractionDigits="3"
											value="${price}" /> 원 (${productPrices.discountRate }% 할인가)
									</em>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="section_booking_form">
					<div class="booking_form_wrap">
						<div class="form_wrap">
							<h3 class="out_tit">예매자 정보</h3>
							<div class="agreement_nessasary help_txt">
								<span class="spr_book ico_nessasary"></span> <span>필수입력</span>
							</div>
							<form class="form_horizontal" action="./reserve" method="post">
								<div class="inline_form">
									<label class="label" for="name"> <span
										class="spr_book ico_nessasary">필수</span> <span>예매자</span>
									</label>
									<div class="inline_control">
										<input type="text" name="reservationName" id="name"
											class="text" placeholder="네이버" maxlength="17" />
										<div class="warning_msg">형식에 틀렸거나 너무 짧아요</div>
									</div>
								</div>
								<div class="inline_form">
									<label class="label" for="tel"> <span
										class="spr_book ico_nessasary">필수</span> <span>연락처</span>
									</label>
									<div class="inline_control tel_wrap">
										<input type="tel" name="reservationTelephone" id="tel"
											class="tel1" value="" placeholder="010"
											maxlength="3" /> -
										<input type="tel" name="reservationTelephone" class="tel2" value="" placeholder="1234" maxlength="4"> -
										<input type="tel" name="reservationTelephone" class="tel3" value="" placeholder="5678" maxlength="4">
										<div class="warning_msg">형식이 틀렸거나 너무 짧아요</div>
									</div>
								</div>
								<div class="inline_form">
									<label class="label" for="email"> <span
										class="spr_book ico_nessasary">필수</span> <span>이메일</span>
									</label>
									<div class="inline_control">
										<input type="email" name="reservationEmail" id="email"
											class="email" value="" placeholder="crong@codesquad.kr"
											maxlength="50" />
										<div class="warning_msg">이메일 양식에 맞게 적어주세요.</div>
									</div>
								</div>
								<div class="inline_form last">
									<label class="label" for="message">예매내용</label>
									<div class="inline_control">
										<p class="inline_txt selected">
											<input type="hidden" class="d_id" name="displayInfoId"
												value="${displayInfoId }"> <input type="hidden"
												class="p_id" name="productId" value="${productId }">
											<input type="hidden" class="r_ymd"
												name="reservationYearMonthDay" value="${reserveRandomDate }">
											${reserveRandomDate }, 총 <span id="totalCount">0</span>매
										</p>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="section_booking_agreement">
						<div class="agreement all">
							<input type="checkbox" id="chk3" class="chk_agree" /> <label
								for="chk3" class="label chk_txt_label"> <span>이용자
									약관 전체동의</span>
							</label>
							<div class="agreement_nessasary">
								<span>필수동의</span>
							</div>
						</div>
						<!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
						<div class="agreement">
							<span class="chk_txt_span"> <i
								class="spr_book ico_arr_ipc2"></i> <span>개인정보 수집 및 이용 동의</span>
							</span>
							<button type="button" class="btn_agreement">
								<span class="btn_text">보기</span> <i class="fn fn-down2"></i>
							</button>
							<div class="useragreement_details">
								&lt;개인정보 수집 및 이용 동의&gt;<br /> <br /> 1. 수집항목 : [필수] 이름, 연락처,
								[선택] 이메일주소<br /> <br /> 2. 수집 및 이용목적 : 사업자회원과 예약이용자의 원활한 거래
								진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정 해결을 위한 기록보존, 네이버 예약 이용 후 리뷰작성에 따른
								네이버페이 포인트 지급 및 관련 안내<br /> <br /> 3. 보관기간<br /> - 회원탈퇴 등 개인정보
								이용목적 달성 시까지 보관<br /> - 단, 상법 및 ‘전자상거래 등에서의 소비자 보호에 관한 법률’ 등 관련
								법령에 의하여 일정 기간 보관이 필요한 경우에는 해당 기간 동안 보관함<br /> <br /> 4. 동의 거부권
								등에 대한 고지: 정보주체는 개인정보의 수집 및 이용 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이
								제한될 수 있습니다.<br />
							</div>
						</div>
						<!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
						<div class="agreement">
							<span class="chk_txt_span"> <i
								class="spr_book ico_arr_ipc2"></i> <span>개인정보 제3자 제공 동의</span>
							</span>
							<button type="button" class="btn_agreement">
								<span class="btn_text">보기</span> <i class="fn fn-down2"></i>
							</button>
							<div class="useragreement_details custom_details_wrap">
								<div class="custom_details">
									&lt;개인정보 제3자 제공 동의&gt;<br /> <br /> 1. 개인정보를 제공받는 자 : 미디어앤아트<br />
									<br /> 2. 제공하는 개인정보 항목 : [필수] 네이버 아이디, 이름, 연락처 [선택] 이메일 주소<br />
									<br /> 3. 개인정보를 제공받는 자의 이용목적 : 사업자회원과 예약이용자의 원활한 거래 진행, 고객상담,
									불만처리 등 민원 처리, 서비스 이용에 따른 설문조사 및 혜택 제공, 분쟁조정 해결을 위한 기록보존<br />
									<br /> 4. 개인정보를 제공받는 자의 개인정보 보유 및 이용기간 : 개인정보 이용목적 달성 시 까지
									보관합니다.<br /> <br /> 5. 동의 거부권 등에 대한 고지 : 정보주체는 개인정보 제공 동의를
									거부할 권리가 있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="box_bk_btn">
					<!-- [D] 약관 전체 동의가 되면 disable 제거 -->
					<div class="bk_btn_wrap disable">
						<button type="button" class="bk_btn">
							<i class="spr_book ico_naver_s"></i> <span>예약하기</span>
							<div class="warning_msg">입력값을 다시 작성해주세요.</div>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<script type="text/javascript">
	
	var form_horizontal = document.querySelector(".form_horizontal");
	var bk_btn_wrap = document.querySelector(".bk_btn_wrap");
	var chk_txt_label = document.querySelector(".chk_txt_label");
	var name_txt = document.querySelector(".text");
	var tel1 = document.querySelector(".tel1");
	var tel2 = document.querySelector(".tel2");
	var tel3 = document.querySelector(".tel3");
	var email = document.querySelector(".email");
	
	
	chk_txt_label.addEventListener("click", function(evt){
		if(bk_btn_wrap.classList.contains("disable")){
			bk_btn_wrap.classList.remove("disable");
		}else{
			bk_btn_wrap.classList.add("disable");
		}
	});
	bk_btn_wrap.addEventListener("click", function(evt){
		const check_name = /^[가-힣a-z0-9_]{2,12}$/;
		const check_tel = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
		const check_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		const name_val = name_txt.value;
		const tel_val = tel1.value +'-'+ tel2.value +'-'+ tel3.value;
		const email_val = email.value;
		if(!bk_btn_wrap.classList.contains("disable")){
			if((check_name.test(name_val))&&(check_tel.test(tel_val))&&(check_email.test(email_val))){
				
				
				const displayInfoId = document.querySelector(".d_id").value;
		        var reservationprices = [];
		        var count_control = document.querySelectorAll('.count_control_input');
		        for(let i = 0; i <count_control.length; i++){
		        	
		        	let prices = {count : 0, productPriceId : 0, reservationInfoId : 0, reservationInfoPriceId : 0};
		        	var counts = document.querySelector('input[name="prices['+i+'].count"]');
		        	var tempProductPriceid = document.querySelector('input[name="prices['+i+'].productPriceId"]');
		        	
		        	prices.count = counts.value;
		        	prices.productPriceId = tempProductPriceid.value;
		        	prices.reservationInfoId = 0 ;
		        	prices.reservationInfoPriceId = 0;
		        	
		        	reservationprices.push(prices);
		        }
		        
		        const productId = document.querySelector(".p_id").value;
		        const reservationEmail = email_val;
		        const reservationName = name_val;
		        const reservationTelephone = tel_val;
		        const reservationYearMonthDay = document.querySelector(".r_ymd").value;
		        
		        var url = "/reservation/reserve";
		        var params = {
		        	  "displayInfoId": displayInfoId,
		        	  "prices": reservationprices,
		        	  "productId": productId,
		        	  "reservationEmail": reservationEmail,
		        	  "reservationName": reservationName,
		        	  "reservationTelephone": reservationTelephone,
		        	  "reservationYearMonthDay": reservationYearMonthDay
		        	};
				var data = JSON.stringify(params);
				function sendAjax(url){
					var oReq = new XMLHttpRequest();
					oReq.open("POST", url);	// method: POST
					oReq.setRequestHeader("Content-Type", "application/json"); // Content-Type: json
					oReq.responseType = "text";		// text for json
					oReq.addEventListener("load", function () { // when success
						/* console.log("성공 : "+this); */
						location.href = "/reservation";
					});
					oReq.send(data);
					
				}
				
				sendAjax(url);
		
			}else{
				if(evt.target.tagName === "I" || evt.target.tagName === "SPAN"){
					evt.target.focus();
					evt.target.parentNode.children[2].style.visibility="visible";
					setTimeout(function() {
						evt.target.parentNode.children[2].style.visibility="hidden";
					}, 1000);
				}else{
					evt.target.focus();
					evt.target.children[2].style.visibility="visible";
					setTimeout(function() {
						evt.target.children[2].style.visibility="hidden";
					}, 1000);
				}
			}
		}
		
		
		/* .parentNode.children[1];
		if(!email_regExp.test(email_value)){
			evt.target.focus();
			warning_msg.style.visibility="visible";
			setTimeout(function() {
				warning_msg.style.visibility="hidden";
			}, 1000);
		} */
		
	});
	
	
	var totalCount = document.querySelector("#totalCount");
	var qty = document.querySelectorAll('.qty');
	for(var i = 0; i <qty.length; i++){
		var each_qty = qty[i];
		each_qty.addEventListener('click', function(evt){
			// 예매 내용의 totalCount 값 변동 & individual_price의 값 변동
			var target = evt.target;
			if(target.classList.contains('ico_plus3')){
				const plus_count = target.previousElementSibling.value;
				let discount_price_text = target.parentNode.parentNode.parentNode.children[1].children[2].innerText;
				let total_price = target.parentNode.parentNode.children[1].children[0];
				let discount_price = discount_price_text.split('원');
				let price = parseInt(discount_price[0].replace(/[^0-9]/g,''));
				let tempcount = total_price.innerText;
				total_price.innerHTML = price + parseInt(tempcount);
				
				var totalCountvalue = totalCount.innerHTML;
				if(plus_count == "0"){
					target.previousElementSibling.classList.remove('disabled');
				}
				const plus_result = parseInt(plus_count) + 1;
				target.previousElementSibling.value = plus_result;
				totalCountvalue = parseInt(totalCountvalue) + 1;
				totalCount.innerHTML = totalCountvalue;
				
			}else if(target.classList.contains('ico_minus3')){
				const minus_count = target.nextElementSibling.value;
				var totalCountvalue = totalCount.innerHTML;

				let discount_price_text = target.parentNode.parentNode.parentNode.children[1].children[2].innerText;
				let total_price = target.parentNode.parentNode.children[1].children[0];
				let discount_price = discount_price_text.split('원');
				let price = parseInt(discount_price[0].replace(/[^0-9]/g,''));
				let tempcount = total_price.innerText;
				
				
				if(minus_count != '0'){
					const minus_result = parseInt(minus_count) - 1;
					target.nextElementSibling.value	 = minus_result;
					totalCountvalue = parseInt(totalCountvalue) -1;
					totalCount.innerHTML = totalCountvalue;
					total_price.innerHTML = parseInt(tempcount) - price;
					if(minus_result == 0){
						target.nextElementSibling.classList.add('disabled');
					}
				}
			}
		});
	}

	var agreement_btns = document.querySelectorAll('.btn_agreement');
		for (var i = 0; i < agreement_btns.length; i++) {
			var each_btn = agreement_btns[i];

			each_btn.addEventListener('click', function(evt) {
				var target = evt.target;
				if (target.tagName == "BUTTON") {
					if (!target.parentNode.classList.contains('open')) {
						target.parentNode.classList.add('open')
					} else if (target.parentNode.classList.contains('open')) {
						target.parentNode.classList.remove('open')
					}
				} else {
					if (!target.parentNode.parentNode.classList
							.contains('open')) {
						target.parentNode.parentNode.classList.add('open')
					} else if (target.parentNode.parentNode.classList
							.contains('open')) {
						target.parentNode.parentNode.classList.remove('open')
					}
				}
			}, false);
		}

		
		
		form_horizontal.addEventListener('change', function(evt){
			if(evt.target.classList.contains('tel1')||evt.target.classList.contains('tel2')||evt.target.classList.contains('tel3')){
				const tel_regExp = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
				const tel1_regExp = /^[0-9]{2,3}$/;
				const tel2_regExp = /^[0-9]{3,4}$/;
				const tel3_regExp = /^[0-9]{4}$/;
				const tel_value = evt.target.value;
				const warning_msg = evt.target.parentNode.children[3];
				if(evt.target.classList.contains('tel1')){
					if(!tel1_regExp.test(tel_value)){
						evt.target.focus();
						warning_msg.style.visibility="visible";
						setTimeout(function() {
							warning_msg.style.visibility="hidden";
						}, 1000);
					}
				};
				if(evt.target.classList.contains('tel2')){
					if(!tel2_regExp.test(tel_value)){
						evt.target.focus();
						warning_msg.style.visibility="visible";
						setTimeout(function() {
							warning_msg.style.visibility="hidden";
						}, 1000);
					}
				};
				if(evt.target.classList.contains('tel3')){
					if(!tel3_regExp.test(tel_value)){
						evt.target.focus();
						warning_msg.style.visibility="visible";
						setTimeout(function() {
							warning_msg.style.visibility="hidden";
						}, 1000);
					}
				};
			}else if(evt.target.classList.contains('text')){
				const name_regExp = /^[가-힣a-z0-9_]{2,12}$/;
				const name_value = evt.target.value;
				const warning_msg = evt.target.parentNode.children[1];
				
				if(!name_regExp.test(name_value)){
					evt.target.focus();
					warning_msg.style.visibility="visible";
					setTimeout(function() {
						warning_msg.style.visibility="hidden";
					}, 1000);
				}
			}else if(evt.target.classList.contains('email')){
				const email_regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
				const email_value = evt.target.value;
				const warning_msg = evt.target.parentNode.children[1];
				
				if(!email_regExp.test(email_value)){
					evt.target.focus();
					warning_msg.style.visibility="visible";
					setTimeout(function() {
						warning_msg.style.visibility="hidden";
					}, 1000);
				}
			}
		});
		
		function compare_regexp(inputValue){
			this.form_horizontal = inputValue;
			this.registerEvents();
		}
		
		/* RegExp.prototype = {
			registerEvents : function () {
				this.tabmenu.addEventListener("change", function (evt) {
					const value = evt.target.value;
					const warning_msg = evt.target.parentNode.children[1];
					let regExp;
					if(evt.target.classList.contains('text')){
						regExp = /^[가-힣a-z0-9_]{2,12}$/;
					}else if(evt.target.classList.contains('tel')){
						regExp = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
					}else if(evt.target.classList.contains('email')){
						regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
					}
					if(!regExp.test(value)){
						evt.target.focus();
						warning_msg.style.visibility="visible";
						setTimeout(function() {
							warning_msg.style.visibility="hidden";
						}, 1000);
					}
				}.bind(this));
			}
		} */
		
		
		/* form_horizontal.addEventListener('click', function(evt){
			console.log(evt.target.classList);
		}); */
		/* 
		 */


		
		
		/* btn_agreement.addEventListener("click", function(evt) {
			var target = evt.target;
			if(!target.parentNode.parentNode.classList.contains('open')){
				target.parentNode.parentNode.classList.add('open')
			}else if(target.parentNode.parentNode.classList.contains('open')){
				target.parentNode.parentNode.classList.remove('open')
			}
		}); */

		/* var buttons = document.querySelectorAll('.btn-click-me');
		for (var i = 0; i < buttons.length; i++) {
		    var self = buttons[i];

		    self.addEventListener('click', function (event) {  
		        // prevent browser's default action
		        event.preventDefault();

		        // call your awesome function here
		        MyAwesomeFunction(this); // 'this' refers to the current button on for loop
		    }, false);
		} */
	</script>
</body>
</html>
