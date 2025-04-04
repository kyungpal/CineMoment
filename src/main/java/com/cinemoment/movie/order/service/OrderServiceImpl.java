package com.cinemoment.movie.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cinemoment.movie.order.dao.OrderDAO;
import com.cinemoment.movie.order.vo.OrderVO;

@Service("orderService")
@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public List<OrderVO> MovieTitleList1() throws Exception{
		List<OrderVO> titleList1 = orderDAO.selectMovieTitleList1();
		return titleList1;
	}
	
	@Override
	public List<OrderVO> MovieTitleList2() throws Exception{
		List<OrderVO> titleList2 = orderDAO.selectMovieTitleList2();
		return titleList2;
	}

	@Override
	public List<OrderVO> selectSeatList1() throws Exception {
		List<OrderVO> seatList1 = orderDAO.seatStatusList1();
		return seatList1;
	}
	
	@Override
	public List<OrderVO> selectSeatList2() throws Exception {
		List<OrderVO> seatList2 = orderDAO.seatStatusList2();
		return seatList2;
	}
	
	@Override
	public List<OrderVO> selectSeatList3() throws Exception {
		List<OrderVO> seatList3 = orderDAO.seatStatusList3();
		return seatList3;
	}
	
	@Override
	public void addOrder(Map orderMap) throws Exception {
		orderDAO.addOrder(orderMap);
		orderDAO.seatStatusUpdate1(orderMap);
		orderDAO.seatStatusUpdate2(orderMap);
		orderDAO.seatStatusUpdate3(orderMap);
	}
	
	@Override
	public int findMovieId(String movie_title) throws Exception {
		int resultMovieId = orderDAO.findMovieId(movie_title);
		return resultMovieId;
	}

	@Override
	public List<OrderVO> selectMyOrderDetailInfo(String member_id) throws Exception {
		List<OrderVO> selectMyOrderDetailInfo = orderDAO.selectMyOrderDetailInfo(member_id);
		return selectMyOrderDetailInfo;
	}
	
	
}
