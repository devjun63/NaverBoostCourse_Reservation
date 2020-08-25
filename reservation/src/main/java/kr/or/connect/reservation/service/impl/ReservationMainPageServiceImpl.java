package kr.or.connect.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReservationMainPageDao;
import kr.or.connect.reservation.service.ReservationMainPageService;

@Service
public class ReservationMainPageServiceImpl implements ReservationMainPageService {

	@Autowired
	ReservationMainPageDao reservationMainPageDao;
	
	@Override
	public int getProductsCountFromAllCategorys() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfProductsCountFromCategory(int category_id) {
		int productsCount = reservationMainPageDao.selectCount(category_id);
		return productsCount;
	}

}
