package com.cinemoment.movie.order.controller;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cinemoment.movie.member.vo.MemberVO;
import com.cinemoment.movie.order.service.OrderService;
import com.cinemoment.movie.order.vo.OrderVO;

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderVO orderVO;
	
	@Autowired
	private MemberVO memberVO;
	
	@RequestMapping(value = "/ticketing.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView MovieTitleList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
//		HttpSession session = request.getSession();
//		MemberVO member = (MemberVO) session.getAttribute("member");
//
//	    if (member == null) { // 로그인하지 않은 상태라면
//	        response.setContentType("text/html; charset=UTF-8");
//	        PrintWriter out = response.getWriter();
//	        out.println("<script>alert('로그인이 필요합니다.'); location.href='/movie/member/loginForm.do';</script>");
//	        out.flush();
//	        return null; // 페이지 이동 방지
//	    }
	    
		List titleList1 = orderService.MovieTitleList1();
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		mav.addObject("titleList1", titleList1);
		return mav;
	}

	@RequestMapping(value = "/seatselect.do", method = RequestMethod.POST)
	private ModelAndView seatSelect (HttpServletRequest request, HttpServletResponse response) throws Exception{
		//현재 사용자의 세션을 가져오는 코드
		//클라이언트(사용자)별로 데이터를 저장하는 공간
		//로그인 정보, 장바구니, 사용자 설정 등을 저장할 때 사용
		//사용자가 처음 요청하면 새로운 세션이 생성됨.
		//기존 세션이 있으면 해당 세션을 반환.
		//세션은 서버에서 유지되며, 쿠키(JSESSIONID)를 사용해 클라이언트를 구분.
		HttpSession session = request.getSession();
		Enumeration enu = request.getParameterNames();//request.getParameter("name")처럼 개별 값을 가져오는 게 아니라, 모든 요청 파라미터의 이름을 가져오는 것! , 클라이언트가 보낸 모든 요청 파라미터의 이름을 가져옴.
		String movie_place = (String) session.getAttribute("movie_place");//로그인 정보, 장바구니 데이터 등 장기적으로 유지해야 할 값을 저장하는 데 사용됨.
		String viewName = (String) request.getAttribute("viewName"); //요청이 끝나면 사라짐 → 새로운 요청에서는 값이 유지되지 않음.
		ModelAndView mav = new ModelAndView(viewName);
		
		//Enumeration에 더 가져올 요소가 있는 동안 반복문을 실행하라는 의미!
		//즉, request.getParameterNames()로 가져온 모든 파라미터 이름을 하나씩 처리할 때 사용됨.
		//while문의 동작 방식
		//enu.hasMoreElements()가 true라면, while문 실행.
		//enu.nextElement()를 호출해서 다음 요소를 가져옴.
		//요소가 남아 있는 동안 계속 반복.
		//모든 요소를 가져오면 false가 되어 while문 종료.Enumeration에 더 가져올 요소가 있는 동안 반복문을 실행하라는 의미!
		while (enu.hasMoreElements()) {
			 String name = (String) enu.nextElement(); // 요청 파라미터 이름 가져오기, enu.nextElement()의 반환 타입이 Object 타입이므로 (String)명시적 형변환(캐스팅)
			 String value = request.getParameter(name); // 해당 파라미터 값 가져오기
			 session.setAttribute(name, value); // 세션에 저장
		}
		List seatList1 = orderService.selectSeatList1(); // 1관
		List seatList2 = orderService.selectSeatList2(); // 2관
		List seatList3 = orderService.selectSeatList3(); // 3관
		mav.addObject("seatList1", seatList1);
		mav.addObject("seatList2", seatList2);
		mav.addObject("seatList3", seatList3);
		mav.addObject("moviePlace", movie_place);
		return mav;
	}
	
	@RequestMapping(value = "/ticketingForm.do", method = RequestMethod.POST)
	private ModelAndView ticketingForm(@RequestParam("seatNum") int seatNum, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		System.out.println("ticketingForm.do :" + seatNum);
		session.setAttribute("seatNum", seatNum);
		String member_id = (String) session.getAttribute("member_id");
		String movie_place = (String) session.getAttribute("movie_place"); 
		String movieTitle = (String) session.getAttribute("movieTitle"); 
		String movie_screening_date = (String) session.getAttribute("movie_screening_date"); 
		String movie_running_time = (String) session.getAttribute("movie_running_time"); 
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("movieTitle", movieTitle);
		mav.addObject("seatNum", seatNum);
		mav.addObject("movie_place", movie_place);
		mav.addObject("movie_screening_date", movie_screening_date);
		mav.addObject("movie_running_time", movie_running_time);
		mav.addObject("member_id", member_id);

		return mav;

	}
	
	@RequestMapping(value="/timeselect.do", method = RequestMethod.POST)
	private ModelAndView timeSelect(@RequestParam("movieNum") int movieNum,
									@RequestParam("movieTitle") String movieTitle,
									HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView(viewName);
		
		session.setAttribute("movieNum", movieNum);
		session.setAttribute("movieTitle", movieTitle);
		
		return mav;
	}
	
	@RequestMapping(value = "addOrder.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map orderMap = new HashMap();
		Enumeration enu = request.getParameterNames();
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			orderMap.put(name, value);
		}

		String member_id = memberVO.getMember_id();
		orderMap.put("member_id", member_id);

		String movie_people_qty1 = (String) orderMap.get("movie_people_qty");
		int movie_people_qty = Integer.parseInt(movie_people_qty1);
		orderMap.remove("movie_people_qty");
		orderMap.put("movie_people_qty", movie_people_qty);

		String movie_price1 = (String) orderMap.get("movie_price");
		int movie_price = Integer.parseInt(movie_price1);
		orderMap.remove("movie_price");
		orderMap.put("movie_price", movie_price);

		String hpNum1 = (String) orderMap.get("hpNum1");
		String hpNum2 = (String) orderMap.get("hpNum2");
		String hpNum3 = (String) orderMap.get("hpNum3");
		orderMap.remove("hpNum1");
		orderMap.remove("hpNum2");
		orderMap.remove("hpNum3");
		String hp_num = hpNum1 + hpNum2 + hpNum3;
		String pay_orderer_hp_num = hpNum1 + "-" + hpNum2 + "-" + hpNum3;
		orderMap.put("pay_orderer_hp_num", pay_orderer_hp_num);

		String cardNum1 = (String) orderMap.get("cardNum1");
		String cardNum2 = (String) orderMap.get("cardNum2");
		String cardNum3 = (String) orderMap.get("cardNum3");
		String cardNum4 = (String) orderMap.get("cardNum4");
		orderMap.remove("cardNum1");
		orderMap.remove("cardNum2");
		orderMap.remove("cardNum3");
		orderMap.remove("cardNum4");
		String card_number = cardNum1 + "-" + cardNum2 + "-" + cardNum3 + "-" + cardNum4;
		orderMap.put("card_number", card_number);

		String cardYear = (String) orderMap.get("cardYear");
		String cardMonth = (String) orderMap.get("cardMonth");
		orderMap.remove("cardYear");
		orderMap.remove("cardMonth");

		String card_pay_month = cardYear + "/" + cardMonth;
		orderMap.put("card_pay_month", card_pay_month);

		int movieNum = (int) session.getAttribute("movieNum");

		System.out.println("movieNum :" + movieNum);
		orderMap.put("movie_id", movieNum);
		session.removeAttribute("movieNum");
		session.removeAttribute("movie_screening_date");
		session.removeAttribute("movie_running_time");
		session.removeAttribute("movie_place");

		Random random = new Random();
		int ticket_number = random.nextInt(999999);
		orderMap.put("ticket_number", ticket_number);

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			orderService.addOrder(orderMap);
			message = "<script>";
			message += " alert('예매에 성공했습니다.');";
			message += " location.href='" + request.getContextPath() + "/mypage/mypage.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해주세요');";
			message += " location.href='" + request.getContextPath() + "/order/ticketing.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

}
