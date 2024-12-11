CREATE EXTENSION IF NOT EXISTS "pgcrypto";
-- Insert into Users table
INSERT INTO p_users (user_id, username, password, role, slack_email)
VALUES
    (gen_random_uuid(), 'master1', 'password1', 'MASTER', 'master1@example.com'),
    (gen_random_uuid(), 'yeon', 'yeon', 'MASTER', 'yeonjh915@gmail.com'),

    (gen_random_uuid(), 'manager1', 'password3', 'MANAGER_HUB', 'manager1@example.com'),
    (gen_random_uuid(), 'manager2', 'password4', 'MANAGER_HUB', 'manager2@example.com'),
    (gen_random_uuid(), 'manager3', 'password5', 'MANAGER_HUB', 'manager3@example.com'),
    (gen_random_uuid(), 'manager4', 'password6', 'MANAGER_HUB', 'manager4@example.com'),
    (gen_random_uuid(), 'manager5', 'password7', 'MANAGER_HUB', 'manager5@example.com'),

    (gen_random_uuid(), 'delivery1', 'password8', 'DELIVERY', 'delivery1@example.com'),
    (gen_random_uuid(), 'delivery2', 'password9', 'DELIVERY', 'delivery2@example.com'),
    (gen_random_uuid(), 'delivery3', 'password10', 'DELIVERY', 'delivery3@example.com'),
    (gen_random_uuid(), 'delivery4', 'password11', 'DELIVERY', 'delivery4@example.com'),
    (gen_random_uuid(), 'delivery5', 'password12', 'DELIVERY', 'delivery5@example.com'),
    (gen_random_uuid(), 'delivery6', 'password13', 'DELIVERY', 'delivery6@example.com'),
    (gen_random_uuid(), 'delivery7', 'password14', 'DELIVERY', 'delivery7@example.com'),
    (gen_random_uuid(), 'delivery8', 'password15', 'DELIVERY', 'delivery8@example.com'),
    (gen_random_uuid(), 'delivery9', 'password16', 'DELIVERY', 'delivery9@example.com'),
    (gen_random_uuid(), 'delivery10', 'password17', 'DELIVERY', 'delivery10@example.com'),

    (gen_random_uuid(), 'company1', 'password18', 'COMPANY', 'company1@example.com'),
    (gen_random_uuid(), 'company2', 'password19', 'COMPANY', 'company2@example.com'),
    (gen_random_uuid(), 'company3', 'password20', 'COMPANY', 'company3@example.com'),
    (gen_random_uuid(), 'company4', 'password21', 'COMPANY', 'company4@example.com'),
    (gen_random_uuid(), 'company5', 'password22', 'COMPANY', 'company5@example.com'),
    (gen_random_uuid(), 'company6', 'password23', 'COMPANY', 'company6@example.com'),
    (gen_random_uuid(), 'company7', 'password24', 'COMPANY', 'company7@example.com'),
    (gen_random_uuid(), 'company8', 'password25', 'COMPANY', 'company8@example.com'),
    (gen_random_uuid(), 'company9', 'password26', 'COMPANY', 'company9@example.com'),
    (gen_random_uuid(), 'company10', 'password27', 'COMPANY', 'company10@example.com'),
    (gen_random_uuid(), 'company11', 'password28', 'COMPANY', 'company11@example.com'),
    (gen_random_uuid(), 'company12', 'password29', 'COMPANY', 'company12@example.com'),
    (gen_random_uuid(), 'company13', 'password30', 'COMPANY', 'company13@example.com'),
    (gen_random_uuid(), 'company14', 'password31', 'COMPANY', 'company14@example.com'),
    (gen_random_uuid(), 'company15', 'password32', 'COMPANY', 'company15@example.com'),
    (gen_random_uuid(), 'company16', 'password33', 'COMPANY', 'company16@example.com'),
    (gen_random_uuid(), 'company17', 'password34', 'COMPANY', 'company17@example.com'),
    (gen_random_uuid(), 'company18', 'password35', 'COMPANY', 'company18@example.com'),
    (gen_random_uuid(), 'company19', 'password36', 'COMPANY', 'company19@example.com'),
    (gen_random_uuid(), 'company20', 'password37', 'COMPANY', 'company20@example.com');

-- Insert into p_companies
INSERT INTO p_companies (company_id, company_name, company_address, company_type, slack_email, user_id)
VALUES
    (gen_random_uuid(), 'Supply Company A', '123 Main Street', 'SUPPLY', 'supplya@example.com', (SELECT user_id FROM p_users WHERE username = 'company1')),
    (gen_random_uuid(), 'Supply Company B', '456 Oak Avenue', 'SUPPLY', 'supplyb@example.com', (SELECT user_id FROM p_users WHERE username = 'company2')),
    (gen_random_uuid(), 'Supply Company C', '789 Pine Road', 'SUPPLY', 'supplyc@example.com', (SELECT user_id FROM p_users WHERE username = 'company3')),

    (gen_random_uuid(), '스타벅스 가든파이브점', '서울 송파구 충민로 10 (문정동) 가든파이브툴', 'DEMAND', 'supplyd@example.com', (SELECT user_id FROM p_users WHERE username = 'company4')),
    (gen_random_uuid(), '스타벅스 고양원흥DT점', '경기 고양시 덕양구 서오릉로663번길 8 (원흥동)', 'DEMAND', 'supplye@example.com', (SELECT user_id FROM p_users WHERE username = 'company5')),
    (gen_random_uuid(), 'CU 백암가창점', '경기 용인시 처인구 백암면 덕평로 168', 'DEMAND', 'demanda@example.com', (SELECT user_id FROM p_users WHERE username = 'company6')),
    (gen_random_uuid(), '부산항 힐링야영장', '부산 동구 초량동 1185-1', 'DEMAND', 'demandb@example.com', (SELECT user_id FROM p_users WHERE username = 'company7')),
    (gen_random_uuid(), '국채보상운동기념도서관', '대구 중구 공평로10길 25', 'DEMAND', 'demandc@example.com', (SELECT user_id FROM p_users WHERE username = 'company8')),
    (gen_random_uuid(), '홈플러스 구월점', '인천 남동구 예술로 198', 'DEMAND', 'demandd@example.com', (SELECT user_id FROM p_users WHERE username = 'company9')),
    (gen_random_uuid(), '상무조각공원', '광주 서구 상무시민로 3 세계광엑스포주제관', 'DEMAND', 'demande@example.com', (SELECT user_id FROM p_users WHERE username = 'company10')),
    (gen_random_uuid(), '스타벅스 대전대덕로점', '대전 서구 대덕대로234번길 18 (둔산동)', 'DEMAND', 'demandf@example.com', (SELECT user_id FROM p_users WHERE username = 'company11')),
    (gen_random_uuid(), '옥동초등학교', '울산 남구 문수로 401', 'DEMAND', 'demandg@example.com', (SELECT user_id FROM p_users WHERE username = 'company12')),
    (gen_random_uuid(), '물빛광장문화공원', '세종 소담동 510-1', 'DEMAND', 'demandh@example.com', (SELECT user_id FROM p_users WHERE username = 'company13')),
    (gen_random_uuid(), '레고랜드 코리아 리조트', '강원 춘천시 하중도길 128 레고랜드 코리아 리조트', 'DEMAND', 'demandi@example.com', (SELECT user_id FROM p_users WHERE username = 'company14')),
    (gen_random_uuid(), 'CGV 청주서문', '충북 청주시 상당구 상당로81번길 63', 'DEMAND', 'demandj@example.com', (SELECT user_id FROM p_users WHERE username = 'company15')),
    (gen_random_uuid(), '용봉보건진료소', '충남 홍성군 홍북읍 용봉산3길 136', 'DEMAND', 'demandk@example.com', (SELECT user_id FROM p_users WHERE username = 'company16')),
    (gen_random_uuid(), '롯데마트 전주점', '전북 전주시 완산구 우전로 240 롯데마트 전주점', 'DEMAND', 'demandl@example.com', (SELECT user_id FROM p_users WHERE username = 'company17')),
    (gen_random_uuid(), '파리바게뜨 옥암대우점', '전남 목포시 남악1로52번길 6-3 (옥암동) 1층(옥암동)', 'DEMAND', 'demandm@example.com', (SELECT user_id FROM p_users WHERE username = 'company18')),
    (gen_random_uuid(), '동백카츠 경북 도청점', '경북 예천군 호명면 새움1로 10', 'DEMAND', 'demandn@example.com', (SELECT user_id FROM p_users WHERE username = 'company19')),
    (gen_random_uuid(), 'KR 모터스', '경남 창원시 성산구 완암로 28', 'DEMAND', 'demando@example.com', (SELECT user_id FROM p_users WHERE username = 'company20'));
