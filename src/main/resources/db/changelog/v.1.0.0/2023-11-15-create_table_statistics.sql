create TABLE IF NOT EXISTS statistics
(
    id                   bigint  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id              VARCHAR  NOT NULL,
    company_id           bigint  references responses(id) on delete cascade,
    hr_interview         date,
    tests                 date,
    tech_interview       date,
    case_interview       date,
    manager_interview    date,
    offer                int
);
