drop database if exists hackathon;
create database hackathon;

create table User (
    userId int auto_increment not null,
    firstname varchar(20) not null,
    surname varchar(50) not null,
    email varchar(50) not null,
    wachtwoord varchar(255) not null,
    gender varchar(30) not null,
    isTeacher boolean not null,
    constraint pk_user primary key (userId)
);

create table Gender (
    genderId int auto_increment not null,
    name varchar(50),
    constraint pk_primary primary key (genderId)
);

create table Teacher (
    teacherId int auto_increment not null,
    userId int not null,
    roleId int not null,
    constraint pk_teacher primary key (teacherId),
    constraint fk_teacherUserUserId foreign key (userId) REFERENCES User(userId),
    constraint fk_teacherRoleId foreign key (roleId) references Role(roleId)
);

create table Student (
    studentId int auto_increment not null,
    userId int not null,
    constraint pk_student primary key (studentId),
    constraint fk_studentUserUserId foreign key (userId) REFERENCES User(userId)
);

create table Role (
    roleId int auto_increment not null,
    name varchar(50) not null,
    constraint pk_roleId primary key (roleId)
);

create table CoreTask (
    coreTaskId int auto_increment not null,
    name varchar(50) not null,
    description varchar(500) not null,
    constraint pk_coreTask primary key (coreTaskId)
);

create table workPorcess (
    workProcessId int auto_increment not null,
    coreTaskId int not null,
    name varchar(50) not null,
    description varchar(500) not null,
    constraint pk_workProcess primary key (workProcessId),
    constraint fk_workProcessCoreTaskCoreTaskId foreign key (coreTaskId) references CoreTask(coreTaskId)
)