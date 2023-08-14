CREATE TABLE message_likes
(
    user_id INT4 NOT NULL REFERENCES usr,
    message_id INT4 NOT NULL REFERENCES message,
    PRIMARY KEY (user_id, message_id)
)