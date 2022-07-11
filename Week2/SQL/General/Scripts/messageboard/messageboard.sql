INSERT INTO messageboard.users
(user_name, user_pass, user_first_name, user_last_name, user_email, user_auth_cookie, user_auth_expiration, is_superadmin)
VALUES('gerardcruz', 'gerardcruz2022', 'Gerard', 'Cruz', 'gerard984@revature.net', NULL, NULL, FALSE);

TRUNCATE messageboard.users RESTART IDENTITY CASCADE;
TRUNCATE messageboard.board RESTART IDENTITY CASCADE;

SELECT COUNT(*) ct FROM messageboard.users WHERE user_name = 'gerardcruz';

SELECT *
FROM messageboard.users
WHERE user_auth_token = NULL ;

UPDATE messageboard.users SET user_name = 'Gunner' WHERE user_id = 4;

-- To be used for creating a new board. Also insert records on members and member_access.
WITH Y AS (
  INSERT INTO messageboard.board(board_name)
  VALUES ('apple')
  RETURNING board_id
), X AS (
  INSERT INTO messageboard.members(board_id, user_id)
  SELECT board_id, 1
  FROM Y
  RETURNING member_id
)
INSERT INTO messageboard.member_access(is_admin, is_moderator, is_member, member_id)
SELECT TRUE, FALSE, FALSE, member_id
FROM X;

SELECT b.board_name, b.board_id, m.user_id, ma.is_admin, ma.is_moderator, ma.is_member
FROM messageboard.board b
INNER JOIN messageboard.members m ON
b.board_id = m.board_id
INNER JOIN messageboard.member_access ma ON
m.member_id = ma.member_id
WHERE m.user_id = 2

