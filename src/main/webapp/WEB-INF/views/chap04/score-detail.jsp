<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .container{
        position: absolute;
        display: inline-block;
        padding: 15px;
        box-shadow: 1px 1px 20px orange;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%); 
    }
    .container .info{
        font-size: 2.0em;
    }
    .container .list{
        margin-top: 10px;
        font-size: 20px;
        margin-left: 20px;
    }
    .container .button{
        background: green;
        color: white;
        margin-top: 20px;
        padding: 10px;
    }

    .container .hidden{
        opacity: 0;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="info">${score.name}님 성적 정보</div>
         <div class="list">
             <div class="kor"># 국어: ${score.kor}점</div>
             <div class="eng"># 영어: ${score.eng}점</div>
             <div class="math"># 수학: ${score.math}점</div>
             <div class="total"># 총점 : ${score.total}점</div>
             <div class="average"># 평균 : ${score.average}점</div>
             <div class="grade"># 학점 : ${score.grade}</div>
             <div class="hidden">${score.stuNum}</div>
         </div>
         <button id="list-button" class="button">목록</button>
         <button id="modify-button" class="button">수정</button>
    </div>


    <script>
        const $listButton = document.getElementById('list-button');
        $listButton.onclick = e => {
            window.location.href = '/score/list';
        }

        const $modifyButton = document.getElementById('modify-button');
        $modifyButton.onclick = e => {
            window.location.href = '/score/modify?stuNum=' + document.querySelector(".hidden").innerText;
        }


        
    </script>
</body>
</html>