<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body{
        width: 50%;
        margin: 0 auto;
        top: 20%;
        border: 1px solid #000;
    }

</style>
</head>
<body>
    <div>번호 : ${board.boardNo} 제목 : ${board.title} </div>
    <div>내용</div>
    <p>${board.content}</p>
    조회수 : ${board.viewCount}<br>
    날짜 : ${board.regDateTime}<br>

    <a href="/board/back"><button>뒤로가기</button></a>
    <a href="/board/update?boardNo=${board.boardNo}"><button>수정하기</button></a>
</body>
</html>