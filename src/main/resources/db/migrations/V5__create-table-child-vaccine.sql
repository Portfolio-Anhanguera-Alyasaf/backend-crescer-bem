CREATE TABLE tb_child_vaccine (
    child_vaccine_id UUID PRIMARY KEY,
    children_id UUID,
    vaccine_id UUID,
    applied BOOLEAN,
    CONSTRAINT fk_tb_child_vaccine_children FOREIGN KEY (children_id) REFERENCES tb_children(children_id),
    CONSTRAINT fk_tb_child_vaccine_vaccine FOREIGN KEY (vaccine_id) REFERENCES tb_vaccines(vaccine_id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);