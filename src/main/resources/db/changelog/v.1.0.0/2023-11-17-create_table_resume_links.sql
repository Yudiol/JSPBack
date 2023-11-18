create TABLE IF NOT EXISTS resume_links
(
    id       bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id  VARCHAR                NOT NULL,
    name     character varying(100) NOT NULL,
    link     character varying(250) NOT NULL
);
