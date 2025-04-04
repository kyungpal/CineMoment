package com.cinemoment.movie.mypage.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cinemoment.movie.board.service.BoardService;
import com.cinemoment.movie.member.vo.MemberVO;
import com.cinemoment.movie.mypage.service.MyPageService;
import com.cinemoment.movie.order.service.OrderService;
import com.cinemoment.movie.order.vo.OrderVO;


@Controller("mypageController")
@RequestMapping(value = "/mypage")
public class MypageController {

	@Autowired
	private MyPageService myPageService;

	@Autowired
	private MemberVO memberVO;

	@Autowired
	private OrderVO orderVO;

	@Autowired
	private BoardService boardService;

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/mypage.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView main(String message, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String member_id = "";
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		
		if(session.getAttribute("member")==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후 이용 가능합니다.'); history.back();</script>");
			out.flush();
			response.flushBuffer();
			out.close();
		}else {
			mav.addObject("session", session.getAttribute("member"));
			MemberVO vo = (MemberVO) session.getAttribute("member");
			member_id = vo.getMember_id();
		}
		
		memberVO = (MemberVO) session.getAttribute("memberInfoList");
		MemberVO memberInfo = myPageService.myDetailInfo(member_id);
		mav.addObject("memberInfo", memberInfo);

		memberVO = (MemberVO) session.getAttribute("member");
		String member_id2 = memberVO.getMember_id();
		orderVO = (OrderVO) session.getAttribute("orderInfoList");
		List orderInfoList = myPageService.findMyOrderInfo(member_id2);
		List selectReviewList = boardService.selectReviewList(member_id2);
		
		mav.addObject("reviewInfoList", selectReviewList);
		mav.addObject("orderInfoList", orderInfoList);
		mav.setViewName(viewName);
		
		return mav;
	}
	
	@RequestMapping(value = "/myOrderDetail.do", method = RequestMethod.GET)
	public ModelAndView myOrderDetail(String member_id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		memberVO = (MemberVO) session.getAttribute("member");
		member_id = memberVO.getMember_id();
		orderVO = (OrderVO) session.getAttribute("orderInfoList");
		List orderInfoList = orderService.selectMyOrderDetailInfo(member_id);
		mav.addObject("orderInfoList", orderInfoList);

		return mav;
	}
	
	@RequestMapping(value = "/myDetailInfo.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	@RequestMapping(value = "/modifyMyInfo.do", method = RequestMethod.POST)
	public ResponseEntity modifyMyInfo(@RequestParam("attribute") String attribute,
									   @RequestParam("value") String value,
									   HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map memberMap = new HashMap<>();		
		String val[] = null;
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		
		if (attribute.contentEquals("member_pw")) {
			val = value.split("");
			memberMap.put("member_pw", value);
		} else if (attribute.contentEquals("member_hp")) {
			val = value.split(",");
			memberMap.put("member_hp1", val[0]);
			memberMap.put("member_hp2", val[1]);
			memberMap.put("member_hp3", val[2]);
		} else {
			memberMap.put(attribute, value);
		}
			memberMap.put("member_id", member_id);
			memberVO = (MemberVO) myPageService.modifyMyInfo(memberMap);
			session.removeAttribute("member");
			session.setAttribute("member", memberVO);
			
			String message = null;
			ResponseEntity resEntity = null;
			HttpHeaders responseHeaders = new HttpHeaders();
			message = "mod_success";
			resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
			return resEntity;
		}
	
}
