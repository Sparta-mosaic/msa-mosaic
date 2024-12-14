-- Insert into Users table
INSERT INTO p_users (user_id, user_uuid, username, password, role, slack_email, created_at, created_by, is_public, is_deleted)
VALUES
    -- MASTER Users
    (1, gen_random_uuid(), 'master1', 'password1', 'MASTER', 'master1@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (2, gen_random_uuid(), 'yeon', 'yeon', 'MASTER', 'yeonjh915@gmail.com', CURRENT_TIMESTAMP, 'master1', true, false),

    -- HUB_MANAGER Users
    (3, gen_random_uuid(), 'manager1', 'password3', 'HUB_MANAGER', 'manager1@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (4, gen_random_uuid(), 'manager2', 'password4', 'HUB_MANAGER', 'manager2@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (5, gen_random_uuid(), 'manager3', 'password5', 'HUB_MANAGER', 'manager3@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (6, gen_random_uuid(), 'manager4', 'password6', 'HUB_MANAGER', 'manager4@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (7, gen_random_uuid(), 'manager5', 'password7', 'HUB_MANAGER', 'manager5@example.com', CURRENT_TIMESTAMP, 'master1', true, false),

    -- DELIVERY Users
    (8, gen_random_uuid(), 'delivery1', 'password8', 'DELIVERY', 'delivery1@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (9, gen_random_uuid(), 'delivery2', 'password9', 'DELIVERY', 'delivery2@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (10, gen_random_uuid(), 'delivery3', 'password10', 'DELIVERY', 'delivery3@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (11, gen_random_uuid(), 'delivery4', 'password11', 'DELIVERY', 'delivery4@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (12, gen_random_uuid(), 'delivery5', 'password12', 'DELIVERY', 'delivery5@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (13, gen_random_uuid(), 'delivery6', 'password13', 'DELIVERY', 'delivery6@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (14, gen_random_uuid(), 'delivery7', 'password14', 'DELIVERY', 'delivery7@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (15, gen_random_uuid(), 'delivery8', 'password15', 'DELIVERY', 'delivery8@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (16, gen_random_uuid(), 'delivery9', 'password16', 'DELIVERY', 'delivery9@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (17, gen_random_uuid(), 'delivery10', 'password17', 'DELIVERY', 'delivery10@example.com', CURRENT_TIMESTAMP, 'master1', true, false),

    -- COMPANY Users
    (18, gen_random_uuid(), 'company1', 'password18', 'COMPANY', 'company1@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (19, gen_random_uuid(), 'company2', 'password19', 'COMPANY', 'company2@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (20, gen_random_uuid(), 'company3', 'password20', 'COMPANY', 'company3@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (21, gen_random_uuid(), 'company4', 'password21', 'COMPANY', 'company4@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (22, gen_random_uuid(), 'company5', 'password22', 'COMPANY', 'company5@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (23, gen_random_uuid(), 'company6', 'password23', 'COMPANY', 'company6@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (24, gen_random_uuid(), 'company7', 'password24', 'COMPANY', 'company7@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (25, gen_random_uuid(), 'company8', 'password25', 'COMPANY', 'company8@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (26, gen_random_uuid(), 'company9', 'password26', 'COMPANY', 'company9@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (27, gen_random_uuid(), 'company10', 'password27', 'COMPANY', 'company10@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (28, gen_random_uuid(), 'company11', 'password28', 'COMPANY', 'company11@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (29, gen_random_uuid(), 'company12', 'password29', 'COMPANY', 'company12@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (30, gen_random_uuid(), 'company13', 'password30', 'COMPANY', 'company13@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (31, gen_random_uuid(), 'company14', 'password31', 'COMPANY', 'company14@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (32, gen_random_uuid(), 'company15', 'password32', 'COMPANY', 'company15@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (33, gen_random_uuid(), 'company16', 'password33', 'COMPANY', 'company16@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (34, gen_random_uuid(), 'company17', 'password34', 'COMPANY', 'company17@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (35, gen_random_uuid(), 'company18', 'password35', 'COMPANY', 'company18@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (36, gen_random_uuid(), 'company19', 'password36', 'COMPANY', 'company19@example.com', CURRENT_TIMESTAMP, 'master1', true, false),
    (37, gen_random_uuid(), 'company20', 'password37', 'COMPANY', 'company20@example.com', CURRENT_TIMESTAMP, 'master1', true, false);



-- Insert into p_companies
INSERT INTO p_companies (company_id, company_uuid, company_name, company_address, company_type, user_id, hub_id, created_at, created_by, is_public, is_deleted)
VALUES
    -- SUPPLY Companies
    (1, gen_random_uuid(), 'Supply Company A', '123 Main Street', 'SUPPLY', (SELECT user_id FROM p_users WHERE username = 'company1'), 1, CURRENT_TIMESTAMP, 'master1', true, false),
    (2, gen_random_uuid(), 'Supply Company B', '456 Oak Avenue', 'SUPPLY', (SELECT user_id FROM p_users WHERE username = 'company2'), 2, CURRENT_TIMESTAMP, 'master1', true, false),
    (3, gen_random_uuid(), 'Supply Company C', '789 Pine Road', 'SUPPLY', (SELECT user_id FROM p_users WHERE username = 'company3'), 3, CURRENT_TIMESTAMP, 'master1', true, false),

    -- DEMAND Companies
    (4, gen_random_uuid(), '스타벅스 가든파이브점', '서울 송파구 충민로 10 (문정동) 가든파이브툴', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company4'), 1, CURRENT_TIMESTAMP, 'master1', true, false),
    (5, gen_random_uuid(), '스타벅스 고양원흥DT점', '경기 고양시 덕양구 서오릉로663번길 8 (원흥동)', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company5'), 2, CURRENT_TIMESTAMP, 'master1', true, false),
    (6, gen_random_uuid(), 'CU 백암가창점', '경기 용인시 처인구 백암면 덕평로 168', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company6'), 3, CURRENT_TIMESTAMP, 'master1', true, false),
    (7, gen_random_uuid(), '부산항 힐링야영장', '부산 동구 초량동 1185-1', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company7'), 4, CURRENT_TIMESTAMP, 'master1', true, false),
    (8, gen_random_uuid(), '국채보상운동기념도서관', '대구 중구 공평로10길 25', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company8'), 5, CURRENT_TIMESTAMP, 'master1', true, false),
    (9, gen_random_uuid(), '홈플러스 구월점', '인천 남동구 예술로 198', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company9'), 6, CURRENT_TIMESTAMP, 'master1', true, false),
    (10, gen_random_uuid(), '상무조각공원', '광주 서구 상무시민로 3 세계광엑스포주제관', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company10'), 7, CURRENT_TIMESTAMP, 'master1', true, false),
    (11, gen_random_uuid(), '스타벅스 대전대덕로점', '대전 서구 대덕대로234번길 18 (둔산동)', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company11'), 8, CURRENT_TIMESTAMP, 'master1', true, false),
    (12, gen_random_uuid(), '옥동초등학교', '울산 남구 문수로 401', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company12'), 9, CURRENT_TIMESTAMP, 'master1', true, false),
    (13, gen_random_uuid(), '물빛광장문화공원', '세종 소담동 510-1', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company13'), 10, CURRENT_TIMESTAMP, 'master1', true, false),
    (14, gen_random_uuid(), '레고랜드 코리아 리조트', '강원 춘천시 하중도길 128 레고랜드 코리아 리조트', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company14'), 11, CURRENT_TIMESTAMP, 'master1', true, false),
    (15, gen_random_uuid(), 'CGV 청주서문', '충북 청주시 상당구 상당로81번길 63', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company15'), 12, CURRENT_TIMESTAMP, 'master1', true, false),
    (16, gen_random_uuid(), '용봉보건진료소', '충남 홍성군 홍북읍 용봉산3길 136', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company16'), 13, CURRENT_TIMESTAMP, 'master1', true, false),
    (17, gen_random_uuid(), '롯데마트 전주점', '전북 전주시 완산구 우전로 240 롯데마트 전주점', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company17'), 14, CURRENT_TIMESTAMP, 'master1', true, false),
    (18, gen_random_uuid(), '파리바게뜨 옥암대우점', '전남 목포시 남악1로52번길 6-3 (옥암동) 1층(옥암동)', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company18'), 15, CURRENT_TIMESTAMP, 'master1', true, false),
    (19, gen_random_uuid(), '동백카츠 경북 도청점', '경북 예천군 호명면 새움1로 10', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company19'), 16, CURRENT_TIMESTAMP, 'master1', true, false),
    (20, gen_random_uuid(), 'KR 모터스', '경남 창원시 성산구 완암로 28', 'DEMAND', (SELECT user_id FROM p_users WHERE username = 'company20'), 17, CURRENT_TIMESTAMP, 'master1', true, false);