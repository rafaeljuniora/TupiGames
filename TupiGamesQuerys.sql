create database if not exists TupiGames;
use TupiGames;

create table if not exists escola (
    escola_id bigint not null auto_increment,
    email varchar(255) not null,
    nome_escola varchar(255) not null,
    senha varchar(255) not null,
    constraint escola_pkey primary key (escola_id),
    constraint ukg3omtu1myy5gj6x6lievp0nb4 unique (email),
    constraint ukfn36yi4rvy5ht6ujd9fxu5vhv unique (nome_escola)
);

create table if not exists aluno (
    aluno_id int not null auto_increment,
    nome_aluno varchar(255),
    senha varchar(255),
    escola_id bigint not null,
    constraint aluno_pkey primary key (aluno_id),
    constraint fk6hk33lkumbcc8hsndhtmwoyl6 foreign key (escola_id) references escola(escola_id)
);

create table if not exists professor (
    professor_id int not null auto_increment,
    data_nascimento varchar(255),
    email varchar(255),
    nome_professor varchar(255),
    senha varchar(255),
    ultima_vez_ativo bigint,
    escola_id bigint not null,
    constraint professor_pkey primary key (professor_id),
    constraint fk1i6c1qt9bycqeqh796sno9on9 foreign key (escola_id) references escola(escola_id)
);

create table if not exists turma (
    turma_id bigint not null,
    nome_turma varchar(255),
    periodo varchar(255),
    qnt_alunos int,
    escola_id bigint not null,
    constraint turma_pkey primary key (turma_id),
    constraint fknk7a7al7r422ftom47mabsvm1 foreign key (escola_id) references escola(escola_id)
);

create table if not exists turma_aluno (
    turma_id bigint not null,
    aluno_id int not null,
    constraint turma_aluno_pkey primary key (turma_id, aluno_id),
    constraint fk8jlmncan0ekxtbsjfdgwcigtn foreign key (turma_id) references turma(turma_id),
    constraint fko4hb9mkklqma8p1p6dbpg790p foreign key (aluno_id) references aluno(aluno_id)
);

create table if not exists turma_professor (
    turma_id bigint not null,
    professor_id int not null,
    constraint turma_professor_pkey primary key (turma_id, professor_id),
    constraint fkgebnucfia04rcb2rv3773hlhh foreign key (turma_id) references turma(turma_id),
    constraint fklopoyd8l825id7etoq9pndf0f foreign key (professor_id) references professor(professor_id)
);

create table if not exists atividade (
    atividade_id bigint not null auto_increment,
    atividade_code bigint not null,
    criador varchar(255),
    data_criacao bigint,
    global boolean,
    nome_atividade varchar(255),
    constraint atividade_pkey primary key (atividade_id)
);

create table if not exists atividade_turma (
    atividade_id bigint not null,
    turma_id bigint not null,
    constraint fk916kh3ov8mcm2718px575s2gd foreign key (atividade_id) references atividade(atividade_id),
    constraint fkomippx438hy8fvxc41ve52t35 foreign key (turma_id) references turma(turma_id)
);

create table if not exists pergunta (
    id bigint not null auto_increment,
    enunciado boolean,
    imagem boolean,
    imagem_enunciado boolean,
    valor text,
    atividade_id bigint,
    constraint pergunta_pkey primary key (id),
    constraint fk9jh1ku8wrhimq6vrya1a7j5xl foreign key (atividade_id) references atividade(atividade_id)
);

create table if not exists alternativa (
    id bigint not null auto_increment,
    acerto boolean,
    enunciado boolean,
    imagem boolean,
    valor text,
    pergunta_id bigint,
    constraint alternativa_pkey primary key (id),
    constraint fk20h0e86jqwhplvdyju4b1tb86 foreign key (pergunta_id) references pergunta(id)
);

create table if not exists resposta (
    resposta_id bigint not null,
    acertos int,
    enviado bigint,
    pontos bigint,
    total int,
    aluno_id int not null,
    atividade_id bigint,
    constraint resposta_pkey primary key (resposta_id),
    constraint ukqidfqe1qr703b9w14axx6skqv unique (aluno_id, atividade_id),
    constraint fk4yt427axuvwrw7hsgvb403w12 foreign key (aluno_id) references aluno(aluno_id),
    constraint fksxb4uwkhi9hyp5qfh61xs635r foreign key (atividade_id) references atividade(atividade_id)
);







select * from atividade;

-- mostrar turmas com média de pontos acima da média geral
select t.turma_id, t.nome_turma, avg(r.pontos) as media_pontos, count(distinct r.aluno_id) as alunos_respondentes from turma t
join turma_aluno ta on t.turma_id = ta.turma_id
join resposta r on ta.aluno_id = r.aluno_id
group by t.turma_id, t.nome_turma
having avg(r.pontos) > (
    select avg(pontos) from resposta)
order by media_pontos desc;

-- atividades com menor taxa de acerto considerando alternativas corretas
select a.atividade_id, a.nome_atividade, dificuldade.taxa_acerto from atividade a
join (
select p.atividade_id, count(case when alt.acerto = true then 1 end) * 100.0 / count(*) as taxa_acerto from pergunta p
    join alternativa alt on p.id = alt.pergunta_id 
    group by p.atividade_id) 
    as dificuldade on a.atividade_id = dificuldade.atividade_id
order by dificuldade.taxa_acerto asc
limit 10;

-- professores que criaram atividades para mais de 2 turmas diferentes
select 
    p.professor_id,
    p.nome_professor,
    count(distinct at.turma_id) as turmas_atingidas,
    count(distinct a.atividade_id) as atividades_criadas
from professor p
join atividade a on a.criador = p.email
join atividade_turma at on a.atividade_id = at.atividade_id
where p.ultima_vez_ativo > unix_timestamp(date_sub(now(), interval 30 day)) * 1000
group by p.professor_id, p.nome_professor
having count(distinct at.turma_id) > 2
order by atividades_criadas desc;

-- retornar a dificuldade de acordo com o acerto nas atividades
delimiter $$
create function calcular_dificuldade(atividade_id_param bigint) 
returns varchar(20)
deterministic
begin
    declare taxa_acerto decimal(5,2);
    
    select count(case when alt.acerto = true then 1 end) * 100.0 / count(*) into taxa_acerto from pergunta p
    join alternativa alt on p.id = alt.pergunta_id
    where p.atividade_id = atividade_id_param;
    
    if taxa_acerto < 30 then
        return 'muito difícil';
    elseif taxa_acerto < 50 then
        return 'difícil';
    elseif taxa_acerto < 70 then
        return 'médio';
    else
        return 'fácil';
    end if;
end $$
delimiter ;
select * from atividade;
select calcular_dificuldade(2);

-- determinar o status do aluno de acordo comas atividades realizadas
drop function status_aluno;
delimiter $$
create function status_aluno(aluno_id_param int) 
returns varchar(30)
reads sql data
begin
    declare total_atividades int;
    declare atividades_realizadas int;
    
    select count(distinct a.atividade_id) into total_atividades from turma_aluno ta
    join atividade_turma at on ta.turma_id = at.turma_id
    join atividade a on at.atividade_id = a.atividade_id
    where ta.aluno_id = aluno_id_param;
    
    select count(distinct atividade_id) into atividades_realizadas from resposta
    where aluno_id = aluno_id_param;
    
    if atividades_realizadas = 0 then
        return 'inativo';
    elseif atividades_realizadas < total_atividades * 0.5 then
        return 'pouco ativo';
    elseif atividades_realizadas < total_atividades then
        return 'ativo';
    else
        return 'muito ativo';
    end if;
end $$
delimiter ;
select * from aluno;
select status_aluno(20);

-- relatorio referente as atividades de uma turma
drop procedure relatorio_turma;
delimiter $$
create procedure relatorio_turma(in turma_id_param bigint)
begin
select a.atividade_id, a.nome_atividade, calcular_dificuldade(a.atividade_id) as dificuldade, avg(r.pontos) as media_pontos from atividade a
    join atividade_turma at on a.atividade_id = at.atividade_id
    left join resposta r on a.atividade_id = r.atividade_id
    where at.turma_id = turma_id_param
    group by a.atividade_id, a.nome_atividade;
end $$
delimiter ;
select * from turma;
call relatorio_turma(102293596);

delimiter $$
create procedure obter_estatisticas_escola(in escola_id_param bigint)
begin
select escola_id_param as escola_id,
	(select count(*) from aluno where escola_id = escola_id_param) as total_alunos,
	(select count(*) from professor where escola_id = escola_id_param) as total_professores,
	(select count(distinct a.atividade_id) from atividade a
         join professor p on a.criador = p.email
         where p.escola_id = escola_id_param) as total_atividades,
        (select avg(r.pontos) from resposta r
         join aluno a on r.aluno_id = a.aluno_id 
         where a.escola_id = escola_id_param)
         as media_pontos, now() as data_consulta;
end $$
delimiter ;
select * from escola;
call obter_estatisticas_escola(41);

create view vw_ranking_alunos as
select e.escola_id, e.nome_escola, a.aluno_id, a.nome_aluno,
    count(r.resposta_id) as atividades_realizadas, sum(r.pontos) as total_pontos,
    rank() over (partition by e.escola_id order by sum(r.pontos) desc) as ranking_escola from escola e
join aluno a on e.escola_id = a.escola_id
left join resposta r on a.aluno_id = r.aluno_id
group by e.escola_id, e.nome_escola, a.aluno_id, a.nome_aluno;

create view vw_desempenho_atividades_turma as
select t.turma_id, t.nome_turma, a.atividade_id, a.nome_atividade,
    count(distinct r.aluno_id) as alunos_respondentes,
    count(r.resposta_id) as respostas,
    avg(r.pontos) as media_pontos,
    calcular_dificuldade(a.atividade_id) as nivel_dificuldade
from turma t
join atividade_turma at on t.turma_id = at.turma_id
join atividade a on at.atividade_id = a.atividade_id
left join resposta r on a.atividade_id = r.atividade_id
group by t.turma_id, t.nome_turma, a.atividade_id, a.nome_atividade;


create table if not exists log_atividades (
    log_id bigint auto_increment primary key,
    atividade_id bigint,
    criador varchar(255),
    data_criacao timestamp default current_timestamp,
    acao varchar(10)
);

delimiter $$
create trigger trg_after_atividade_insert
after insert on atividade
for each row
begin
    insert into log_atividades (atividade_id, criador, acao)
    values (new.atividade_id, new.criador, 'insert');
end $$
delimiter ;

delimiter $$
create trigger trg_after_atividade_delete
after delete on atividade
for each row
begin
    insert into log_atividades (atividade_id, criador, acao)
    values (old.atividade_id, old.criador, 'delete');
end $$
delimiter ;

-- index utilizados para otimizar as querys
create index idx_resposta_atividade on resposta(atividade_id);
create index idx_resposta_aluno on resposta(aluno_id);
create index idx_atividade_turma_atividade on atividade_turma(atividade_id);
create index idx_atividade_turma_turma on atividade_turma(turma_id);
create index idx_pergunta_atividade on pergunta(atividade_id);
create index idx_alternativa_pergunta on alternativa(pergunta_id);
create index idx_professor_email on professor(email);
create index idx_atividade_criador on atividade(criador(100));

-- consulta sem otimização 0.259s
-- consulta com otimização 0.063s
select * from resposta;

-- consulta sem otimização 0.016s
-- consulta com otimização 0.000s
select * from resposta where atividade_id = 106;

-- Consulta sem index  0.087s
-- Consulta com index 0.031s
select a.atividade_id, a.nome_atividade, calcular_dificuldade(a.atividade_id) as dificuldade 
from atividade a
where a.atividade_id = 5;

-- Consulta para aluno muito ativo sem index 0.038s
-- Consulta para aluno muito ativo com index index 0.016s
select aluno_id, nome_aluno, status_aluno(aluno_id) as status 
from aluno 
where aluno_id = 15;

-- Turma com muitas atividades sem index 0.125s
-- Turma com muitas atividades com index 0.063s
call relatorio_turma(102293596);

-- Consulta completa da view sem index 1.354s
-- Consulta completa da view com index 0.516s
select * from vw_ranking_alunos;

-- Consulta filtrada por escola sem index 0.054s
-- Consulta filtrada por escola com index 0.015s
select * from vw_ranking_alunos where escola_id = 41;

-- Escola com muitos dados sem index 0.054s
-- Escola com muitos dados com index 0.018s
call obter_estatisticas_escola(41);


-- Consulta completa da view com index 13.281s
select * from vw_desempenho_atividades_turma;

-- Consulta filtrada por turma com index 0.047s
select * from vw_desempenho_atividades_turma where turma_id = 102293596;