CREATE TABLE tb_child_consultations (
    children_id UUID NOT NULL,
    consultation_id UUID NOT NULL,
    PRIMARY KEY (children_id, consultation_id),
    CONSTRAINT fk_children_consultations_children FOREIGN KEY (children_id) REFERENCES tb_children(children_id),
    CONSTRAINT fk_children_consultations_consultation FOREIGN KEY (consultation_id) REFERENCES tb_consultations(consultation_id)
);