package com.cinemoment.movie.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cinemoment.movie.order.vo.OrderVO;
import com.cinemoment.movie.order.vo.SeatVO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectMovieTitleList1() throws DataAccessException {
		List<OrderVO> titleList1 = sqlSession.selectList("mapper.order.selectMovieTitleList1");
		return titleList1;
	}

	@Override
	public List selectMovieTitleList2() throws DataAccessException {
		List<OrderVO> titleList2 = sqlSession.selectList("mapper.order.selectMovieTitleList2");
		return titleList2;
	}


	@Override
	public void addOrder(Map orderMap) throws DataAccessException {
		sqlSession.insert("mapper.order.addOrder", orderMap);
	}

	@Override
	public void seatStatusUpdate(Map orderMap) throws DataAccessException {
		sqlSession.insert("mapper.order.seatStatusUpdate", orderMap);

	}


	@Override
	public int findMovieId(String movie_title) throws DataAccessException {
		int resultMovieId = sqlSession.selectOne("mapper.order.findMovieId", movie_title);
		return resultMovieId;
	}

	@Override
	public List selectMyOrderDetailInfo(String member_id) throws DataAccessException {
		List<OrderVO> selectMyOrderDetailInfo = 
				sqlSession.selectList("mapper.order.selectMyOrderDetailInfo", member_id);
		return selectMyOrderDetailInfo;
	}
	
	@Override
	public List selectSchedulesByMovieId(int movie_id) throws DataAccessException{
		return sqlSession.selectList("mapper.order.selectSchedulesByMovieId", movie_id);
	}
	
	@Override
	public List selectSeatListByPlace(Map<String, Object> paramMap) throws DataAccessException{
		return sqlSession.selectList("mapper.order.selectSeatListByPlace", paramMap);
	}
	
	@Override
	public OrderVO selectScheduleById(int schedule_id) throws DataAccessException {
	    return sqlSession.selectOne("mapper.order.selectScheduleById", schedule_id);
	}

	@Override
	public List<OrderVO> selectAllOrderList() throws DataAccessException {
	    return sqlSession.selectList("mapper.order.selectAllOrderList");
	}
	
	
	@Override
	public void orderCancel(int morder_seq_num) throws DataAccessException {
		sqlSession.delete("mapper.order.orderCancel", morder_seq_num);
	}
	

	@Override
	public void deleteSeatStatus(int schedule_id, int seat_id) throws DataAccessException {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("schedule_id", schedule_id);
	    paramMap.put("seat_id", seat_id);
	    sqlSession.delete("mapper.order.deleteSeatStatus", paramMap);
	}
	

}
