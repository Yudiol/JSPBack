create TABLE IF NOT EXISTS uploaded_resume
(
    id       bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     character varying(100) NOT NULL,
    user_id  VARCHAR                NOT NULL,
    data     bytea                  NOT NULL
);
