package com.cinemoment.movie.movie.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.cinemoment.movie.movie.vo.MovieVO;


public interface MovieDAO {

	public List<MovieVO> viewMovieDetail(int movie_id) throws DataAccessException;
	public List<MovieVO> searchMovie(String movie_keyword) throws DataAccessException;
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
	public void addOneLineReview(Map reviewMap) throws DataAccessException;
	public List<MovieVO> oneLineReviewList(int movie_id) throws DataAccessException;
	public void deleteOneLineReview(Object oneLineReviewNO)throws DataAccessException;
	public String getReviewPassword(Object oneLineReviewNO)throws DataAccessException;
}