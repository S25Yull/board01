<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>메인페이지</title>
    <style>
        body {
            background-color: #000;
            color: #fff;
        }

        .btn-dark {
            background-color: #000;
            border-color: #000;
        }

        .btn-dark:hover {
            background-color: #333;
            border-color: #333;
        }

        .container {
            margin-top: 100px;
        }
    </style>
</head>
<body>

<div class="container text-center">
    <h1>환영합니다!</h1>
    <p class="lead">사이트의 기능을 이용하려면 회원가입 또는 로그인해주세요.</p>

    <div class="row mt-5">
        <div class="col-md-4">
            <a href="member/signup" class="btn btn-dark btn-lg">회원가입</a>
        </div>
        <div class="col-md-4">
            <a href="member/login" class="btn btn-dark btn-lg">로그인</a>
        </div>
        <div class="col-md-4">
            <a href="board/list" class="btn btn-dark btn-lg">게시판</a>
        </div>
    </div>
</div>

<!-- Bootstrap JS 및 Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
