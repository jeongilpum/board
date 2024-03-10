document.addEventListener('DOMContentLoaded', function() {
    // 댓글 불러오기
    function loadComments() {
        const boardId = document.getElementById('deleteBtn').getAttribute('data-id');

        fetch(`/api/boards/${boardId}/comments`)
            .then(response => response.json())
            .then(comments => {
                const commentsList = document.getElementById('commentsList');
                commentsList.innerHTML = ''; // 목록 초기화
                comments.forEach(comment => {
                    const li = document.createElement('li');
                    li.textContent = comment.content;
                    commentsList.appendChild(li);
                });
            })
            .catch(error => console.error('댓글을 불러오는 중 오류가 발생했습니다:', error));
    }

    // 댓글 작성 이벤트 처리
    const commentForm = document.getElementById('commentForm');
    commentForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const boardId = document.getElementById('deleteBtn').getAttribute('data-id');
        const content = document.getElementById('commentContent').value;

        fetch(`/api/boards/${boardId}/comments`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ content: content })
        })
            .then(response => {
                if (response.ok) {
                    loadComments(); // 댓글 목록 새로고침
                    document.getElementById('commentContent').value = ''; // 입력 필드 초기화
                } else {
                    alert('댓글 작성에 실패했습니다.');
                }
            })
            .catch(error => console.error('Error:', error));
    });

    loadComments(); // 페이지 로드 시 댓글 목록 로드
});
