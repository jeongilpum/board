$(document).ready(function() {
    // 게시판 목록을 불러오는 함수
    function loadBoardList() {
        $.ajax({
            url: '/api/boards', // 게시판 목록을 불러오는 서버의 URL
            type: 'GET', // HTTP 요청 방식
            dataType: 'json', // 받아올 데이터의 타입
            // beforeSend: function(xhr) {
            //     // 로컬 스토리지에서 JWT 토큰을 가져와서 요청 헤더에 추가
            //     const token = localStorage.getItem('jwt');
            //     if (token) {
            //         xhr.setRequestHeader('Authorization', 'Bearer ' + token);
            //     }
            // },
            success: function(boards) {
                // 요청이 성공하면 실행되는 콜백 함수
                $('#boardList').empty(); // 기존 목록을 비움
                // 받아온 게시판 데이터로 리스트 아이템을 생성하여 페이지에 추가
                boards.forEach(function(board) {
                    $('#boardList').append(
                        // `<li class="list-group-item">
                        //     <h5>${board.title}</h5>
                        // </li>`
                        `<a href="/api/dashboard/details/${board.id}" class="list-group-item list-group-item-action">
                            <h5>${board.title}</h5>
                        </a>`
                    );
                });
            },
            error: function() {
                // 요청이 실패하면 실행되는 콜백 함수
                alert('게시판 목록을 불러오는 데 실패했습니다.');
            }
        });
    }

    // 페이지가 준비되면 게시판 목록을 불러오는 함수 호출
    loadBoardList();

    // 글쓰기 버튼 클릭 이벤트 처리
    $('#writeBtn').click(function() {
        window.location.href = '/api/dashboard/write'; // 게시글 작성 페이지로 리다이렉트
    });
});
