document.addEventListener('DOMContentLoaded', function() {
    const submitBtn = document.getElementById('submitBtn');
    submitBtn.addEventListener('click', function() {
        const form = document.getElementById('editForm');
        const formData = new FormData(form);
        const boardId = this.getAttribute('data-id');
        const data = {
            title: formData.get('title'),
            content: formData.get('content')
        };

        fetch(`/api/boards/${boardId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }).then(response => {
            if (response.ok) {
                alert('게시글이 수정되었습니다.');
                window.location.href = '/api/dashboard';
            } else {
                alert('게시글 수정에 실패했습니다.');
            }
        }).catch(error => {
            console.error('Error:', error);
            alert('게시글 수정 중 오류가 발생했습니다.');
        });
    });
});
