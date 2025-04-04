package com.cinemoment.movie.movie.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cinemoment.movie.movie.vo.MovieVO;

@Repository("movieDAO")
public class MovieDAOImpl implements MovieDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MovieVO> viewMovieDetail(int movie_id) throws DataAccessException {
		List<MovieVO> movieList = sqlSession.selectList("mapper.movie.selectMovieDetail", movie_id);
		return movieList;
	}
	
	@Override
	public List<MovieVO> searchMovie(String movie_keyword) throws DataAccessException {
		List<MovieVO> searchList = sqlSession.selectList("mapper.movie.searchMovie", movie_keyword);
		return searchList;
	}

	@Override
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
		List<String> list = (ArrayList) sqlSession.selectList("mapper.movie.selectKeywordSearch", keyword);
		return list;
	}

	@Override
	public void addOneLineReview(Map reviewMap) throws DataAccessException {
		int ONELINEREVIEWNO = NewOneLineReviewNO();
		reviewMap.put("oneLineReviewNO", ONELINEREVIEWNO);
		if(reviewMap.get("parentOneLineReviewNO") == null){ //원글
			reviewMap.put("parentOneLineReviewNO", ONELINEREVIEWNO);
		}
		sqlSession.insert("mapper.movie.addOneLineReview", reviewMap);
	}

	private int NewOneLineReviewNO() {
		return sqlSession.selectOne("mapper.movie.NewOneLineReviewNO");
	}

	@Override
	public List<MovieVO> oneLineReviewList(int movie_id) throws DataAccessException {
		List<MovieVO> oneLineReviewList = sqlSession.selectList("mapper.movie.oneLineReviewList", movie_id);
		return oneLineReviewList;
	}
	
	@Override
	public String getReviewPassword(Object oneLineReviewNO)throws DataAccessException {
        return sqlSession.selectOne("mapper.movie.getReviewPassword", oneLineReviewNO);
    }
	
	
	@Override
	public void deleteOneLineReview(Object oneLineReviewNO)throws DataAccessException {
		sqlSession.delete("mapper.movie.deleteOneLineReview", oneLineReviewNO);
	}
}
