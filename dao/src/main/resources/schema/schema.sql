CREATE TABLE public.users
(
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    password_hash character varying(255) NOT NULL,
    salt character varying(3) NOT NULL,
    PRIMARY KEY (id)
)