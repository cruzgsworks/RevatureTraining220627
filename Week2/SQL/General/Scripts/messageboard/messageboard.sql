INSERT INTO messageboard.users
(user_name, user_pass, user_first_name, user_last_name, user_email, user_auth_cookie, user_auth_expiration, is_superadmin)
VALUES('gerardcruz', 'gerardcruz2022', 'Gerard', 'Cruz', 'gerard984@revature.net', NULL, NULL, FALSE);

TRUNCATE messageboard.users RESTART IDENTITY CASCADE;
TRUNCATE messageboard.board RESTART IDENTITY CASCADE;
TRUNCATE messageboard.messages RESTART IDENTITY CASCADE;

SELECT COUNT(*) ct FROM messageboard.users WHERE user_name = 'gerardcruz';

SELECT *
FROM messageboard.users
WHERE user_auth_token = NULL ;

UPDATE messageboard.users SET user_name = 'Gunner' WHERE user_id = 4;

-- To be used for creating a new board. Also insert records on members and member_access.
WITH Z AS (
  INSERT INTO messageboard.board(board_name)
  VALUES ('apple')
  RETURNING *
), Y AS (
  INSERT INTO messageboard.members(board_id, user_id)
  SELECT board_id, 1
  FROM Z
  RETURNING member_id
), X AS (
INSERT INTO messageboard.member_access(is_admin, is_moderator, is_member, member_id)
  SELECT TRUE, FALSE, FALSE, member_id
  FROM Y
) SELECT * FROM Z
  


SELECT b.board_name, b.board_id, m.user_id, ma.is_admin, ma.is_moderator, ma.is_member
FROM messageboard.board b
INNER JOIN messageboard.members m ON
b.board_id = m.board_id
INNER JOIN messageboard.member_access ma ON
m.member_id = ma.member_id
WHERE b.board_id = 3 AND m.user_id = 2;

SELECT ma.is_admin 
FROM messageboard.board b
INNER JOIN messageboard.members m ON
b.board_id = m.board_id
INNER JOIN messageboard.member_access ma ON
m.member_id = ma.member_id
WHERE b.board_id = 3 AND m.user_id = 2;

INSERT INTO messageboard.users(user_name, user_pass, user_first_name, user_last_name, user_email, user_auth_token, user_auth_expiration, is_superadmin)
VALUES('gerardcruz', 'VxPKQEJExN4/zL18ESRwDlldOkR+Qvir', 'Gerard', 'Cruz', 'gerard984@revature.net', NULL, NULL, TRUE) RETURNING *;

DELETE FROM messageboard.users WHERE user_name = 'aceventura' RETURNING *;

DELETE FROM messageboard.board WHERE board_name = '' RETURNING *;

WITH Y AS (
  INSERT INTO messageboard.members(board_id, user_id)
  VALUES (2, 2)
  RETURNING *
), X AS (
INSERT INTO messageboard.member_access(is_admin, is_moderator, is_member, member_id)
  SELECT FALSE, FALSE, TRUE, member_id
  FROM Y
  RETURNING *
) SELECT * FROM X;

SELECT * FROM messageboard.board b WHERE lower(board_name) = 'apple';

SELECT b.board_id, b.board_name, m.user_id, u.user_name, ma.is_admin, ma.is_moderator, ma.is_member
FROM messageboard.board b
INNER JOIN messageboard.members m ON
b.board_id = m.board_id
INNER JOIN messageboard.users u ON
m.user_id = u.user_id
INNER JOIN messageboard.member_access ma ON
m.member_id = ma.member_id
WHERE lower(b.board_name) = 'apple';

SELECT * FROM messageboard.members m;

SELECT member_id FROM messageboard.members m
WHERE m.board_id = 1 AND m.user_id = 3;

UPDATE messageboard.member_access SET is_member = 'TRUE', is_moderator = 'FALSE', is_admin = 'FALSE' WHERE access_id = 5 RETURNING member_id

SELECT ma.access_id, ma.is_admin, ma.is_moderator, ma.is_member, ma.member_id 
FROM messageboard.board b
INNER JOIN messageboard.members m ON
b.board_id = m.board_id
INNER JOIN messageboard.member_access ma ON
m.member_id = ma.member_id
WHERE lower(b.board_name) = 'apple' AND m.user_id = 2;

SELECT m.message_id, m.message_timestamp, m.message_content, m.board_id, m.user_id, u.user_first_name, u.user_last_name
FROM messageboard.messages m
INNER JOIN messageboard.users u ON m.user_id = u.user_id
WHERE board_id = 1 AND lower(message_content) LIKE 'fox%dog';

SELECT ma.* FROM messageboard.messages m
LEFT JOIN messageboard.board b ON m.board_id = b.board_id 
LEFT JOIN messageboard.members m2 ON m.user_id = m2.user_id AND m.board_id = m2.board_id 
LEFT JOIN member_access ma ON m2.member_id = ma.member_id
WHERE m.message_id = 1;

DELETE
FROM messageboard.messages m
WHERE m.message_id = 4;

SELECT COUNT(*) ct FROM messageboard.users WHERE is_superadmin = True  user_name = 'maryjane'