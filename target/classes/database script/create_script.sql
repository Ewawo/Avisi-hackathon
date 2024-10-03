-- Drop and create database
drop database if exists hackathon;
create database hackathon;
use hackathon;

-- User Table (shared for Teacher/Student)
create table User (
    userId int auto_increment not null,
    firstname varchar(20) not null,
    surname varchar(50) not null,
    email varchar(50) not null,
    wachtwoord varchar(255) not null,
    isTeacher boolean not null,
    constraint pk_user primary key (userId)
);

-- Role Table
create table Role (
    roleId int auto_increment not null,
    name varchar(50) not null,
    constraint pk_roleId primary key (roleId)
);

-- Teacher Table
create table Teacher (
    teacherId int auto_increment not null,
    userId int not null,
    constraint pk_teacher primary key (teacherId),
    constraint fk_teacherUserUserId foreign key (userId) REFERENCES User(userId)
);

-- Link Table for Teacher-Role Relationship (A teacher can have multiple roles)
create table TeacherRole (
    teacherId int not null,
    roleId int not null,
    constraint pk_teacherRole primary key (teacherId, roleId),
    constraint fk_teacherRoleTeacherId foreign key (teacherId) references Teacher(teacherId),
    constraint fk_teacherRoleRoleId foreign key (roleId) references Role(roleId)
);

-- Student Table
create table Student (
    studentId int auto_increment not null,
    userId int not null,
    constraint pk_student primary key (studentId),
    constraint fk_studentUserUserId foreign key (userId) REFERENCES User(userId)
);

-- Core Task Table
create table CoreTask (
    coreTaskId int auto_increment not null,
    name varchar(50) not null,
    description varchar(500) not null,
    constraint pk_coreTask primary key (coreTaskId)
);

-- Work Process Table (linked to Core Task)
create table WorkProcess (
    workProcessId int auto_increment not null,
    coreTaskId int not null,
    name varchar(50) not null,
    description varchar(500) not null,
    constraint pk_workProcess primary key (workProcessId),
    constraint fk_workProcessCoreTaskCoreTaskId foreign key (coreTaskId) references CoreTask(coreTaskId)
);

-- Criteria Table (linked to Work Process)
create table Criteria (
    criteriaId int auto_increment not null,
    workProcessId int not null,
    name varchar(50) not null,
    description varchar(500) not null,
    constraint pk_criteria primary key (criteriaId),
    constraint fk_criteriaWorkProcessId foreign key (workProcessId) references WorkProcess(workProcessId)
);

-- Period Table (for managing periods)
create table Period (
    periodId int auto_increment not null,
    periodNumber int not null,
    constraint pk_period primary key (periodId)
);

-- Student Period Planning Table (Linking Students to Criteria for a Specific Period)
create table StudentPeriodPlan (
    studentId int not null,
    criteriaId int not null,
    periodId int not null,
    isCompleted boolean default false,
    constraint pk_studentPeriodPlan primary key (studentId, criteriaId, periodId),
    constraint fk_studentPeriodPlanStudentId foreign key (studentId) references Student(studentId),
    constraint fk_studentPeriodPlanCriteriaId foreign key (criteriaId) references Criteria(criteriaId),
    constraint fk_studentPeriodPlanPeriodId foreign key (periodId) references Period(periodId)
);

create table Grade (
    gradeId int auto_increment not null,
    name varchar(50) not null,
    value int not null,
    constraint pk_grade primary key (gradeId)
);

-- Student Grades Table (Linking Students to Grades for Specific Criteria)
create table StudentGrade (
    studentId int not null,
    criteriaId int not null,
    gradeId int not null, -- Example: A grade with two decimal places
    constraint pk_studentGrade primary key (studentId, criteriaId),
    constraint fk_studentGradeStudentId foreign key (studentId) references Student(studentId),
    constraint fk_studentGradeCriteriaId foreign key (criteriaId) references Criteria(criteriaId),
    constraint fk_studentGradeGradeId foreign key (gradeId) references Grade(gradeId)
);

create table UserSession (
    sessionId varchar(255) not null,
    userId int not null,
    constraint pk_userSession primary key (sessionId)
);