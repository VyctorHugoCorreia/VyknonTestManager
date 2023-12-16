CREATE DATABASE IF NOT EXISTS gerTestes;
USE gerTestes;

CREATE TABLE IF NOT EXISTS Ttime (
    id_time INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome_time VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS CTimeProduto (
    id_tproduto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    desc_produto VARCHAR(100) NOT NULL,
    id_time INT NOT NULL,
    FOREIGN KEY (id_time) REFERENCES Ttime(id_time)
);

CREATE TABLE IF NOT EXISTS CPlanoTestes (
    id_plano INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    desc_plano VARCHAR(100) NOT NULL,
    id_time INT NOT NULL,
    id_tproduto INT NOT NULL,
    FOREIGN KEY (id_time) REFERENCES Ttime(id_time),
    FOREIGN KEY (id_tproduto) REFERENCES CTimeProduto(id_tproduto)
);

CREATE TABLE IF NOT EXISTS CSuiteTestes (
    id_suite INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    desc_plano VARCHAR(100) NOT NULL,
    id_time INT NOT NULL,
    id_tproduto INT NOT NULL,
    id_plano INT NOT NULL,
    FOREIGN KEY (id_time) REFERENCES Ttime(id_time),
    FOREIGN KEY (id_tproduto) REFERENCES CTimeProduto(id_tproduto),
    FOREIGN KEY (id_plano) REFERENCES CPlanoTestes(id_plano)
);

CREATE TABLE IF NOT EXISTS Cfuncionalidade (
    id_funcionalidade INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    desc_funcionalidade VARCHAR(100) NOT NULL,
    id_time INT NOT NULL,
    id_tproduto INT NOT NULL,
    FOREIGN KEY (id_time) REFERENCES Ttime(id_time),
    FOREIGN KEY (id_tproduto) REFERENCES CTimeProduto(id_tproduto)
);

CREATE TABLE IF NOT EXISTS Tplataforma (
    id_plataforma INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    desc_plataforma VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Tstatus (
    id_status INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    desc_status VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS TAutomatizado (
    id_automatizado INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    desc_automatizado VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS TpCenario (
    id_tpcenario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    desc_tpcenario VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS TCenario (
    id_cenario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo_cenario VARCHAR(100) NOT NULL,
    desc_cenario VARCHAR(200) NOT NULL,
    link_cenario VARCHAR(200) NOT NULL,
    id_time INT NOT NULL,
    id_plano INT NOT NULL,
    id_suite INT NOT NULL,
    id_tproduto INT NOT NULL,
    id_funcionalidade INT NOT NULL,
    id_tpcenario INT NOT NULL,
    id_plataforma INT NOT NULL,
    id_status INT NOT NULL,
    id_automatizado INT NOT NULL,
    tagcenario VARCHAR(100) NOT NULL,
    evidencias BLOB NOT NULL,
    FOREIGN KEY (id_time) REFERENCES Ttime(id_time),
    FOREIGN KEY (id_plano) REFERENCES CPlanoTestes(id_plano),
    FOREIGN KEY (id_suite) REFERENCES CSuiteTestes(id_suite),
    FOREIGN KEY (id_tproduto) REFERENCES CTimeProduto(id_tproduto),
    FOREIGN KEY (id_funcionalidade) REFERENCES Cfuncionalidade(id_funcionalidade),
    FOREIGN KEY (id_tpcenario) REFERENCES TpCenario(id_tpcenario),
    FOREIGN KEY (id_plataforma) REFERENCES Tplataforma(id_plataforma),
    FOREIGN KEY (id_status) REFERENCES Tstatus(id_status),
    FOREIGN KEY (id_automatizado) REFERENCES TAutomatizado(id_automatizado)
);

CREATE TABLE IF NOT EXISTS Tsteps (
    id_steps INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_cenario INT NOT NULL,
    ordem INT NOT NULL,
    desc_steps VARCHAR(250) NOT NULL,
    status BOOLEAN NOT NULL,
    FOREIGN KEY (id_cenario) REFERENCES TCenario(id_cenario)
);

INSERT INTO TAutomatizado (desc_automatizado)
VALUES ('SIM'), ('NÃO'), ('NÃO SE APLICA');

INSERT INTO TpCenario (desc_tpcenario)
VALUES ('Fluxo positivo'), ('Fluxo alternativo'), ('Fluxo negativo');

INSERT INTO Tplataforma (desc_plataforma)
VALUES ('Mobile'), ('Web'), ('Api');

INSERT INTO Tstatus (desc_status)
VALUES ('Executado com sucesso'), ('Executado com falha'), ('Pendente execução');
