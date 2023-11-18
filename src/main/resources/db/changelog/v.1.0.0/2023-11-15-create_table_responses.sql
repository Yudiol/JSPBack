create TABLE IF NOT EXISTS responses
(
    id                bigint                   GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id           VARCHAR                  NOT NULL,
    response_date     date                     NOT NULL,
    name              character varying(200),
    vacancy           character varying,
    position          character varying(200),
    contact           character varying(50),
    status            int,
    comments          text
);
