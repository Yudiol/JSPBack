create TABLE IF NOT EXISTS advices
(
    id       bigint  NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    advice   text    NOT NULL
);
