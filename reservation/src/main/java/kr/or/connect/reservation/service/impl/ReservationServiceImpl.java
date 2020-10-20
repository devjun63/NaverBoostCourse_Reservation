package kr.or.connect.reservation.service.impl;

import java.text.ParseException;
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
import kr.or.connect.reservation.dao.ReservationPriceDao;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.dto.ReservationPrice;
import kr.or.connect.reservation.dto.ReservationResponse;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	ReservationDao reservationDao;
	
	@Autowired
	ReservationPriceDao reservationPriceDao;
	

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
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREAN);
		String reserveStartDate = formatTime.format(cal.getTime());
		return reserveStartDate;
	}

	@Override
	public String getReserveEndDate() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREAN); //"yyyy.MM.dd.(EEE)" -> 연.월.일.(요일)
		cal.add(Calendar.DATE, 4);
		String reserveEndDate = formatTime.format(cal.getTime());
		return reserveEndDate;
	}

	@Override
	public String getReserveRandomDate() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREAN); //"yyyy.MM.dd.(EEE)" -> 연.월.일.(요일)
		cal.add(Calendar.DATE, (int)((Math.random() * 5)+1));
		String reserveEndDate = formatTime.format(cal.getTime());
		return reserveEndDate;
	}

	@Override
	public List<ProductPrice> getProductPrices(Integer displayInfoId) {
		List<ProductPrice> productPrice = reservationDao.getProductPrices(displayInfoId);
		return productPrice;
	}

	@Override
	public int deleteReservationInfo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public ReservationResponse setReservation(ReservationParam param) {
		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setCreateDate(new Date());
		reservationResponse.setCancelYn(false);
		reservationResponse.setDisplayInfoId(param.getDisplayInfoId());
		reservationResponse.setModifyDate(new Date());
		reservationResponse.setProductId(param.getProductId());
		
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy.mm.dd");
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date tempDate = null;
		try {
			tempDate = beforeFormat.parse(param.getReservationYearMonthDay());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
		String transDate = afterFormat.format(tempDate);

		// 반환된 String 값을 Date로 변경한다.
		java.sql.Date reserveDate = java.sql.Date.valueOf(transDate); 
		reservationResponse.setReservationDate(reserveDate);

		reservationResponse.setReservationEmail(param.getReservationEmail());
		reservationResponse.setReservationName(param.getReservationName());
		reservationResponse.setReservationTelephone(param.getReservationTelephone());
		Integer reservationInfoId = reservationDao.insertReservationResponse(reservationResponse);
		System.out.println("예약 아이디 : "+reservationInfoId);
		
		List<ReservationPrice> reservationPrices = new ArrayList<ReservationPrice>();
		for(int i = 0; i < param.getPrices().size(); i++) {
			ReservationPrice reservationPrice = new ReservationPrice();
			reservationPrice.setCount(param.getPrices().get(i).getCount());
			reservationPrice.setProductPriceId(param.getPrices().get(i).getProductPriceId());
			reservationPrice.setReservationInfoId(reservationInfoId.intValue());
			Integer reservationInfoPriceId = reservationPriceDao.insertReservationPrice(reservationPrice);
			reservationPrice.setReservationInfoPriceId(reservationInfoPriceId.intValue());
			reservationPrices.add(reservationPrice);
		}
		
		reservationResponse.setPrices(reservationPrices);
		return reservationResponse;
		
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
