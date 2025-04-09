<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>

<!-- css -->
<style>
img {
	display: block;
	margin: auto;
}

.button {
	border: 1px solid #000000; /*---테두리 정의---*/
	background-Color: #ffffff; /*--백그라운드 정의---*/
	font: 25px 굴림; /*--폰트 정의---*/
	font-weight: bold; /*--폰트 굵기---*/
	color: #000000; /*--폰트 색깔---*/
	width: 50;
	height: 50; /*--버튼 크기---*/
	margin-top: 20px;
	border-radius : 5px;
}

.button:hover {
	background: gray;
	color: white;
}

.button[disabled] {
	background-color: red;
}

text {
	font-size: large;
}
</style>

<script >
function test(e, url) {
    // 좌석 번호는 버튼의 value 속성에 들어있으므로 그걸 사용
    var seatNum = $(e).val(); // value="${seat.movie_seat_number}"

    console.log('선택한 좌석번호 :' + seatNum);
    $(e).prop("disabled", true); // 중복 클릭 방지

    // 폼 생성
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", url);

    // hidden input에 좌석 번호 담기
    var seatNumInput = document.createElement("input");
    seatNumInput.setAttribute("type", "hidden");
    seatNumInput.setAttribute("name", "seatNum");
    seatNumInput.setAttribute("value", seatNum); // 수정된 부분

    form.appendChild(seatNumInput);
    document.body.appendChild(form);
    form.submit();
}

</script>

</head>
<body>
	<div id="outer_wrap">
		<div id="wrap">
			<img src="${contextPath}/resources/image/screen.png" style = "border-radius : 20px;"/>
			<!-- 예매하기 이미지 -->

			<div style="text-align: center; margin-top: 20px;">

<c:if test="${movie_place == '1관'}">
    <c:forEach var="seat" items="${seatList}" varStatus="status">
        <c:set var="index" value="${status.index}" />

        <%-- 10개마다 줄바꿈 --%>
        <c:if test="${index % 10 == 0 && index != 0}">
            <br>
        </c:if>

        <%-- 좌석 버튼 출력 (예약 여부에 따라 다르게 표시) --%>
        <c:choose>
            <c:when test="${seat.is_reserved == 'Y'}">
                <input type="button" class="button reserved" 
                    value="${seat.movie_seat_number} (예약됨)" 
                    id="seat_${movie_place}_${seat.movie_seat_number}" 
                    disabled />
            </c:when>
            <c:otherwise>
                <input type="button" class="button"
                    value="${seat.movie_seat_number}"
                    id="seat_${movie_place}_${seat.movie_seat_number}"
                    onclick="location.href='${contextPath}/order/ticketingForm.do?seat_id=${seat.seat_id}&schedule_id=${schedule.schedule_id}&seatNum=${seat.movie_seat_number}'" />
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>
<c:if test="${movie_place == '2관'}">
    <c:forEach var="seat" items="${seatList}" varStatus="status">
        <c:set var="index" value="${status.index}" />

        <%-- 10개마다 줄바꿈 --%>
        <c:if test="${index % 10 == 0 && index != 0}">
            <br>
        </c:if>

        <%-- 좌석 버튼 출력 (예약 여부에 따라 다르게 표시) --%>
        <c:choose>
            <c:when test="${seat.is_reserved == 'Y'}">
                <input type="button" class="button reserved" 
                    value="${seat.movie_seat_number} (예약됨)" 
                    id="seat_${movie_place}_${seat.movie_seat_number}" 
                    disabled />
            </c:when>
            <c:otherwise>
                <input type="button" class="button"
                    value="${seat.movie_seat_number}"
                    id="seat_${movie_place}_${seat.movie_seat_number}"
                    onclick="location.href='${contextPath}/order/ticketingForm.do?seat_id=${seat.seat_id}&schedule_id=${schedule.schedule_id}&seatNum=${seat.movie_seat_number}'" />
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>
<c:if test="${movie_place == '3관'}">
    <c:forEach var="seat" items="${seatList}" varStatus="status">
        <c:set var="index" value="${status.index}" />

        <%-- 10개마다 줄바꿈 --%>
        <c:if test="${index % 10 == 0 && index != 0}">
            <br>
        </c:if>

        <%-- 좌석 버튼 출력 (예약 여부에 따라 다르게 표시) --%>
        <c:choose>
            <c:when test="${seat.is_reserved == 'Y'}">
                <input type="button" class="button reserved" 
                    value="${seat.movie_seat_number} (예약됨)" 
                    id="seat_${movie_place}_${seat.movie_seat_number}" 
                    disabled />
            </c:when>
            <c:otherwise>
                <input type="button" class="button"
                    value="${seat.movie_seat_number}"
                    id="seat_${movie_place}_${seat.movie_seat_number}"
                    onclick="location.href='${contextPath}/order/ticketingForm.do?seat_id=${seat.seat_id}&schedule_id=${schedule.schedule_id}&seatNum=${seat.movie_seat_number}'" />
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>
<c:if test="${movie_place == '4관'}">
    <c:forEach var="seat" items="${seatList}" varStatus="status">
        <c:set var="index" value="${status.index}" />

        <%-- 10개마다 줄바꿈 --%>
        <c:if test="${index % 10 == 0 && index != 0}">
            <br>
        </c:if>

        <%-- 좌석 버튼 출력 (예약 여부에 따라 다르게 표시) --%>
        <c:choose>
            <c:when test="${seat.is_reserved == 'Y'}">
                <input type="button" class="button reserved" 
                    value="${seat.movie_seat_number} (예약됨)" 
                    id="seat_${movie_place}_${seat.movie_seat_number}" 
                    disabled />
            </c:when>
            <c:otherwise>
                <input type="button" class="button"
                    value="${seat.movie_seat_number}"
                    id="seat_${movie_place}_${seat.movie_seat_number}"
                    onclick="location.href='${contextPath}/order/ticketingForm.do?seat_id=${seat.seat_id}&schedule_id=${schedule.schedule_id}&seatNum=${seat.movie_seat_number}'" />
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>
<c:if test="${movie_place == '5관'}">
    <c:forEach var="seat" items="${seatList}" varStatus="status">
        <c:set var="index" value="${status.index}" />

        <%-- 10개마다 줄바꿈 --%>
        <c:if test="${index % 10 == 0 && index != 0}">
            <br>
        </c:if>

        <%-- 좌석 버튼 출력 (예약 여부에 따라 다르게 표시) --%>
        <c:choose>
            <c:when test="${seat.is_reserved == 'Y'}">
                <input type="button" class="button reserved" 
                    value="${seat.movie_seat_number} (예약됨)" 
                    id="seat_${movie_place}_${seat.movie_seat_number}" 
                    disabled />
            </c:when>
            <c:otherwise>
                <input type="button" class="button"
                    value="${seat.movie_seat_number}"
                    id="seat_${movie_place}_${seat.movie_seat_number}"
                    onclick="location.href='${contextPath}/order/ticketingForm.do?seat_id=${seat.seat_id}&schedule_id=${schedule.schedule_id}&seatNum=${seat.movie_seat_number}'" />
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>
			</div>
		</div>
	</div>
</body>
</html>