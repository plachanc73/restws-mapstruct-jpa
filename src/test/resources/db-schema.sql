
    drop table CLASSE if exists;

    drop table USER if exists;

    drop table USER_ROLE if exists;

    drop sequence if exists CLASSE_SEQ;
create sequence CLASSE_SEQ start with 1 increment by 50;

    create table CLASSE (
       ID_CLASSE bigint not null,
        CODE varchar(5) not null,
        LIBELLE varchar(100) not null,
        TYPE_CLASSE varchar(6) not null,
        primary key (ID_CLASSE)
    );

    create table USER (
       ID_USER bigint not null,
        CODE varchar(8) not null,
        EMAIL varchar(65) not null,
        LANGUE varchar(2) not null,
        LASTNAME varchar(60) not null,
        FIRSTNAME varchar(60) not null,
        primary key (ID_USER)
    );

    create table USER_ROLE (
       ID_USER_ROLE bigint not null,
        DATE_DEBUT timestamp,
        DATE_FIN timestamp,
        TYPE_ROLE varchar(255) not null,
        ID_USER bigint not null,
        primary key (ID_USER_ROLE)
    );

    alter table USER_ROLE 
       add constraint FKqq0e7cla9lpndcmm9c2my1dyv 
       foreign key (ID_USER) 
       references USER;
