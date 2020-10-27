package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.dto.ReservationResponse;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@Autowired
	DetailService detailService;
	
	@PutMapping(value = "/cancleReservation/{reservationInfoId}")
	@ResponseBody
	public ReservationResponse cancleReservation(@PathVariable(name = "reservationInfoId") Integer reservationInfoId) {
		System.out.println("예약 : "+reservationInfoId);
		ReservationResponse reservationResponse = reservationService.cancelReservation(reservationInfoId);
		System.out.println("취소 여부 : " + reservationResponse.isCancelYn());
		System.out.println("예약 아이디 : " + reservationResponse.getReservationInfoId());
		System.out.println("예약 이메일 : " + reservationResponse.getReservationEmail());
		return reservationResponse; 	
	}

	
	@PostMapping(path = "/checkReservation")
	@ResponseBody
	public String checkReservation(@RequestBody String reservationEmail) {
		ReservationInfoResponse reservationInfoResponse = reservationService.getReservationInfo(reservationEmail);
		Map<String, Object> map = new HashMap<>();
		if(reservationInfoResponse == null) {
			map.put("size", 0);
			return "0";
		}else {
			System.out.println(reservationInfoResponse.getReservations().get(0).getReservationName());
			map.put("size", reservationInfoResponse.getSize());
			map.put("reservations",reservationInfoResponse.getReservations());
		}
		return "1";
	}
	
	@GetMapping("/myreservation")
	public String myreservePage(@RequestParam String reservationEmail, ModelMap model, HttpSession session) {
		
		ReservationInfoResponse reservationInfoResponse = reservationService.getReservationInfo(reservationEmail);
		System.out.println("내 예약 이메일"+reservationEmail);
		List<ReservationInfo> reservations = new ArrayList<>();
		List<ReservationInfo> cancelReservations = new ArrayList<>();
		List<ReservationInfo> completeReservations = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		Date currentDate = new Date();
		for(int i = 0; i < reservationInfoResponse.getReservations().size(); i++) {
			Date getDate = reservationInfoResponse.getReservations().get(i).getReservationDate();
			int result = currentDate.compareTo(getDate);
			if(reservationInfoResponse.getReservations().get(i).isCancelYn() == true) {
				cancelReservations.add(reservationInfoResponse.getReservations().get(i));
			}else {
				if(result < 0) {
					reservations.add(reservationInfoResponse.getReservations().get(i));
				}else {
					completeReservations.add(reservationInfoResponse.getReservations().get(i));
				}
			}
		}
		map.put("size", reservationInfoResponse.getSize());
		map.put("reservations", reservations);
		map.put("completeReservations", completeReservations);
		map.put("cancelReservations", cancelReservations);
		map.put("reservationEmail", reservationEmail);
		model.addAllAttributes(map);
		session.setAttribute("reserveEmail", reservationEmail);
		return "myreservation";
	}

	@GetMapping("/reservePage")
	public String reservePage(@RequestParam(name="id", required=true) Integer displayInfoId, ModelMap model) {
		Map<String, Object> map = new HashMap<>();
		// detailPage의 이미지 한장을 가져와야 하는데 detailService의 메서드를 사용해서 response 전체를 가져오는 것은 효율적이지 않을 것 같다.
		// 하지만 당장 만들어 놓은 서비스가 있는데 사용하지 않는것도 이상하다고 생각... 따로 메서드를 만든다? 어짜피 displayInfoId는 가져오기에 아무사진 한장만 가져오면 됨...
		// 혹은 reservationService에 detailService에 있는 메서드를 차용해도 무관하다.
		// 일단은 기존 서비스에 존재하는 메서드를 차용해서 진행 후 리팩토링
		DisplayInfoResponse displayInfoResponse = detailService.getDisplayInfoResponse(displayInfoId);
		List<ProductPrice> productPrices = reservationService.getProductPrices(displayInfoId);
		String productId = String.valueOf((displayInfoResponse.getDisplayInfo().getProductId()));
		String reserveStartDate = reservationService.getReserveStartDate();
		String reserveEndDate = reservationService.getReserveEndDate();
		String reserveRandomDate = reservationService.getReserveRandomDate();
		
		map.put("displayInfoId", displayInfoId);
		map.put("productId", productId);
		map.put("productImages", displayInfoResponse.getProductImages());
		map.put("displayInfo", displayInfoResponse.getDisplayInfo());
		map.put("productPrices", productPrices);
		map.put("reserveStartDate", reserveStartDate);
		map.put("reserveEndDate", reserveEndDate);
		map.put("reserveRandomDate", reserveRandomDate);

		model.addAllAttributes(map);
		return "reserve";
	}

	
	@PostMapping(path = "/reserve")
	public ReservationResponse setReservations(@RequestBody ReservationParam param){
		 return reservationService.setReservation(param);
	}
}
