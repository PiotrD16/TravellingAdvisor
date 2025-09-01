--
-- PostgreSQL database dump
--

\restrict 6Sq5NlNsRA9YRRwl6HopgdUAQ0tUfZOpUyTcZ6eaPigQg7wkX7id5YSjehcK1sE

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.6

-- Started on 2025-09-01 21:52:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 16397)
-- Name: miasta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.miasta (
    id_miasta integer NOT NULL,
    miasto character varying(50) NOT NULL,
    id_panstwa integer NOT NULL
);


ALTER TABLE public.miasta OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16396)
-- Name: miasta_id_miasta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.miasta ALTER COLUMN id_miasta ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.miasta_id_miasta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 16408)
-- Name: miejsca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.miejsca (
    id_miejsca integer NOT NULL,
    nazwa_miejsca character varying(100) NOT NULL,
    opis character varying(255) NOT NULL,
    id_panstwa integer NOT NULL,
    id_miasta integer NOT NULL
);


ALTER TABLE public.miejsca OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16407)
-- Name: miejsca_id_miejsca_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.miejsca ALTER COLUMN id_miejsca ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.miejsca_id_miejsca_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 16391)
-- Name: panstwa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.panstwa (
    id_panstwa integer NOT NULL,
    panstwo character varying(50) NOT NULL
);


ALTER TABLE public.panstwa OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16390)
-- Name: panstwa_id_panstwa_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.panstwa ALTER COLUMN id_panstwa ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.panstwa_id_panstwa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4808 (class 0 OID 16397)
-- Dependencies: 220
-- Data for Name: miasta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.miasta (id_miasta, miasto, id_panstwa) FROM stdin;
1	Warszawa	1
2	Kraków	1
3	Berlin	2
4	Monachium	2
5	Paryż	3
6	Jędrzejów	1
7	Nowy York	4
8	Not specified	5
\.


--
-- TOC entry 4810 (class 0 OID 16408)
-- Dependencies: 222
-- Data for Name: miejsca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.miejsca (id_miejsca, nazwa_miejsca, opis, id_panstwa, id_miasta) FROM stdin;
1	Zamek Królewski	Historyczna rezydencja królów Polski w Warszawie.	1	1
3	Brama Brandenburska	Symbol Berlina i Niemiec.	2	3
2	Wawel	Zamek królewski na Wawelu w Krakowie. Mieszkanie wielu królów.	1	2
5	Wieża Eiffla	Najsłynniejszy symbol Paryża i Francji. Gwarantuje piękny widok	3	5
8	Statua Wolności	Najbardziej rozpoznawany symbol Nowego Yorku. Wielokrotnie niszczona w filmach.	4	7
9	Pustynia Sahara	Największa pustynia na świecie. Bez wody może być ciężko	5	8
10	Empire State Buidling	Jeden z najwyzszych budynkow w USA, znany jest między innymi w z filmów o King Kongu. Bardzo rozpoznawany na świecie	4	7
\.


--
-- TOC entry 4806 (class 0 OID 16391)
-- Dependencies: 218
-- Data for Name: panstwa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.panstwa (id_panstwa, panstwo) FROM stdin;
1	Polska
2	Niemcy
3	Francja
4	USA
5	Egipt
\.


--
-- TOC entry 4816 (class 0 OID 0)
-- Dependencies: 219
-- Name: miasta_id_miasta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.miasta_id_miasta_seq', 8, true);


--
-- TOC entry 4817 (class 0 OID 0)
-- Dependencies: 221
-- Name: miejsca_id_miejsca_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.miejsca_id_miejsca_seq', 10, true);


--
-- TOC entry 4818 (class 0 OID 0)
-- Dependencies: 217
-- Name: panstwa_id_panstwa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.panstwa_id_panstwa_seq', 5, true);


--
-- TOC entry 4654 (class 2606 OID 16401)
-- Name: miasta miasta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.miasta
    ADD CONSTRAINT miasta_pkey PRIMARY KEY (id_miasta);


--
-- TOC entry 4656 (class 2606 OID 16414)
-- Name: miejsca miejsca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.miejsca
    ADD CONSTRAINT miejsca_pkey PRIMARY KEY (id_miejsca);


--
-- TOC entry 4652 (class 2606 OID 16395)
-- Name: panstwa panstwa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.panstwa
    ADD CONSTRAINT panstwa_pkey PRIMARY KEY (id_panstwa);


--
-- TOC entry 4657 (class 2606 OID 16425)
-- Name: miasta miasta_id_panstwa_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.miasta
    ADD CONSTRAINT miasta_id_panstwa_fk FOREIGN KEY (id_panstwa) REFERENCES public.panstwa(id_panstwa);


--
-- TOC entry 4658 (class 2606 OID 16435)
-- Name: miejsca miejsca_id_miasta_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.miejsca
    ADD CONSTRAINT miejsca_id_miasta_fk FOREIGN KEY (id_miasta) REFERENCES public.miasta(id_miasta);


--
-- TOC entry 4659 (class 2606 OID 16430)
-- Name: miejsca miejsca_id_panstwa_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.miejsca
    ADD CONSTRAINT miejsca_id_panstwa_fk FOREIGN KEY (id_panstwa) REFERENCES public.panstwa(id_panstwa) ON DELETE CASCADE;


-- Completed on 2025-09-01 21:52:41

--
-- PostgreSQL database dump complete
--

\unrestrict 6Sq5NlNsRA9YRRwl6HopgdUAQ0tUfZOpUyTcZ6eaPigQg7wkX7id5YSjehcK1sE

