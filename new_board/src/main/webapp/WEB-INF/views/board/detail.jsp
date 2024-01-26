<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>글 상세 보기</title>
</head>
<body>

<div class="container mt-5">
    <h2>글 상세 보기</h2>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">${data.title}</h5>
            <p class="card-text">작성자: ${data.writer}</p>
            <p class="card-text">내용: ${data.content}</p>
            <p class="card-text">첨부 파일: ${data.attach}</p>
            <p class="card-text">작성일: ${data.reg_date}</p>
            <p class="card-text">조회수: ${data.hit}</p>
        </div>
    </div>

    <!-- 목록으로 돌아가기 버튼과 수정 버튼 추가 -->
    <a href="list" class="btn btn-secondary mt-3">목록으로 돌아가기</a>
    <a href="edit?id=${data.id}" class="btn btn-warning mt-3">수정</a>
    <!-- 삭제 버튼 추가 -->
    <button type="button" class="btn btn-danger mt-3" onclick="confirmDelete()">삭제</button>
</div>

<!-- Bootstrap JS 및 Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- JavaScript 함수 정의 -->
<script>
    function confirmDelete() {
        // 확인 팝업
        if (confirm("정말로 삭제하시겠습니까?")) {
            // 삭제를 확인하면 서버로 삭제 요청을 보낼 수 있는 로직을 추가
            // 예를 들어, AJAX를 사용하여 비동기적으로 삭제 요청을 서버로 보낼 수 있음
            // 이 예제에서는 삭제를 확인하면 단순히 "delete" 페이지로 이동하는 예시
            window.location.href = "delete?id=${data.id}";
        }
    }
</script>

</body>
</html>
