insert into users (id, email, name) VALUE (1, 'email@gmail.com', 'Name Surname');
insert into accounts (id, number, balance, created, user_id) VALUE (1, 111111111, 500, current_timestamp, 1);

insert into categories (id, name, type) VALUES
(1, 'salary', 'INCOME'),
(2, 'personal funds transfer', 'INCOME'),
(3, 'cashback', 'INCOME'),
(4, 'personal funds transfer', 'EXPENSE'),
(5, 'subscriptions', 'EXPENSE'),
(6, 'online purchases', 'EXPENSE');