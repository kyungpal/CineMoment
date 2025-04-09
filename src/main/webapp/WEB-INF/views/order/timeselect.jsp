<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
<style>
#font{
font-family: 'GongGothicMedium';
font-size : 20px;
}

img {
	display: block;
	margin: auto;
}

.timeselectinput {
	margin-top: 20px;
}

.button {
	border: 1px solid #000000; /*---테두리 정의---*/
	background-Color: #ffffff; /*--백그라운드 정의---*/
	font: 12px 굴림; /*--폰트 정의---*/
	color: #000000; /*--폰트 색깔---*/
	width: 250;
	height: 50; /*--버튼 크기---*/
	margin-top: 20px;
	border-radius : 10px;
}

.button:hover {
	background: gray;
	color: white;
}

</style>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	</head>
	<body>
		<div id="outer_wrap">
			<div id="wrap">

				<div style="text-align: center; margin-top: 20px;">
						
					<form name="timeselectForm" method="post" action="${contextPath }/order/seatselect.do" enctype="text">
					
					<br> 
		<form action="${contextPath}/seatselect.do" method="post">
    	<select name="schedule_id">
        <c:forEach var="schedule" items="${scheduleList}">
         <option value="${schedule.schedule_id}">
          ${schedule.movie_screening_date} ${schedule.movie_running_time} ${schedule.movie_place}
        </option>
        </c:forEach>
    </select>
    <!-- movie_place는 첫 번째 schedule 기준으로 전달 -->
    <input type="hidden" name="movie_place" value="${scheduleList[0].movie_place}" />
    <input type="submit" value="좌석 선택하기" />
		</form>

		
				</form>
			</div>
		</div>
	</div>
</body>
</html>