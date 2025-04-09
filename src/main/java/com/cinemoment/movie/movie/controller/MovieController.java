package com.cinemoment.movie.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cinemoment.movie.movie.service.MovieService;
import com.cinemoment.movie.movie.vo.MovieVO;

import net.sf.json.JSONObject;

@Controller("movieController")
@RequestMapping(value = "/movie")
@EnableAspectJAutoProxy
public class MovieController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieVO movieVO;

	
	//영화정보
	@RequestMapping(value = "/movieDetail.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView movieDetail(@RequestParam("movie_id") int movie_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		List movieList = movieService.movieDetail(movie_id);
		List oneLineReviewList = movieService.oneLineReviewList(movie_id);

		mav.addObject("oneLineReviewList", oneLineReviewList);
		mav.addObject("movieList", movieList);
		
		return mav;
	}

	//영화 검색
	@RequestMapping(value = "/searchMovie.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView searchMovie( @RequestParam(value = "movie_keyword") String movie_keyword, 
									HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		System.out.println("movie_title: " + movie_keyword);
		List<MovieVO> searchList = movieService.searchMovie(movie_keyword);
		int Listsize = searchList.size();
		
		mav.addObject("movie_keyword", movie_keyword);
		mav.addObject("searchList", searchList);
		mav.addObject("ListSize", Listsize);
		System.out.println("searchList: " + searchList);

		return mav;
	}
	
	// 검색 키워드 자동완성
	@RequestMapping(value = "/keywordSearch.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public String keywordSearch(@RequestParam("keyword") String keyword, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.setContentType("utf-8");

		if (keyword == null || keyword.equals(""))
			return null;

		keyword = keyword.toUpperCase();
		List<String> keywordList = movieService.keywordSearch(keyword);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);

		String jsonInfo = jsonObject.toString();
		return jsonInfo;
	}

	//한줄평(댓글)ㄴ
	@RequestMapping(value = "/oneLineReview.do", method = RequestMethod.POST)
	public ResponseEntity oneLineReview(@RequestParam("movie_id") String movie_id, @RequestParam("id") String id,
										@RequestParam("password") String password, @RequestParam("content") String content,
										@RequestParam(required = false, value = "parentOneLineReviewNO") String parentOneLineReviewNO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, String> reviewMap = new HashMap<String, String>();
		reviewMap.put("parentOneLineReviewNO", parentOneLineReviewNO);
		reviewMap.put("movie_id", movie_id);
		reviewMap.put("id", id);
		reviewMap.put("password", password);
		reviewMap.put("content", content);

		movieService.oneLineReview(reviewMap);

		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message = "success";
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	// 한줄평삭제 메서드
	@RequestMapping(value = "/deleteOneLineReview.do", method = RequestMethod.POST)
	public ResponseEntity<String> deleteOneLineReview(@RequestParam("movie_id") int movie_id,
													  @RequestParam("oneLineReviewNO") int oneLineReviewNO, 
													  @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> reviewMap = new HashMap<>();
		reviewMap.put("movie_id", movie_id);
		reviewMap.put("oneLineReviewNO", oneLineReviewNO);
		reviewMap.put("password", password);

		boolean isDeleted = movieService.deleteOneLineReview(reviewMap);

		// 삭제 성공 여부에 따라 응답 메시지 설정
		String message = isDeleted ? "success" : "wrong_password";

		return new ResponseEntity<>(message, HttpStatus.OK);

	}
	
}
