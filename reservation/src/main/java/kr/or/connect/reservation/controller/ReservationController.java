package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.protobuf.Method;

import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@Autowired
	DetailService detailService;
	
	@GetMapping("/myreservation")
	public String myreservation(@RequestParam(name="resrv_email", required=true)String reservationEmail, ModelMap model) {
		
		ReservationInfoResponse reservationInfoResponse = reservationService.getReservationInfo(reservationEmail);
		Map<String, Object> map = new HashMap<>();
		if(reservationInfoResponse == null) {
			map.put("size", 0);
		}else {
			map.put("reservations", reservationInfoResponse.getReservations());
			map.put("size", reservationInfoResponse.getSize());
		}
		model.addAllAttributes(map);
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
		String reserveStartDate = reservationService.getReserveStartDate();
		String reserveEndDate = reservationService.getReserveEndDate();
		String reserveRandomDate = reservationService.getReserveRandomDate();
		
		map.put("displayInfoId", displayInfoId);
		map.put("productImages", displayInfoResponse.getProductImages());
		map.put("displayInfo", displayInfoResponse.getDisplayInfo());
		map.put("productPrices", productPrices);
        map.put("reserveStartDate", reserveStartDate);
        map.put("reserveEndDate", reserveEndDate);
		map.put("reserveRandomDate", reserveRandomDate);
        
        model.addAllAttributes(map);
		return "reserve";
	}
	
	@PostMapping(value="/reserve")
	public String reserve() {
		
		return "redirect:./";
	}
}
