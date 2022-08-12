<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>공지</title>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<style>

#field {
	width: 65%;
	font-size: 15px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 5%;
	background-color: white;
	font-family: 'Times New Roman', Times, serif;
}

#list {
	margin-left: auto;
	margin-right: auto;
	font-family: 'Times New Roman', Times, serif;
	font-size: 15px;
}

a {
	text-decoration: none;
}

a.button {
	display: block;
	position: absolute;
	right: 340px;
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
</head>
<body>
	<table class="table" id="field">
		<thead>
			<tr>
				<th width=50 bgcolor=lightgreen>번호</th>
				<th width=250 bgcolor=lightgreen>제목</th>
				<th width=50 bgcolor=lightgreen>조회수</th>
				<th width=100 bgcolor=lightgreen>등록일</th>
				<th width=100 bgcolor=lightgreen>수정일</th>
			</tr>
		</thead>
		<c:forEach var="list" items="${list}" varStatus="status">
			<tr>
				<th width=50>${list.id}</th>	
				<td width=250><a href="/notice/listview?id=${list.id}">${list.title}</a></td>
				<td width=50>${list.viewcnt}</td>
				<td width=100>
				<fmt:parseDate value="${list.createdDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedCreatedDateTime" type="both" />
				<fmt:formatDate value="${parsedCreatedDateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				<td width=100>
				<fmt:parseDate value="${list.modifiedDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedModifiedDateTime" type="both" />
				<fmt:formatDate value="${parsedModifiedDateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
			</tr>
		</c:forEach>
	</table>
	
	<p>
		<a href="/notice/moveToWrite" title="Button push blue/green" class="button btnPush btnBlueGreen">등록</a>
	</p>
	
	<nav aria-label="Page navigation example">
  		<ul class="pagination justify-content-center">
    		<li class="page-item">
	      		<a class="page-link" href="/notice/mainView?page=${p.ppPage-1}&size=${p.pageSize}" aria-label="Previous">
	        		<span aria-hidden="true">&laquo;</span>
	     		 </a>
    		</li>
    		<li class="page-item">
	      		<a class="page-link" href="/notice/mainView?page=${p.pPage-1}&size=${p.pageSize}" aria-label="Previous">
	        		<span aria-hidden="true">&lt;</span>
	     		 </a>
    		</li>
    		<c:forEach var="i" begin="${p.sPage}" end="${p.ePage}">
				<li class="page-item"><a class="page-link" href="/notice/mainView?page=${i-1}&size=${p.pageSize}">${i}</a></li>
			</c:forEach>
		    <li class="page-item">
	      		<a class="page-link" href="/notice/mainView?page=${p.nPage-1}&size=${p.pageSize}" aria-label="Next">
	       			<span aria-hidden="true">&gt;</span>
	     		</a>
	   		</li>
	    	<li class="page-item">
      			<a class="page-link" href="/notice/mainView?page=${p.nnPage-1}&size=${p.pageSize}" aria-label="Next">	
      			    <span aria-hidden="true">&raquo;</span>
	      		</a>
	    	</li>
	  	</ul>
	</nav>
</body>
</html>