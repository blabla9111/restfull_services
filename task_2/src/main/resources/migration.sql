CREATE TABLE IF NOT EXISTS public.payment
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default",
    part character(1) COLLATE pg_catalog."default" NOT NULL,
    supply_date date,
    value bigint NOT NULL,
    CONSTRAINT payment_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.payment
    OWNER to root;