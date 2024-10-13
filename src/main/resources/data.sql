insert into aplicativos (nome, custo_mensal)
values ('YouTube Premium', 18.99),
       ('HBO Max', 24.99),
       ('Apple Music', 19.90),
       ('Paramount+', 14.90),
       ('Peloton', 40.00);

insert into clientes (nome, email)
values ('Carlos', 'carlos.silva@gmail.com'),
       ('Renata', 'renata.rodrigues@yahoo.com'),
       ('Gabriel', 'gabriel.oliveira@outlook.com'),
       ('Thiago', 'thiago.martins@gmail.com'),
       ('Ana', 'ana.souza@hotmail.com'),
       ('Luiz', 'luiz.ferreira@yahoo.com'),
       ('Bruna', 'bruna.monteiro@gmail.com'),
       ('Fernanda', 'fernanda.almeida@hotmail.com'),
       ('Rafael', 'rafael.antonio@gmail.com'),
       ('Camila', 'camila.moreira@icloud.com');

insert into assinaturas (cliente_codigo, aplicativo_codigo, inicio_vigencia, fim_vigencia)
values (1, 1, '2022-01-15', '2022-07-15'),
       (1, 2, '2022-01-15', '2022-07-15'),
       (1, 3, '2022-01-15', '2022-07-15'),
       (2, 1, '2023-02-10', '2024-08-10'),
       (2, 2, '2023-02-10', '2024-08-10'),
       (2, 3, '2023-02-10', '2024-08-10'),
       (2, 4, '2023-02-10', '2024-08-10'),
       (3, 1, '2016-06-15', '2021-12-01'),
       (3, 2, '2016-06-15', '2021-12-01'),
       (3, 3, '2016-06-15', '2021-12-01'),
       (4, 2, '2019-11-12', '2023-12-12'),
       (4, 3, '2019-11-12', '2024-01-15'),
       (4, 4, '2018-04-18', '2023-09-18');

insert into pagamentos (cod_assinatura, valor_pago, data_pagamento)
values (3, 19.90, '2016-06-15'),
       (4, 14.90, '2018-04-18'),
       (1, 18.99, '2022-01-15'),
       (2, 24.99, '2023-02-10');

insert into usuarios (usuario, senha)
values ('admin2', 'password123'),
       ('carlos', 'pass4321'),
       ('renata', 'senhaSegura456'),
       ('gabriel', 'novaSenha789'),
       ('thiago', 'senhaTop123');
