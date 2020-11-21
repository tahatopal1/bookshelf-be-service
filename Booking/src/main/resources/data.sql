INSERT INTO app_user (id,approved, auth_code, manager, password, username, name, surname)
SELECT 'aaaaaaaa-0000-1111-2222-bbbbbbbbbbbb',1,3,null,'appmaster', 'AppMaster', 'AppMaster', 'AppMaster'
WHERE NOT EXISTS (SELECT app_user.id, app_user.approved, app_user.auth_code, app_user.manager, app_user.password, app_user.username, app_user.name, app_user.surname  FROM app_user WHERE username = 'AppMaster');
