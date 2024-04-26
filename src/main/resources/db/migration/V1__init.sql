CREATE TABLE Internship (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    closing_date_for_registration TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE Users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE Intern (
    id BIGSERIAL PRIMARY KEY,
    user_id bigint REFERENCES Users(id) NOT NULL,
    fullname VARCHAR(150) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    telegram_id VARCHAR(50) NOT NULL,
    info TEXT NOT NULL,
    date_of_birth DATE NOT NULL,
    city VARCHAR(100) NOT NULL,
    education_status VARCHAR(100) NOT NULL,
    university VARCHAR(200) NOT NULL,
    faculty VARCHAR(200) NOT NULL,
    speciality VARCHAR(150) NOT NULL,
    course INTEGER NOT NULL
);

CREATE TABLE IntershipMembers (
    id BIGSERIAL PRIMARY KEY,
    internship_id BIGINT REFERENCES Internship(id) NOT NULL,
    member_id BIGINT REFERENCES Intern(id) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE Lessons (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    link_to_materials VARCHAR(255) NOT NULL
);

CREATE TABLE Tasks (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    link_to_repository VARCHAR(255) NOT NULL,
    lesson_id BIGINT REFERENCES Lessons(id) NOT NULL
);

CREATE TABLE IntershipMembersTasks (
    id BIGSERIAL PRIMARY KEY,
    internship_id BIGINT REFERENCES Internship(id) NOT NULL,
    member_id BIGINT REFERENCES Intern(id) NOT NULL,
    task_id BIGINT REFERENCES Tasks(id) NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE IntershipLessons (
    id BIGSERIAL PRIMARY KEY,
    internship_id BIGINT REFERENCES Internship(id) NOT NULL,
    lesson_id BIGINT REFERENCES Lessons(id) NOT NULL
);