<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<c:if test="${empty sessionScope.member or sessionScope.member.member_id ne 'admin'}">
    <script>
        alert('운영자만 접근이 가능합니다.');
        location.href = '${contextPath}/main/main.do'; // 로그인 페이지로 리디렉트
    </script>
</c:if>
<html>
<head>
<style>
.button {
	border: 1px solid #000000;
	background-Color: #ffffff;
	font: 25px 굴림;
	font-weight: bold;
	color: #000000;
	width: 50;
	height: 50;
	margin-top: 20px;
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

</head>
<body>
	<body>
	<div id="outer_wrap">
		<div id="wrap">
			<h1>전체 회원 예매 내역</h1>
			<table class="list_view">
				<tbody align=center>
					<tr style="background: #5e58584b">
						<td>구매자</td>
						<td>예매 일자</td>
						<td>예매 영화</td>
						<td>좌석 번호</td>
						<td>카드 번호</td>
						<td>카드사</td>
						<td>결제일</td>
						<td>결제 금액</td>
					</tr>
					<c:forEach var="item" items="${orderList }">
						<tr>
							<td>${item.member_id}</td>
							<td>${item.movie_screening_date}<br> ${item.movie_running_time} </td>
							<td>${item.movie_title }</td>
							<td>${item.movie_seat_number }</td>
							<td>${item.card_number }</td>
							<td>${item.card_name }</td>
							<td>${item.pay_order_time }</td>
							<td>${item.movie_price }</td>
							<td>
						<form action="${pageContext.request.contextPath}/admin/adminOrderCancel.do" method="post"
				     					 onsubmit="return confirm('정말 취소하시겠습니까?');">
									
									<input type="hidden" name="morder_seq_num" value="${item.morder_seq_num}"> 
									<input type="hidden" name="schedule_id" value="${item.schedule_id}">
									<input type="hidden" name="seat_id" value="${item.seat_id}"> 
									<input type="hidden" name="member_id" value="${item.member_id}">
										
									<!-- 이건 form을 전송함 -->
									<input type="submit" class="favorite styled" value="예매 취소하기" />
					</form>

						</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			
				<%-- 	<div class="cls2">
						<c:if test="${totalBoard != null }">
							<c:choose>
								<c:when test="${totalBoard >100 }">
									<!-- 글 개수가 100 초과인경우 -->
									<c:forEach var="page" begin="1" end="10" step="1">
										<c:if test="${section >1 && page==1 }">
											<a
												href="${contextPath }/admin/orderManage.do?section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;
												pre </a>
										</c:if>
										<a
											href="${contextPath }/admin/orderManage.do?section=${section}&pageNum=${page}">${(section-1)*10 +page }
										</a>
										<c:if test="${page ==10 }">
											<a
												href="${contextPath }/admin/orderManage.do?section=${section+1}&pageNum=${section*10+1}">&nbsp;
												next</a>
										</c:if>
									</c:forEach>
								</c:when>
								<c:when test="${totalBoard ==100 }">
									<!--등록된 글 개수가 100개인경우  -->
									<c:forEach var="page" begin="1" end="10" step="1">
										<a href="#">${page } </a>
									</c:forEach>
								</c:when>

								<c:when test="${totalBoard< 100 }">
									<!--등록된 글 개수가 100개 미만인 경우  -->
									<c:forEach var="page" begin="1" end="${totalBoard/10 +1}"
										step="1">
										<c:choose>
											<c:when test="${page==pageNum }">
												<a
													href="${contextPath }/admin/orderManage.do?section=${section}&pageNum=${page}">${page }
												</a>
											</c:when>
											<c:otherwise>
												<a
													href="${contextPath }/admin/orderManage.do?section=${section}&pageNum=${page}">${page }
												</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:if>
					</div> --%>
			
			<div class="clear"></div>
			<br> <br> <br>
			<div class="clear"></div>
			<br> <br> <br> <br> <br> 
		
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>