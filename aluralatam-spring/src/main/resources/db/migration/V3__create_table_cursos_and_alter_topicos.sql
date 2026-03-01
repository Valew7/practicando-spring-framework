CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    activo TINYINT DEFAULT 1
);

-- Migración de datos existentes, si los hay, antes de alterar la estructura (como precaución).
-- Al ser un proyecto nuevo es esperable que esté vacío, pero adaptaremos la tabla de todos modos.

ALTER TABLE topicos DROP COLUMN autor;
ALTER TABLE topicos DROP COLUMN curso;

ALTER TABLE topicos ADD COLUMN autor_id BIGINT NOT NULL;
ALTER TABLE topicos ADD COLUMN curso_id BIGINT NOT NULL;

ALTER TABLE topicos ADD CONSTRAINT fk_topicos_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios(id);
ALTER TABLE topicos ADD CONSTRAINT fk_topicos_curso_id FOREIGN KEY (curso_id) REFERENCES cursos(id);
