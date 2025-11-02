# 🌱 ESG Energy Monitor

Sistema de monitoramento de eficiência energética - Spring Boot + Oracle FIAP

---

## 🚀 Início Rápido

### Configuração de Variáveis de Ambiente

1. Copie o arquivo `.env.example` para `.env`:
```bash
cp .env.example .env
```

2. Edite o arquivo `.env` com suas credenciais:
```bash
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
SPRING_DATASOURCE_USERNAME=seu_usuario
SPRING_DATASOURCE_PASSWORD=sua_senha
JWT_SECRET=sua_chave_jwt_em_base64
```

3. Execute a aplicação:
```bash
mvn clean install
mvn spring-boot:run
```

### Usando Docker

```bash
docker compose up --build -d
```

Acesse: **http://localhost:8080/swagger-ui.html**

### Login no Swagger
1. POST `/api/auth/login` → Try it out
2. Use: `{"username": "admin", "password": "admin123"}`
3. Copie o token
4. Clique **Authorize** 🔒 e cole o token

---

## 🗄️ Banco Oracle FIAP

Configure suas credenciais no arquivo `.env` (veja seção "Início Rápido").

Flyway cria 5 tabelas automaticamente.

---

## 📊 Funcionalidades

- 🏢 Gestão de Unidades
- 📈 Leituras de Consumo
- ⚠️ Alertas Automáticos
- 📅 Agendamentos
- 📊 Dashboard

---

## 🔐 Usuários

- Admin: `admin/admin123`
- User: `user/user123`

---

## 📡 Endpoints

| Método | Endpoint | Auth |
|--------|----------|------|
| POST | `/api/auth/login` | Público |
| GET | `/api/unidades` | Sim |
| POST | `/api/leituras` | Público* |
| GET | `/api/alertas/nao-lidos` | Sim |
| GET | `/api/indicadores?unidadeId=1` | Sim |

*Público para IoT

---

## 🛠️ Stack

- Java 17 + Spring Boot 3.2.0
- Spring Security + JWT
- Spring Data JPA + Oracle
- Flyway + Swagger

---

## 🐛 Troubleshooting

```bash
# Porta ocupada
lsof -ti:8080 | xargs kill -9

# Testar Oracle
./test-connection.sh

# Verificar Java
java -version
```

---

**RM559684** | FIAP 2025
