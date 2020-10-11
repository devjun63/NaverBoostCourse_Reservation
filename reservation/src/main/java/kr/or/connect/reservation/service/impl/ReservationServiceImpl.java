package kr.or.connect.reservation.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationDao;
import kr.or.connect.reservation.dto.ProductPrice;
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

	@Override
	public String getReserveStartDate() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
        cal.setTime(date);
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy.MM.dd.(EEE)", Locale.KOREAN);
        String reserveStartDate = formatTime.format(cal.getTime());
		return reserveStartDate;
	}

	@Override
	public String getReserveEndDate() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
        cal.setTime(date);
        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy.MM.dd.(EEE)", Locale.KOREAN);
        cal.add(Calendar.DATE, 4);
        String reserveEndDate = formatTime.format(cal.getTime());
		return reserveEndDate;
	}

	@Override
	public List<ProductPrice> getProductPrices(Integer displayInfoId) {
		List<ProductPrice> productPrice = reservationDao.getProductPrices(displayInfoId);
		return productPrice;
	}

	/*@Override
	public List<ProductPrice> getFormatPrices(List<ProductPrice> productPrices) {

		for(int i= 0; i < productPrices.size(); i++)
		{
			DecimalFormat formatter = new DecimalFormat("###,###.##");
			int tempPrice = productPrices.get(i).getPrice();
			tempPrice = Integer.valueOf(formatter.format(tempPrice));
			productPrices.get(i).setPrice(tempPrice);
		}
		
		return productPrices;
	}*/
}
