-- 서버 실행시 h2 초기값

-- User table 초기화
INSERT INTO app_user (name, email, password, role) VALUES ('User1', 'user1@example.com', '$2b$12$oDzgMvMoKqoxRWaW7HrnJOxwMt/LQf1MSEpzmg6PjJIpyRb4kYQFC', 'USER');
INSERT INTO app_user (name, email, password, role) VALUES ('Admin', 'admin@example.com', '$2b$12$oDzgMvMoKqoxRWaW7HrnJOxwMt/LQf1MSEpzmg6PjJIpyRb4kYQFC', 'ADMIN');

-- Board table 초기화
INSERT INTO board (title, content) VALUES ('Title 1', 'Content of board 1');
INSERT INTO board (title, content) VALUES ('Title 2', 'Content of board 2');
