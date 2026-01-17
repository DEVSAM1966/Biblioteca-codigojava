USE biblio_codigojava;

-- =========================
-- AUTORES
-- =========================
INSERT INTO authors (name_author) VALUES
                                      ('Isaac Asimov'),
                                      ('Ursula K. Le Guin'),
                                      ('Gabriel García Márquez'),
                                      ('J.R.R. Tolkien'),
                                      ('George Orwell'),
                                      ('Jane Austen'),
                                      ('Haruki Murakami'),
                                      ('Stephen King');

-- =========================
-- PUBLISHERS
-- =========================
INSERT INTO publishers (publisher_id, name_publisher, address, city, province, postal_code, country, phone, notes) VALUES
                                                                                                                       (1, 'Penguin Random House', 'Calle Mayor 12', 'Madrid', 'Madrid', '28013', 'España', '+34-910000001', 'Editorial internacional'),
                                                                                                                       (2, 'Minotauro', 'Av. Diagonal 123', 'Barcelona', 'Cataluña', '08019', 'España', '+34-930000002', 'Especializada en fantasía'),
                                                                                                                       (3, 'Alfaguara', 'Calle Serrano 45', 'Madrid', 'Madrid', '28006', 'España', '+34-910000003', 'Literatura latinoamericana'),
                                                                                                                       (4, 'Seix Barral', 'Calle Balmes 200', 'Barcelona', 'Cataluña', '08006', 'España', '+34-930000004', 'Narrativa contemporánea'),
                                                                                                                       (5, 'Planeta', 'Av. Tibidabo 45', 'Barcelona', 'Cataluña', '08022', 'España', '+34-930000005', 'Editorial generalista');

-- =========================
-- CATEGORÍAS
-- =========================
INSERT INTO categories (name_category, subtopic_category) VALUES
                                                              ('Ciencia Ficción', 'Robots'),
                                                              ('Fantasía', 'Épica'),
                                                              ('Realismo Mágico', 'Latinoamérica'),
                                                              ('Distopía', 'Política'),
                                                              ('Romántica', 'Clásicos'),
                                                              ('Terror', 'Sobrenatural'),
                                                              ('Contemporánea', 'Japón');

-- =========================
-- LIBROS
-- =========================
INSERT INTO books (isbn, title, pages, summary, edition_date, book_cover, book_file, language, authors, author_id, publisher_id, category_id) VALUES
                                                                                                                                                  ('9781234567890', 'Fundación', 255, 'La primera novela de la saga Fundación.', '1951-01-01', 'uploads/cover/fundacion.jpg', 'uploads/file/fundacion.pdf', 'Español', 'Coautor: John Doe', 1, 1, 1),
                                                                                                                                                  ('9781234567891', 'La mano izquierda de la oscuridad', 300, 'Novela de ciencia ficción feminista.', '1969-01-01', 'uploads/cover/mano_izquierda.jpg', 'uploads/file/mano_izquierda.pdf', 'Español', 'Coautor: Jane Roe', 2, 1, 1),
                                                                                                                                                  ('9781234567892', 'Cien años de soledad', 417, 'Obra cumbre del realismo mágico.', '1967-01-01', 'uploads/cover/cien_anos.jpg', 'uploads/file/cien_anos.pdf', 'Español', '', 3, 3, 3),
                                                                                                                                                  ('9781234567893', 'El Señor de los Anillos', 1200, 'Trilogía épica de fantasía.', '1954-01-01', 'uploads/cover/lotr.jpg', 'uploads/file/lotr.pdf', 'Español', 'Coautor: Christopher Tolkien', 4, 2, 2),
                                                                                                                                                  ('9781234567894', '1984', 328, 'Distopía política sobre vigilancia y control.', '1949-01-01', 'uploads/cover/1984.jpg', 'uploads/file/1984.pdf', 'Español', '', 5, 4, 4),
                                                                                                                                                  ('9781234567895', 'Orgullo y Prejuicio', 279, 'Clásico romántico inglés.', '1813-01-01', 'uploads/cover/pride.jpg', 'uploads/file/pride.pdf', 'Español', '', 6, 1, 5),
                                                                                                                                                  ('9781234567896', 'Kafka en la orilla', 505, 'Novela contemporánea japonesa.', '2002-01-01', 'uploads/cover/kafka.jpg', 'uploads/file/kafka.pdf', 'Español', '', 7, 4, 7),
                                                                                                                                                  ('9781234567897', 'It', 1138, 'Novela de terror sobrenatural.', '1986-01-01', 'uploads/cover/it.jpg', 'uploads/file/it.pdf', 'Español', '', 8, 5, 6);

-- =========================
-- USUARIOS
-- =========================
INSERT INTO users (fullname, dni, address, city, province, postal_code, country, phone, email, password, registration_date, user_drop, days_disciplinary, role) VALUES
                                                                                                                                                                   ('Ana Pérez', '12345678A', 'Calle Luna 5', 'Madrid', 'Madrid', '28001', 'España', '+34-600000001', 'ana@example.com', 'hashedpass1', '2024-01-15', FALSE, 0, 'USER'),
                                                                                                                                                                   ('Luis Gómez', '87654321B', 'Calle Sol 8', 'Barcelona', 'Cataluña', '08002', 'España', '+34-600000002', 'luis@example.com', 'hashedpass2', '2024-02-20', FALSE, 0, 'SUPPORT'),
                                                                                                                                                                   ('Marta Ruiz', '11223344C', 'Av. Mar 10', 'Valencia', 'Valencia', '46001', 'España', '+34-600000003', 'marta@example.com', 'hashedpass3', '2024-03-10', FALSE, 0, 'ADMIN'),
                                                                                                                                                                   ('Carlos López', '44556677D', 'Calle Verde 12', 'Sevilla', 'Andalucía', '41001', 'España', '+34-600000004', 'carlos@example.com', 'hashedpass4', '2024-04-05', FALSE, 0, 'USER');

-- =========================
-- PRÉSTAMOS
-- =========================
INSERT INTO loans (loan_date, return_date, user_id, isbn) VALUES
                                                              ('2024-04-01', '2024-04-15', 1, '9781234567890'),
                                                              ('2024-04-05', '2024-04-20', 2, '9781234567892'),
                                                              ('2024-04-10', '2024-04-25', 3, '9781234567893'),
                                                              ('2024-05-01', '2024-05-15', 4, '9781234567894'),
                                                              ('2024-05-05', '2024-05-20', 1, '9781234567895'),
                                                              ('2024-05-10', '2024-05-25', 2, '9781234567896'),
                                                              ('2024-05-15', '2024-05-30', 3, '9781234567897');

-- =========================
-- HISTORIALES (opiniones de usuarios sobre libros leídos)
-- =========================
INSERT INTO histories (loan_id, date_feedback, feedback) VALUES
                                                             (1, '2024-04-16', 'Fundación es brillante, aunque algo denso en partes.'),
                                                             (2, '2024-04-21', 'Cien años de soledad me atrapó, aunque el realismo mágico es complejo.'),
                                                             (3, '2024-04-26', 'El Señor de los Anillos es épico, pero largo.'),
                                                             (4, '2024-05-16', '1984 es inquietante y muy actual.'),
                                                             (5, '2024-05-21', 'Orgullo y Prejuicio es encantador y elegante.'),
                                                             (6, '2024-05-26', 'Kafka en la orilla es surrealista y fascinante.'),
                                                             (7, '2024-06-01', 'It es aterrador, me costó dormir después de leerlo.');
