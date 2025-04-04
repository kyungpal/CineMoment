<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
<style>
/* focus는 선택되서 커서가 깜빡일 때를 이야기함*/
input[type="text"] {
	font-size: 17px;
	color: black;
	font-weight: bold;
	border-radius: 10px;
}

input[type="text"]:focus {
	background-color: lemonchiffon;
}

textarea {
	font-size: 17px;
	color: black;
	font-weight: bold;
	border-radius: 10px;
}

textarea:focus {
	background-color: lemonchiffon;
}

select {
	color: black;
	font-weight: bold;
	border-radius: 10px;
	font-size: 17px;
}
</style>

<link href="${contextPath}/resources/css/table.css" rel="stylesheet" />
<script type="text/javascript">
	var cnt = 0;
	function fn_addFile() {
		if (cnt == 0) {
			$("#d_file")
					.append(
							"<br>"
									+ "<input  type='file' name='main_image' id='f_main_image' />");
		} else {
			$("#d_file")
					.append(
							"<br>"
									+ "<input  type='file' name='detail_image"+cnt+"' />");
		}

		cnt++;
	}
	function validateForm() {
		var boardContent = document.getElementById("Content")
        var fileInput = document.getElementById("f_main_image");
		   // 리뷰 이미지가 없으면 경고
        if (!fileInput || fileInput.files.length === 0) {
            alert("리뷰 이미지를 첨부해야 글을 등록할 수 있습니다.");
            return false; // 폼 제출 방지
        }
        // 글 내용이 비어있으면 경고
        if (!boardContent || boardContent.value.trim() === "") {
            alert("글 내용을 작성해야 합니다.");
            return false; // 폼 제출 방지
        }
        return true; // 제출 허용
    }
</script>
</head>
<body>
	<div id="outer_wrap">
		<div id="wrap">

			<form name="reviewForm" method="post"
				action="${contextPath }/board/reviewMod.do"
				enctype="multipart/form-data"
				onsubmit="return validateForm()">

				<input type="text" name="boardNO" value="${review.board.boardNO}" readOnly /> <!-- 글번호확인용 -->
				
				<table>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="member_id" size="20"
							maxlength="40" value="${review.board.member_id}" readOnly /></td>
					</tr>
					<tr>
						<th>글 제목</th>
						<td>
							<input type="text" name="boardTitle" size="66" maxlength="200" value="${review.board.boardTitle}" />
						</td>
					</tr>
					<tr>
						<th>글 내용</th>
						<td><textarea name="boardContent" rows="10" cols="99" id="Content"
								maxlength="4000">${review.board.boardContent}</textarea></td>
					</tr>
					<tr>
						<th>평점</th>
						<td><div class="starpoint_wrap">
								<div class="starpoint_box">
									<label for="starpoint_1" class="label_star" title="0.5"><span
										class="blind">0.5점</span></label> <label for="starpoint_2"
										class="label_star" title="1"><span class="blind">1점</span></label>
									<label for="starpoint_3" class="label_star" title="1.5"><span
										class="blind">1.5점</span></label> <label for="starpoint_4"
										class="label_star" title="2"><span class="blind">2점</span></label>
									<label for="starpoint_5" class="label_star" title="2.5"><span
										class="blind">2.5점</span></label> <label for="starpoint_6"
										class="label_star" title="3"><span class="blind">3점</span></label>
									<label for="starpoint_7" class="label_star" title="3.5"><span
										class="blind">3.5점</span></label> <label for="starpoint_8"
										class="label_star" title="4"><span class="blind">4점</span></label>
									<label for="starpoint_9" class="label_star" title="4.5"><span
										class="blind">4.5점</span></label> <label for="starpoint_10"
										class="label_star" title="5"><span class="blind">5점</span></label>
									<input type="radio" name="starpoint" id="starpoint_1" value="1"
										class="star_radio" ${review.board.starpoint == 1 ? 'checked' : ''}>
									<input type="radio" name="starpoint" id="starpoint_2" value="2"
										class="star_radio" ${review.board.starpoint == 2 ? 'checked' : ''}>
									<input type="radio" name="starpoint" id="starpoint_3" value="3"
										class="star_radio" ${review.board.starpoint == 3 ? 'checked' : ''}>
									<input type="radio" name="starpoint" id="starpoint_4" value="4"
										class="star_radio" ${review.board.starpoint == 4 ? 'checked' : ''}>
									<input type="radio" name="starpoint" id="starpoint_5" value="5"
										class="star_radio" ${review.board.starpoint == 5 ? 'checked' : ''}>
									<input type="radio" name="starpoint" id="starpoint_6" value="6"
										class="star_radio" ${review.board.starpoint == 6 ? 'checked' : ''}>
									<input type="radio" name="starpoint" id="starpoint_7" value="7"
										class="star_radio" ${review.board.starpoint == 7 ? 'checked' : ''}>
									<input type="radio" name="starpoint" id="starpoint_8" value="8"
										class="star_radio" ${review.board.starpoint == 8 ? 'checked' : ''}>
									<input type="radio" name="starpoint" id="starpoint_9" value="9"
										class="star_radio" ${review.board.starpoint == 9 ? 'checked' : ''}>
									<input type="radio" name="starpoint" id="starpoint_10"
										value="10" class="star_radio"
										${review.board.starpoint == 10 ? 'checked' : ''}> <span
										class="starpoint_bg"></span>
								</div>
							</div></td>
					</tr>
					<tr>
						<th>이미지 첨부</th>
						<td>
						<input type="file" name="main_image" id="f_main_image" />
							<div id="d_file">
								<c:forEach var="image" items="${review.imageList}">
									<img src="${contextPath}/images/${review.boardNO}/${image.fileName}" alt="image" />
								</c:forEach>
							</div></td>
					</tr>

				</table>
				<div style="text-align: center;">
					<input type="submit" value="글 수정" class="favorite styled"
						style="margin-top: 20px;" />
				</div>

			</form>
		</div>
	</div>
</body>
</html>