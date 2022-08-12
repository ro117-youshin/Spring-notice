<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>글 보기</title>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<style>
div {
	position: relative;
	text-align: center;
}

div.input-group{
	width: 50%;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
}

#commenttable {
	width: 50%;
	margin-left: auto;
	margin-right: auto;
}

#field, #commentbox{
	width: 50%;
	font-size: 15px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 2%;
	background-color: white;
	font-family: 'Times New Roman', Times, serif;
}

#buttontable{
	width: 30%;
	margin-left: auto;
	margin-right: auto;
	margin-top: 2%;
}

textarea {
	width: 500px;
	heigt: 250px;
	resize: none;
}

a.button {
	display: block;
	width: 100px;
	padding: 0;
	margin: 0px auto;
	font-weight: 600;
	text-align: center;
	line-height: 50px;
	color: #FFF;
	border-radius: 5px;
	transition: all 0.2s;
	text-decoration: none;
}

input.button {
	display: block;
	position: absolute;
	right: 410px;
	float: left;
	width: 100px;
	padding: 0;
	margin: 10px 20px 10px 0;
	font-weight: 600;
	text-align: center;
	line-height: 50px;
	color: #FFF;
	border-radius: 5px;
	transition: all 0.2s;
}

.btnBlueGreen {
	background: #00AE68;
}

.btnBlueGreen.btnPush {
	box-shadow: 0px 5px 0px 0px #007144;
}

.btnBlueGreen.btnPush:hover {
	box-shadow: 0px 0px 0px 0px #007144;
}
</style>
<script>
	window.onload = function() {
		document.querySelector('form').addEventListener('submit', function(e){
			if(document.getElementById('author').value == '' || document.getElementById('content').value == ''){
				e.preventDefault()//이름 미입력을 방지
				alert('작성자와 내용을 모두 입력하세요')} 
		});
	}
</script>
</head>
<body>

	<table class="table" id=field>
		<tr>
			<td width=100>번호</td>
			<td><input type=hidden name=noticeid value="${noticeInfo.id}" />${noticeInfo.id}</td>
		</tr>
		<tr>
			<td width=100>제목</td>
			<td>${noticeInfo.title}</td>
		</tr>
		<tr>
			<td width=100>등록 일자</td>
			<td><fmt:parseDate value="${noticeInfo.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedCreatedDateTime" type="both" />
				<fmt:formatDate value="${parsedCreatedDateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<td width=100>수정 일자</td>
			<td><fmt:parseDate value="${noticeInfo.modifiedDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedModifiedDateTime" type="both" />
				<fmt:formatDate value="${parsedModifiedDateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
		</tr>
		<tr height=200>
			<td width=100>내용</td>
			<td>${noticeInfo.content}</td>
		</tr>
	</table>
	
	<table id=buttontable>
		<tr>
			<td width=50><p><a href="/" title="Button push blue/green" class="button btnPush btnBlueGreen">목록</a></p></td>
			<td width=50><p><a href="/notice/delete?id=${noticeInfo.id}" title="Button push blue/green" class="button btnPush btnBlueGreen">삭제</a></p></td>
			<td width=50><p><a href="/notice/movetomodify?id=${noticeInfo.id}" title="Button push blue/green" class="button btnPush btnBlueGreen">수정</a></p></td>
			<td width=50><p><a href="/notice/reply?id=${noticeInfo.id}" title="Button push blue/green" class="button btnPush btnBlueGreen">답글</a></p></td>
		</tr>
	</table>
	
		<c:forEach var="comment" items="${noticeInfo.comments}">
			
			<table class=table id=commenttable >
					<tr>
						<td width=100><p align=center>작성자</p></td>
						<td><p>${comment.author}</p></td>				
					</tr>
					<tr height=100>	
						<td width=100><p align=center>내용</p></td>
						<td><p>${comment.content}</p></td>
					</tr> 
			</table>
			
			<div id=commentButton>
				<a class="btn btn-outline-success" href="/comment/modify?id=${comment.id}">댓글 수정</a>
				<a class="btn btn-outline-success" href="/comment/delete?id=${comment.id}">댓글 삭제</a>
			</div>
			
		</c:forEach>
	<form action="/comment/create" method="POST">
	<input type=hidden name=noticeid value="${noticeInfo.id}"/>
		<table id=commentbox>
			<tr>
				<td><p align=center>작성자</p></td>
				<td><input type=text class=form-control placeholder=Username aria-label=Username id=author name=author maxlength=100 /></td>
			</tr>
			<tr>
				<td><p align=center>내용</p></td>
				<td><textarea class=form-control aria-label="With textarea"id=content name=content></textarea></td>
			</tr>
		</table>
		
		<input type=submit title="Button push blue/green" class="button btnPush btnBlueGreen" value="댓글 달기" />
	</form>
	
</body>
</html>