CREATE TABLE tb_consultations (
    consultation_id UUID PRIMARY KEY,
    doctor_name VARCHAR(100) NOT NULL,
    hospital_name VARCHAR(100) NOT NULL,
    date_consult TIMESTAMP NOT NULL,
    user_id UUID,
    CONSTRAINT fk_tb_consultations_user FOREIGN KEY (user_id) REFERENCES tb_users(user_id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);