CREATE TABLE tb_consultations (
    consultation_id UUID PRIMARY KEY,
    doctor_name VARCHAR(100) NOT NULL,
    hospital_name VARCHAR(100) NOT NULL,
    date_consult TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);