package com.cinemoment.movie.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cinemoment.movie.order.vo.OrderVO;

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
	public List seatStatusList1() throws DataAccessException {
		List<OrderVO> seatList1 = sqlSession.selectList("mapper.order.seatList1");
		return seatList1;
	}

	@Override
	public List seatStatusList2() throws DataAccessException {
		List<OrderVO> seatList2 = sqlSession.selectList("mapper.order.seatList2");
		return seatList2;
	}

	@Override
	public List seatStatusList3() throws DataAccessException {
		List<OrderVO> seatList3 = sqlSession.selectList("mapper.order.seatList3");
		return seatList3;
	}

	@Override
	public void addOrder(Map orderMap) throws DataAccessException {
		sqlSession.insert("mapper.order.addOrder", orderMap);
	}

	@Override
	public void seatStatusUpdate1(Map orderMap) throws DataAccessException {
		sqlSession.update("mapper.order.seatStatusUpdate1", orderMap);

	}

	@Override
	public void seatStatusUpdate2(Map orderMap) throws DataAccessException {
		sqlSession.update("mapper.order.seatStatusUpdate2", orderMap);

	}

	@Override
	public void seatStatusUpdate3(Map orderMap) throws DataAccessException {
		sqlSession.update("mapper.order.seatStatusUpdate3", orderMap);

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
}
