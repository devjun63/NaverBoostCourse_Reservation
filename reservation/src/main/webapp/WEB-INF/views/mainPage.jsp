<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
		<div class="header">
			<header class="header_tit">
			<h1 class="logo">
				<a href="./" class="lnk_logo" title="네이버"> <span
					class="spr_bi ico_n_logo">네이버</span>
				</a> <a href="./" class="lnk_logo" title="예약"> <span
					class="spr_bi ico_bk_logo">예약</span>
				</a>
			</h1>
			<c:choose>
				<c:when test="${sessionScope.reserveEmail != null}">
					<a href="/reservation/myreservation?reservationEmail=${sessionScope.reserveEmail }" class="btn_my"> 
					<span class="viewReservation" title="예약확인">
					${sessionScope.reserveEmail }
					</span>
					</a>
				</c:when>
				<c:otherwise>
					<a href="./bookinglogin" class="btn_my"> <span class="viewReservation" title="예약확인">
						예약확인
					</span>
					</a>
				</c:otherwise>
			</c:choose>
			</header>
		</div>
		<hr />
		<div class="event">
			<div class="section_visual">
				<div class="group_visual">
					<div class="container_visual">
						<div class="prev_e" style="display: none;">
							<div class="prev_inn">
								<a href="#" class="btn_pre_e" title="이전"> <i
									class="spr_book_event spr_event_pre">이전</i>
								</a>
							</div>
						</div>
						<div class="nxt_e" style="display: none;">
							<div class="nxt_inn">
								<a href="#" class="btn_nxt_e" title="다음"> <i
									class="spr_book_event spr_event_nxt">다음</i>
								</a>
							</div>
						</div>
						<div>
							<div class="container_visual">
								<!-- 슬라이딩기능: 이미지 (type = 'th')를 순차적으로 노출 -->
								<ul class="visual_img">
									<c:forEach var="promotion" items="${promotions}">
										<li><img alt=${promotion.product_id }
											src="${promotion.productImageUrl }"></li>
									</c:forEach>
								</ul>
							</div>
							<span class="nxt_fix" style="display: none;"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item" data-category="0"><a class="anchor active">
							<span>전체리스트</span>
					</a></li>
					<c:forEach var="category" items="${categories}"
						varStatus="status">
						<li class="item"
							data-category=<c:out value="${category.id }"/>><a
							class="anchor"> <span><c:out
										value="${category.name }" /></span>
						</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="section_event_lst">
				<p class="event_lst_txt">
					바로 예매 가능한 행사가 <span class="pink">${totalProductCount}개</span> 있습니다
				</p>
				<div class="wrap_event_box">
					<!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
					<ul class="lst_event_box">
						<c:forEach var="product_items" items="${product_list}"
							varStatus="status">
							<c:if test="${status.index%2==0}">
								<li class="item"><a
									href="detail?id=${product_items.productId}"
									class="item_book">
										<div class="item_preview">
											<img alt=<c:out value="${product_items.productContent }" />
												class="img_thumb"
												src="<c:out value="${product_items.productImageUrl }"/>" />
											<span class="img_border"></span>
										</div>
										<div class="event_txt">
											<h4 class="event_txt_tit">
												<span><c:out
														value="${product_items.productDescription }" /></span> <small
													class="sm"> <c:out
														value="${product_items.placeName }" /></small>
											</h4>
											<p class="event_txt_dsc">
												<c:out value="${product_items.productContent }" />
											</p>
										</div>
								</a></li>
							</c:if>
						</c:forEach>
					</ul>
					<ul class="lst_event_box">
						<c:forEach var="product_items" items="${product_list}"
							varStatus="status">
							<c:if test="${status.index%2!=0}">
								<li class="item"><a
									href="detail?id=${product_items.productId}"
									class="item_book">
										<div class="item_preview">
											<img alt=<c:out value="${product_items.productContent }" />
												class="img_thumb"
												src="<c:out value="${product_items.productImageUrl }"/>" />
											<span class="img_border"></span>
										</div>
										<div class="event_txt">
											<h4 class="event_txt_tit">
												<span><c:out
														value="${product_items.productDescription }" /></span> <small
													class="sm"> <c:out
														value="${product_items.placeName }" /></small>
											</h4>
											<p class="event_txt_dsc">
												<c:out value="${product_items.productContent }" />
											</p>
										</div>
								</a></li>
							</c:if>
						</c:forEach>
					</ul>
					<!-- 더보기 -->
					<div class="more">
						<button class="btn">
							<span>더보기</span>
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
	<div class="footer">
		<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불
			등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
		<span class="copyright">© NAVER Corp.</span>
	</div>
	</footer>
	<script type="text/javascript" src="js/mainPage.js" defer></script>
	<script>
	function sendAjax(url,clickedName){
		var oReq = new XMLHttpRequest();
		oReq.open("GET", url);	// method: GET 
		oReq.setRequestHeader("Content-Type", "application/json"); // Content-Type: json
		oReq.responseType = "text";		// text for json
		oReq.addEventListener("load", function () { // when success
			console.log(this);
			if( clickedName === "tab" ) 	{	makeHtmlContent(url, this);	  }  // TABUI 기능
			else if( clickedName === "more" ) {	addhtmlcontent(url, this);  } // 더보기 기능
		});
		oReq.send();
	}
		var cateogorytabmenu = document.querySelector(".event_tab_lst");
		cateogorytabmenu.addEventListener("click", function(evt) {
			var categoryId;
			if( evt.target.tagName === "LI" ) {
				categoryId = evt.target.dataset.category;
			} else if ( evt.target.tagName === "UL" ) {
				categoryId = evt.target.firstChild.dataset.category;
			} else if ( evt.target.tagName === "A" ) {
				categoryId = evt.target.parentElement.dataset.category;
			} else if( evt.target.tagName === "SPAN" ) {
				categoryId = evt.target.parentElement.parentElement.dataset.category;
			}
			
			//document.getElementById("start").value = 0;
			sendAjax("/reservation/maincontents?categoryId="+categoryId, "tab");
		});
		
		var morebtn = document.querySelector(".more");
		
		morebtn.addEventListener("click", function() {
			var categoryId;
			var startNum;
			var activeEle = document.querySelector(".active");
			console.log(activeEle);
			categoryId = activeEle.parentElement.dataset.category;
			console.log(categoryId);
			startNum = document.querySelectorAll(".item_preview").length;
			console.log(startNum);
			sendAjax("/reservation/maincontents?categoryId="+categoryId+"&start="+startNum, "more");
		});
		
		function makeHtmlContent(url,res) {
			// debugger;
			/* 1) 결과 데이터 값 json으로 받기 */
			var resJson = JSON.parse(res.responseText);

			/* 2) 결과 - HTML 그려주기 */
			// 1. 카테고리 변경
			var categoryId = resJson.categoryId
			drawCategoryHtml(resJson,categoryId); // 카테고리 탭 변경
			
			// 2. 상품 리스트
			Array.from(document.querySelectorAll(".lst_event_box")).forEach( v => {	v.remove();	}); // 기존 상품 Element 모두 제거
			drawProductHtml(resJson); // 카테고리별 상품 화면에 추가(기본 4개)
			
			// 3. product 갯수
			document.querySelector(".pink").innerHTML = resJson.totalProductCount+"개";
			
			// 4. 더보기 탭
			if( resJson.morebtn ) {
				document.querySelector(".more").style.display = "block";
			} else {
				document.querySelector(".more").style.display = "none";
			}
		}
		
		// Category HTML 동적 생성
		function drawCategoryHtml(resJson,categoryId) {
			var categoryhtml = document.getElementById("categoryItem").innerHTML;
			var categoryResult = document.getElementById("totalCategoryItem").innerHTML;;
			
			for(var i=0; i<resJson.categories.length; i++) {
				categoryResult += categoryhtml.replace("{categoryId}", resJson.categories[i].id)
									.replace("{className}","anchor")
									.replace("{categoryName}", resJson.categories[i].name)
				
			}
			document.querySelector(".event_tab_lst").innerHTML = categoryResult;
			
			Array.from(document.querySelector(".tab_lst_min").querySelectorAll("li")).forEach( a => {
				a.firstElementChild.className = (a.dataset.category == categoryId) ? "anchor active" : "anchor"; 
			});
		}
		
		function drawProductHtml(resJson) {
			var producthtml = document.getElementById("productItem").innerHTML;
			console.log("프로덕트리스트 길이 : "+resJson.product_list.length);
			var result = "<ul class='lst_event_box'>";
				for(var i=0; i<resJson.product_list.length; i+=2) {
					
						result += producthtml.replace("{id}", resJson.product_list[i].displayInfoId)
						.replace("{alt}", resJson.product_list[i].productContent)
						.replace("{content}", resJson.product_list[i].productContent)
						.replace("{description}", resJson.product_list[i].productDescription)
						.replace("{placeName}", resJson.product_list[i].placeName)
						.replace("{productImageUrl}", resJson.product_list[i].productImageUrl);
				}
				result += "</ul>";
				document.querySelector(".more").insertAdjacentHTML("beforebegin",result);
				result = "<ul class='lst_event_box'>";
				for(var i=1; i<resJson.product_list.length; i+=2) {
					
					result += producthtml.replace("{id}", resJson.product_list[i].displayInfoId)
					.replace("{alt}", resJson.product_list[i].productContent)
					.replace("{content}", resJson.product_list[i].productContent)
					.replace("{description}", resJson.product_list[i].productDescription)
					.replace("{placeName}", resJson.product_list[i].placeName)
					.replace("{productImageUrl}", resJson.product_list[i].productImageUrl);
				}
				result += "</ul>";
				document.querySelector(".more").insertAdjacentHTML("beforebegin",result);
			}
		
		function addhtmlcontent(url, res) {
			var resJson = JSON.parse(res.responseText);
			drawProductHtml(resJson); // 카테고리별 상품 화면에 추가(기본 4개)
			
			if( resJson.morebtn ) {
				document.querySelector(".more").style.display = "block";
			} else {
				document.querySelector(".more").style.display = "none";
			}
		}
		
	</script>
	<script type="totalCategory-template" id="totalCategoryItem">
	<li class="item" data-category=0>
		<a class="anchor"> <span>전체리스트</span></a>
	</li>
	</script>
	<script type="rv-template" id="categoryItem">
	<li class="item" data-category={categoryId}>
	  	<a class="{className}"> <span>{categoryName}</span></a>
	</li>
	</script>


	<script type="rv-template" id="productItem">
    <li class="item">
       <a href="detail?id={id}" class="item_book">
            <div class="item_preview">
               <img alt="{alt}" class="img_thumb" src="{productImageUrl}">
               <span class="img_border"></span>
            </div>
            <div class="event_txt">
                <h4 class="event_txt_tit"><span>{description}</span> <small class="sm">{placeName}</small></h4>
                <p class="event_txt_dsc">{content}</p>
            </div>
        </a>
    </li>
</script>
</body>
</html>