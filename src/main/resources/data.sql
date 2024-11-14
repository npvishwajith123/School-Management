insert into AdminDatabase(name, role, contactNumber, office, email, emergencyContact) values('Emily Turner', 'Principal', '123-456-7890', 'San Fransisco - A', 'emily.turner@gmail.com', '221-425-5221');
insert into AdminDatabase(name, role, contactNumber, office, email, emergencyContact) values('James Foster', 'Vice-Principal', '321-456-7890','San Fransisco - B', 'james.foster@yahoo.com', '422-635-6565');

insert into FacultyDetails(name, department, designation, hireDate, experience) values('Dr.Susan Hill','Mathemathics', 'Professor', '2015-06-12', 5);
insert into FacultyDetails(name, department, designation, hireDate, experience) values('Dr.John Adams','Science', 'Assocciate Prof.', '2017-08-22', 7);
insert into FacultyDetails(name, department, designation, hireDate, experience) values('Dr.Mary Clark','Literature', 'Professor', '2015-04-16', 3);

insert into StaffDetails(name, position, department, hireDate) values('Mike Harris','Clerk','Administration','2010-04-01');
insert into StaffDetails(name, position, department, hireDate) values('Sara Lewis','Librarian','Library','2016-05-03');
insert into StaffDetails(name, position, department, hireDate) values('Tim Baker','Security','Security','2012-04-11');

insert into StudentDetails(name, age, address, admissionDate, facultyId, classEnrolled) values('Alice Green',15,'101 Main St','2024-01-05',1, '10A');
insert into StudentDetails(name, age, address, admissionDate, facultyId, classEnrolled) values('Bob White',16,'202 Oak St','2022-02-05',2, '10A');
insert into StudentDetails(name, age, address, admissionDate, facultyId, classEnrolled) values('Carol Black',17,'303 Pine St','2023-03-10',3, '10B');
insert into StudentDetails(name, age, address, admissionDate, facultyId, classEnrolled) values('David Grey',15,'404 Main St','2021-01-06',1, '10B');

insert into FeesManagement(studentId, amountDue, dueDate, paymentStatus) values(1, 1500, '2024-03-01', 'Paid');
insert into FeesManagement(studentId, amountDue, dueDate, paymentStatus) values(2, 1000, '2024-05-01', 'Pending');
insert into FeesManagement(studentId, amountDue, dueDate, paymentStatus) values(3, 1800, '2024-02-01', 'Paid');
insert into FeesManagement(studentId, amountDue, dueDate, paymentStatus) values(4, 2000, '2024-01-01', 'Pending');

insert into Roles(roleName) values('ROLE_ADMIN');
insert into Roles(roleName) values('ROLE_STUDENT');
insert into Roles(roleName) values('ROLE_FACULTY');

insert into Users(username, password) values('np','test123');
insert into Users(username, password) values('np1','test123');
insert into Users(username, password) values('np2','test123');

insert into USER_ROLE(userId, roleId) values(1,1);
insert into USER_ROLE(userId, roleId) values(2,2);
insert into USER_ROLE(userId, roleId) values(3,3);
