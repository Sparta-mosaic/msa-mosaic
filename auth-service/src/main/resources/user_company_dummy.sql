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
    (gen_random_uuid(), 'Supply Company D', '321 Birch Street', 'SUPPLY', 'supplyd@example.com', (SELECT user_id FROM p_users WHERE username = 'company4')),
    (gen_random_uuid(), 'Supply Company E', '654 Maple Avenue', 'SUPPLY', 'supplye@example.com', (SELECT user_id FROM p_users WHERE username = 'company5')),

    (gen_random_uuid(), 'Demand Company A', '987 Cedar Road', 'DEMAND', 'demanda@example.com', (SELECT user_id FROM p_users WHERE username = 'company6')),
    (gen_random_uuid(), 'Demand Company B', '741 Walnut Street', 'DEMAND', 'demandb@example.com', (SELECT user_id FROM p_users WHERE username = 'company7')),
    (gen_random_uuid(), 'Demand Company C', '852 Chestnut Avenue', 'DEMAND', 'demandc@example.com', (SELECT user_id FROM p_users WHERE username = 'company8')),
    (gen_random_uuid(), 'Demand Company D', '963 Aspen Road', 'DEMAND', 'demandd@example.com', (SELECT user_id FROM p_users WHERE username = 'company9')),
    (gen_random_uuid(), 'Demand Company E', '159 Willow Street', 'DEMAND', 'demande@example.com', (SELECT user_id FROM p_users WHERE username = 'company10')),
    (gen_random_uuid(), 'Demand Company F', '753 Elm Avenue', 'DEMAND', 'demandf@example.com', (SELECT user_id FROM p_users WHERE username = 'company11')),
    (gen_random_uuid(), 'Demand Company G', '357 Oak Road', 'DEMAND', 'demandg@example.com', (SELECT user_id FROM p_users WHERE username = 'company12')),
    (gen_random_uuid(), 'Demand Company H', '246 Birch Street', 'DEMAND', 'demandh@example.com', (SELECT user_id FROM p_users WHERE username = 'company13')),
    (gen_random_uuid(), 'Demand Company I', '135 Maple Avenue', 'DEMAND', 'demandi@example.com', (SELECT user_id FROM p_users WHERE username = 'company14')),
    (gen_random_uuid(), 'Demand Company J', '864 Cedar Road', 'DEMAND', 'demandj@example.com', (SELECT user_id FROM p_users WHERE username = 'company15')),
    (gen_random_uuid(), 'Demand Company K', '975 Walnut Street', 'DEMAND', 'demandk@example.com', (SELECT user_id FROM p_users WHERE username = 'company16')),
    (gen_random_uuid(), 'Demand Company L', '486 Chestnut Avenue', 'DEMAND', 'demandl@example.com', (SELECT user_id FROM p_users WHERE username = 'company17')),
    (gen_random_uuid(), 'Demand Company M', '579 Aspen Road', 'DEMAND', 'demandm@example.com', (SELECT user_id FROM p_users WHERE username = 'company18')),
    (gen_random_uuid(), 'Demand Company N', '690 Willow Street', 'DEMAND', 'demandn@example.com', (SELECT user_id FROM p_users WHERE username = 'company19')),
    (gen_random_uuid(), 'Demand Company O', '801 Elm Avenue', 'DEMAND', 'demando@example.com', (SELECT user_id FROM p_users WHERE username = 'company20'));
