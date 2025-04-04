package com.cinemoment.movie.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemoment.movie.member.vo.MemberVO;
import com.cinemoment.movie.mypage.dao.MyPageDAO;

@Service("myPageService")
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired
	private MyPageDAO myPageDAO;
	
	@Override
	public MemberVO myDetailInfo(String member_id) throws Exception {
		MemberVO memberInfoList = myPageDAO.selectMyDetailInfo(member_id);
		return memberInfoList;
	}

	@Override
	public List findMyOrderInfo(String member_id2) throws Exception {
		List orderInfoList = myPageDAO.selectMyOrderInfo(member_id2);
		return orderInfoList;
	}

	@Override
	public List myDetailreview(String id) throws Exception {
		List reviewInfoList = myPageDAO.selectMyReviewInfo(id);
		return reviewInfoList;
	}

	@Override
	public MemberVO modifyMyInfo(Map memberMap) throws Exception {
		String member_id = (String) memberMap.get("member_id");
		myPageDAO.updateMyInfo(memberMap);
		return myPageDAO.selectMyDetailInfo(member_id);
	}
	
}
