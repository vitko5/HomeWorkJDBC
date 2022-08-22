
CREATE TABLE books (
    id uuid PRIMARY KEY,
    bookName text NOT NULL,
    bookSize int NOT NULL,
    bookISBN varchar(20) NOT NULL UNIQUE,
    bookAuthor text NOT NULL,
    bookPublication date NOT NULL
);

INSERT INTO books (id,bookName,bookSize,bookISBN,bookAuthor,bookPublication)
    VALUES
        ('d6e4eb0f-6544-4ce7-90ad-adf79787d0cb','Core Java Volume I – Fundamentals',889,'0135166306','Cay Horstmann','27.08.2018'),
        ('117fa19f-5b14-461e-849c-e4546b21e891','Effective Java',412,'0134685997','Joshua Bloch','27.11.2017');

CREATE TABLE student (
    id uuid PRIMARY KEY,
    firstName text NOT NULL,
    lastName text NOT NULL,
    dateOfBirth date NOT NULL,
    book_id uuid,
    course_id uuid
);

CREATE TABLE course (
    id uuid PRIMARY KEY,
    courseName text NOT NULL,
    courseDuration text NOT NULL
);

