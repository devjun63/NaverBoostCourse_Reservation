<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta
      name="description"
      content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다."
    />
    <meta
      name="viewport"
      content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no"
    />
    <title>네이버 예약</title>
    <link href="css/style.css" rel="stylesheet" />
  </head>

  <body>
    <div id="container">
      <div class="header">
        <header class="header_tit">
          <h1 class="logo">
            <a href="./" class="lnk_logo" title="네이버">
              <span class="spr_bi ico_n_logo">네이버</span>
            </a>
            <a href="./" class="lnk_logo" title="예약">
              <span class="spr_bi ico_bk_logo">예약</span>
            </a>
          </h1>
          <a href="#" class="btn_my">
            <span title="내예약" class="viewReservation">${reservationEmail }</span>
            <span title="로그아웃" class="logout"> / 로그아웃</span>
          </a>
        </header>
      </div>
      <hr />
      <div class="ct">
        <div class="section_my">
          <!-- 예약 현황 -->
          <div class="my_summary">
            <ul class="summary_board">
              <li class="item">
                <!--[D] 선택 후 .on 추가 link_summary_board -->
                <a href="#" class="link_summary_board on">
                  <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em>
                  <span class="figure">${size }</span>
                </a>
              </li>
              <li class="item">
                <a href="#" class="link_summary_board">
                  <i class="spr_book2 ico_book_ss"></i>
                  <em class="tit">이용예정</em> <span class="figure">
                  <c:choose>
                  	<c:when test="${!empty reservations }">
                  		${fn:length(reservations) }
                  	</c:when>
                  	<c:otherwise>
                  	0
                  	</c:otherwise>
                  </c:choose>
                  </span>
                </a>
              </li>
              <li class="item">
                <a href="#" class="link_summary_board">
                  <i class="spr_book2 ico_check"></i>
                  <em class="tit">이용완료</em> <span class="figure">
                  <c:choose>
                  	<c:when test="${!empty completeReservations}">
                  		${fn:length(completeReservations) }
                  	</c:when>
                  	<c:otherwise>
                  	0
                  	</c:otherwise>
                  </c:choose>
                  </span>
                </a>
              </li>
              <li class="item">
                <a href="#" class="link_summary_board">
                  <i class="spr_book2 ico_back"></i>
                  <em class="tit">취소·환불</em> <span class="figure">
                  <c:choose>
                  	<c:when test="${!empty cancelReservations}">
                  		${fn:length(cancelReservations) }
                  	</c:when>
                  	<c:otherwise>
                  	0
                  	</c:otherwise>
                  </c:choose>
                  </span>
                </a>
              </li>
            </ul>
          </div>
          <!--// 예약 현황 -->

          <!-- 내 예약 리스트 -->
          <div class="wrap_mylist">
            <ul class="list_cards" ng-if="bookedLists.length > 0">
            <c:if test="${reservations ne null}">
            	<c:forEach var="reservations" items="${reservations}" varStatus="status">
            		<li class="card confirmed">
                <div class="link_booking_details">
                  <div class="card_header">
                    <div class="left"></div>
                    <div class="middle">
                      <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                      <i class="spr_book2 ico_check2"></i>
                      <span class="tit">예약 확정</span>
                    </div>
                    <div class="right"></div>
                  </div>
                </div>
                <article class="card_item">
                  <a href="#" class="link_booking_details">
                    <div class="card_body">
                      <div class="left"></div>
                      <div class="middle">
                        <div class="card_detail">
                          <em class="booking_number">
                          No.${reservations.reservationInfoId}<!-- 7자리 fmt -->
                          </em>
                          <h4 class="tit">
                          <c:forEach var="displayInfo" items="${reservations.displayInfo}" begin="0" end="0">
                              		${displayInfo.categoryName} / ${displayInfo.productDescription}
                          </c:forEach>
                          </h4>
                          <ul class="detail">
                            <li class="item">
                              <span class="item_tit">일정</span>
                              <em class="item_dsc">
                                <fmt:formatDate value="${reservations.createDate }" pattern="yyyy.MM.dd.(EEE)"/>
                                ~
                                <fmt:formatDate value="${reservations.reservationDate }" pattern="yyyy.MM.dd.(EEE)"/>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">내역</span>
                              <em class="item_dsc"> 
                              <c:forEach var="displayInfo" items="${reservations.displayInfo}" begin="0" end="0">
                              		${displayInfo.productContent}
                              </c:forEach>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">장소</span>
                              <em class="item_dsc">
                              <c:forEach var="displayInfo" items="${reservations.displayInfo}" begin="0" end="0">
                              		${displayInfo.placeName}
                              </c:forEach>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">업체</span>
                              <em class="item_dsc">
                              	<c:forEach var="displayInfo" items="${reservations.displayInfo}" begin="0" end="0">
                              		<c:choose>
                              			<c:when test="${!empty displayInfo.homepage}">
                              				${displayInfo.homepage}
                              			</c:when>
                              			<c:otherwise>
                              				업체명이 없습니다.
                              			</c:otherwise>
                              		</c:choose>
                              </c:forEach>
                              </em>
                            </li>
                          </ul>
                          <div class="price_summary">
                            <span class="price_tit">결제 예정금액</span>
                            <em class="price_amount">
                              <span>
                              <fmt:formatNumber type="number" maxFractionDigits="3" value="${reservations.totalPrice}" />
                              </span>
                              <span class="unit">원</span>
                            </em>
                          </div>
                          <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
                          <div class="booking_cancel" data-reserveid="${reservations.reservationInfoId}">
                            <button class="btn"><span>취소</span></button>
                          </div>
                        </div>
                      </div>
                      <div class="right"></div>
                    </div>
                    <div class="card_footer">
                      <div class="left"></div>
                      <div class="middle"></div>
                      <div class="right"></div>
                    </div>
                  </a>
                  <a
                    href="#"
                    class="fn fn-share1 naver-splugin btn_goto_share"
                    title="공유하기"
                  ></a>
                </article>
              	</li>
            	</c:forEach>
            </c:if>
            <c:if test="${completeReservations ne null}">
            	<c:forEach var="completeReservations" items="${completeReservations}" varStatus="status">
            		<li class="card used">
                <div class="link_booking_details">
                  <div class="card_header">
                    <div class="left"></div>
                    <div class="middle">
                      <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                      <i class="spr_book2 ico_check2"></i>
                      <span class="tit">이용 완료</span>
                    </div>
                    <div class="right"></div>
                  </div>
                </div>
                <article class="card_item">
                  <a href="#" class="link_booking_details">
                    <div class="card_body">
                      <div class="left"></div>
                      <div class="middle">
                        <div class="card_detail">
                          <em class="booking_number">No.${completeReservations.reservationInfoId}</em>
                          <h4 class="tit">
                          	<c:forEach var="displayInfo" items="${completeReservations.displayInfo}" begin="0" end="0">
                              		${displayInfo.categoryName} / ${displayInfo.productDescription}
                          </c:forEach>
                          </h4>
                          <ul class="detail">
                            <li class="item">
                              <span class="item_tit">일정</span>
                              <em class="item_dsc">
                                <fmt:formatDate value="${completeReservations.createDate }" pattern="yyyy.MM.dd.(EEE)"/>
                                ~
                                <fmt:formatDate value="${completeReservations.reservationDate }" pattern="yyyy.MM.dd.(EEE)"/>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">내역</span>
                              <em class="item_dsc"> 
                              <c:forEach var="displayInfo" items="${completeReservations.displayInfo}" begin="0" end="0">
                              		${displayInfo.productContent}
                              </c:forEach>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">장소</span>
                              <em class="item_dsc">
                              <c:forEach var="displayInfo" items="${completeReservations.displayInfo}" begin="0" end="0">
                              		${displayInfo.placeName}
                              </c:forEach>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">업체</span>
                              <em class="item_dsc">
                              	<c:forEach var="displayInfo" items="${completeReservations.displayInfo}" begin="0" end="0">
                              		<c:choose>
                              			<c:when test="${!empty displayInfo.homepage}">
                              				${displayInfo.homepage}
                              			</c:when>
                              			<c:otherwise>
                              				업체명이 없습니다.
                              			</c:otherwise>
                              		</c:choose>
                              </c:forEach>
                              </em>
                            </li>
                          </ul>
                          <div class="price_summary">
                            <span class="price_tit">결제 예정금액</span>
                            <em class="price_amount">
                              <span>
                              <fmt:formatNumber type="number" maxFractionDigits="3" value="${completeReservations.totalPrice}" />
                              </span>
                              <span class="unit">원</span>
                            </em>
                          </div>
                          <div class="booking_cancel">
                            <a href="./reviewWrite.html"><button class="btn">
                                <span>예매자 리뷰 남기기</span>
                              </button></a>
                          </div>
                        </div>
                      </div>
                      <div class="right"></div>
                    </div>
                    <div class="card_footer">
                      <div class="left"></div>
                      <div class="middle"></div>
                      <div class="right"></div>
                    </div>
                  </a>
                </article>
              </li>
            	</c:forEach>
            </c:if>
            <c:if test="${cancelReservations ne null}">
            	<c:forEach var="cancelReservations" items="${cancelReservations}" varStatus="status">
            		<li class="card used cancel">
                <div class="link_booking_details">
                  <div class="card_header">
                    <div class="left"></div>
                    <div class="middle">
                      <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                      <i class="spr_book2 ico_cancel"></i>
                      <span class="tit">취소된 예약</span>
                    </div>
                    <div class="right"></div>
                  </div>
                </div>
                <article class="card_item">
                  <a href="#" class="link_booking_details">
                    <div class="card_body">
                      <div class="left"></div>
                      <div class="middle">
                        <div class="card_detail">
                          <em class="booking_number">No.${cancelReservations.reservationInfoId}</em>
                          <h4 class="tit">서비스명/상품명</h4>
                          <ul class="detail">
                            <li class="item">
                              <span class="item_tit">일정</span>
                              <em class="item_dsc">
                                <fmt:formatDate value="${cancelReservations.createDate }" pattern="yyyy.MM.dd.(EEE)"/>
                                ~
                                <fmt:formatDate value="${cancelReservations.reservationDate }" pattern="yyyy.MM.dd.(EEE)"/>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">내역</span>
                              <em class="item_dsc"> 
                              <c:forEach var="displayInfo" items="${cancelReservations.displayInfo}" begin="0" end="0">
                              		${displayInfo.productContent}
                              </c:forEach>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">장소</span>
                              <em class="item_dsc">
                              <c:forEach var="displayInfo" items="${cancelReservations.displayInfo}" begin="0" end="0">
                              		${displayInfo.placeName}
                              </c:forEach>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">업체</span>
                              <em class="item_dsc">
                              	<c:forEach var="displayInfo" items="${cancelReservations.displayInfo}" begin="0" end="0">
                              		<c:choose>
                              			<c:when test="${!empty displayInfo.homepage}">
                              				${displayInfo.homepage}
                              			</c:when>
                              			<c:otherwise>
                              				업체명이 없습니다.
                              			</c:otherwise>
                              		</c:choose>
                              </c:forEach>
                              </em>
                            </li>
                          </ul>
                          <div class="price_summary">
                            <span class="price_tit">결제 예정금액</span>
                            <em class="price_amount">
                              <span>
                              <fmt:formatNumber type="number" maxFractionDigits="3" value="${cancelReservations.totalPrice}" />
                              </span>
                              <span class="unit">원</span>
                            </em>
                          </div>
                        </div>
                      </div>
                      <div class="right"></div>
                    </div>
                    <div class="card_footer">
                      <div class="left"></div>
                      <div class="middle"></div>
                      <div class="right"></div>
                    </div>
                  </a>
                </article>
              </li>
            	</c:forEach>
            </c:if>
              <!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
              <li class="card" style="display: none">
                <div class="link_booking_details">
                  <div class="card_header">
                    <div class="left"></div>
                    <div class="middle">
                      <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book2 -->
                      <i class="spr_book2 ico_clock"></i>
                      <span class="tit">예약 신청중</span>
                    </div>
                    <div class="right"></div>
                  </div>
                </div>
                <article class="card_item">
                  <a href="#" class="link_booking_details">
                    <div class="card_body">
                      <div class="left"></div>
                      <div class="middle">
                        <div class="card_detail">
                          <em class="booking_number">No.0000001</em>
                          <h4 class="tit">서비스명/상품명1</h4>
                          <ul class="detail">
                            <li class="item">
                              <span class="item_tit">일정</span>
                              <em class="item_dsc">
                                2000.0.00.(월)2000.0.00.(일)
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">내역</span>
                              <em class="item_dsc"> 내역이 없습니다. </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">장소</span>
                              <em class="item_dsc"> 내역이 없습니다. </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">업체</span>
                              <em class="item_dsc"> 업체명이 없습니다. </em>
                            </li>
                          </ul>
                          <div class="price_summary">
                            <span class="price_tit">결제 예정금액</span>
                            <em class="price_amount">
                              <span>000,000,000</span>
                              <span class="unit">원</span>
                            </em>
                          </div>
                          <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
                          <div class="booking_cancel">
                            <button class="btn"><span>취소</span></button>
                          </div>
                        </div>
                      </div>
                      <div class="right"></div>
                    </div>
                    <div class="card_footer">
                      <div class="left"></div>
                      <div class="middle"></div>
                      <div class="right"></div>
                    </div>
                  </a>
                  <a
                    href="#"
                    class="fn fn-share1 naver-splugin btn_goto_share"
                    title="공유하기"
                  ></a>
                </article>
                <article class="card_item">
                  <a href="#" class="link_booking_details">
                    <div class="card_body">
                      <div class="left"></div>
                      <div class="middle">
                        <div class="card_detail">
                          <em class="booking_number">No.0000000</em>
                          <h4 class="tit">서비스명/상품명</h4>
                          <ul class="detail">
                            <li class="item">
                              <span class="item_tit">일정</span>
                              <em class="item_dsc">
                                2000.0.00.(월)2000.0.00.(일)
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">내역</span>
                              <em class="item_dsc"> 내역이 없습니다. </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">장소</span>
                              <em class="item_dsc"> 내역이 없습니다. </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">업체</span>
                              <em class="item_dsc"> 업체명이 없습니다. </em>
                            </li>
                          </ul>
                          <div class="price_summary">
                            <span class="price_tit">결제 예정금액</span>
                            <em class="price_amount">
                              <span>000,000,000</span>
                              <span class="unit">원</span>
                            </em>
                          </div>
                          <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
                          <div class="booking_cancel">
                            <button class="btn"><span>취소</span></button>
                          </div>
                        </div>
                      </div>
                      <div class="right"></div>
                    </div>
                    <div class="card_footer">
                      <div class="left"></div>
                      <div class="middle"></div>
                      <div class="right"></div>
                    </div>
                  </a>
                  <a
                    href="#"
                    class="fn fn-share1 naver-splugin btn_goto_share"
                    title="공유하기"
                  ></a>
                </article>
              </li>
            </ul>
          </div>
          <!--// 내 예약 리스트 -->

          <!-- 예약 리스트 없음 -->
          <!-- <div class="err">
            <i class="spr_book ico_info_nolist"></i>
            <h1 class="tit">예약 리스트가 없습니다</h1>
          </div> -->
          <!--// 예약 리스트 없음 -->
        </div>
      </div>
      <hr />
    </div>
    <footer>
      <div class="gototop">
        <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
      </div>
      <div id="footer" class="footer">
        <p class="dsc_footer">
          네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
          환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.
        </p>
        <span class="copyright">© NAVER Corp.</span>
      </div>
    </footer>

    <!-- 취소 팝업 -->
    <!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
    <div class="popup_booking_wrapper" style="display: none">
      <div class="dimm_dark" style="display: block"></div>
      <div class="popup_booking refund">
        <h1 class="pop_tit">
          <span>서비스명/상품명</span>
          <small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
        </h1>
        <div class="nomember_alert">
          <p>취소하시겠습니까?</p>
        </div>
        <div class="pop_bottom_btnarea">
          <div class="btn_gray">
            <a href="#" class="btn_bottom"><span>아니오</span></a>
          </div>
          <div class="btn_green">
            <a href="#" class="btn_bottom"><span>예</span></a>
          </div>
        </div>
        <!-- 닫기 -->
        <a href="#" class="popup_btn_close" title="close">
          <i class="spr_book2 ico_cls"></i>
        </a>
        <!--// 닫기 -->
      </div>
    </div>
    <!--// 취소 팝업 -->
    <script type="rv-template" id="canceltItem">
                <div class="link_booking_details">
                  <div class="card_header">
                    <div class="left"></div>
                    <div class="middle">
                      <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                      <i class="spr_book2 ico_cancel"></i>
                      <span class="tit">취소된 예약</span>
                    </div>
                    <div class="right"></div>
                  </div>
                </div>
                <article class="card_item">
                  <a href="#" class="link_booking_details">
                    <div class="card_body">
                      <div class="left"></div>
                      <div class="middle">
                        <div class="card_detail">
                          <em class="booking_number">No.{reservationInfoId}</em>
                          <h4 class="tit">서비스명/상품명</h4>
                          <ul class="detail">
                            <li class="item">
                              <span class="item_tit">일정</span>
                              <em class="item_dsc">
                                {createDate }
                                ~
                                {reservationDate }
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">내역</span>
                              <em class="item_dsc"> 
                              <c:forEach var="displayInfo" items="{displayInfo}" begin="0" end="0">
                              		{productContent}
                              </c:forEach>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">장소</span>
                              <em class="item_dsc">
                              <c:forEach var="displayInfo" items="{displayInfo}" begin="0" end="0">
                              		{placeName}
                              </c:forEach>
                              </em>
                            </li>
                            <li class="item">
                              <span class="item_tit">업체</span>
                              <em class="item_dsc">
                              	<c:forEach var="displayInfo" items="{displayInfo}" begin="0" end="0">
                              		<c:choose>
                              			<c:when test="{homepage}">
                              				{homepage}
                              			</c:when>
                              			<c:otherwise>
                              				업체명이 없습니다.
                              			</c:otherwise>
                              		</c:choose>
                              </c:forEach>
                              </em>
                            </li>
                          </ul>
                          <div class="price_summary">
                            <span class="price_tit">결제 예정금액</span>
                            <em class="price_amount">
                              <span>
                              {totalPrice}
                              </span>
                              <span class="unit">원</span>
                            </em>
                          </div>
                        </div>
                      </div>
                      <div class="right"></div>
                    </div>
                    <div class="card_footer">
                      <div class="left"></div>
                      <div class="middle"></div>
                      <div class="right"></div>
                    </div>
                  </a>
                </article>
              
	</script>
  </body>
  <script type="text/javascript">
  var logoutBtn = document.querySelector(".logout");
  logoutBtn.addEventListener('click', function(evt){
	  const checkLogout = confirm("로그아웃 하시겠습니까?");
	  if(checkLogout === true){
		  location.href = "/reservation/logout";
	  }
  });
  
  var cancelBtns = document.querySelectorAll(".booking_cancel");
  let i = 0;
  for(i; i < cancelBtns.length; i++){
	  cancelBtns[i].addEventListener('click', function(evt){
		  const checkCancle = confirm("정말로 취소하시겠습니까?");
		  var data = {}
		  let reserveId;
		  if(checkCancle === true){
			  if( evt.target.tagName === "DIV" ) {
				  reserveId = evt.target.dataset.reserveid;
				  data.reservationInfoId = evt.target.dataset.reserveid;
				} else if ( evt.target.tagName === "BUTTON" ) {
					reserveId = evt.target.parentElement.dataset.reserveid;
					data.reservationInfoId = evt.target.parentElement.dataset.reserveid;
				} else if (evt.target.tagName === "SPAN") {
					reserveId = evt.target.parentElement.parentElement.dataset.reserveid;
					data.reservationInfoId = evt.target.parentElement.dataset.reserveid;
				}
			  
			  evt.target.closest(".confirmed").remove();
			  
			  var json = JSON.stringify(data);
			  var url = "/reservation/cancleReservation/";
			  var xhr = new XMLHttpRequest();
			  xhr.open("PUT", url+reserveId, true);
			  xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
			  xhr.send(json);
			  xhr.onreadystatechange = function (e) {
				  if (xhr.readyState !== XMLHttpRequest.DONE) return;
				  if(xhr.status === 200) {
					  var resJson = JSON.parse(xhr.responseText);
				      console.log(resJson);
				    
				      drawCancelHtml(resJson);
				  } else {
				    console.log("Error!");
				  }
				};
			  
		  }
	  });
  }
  function drawCancelHtml(resJson) {
		var producthtml = document.getElementById("canceltItem").innerHTML;
		console.log("프로덕트리스트 길이 : "+resJson.product_list.length);
		var result = "<li class='card used cancel'>";
			for(var i=0; i<resJson.product_list.length; i+=2) {
				
					result += producthtml.replace("{reservationInfoId}", resJson.product_list[i].displayInfoId)
					.replace("{createDate}", resJson.product_list[i].productContent)
					.replace("{reservationDate}", resJson.product_list[i].productContent)
					.replace("{productContent}", resJson.product_list[i].productDescription)
					.replace("{placeName}", resJson.product_list[i].placeName)
					.replace("{homepage}", resJson.product_list[i].productImageUrl)
					.replace("{totalPrice}", resJson.product_list[i].productImageUrl);
			}
			result += "</li>";
			document.querySelector("footer").insertAdjacentHTML("beforebegin",result);
		}
  
	
  </script>
  
</html>
