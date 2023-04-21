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
    <form action="/board/update" method="post">
        <input type="hidden" name="boardNo" value="${board.boardNo}">
    <div>번호 : ${board.boardNo} 제목 : <input type="text" value="${board.title}" name="title"> </div>
    <div>내용</div>
    <textarea id="content" name="content" maxlength="200"  required>${board.content}</textarea>
    조회수 : ${board.viewCount}<br>
    날짜 : ${board.regDateTime}<br>
        <input type="submit" value="수정">
        <input type="reset" value="취소">
    </form>
    <a href="/board/back"><button>뒤로가기</button></a>
    <a href="/board/list"><button>목록보기</button></a>
</body>
</html>