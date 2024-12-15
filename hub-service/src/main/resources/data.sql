
INSERT INTO p_hubs (hub_uuid, manager_id, name, address, latitude, longitude, created_at, created_by, is_public, is_deleted)
VALUES
    ('e1b84264-b95b-4a53-b7b9-3fba65bd784e', 1, '서울특별시 센터', '서울특별시 송파구 송파대로 55', 37.456171545341, 126.70541564685742, CURRENT_TIMESTAMP, 'master1', true, false),
    ('a4d984c7-71bb-4eeb-b329-5ef2d39c8c63', 2, '경기 북부 센터', '경기도 고양시 덕양구 권율대로 570', 37.640653137831514, 126.87359977084844, CURRENT_TIMESTAMP, 'master1', true, false),
    ('ee7a252b-39cf-4411-8dc4-5b7d76e0c945', 3, '경기 남부 센터', '경기도 이천시 덕평로 257-21', 37.19060194448469, 127.3758115903835, CURRENT_TIMESTAMP, 'master1', true, false),
    ('5f19e4d3-8a42-4af1-abc7-1f0c621627d9', 4, '부산광역시 센터', '부산 동구 중앙대로 206', 35.11649039962057, 129.04266556006112, CURRENT_TIMESTAMP, 'master1', true, false),
    ('cb3d914b-b08f-41d7-9c8a-c64f7db8ab21', 5, '대구광역시 센터', '대구 북구 태평로 161', 35.87628985090422, 128.59624307320175, CURRENT_TIMESTAMP, 'master1', true, false),
    ('0c66a9da-092f-4505-9a29-8f7ba531bfb3', 6, '인천광역시 센터', '인천 남동구 정각로 29', 37.456427456703615, 126.70506426997024, CURRENT_TIMESTAMP, 'master1', true, false),
    ('781118dc-0d1b-4c89-83b5-bbfbdb19a497', 7, '광주광역시 센터', '광주 서구 내방로 111', 35.16057091390541, 126.85183215964, CURRENT_TIMESTAMP, 'master1', true, false),
    ('cd781be2-6ef8-43a4-b56c-b1093f0938cf', 8, '대전광역시 센터', '대전 서구 둔산로 100', 36.350328381622745, 127.38444389861235, CURRENT_TIMESTAMP, 'master1', true, false),
    ('d4e21760-28f3-4528-a60f-648b29275076', 9, '울산광역시 센터', '울산 남구 중앙로 201', 35.539415818473465, 129.31129512092022, CURRENT_TIMESTAMP, 'master1', true, false),
    ('6a158b7c-fc16-4c7a-8715-fb592e9d11ff', 10, '세종특별자치시 센터', '세종특별자치시 한누리대로 2130', 36.48029344294604, 127.28897299485354, CURRENT_TIMESTAMP, 'master1', true, false),
    ('f4908056-9c33-42e5-9bc7-ecf6b60c6d4a', 11, '강원특별자치도 센터',  '강원특별자치도 춘천시 중앙로 1', 37.88516555712624, 127.72980696761263, CURRENT_TIMESTAMP, 'master1', true, false),
    ('b0b0a5b7-58e4-4933-81bc-0ec7dc957b67', 12, '충청북도 센터', '충북 청주시 상당구 상당로 82', 36.636070071293545, 127.49124269428535, CURRENT_TIMESTAMP, 'master1', true, false),
    ('8bc37a5d-bb5a-4b5b-b26a-8a7ebc8ec63a', 13, '충청남도 센터', '충남 홍성군 홍북읍 충남대로 21', 36.658783062657136, 126.6726914, CURRENT_TIMESTAMP, 'master1', true, false),
    ('7204a4ad-8c55-4e5e-bfa1-e85065016b99', 14, '전북특별자치도 센터','전북특별자치도 전주시 완산구 효자로 225', 35.82059725765709, 127.1081639145246, CURRENT_TIMESTAMP, 'master1', true, false),
    ('3fc6e6e7-6a23-4df1-9b74-3e28a8c2c17c', 15, '전라남도 센터', '전남 무안군 삼향읍 오룡길 1', 34.816290460031524, 126.46260445382912, CURRENT_TIMESTAMP, 'master1', true, false),
    ('0628e362-5b0f-4bc5-84d7-3ef7c6919c9b', 16, '경상북도 센터', '경북 안동시 풍천면 도청대로 455', 36.57599855798378, 128.50547197215465, CURRENT_TIMESTAMP, 'master1', true, false),
    ('c10a579b-d097-4424-a964-d8e362d27a4f', 17, '경상남도 센터', '경남 창원시 의창구 중앙대로 300', 35.237883649443155, 128.69154655690582, CURRENT_TIMESTAMP, 'master1', true, false);

INSERT INTO p_hub_transfers (departure_hub_id, arrival_hub_id, estimated_time, estimated_distance, created_at, created_by, is_public, is_deleted)
VALUES
    (1, 3, 76, 88, CURRENT_TIMESTAMP, 'master1', true, false),
    (2, 3, 52, 57,CURRENT_TIMESTAMP, 'master1', true, false),
    (6, 3, 62, 80,CURRENT_TIMESTAMP, 'master1', true, false),
    (11, 3, 109, 108,CURRENT_TIMESTAMP, 'master1', true, false),
    (3, 16, 139, 158,CURRENT_TIMESTAMP, 'master1', true, false),
    (3, 8, 90, 109,CURRENT_TIMESTAMP, 'master1', true, false),
    (3, 5, 202, 226,CURRENT_TIMESTAMP, 'master1', true, false),

    (13, 8, 64, 90,CURRENT_TIMESTAMP, 'master1', true, false),
    (12, 8, 43, 38,CURRENT_TIMESTAMP, 'master1', true, false),
    (10, 8, 27, 21,CURRENT_TIMESTAMP, 'master1', true, false),
    (14, 8, 72, 85,CURRENT_TIMESTAMP, 'master1', true, false),
    (7, 8, 108, 169,CURRENT_TIMESTAMP, 'master1', true, false),
    (15, 8, 142, 220,CURRENT_TIMESTAMP, 'master1', true, false),
    (3, 8, 90, 109,CURRENT_TIMESTAMP, 'master1', true, false),
    (8, 5, 102, 151,CURRENT_TIMESTAMP, 'master1', true, false),

    (16, 5, 74, 103,CURRENT_TIMESTAMP, 'master1', true, false),
    (3, 5, 202, 226,CURRENT_TIMESTAMP, 'master1', true, false),
    (8, 5, 102, 151,CURRENT_TIMESTAMP, 'master1', true, false),
    (5, 17, 73, 95,CURRENT_TIMESTAMP, 'master1', true, false),
    (5, 4, 78, 112,CURRENT_TIMESTAMP, 'master1', true, false),
    (5, 9, 84, 109,CURRENT_TIMESTAMP, 'master1', true, false),

    (3, 16, 139, 158,CURRENT_TIMESTAMP, 'master1', true, false),
    (16, 5, 74, 103,CURRENT_TIMESTAMP, 'master1', true, false);