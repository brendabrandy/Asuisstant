--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

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
-- Name: activity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE activity (
    employee character varying(10),
    content character varying(200)
);


ALTER TABLE activity OWNER TO postgres;

--
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE employee (
    name character varying(10),
    password character varying(10)
);


ALTER TABLE employee OWNER TO postgres;

--
-- Name: log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE log (
    id integer NOT NULL,
    employee character varying(10),
    client character varying(10),
    rep character varying(10),
    datetime character varying(19),
    done boolean,
    ticker_sentiment character varying(100),
    notes character varying(200)
);


ALTER TABLE log OWNER TO postgres;

--
-- Name: ticks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ticks (
    ticker character varying(4)
);


ALTER TABLE ticks OWNER TO postgres;

--
-- Data for Name: activity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY activity (employee, content) FROM stdin;
Shalin	Apple sucks!
Yash	I like turtles
Yash	Everyone
Yash	The meeting with John went well!
Brenda	We just lost 1000000 dollars
Yash	Amazon is awesome!!!!!!!!!!
Yash	Facebook sucks!
Brenda	Brenda js heree
\.


--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY employee (name, password) FROM stdin;
Yash	123
Brenda	123456
Shalin	123456789
\.


--
-- Data for Name: log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY log (id, employee, client, rep, datetime, done, ticker_sentiment, notes) FROM stdin;
2	Yash	Google	Dave	2016-02-14 13:15:23	f		
3	Yash	Suisse	Mike	2016-11-14 04:00:23	f		
5	Brenda	Delta	Sandeep	2013-11-12 08:00:23	f		
6	Brenda	Chase	Hilary	2016-08-10 09:00:23	f		
7	Brenda	Microsoft	Cory	2020-01-14 11:00:23	f		
4	Yash	Amazon	Susan	2015-01-14 08:00:15	t	DIS,5;	I met with Susan at Amazon to talk about @DIS it went well we made good progress and the prospects are good
1	Yash	Facebook	Jim	2016-05-14 04:15:23	t	DIS,1;	I met with Jim at Facebook to talk about @DIS it went terrible they suck their finances suck
8	Brenda	LG	Bob	2015-05-14 11:02:23	t		idifjd
\.


--
-- Data for Name: ticks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ticks (ticker) FROM stdin;
AAPL
GOOG
CS
FB
MSFT
SIRI
CSCO
NFLX
C
F
GE
PFE
INTC
NOK
AMZN
WMT
BAC
JPM
MCD
GS
\.


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

