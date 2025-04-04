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
<link href="${contextPath}/resources/css/table.css" rel="stylesheet" />
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
</head>
<body>
	<div id="outer_wrap">
		<div id="wrap">


			<form name="eventForm" method="post"
				action="${contextPath }/board/addEvent.do"
				enctype="multipart/form-data">
				<table>
					<tr>
						<th>글 제목</th>
						<td><input type="text" name="boardTitle" size="100"
							maxlength="200" /></td>
					</tr>
					<tr>
						<th>이미지 첨부</th>
						<td><input type="file" name="detail_image"
							onchange="readURL(this);" /> <img id="preview" src="#"
							text-align=left width=250 height=400 /></td>
					</tr>
				</table>
				<div style="text-align: center;">
					<input type="submit" class="favorite styled" value="글쓰기" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>