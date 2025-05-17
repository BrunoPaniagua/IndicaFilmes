CREATE TABLE filme (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    categoria VARCHAR(50),
    data_que_foi_visto DATE
);