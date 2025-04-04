package com.cinemoment.movie.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cinemoment.movie.member.vo.MemberVO;



@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException {
		MemberVO memberInfo = (MemberVO) sqlSession.selectOne("mapper.mypage.selectMyDetailInfo", member_id);
		return memberInfo;
	}
	
	@Override
	public List selectMyOrderInfo(String member_id) throws DataAccessException {
		List orderInfoList = (List) sqlSession.selectList("mapper.mypage.selectMyOrderDetailInfo", member_id);
		return orderInfoList;
	}
	
	@Override
	public List selectMyReviewInfo(String id) throws DataAccessException {
		List reviewInfoList = (List) sqlSession.selectList("mapper.mypage.selectMyReview");
		return reviewInfoList;
	}
	
	@Override
	public void updateMyInfo(Map memberMap) throws DataAccessException {
		sqlSession.update("mapper.mypage.updateMyInfo", memberMap);
	}
	
}
