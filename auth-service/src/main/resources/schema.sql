-- P_USERS 테이블 생성
DROP TABLE IF EXISTS P_USERS CASCADE ;

CREATE TABLE P_USERS (
        USER_ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        USER_UUID VARCHAR(36) NOT NULL UNIQUE,
        USERNAME VARCHAR(100) NOT NULL,
        PASSWORD VARCHAR(100) NOT NULL,
        ROLE VARCHAR(50) NOT NULL,
        SLACK_EMAIL VARCHAR(100) NOT NULL,
        CREATED_AT TIMESTAMP(6)     NOT NULL,
        CREATED_BY VARCHAR(36)      NOT NULL,
        UPDATED_AT TIMESTAMP(6),
        UPDATED_BY VARCHAR(36),
        DELETED_AT TIMESTAMP(6),
        DELETED_BY VARCHAR(36),
        IS_DELETED BOOLEAN          NOT NULL,
        IS_PUBLIC  BOOLEAN          NOT NULL,
        IS_ACTIVATED BOOLEAN        NOT NULL
);

-- 인덱스 생성
CREATE INDEX IDX_USER_ID ON P_USERS (USER_ID);


-- P_COMPANIES 테이블 생성
DROP TABLE IF EXISTS P_COMPANIES;
CREATE TABLE P_COMPANIES (
        COMPANY_ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
        COMPANY_UUID VARCHAR(36) NOT NULL UNIQUE,
        COMPANY_NAME VARCHAR(100) NOT NULL,
        COMPANY_ADDRESS VARCHAR(255) NOT NULL,
        COMPANY_TYPE VARCHAR(50) NOT NULL,
        USER_ID BIGINT NOT NULL REFERENCES P_USERS(USER_ID) ON DELETE CASCADE,
        HUB_ID BIGINT NOT NULL,
        CREATED_AT TIMESTAMP(6)     NOT NULL,
        CREATED_BY VARCHAR(36)      NOT NULL,
        UPDATED_AT TIMESTAMP(6),
        UPDATED_BY VARCHAR(36),
        DELETED_AT TIMESTAMP(6),
        DELETED_BY VARCHAR(36),
        IS_DELETED BOOLEAN          NOT NULL,
        IS_PUBLIC  BOOLEAN          NOT NULL
);

-- 인덱스 생성
CREATE INDEX IDX_COMPANY_ID ON P_COMPANIES (COMPANY_ID);

