<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>글 추가</title>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<style>
#field {
	width: 50%;
	font-size: 20px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 5%;
	background-color: white;
	font-family: 'Times New Roman', Times, serif;
}

#title {
	width: 100%;
}

#content {
	width: 100%;
	heigt: 1000px;
	resize: none;
}

a.button {
	display: block;
	position: absolute;
	right: 530px;
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
		document.querySelector('form').addEventListener('submit', function(e) {
			if (document.getElementById('title').value == '' || document.getElementById('content').value == '') {
				e.preventDefault()//이름 미입력을 방지
				alert('제목과 내용을 모두 입력하세요')
			}
		});
	}
</script>
</head>
<body>
	<form action="/notice/writeComplete" method="POST">
		<table class=table id=field>
			<thead>
				<tr>
					<th colspan="2">공지사항 등록</th>
				</tr>
			</thead>
			<tr>
				<td width=100>번호</td>
				<td>신규(insert)</td>
			</tr>
			<tr>
				<td width=100>제목</td>
				<td width=800><input type=text class=form-control placeholder=title aria-label=Username id=title name=title maxlength=100 /></td>
			</tr>
			<tr>
				<td width=100>내용</td>
				<td width=800><textarea id=content name=content></textarea></td>
			</tr>
		</table>
		<p>
			<a href="/" title="Button push blue/green" class="button btnPush btnBlueGreen">취소</a>
		</p>
		<input type=submit id=submit title="Button push blue/green" class="button btnPush btnBlueGreen" value="등록">
	</form>
</body>
</html>