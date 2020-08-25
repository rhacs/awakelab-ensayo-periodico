----------------------------------------------------------------------------------------------------
-- Tabla: users
----------------------------------------------------------------------------------------------------

CREATE TABLE users (
    id NUMBER NOT NULL,
    name NVARCHAR2(50) NOT NULL,
    username NVARCHAR2(20) NOT NULL,
    email NVARCHAR2(50) NOT NULL,

    -- Llave primaria
    CONSTRAINT users_pk PRIMARY KEY (id)
);

----------------------------------------------------------------------------------------------------
-- Tabla: posts
----------------------------------------------------------------------------------------------------

CREATE TABLE posts (
    id NUMBER NOT NULL,
    title NVARCHAR2(250) NOT NULL,
    body NVARCHAR2(1000) NOT NULL,
    userId NUMBER NOT NULL,

    -- Llave primaria
    CONSTRAINT posts_pk PRIMARY KEY (id),

    -- Llave foránea
    CONSTRAINT posts_users_fk FOREIGN KEY (userId) REFERENCES users (id)
);

----------------------------------------------------------------------------------------------------
-- Tabla: comments
----------------------------------------------------------------------------------------------------

CREATE TABLE comments (
    id NUMBER NOT NULL,
    name NVARCHAR2(250) NOT NULL,
    email NVARCHAR2(100) NOT NULL,
    body NVARCHAR2(1000) NOT NULL,
    postId NUMBER NOT NULL,

    -- Llave primaria
    CONSTRAINT comments_pk PRIMARY KEY (id),

    -- Llave foránea
    CONSTRAINT comments_posts_fk FOREIGN KEY (postId) REFERENCES posts (id)
);
