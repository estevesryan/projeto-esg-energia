#!/bin/bash

echo "🔍 Teste de Conexão - Oracle FIAP"
echo "=================================="
echo ""

# Informações do banco
DB_HOST="oracle.fiap.com.br"
DB_PORT="1521"
DB_USER="RM559684"
DB_SID="ORCL"

echo "📊 Configuração:"
echo "  Host: $DB_HOST"
echo "  Port: $DB_PORT"
echo "  SID: $DB_SID"
echo "  User: $DB_USER"
echo ""

# Teste 1: Ping
echo "1️⃣ Testando conectividade (ping)..."
if ping -c 3 $DB_HOST > /dev/null 2>&1; then
    echo "   ✅ Host acessível"
else
    echo "   ⚠️  Host não responde ao ping (pode ser normal se ICMP estiver bloqueado)"
fi
echo ""

# Teste 2: Porta
echo "2️⃣ Testando porta $DB_PORT..."
if command -v nc &> /dev/null; then
    if nc -z -w5 $DB_HOST $DB_PORT 2>/dev/null; then
        echo "   ✅ Porta $DB_PORT está aberta"
    else
        echo "   ❌ Porta $DB_PORT não está acessível"
    fi
elif command -v telnet &> /dev/null; then
    (echo quit) | telnet $DB_HOST $DB_PORT 2>&1 | grep -q "Connected" && \
        echo "   ✅ Porta $DB_PORT está aberta" || \
        echo "   ❌ Porta $DB_PORT não está acessível"
else
    echo "   ⚠️  nc ou telnet não disponível, pulando teste de porta"
fi
echo ""

# Teste 3: Variáveis de ambiente
echo "3️⃣ Verificando variáveis de ambiente..."
if [ -z "$JAVA_HOME" ]; then
    echo "   ⚠️  JAVA_HOME não está configurado"
else
    echo "   ✅ JAVA_HOME: $JAVA_HOME"
fi

if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
    echo "   ✅ Java: $JAVA_VERSION"
else
    echo "   ❌ Java não encontrado no PATH"
fi

if command -v mvn &> /dev/null; then
    MVN_VERSION=$(mvn -version | head -n 1 | cut -d' ' -f3)
    echo "   ✅ Maven: $MVN_VERSION"
else
    echo "   ❌ Maven não encontrado no PATH"
fi
echo ""

# Teste 4: Arquivo de configuração
echo "4️⃣ Verificando configuração da aplicação..."
if [ -f "src/main/resources/application.yml" ]; then
    echo "   ✅ application.yml encontrado"
    
    if grep -q "oracle.fiap.com.br" src/main/resources/application.yml; then
        echo "   ✅ Configurado para Oracle FIAP"
    else
        echo "   ⚠️  Não configurado para Oracle FIAP"
    fi
    
    if grep -q "RM559684" src/main/resources/application.yml; then
        echo "   ✅ Usuário RM559684 configurado"
    else
        echo "   ⚠️  Usuário não configurado corretamente"
    fi
else
    echo "   ❌ application.yml não encontrado"
fi
echo ""

# Resumo
echo "=================================="
echo "📋 Resumo:"
echo ""
echo "Para executar o projeto:"
echo "  1. mvn clean install"
echo "  2. mvn spring-boot:run"
echo ""
echo "Para acessar a aplicação:"
echo "  http://localhost:8080/swagger-ui.html"
echo ""
echo "Credenciais de login:"
echo "  Admin: admin / admin123"
echo "  User: user / user123"
echo ""
echo "=================================="
