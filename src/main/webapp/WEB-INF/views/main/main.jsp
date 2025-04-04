<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
%>

<head>
<style>
.font{
font-family: 'NotoSansKR';
}
.tabBtn_wrap h3 a {
    position: relative;
    display: inline-block;
    padding: 10px 20px;
    font-size: 18px;
    text-decoration: none;
    color: #333;
    transition: color 0.3s ease-in-out;
    overflow: hidden;
}


.tabBtn_wrap h3 a::after {
    content: "";
    position: absolute;
    left: 50%;
    bottom: 0;
    width: 0;
    height: 3px;
    background-color: #ff5733;
    transition: all 0.3s ease-in-out;
    transform: translateX(-50%);
}

.tabBtn_wrap h3 a:hover {
    color: #ff5733;
}

.tabBtn_wrap h3 a:hover::after {
    width: 100%;
}
</style>
<script>


$(document).ready(function() {
	$('.effect5').show(); //페이지를 로드할 때 표시할 요소
	$('.effect51').hide(); //페이지를 로드할 때 숨길 요소
	$('.active1').click(function(){
	$ ('.effect5').hide(); //클릭 시 첫 번째 요소 숨김
	$ ('.effect51').show(); //클릭 시 두 번째 요소 표시
	return false;
	
	});
	$('.active').click(function(){
		$ ('.effect51').hide(); //클릭 시 첫 번째 요소 숨김
		$ ('.effect5').show(); //클릭 시 두 번째 요소 표시
		return false;
		
		});
	});
	
function fn_form(isLogOn,url, movieNum, movieTitle,loginForm){
	if(isLogOn != '' && isLogOn != 'false'){
	 var form = document.createElement("form");
	 form.setAttribute("method", "post");
	 form.setAttribute("action", url);
   var movieNumInput = document.createElement("input");
   movieNumInput.setAttribute("type","hidden");
   movieNumInput.setAttribute("name","movieNum");
   movieNumInput.setAttribute("value", movieNum);
   
   var movieTitleInput = document.createElement("input");
   movieTitleInput.setAttribute("type","hidden");
   movieTitleInput.setAttribute("name","movieTitle");
   movieTitleInput.setAttribute("value", movieTitle);
	 
   form.appendChild(movieNumInput);
   form.appendChild(movieTitleInput);
   document.body.appendChild(form);
	 form.submit();
	}
	else{
	    alert("로그인 후 예매가 가능합니다.")
	    location.href=loginForm+'?action=/member/loginForm.do';
	  }
}
	
</script>
</head>
<body>
<div class="movieChartBeScreen_wrap">
	<div id="outer_wrap">

		<div id="wrap">
			<div class="movieChartBeScreen_btn_wrap">
				<div class="tabBtn_wrap">
					<h3>
						<a href="#none" class="active" id="btnMovie" >박스오피스</a>
					</h3>
					<h3>
						<a href="#none" id="btnReserMovie" class="active1">상영예정작</a>
					</h3>
				</div>
			</div>
			<!-- 현재상영작 -->
		<div class="effect-wrap">
    <c:forEach var="titleList" items="${titleList1}" varStatus="titleListNum">
        <c:if test="${titleList.movie_status == 'y' }">
            <figure class="effect5" style="position: relative; display: inline-block;">
                <img src="${contextPath}/thumbnails.do?movie_id=${titleList.movie_id}&fileName=${titleList.movie_filename}"
                     style="border-radius: 10px;">
                <!-- 버튼을 썸네일 하단에 배치 -->
					<div style="text-align: center; display: flex;">
                    <a href="${contextPath}/movie/movieDetail.do?movie_id=${titleList.movie_id}" class="myButton">상세보기</a>
                    <a href="javascript:callFunction();"
                       onClick="fn_form('${isLogOn}','${contextPath}/order/timeselect.do', ${titleList.movie_id},'${titleList.movie_title}','${contextPath}/member/loginForm.do')"
                       class="myButton1">예매하기</a>
                </div>
            </figure>
        </c:if>
    </c:forEach>
</div>
			<!-- 상영예정작 -->
			<div class="effect-wrap">
				<c:forEach var="titleList" items="${titleList2 }"
						   varStatus="titleListNum">
					<c:if test="${titleList.movie_status == 'e' }">
						 <figure class="effect51" style="position: relative; display: inline-block;">
							<img alt="HTML5 &amp; CSS3"
								 src="${contextPath}/thumbnails.do?movie_id=${titleList.movie_id}&fileName=${titleList.movie_filename}"
								style="border-radius: 10px; display: block; width: 100%;">
								  <!-- 버튼을 썸네일 하단에 배치 -->
								<div style="text-align: center; display: flex; ">
									<a href="${contextPath}/movie/movieDetail.do?movie_id=${titleList.movie_id}"
											class="myButton2">상세보기</a> 
								</div>
						</figure>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

	<div class="movieSelection_wrap">
		<div class="contents">

			<div class="video_wrap">
				
				<video autoplay="" muted="" loop="">
					<source src="http://h.vod.cgv.co.kr/vodCGVa/89485/89485_234628_1200_128_960_540.mp4" type="video/mp4">
				</video>

				<strong class="movieSelection_title">승부</strong> <span
					class="movieSelection_txt">김형주 감독 작품<br>세계 최고 바둑 대회에서 국내 최초 우승자가 된 조훈현의 이야기!
				</span>
				<div class="movieSelection_video_controller_wrap">
					<a
						href="http://localhost:8090/movie/movie/movieDetail.do?movie_id=1"
						class="btn_movieSelection_detailView">상세보기</a>
				</div>
			</div>
		</div>
	</div>
	<div id="outer_wrap">
		<div id="wrap">
			<div class=slideshow-container1>
				<div class="movieChartBeScreen_btn_wrap">
					<div class="tabBtn_wrap">
						<h3>
							<a href="#none" class="active">이벤트</a>
						</h3>
					</div>
				</div>
				<div class="slideshow-container">

					<div class="mySlides fade">
						<a href="${contextPath}/board/eventView.do?eventBoardNO=1"> <img
							src="${contextPath }/resources/image/Untitled-1.png"
							style="width: 100%">
						</a>
					</div>

					<div class="mySlides fade">
						<a href="${contextPath}/board/eventView.do?eventBoardNO=2"> <img
							src="${contextPath }/resources/image/Untitled-2.png"
							style="width: 100%">
						</a>
					</div>

					<div class="mySlides fade">
						<a href="${contextPath}/board/eventView.do?eventBoardNO=3"> <img
							src="${contextPath }/resources/image/Untitled-3.png"
							style="width: 100%">
						</a>
					</div>

				</div>
				<div style="text-align: center; margin-top: 20px;">
					<span class="dot"></span> <span class="dot"></span> <span
						class="dot"></span>
				</div>
			</div>

			<script src="${contextPath}/resources/jquery/app.js"
				type="text/javascript"></script>

			<section class="notice">
				<!-- board list area -->
				<div class="movieChartBeScreen_btn_wrap">
					<div class="tabBtn_wrap">
						<h3>
							<a href="#none" class="active">공지사항</a>
						</h3>
					</div>
				</div>
				<a href="${contextPath}/board/notice.do">
					<p style="text-align: right; color: gray; font-size: 11px;">더보기</p>
				</a>
				<div id="board-list">
						<table class="board-table">
							<thead>
								<tr>
									<th scope="col" class="th-num">번호</th>
									<th scope="col" class="th-title">제목</th>
									<th scope="col" class="th-date">등록일</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${noticeList == null }">
										<tr height="10">
											<td colspan="3">
												<p align="center">
													<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
												</p>
											</td>
										</tr>
									</c:when>
									<c:when test="${noticeList != null }">
										<c:forEach var="noticeList" items="${noticeList }"
											varStatus="noticeListNum">
											<c:if test="${noticeListNum.count <= 5 }">
												<tr>
													<td>${noticeListNum.count }</td>
													<th><a
														href="${contextPath}/board/noticeView.do?noticeBoardNO=${noticeList.boardNO}">${noticeList.boardTitle }</a>
													</th>
													<td>${noticeList.boardWriteDate }</td>
												</tr>
											</c:if>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
				</div>

			</section>
		</div>
	</div>

</body>
