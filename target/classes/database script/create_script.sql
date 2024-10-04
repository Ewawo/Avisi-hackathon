-- Drop and create database
drop database if exists hackathon;
create database hackathon;
use hackathon;


-- Role Table
create table Role (
    roleId int auto_increment not null,
    name varchar(50) not null,
    constraint pk_roleId primary key (roleId)
);

-- User Table (shared for Teacher/Student)
create table User (
    userId int auto_increment not null,
    firstname varchar(20) not null,
    surname varchar(50) not null,
    email varchar(50) not null,
    wachtwoord varchar(255) not null,
    isTeacher boolean not null,
    roleId int not null,
    constraint pk_user primary key (userId),
    constraint fk_UserRoleId foreign key (roleId) references Role(roleId)
);


insert into Role (name) values
                                    ('student'),
                                    ('lbc'),
                                    ('po'),
                                    ('beheerder');

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
    onvoldoende varchar(50),
    orientatieVoldoende varchar(50),
    orientatieGoed varchar(50),
    developerVoldoende varchar(50),
    developerGoed varchar(50),
    expertVoldoende varchar(50),
    expertGoed varchar(50),

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
    schatting varchar(50) not null,
    verantwoording varchar(500) not null,
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

# insert into CoreTask values (1,'coretask','description');
# insert into WorkProcess values(1,1,'Workproces','description');
# insert into Criteria values(1,1,'Criteria',"description","Onvoldoende","orientatievoldoende","orientatiegoed","developerVoldoende","developerGoed","expertVoldoende","expertGoed")

insert into CoreTask values (1,"coretask","description");
insert into WorkProcess values(1,1,"Workproces","description");
insert into Criteria values(1,1,"Criteria","description","Onvoldoende","orientatievoldoende","orientatiegoed","developerVoldoende","developerGoed","expertVoldoende","expertGoed")


INSERT INTO CoreTask VALUES (2, 'coretask2', 'description of coretask2');
INSERT INTO CoreTask VALUES (3, 'coretask3', 'description of coretask3');
INSERT INTO CoreTask VALUES (4, 'coretask4', 'description of coretask4');
INSERT INTO CoreTask VALUES (5, 'coretask5', 'description of coretask5');
INSERT INTO WorkProcess VALUES (2, 2, 'Workprocess2', 'description of workprocess2');
INSERT INTO WorkProcess VALUES (3, 3, 'Workprocess3', 'description of workprocess3');
INSERT INTO WorkProcess VALUES (4, 4, 'Workprocess4', 'description of workprocess4');
INSERT INTO WorkProcess VALUES (5, 5, 'Workprocess5', 'description of workprocess5');
INSERT INTO Criteria VALUES (2, 2, 'Criteria2', 'description of criteria2', "Onvoldoende", "orientatievoldoende", "orientatiegoed", "developerVoldoende", "developerGoed", "expertVoldoende", "expertGoed");
INSERT INTO Criteria VALUES (3, 3, 'Criteria3', 'description of criteria3', "Onvoldoende", "orientatievoldoende", "orientatiegoed", "developerVoldoende", "developerGoed", "expertVoldoende", "expertGoed");
INSERT INTO Criteria VALUES (4, 4, 'Criteria4', 'description of criteria4', "Onvoldoende", "orientatievoldoende", "orientatiegoed", "developerVoldoende", "developerGoed", "expertVoldoende", "expertGoed");
INSERT INTO Criteria VALUES (5, 5, 'Criteria5', 'description of criteria5', "Onvoldoende", "orientatievoldoende", "orientatiegoed", "developerVoldoende", "developerGoed", "expertVoldoende", "expertGoed");

select * from UserSession;

SELECT r.name FROM UserSession s join User u on s.userId = u.userId join Role r on u.roleId = r.roleId WHERE s.sessionId = '8b3e83e9-8db3-4608-b378-9b06424d86f2'