CREATE TABLE T_TEAM
(
    id               UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    name             VARCHAR(255) NOT NULL,
    product          VARCHAR(255) NOT NULL,
    created_at       TIMESTAMP DEFAULT NOW(),
    modified_at      TIMESTAMP DEFAULT NOW(),
    default_location VARCHAR(255) NOT NULL
);

CREATE TABLE T_TEAM_MEMBER
(
    id          UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    team_id     UUID,
    ctw_id      VARCHAR(255) NOT NULL UNIQUE,
    name        VARCHAR(255) NOT NULL,
    CREATED_AT  TIMESTAMP DEFAULT NOW(),
    MODIFIED_AT TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_member_team_id FOREIGN KEY (team_id) REFERENCES T_TEAM (id)
);

CREATE TABLE T_RACK
(
    id               UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    serial_number    VARCHAR(20)  NOT NULL UNIQUE,
    status           VARCHAR(255) NOT NULL,
    team_id          UUID,
    default_location VARCHAR(255) NOT NULL,
    CREATED_AT       TIMESTAMP DEFAULT NOW(),
    MODIFIED_AT      TIMESTAMP DEFAULT NOW(),
    CONSTRAINT fk_rack_team_id FOREIGN KEY (team_id) REFERENCES T_TEAM (id)
);

CREATE TABLE T_RACK_ASSET
(
    ID        UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    ASSET_TAG VARCHAR(255),
    RACK_ID   UUID NOT NULL,
    FOREIGN KEY (RACK_ID) REFERENCES T_RACK (ID)
);

CREATE TABLE T_BOOKING
(
    ID           UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    RACK_ID      UUID NOT NULL,
    CONSTRAINT fk_booking_rack_id FOREIGN KEY (RACK_ID) REFERENCES T_RACK (ID),
    REQUESTER_ID UUID NOT NULL,
    CONSTRAINT fk_booking_team_member_id FOREIGN KEY (REQUESTER_ID) REFERENCES T_TEAM_MEMBER (ID),
    BOOK_FROM    TIMESTAMP,
    BOOK_TO      TIMESTAMP,
    CREATED_AT   TIMESTAMP DEFAULT NOW(),
    MODIFIED_AT  TIMESTAMP DEFAULT NOW()
);