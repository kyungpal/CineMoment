package com.cinemoment.movie.order.controller;

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
import com.cinemoment.movie.order.vo.SeatVO;

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderVO orderVO;
	
	@Autowired
	private MemberVO memberVO;
	
	
	//빠른예매
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

	
	//상영시간선택
		@RequestMapping(value="/timeselect.do", method = RequestMethod.POST)
		private ModelAndView timeSelect(@RequestParam("movieNum") int movieNum,
										@RequestParam("movieTitle") String movieTitle,
										HttpServletRequest request, HttpServletResponse response) throws Exception{
			System.out.println("영화 번호: " + movieNum);
			String viewName = (String) request.getAttribute("viewName");
			HttpSession session = request.getSession();
			ModelAndView mav = new ModelAndView(viewName);
			 // 세션에 영화 정보 저장
			session.setAttribute("movieNum", movieNum);
			session.setAttribute("movieTitle", movieTitle);
			
			List<OrderVO> scheduleList = orderService.selectSchedulesByMovieId(movieNum);
		    System.out.println("scheduleList: " + scheduleList); // null이나 비어있지 않은지 체크

		    mav.addObject("movie_id", movieNum);
		    mav.addObject("scheduleList", scheduleList);
			
			return mav;
		}
		
	
	//좌석선택
	@RequestMapping(value = "/seatselect.do", method =  RequestMethod.POST)
	private ModelAndView seatSelect (HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Enumeration enu = request.getParameterNames();//request.getParameter("name")처럼 개별 값을 가져오는 게 아니라, 모든 요청 파라미터의 이름을 가져오는 것! , 클라이언트가 보낸 모든 요청 파라미터의 이름을 가져옴.
		String viewName = (String) request.getAttribute("viewName"); //요청이 끝나면 사라짐 → 새로운 요청에서는 값이 유지되지 않음.
		ModelAndView mav = new ModelAndView(viewName);
		
		while (enu.hasMoreElements()) {
			 String name = (String) enu.nextElement(); 
			 String value = request.getParameter(name); // 해당 파라미터 값 가져오기
			 session.setAttribute(name, value); // 세션에 저장
		}
		
		String movie_place = request.getParameter("movie_place");
		System.out.println("파라미터로 넘어온 상영관: " + movie_place);
		
		
		String schedule_id_str = request.getParameter("schedule_id");
		System.out.println("넘어온 schedule_id: " + schedule_id_str);
		int schedule_id = Integer.parseInt(schedule_id_str);
		session.setAttribute("schedule_id", schedule_id);
		OrderVO schedule = orderService.selectScheduleById(schedule_id);
		
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("movie_place", movie_place);
		paramMap.put("schedule_id", schedule_id);
		List<SeatVO> seatList = orderService.selectSeatListByPlace(paramMap);
		
		
		mav.addObject("seatList", seatList);
		mav.addObject("schedule", schedule); 
		mav.addObject("movie_place", movie_place);
		return mav;
	}

	
	//티켓폼 구매정보입력
	@RequestMapping(value = "/ticketingForm.do", method = {RequestMethod.POST, RequestMethod.GET})
	private ModelAndView ticketingForm(
	        @RequestParam("seatNum") String seatNum,
	        @RequestParam("seat_id") String seat_id,
	        @RequestParam("schedule_id") int schedule_id,
	        HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    String viewName = (String) request.getAttribute("viewName");
	    ModelAndView mav = new ModelAndView(viewName);
	    
	    // 파라미터 기본 검증
	    if (seat_id == null || seat_id.isEmpty() ||
	        seatNum == null || seatNum.isEmpty() ||
	        schedule_id <= 0) {
	        mav.setViewName("redirect:/main/main.do");
	        return mav;
	    }
	    // 세션 검증
	    if (session.getAttribute("movieTitle") == null || session.getAttribute("movie_place") == null) {
	        return new ModelAndView("redirect:/main/main.do"); // 예시
	    }
	    
	    
	    // 필수 정보 세션에 저장
	    session.setAttribute("seat_id", seat_id); // 좌석 아이디
	    session.setAttribute("schedule_id", schedule_id); // 상영정보 아이디 
	    session.setAttribute("movie_seat_number", seatNum); // 좌석번호
	    
	    // 상영일/시간은 DB에서 불러오는 방식으로 수정
	    OrderVO schedule = orderService.selectScheduleById(schedule_id); // DB에서 조회

	    // 모델에 값 담기
	    mav.addObject("seat_id", seat_id);
	    mav.addObject("schedule_id", schedule_id);
	    // schedule에서 날짜와 시간 가져올 수 있어야 함 (JSP에서 schedule.movie_screening_date 등으로)
	    mav.addObject("schedule", schedule);

	    return mav;
	}

	
	//예매하기
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

		orderMap.put("movie_id", movieNum);
		session.removeAttribute("movieNum");
		session.removeAttribute("movie_screening_date");
		session.removeAttribute("movie_running_time");
		session.removeAttribute("movie_place");

		
		String seat_id = (String) session.getAttribute("seat_id");
		int schedule_id = (Integer) session.getAttribute("schedule_id");
		orderMap.put("seat_id", seat_id);
		orderMap.put("schedule_id", schedule_id);
		
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
			message += " alert('결제 완료 되었습니다.');";
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
