INSERT INTO tb_vaccines (vaccine_id, name, description, created_at, updated_at) VALUES
    (gen_random_uuid(), 'BCG', 'Protege contra formas graves de tuberculose', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Hepatite B', 'Protege contra o vírus da hepatite B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Penta (DTP + Hib + Hepatite B)', 'Protege contra difteria, tétano, coqueluche, meningite e hepatite B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'VIP (Poliomielite Inativada)', 'Protege contra a poliomielite', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'VOP (Poliomielite Oral)', 'Reforço contra a poliomielite', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Pneumocócica 10-valente', 'Protege contra pneumonia, otite e meningite pneumocócica', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Meningocócica C conjugada', 'Protege contra meningite causada pelo meningococo C', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Febre Amarela', 'Protege contra a febre amarela', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Tríplice Viral (SCR)', 'Protege contra sarampo, caxumba e rubéola', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'DTP', 'Reforço contra difteria, tétano e coqueluche', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Hepatite A', 'Protege contra o vírus da hepatite A', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Varicela', 'Protege contra catapora', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);