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
-- Name: cars; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE cars (
    id integer NOT NULL,
    name character varying,
    rentaldays integer,
    price integer,
    userid integer
);


ALTER TABLE cars OWNER TO "Guest";

--
-- Name: cars_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE cars_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cars_id_seq OWNER TO "Guest";

--
-- Name: cars_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE cars_id_seq OWNED BY cars.id;


--
-- Name: flights; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE flights (
    id integer NOT NULL,
    startdate character varying,
    enddate character varying,
    price integer,
    groupsize integer,
    userid integer,
    startlocation character varying,
    endlocation character varying,
    carrier character varying
);


ALTER TABLE flights OWNER TO "Guest";

--
-- Name: flights_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE flights_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE flights_id_seq OWNER TO "Guest";

--
-- Name: flights_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE flights_id_seq OWNED BY flights.id;


--
-- Name: hotels; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE hotels (
    id integer NOT NULL,
    name character varying,
    roomsbooked integer,
    duration integer,
    price integer,
    userid integer
);


ALTER TABLE hotels OWNER TO "Guest";

--
-- Name: hotels_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE hotels_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hotels_id_seq OWNER TO "Guest";

--
-- Name: hotels_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE hotels_id_seq OWNED BY hotels.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    name character varying,
    email character varying,
    age integer,
    password character varying
);


ALTER TABLE users OWNER TO "Guest";

--
-- Name: users_booking; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE users_booking (
    id integer NOT NULL,
    flightid integer,
    hotelid integer,
    carid integer
);


ALTER TABLE users_booking OWNER TO "Guest";

--
-- Name: users_booking_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE users_booking_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_booking_id_seq OWNER TO "Guest";

--
-- Name: users_booking_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE users_booking_id_seq OWNED BY users_booking.id;


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO "Guest";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cars ALTER COLUMN id SET DEFAULT nextval('cars_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY flights ALTER COLUMN id SET DEFAULT nextval('flights_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY hotels ALTER COLUMN id SET DEFAULT nextval('hotels_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY users_booking ALTER COLUMN id SET DEFAULT nextval('users_booking_id_seq'::regclass);


--
-- Data for Name: cars; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cars (id, name, rentaldays, price, userid) FROM stdin;
7	Bike	2	12	12
\.


--
-- Name: cars_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cars_id_seq', 7, true);


--
-- Data for Name: flights; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY flights (id, startdate, enddate, price, groupsize, userid, startlocation, endlocation, carrier) FROM stdin;
91	2016-10-10	2016-10-14	574	2	7	LAX	JFK	Sun Country Airlines
98	2016-10-10	2016-10-17	493	3	12	LAX	JFK	Sun Country Airlines
99	2016-10-10	2016-10-17	493	2	8	LAX	JFK	Sun Country Airlines
\.


--
-- Name: flights_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('flights_id_seq', 99, true);


--
-- Data for Name: hotels; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY hotels (id, name, roomsbooked, duration, price, userid) FROM stdin;
7	Best Western	3	3	143	10
8	Best Western	1	3	143	11
9	Motel 6	1	3	76	12
\.


--
-- Name: hotels_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('hotels_id_seq', 9, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users (id, name, email, age, password) FROM stdin;
7	Andrew	andrew@andrew.com	28	password
8	1	1	1	1
9	Andrew Fisher	andrewF@andrew.com	25	1234
10	2	2	2	2
11	1	Alex	1	pass
12	3	3	3	3
\.


--
-- Data for Name: users_booking; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY users_booking (id, flightid, hotelid, carid) FROM stdin;
\.


--
-- Name: users_booking_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_booking_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('users_id_seq', 12, true);


--
-- Name: cars_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);


--
-- Name: flights_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY flights
    ADD CONSTRAINT flights_pkey PRIMARY KEY (id);


--
-- Name: hotels_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (id);


--
-- Name: users_booking_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY users_booking
    ADD CONSTRAINT users_booking_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

