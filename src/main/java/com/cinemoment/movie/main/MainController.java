package com.cinemoment.movie.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinemoment.movie.board.service.BoardService;
import com.cinemoment.movie.order.service.OrderService;


@Controller("mainController")
@EnableAspectJAutoProxy // 프록시 자동 생성 어노테이션 AOP 기능, 
public class MainController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value= "/main/main.do", method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView main (HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//영화목록, 공지사항 리스트
		List titleList1 = orderService.MovieTitleList1();
		List titleList2 = orderService.MovieTitleList2();
		List noticeList = boardService.noticeList();
		
		//클라이언트가 보낸 요청 정보를 담고 있음 getAttribute("viewName")은 이전에 서버에서 설정한 viewName 값을 가져오는 것
		//viewName은 어떤 JSP 파일을 보여줄지 결정하는 값
		//➡ 즉, viewName은 "어떤 화면을 띄울지" 저장하는 변수
		//request.getAttribute("viewName")에서 가져오지만, 이전에 어디선가 setAttribute("viewName", 값)으로 저장한 곳이 있어야 함
		//보통 인터셉터(HandlerInterceptor)나 필터(Filter)에서 설정하는 경우가 많음
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName"); 
		
		//mav에 titleList(영화 목록)와 noticeList(공지사항 목록)를 담아서 메인 화면으로 전달하는 코드
		//➡ setViewName()은 어떤 JSP 파일을 보여줄지 설정하는 것이고,
		//➡ addObject()는 해당 JSP에서 사용할 데이터를 담는 것이기 때문에,
		//둘의 순서는 코드 실행에 영향을 주지 않음.
		mav.addObject("titleList1", titleList1);
		mav.addObject("titleList2", titleList2);
		mav.addObject("noticeList", noticeList);
		mav.setViewName(viewName);
		
		//1️⃣ request.getSession();
		//→ 사용자의 세션(Session) 객체를 가져옴
		//→ 세션은 서버가 사용자의 정보를 유지하는 공간 (예: 로그인 정보)
		HttpSession session = request.getSession();
		if (session.getAttribute("member") != null) {
			mav.addObject("session", session.getAttribute("member"));
		} 
		return mav;
	}
}
