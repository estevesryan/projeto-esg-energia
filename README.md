# 🌱 ESG Energy Monitor

Sistema de monitoramento de eficiência energética - Spring Boot + Oracle FIAP

---

## 🚀 Início Rápido

```bash
mvn clean install
mvn spring-boot:run
```

Acesse: **http://localhost:8080/swagger-ui.html**

### Login no Swagger
1. POST `/api/auth/login` → Try it out
2. Use: `{"username": "admin", "password": "admin123"}`
3. Copie o token
4. Clique **Authorize** 🔒 e cole o token

---

## 🗄️ Banco Oracle FIAP

- Host: `oracle.fiap.com.br:1521`
- User: `***REMOVED***`
- SID: `ORCL`

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

*****REMOVED***** | FIAP 2025
