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
-- Name: estado; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE estado (
    idestado integer NOT NULL,
    datainicio date NOT NULL,
    datatermino date,
    estadodescricao character varying(200)
);


ALTER TABLE public.estado OWNER TO postgres;

--
-- Name: estado_idestado_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE estado_idestado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_idestado_seq OWNER TO postgres;

--
-- Name: estado_idestado_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE estado_idestado_seq OWNED BY estado.idestado;


--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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
    senha character varying(50) NOT NULL,
    classificacao integer
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- Name: funcionario_idfunc_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE funcionario_idfunc_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.funcionario_idfunc_seq OWNER TO postgres;

--
-- Name: funcionario_idfunc_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE funcionario_idfunc_seq OWNED BY funcionario.idfunc;


--
-- Name: servico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE servico (
    idservico integer NOT NULL,
    data date,
    idtiposervico integer,
    qtde integer
);


ALTER TABLE public.servico OWNER TO postgres;

--
-- Name: servico_funcionario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE servico_funcionario (
    idservicofuncionaio integer NOT NULL,
    idservico integer,
    idfunc integer
);


ALTER TABLE public.servico_funcionario OWNER TO postgres;

--
-- Name: servico_funcionario_idservicofuncionaio_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE servico_funcionario_idservicofuncionaio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.servico_funcionario_idservicofuncionaio_seq OWNER TO postgres;

--
-- Name: servico_funcionario_idservicofuncionaio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE servico_funcionario_idservicofuncionaio_seq OWNED BY servico_funcionario.idservicofuncionaio;


--
-- Name: servico_idservico_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE servico_idservico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.servico_idservico_seq OWNER TO postgres;

--
-- Name: servico_idservico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE servico_idservico_seq OWNED BY servico.idservico;


--
-- Name: tiposervico; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tiposervico (
    funcao character varying(20),
    cor character varying(20),
    idtiposervico integer NOT NULL
);


ALTER TABLE public.tiposervico OWNER TO postgres;

--
-- Name: tiposervico_idtiposervico_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tiposervico_idtiposervico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tiposervico_idtiposervico_seq OWNER TO postgres;

--
-- Name: tiposervico_idtiposervico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tiposervico_idtiposervico_seq OWNED BY tiposervico.idtiposervico;


--
-- Name: idestado; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY estado ALTER COLUMN idestado SET DEFAULT nextval('estado_idestado_seq'::regclass);


--
-- Name: idfunc; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario ALTER COLUMN idfunc SET DEFAULT nextval('funcionario_idfunc_seq'::regclass);


--
-- Name: idservico; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY servico ALTER COLUMN idservico SET DEFAULT nextval('servico_idservico_seq'::regclass);


--
-- Name: idservicofuncionaio; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY servico_funcionario ALTER COLUMN idservicofuncionaio SET DEFAULT nextval('servico_funcionario_idservicofuncionaio_seq'::regclass);


--
-- Name: idtiposervico; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tiposervico ALTER COLUMN idtiposervico SET DEFAULT nextval('tiposervico_idtiposervico_seq'::regclass);


--
-- Data for Name: estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY estado (idestado, datainicio, datatermino, estadodescricao) FROM stdin;
1	2016-04-29	2016-04-30	awesome
2	2016-04-30	2016-05-01	awesome1
3	2016-05-01	2016-06-20	a3imeeee
4	2016-05-01	2016-06-20	a3imeeee
5	2016-05-01	2020-02-01	andrade
\.


--
-- Name: estado_idestado_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('estado_idestado_seq', 5, true);


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY funcionario (idfunc, nomecompleto, eadmin, idestado, matricula, email, telefone, habilitacao, senha, classificacao) FROM stdin;
0	Super Usuário	t	1	0	@root	0          	0	toor	\N
52	new eu	f	2	13000	eu@eu	12365479   	31	TODO	3
53	eu++	f	3	13001	eu@eu+	321456789  	31	TODO	4
54	maj escouper	t	4	13002	maj@escouper	1236544    	31	TODO	5
55	asfasfa	f	\N	123	asfs@asfas	174654     	31	TODO	6
62	teste3	t	\N	1	sfsd@	123654     	0	TODO	\N
49	andrade	t	5	13065	@andrade	aaaaaaaaaaa	31	teste	2
63	funcionarioComum	f	\N	5	@comum	98764      	0	TODO	\N
64	teste bobberto	f	\N	6	@boberto	65464      	0	TODO	\N
35	teste edit	f	\N	3	y	y          	1	y	1
\.


--
-- Name: funcionario_idfunc_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('funcionario_idfunc_seq', 66, true);


--
-- Data for Name: servico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY servico (idservico, data, idtiposervico, qtde) FROM stdin;
12	2016-05-07	0	1
2	2016-05-02	1	1
4	2016-05-03	1	1
6	2016-05-04	1	1
8	2016-05-05	1	1
10	2016-05-06	1	1
7	2016-05-04	3	1
5	2016-05-03	3	1
1	2016-05-01	2	1
0	2016-05-01	0	1
3	2016-05-02	3	1
9	2016-05-05	3	1
11	2016-05-06	3	1
13	2016-05-07	2	1
\.


--
-- Data for Name: servico_funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY servico_funcionario (idservicofuncionaio, idservico, idfunc) FROM stdin;
0	0	35
1	1	49
12	12	49
13	13	35
2	2	52
3	3	53
4	4	54
5	5	55
6	6	35
7	7	49
8	8	52
9	9	53
10	10	54
11	11	55
\.


--
-- Name: servico_funcionario_idservicofuncionaio_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('servico_funcionario_idservicofuncionaio_seq', 2, true);


--
-- Name: servico_idservico_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('servico_idservico_seq', 1, false);


--
-- Data for Name: tiposervico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tiposervico (funcao, cor, idtiposervico) FROM stdin;
cb dia	vermelha	0
cb dia	preta	1
sgt dia	vermelha	2
sgt dia	preta	3
\.


--
-- Name: tiposervico_idtiposervico_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tiposervico_idtiposervico_seq', 1, false);


--
-- Name: estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (idestado);


--
-- Name: funcionario_matricula_unique; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_matricula_unique UNIQUE (matricula);


--
-- Name: funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (idfunc);


--
-- Name: id_servico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY servico
    ADD CONSTRAINT id_servico_pkey PRIMARY KEY (idservico);


--
-- Name: servico_funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY servico_funcionario
    ADD CONSTRAINT servico_funcionario_pkey PRIMARY KEY (idservicofuncionaio);


--
-- Name: tiposervico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tiposervico
    ADD CONSTRAINT tiposervico_pkey PRIMARY KEY (idtiposervico);


--
-- Name: funcionario_idestado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_idestado_fkey FOREIGN KEY (idestado) REFERENCES estado(idestado);


--
-- Name: idfuncionario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY servico_funcionario
    ADD CONSTRAINT idfuncionario_fkey FOREIGN KEY (idfunc) REFERENCES funcionario(idfunc);


--
-- Name: idservico_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY servico_funcionario
    ADD CONSTRAINT idservico_fkey FOREIGN KEY (idservico) REFERENCES servico(idservico);


--
-- Name: idtiposervico_foreign_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY servico
    ADD CONSTRAINT idtiposervico_foreign_key FOREIGN KEY (idtiposervico) REFERENCES tiposervico(idtiposervico);


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

