package kr.or.connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	ReservationDao reservationDao;
	
	@Transactional
	@Override
	public ReservationInfoResponse getReservationInfo(String reservationEmail) {
		
		/*
		reservationEmail 기반으로 reservation 정보를 받아온다.
		reservationinfo에는 예약 정보들과 displayinfo
		param 값으로 reservationinfo 받아온다.
		
		*/
		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
		List<ReservationInfo> reservations = reservationDao.getReservations(reservationEmail);
		reservations = reservationDao.setReservationsAsDisplayInfo(reservations);
		
		int size = 0;
		size = reservations.size();
		
		reservationInfoResponse.setReservations(reservations);
		reservationInfoResponse.setSize(size);
		
		return reservationInfoResponse;
	}
}
