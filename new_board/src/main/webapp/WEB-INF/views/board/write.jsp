<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>글쓰기</title>
</head>
<body>

<div class="container mt-5">
    <h2>글쓰기</h2>

    <!-- 글쓰기 폼 추가 -->
    <form action="WriteServlet" method="POST">
        <div class="mb-3">
            <label for="writer" class="form-label">글쓴이</label>
            <input type="text" class="form-control" id="writer" name="writer" required>
        </div>
        <div class="mb-3">
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">내용</label>
            <textarea class="form-control" id="content" name="content" rows="4" required></textarea>
        </div>
        <div class="mb-3">
            <label for="attach" class="form-label">첨부파일</label>
            <input type="text" class="form-control" id="attach" name="attach">
        </div>
        <button type="submit" class="btn btn-primary">글쓰기 완료</button>
    </form>

    <!-- 돌아가기 버튼 -->
    <a href="list.jsp" class="btn btn-secondary mt-3">글 목록으로 돌아가기(수정중)</a>
</div>

<!-- Bootstrap JS 및 Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
