# 🌱 ESG Energy Monitor

> **Sistema Inteligente de Monitoramento e Gestão de Eficiência Energética**  
> Plataforma ESG para otimização do consumo de energia em tempo real

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green?logo=springboot)
![Oracle](https://img.shields.io/badge/Oracle-FIAP-red?logo=oracle)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?logo=docker)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 📖 Sobre o Projeto

O **ESG Energy Monitor** é uma solução completa para **monitoramento, análise e gestão de eficiência energética** em ambientes corporativos e industriais. Desenvolvido com foco em práticas **ESG (Environmental, Social and Governance)**, o sistema permite:

- 🔌 **Monitoramento em Tempo Real**: Coleta automática de dados de consumo através de sensores IoT
- 📊 **Análise Preditiva**: Dashboard com indicadores consolidados e tendências de consumo
- ⚠️ **Sistema de Alertas Inteligente**: Notificações automáticas quando há desvios no padrão de consumo
- 🏢 **Gestão Multiunit**: Gerenciamento centralizado de múltiplas unidades consumidoras
- 📅 **Agendamento de Manutenções**: Planejamento preventivo e corretivo de equipamentos
- 📈 **Relatórios de Sustentabilidade**: Métricas para compliance ESG e redução de pegada de carbono

### 🎯 Objetivos

1. **Reduzir Custos**: Identificar e eliminar desperdícios energéticos
2. **Sustentabilidade**: Contribuir para metas de redução de emissões de CO₂
3. **Conformidade ESG**: Fornecer dados para relatórios de sustentabilidade
4. **Prevenção**: Detectar anomalias antes que gerem custos adicionais
5. **Automação**: Integração com dispositivos IoT para coleta automática de dados

---

## 🏗️ Arquitetura

### Stack Tecnológico

```
┌─────────────────────────────────────────────────────────┐
│                    FRONTEND / API CLIENTS                │
│         Postman • Insomnia • IoT Devices • Web           │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                   SPRING BOOT 3.2.0                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │ Controllers  │  │   Services   │  │ Repositories │  │
│  │  REST API    │→ │   Business   │→ │  Spring Data │  │
│  │   + Swagger  │  │    Logic     │  │     JPA      │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
│                                                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │   Security   │  │     JWT      │  │   Flyway     │  │
│  │    Filter    │  │  Auth Token  │  │  Migrations  │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│              ORACLE DATABASE (FIAP)                      │
│   Usuários • Unidades • Leituras • Alertas • Agenda     │
└─────────────────────────────────────────────────────────┘
```

### Componentes Principais

#### 🔐 **Security Layer**
- **Spring Security** + **JWT (JJWT 0.12.3)**
- Autenticação stateless com tokens Bearer
- Autorização baseada em roles (ADMIN/USER)
- Senhas criptografadas com BCrypt
- Filtro customizado para validação de tokens

#### 🎮 **Controllers (REST API)**
- `AuthController`: Autenticação e login
- `UnidadeController`: CRUD de unidades consumidoras
- `LeituraController`: Registro e consulta de leituras
- `AlertaController`: Gerenciamento de alertas
- `IndicadorController`: Dashboard e métricas consolidadas
- `AgendamentoController`: Gestão de manutenções

#### 💼 **Services (Business Logic)**
- Validação de regras de negócio
- Geração automática de alertas por threshold
- Cálculos de indicadores (média, variação, meta)
- Gerenciamento de agendamentos e status

#### 🗄️ **Persistence Layer**
- **Spring Data JPA** com Oracle Database
- Repositories com queries customizadas
- Entidades mapeadas com relacionamentos
- Auditoria automática com `@EntityListeners`

#### 📚 **Database Migration**
- **Flyway** para versionamento de schema
- Migrations automáticas na inicialização
- Scripts de criação de tabelas e dados seed

---

## 🚀 Início Rápido

### Pré-requisitos

- ☕ **Java 17** ou superior
- 🐳 **Docker** e **Docker Compose** (opcional)
- 🗄️ Acesso ao **Oracle Database FIAP**
- 📦 **Maven 3.8+**

### 1️⃣ Configuração de Variáveis de Ambiente

```bash
# Clone o repositório (se necessário)
git clone https://github.com/estevesryan/projeto-esg-energia.git
cd projeto-esg-energia

# Copie o arquivo de exemplo
cp .env.example .env

# Edite com suas credenciais
nano .env  # ou vim, code, etc.
```

**Arquivo `.env`:**
```bash
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
SPRING_DATASOURCE_USERNAME=seu_rm
SPRING_DATASOURCE_PASSWORD=sua_senha
JWT_SECRET=c3VhLWNoYXZlLXNlY3JldGEtbXVpdG8tc2VndXJhLWNvbS1uby1taW5pbW8tMjU2LWJpdHMtcGFyYS1oczI1Ng==
```

> ⚠️ **Importante**: Nunca commite o arquivo `.env` no Git!

### 2️⃣ Executar com Maven

```bash
# Limpar, compilar e instalar
mvn clean install

# Executar a aplicação
mvn spring-boot:run
```

### 3️⃣ Executar com Docker 🐳

```bash
# Construir e iniciar os containers
docker compose up --build -d

# Ver logs
docker compose logs -f

# Parar os containers
docker compose down
```

### 4️⃣ Acessar a Aplicação

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **Health Check**: http://localhost:8080/actuator/health

### 5️⃣ Autenticar no Swagger

1. Acesse a interface Swagger
2. Encontre o endpoint `POST /api/auth/login`
3. Clique em **"Try it out"**
4. Use as credenciais:
   ```json
   {
     "username": "admin",
     "password": "admin123"
   }
   ```
5. Copie o **token** do response
6. Clique no botão **🔒 Authorize** (topo da página)
7. Cole o token no campo **Value**
8. Clique em **Authorize** e depois **Close**

✅ Agora você pode testar todos os endpoints protegidos!

---

## 📊 Modelo de Dados

### 🗂️ Entidades e Relacionamentos

```
┌─────────────┐
│   USUARIO   │
│─────────────│
│ id (PK)     │
│ username    │
│ password    │
│ nome        │
│ email       │
│ role        │
│ ativo       │
└─────────────┘

┌─────────────┐
│   UNIDADE   │◄────────┐
│─────────────│         │
│ id (PK)     │         │
│ nome        │         │
│ localizacao │         │
│ limite_kwh  │         │
│ ativo       │         │
└─────────────┘         │
      ▲                 │
      │                 │
      │                 │
┌─────────────┐   ┌─────────────┐   ┌──────────────┐
│   LEITURA   │   │    ALERTA   │   │ AGENDAMENTO  │
│─────────────│   │─────────────│   │──────────────│
│ id (PK)     │   │ id (PK)     │   │ id (PK)      │
│ unidade_id  │──►│ unidade_id  │──►│ unidade_id   │──┘
│ timestamp   │   │ tipo        │   │ descricao    │
│ consumo_kwh │   │ mensagem    │   │ tipo_acao    │
│ sensor_id   │   │ consumo_kwh │   │ data_hora    │
└─────────────┘   │ limite_kwh  │   │ status       │
                  │ lido        │   └──────────────┘
                  └─────────────┘
```

### 📋 Descrição das Tabelas

#### 👤 **USUARIO**
Armazena usuários do sistema com autenticação e autorização.
- **Roles**: `ROLE_ADMIN` (acesso total) ou `ROLE_USER` (acesso limitado)
- **Password**: Criptografado com BCrypt
- **Campos**: username, password, nome, email, role, ativo

#### 🏢 **UNIDADE**
Representa unidades consumidoras (prédios, fábricas, lojas).
- **Limite**: Define threshold diário de consumo em kWh
- **Localização**: Endereço completo da unidade
- **Status**: Ativo/Inativo para controle de unidades operacionais

#### 📈 **LEITURA**
Registros de consumo energético coletados por sensores IoT.
- **Timestamp**: Data/hora da leitura
- **Consumo**: Valor em kWh
- **Sensor ID**: Identificação do dispositivo IoT
- **Relacionamento**: N leituras → 1 unidade

#### ⚠️ **ALERTA**
Notificações geradas automaticamente quando há desvios.
- **Tipos**: `CONSUMO_ALTO`, `EQUIPAMENTO_INEFICIENTE`, etc.
- **Threshold**: Compara consumo_registrado vs limite_kwh
- **Status Leitura**: Campo `lido` para controle de visualização

#### 📅 **AGENDAMENTO**
Planejamento de manutenções preventivas e corretivas.
- **Tipos**: `MANUTENCAO`, `INSPECAO`, `CALIBRACAO`
- **Status**: `PENDENTE`, `EXECUTADO`, `CANCELADO`
- **Observações**: Campo para registrar resultados da ação

---

## 📡 API Endpoints

### 🔐 Autenticação

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| POST | `/api/auth/login` | Autenticação e geração de JWT | Público |

**Request Body:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tipo": "Bearer",
  "expiresIn": 86400000
}
```

### 🏢 Unidades

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| GET | `/api/unidades` | Listar todas as unidades | JWT |
| GET | `/api/unidades/ativas` | Listar apenas unidades ativas | JWT |
| GET | `/api/unidades/{id}` | Buscar unidade por ID | JWT |
| POST | `/api/unidades` | Criar nova unidade | JWT |
| PUT | `/api/unidades/{id}` | Atualizar unidade | JWT |
| DELETE | `/api/unidades/{id}` | Deletar unidade | JWT |

**Exemplo - Criar Unidade:**
```json
{
  "nome": "Unidade Paulista",
  "endereco": "Av. Paulista, 1000",
  "cidade": "São Paulo",
  "estado": "SP",
  "capacidadeKw": 150.0,
  "metaConsumoMensal": 30000.0
}
```

### 📈 Leituras

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| GET | `/api/leituras` | Listar leituras (paginado) | JWT |
| GET | `/api/leituras/periodo` | Buscar por período | JWT |
| POST | `/api/leituras` | Registrar leitura | Público* |

> *Endpoint público para integração com dispositivos IoT

**Exemplo - Registrar Leitura:**
```json
{
  "unidadeId": 1,
  "consumoKwh": 8500.5,
  "tensaoV": 220.0,
  "correnteA": 38.6,
  "fatorPotencia": 0.92,
  "dataLeitura": "2025-11-05T14:30:00"
}
```

### ⚠️ Alertas

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| GET | `/api/alertas` | Listar alertas (paginado) | JWT |
| GET | `/api/alertas/nao-lidos` | Alertas não lidos | JWT |
| GET | `/api/alertas/unidade/{id}` | Alertas por unidade | JWT |
| GET | `/api/alertas/contador` | Contador de não lidos | JWT |
| PATCH | `/api/alertas/{id}/marcar-lido` | Marcar como lido | JWT |
| PATCH | `/api/alertas/marcar-todos-lidos` | Marcar todos como lidos | JWT |

### 📊 Indicadores (Dashboard)

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| GET | `/api/indicadores?unidadeId={id}` | Dashboard consolidado | JWT |

**Response:**
```json
{
  "unidadeId": 1,
  "nomeUnidade": "Unidade Paulista",
  "consumoMedioKwh": 8234.5,
  "totalLeituras": 120,
  "alertasAtivos": 3,
  "economiaEstimada": 15.2,
  "metaMensal": 30000.0,
  "percentualMeta": 82.3
}
```

### 📅 Agendamentos

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| GET | `/api/agendamentos` | Listar todos (paginado) | JWT |
| GET | `/api/agendamentos/unidade/{id}` | Por unidade | JWT |
| GET | `/api/agendamentos/status/{status}` | Por status | JWT |
| GET | `/api/agendamentos/{id}` | Buscar por ID | JWT |
| POST | `/api/agendamentos` | Criar agendamento | JWT |
| PUT | `/api/agendamentos/{id}` | Atualizar completo | JWT |
| PATCH | `/api/agendamentos/{id}/status` | Atualizar status | JWT |
| DELETE | `/api/agendamentos/{id}` | Deletar agendamento | JWT |

**Exemplo - Criar Agendamento:**
```json
{
  "unidadeId": 1,
  "tipoAgendamento": "MANUTENCAO",
  "descricao": "Manutenção preventiva no sistema elétrico",
  "dataAgendada": "2025-11-15T09:00:00"
}
```

---

## 🔐 Autenticação e Segurança

### JWT (JSON Web Token)

O sistema utiliza **JWT** para autenticação stateless:

1. **Login**: Cliente envia username/password
2. **Token**: Server gera JWT assinado com HS256
3. **Requests**: Cliente inclui token no header `Authorization: Bearer <token>`
4. **Validação**: Filtro verifica assinatura e expiração do token

**Configuração de Segurança:**
```java
// Endpoints públicos (sem autenticação)
- POST /api/auth/login
- POST /api/leituras (para IoT)
- /swagger-ui/**
- /api-docs/**

// Endpoints protegidos (requerem JWT)
- Todos os outros endpoints
```

### Roles e Permissões

| Role | Permissões |
|------|------------|
| **ROLE_ADMIN** | Acesso completo: criar, ler, atualizar, deletar |
| **ROLE_USER** | Acesso limitado: apenas leitura e consultas |

### Usuários Padrão

| Username | Password | Role | Descrição |
|----------|----------|------|-----------|
| `admin` | `admin123` | ROLE_ADMIN | Administrador do sistema |
| `user` | `user123` | ROLE_USER | Usuário com acesso limitado |

> 🔒 **Produção**: Altere as senhas padrão e use variáveis de ambiente!

---

## 🗄️ Banco de Dados

### Oracle Database FIAP

**Configuração de Conexão:**
```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: oracle.jdbc.OracleDriver
```

### Flyway Migrations

O sistema usa **Flyway** para gerenciar versões do schema:

**Migrations:**
- `V1__create_tables.sql`: Criação de 5 tabelas + índices
- `V2__seed_users.sql`: Inserção de usuários admin e user

**Comportamento:**
- ✅ Execução automática na inicialização
- ✅ Versionamento controlado
- ✅ Rollback em caso de erro

### Scripts de Teste

```bash
# Testar conexão com Oracle
./test-connection.sh

# Verificar status do Flyway
mvn flyway:info

# Limpar banco (desenvolvimento)
mvn flyway:clean
```

---

## 📦 Collections para Testes

**Arquivos disponíveis:**
- ✅ `ESG-Energia-API.postman_collection.json` (Postman v2.1.0)
- ✅ `ESG-Energia-API.insomnia.json` (Insomnia v4)
- ✅ `README-COLLECTIONS.md` (Documentação das collections)

**Conteúdo:**
- 🔐 2 endpoints de autenticação
- 🏢 6 endpoints de unidades
- 📈 4 endpoints de leituras
- ⚠️ 6 endpoints de alertas
- 📊 1 endpoint de indicadores
- 📅 8 endpoints de agendamentos
- **Total: 27 endpoints documentados**

**Como usar:**
1. Importe a collection no Postman ou Insomnia
2. Execute o login para obter o token JWT
3. Token é salvo automaticamente (Postman) ou manualmente (Insomnia)
4. Teste todos os endpoints com autenticação configurada

---

## 🐛 Troubleshooting

### Problemas Comuns

#### ❌ Erro: "Port 8080 already in use"
```bash
# macOS/Linux
lsof -ti:8080 | xargs kill -9

# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

#### ❌ Erro: "Oracle connection refused"
```bash
# Testar conectividade
./test-connection.sh

# Verificar credenciais no .env
cat .env | grep DATASOURCE
```

#### ❌ Erro: "JWT signature does not match"
```bash
# Verificar se o JWT_SECRET está em Base64
echo "sua-chave" | base64

# Atualizar no .env
JWT_SECRET=<base64_string>
```

#### ❌ Erro: "Flyway validation failed"
```bash
# Limpar e reexecutar migrations (CUIDADO: apaga dados!)
mvn flyway:clean
mvn spring-boot:run
```

#### ❌ Docker: "Cannot connect to Docker daemon"
```bash
# Verificar se Docker está rodando
docker ps

# Iniciar Docker Desktop (macOS)
open -a Docker

# Verificar versão do Docker Compose
docker compose version
```

### Logs e Debug

```bash
# Ver logs da aplicação
mvn spring-boot:run

# Ver logs do Docker
docker compose logs -f

# Ver apenas erros
docker compose logs -f | grep ERROR

# Acessar container
docker compose exec app bash
```

---

## 🛠️ Desenvolvimento

### Estrutura do Projeto

```
src/main/java/com/esg/energia/
├── config/                    # Configurações
│   ├── SecurityConfig.java   # Spring Security + JWT
│   ├── JwtTokenProvider.java # Geração/validação JWT
│   ├── JwtAuthFilter.java    # Filtro de autenticação
│   └── OpenApiConfig.java    # Swagger/OpenAPI
├── controller/                # REST Controllers
│   ├── AuthController.java
│   ├── UnidadeController.java
│   ├── LeituraController.java
│   ├── AlertaController.java
│   ├── IndicadorController.java
│   └── AgendamentoController.java
├── service/                   # Business Logic
│   ├── AuthService.java
│   ├── UnidadeService.java
│   ├── LeituraService.java
│   ├── AlertaService.java
│   ├── IndicadorService.java
│   └── AgendamentoService.java
├── repository/                # Data Access
│   ├── UsuarioRepository.java
│   ├── UnidadeRepository.java
│   ├── LeituraRepository.java
│   ├── AlertaRepository.java
│   └── AgendamentoRepository.java
├── entity/                    # JPA Entities
│   ├── Usuario.java
│   ├── Unidade.java
│   ├── Leitura.java
│   ├── Alerta.java
│   └── Agendamento.java
├── dto/                       # Data Transfer Objects
│   ├── LoginRequest.java
│   ├── UnidadeRequest.java
│   ├── LeituraRequest.java
│   └── ...
├── exception/                 # Error Handling
│   ├── GlobalExceptionHandler.java
│   └── ErrorResponse.java
└── util/                      # Utilities
    └── PasswordHashGenerator.java
```

### Tecnologias e Versões

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 17 | Linguagem base |
| **Spring Boot** | 3.2.0 | Framework web |
| **Spring Security** | 6.2.0 | Autenticação/Autorização |
| **Spring Data JPA** | 3.2.0 | Persistência de dados |
| **JJWT** | 0.12.3 | Geração/validação JWT |
| **Flyway** | 9.22.3 | Migrations de banco |
| **SpringDoc OpenAPI** | 2.3.0 | Documentação Swagger |
| **Oracle JDBC** | 23.3.0 | Driver Oracle |
| **Lombok** | 1.18.30 | Redução de boilerplate |
| **Maven** | 3.8+ | Gerenciamento de dependências |

### Boas Práticas Implementadas

- ✅ **Arquitetura em Camadas**: Controller → Service → Repository
- ✅ **DTOs**: Separação entre entidades e payloads de API
- ✅ **Exception Handling**: Tratamento global de erros
- ✅ **Validation**: Bean Validation com `@Valid`
- ✅ **Security**: JWT stateless, BCrypt, HTTPS-ready
- ✅ **Documentation**: Swagger/OpenAPI completo
- ✅ **Database Migrations**: Flyway versionado
- ✅ **Environment Variables**: Configuração externalizada
- ✅ **Docker**: Containerização multi-stage
- ✅ **Git Security**: Credentials fora do repositório

---

## 📈 Roadmap Futuro

### 🚀 Features Planejadas

- [ ] **Frontend Web**: Dashboard React/Angular
- [ ] **Análise Preditiva**: Machine Learning para prever consumo
- [ ] **Integração IoT**: MQTT, LoRaWAN, WebSockets
- [ ] **Relatórios PDF**: Geração automática de relatórios mensais
- [ ] **Notificações**: E-mail, SMS, Push notifications
- [ ] **Multi-tenant**: Suporte para múltiplas organizações
- [ ] **Auditoria**: Log completo de ações dos usuários
- [ ] **Backup Automático**: Rotina de backup do banco
- [ ] **Kubernetes**: Deploy em cluster K8s
- [ ] **CI/CD**: Pipeline automatizado com GitHub Actions

### 🔧 Melhorias Técnicas

- [ ] Testes unitários (JUnit 5 + Mockito)
- [ ] Testes de integração
- [ ] Cache com Redis
- [ ] Rate limiting
- [ ] API versioning
- [ ] GraphQL endpoint
- [ ] WebSocket para tempo real
- [ ] Observability (Prometheus + Grafana)

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👥 Autor

**Ryan Esteves**  
🎓 **RM559684** - FIAP 2025  
📧 Email: [seu-email@fiap.com.br]  
🔗 GitHub: [@estevesryan](https://github.com/estevesryan)

---

## 🙏 Agradecimentos

- **FIAP** pela infraestrutura Oracle
- **Spring Community** pela excelente documentação
- **Contributors** de todos os projetos open-source utilizados

---

## 📞 Suporte

### Documentação Adicional

- 📖 [README-COLLECTIONS.md](README-COLLECTIONS.md) - Como usar as collections
- 🔒 [SECURITY-FIX.md](SECURITY-FIX.md) - Guia de segurança
- 🐳 [docker-compose.yml](docker-compose.yml) - Configuração Docker
- 🗄️ [setup-oracle.sql](setup-oracle.sql) - Scripts de banco

### Links Úteis

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **Oracle FIAP**: https://www.fiap.com.br

---

<div align="center">

**⭐ Se este projeto foi útil, considere dar uma estrela no GitHub! ⭐**

**Desenvolvido com ❤️ para um futuro mais sustentável**

![ESG](https://img.shields.io/badge/ESG-Compliant-green)
![Status](https://img.shields.io/badge/Status-Production--Ready-brightgreen)

</div>
