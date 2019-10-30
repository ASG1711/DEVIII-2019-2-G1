# DEVIII-2019-2-G1
Repositório do projeto de Desenvolvimento de Software III do Grupo 1, 2019/2


# Rodar Back-end

Baixar Oracle XE 18c : 
 - https://www.oracle.com/database/technologies/xe-downloads.html

Após instalar o Oracle XE 18c, baixar o Sql Developer para fazer consultar e executar scripts no banco: 
 - https://www.oracle.com/tools/downloads/sqldev-v192-downloads.html
 
Verificar após instalação que serviços relevantes do Oracle XE estão rodando, pelo Gerenciador de Tarefas (inicializar caso contrário).
Os serviços são: OracleOraDB18Home1MTSRecoveryService, OracleOraDB18Home1TNSListener e OracleServiceXE.

Rodar Prompt de Comando como administrador (Atalho: WINDOWS + X -> "Prompt de Comando (Admin)"), verificar se Listener está ativo com o comando:
```bash
lsnrctl status
```
 
Caso não esteja, ativar com:
```bash
lsnrctl start
```
 
Feito isso, criar um usuário no banco e dar Grant, o que pode ser feito usando o comando da linha de comando "sqlplus" - note que é necessário constar ";" no fim da instrução:
```bash	
CREATE USER Desenvolvimento identified by Teste123; 
```
(pode mudar se quiser, lembre de mudar no ApplicationProprieties)
```bash
GRANT create session,create table,create view TO Desenvolvimento;
```

Para conveniência, criar nova conexão no SQL Developer, apontando pro host "localhost" e porta default "1521". Usar SID "ex" e usuário e senha "Developer" e "Teste123".

Abra o Projeto na sua IDE preferida, foi utilizado o NetBeans, mas deve dar certo nas outras. Realize build, execute o algoritmo.

O Primeiro Build é o mais lento pelo fato de baixar todas dependencias necessárias.

# Rodar Front-end

Instale o NodeJS.

Então abra o cmd na pasta raiz do front-end (Atalho: SHIFT + Right Click -> "Abrir janela de comando aqui") e digite:
```bash
npm i
```

Caso não possua, instale o http-server:
```bash
npm install -g http-server
```

Em seguida digitar:
```bash
http-server -c-1
```
Acessar no Chrome http://localhost:8080



