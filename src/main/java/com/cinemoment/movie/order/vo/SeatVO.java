package com.cinemoment.movie.order.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("seatVO")
public class SeatVO {
	
	 	private int seat_id;
	    private String movie_place;
	    private int movie_seat_number;
	    private String is_reserved;
	    private int schedule_id;
	    
		@Override
		public String toString() {
			return "SeatVO [seat_id=" + seat_id + ", movie_place=" + movie_place + ", movie_seat_number="
					+ movie_seat_number + ", is_reserved=" + is_reserved + "]";
		}
		
		
		public int getSchedule_id() {
			return schedule_id;
		}


		public void setSchedule_id(int schedule_id) {
			this.schedule_id = schedule_id;
		}


		public int getSeat_id() {
			return seat_id;
		}
		public void setSeat_id(int seat_id) {
			this.seat_id = seat_id;
		}
		public String getMovie_place() {
			return movie_place;
		}
		public void setMovie_place(String movie_place) {
			this.movie_place = movie_place;
		}
		public int getMovie_seat_number() {
			return movie_seat_number;
		}
		public void setMovie_seat_number(int movie_seat_number) {
			this.movie_seat_number = movie_seat_number;
		}
		public String getIs_reserved() {
			return is_reserved;
		}
		public void setIs_reserved(String is_reserved) {
			this.is_reserved = is_reserved;
		}
	    
	    
}