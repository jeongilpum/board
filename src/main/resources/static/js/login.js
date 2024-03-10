



// 각 ajax요청시 헤더에 jwt토큰 전송
// $.ajaxPrefilter(function(options, originalOptions, jqXHR) {
//     var token = localStorage.getItem("jwtToken");
//     if (token) {
//         jqXHR.setRequestHeader('Authorization', 'Bearer ' + token);
//     }
// });


$(document).ready(function() {


    $('#loginForm').submit(function(event) {
        event.preventDefault();

        var formData = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: "POST",
            url: "/api/auth/login",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function(response) {
                console.log("Login successful:", response);
                localStorage.setItem('jwt', response); // JWT 세션 스토리지에 저장
// AJAX 요청으로 대시보드 페이지 데이터 요청
                $.ajax({
                    type: "GET",
                    url: "/api/dashboard",
                    contentType: "application/json",
                    beforeSend: function(xhr) {
                        const token = localStorage.getItem('jwt');
                        if (token) {
                            xhr.setRequestHeader("Authorization", "Bearer " + token);
                        } else {
                            console.error("JWT 토큰이 없습니다!");
                        }
                    },
                    success: function(response) {
                        localStorage.setItem('jwt', response); // JWT 세션 스토리지에 저장
                        window.location.href = '/api/dashboard';
                    },
                    error: function(xhr, status, error) {
                        console.error("Access to dashboard failed:", xhr.responseText);
                    }
                });

            },
            error: function(xhr, status, error) {
                console.error("Login failed:", xhr.responseText);
            }
        });
    });
});

