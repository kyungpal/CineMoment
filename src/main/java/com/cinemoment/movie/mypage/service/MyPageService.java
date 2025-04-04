package com.cinemoment.movie.mypage.service;

import java.util.List;
import java.util.Map;

import com.cinemoment.movie.member.vo.MemberVO;

public interface MyPageService {
	
	public List findMyOrderInfo(String member_id2) throws Exception;
	public MemberVO modifyMyInfo(Map memberMap) throws Exception;
	public MemberVO myDetailInfo(String member_id) throws Exception;
	public List myDetailreview(String id) throws Exception;
	
}

