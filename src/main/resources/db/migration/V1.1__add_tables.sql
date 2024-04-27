ALTER TABLE InternshipLessons
    ADD is_open BOOLEAN NOT NULL;

CREATE TABLE Messages (
    id BIGSERIAL PRIMARY KEY,
    recipient_id BIGINT REFERENCES Users(id),
    message TEXT NOT NULL
);

CREATE TABLE InternshipApplications (
    internship_id BIGINT REFERENCES Internship(id),
    intern_id BIGINT REFERENCES Intern(id),
    PRIMARY KEY (internship_id, intern_id)
);