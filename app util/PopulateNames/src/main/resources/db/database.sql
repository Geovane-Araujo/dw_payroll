create table if not exists types(
    id int primary key,
    description varchar(30)
);
INSERT INTO types(0,'ACRECIMO');
INSERT INTO types(1,'DESCONTO');

create table if not exists benefits(
    id serial primary key,
    descricao varchar(50),
    percentege numeric(18,2),
    value numeric(18,2),
    type smallint
);
alter table benefits add constraint fk_benefits_types foreign key (type) REFERENCES types(id);

create table if not exists taxes(
    id serial primary key,
    description_taxes varchar(70),
    percentege numeric(18,2),
    type smallint
);
alter table taxes add constraint fk_taxes_types foreign key (type) REFERENCES types(id);

create table person(
    id serial primary key,
    nome varchar(150)
);
create table person_salary(
    current_value numeric(18,2)
)INHERITS(person);

create table if not exists person_benefits(
    id serial primary key,
    id_person int,
    id_benefts int
);

