$(document).ready(function() {
    $('#registerForm').submit(function(event) {
        event.preventDefault();

        var formData = {
            name: $('#name').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            role: $('#role').val() // 역할 추가
        };

        $.ajax({
            type: "POST",
            url: "/api/users/register",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function(response) {
                console.log("Registration successful:", response);
                window.location = '/api/auth';
            },
            error: function(xhr, status, error) {
                console.error("Registration failed:", xhr.responseText);
            }
        });
    });
});
