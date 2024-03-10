$(document).ready(function() {
    $('#writeForm').submit(function(e) {
        e.preventDefault(); // 폼의 기본 제출을 방지
        var postData = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        $.ajax({
            url: '/api/boards', // 게시글을 저장하는 서버의 URL
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(postData),
            dataType: 'json',
            // beforeSend: function(xhr) {
            //     // 로컬 스토리지에서 JWT 토큰을 가져와서 요청 헤더에 추가
            //     const token = localStorage.getItem('jwt');
            //     if (token) {
            //         xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            //     }
            // },
            success: function(response) {
                alert('게시글이 성공적으로 등록되었습니다.');
                window.location.href = '/api/dashboard'; // 게시판 목록 페이지로 리다이렉션
            },
            error: function() {
                alert('게시글 등록에 실패했습니다.');
            }
        });
    });
});