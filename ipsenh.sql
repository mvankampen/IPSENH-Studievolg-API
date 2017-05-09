--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = ON;
SET check_function_bodies = FALSE;
SET client_min_messages = WARNING;
SET row_security = OFF;

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

SET default_with_oids = FALSE;

--
-- Name: ab_restriction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ab_restriction (
  course          CHARACTER VARYING(255),
  required_course CHARACTER VARYING(255)
);


ALTER TABLE ab_restriction
  OWNER TO postgres;

--
-- Name: course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE course (
  code        CHARACTER VARYING(255) NOT NULL,
  description CHARACTER VARYING(255),
  start_date  DATE                   NOT NULL,
  end_date    DATE                   NOT NULL
);


ALTER TABLE course
  OWNER TO postgres;

--
-- Name: course_enrollment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE course_enrollment (
  user_email      CHARACTER VARYING(255),
  course_code     CHARACTER VARYING(255),
  enrollment_date DATE DEFAULT now()
);


ALTER TABLE course_enrollment
  OWNER TO postgres;

--
-- Name: course_owner; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE course_owner (
  user_email  CHARACTER VARYING(255),
  course_code CHARACTER VARYING(255)
);


ALTER TABLE course_owner
  OWNER TO postgres;

--
-- Name: course_passed; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE course_passed (
  course     CHARACTER VARYING(255),
  user_email CHARACTER VARYING(255)
);


ALTER TABLE course_passed
  OWNER TO postgres;

--
-- Name: course_restriction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE course_restriction (
  restriction CHARACTER VARYING(40),
  course      CHARACTER VARYING(255)
);


ALTER TABLE course_restriction
  OWNER TO postgres;

--
-- Name: restriction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE restriction (
  name CHARACTER VARYING(40) NOT NULL
);


ALTER TABLE restriction
  OWNER TO postgres;

--
-- Name: user_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_account (
  email         CHARACTER VARYING(255) NOT NULL,
  first_name    CHARACTER VARYING(255) NOT NULL,
  insertion     CHARACTER VARYING(255),
  last_name     CHARACTER VARYING(255) NOT NULL,
  date_of_birth DATE                   NOT NULL,
  password      CHARACTER VARYING(255) NOT NULL,
  role          CHARACTER VARYING(255)
);


ALTER TABLE user_account
  OWNER TO postgres;

--
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_role (
  role_name CHARACTER VARYING(255) NOT NULL
);


ALTER TABLE user_role
  OWNER TO postgres;

--
-- Name: ab_restriction_course_required_course_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ab_restriction
  ADD CONSTRAINT ab_restriction_course_required_course_key UNIQUE (course, required_course);

--
-- Name: course_enrollment_user_email_course_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_enrollment
  ADD CONSTRAINT course_enrollment_user_email_course_code_key UNIQUE (user_email, course_code);

--
-- Name: course_owner_user_email_course_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_owner
  ADD CONSTRAINT course_owner_user_email_course_code_key UNIQUE (user_email, course_code);

--
-- Name: course_passed_course_user_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_passed
  ADD CONSTRAINT course_passed_course_user_email_key UNIQUE (course, user_email);

--
-- Name: course_restriction_restriction_course_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_restriction
  ADD CONSTRAINT course_restriction_restriction_course_key UNIQUE (restriction, course);

--
-- Name: pk_code; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course
  ADD CONSTRAINT pk_code PRIMARY KEY (code);

--
-- Name: pk_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account
  ADD CONSTRAINT pk_email PRIMARY KEY (email);

--
-- Name: pk_role_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_role
  ADD CONSTRAINT pk_role_name PRIMARY KEY (role_name);

--
-- Name: restriction_name; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY restriction
  ADD CONSTRAINT restriction_name PRIMARY KEY (name);

--
-- Name: fki_course; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_course
  ON ab_restriction USING BTREE (course);

--
-- Name: fki_course_name; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_course_name
  ON course_restriction USING BTREE (course);

--
-- Name: fki_required_course; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_required_course
  ON ab_restriction USING BTREE (required_course);

--
-- Name: fki_restriction; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_restriction
  ON course_restriction USING BTREE (restriction);

--
-- Name: course_enrollment_course_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_enrollment
  ADD CONSTRAINT course_enrollment_course_code_fkey FOREIGN KEY (course_code) REFERENCES course (code);

--
-- Name: course_enrollment_user_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_enrollment
  ADD CONSTRAINT course_enrollment_user_email_fkey FOREIGN KEY (user_email) REFERENCES user_account (email);

--
-- Name: course_owner_course_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_owner
  ADD CONSTRAINT course_owner_course_code_fkey FOREIGN KEY (course_code) REFERENCES course (code);

--
-- Name: course_owner_user_email_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_owner
  ADD CONSTRAINT course_owner_user_email_fkey FOREIGN KEY (user_email) REFERENCES user_account (email);

--
-- Name: fk_course; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ab_restriction
  ADD CONSTRAINT fk_course FOREIGN KEY (course) REFERENCES course (code);

--
-- Name: fk_course_code; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_passed
  ADD CONSTRAINT fk_course_code FOREIGN KEY (course) REFERENCES course (code);

--
-- Name: fk_course_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_restriction
  ADD CONSTRAINT fk_course_name FOREIGN KEY (course) REFERENCES course (code);

--
-- Name: fk_required_course; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ab_restriction
  ADD CONSTRAINT fk_required_course FOREIGN KEY (required_course) REFERENCES course (code);

--
-- Name: fk_restriction; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_restriction
  ADD CONSTRAINT fk_restriction FOREIGN KEY (restriction) REFERENCES restriction (name);

--
-- Name: fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY course_passed
  ADD CONSTRAINT fk_user FOREIGN KEY (user_email) REFERENCES user_account (email);

--
-- Name: user_account_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_account
  ADD CONSTRAINT user_account_role_fkey FOREIGN KEY (role) REFERENCES user_role (role_name);

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
