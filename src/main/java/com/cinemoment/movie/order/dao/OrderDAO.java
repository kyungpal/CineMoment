package com.cinemoment.movie.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.cinemoment.movie.order.vo.OrderVO;
import com.cinemoment.movie.order.vo.SeatVO;

public interface OrderDAO {

	public List selectMovieTitleList1() throws DataAccessException;
	
	public List selectMovieTitleList2() throws DataAccessException;
	
	public void addOrder(Map orderMap) throws DataAccessException;

	public void seatStatusUpdate(Map orderMap) throws DataAccessException;

	public int findMovieId(String movie_title) throws DataAccessException;
	
	public List selectMyOrderDetailInfo(String member_id) throws DataAccessException;
	
	public List selectSchedulesByMovieId(int movie_id) throws DataAccessException;
	
	public List selectSeatListByPlace(Map<String, Object> paramMap) throws DataAccessException;
	
	public OrderVO selectScheduleById(int schedule_id) throws DataAccessException;
	
	public List<OrderVO> selectAllOrderList() throws DataAccessException;
	
	public void orderCancel(int morder_seq_num) throws DataAccessException;
	
	public void deleteSeatStatus(int schedule_id, int seat_id) throws DataAccessException;
	
	
}
