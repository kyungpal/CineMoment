package com.cinemoment.movie.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cinemoment.movie.order.dao.OrderDAO;
import com.cinemoment.movie.order.vo.OrderVO;
import com.cinemoment.movie.order.vo.SeatVO;

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
	public void addOrder(Map orderMap) throws Exception {
		orderDAO.addOrder(orderMap);
		orderDAO.seatStatusUpdate(orderMap);
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
	
	@Override
	public List<OrderVO> selectSchedulesByMovieId(int movie_id) throws Exception{
		return orderDAO.selectSchedulesByMovieId(movie_id);
	}
	
	@Override
	public List<SeatVO> selectSeatListByPlace(Map<String, Object> paramMap) throws Exception{
		return orderDAO.selectSeatListByPlace(paramMap);
	}
	
	@Override
	public OrderVO selectScheduleById(int schedule_id) throws Exception {
	    return orderDAO.selectScheduleById(schedule_id);
	}
	
	@Override
	public List<OrderVO> selectAllOrderAndSeatStatus() throws Exception {
	    return orderDAO.selectAllOrderList();
	}

	
	@Override
	public void orderCancel(int morder_seq_num,int schedule_id, int seat_id) throws Exception{
	    orderDAO.deleteSeatStatus(schedule_id, seat_id);
		orderDAO.orderCancel(morder_seq_num);
	}
	
	
	@Override
	public void adminOrderCancel(int morder_seq_num, int schedule_id, int seat_id)throws Exception{
		orderDAO.deleteSeatStatus(schedule_id, seat_id);
		orderDAO.orderCancel(morder_seq_num);
	}
	
	
}
