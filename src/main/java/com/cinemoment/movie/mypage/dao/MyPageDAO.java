package com.cinemoment.movie.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.cinemoment.movie.member.vo.MemberVO;

public interface MyPageDAO {

	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException;
	public List selectMyOrderInfo(String member_id2) throws DataAccessException;
	public List selectMyReviewInfo(String id) throws DataAccessException;
	public void updateMyInfo(Map memberMap) throws DataAccessException;
}
