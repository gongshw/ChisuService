CREATE TABLE recipe (
  id                INT IDENTITY NOT NULL,
  creator_name      VARCHAR(32)  NOT NULL,
  creator_email     VARCHAR(32)  NOT NULL,
  target_name       VARCHAR(32)  NOT NULL,
  target_email      VARCHAR(32)  NOT NULL,
  create_time       DATETIME     NOT NULL,
  delay_strategy_id INT          NOT NULL
);
INSERT INTO recipe (creator_name, creator_email, target_name, target_email, create_time, delay_strategy_id)
VALUES ('龚世伟', 'gongshw1992@gmail.com', '陈婕妤', '457963877@qq.com', '2015-10-30 00:0:00', 0);


CREATE TABLE delay_email (
  id             INT IDENTITY  NOT NULL,
  recipe_id      INT           NOT NULL,
  receive_time   DATETIME      NOT NULL,
  plan_send_time DATETIME      NOT NULL,
  real_send_time DATETIME,
  subject        VARCHAR(128)  NOT NULL,
  content        VARCHAR(4096) NOT NULL
);
