# LoginNew

## O que foi usado

- Java com Swing (as telas)
- MySQL (o banco de dados)
- NetBeans (para programar)
- MySQL Workbench (para mexer no banco)

## Arquivos do projeto

Tudo fica no pacote `loginnew`:

- `Conexao.java` – faz a ligação com o banco
- `Usuario.java` – tudo que mexe na tabela de usuários (entrar, cadastrar, alterar, excluir)
- `TelaLogin.java` – tela de entrada
- `TelaCadastro.java` – tela para criar um usuário novo
- `TelaInicio.java` – menu que aparece depois do login
- `TelaAlteracao.java` – tela para mudar nome e senha
- `TestaConexao.java` – testa se o banco está conectando
- `TestaTela.java` – é ele que você roda para abrir o sistema

## Como funciona

1. Você roda o `TestaTela` e abre a tela de login.
2. Pode entrar com um usuário que já existe ou clicar em **Cadastrar** para criar um.
3. Depois de entrar, aparece o menu com "Bem vindo" e o nome.
4. No menu é possivel **Alterar Dados**, **Excluir** a conta ou **Voltar** para o login.

## Banco de dados


```sql
CREATE DATABASE IF NOT EXISTS bd_login;
USE bd_login;

CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha CHAR(64) NOT NULL
);

INSERT INTO usuario (nome, usuario, senha)
VALUES ('Administrador', 'admin',
        '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
```

## Usuário para testar

- **Usuário:** `admin`
- **Senha:** `123456`
