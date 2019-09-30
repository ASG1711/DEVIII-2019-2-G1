# DEVIII-2019-2-G1
Repositório do projeto de Desenvolvimento de Software III do Grupo 1, 2019/2


# Rodar Back-end

Baixar Oracle XE 18c : 
 - https://www.oracle.com/database/technologies/xe-downloads.html

Ap�s instalar o Oracle XE 18c, baixar o Sql Developer para fazer consultar e executar scripts no banco: 
 - https://www.oracle.com/tools/downloads/sqldev-v192-downloads.html
 
Feito isso, criar um usu�rio no banco e dar Grant:

	
CREATE USER Desenvolvimento identified by Teste123; (pode mudar se quiser, lembre de mudar no ApplicationProprieties)

GRANT create session,create table,create view TO Desenvolvimento;

Abra o Projeto na sua IDE preferida, foi utilizado o NetBeans, mas deve dar certo nas outras.

O Primeiro Build � o mais lento pelo fato de baixar todas dependencias necess�rias.

#Rodar Front-end
Instale o NodeJS

Ent�o abra o cmd na pasta raiz do front-end e digite:
```bash
npm i
```

Em seguida digitar:
```bash
http-server -c-1
```
Acessar no Chrome http://localhost:8080



