package com.cinemoment.movie.admin.service;

import java.util.List;
import java.util.Map;

import com.cinemoment.movie.member.vo.MemberVO;
import com.cinemoment.movie.movie.vo.ImageFileVO;
import com.cinemoment.movie.movie.vo.MovieVO;


public interface AdminService {

	public int addNewMovie(Map addMovieMap) throws Exception;

	public List<MovieVO> movieManageList() throws Exception;

	public void deleteMovie(int movie_id) throws Exception;

	public List deleteMovieImage(int movie_id) throws Exception;

	public void modifyMovieInfo(Map movieMap) throws Exception;

	public void allModifyMovie(Map modMovieMap) throws Exception;

	public List selectMovieImageFileList(int movie_id) throws Exception;

	public void modifyMovieImage(List<ImageFileVO> imageFileList) throws Exception;

	public List selectOrderDetailInfo(String member_id)throws Exception;
	}

