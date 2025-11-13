-- ==============
-- PARTE 1 - UP
-- ==============

-- Usuários gerais do sistema (cliente, restaurante, entregador, suporte, etc.)
CREATE TABLE usuarios (
    id              BIGSERIAL PRIMARY KEY,
    nome            VARCHAR(150)        NOT NULL,
    email           VARCHAR(150)        NOT NULL UNIQUE,
    senha_hash      VARCHAR(255)        NOT NULL,
    telefone        VARCHAR(20),
    tipo_usuario    VARCHAR(20)         NOT NULL, -- 'cliente', 'restaurante', 'entregador', 'suporte'
    criado_em       TIMESTAMP           NOT NULL DEFAULT NOW()
);

-- Dados específicos de cliente
CREATE TABLE clientes (
    id              BIGSERIAL PRIMARY KEY,
    usuario_id      BIGINT              NOT NULL UNIQUE,
    -- Exemplo de campos extras (opcional)
    endereco_padrao TEXT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Dados específicos de restaurante
CREATE TABLE restaurantes (
    id              BIGSERIAL PRIMARY KEY,
    usuario_id      BIGINT              NOT NULL UNIQUE,
    nome_fantasia   VARCHAR(150)        NOT NULL,
    cnpj            VARCHAR(18)         NOT NULL UNIQUE,
    endereco        TEXT                NOT NULL,
    dados_bancarios TEXT,                          -- pode ser separado depois
    ativo           BOOLEAN             NOT NULL DEFAULT TRUE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- ==============
-- PARTE 1 - DOWN
-- (rollback)
-- ==============

DROP TABLE IF EXISTS restaurantes;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS usuarios;
