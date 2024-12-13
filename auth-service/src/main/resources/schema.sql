-- P_USERS 테이블 생성
DROP TABLE IF EXISTS p_users;

CREATE TABLE p_users (
        user_id BIGINT PRIMARY KEY,
        user_uuid VARCHAR(36) NOT NULL UNIQUE,
        username VARCHAR(100) NOT NULL,
        password VARCHAR(100) NOT NULL,
        role VARCHAR(50) NOT NULL,
        slack_email VARCHAR(100) NOT NULL,
        is_deleted BOOLEAN DEFAULT FALSE
);

-- 인덱스 생성
CREATE INDEX idx_user_id ON p_users (user_id);


-- P_COMPANIES 테이블 생성
DROP TABLE if EXISTS p_companies;
CREATE TABLE p_companies (
        company_id BIGINT PRIMARY KEY,
        company_uuid VARCHAR(36) NOT NULL UNIQUE,
        company_name VARCHAR(100) NOT NULL,
        company_address VARCHAR(255) NOT NULL,
        company_type VARCHAR(50) NOT NULL,
        user_id BIGINT NOT NULL REFERENCES p_users(user_id) ON DELETE CASCADE,
        hub_id BIGINT NOT NULL,
        is_deleted BOOLEAN DEFAULT FALSE
);

-- 인덱스 생성
CREATE INDEX idx_company_id ON p_companies (company_id);

