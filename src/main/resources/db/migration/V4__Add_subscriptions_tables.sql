CREATE TABLE user_subscriptions
(
    channel_id INT4 NOT NULL REFERENCES usr,
    subscriber_id INT4 NOT NULL REFERENCES usr,
    PRIMARY KEY (channel_id, subscriber_id)
);