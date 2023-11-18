create TABLE IF NOT EXISTS uploaded_resume
(
    id       bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id  VARCHAR                NOT NULL,
    bytes     bytea                 NOT NULL
);
