package kr.or.connect.reservation.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.service.DetailService;

@Service
public class DetailServiceImpl implements DetailService{

	@Override
	@Transactional
	public DisplayInfoResponse getDisplayInfoResponse(Integer displayInfoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
