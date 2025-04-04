package com.cinemoment.movie.movie.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemoment.movie.movie.dao.MovieDAO;
import com.cinemoment.movie.movie.vo.MovieVO;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieDAO movieDAO;

	@Override
	public List<MovieVO> movieDetail(int movie_id) throws Exception {
		List<MovieVO> movieList = movieDAO.viewMovieDetail(movie_id);
		return movieList;
	}

	@Override
	public List<MovieVO> searchMovie(String movie_keyword) throws Exception {
		List<MovieVO> searchList = movieDAO.searchMovie(movie_keyword);
		return searchList;
	}

	public List<String> keywordSearch(String keyword) throws Exception {
		List<String> list = movieDAO.selectKeywordSearch(keyword);
		return list;
	}

	@Override
	public void oneLineReview(Map reviewMap) throws Exception {
		movieDAO.addOneLineReview(reviewMap);
	}

	@Override
	public List<MovieVO> oneLineReviewList(int movie_id) throws Exception {
		List<MovieVO> oneLineReviewList = movieDAO.oneLineReviewList(movie_id);
		return oneLineReviewList;
	}
	
	@Override
	public boolean deleteOneLineReview(Map<String, Object> reviewMap) {
        // 1. DB에서 해당 review_id와 비밀번호 가져오기
        String storedPassword = movieDAO.getReviewPassword(reviewMap.get("oneLineReviewNO"));
        // 2. 입력받은 비밀번호와 비교
        if (storedPassword != null && storedPassword.equals(reviewMap.get("password"))) {
        // 3. 비밀번호가 맞으면 삭제
            movieDAO.deleteOneLineReview(reviewMap.get("oneLineReviewNO"));
            return true; // 삭제 성공
        }
        return false; // 비밀번호 틀림
    }
	
}
