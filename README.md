# Digital Wallet API

API Rest para um sistema de Carteira Digital, oferecendo funcionalidades como depósitos, transferências e pagamentos.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **Docker**
- **Swagger**
- **java-jwt (com.auth0:java-jwt:4.4.0):** Implementação de JWT para autenticação e autorização.
- **Liquibase (org.liquibase:liquibase-core):** Gerenciamento de versionamento e migrações de banco de dados.

---

## 🏗️ Arquitetura do Projeto

A aplicação segue o padrão **MVC (Model-View-Controller)**, promovendo uma separação clara de responsabilidades:

- **Model:** Representa os dados e as regras de negócio.
- **DTOs (Data Transfer Objects):** Utilizados para entrada (**input**) e saída (**output**) de dados na API, garantindo a separação entre a estrutura interna da aplicação e os dados expostos aos consumidores da API.
- **View:** (No caso de APIs REST, a visualização se dá através de respostas JSON).
- **Controller:** Manipula as requisições HTTP, interagindo com os serviços e retornando respostas apropriadas.
- **Service:** Contém a lógica de negócios e interage com os repositórios.
- **Repository:** Responsável pela persistência e acesso aos dados.

Esse padrão facilita a manutenção, testes e escalabilidade da aplicação, garantindo também maior segurança e controle sobre os dados expostos.

---

## 📦 Como Rodar o Projeto Localmente

### 1. Clone o repositório

```bash
git clone <URL_DO_REPOSITORIO>
cd digital-wallet-api
```

### 2. Configure o arquivo `.env`

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```env
DB_USERNAME=seu_usuario_db
DB_PASSWORD=sua_senha_db
SPRING_PROFILES_ACTIVE=dev
DB_URL=jdbc:postgresql://db:5432/app_db
JWT_SECRET_KEY=sua_chave_jwt
CORS_ORIGINS=http://localhost:4200
MAIL_HOST=smtp.exemplo.com
MAIL_PASSWORD=sua_senha_email
MAIL_PORT=587
PROVIDER_AGENCY=0001
PROVIDER_BANK=000
PROVIDER_NAME=DigitalWalletProvider
```

> 💡 **Configuração no IntelliJ IDEA**:  
> Para rodar o projeto diretamente no IntelliJ IDEA, configure as variáveis de ambiente em:  
> **Run > Edit Configurations > Environment variables**.

### 3. Compile o projeto

```bash
./mvnw clean package -DskipTests
```

### 4. Suba o ambiente com Docker Compose

```bash
docker-compose up --build
```

A aplicação estará disponível em: `http://localhost:8080`

### 🔗 **Documentação Swagger:**
Acesse a documentação interativa da API em:  
`http://localhost:8080/swagger-ui/index.html#/`

> 🔒 **Observação:** As rotas do Swagger e documentação da API estão públicas, conforme configurado em:
> ```java
> .requestMatchers(HttpMethod.GET, "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
> ```

---

## 🐳 Como Gerar a Imagem Docker e Fazer o Build

1. **Gerar o JAR do projeto:**

```bash
./mvnw clean package -DskipTests
```

2. **Gerar a imagem Docker:**

```bash
docker build -t digital-wallet-api .
```

3. **Rodar o container:**

```bash
docker run -p 8080:8080 --env-file .env digital-wallet-api
```

---

## ⚙️ Perfis da Aplicação (`SPRING_PROFILES_ACTIVE`)

A aplicação utiliza perfis do Spring para gerenciar configurações de ambiente:

### 1. **application.yaml** (Padrão)
Contém configurações comuns a todos os ambientes, incluindo:
- Context path.
- Configuração do datasource (driver e pool HikariCP).
- Integração com **Liquibase**.
- Configurações de e-mail e segurança JWT (**java-jwt**).

### 2. **application-dev.yaml** (Desenvolvimento)
- Mostra SQL no console.
- Configuração de banco local com variáveis `DB_URL_DEV`, `DB_USERNAME_DEV` e `DB_PASSWORD_DEV`.
- CORS configurado para ambiente local (`CORS_ORIGINS_DEV`).

### 3. **application-prod.yaml** (Produção)
- Banco de dados configurado com `DB_URL`, `DB_USERNAME` e `DB_PASSWORD`.
- CORS configurado para ambiente de produção (`CORS_ORIGINS`).

> 🔄 **Como definir o perfil ativo**:
> - **Ambiente local:** `SPRING_PROFILES_ACTIVE=dev`
> - **Produção:** `SPRING_PROFILES_ACTIVE=prod`

O perfil `SPRING_PROFILES_ACTIVE` é essencial para a correta configuração do ambiente, determinando quais propriedades e comportamentos serão aplicados durante a execução.

---

## 📝 Licença

Distribuído sob a licença **MIT**. Veja [LICENSE](LICENSE) para mais informações.

---

## 🤝 Contribuições

Contribuições são bem-vindas! Para contribuir:

1. Fork este repositório.
2. Crie uma branch com sua feature (`git checkout -b minha-feature`).
3. Commit suas alterações (`git commit -m 'Adiciona nova feature'`).
4. Push para a branch (`git push origin minha-feature`).
5. Abra um Pull Request.

---

## 💬 Contato

Se tiver alguma dúvida ou sugestão, sinta-se à vontade para abrir uma issue ou pull request. 🚀

