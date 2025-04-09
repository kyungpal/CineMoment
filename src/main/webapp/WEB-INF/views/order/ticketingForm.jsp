<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
<style>
img {
	display: block;
	margin: auto;
}

.button {
	border: 1px solid #000000; /*---테두리 정의---*/
	background-Color: #ffffff; /*--백그라운드 정의---*/
	font-size : 20px;
	font-family: 'GongGothicMedium';
	color: #000000; /*--폰트 색깔---*/
	width: 300;
	height: 50; /*--버튼 크기---*/
	margin-top: 20px;
	border-radius : 10px;
}

.button:hover {
	background: gray;
	color: white;
}

.ticketinginput {
	width: 300px;
	height: 32px;
	font-size: 15px;
	font-weight: 900;
	border: 0;
	border-radius: 5px;
	outline: none;
	padding-left: 10px;
	background-color: rgb(233, 233, 233);
	margin: 0px 0px 20px 0px;
	border: 2px solid black;
	
}

.ticketinginput1 {
	width: 60px;
	height: 32px;
	font-size: 15px;
	border: 0;
	border-radius: 5px;
	outline: none;
	padding-left: 10px;
	background-color: rgb(233, 233, 233);
	margin: 0px 0px 20px 0px;
	border: 2px solid black;
}

.wrapper1 {
	padding-left: 20px;
	padding-right: 20px;
	max-width: 300px;
	margin: 0 auto;
}

text {
	maring-top: 20px;
}
</style>
<script>
  function updatePrice() {
    const ageRadios = document.getElementsByName("orderd_age");
    const priceInput = document.getElementById("movie_price");
    for (let i = 0; i < ageRadios.length; i++) {
      if (ageRadios[i].checked) {
        if (ageRadios[i].value === "청소년") {
          priceInput.value = "9000";
        } else {
          priceInput.value = "14000";
        }
      }
    }
  }
</script>
</head>
<body>

	<div id="outer_wrap">
		<div id="wrap">
			<div class="wrapper1">
			
				<form name="finalForm" method="post"
					action="${contextPath }/order/addOrder.do" enctype="text">
					 <input type="hidden" name="seat_id" value="${param.seat_id}" />
  					<input type="hidden" name="schedule_id" value="${param.schedule_id}" />

					영화 제목<br> <input type="text" class="ticketinginput"
						name="movie_title" value="${sessionScope.movieTitle }"readOnly/ ><br>
					좌석 번호<br> <input type="text" class="ticketinginput"
						name="movie_seat_number" value="${sessionScope.movie_seat_number }" readOnly /><br>
					상영 장소<br> <input type="text" class="ticketinginput"
						name="movie_place" value="${sessionScope.movie_place }"readOnly/ ><br>
					상영 날짜<br> <input type="text" class="ticketinginput"
						name="movie_screening_date" value="${schedule.movie_screening_date }"readOnly/ ><br>
					상영 시간<br> <input type="text" class="ticketinginput"
						name="movie_running_time" value="${schedule.movie_running_time }"readOnly/ ><br>
					티켓 개수<br> <input type="text" class="ticketinginput"
						name="movie_people_qty" value="1" readOnly /><br> 결제 방법 <input
						type="radio" name="pay_method" value="card" checked="checked" />카드<br>
					<br> 나이 <input type="radio" name="orderd_age" value="성인" checked onclick="updatePrice()" />성인
								<input type="radio" name="orderd_age" value="청소년" onclick="updatePrice()" />청소년
						<br>
					<br> 핸드폰번호<br> <select class="ticketinginput1"
						name="hpNum1">
						<option value="010" selected="selected">010</option>
					</select>-<input type="text" class="ticketinginput1" name="hpNum2" size="4" maxlength="4"
							oninput="this.value = this.value.replace(/[^0-9]/g, '')"/>-<input
						type="text" class="ticketinginput1" name="hpNum3" size="4" maxlength="4"
							oninput="this.value = this.value.replace(/[^0-9]/g, '')"/><br>
					카드 회사<br> <input type="text" class="ticketinginput1"
						name="card_name" /><br> 카드번호<br> <input type="text"
						class="ticketinginput1" name="cardNum1" size="4"maxlength="4"
							oninput="this.value = this.value.replace(/[^0-9]/g, '')" />- <input
						type="text" class="ticketinginput1" name="cardNum2" size="4" maxlength="4"
							oninput="this.value = this.value.replace(/[^0-9]/g, '')"/>- <input
						type="text" class="ticketinginput1" name="cardNum3" size="4" maxlength="4"
							oninput="this.value = this.value.replace(/[^0-9]/g, '')" />- <input
						type="text" class="ticketinginput1" name="cardNum4" size="4" maxlength="4"
							oninput="this.value = this.value.replace(/[^0-9]/g, '')"/><br>
					유호기간<br> <input type="text" class="ticketinginput1"
						name="cardYear" placeholder="YY" size="2" maxlength="2"
							oninput="this.value = this.value.replace(/[^0-9]/g, '')" />/ <input type="text"
						class="ticketinginput1" name="cardMonth" placeholder="MM" size="2"maxlength="2"
							oninput="this.value = this.value.replace(/[^0-9]/g, '')" /><br>
					가격<br> <input type="text"  id="movie_price" class="ticketinginput"
						name="movie_price" value="14000" size="4" readOnly /><br>
						
						<input type="submit" class="button" value="예매하기" />
				</form>
			</div>
		</div>
	</div>


</body>
</html>