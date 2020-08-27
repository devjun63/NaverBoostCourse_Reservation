package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.reservation.service.MainPageService;


@Controller
@RequestMapping(path="/mainPage")
public class MainPageController {
	
	@Autowired
	MainPageService reservationMainPageService;

	@GetMapping("/api")
	public String mainPage(@RequestParam(name="category_id", required=false, defaultValue="1") Integer category_id,
			ModelMap model) {
		System.out.println("카테고리 아이디 값 체크 : " + category_id);
		int count = reservationMainPageService.getNumberOfProductsCountFromCategory(category_id);
		System.out.println(count);
		model.addAttribute("count", count);
		return "mainTestPage";
	}


}
