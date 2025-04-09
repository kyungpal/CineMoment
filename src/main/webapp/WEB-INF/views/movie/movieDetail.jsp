<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<head>
<link href="${contextPath}/resources/css/table.css" rel="stylesheet" />
<script>
	function fn_review_movie(movie_id, oneLineId, oneLinePassword, oneLineContent) {
		var review = document.review;
		var id = "";
		var password = "";
		var content = ""
		if (oneLineId == 'id') {
			id = review.id.value;
			password = review.password.value;
			content = review.content.value;
		}
		//빈칸체크
		   if (id.trim() === '') {
		        alert("닉네임을 입력해주세요.");
		        return;
		    }
		    if (password.trim() === '') {
		        alert("비밀번호를 입력해주세요.");
		        return;
		    }
		    if (content.trim() === '') {
		        alert("내용을 입력해주세요.");
		        return;
		    }

		$.ajax({
			type : "post",
			async : true,
			url : "${contextPath}/movie/oneLineReview.do",
			data : {
				movie_id : movie_id,
				id : id,
				password : password,
				content : content
			},
			success : function(data, textStatus) {
				if (data.trim() == 'success') {
					alert("한줄평을 작성하셨습니다.")
					location.reload();
				} else if (data.trim() == 'failed') {
					alert("다시 작성해 주세요.");
				}
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다." + data);
			},
			complete : function(data, textStatus) {
			}

		});
	}
</script>
<style>
</style>
</head>
<body>
	<div id="outer_wrap">
		<div id="wrap">
			<!-- foreach 구문 사용 -->
			<div class="movieDetail page-content-group">
				<div class="container">
					<c:forEach var="movieList" items="${movieList}"
						varStatus="movieListNum">

						<div class="col-poster-image">
							<div class="poster-image light"
								style="background-image: url('${contextPath}/thumbnails.do?movie_id=${movieList.movie_id}&fileName=${movieList.filename}')"></div>
						</div>
						<div class="col-content">
							<div class="movie-title-frame">
								<div class="text1">${movieList.movie_title }</div>
								<div class="level">
									<c:if test="${movieList.movie_rank == '전체 관람가'}">
										<div class="movie-rate-badge movie-rate-badge-all"></div>
									</c:if>
									<c:if test="${movieList.movie_rank == '12세 이상 관람가'}">
										<div class="movie-rate-badge movie-rate-badge-12"></div>
									</c:if>
									<c:if test="${movieList.movie_rank == '15세 이상 관람가'}">
										<div class="movie-rate-badge movie-rate-badge-15"></div>
									</c:if>
									<c:if test="${movieList.movie_rank == '18세 이상 관람가'}">
										<div class="movie-rate-badge movie-rate-badge-18"></div>
									</c:if>
									${movieList.movie_rank }
								</div>
							</div>
							<table class="pb-form-table">
								<tbody>
									<tr>
										<th>개봉일</th>
										<td>${movieList.movie_openday }</td>
									</tr>
									<tr>
										<th>감독</th>
										<td>${movieList.movie_director }</td>
									</tr>
									<tr>
										<th>평점</th>
										<td>${movieList.starpoint / 2 }</td>
									</tr>
									<tr>
										<th>주연</th>
										<td>${movieList.movie_actor }</td>
									</tr>
									<tr>
										<th>장르</th>
										<td>${movieList.movie_genre}</td>
									</tr>
									<tr>
										<th>러닝타임</th>
										<td>${movieList.movie_time}</td>
									</tr>
									<tr>
										<th>줄거리</th>
										<td>${movieList.movie_story}</td>
									</tr>
								</tbody>
							</table>
						</div>
				</div>
				<h1 style="font-family: 'GongGothicMedium'; font-weight: 100">한줄평</h1>
				<table>
					<c:choose>
						<c:when test="${oneLineReviewList == '[]'}">
							<a style="font-family: 'GongGothicMedium'; font-weight: 100">가장
								먼저 <strong>${movieList.movie_title }</strong> 에 대한 한줄평을 남겨보세요!
							</a>
							<br>
						</c:when>


						<c:when test="${oneLineReviewList != null }">
							<c:forEach var="oneLineReviewList" items="${oneLineReviewList }">
								<tr>
									<th><c:choose>
											<c:when
												test="${oneLineReviewList.oneLineReviewNO eq oneLineReviewList.parent_onelinereviewno}">
												<img src="${contextPath }/resources/image/profile.jpg"
													width=50px height=50px /></th>
						</c:when>
						<c:otherwise>
							<strong>┗</strong>
						</c:otherwise>
					</c:choose>

					<td class="review-td ${oneLineReviewList.oneLineReviewNO}"><a
						style="color: blue;"><strong>${oneLineReviewList.id }</strong></a><br>${oneLineReviewList.content }
						<button type="button" class="delete-btn"
							data-movie-id="${movieList.movie_id}"
							data-review-id="${oneLineReviewList.oneLineReviewNO}">삭제</button>
					</td>
					</tr>
					<c:if
						test="${oneLineReviewList.oneLineReviewNO eq oneLineReviewList.parent_onelinereviewno}">
						<tr class="recomment-tr-${oneLineReviewList.oneLineReviewNO}"
							style="display: none;">
							<th></th>
							<td>
								<form class="recomment-form" action="/movie/oneLineReview.do"
									method="post">
									<table>
										<tr>
											<th><span>닉네임</span></th>
											<td><input type="hidden" name="parentOneLineReviewNO"
												value="${oneLineReviewList.oneLineReviewNO}"
												aria-label="원글번호" /> <input type="hidden" name="movie_id"
												value="${oneLineReviewList.movie_id}" aria-label="영화번호" />
												<input type="text" name="id" aria-label="닉네임" /></td>
										</tr>
										<tr>
											<th><span>비밀번호</span></th>
											<td><input type="password" name="password"
												aria-label="비밀번호" /></td>
										</tr>
										<tr>
											<th>내용</th>
											<td><input type="text" name="content"
												class="recomment-input ${oneLineReviewList.oneLineReviewNO}" />
												<button type="button" name="recomment-btn"
													class="recomment-reg-btn ${oneLineReviewList.oneLineReviewNO}">등록</button>
											</td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
					</c:if>

					</c:forEach>
					</c:when>

					</c:choose>
					<script>
					$(document).ready(function() {
					    $('.delete-btn').click(function() {
					        var movie_id = $(this).data('movie-id');
					        var review_id = $(this).data('review-id');
					        var password = prompt("삭제하려면 비밀번호를 입력해주세요:");  // 비밀번호 입력받기

					        if (password) {
					            $.ajax({
					                type: "POST",
					                url: "${contextPath}/movie/deleteOneLineReview.do",
					                data: {
					                    movie_id: movie_id,
					                    oneLineReviewNO: review_id,
					                    password: password
					                },
					                success: function(response) {
					                    if (response.trim() == 'success') {
					                        alert("한줄평이 삭제되었습니다.");
					                        location.reload();  // 페이지 리로드하여 삭제된 리뷰를 반영
					                    } else if (response.trim() == 'wrong_password') {
					                        alert("비밀번호가 잘못되었습니다.");
					                    }
					                },
					                error: function() {
					                    alert("에러가 발생했습니다.");
					                }
					            });
					        }
					    });
					});

					</script>


					<script>
						$(document).ready(function(){
							$('.recomment-reg-btn').click(function(e){
								var $form = $(this).closest('form');
								// $.find('input[name="parentOneLineReviewNO"]').val()
								var formDataObject = $form.serializeArray().reduce(function(obj,item){
									obj[item.name] = item.value;
									return obj;
								},{})

								if(formDataObject.id === ''){
									alert('닉네임을 입력해주세요');
									return;
								}
								if(formDataObject.password === ''){
									alert('비밀번호를 입력해주세요');
									return;
								}
								if(formDataObject.content === ''){
									alert('내용을 입력해주세요');
									return;
								}
								$.ajax({
									type : "post",
									async : true,
									url : "${contextPath}/movie/oneLineReview.do",
									data : formDataObject,
									success : function(data, textStatus) {
										if (data.trim() == 'success') {
											alert("한줄평을 작성하셨습니다.")
											location.reload();
										} else if (data.trim() == 'failed') {
											alert("다시 작성해 주세요.");
										}
									},
									error : function(data, textStatus) {
										alert("에러가 발생했습니다." + data);
									},
									complete : function(data, textStatus) {
										//alert("작업을완료 했습니다");

									}


								})
							})
						})
						$(document).ready(()=>{
							$('.review-td').click(function(){
								console.log(this.classList)
								var movie_id = this.classList[1];
								console.log(movie_id)
								var rt = $('.recomment-tr-'+movie_id);
								console.log(rt)
								rt.toggle()
								if(!rt.is(":visible")){
									$(rt).find('textarea').val("")
								}
							})
						})

					</script>
					<form name="review" method="post">
						<tr>
							<th>닉네임</th>
							<td><input type="text" name="id" maxlength="20" /></td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="password" maxlength="20" />
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td><input type="text" name="content" maxlength="50" /><input
								class="onelinereviewAddBtn" type="button" value="등록"
								onClick="fn_review_movie('${movieList.movie_id}','id','password','content')" /></td>
						</tr>
					</form>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
