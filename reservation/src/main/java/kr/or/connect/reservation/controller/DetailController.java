package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.service.DetailService;

@Controller
@RequestMapping(path="")
public class DetailController {

	@Autowired
	DetailService detailService;
	
	@GetMapping(path = "/detail")
	public String detail(@RequestParam(name="id", required=false, defaultValue="0") Integer displayInfoId, ModelMap model) {
		
		DisplayInfoResponse displayInfoResponse = detailService.getDisplayInfoResponse(displayInfoId);
		Map<String, Object> map = new HashMap<>();
		map.put("displayInfo", displayInfoResponse.getDisplayInfo());
		if(displayInfoResponse.getProductImages().size() > 1) {
			map.put("etc", "hasEtc");
		}else {
			map.put("etc", "notEtc");
		}
		map.put("productImages", displayInfoResponse.getProductImages());
		map.put("displayInfoImage", displayInfoResponse.getDisplayInfoImage());
		map.put("comments", displayInfoResponse.getComments());
		map.put("averageScore", (Double)displayInfoResponse.getAverageScore());    
		map.put("productPrices", displayInfoResponse.getProductPrices());
		map.put("displayInfoId", displayInfoId);
		model.addAllAttributes(map);
		return "detail";
	}
	
	@GetMapping(path = "/review/{id}")
	public String review(@PathVariable("id") Integer displayInfoId, ModelMap model) {
		
		DisplayInfoResponse displayInfoResponse = detailService.getDisplayInfoResponse(displayInfoId);
		Map<String, Object> map = new HashMap<>();
		map.put("displayInfoId", displayInfoId);
		map.put("comments", displayInfoResponse.getComments());
		map.put("averageScore", (Double)displayInfoResponse.getAverageScore());    
		model.addAllAttributes(map);
		return "review";
	}
	
	@GetMapping(path="/info_tab_list", produces = "application/json; charset=utf-8")
	@ResponseBody 
	public Map<String,Object> content(@RequestParam("displayInfoId")Integer displayInfoId)
	{
		DisplayInfoResponse displayInfoResponse = detailService.getDisplayInfoResponse(displayInfoId);
		Map<String, Object> map = new HashMap<>();
		map.put("displayInfoId", displayInfoId);
		map.put("displayInfoImage", displayInfoResponse.getDisplayInfoImage());
		map.put("displayInfo", displayInfoResponse.getDisplayInfo());
		return map;
	}
}
