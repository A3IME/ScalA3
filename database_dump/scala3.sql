--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: estado; Type: TABLE; Schema: public; Owner: scala3; Tablespace: 
--

CREATE TABLE estado (
    idestado integer NOT NULL,
    idtipoestado integer,
    datainicio date NOT NULL,
    datatermino date,
    estadodescricao character varying(200)
);


ALTER TABLE public.estado OWNER TO scala3;

--
-- Name: estado_idestado_seq; Type: SEQUENCE; Schema: public; Owner: scala3
--

CREATE SEQUENCE estado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_idestado_seq OWNER TO scala3;

--
-- Name: estado_idestado_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scala3
--

ALTER SEQUENCE estado_idestado_seq OWNED BY estado.idestado;


--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: scala3; Tablespace: 
--

CREATE TABLE funcionario (
    idfunc integer NOT NULL,
    nomecompleto character varying(50) NOT NULL,
    eadmin boolean NOT NULL,
    idestado integer,
    matricula integer NOT NULL,
    email character varying(30) NOT NULL,
    telefone character(11) NOT NULL
);


ALTER TABLE public.funcionario OWNER TO scala3;

--
-- Name: funcionario_idfunc_seq; Type: SEQUENCE; Schema: public; Owner: scala3
--

CREATE SEQUENCE funcionario_idfunc_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.funcionario_idfunc_seq OWNER TO scala3;

--
-- Name: funcionario_idfunc_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scala3
--

ALTER SEQUENCE funcionario_idfunc_seq OWNED BY funcionario.idfunc;


--
-- Name: tipoestado; Type: TABLE; Schema: public; Owner: scala3; Tablespace: 
--

CREATE TABLE tipoestado (
    idtipoestado integer NOT NULL,
    nomeestado character varying(50) NOT NULL
);


ALTER TABLE public.tipoestado OWNER TO scala3;

--
-- Name: tipoestado_idtipoestado_seq; Type: SEQUENCE; Schema: public; Owner: scala3
--

CREATE SEQUENCE tipoestado_idtipoestado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipoestado_idtipoestado_seq OWNER TO scala3;

--
-- Name: tipoestado_idtipoestado_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scala3
--

ALTER SEQUENCE tipoestado_idtipoestado_seq OWNED BY tipoestado.idtipoestado;


--
-- Name: idestado; Type: DEFAULT; Schema: public; Owner: scala3
--

ALTER TABLE ONLY estado ALTER COLUMN idestado SET DEFAULT nextval('estado_idestado_seq'::regclass);


--
-- Name: idfunc; Type: DEFAULT; Schema: public; Owner: scala3
--

ALTER TABLE ONLY funcionario ALTER COLUMN idfunc SET DEFAULT nextval('funcionario_idfunc_seq'::regclass);


--
-- Name: idtipoestado; Type: DEFAULT; Schema: public; Owner: scala3
--

ALTER TABLE ONLY tipoestado ALTER COLUMN idtipoestado SET DEFAULT nextval('tipoestado_idtipoestado_seq'::regclass);


--
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: scala3
--

COPY estado (idestado, idtipoestado, datainicio, datatermino, estadodescricao) FROM stdin;
\.


--
-- Name: estado_idestado_seq; Type: SEQUENCE SET; Schema: public; Owner: scala3
--

SELECT pg_catalog.setval('estado_idestado_seq', 1, false);


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: scala3
--

COPY funcionario (idfunc, nomecompleto, eadmin, idestado, matricula, email, telefone) FROM stdin;
\.


--
-- Name: funcionario_idfunc_seq; Type: SEQUENCE SET; Schema: public; Owner: scala3
--

SELECT pg_catalog.setval('funcionario_idfunc_seq', 4, true);


--
-- Data for Name: tipoestado; Type: TABLE DATA; Schema: public; Owner: scala3
--

COPY tipoestado (idtipoestado, nomeestado) FROM stdin;
\.


--
-- Name: tipoestado_idtipoestado_seq; Type: SEQUENCE SET; Schema: public; Owner: scala3
--

SELECT pg_catalog.setval('tipoestado_idtipoestado_seq', 1, false);


--
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: scala3; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (idestado);


--
-- Name: funcionario_matricula_unique; Type: CONSTRAINT; Schema: public; Owner: scala3; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_matricula_unique UNIQUE (matricula);


--
-- Name: funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: scala3; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (idfunc);


--
-- Name: tipoestado_pkey; Type: CONSTRAINT; Schema: public; Owner: scala3; Tablespace: 
--

ALTER TABLE ONLY tipoestado
    ADD CONSTRAINT tipoestado_pkey PRIMARY KEY (idtipoestado);


--
-- Name: estado_idtipoestado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: scala3
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_idtipoestado_fkey FOREIGN KEY (idtipoestado) REFERENCES tipoestado(idtipoestado);


--
-- Name: funcionario_idestado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: scala3
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_idestado_fkey FOREIGN KEY (idestado) REFERENCES estado(idestado);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

