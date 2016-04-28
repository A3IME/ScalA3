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
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: estado; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE estado (
    idestado integer NOT NULL,
    datainicio date NOT NULL,
    datatermino date,
    estadodescricao character varying(200)
);


--
-- Name: estado_idestado_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE estado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: estado_idestado_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE estado_idestado_seq OWNED BY estado.idestado;


--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE funcionario (
    idfunc integer NOT NULL,
    nomecompleto character varying(50) NOT NULL,
    eadmin boolean NOT NULL,
    idestado integer,
    matricula integer NOT NULL,
    email character varying(30) NOT NULL,
    telefone character(11) NOT NULL,
    habilitacao integer NOT NULL,
    senha character varying(50) NOT NULL
);


--
-- Name: funcionario_idfunc_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE funcionario_idfunc_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: funcionario_idfunc_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE funcionario_idfunc_seq OWNED BY funcionario.idfunc;


--
-- Name: servico; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE servico (
    idservico integer NOT NULL,
    data date,
    idtiposervico integer,
    qtde integer
);


--
-- Name: servico_funcionario; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE servico_funcionario (
    idservicofuncionaio integer NOT NULL,
    idservico integer,
    idfuncionario integer
);


--
-- Name: servico_funcionario_idservicofuncionaio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE servico_funcionario_idservicofuncionaio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: servico_funcionario_idservicofuncionaio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE servico_funcionario_idservicofuncionaio_seq OWNED BY servico_funcionario.idservicofuncionaio;


--
-- Name: servico_idservico_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE servico_idservico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: servico_idservico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE servico_idservico_seq OWNED BY servico.idservico;


--
-- Name: tiposervico; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE tiposervico (
    funcao character varying(20),
    cor character varying(20),
    idtiposervico integer NOT NULL
);


--
-- Name: tiposervico_idtiposervico_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tiposervico_idtiposervico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: tiposervico_idtiposervico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tiposervico_idtiposervico_seq OWNED BY tiposervico.idtiposervico;


--
-- Name: idestado; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY estado ALTER COLUMN idestado SET DEFAULT nextval('estado_idestado_seq'::regclass);


--
-- Name: idfunc; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY funcionario ALTER COLUMN idfunc SET DEFAULT nextval('funcionario_idfunc_seq'::regclass);


--
-- Name: idservico; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY servico ALTER COLUMN idservico SET DEFAULT nextval('servico_idservico_seq'::regclass);


--
-- Name: idservicofuncionaio; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY servico_funcionario ALTER COLUMN idservicofuncionaio SET DEFAULT nextval('servico_funcionario_idservicofuncionaio_seq'::regclass);


--
-- Name: idtiposervico; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY tiposervico ALTER COLUMN idtiposervico SET DEFAULT nextval('tiposervico_idtiposervico_seq'::regclass);


--
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: -
--

COPY estado (idestado, datainicio, datatermino, estadodescricao) FROM stdin;
\.


--
-- Name: estado_idestado_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('estado_idestado_seq', 1, false);


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: -
--

COPY funcionario (idfunc, nomecompleto, eadmin, idestado, matricula, email, telefone, habilitacao, senha) FROM stdin;
5	a	t	\N	20	c	d          	10	e
6	a3ime	t	\N	10	a3ime@a3ime.com	66666666666	11	TODO
\.


--
-- Name: funcionario_idfunc_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('funcionario_idfunc_seq', 15, true);


--
-- Data for Name: servico; Type: TABLE DATA; Schema: public; Owner: -
--

COPY servico (idservico, data, idtiposervico, qtde) FROM stdin;
\.


--
-- Data for Name: servico_funcionario; Type: TABLE DATA; Schema: public; Owner: -
--

COPY servico_funcionario (idservicofuncionaio, idservico, idfuncionario) FROM stdin;
\.


--
-- Name: servico_funcionario_idservicofuncionaio_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('servico_funcionario_idservicofuncionaio_seq', 1, false);


--
-- Name: servico_idservico_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('servico_idservico_seq', 1, false);


--
-- Data for Name: tiposervico; Type: TABLE DATA; Schema: public; Owner: -
--

COPY tiposervico (funcao, cor, idtiposervico) FROM stdin;
\.


--
-- Name: tiposervico_idtiposervico_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('tiposervico_idtiposervico_seq', 1, false);


--
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (idestado);


--
-- Name: funcionario_matricula_unique; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_matricula_unique UNIQUE (matricula);


--
-- Name: funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (idfunc);


--
-- Name: id_servico_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY servico
    ADD CONSTRAINT id_servico_pkey PRIMARY KEY (idservico);


--
-- Name: servico_funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY servico_funcionario
    ADD CONSTRAINT servico_funcionario_pkey PRIMARY KEY (idservicofuncionaio);


--
-- Name: tiposervico_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY tiposervico
    ADD CONSTRAINT tiposervico_pkey PRIMARY KEY (idtiposervico);


--
-- Name: funcionario_idestado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_idestado_fkey FOREIGN KEY (idestado) REFERENCES estado(idestado);


--
-- Name: idfuncionario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY servico_funcionario
    ADD CONSTRAINT idfuncionario_fkey FOREIGN KEY (idfuncionario) REFERENCES funcionario(idfunc);


--
-- Name: idservico_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY servico_funcionario
    ADD CONSTRAINT idservico_fkey FOREIGN KEY (idservico) REFERENCES servico(idservico);


--
-- Name: idtiposervico_foreign_key; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY servico
    ADD CONSTRAINT idtiposervico_foreign_key FOREIGN KEY (idtiposervico) REFERENCES tiposervico(idtiposervico);


--
-- PostgreSQL database dump complete
--

