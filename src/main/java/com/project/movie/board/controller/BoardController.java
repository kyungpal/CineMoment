package com.project.movie.board.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.movie.board.service.BoardService;
import com.project.movie.board.vo.BoardVO;
import com.project.movie.common.base.BaseController;
import com.project.movie.member.vo.MemberVO;
import com.project.movie.movie.vo.ImageFileVO;
import com.project.movie.order.service.OrderService;
import com.project.movie.order.vo.OrderVO;

@Controller("boardController")
@RequestMapping(value = "/board")
public class BoardController extends BaseController {

	private static final String CURR_IMAGE_REPO_PATH = "C:\\movie\\file_repo";

	@Autowired
	private BoardService boardService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BoardVO boardVO;

	@Autowired
	private MemberVO memberVO;

	@RequestMapping(value = "/notice.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView noticeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		List noticeList = boardService.noticeList();
		mav.addObject("noticeList", noticeList);
		return mav;
	}

	@RequestMapping(value = "/event.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView eventList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		List eventList = boardService.eventList();
		mav.addObject("eventList", eventList);
		return mav;
	}

	@RequestMapping(value = "/review.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView reviewList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		
		int section = Integer.parseInt(((_section == null) ? "1" : _section));
		int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));

		Map pagingMap = new HashMap();

		pagingMap.put("section", section);
		pagingMap.put("pageNum", pageNum);
		
		Map boardMap = boardService.reviewList(pagingMap);
		
		boardMap.put("section", section);
		boardMap.put("pageNum", pageNum);
		
		ModelAndView mav = new ModelAndView();
		String viewName = (String) request.getAttribute("viewName");
		mav.setViewName(viewName);
		mav.addObject("boardMap", boardMap);
		return mav;
	}
	@RequestMapping(value = "/noticeView.do", method = RequestMethod.GET)
	public ModelAndView noticeView(@RequestParam("noticeBoardNO") int noticeBoardNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("noticeBoardNO : " + noticeBoardNO);

		String viewName = (String) request.getAttribute("viewName");
		boardVO = boardService.noticeView(noticeBoardNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("board", boardVO);
		return mav;
	}

	@RequestMapping(value = "/eventView.do", method = RequestMethod.GET)
	public ModelAndView eventView(@RequestParam("eventBoardNO") int eventBoardNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("eventBoardNO : " + eventBoardNO);

		String viewName = (String) request.getAttribute("viewName");
		Map boardMap = boardService.eventView(eventBoardNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("boardMap", boardMap);
		return mav;
	}

	@RequestMapping(value = "/reviewView.do", method = RequestMethod.GET)
	public ModelAndView reviewView(@RequestParam("reviewBoardNO") int reviewBoardNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("reviewBoardNO : " + reviewBoardNO);

		String viewName = (String) request.getAttribute("viewName");
		Map boardMap = boardService.reviewView(reviewBoardNO);
		boardService.boardView(reviewBoardNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("boardMap", boardMap);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/addReview.do", method = RequestMethod.POST)
	public ResponseEntity addReview(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		Map<String, Object> boardMap = new HashMap<String, Object>();
		String imageFileName = null;
		Enumeration enu = multipartRequest.getParameterNames();

		while (enu.hasMoreElements()) {

			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			boardMap.put(name, value);
			System.out.println("name :" + name);
			System.out.println("value :" + value);

		}
		HttpSession session = multipartRequest.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		boardMap.put("member_id", member_id);

		String movie_title = (String) boardMap.get("movie_title");
		int movie_id = orderService.findMovieId(movie_title);
		System.out.println("movie_id:" + movie_id);
		String boardTitle1 = (String) boardMap.get("boardTitle");
		String boardTitle = "[" + movie_title + "]" + boardTitle1;
		boardMap.put("boardTitle", boardTitle);
		boardMap.put("movie_id", movie_id);
		String reg_id = memberVO.getMember_id();

		List<ImageFileVO> imageFileList = upload(multipartRequest);
		if (imageFileList != null && imageFileList.size() != 0) {
			for (ImageFileVO imageFileVO : imageFileList) {
				imageFileVO.setReg_id(reg_id);
			}
			boardMap.put("imageFileList", imageFileList);
		}

		String msg;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			int boardNO = boardService.addReview(boardMap);

			if (imageFileList != null && imageFileList.size() != 0) {
				for (ImageFileVO imageFileVO : imageFileList) {
					imageFileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + boardNO);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}

			msg = "<script>";
			msg += " alert('������ �߰��߽��ϴ�.');";
			msg += " location.href = '" + multipartRequest.getContextPath() + "/board/review.do';";
			msg += " </script>";
			resEnt = new ResponseEntity(msg, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				for (ImageFileVO imageFileVO : imageFileList) {
					imageFileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + imageFileName);
					srcFile.delete();
				}
			}
			msg = " <script>";
			msg += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			msg += " location.href='" + multipartRequest.getContextPath() + "/board/reviewForm.do'; ";
			msg += " </script>";

			resEnt = new ResponseEntity(msg, responseHeaders, HttpStatus.CREATED);

			e.printStackTrace();
		}
		return resEnt;
	}

	@RequestMapping(value = "/*Form.do", method = RequestMethod.GET)
	public ModelAndView Form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		List<OrderVO> movieList = orderService.MovieTitleList();
		mav.addObject("movieList", movieList);
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/push.do", method = RequestMethod.GET)
	public ResponseEntity boardPush(@RequestParam("reviewBoardNO") int reviewBoardNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String msg;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			boardService.boardPush(reviewBoardNO);

			msg = "<script>";
			msg += " alert('��õ�� �Ϸ��߽��ϴ�..');";
			msg += " location.href = '" + request.getContextPath() + "/board/reviewView.do?reviewBoardNO="
					+ reviewBoardNO + "';";
			msg += " </script>";
			resEnt = new ResponseEntity(msg, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {

			msg = " <script>";
			msg += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			msg += " location.href='" + request.getContextPath() + "/board/reviewForm.do'; ";
			msg += " </script>";

			resEnt = new ResponseEntity(msg, responseHeaders, HttpStatus.CREATED);

			e.printStackTrace();
		}
		return resEnt;
	}

	@ResponseBody
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ResponseEntity delete(@RequestParam("boardNO") int boardNO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html; charset=utf-8");

		String message;
		String fileName;
		ResponseEntity resEnt = null;

		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {

			boardService.delete(boardNO);
			List<ImageFileVO> deleteImageList = boardService.deleteImage(boardNO);

			if (deleteImageList != null && deleteImageList.size() != 0) {
				for (ImageFileVO imageFileVO : deleteImageList) {
					fileName = imageFileVO.getFileName();
					File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + boardNO + "\\" + fileName);
					destDir.delete();
				}

			}

			message = "<script>";
			message += " alert('���� �����߽��ϴ�.');";
			message += " location.href='" + request.getContextPath() + "/board/review.do';";
			message += " </script>";

			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {

			message = "<script>";
			message += " alert('�۾��� ������ �߻��߽��ϴ�.�ٽ� �õ��� �ּ���.');";
			message += " location.href='" + request.getContextPath() + "/board/review.do';";
			message += " </script>";

			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

			e.printStackTrace();
		}
		return resEnt;
	}

	@ResponseBody
	@RequestMapping(value = "/addEvent.do", method = RequestMethod.POST)
	public ResponseEntity addEvent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String fileName = null;
		Map boardMap = new HashMap();

		Enumeration enu = multipartRequest.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			boardMap.put(name, value);
			System.out.println("name :" + name);
			System.out.println("value :" + value);
		}

		HttpSession session = multipartRequest.getSession();

		List<ImageFileVO> imageFileList = upload(multipartRequest);
		boardMap.put("imageFileList", imageFileList);

		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int movie_id = boardService.addEvent(boardMap);
			if (imageFileList != null && imageFileList.size() != 0) {
				for (ImageFileVO imageFileVO : imageFileList) {
					fileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + fileName);
					File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + movie_id);
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
			}
			message = "<script>";
			message += " alert('������ �߰��߽��ϴ�..');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/event.do';";
			message += "</script>";
		} catch (Exception e) {
			if (imageFileList != null && imageFileList.size() != 0) {
				for (ImageFileVO imageFileVO : imageFileList) {
					fileName = imageFileVO.getFileName();
					File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\" + "temp" + "\\" + fileName);
					srcFile.delete();
				}
			}

			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/eventForm.do';";
			message += "</script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@ResponseBody
	@RequestMapping(value = "/addNotice.do", method = RequestMethod.POST)
	public ResponseEntity addNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		Map boardMap = new HashMap();

		Enumeration enu = request.getParameterNames();

		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			boardMap.put(name, value);
			System.out.println("name :" + name);
			System.out.println("value :" + value);
		}

		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		boardMap.put("member_id", member_id);

		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int movie_id = boardService.addNotice(boardMap);

			message = "<script>";
			message += " alert('������ �߰��߽��ϴ�..');";
			message += " location.href='" + request.getContextPath() + "/board/notice.do';";
			message += "</script>";
		} catch (Exception e) {

			message = "<script>";
			message += " alert('������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message += " location.href='" + request.getContextPath() + "/board/noticeForm.do';";
			message += "</script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/reviewMod.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity reviewMod(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
	        throws Exception {
		
	    multipartRequest.setCharacterEncoding("utf-8");
	    response.setContentType("text/html; charset=UTF-8");
	    Map<String, Object> boardMap = new HashMap<String, Object>();
	    String imageFileName = null;
	    Enumeration enu = multipartRequest.getParameterNames();

	    while (enu.hasMoreElements()) {
	        String name = (String) enu.nextElement();
	        String value = multipartRequest.getParameter(name);
	        
	        System.out.println("Parameter Name: " + name + " Value: " + value);
	        
	        boardMap.put(name, value);
	    }

	    HttpSession session = multipartRequest.getSession();
	    memberVO = (MemberVO) session.getAttribute("member");
	    String member_id = memberVO.getMember_id();
	    boardMap.put("member_id", member_id);

	    // 글 ID 가져오기
	    int boardNO = Integer.parseInt((String) boardMap.get("boardNO"));

	    String movie_title = (String) boardMap.get("movie_title");
	    String boardTitle1 = (String) boardMap.get("boardTitle");
	    String boardTitle = "[" + movie_title + "]" + boardTitle1;
	    boardMap.put("boardTitle", boardTitle);

	    
	    List<ImageFileVO> imageFileList = upload(multipartRequest);
	    if (imageFileList != null && !imageFileList.isEmpty()) {
	        for (ImageFileVO imageFileVO : imageFileList) {
	            imageFileVO.setReg_id(member_id);
	        }
	        boardMap.put("imageFileList", imageFileList);
	    }

	    String msg;
	    ResponseEntity resEnt = null;
	    HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add("Content-Type", "text/html; charset=utf-8");

	    try {
	        boardService.modifyReview(boardMap);

	        if (imageFileList != null && !imageFileList.isEmpty()) {
	            for (ImageFileVO imageFileVO : imageFileList) {
	                imageFileName = imageFileVO.getFileName();
	                File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\temp\\" + imageFileName);
	                File destDir = new File(CURR_IMAGE_REPO_PATH + "\\" + boardNO);
	                FileUtils.moveFileToDirectory(srcFile, destDir, true);
	            }
	        }
	        
	        
	        
	        msg = "<script>";
	        msg += " alert('글 수정이 완료되었습니다.');";
	        msg += " location.href = '" + multipartRequest.getContextPath() + "/board/review.do';";
	        msg += " </script>";
	        resEnt = new ResponseEntity(msg, responseHeaders, HttpStatus.OK);

	    } catch (Exception e) {
	        if (imageFileList != null && !imageFileList.isEmpty()) {
	            for (ImageFileVO imageFileVO : imageFileList) {
	                imageFileName = imageFileVO.getFileName();
	                File srcFile = new File(CURR_IMAGE_REPO_PATH + "\\temp\\" + imageFileName);
	                srcFile.delete();
	            }
	        }
	        msg = "<script>";
	        msg += " alert('글 수정 실패');";
	        msg += " location.href='" + multipartRequest.getContextPath() + "/board/reviewForm.do'; ";
	        msg += " </script>";

	        resEnt = new ResponseEntity(msg, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
	    }
	    return resEnt;
	}
	
	@RequestMapping(value = "/reviewModForm.do", method = RequestMethod.GET)
	public ModelAndView reviewModForm(@RequestParam(value = "boardNO", required = false) String boardNOStr) throws Exception {
	    ModelAndView mav = new ModelAndView();
	    
	    System.out.println("Requested board NO: " + boardNOStr);
	    
	    if (boardNOStr == null || boardNOStr.trim().isEmpty()) {
	        mav.setViewName("redirect:/board/review.do");
	        return mav;
	    }

	    int boardNO;
	    
	    try {
	        boardNO = Integer.parseInt(boardNOStr);
	    } catch (NumberFormatException e) {
	        System.out.println("Invalid boardNO format.");
	        mav.setViewName("redirect:/board/review.do");
	        return mav;
	    }

	    Map<String, Object> review = boardService.reviewView(boardNO);

	  

	    // 로그 출력 (디버깅용)
	    System.out.println("Review Data: " + review);
	    System.out.println("Review  review.get(\"board\"): " +  review.get("board"));

	    // 기존 글 정보를 ModelAndView에 추가
	    mav.addObject("review", review);
	    mav.setViewName("board/reviewMod");  // 수정 페이지로 이동
	    
	    return mav;
	}



}
