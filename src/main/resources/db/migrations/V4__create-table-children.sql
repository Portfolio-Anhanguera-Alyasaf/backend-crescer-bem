CREATE TABLE tb_children (
    children_id UUID PRIMARY KEY,
    name VARCHAR(255),
    birthday DATE,
    weight DOUBLE PRECISION,
    height DOUBLE PRECISION,
    user_id UUID,
    CONSTRAINT fk_tb_children_user FOREIGN KEY (user_id) REFERENCES tb_users(user_id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);