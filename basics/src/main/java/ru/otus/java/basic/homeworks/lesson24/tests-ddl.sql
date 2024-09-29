CREATE TABLE tests (
    test_id SERIAL,
    name varchar(150) NOT NULL,
    CONSTRAINT tests_pkey PRIMARY KEY(test_id)
);

CREATE TABLE questions (
    question_id SERIAL,
    content text NOT NULL,
    test_id int NOT NULL,
    CONSTRAINT questions_pkey PRIMARY KEY(question_id),
    CONSTRAINT questions_test_fkey FOREIGN KEY(test_id) REFERENCES tests(test_id)
);

CREATE TABLE answers (
    answer_id SERIAL,
    content text NOT NULL,
    question_id int NOT NULL,
    CONSTRAINT answers_pkey PRIMARY KEY(answer_id),
    CONSTRAINT answers_question_fkey FOREIGN KEY(question_id) REFERENCES questions(question_id)
);

CREATE TABLE correct_answers (
    question_id int,
    answer_id int,
    CONSTRAINT questions_answers_pkey PRIMARY KEY(question_id, answer_id),
    CONSTRAINT questions_answers_question_fkey FOREIGN KEY(question_id) REFERENCES questions(question_id),
    CONSTRAINT questions_answers_answer_fkey FOREIGN KEY(answer_id) REFERENCES answers(answer_id)
);
