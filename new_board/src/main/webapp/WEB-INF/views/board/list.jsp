<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>글 목록</title>
</head>
<body>

<div class="container mt-5">
    <h2>글 목록</h2>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">글번호</th>
            <th scope="col">글쓴이</th>
            <th scope="col">제목</th>
            <th scope="col">내용</th>
            <th scope="col">첨부파일</th>
            <th scope="col">작성일</th>
            <th scope="col">조회수</th>
        </tr>
        </thead>
        <tbody>
        <!-- 예시 데이터, 실제 데이터베이스 연동 필요 -->
        <tr>
            <th scope="row">1</th>
            <td>작성자1</td>
            <td>제목1</td>
            <td>내용1</td>
            <td>파일1.txt</td>
            <td>2022-01-01</td>
            <td>100</td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>작성자2</td>
            <td>제목2</td>
            <td>내용2</td>
            <td>파일2.txt</td>
            <td>2022-01-02</td>
            <td>150</td>
        </tr>
        <!-- 추가 데이터들... -->
        </tbody>
    </table>

    <!-- 글쓰기 버튼 추가 -->
    <a href="write" class="btn btn-primary">글쓰기</a>
</div>

<!-- Bootstrap JS 및 Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
