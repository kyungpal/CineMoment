<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

</head>
<body>
	<div id="outer_wrap">
		<div id="wrap">
			<h1>예매 내역 정보</h1>
			<table class="list_view">
				<tbody align=center>
					<tr style="background: #5e58584b">
						<td>티켓 번호</td>
						<td>예매 일자</td>
						<td>예매 영화</td>
						<td>좌석 번호</td>
						<td>카드 번호</td>
						<td>카드사</td>
						<td>결제일</td>
						<td>결제 금액</td>
					</tr>
					<c:forEach var="item" items="${orderInfoList }">
						<tr>
							<td>${item.ticket_number}</td>
							<td>${item.movie_screening_date}<br> ${item.movie_running_time} </td>
							<td>${item.movie_title }</td>
							<td>${item.movie_seat_number }</td>
							<td>${item.card_number }</td>
							<td>${item.card_name }</td>
							<td>${item.pay_order_time }</td>
							<td>${item.movie_price }</td>
							<td>
								<form
									action="${pageContext.request.contextPath}/mypage/orderCancel.do"
									method="post" onsubmit="return confirm('정말 취소하시겠습니까?');">
									
									<input type="hidden" name="morder_seq_num" value="${item.morder_seq_num}"> 
									<input type="hidden" name="schedule_id" value="${item.schedule_id}">
									<input type="hidden" name="seat_id" value="${item.seat_id}"> 
									<input type="hidden" name="member_id" value="${item.member_id}">
										
									<!-- 이건 form을 전송함 -->
									<input type="submit" class="favorite styled" value="예매 취소하기" />
								</form>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<div class="clear"></div>
			<br> <br> <br>
			<div class="clear"></div>
			<br> <br> <br> <br> <br>

			<div class="clear"></div>
		</div>
	</div>