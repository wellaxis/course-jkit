-- table
CREATE TABLE PLANET_SYSTEM (
    ID                   BIGINT NOT NULL,
    NAME                 VARCHAR(100) NOT NULL,
    AGE                  BIGINT,
    STARS                INT,
    LOCATION             VARCHAR(200),
    NOTE                 TEXT
);
-- sequence
CREATE SEQUENCE PLANET_SYSTEM_SEQ START WITH 1001 INCREMENT BY 1;
-- index
ALTER TABLE PLANET_SYSTEM ADD CONSTRAINT PKPS_ID PRIMARY KEY (ID);
-- comment
COMMENT ON TABLE PLANET_SYSTEM IS 'Entity representing planet system.';
COMMENT ON COLUMN PLANET_SYSTEM.ID IS 'Planet system identifier. Primary Key.';
COMMENT ON COLUMN PLANET_SYSTEM.NAME IS 'Name of the planet system.';
COMMENT ON COLUMN PLANET_SYSTEM.AGE IS 'Age of the planet system.';
COMMENT ON COLUMN PLANET_SYSTEM.STARS IS 'Number of stars into the planet system.';
COMMENT ON COLUMN PLANET_SYSTEM.LOCATION IS 'Location of the planet system.';
COMMENT ON COLUMN PLANET_SYSTEM.NOTE IS 'Note of the planet system.';
