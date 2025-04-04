package com.cinemoment.movie.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface OrderDAO {

	public List selectMovieTitleList1() throws DataAccessException;
	
	public List selectMovieTitleList2() throws DataAccessException;
	
	public List seatStatusList1() throws DataAccessException;

	public List seatStatusList2() throws DataAccessException;

	public List seatStatusList3() throws DataAccessException;
	
	public void addOrder(Map orderMap) throws DataAccessException;

	public void seatStatusUpdate1(Map orderMap) throws DataAccessException;

	public void seatStatusUpdate2(Map orderMap) throws DataAccessException;

	public void seatStatusUpdate3(Map orderMap) throws DataAccessException;
	
	public int findMovieId(String movie_title) throws DataAccessException;
	
	public List selectMyOrderDetailInfo(String member_id) throws DataAccessException;
	
}
