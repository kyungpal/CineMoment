package com.cinemoment.movie.order.service;

import java.util.List;
import java.util.Map;

import com.cinemoment.movie.order.vo.OrderVO;

public interface OrderService {
	
	
	public List<OrderVO> MovieTitleList1() throws Exception;
	public List<OrderVO> MovieTitleList2() throws Exception;
	public List<OrderVO> selectSeatList1() throws Exception;
	public List<OrderVO> selectSeatList2() throws Exception;
	public List<OrderVO> selectSeatList3() throws Exception;
	public void addOrder(Map orderMap) throws Exception;
	public int findMovieId(String movie_title) throws Exception;
	public List<OrderVO> selectMyOrderDetailInfo(String member_id) throws Exception;
	
}
