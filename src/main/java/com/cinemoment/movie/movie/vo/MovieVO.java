package com.cinemoment.movie.movie.vo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("movieVO")
public class MovieVO {
	private int movie_id;
	private String movie_title;
	private String movie_keyword;
	private String movie_genre;
	private String movie_time;
	private String movie_director;
	private String movie_actor;
	private String movie_story;
	private String movie_openday;
	private String movie_rank;
	private String movie_producer;
	private String movie_status;
	private String filename;
	private int starpoint;
	private int oneLineReviewNO;
	private String id;
	private String password;
	private String content;
	private int parent_onelinereviewno;
	private List<MovieVO> list;

	@Override
	public String toString() {
		return "MovieVO [movie_id=" + movie_id + ", movie_title=" + movie_title + ", movie_keyword=" + movie_keyword
				+ ", movie_genre=" + movie_genre + ", movie_time=" + movie_time + ", movie_director=" + movie_director
				+ ", movie_actor=" + movie_actor + ", movie_story=" + movie_story + ", movie_openday=" + movie_openday
				+ ", movie_rank=" + movie_rank + ", movie_producer=" + movie_producer + ", movie_status=" + movie_status
				+ ", filename=" + filename + ", starpoint=" + starpoint + ", oneLineReviewNO=" + oneLineReviewNO
				+ ", id=" + id + ", password=" + password + ", content=" + content + ", parent_onelinereviewno="
				+ parent_onelinereviewno + ", list=" + list + "]";
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public String getMovie_keyword() {
		return movie_keyword;
	}

	public void setMovie_keyword(String movie_keyword) {
		this.movie_keyword = movie_keyword;
	}

	public String getMovie_genre() {
		return movie_genre;
	}

	public void setMovie_genre(String movie_genre) {
		this.movie_genre = movie_genre;
	}

	public String getMovie_time() {
		return movie_time;
	}

	public void setMovie_time(String movie_time) {
		this.movie_time = movie_time;
	}

	public String getMovie_director() {
		return movie_director;
	}

	public void setMovie_director(String movie_director) {
		this.movie_director = movie_director;
	}

	public String getMovie_actor() {
		return movie_actor;
	}

	public void setMovie_actor(String movie_actor) {
		this.movie_actor = movie_actor;
	}

	public String getMovie_story() {
		return movie_story;
	}

	public void setMovie_story(String movie_story) {
		this.movie_story = movie_story;
	}

	public String getMovie_openday() {
		return movie_openday;
	}

	public void setMovie_openday(String movie_openday) {
		this.movie_openday = movie_openday;
	}

	public String getMovie_rank() {
		return movie_rank;
	}

	public void setMovie_rank(String movie_rank) {
		this.movie_rank = movie_rank;
	}

	public String getMovie_producer() {
		return movie_producer;
	}

	public void setMovie_producer(String movie_producer) {
		this.movie_producer = movie_producer;
	}

	public String getMovie_status() {
		return movie_status;
	}

	public void setMovie_status(String movie_status) {
		this.movie_status = movie_status;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getStarpoint() {
		return starpoint;
	}

	public void setStarpoint(int starpoint) {
		this.starpoint = starpoint;
	}

	public int getOneLineReviewNO() {
		return oneLineReviewNO;
	}

	public void setOneLineReviewNO(int oneLineReviewNO) {
		this.oneLineReviewNO = oneLineReviewNO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getParent_onelinereviewno() {
		return parent_onelinereviewno;
	}

	public void setParent_onelinereviewno(int parent_onelinereviewno) {
		this.parent_onelinereviewno = parent_onelinereviewno;
	}

	public List<MovieVO> getList() {
		return list;
	}

	public void setList(List<MovieVO> list) {
		this.list = list;
	}

}
