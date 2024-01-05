CREATE TYPE competence AS ENUM ('Trainee','Junior','Middle','Senior');

CREATE TABLE if not exists worker
(
    id INT PRIMARY KEY,
    name VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2),
    birthday DATE CHECK (birthday>= '1900-01-01'),
    level competence NOT NULL,
    salary INT NOT NULL CHECK (salary>= 100 AND salary <=100000)
);

CREATE TABLE client
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2)
);

CREATE TABLE project
(
    id INT PRIMARY KEY,
    client_id BIGINT,
    start_date DATE,
    finish_date DATE
);

CREATE TABLE project_worker
(
    project_id BIGINT NOT NULL,
    worker_id BIGINT NOT NULL,
    PRIMARY KEY (project_id, worker_id),
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (worker_id) REFERENCES worker(id)
);

ALTER TABLE project ADD CONSTRAINT client_id_fk FOREIGN  KEY(client_id) REFERENCES client(id);