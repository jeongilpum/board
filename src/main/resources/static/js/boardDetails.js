document.addEventListener('DOMContentLoaded', function() {
    // "삭제하기" 버튼에 대한 이벤트 리스너
    const deleteBtn = document.getElementById('deleteBtn');
    if (deleteBtn) {
        deleteBtn.addEventListener('click', function() {
            const boardId = this.getAttribute('data-id'); // HTML에서 data-id 속성을 통해 설정된 게시글 ID를 가져옴

            // fetch API를 사용하여 삭제 요청을 보냄
            fetch(`/api/boards/${boardId}`, {
                method: 'DELETE'
            }).then(response => {
                if (response.ok) {
                    alert('게시글이 삭제되었습니다.');
                    window.location.href = '/api/dashboard';
                } else {
                    alert('게시글 삭제에 실패했습니다.');
                }
            }).catch(error => console.error('Error:', error));
        });
    }

    // "수정하기" 버튼에 대한 이벤트 리스너
    const editBtn = document.getElementById('editBtn');
    if (editBtn) {
        editBtn.addEventListener('click', function() {
            const boardId = this.dataset.id; // HTML에서 data-id 속성을 통해 설정된 게시글 ID를 가져옴
            window.location.href = `/api/dashboard/edit/${boardId}`; // 수정 페이지로 이동
        });
    }
});
