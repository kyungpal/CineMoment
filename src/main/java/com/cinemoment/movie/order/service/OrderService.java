package com.cinemoment.movie.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.cinemoment.movie.order.vo.OrderVO;
import com.cinemoment.movie.order.vo.SeatVO;

public interface OrderService {
	
	public List<OrderVO> MovieTitleList1() throws Exception;
	public List<OrderVO> MovieTitleList2() throws Exception;
	public void addOrder(Map orderMap) throws Exception;
	public int findMovieId(String movie_title) throws Exception;
	public List<OrderVO> selectMyOrderDetailInfo(String member_id) throws Exception;
	public List<OrderVO> selectSchedulesByMovieId(int movie_id) throws Exception;
	public List<SeatVO> selectSeatListByPlace(Map<String, Object> paramMap) throws Exception;
	public OrderVO selectScheduleById(int schedule_id) throws Exception;
	public List<OrderVO> selectAllOrderAndSeatStatus() throws Exception;
	public void orderCancel(int morder_seq_num,int schedule_id, int seat_id)throws Exception;
	public void adminOrderCancel(int morder_seq_num, int schedule_id, int seat_id)throws Exception;
}
