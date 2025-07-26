# CoinConvert

# 💱 Currency Converter - Conversor de Moedas em Java

Este é um projeto simples de **conversor de moedas** desenvolvido em **Java com Swing**, que utiliza a API [ExchangeRate-API](https://www.exchangerate-api.com/) para obter taxas de câmbio em tempo real.

## 🖥️ Tecnologias Utilizadas

- Java 8+
- Swing (interface gráfica)
- HTTP (HttpURLConnection)
- JSON (via org.json)

---

## 🚀 Funcionalidades

- Conversão entre múltiplas moedas: USD, EUR, BRL, GBP, JPY, CAD, AUD, CNY, INR e MXN
- Interface gráfica amigável e responsiva
- Obtenção das taxas de câmbio atualizadas via API externa
- Validação de campos e mensagens de erro ao usuário

---

## 🧪 Exemplo de Uso

```bash

Digite o valor: 100
De: BRL
Para: USD
Resultado: 100.00 BRL = 20.15 USD
Última atualização: Sat Jul 26 11:00:00 BRT 2025

```

---
## 📦 Como Executar Localmente

##### 1. Clone o repositório:
```bash

bash
Copiar
Editar
git clone https://github.com/seu-usuario/currency-converter-java.git
cd currency-converter-java

```

##### 2. Compile e execute o projeto (no terminal ou usando o IntelliJ/Eclipse):
```bash
 
javac -cp . CurrencyConverter.java
java -cp . CurrencyConverter

```

# 🔐 Configuração da API

Este projeto utiliza a ExchangeRate-API. Para funcionar corretamente, você precisa de uma chave de API.

Acesse: https://www.exchangerate-api.com/

Crie uma conta gratuita

Copie sua chave e substitua no código:

- java
- Copiar
- Editar
```bash
 
private static final String API_KEY = "SUA_CHAVE_AQUI";

```

# 📁 Estrutura do Projeto
```bash
currency-converter-java/
├── CurrencyConverter.java
├── README.md
```
---

# 🙋‍♂️ Autor
## Desenvolvido por João P.
 📧 [E-Mail](mendesserafimjoaopedro@gmail.com)
 🔗 [Linkedin](https://www.linkedin.com/in/joao-pedro-serafim-engsof/)

