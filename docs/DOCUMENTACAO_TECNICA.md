# Schedula — Documentação Técnica

## 1. Arquitetura geral

O Schedula é uma aplicação desktop Java construída com:

- **Interface gráfica:** Java Swing (com SwingX, AbsoluteLayout, AppFramework)
- **Persistência:** Hibernate 3 (mapeamento XML `.hbm.xml`)
- **Banco de dados:** MySQL 5.x (via MySQL Connector/J 5.1.5)
- **Relatórios:** JasperReports 3.0 + iText 1.3.1 + Apache POI 3.0.1
- **Build:** Apache Ant (integrado ao NetBeans IDE)

A arquitetura segue o padrão **DAO (Data Access Object)** com separação entre entidades, camada de acesso a dados e interface gráfica.

## 2. Pacotes e responsabilidades

| Pacote / Diretório | Responsabilidade |
|---|---|
| `entity/` | Entidades (POJOs) e seus mapeamentos Hibernate (`.hbm.xml`) |
| `model/` | DAOs, `HibernateUtil`, `HibernateDAOFactory`, `SchedulaDAOFactory`, `Seguranca` |
| `reports/` | Templates JasperReports (`.jrxml` e `.jasper` compilados) |
| `resources/` | Imagens e ícones da interface |
| `versoes/` | Scripts de migração do sistema legado ATDB (Firebird → MySQL) |
| Raiz do pacote (`schedula/`) | Telas Swing (`Cadastro*.java`, `Pesquisa*.java`), `SchedulaApp.java`, configuração |

### Classes principais na raiz do pacote

| Classe | Descrição |
|---|---|
| `SchedulaApp.java` | Classe principal, ponto de entrada da aplicação |
| `SchedulaView.java` | Janela principal (MDI) |
| `Autenticacao.java` | Tela de login |
| `TrocaSenha.java` | Tela de alteração de senha |
| `SchedulaConfigForm.java` | Tela de configuração de conexão com banco |
| `SchedulaConfig.java` | Entidade de configuração |
| `SchedulaConfigDAOXml.java` | DAO para leitura/escrita do XML de configuração |
| `SchedulaInstalacao.java` | Classe de implantação inicial (criação de dados base) |
| `Imagens.java` | Utilitário de carregamento de recursos visuais |
| `CadastroProcesso.java` | Formulário de processos trabalhistas |
| `CadastroAndamentoProcesso.java` | Formulário de acompanhamento/andamento |
| `CadastroPessoaFisica.java` | Formulário de reclamantes |
| `CadastroPessoaJuridica.java` | Formulário de reclamados |
| `CadastroAdvogados.java` | Formulário de advogados |
| `CadastroComarca.java` | Formulário de comarcas |
| `CadastroVara.java` | Formulário de varas |
| `CadastroEstadoCivil.java` | Formulário de estados civis |
| `CadastroAudienciaTipo.java` | Formulário de tipos de audiência |
| `CadastroUsuarioHabilitacoes.java` | Formulário de usuários e permissões |
| `RelatorioPautaDiaria.java` | Geração do relatório de pauta diária |
| `Pesquisa*.java` | Telas de pesquisa/busca para cada entidade |

## 3. Modelo de dados

O sistema possui 16 entidades mapeadas via Hibernate (arquivos `.hbm.xml`):

| Entidade | Descrição | Relacionamentos principais |
|---|---|---|
| `Processo` | Processo trabalhista | → `PessoaFisica` (reclamante), → `PessoaJuridica` (reclamado), → `Advogado`, → `Vara` |
| `Acompanhamento` | Andamento/movimentação processual | → `Processo`, → `AcompanhamentoTipo`, → `AudienciaTipo` |
| `AcompanhamentoTipo` | Tipo de acompanhamento (audiência, despacho, etc.) | — |
| `AudienciaTipo` | Tipo de audiência | — |
| `AcordoParcela` | Parcela de acordo trabalhista | → `Acompanhamento` |
| `Pessoa` | Classe base de pessoa (herança) | → `Uf` |
| `PessoaFisica`* | Reclamante (pessoa física) | herda `Pessoa`, → `EstadoCivil` |
| `PessoaJuridica`* | Reclamado (pessoa jurídica) | herda `Pessoa` |
| `Advogado` | Advogado responsável | → `Uf` (OAB), → `EstadoCivil` |
| `Comarca` | Comarca judiciária | — |
| `Vara` | Vara trabalhista | → `Comarca` |
| `Uf` | Unidade federativa | — |
| `EstadoCivil` | Estado civil | — |
| `Usuario` | Usuário do sistema | — |
| `UsuarioDireito` | Permissão de acesso por funcionalidade | → `Usuario` |
| `Schedula` | Configuração/metadados do sistema | — |
| `SchedulaRotina` | Rotinas agendadas do sistema | — |
| `TipoDeAcompanhamento` | Categorização adicional de acompanhamentos | — |

> \* `PessoaFisica` e `PessoaJuridica` usam herança mapeada em `Pessoa.hbm.xml`.

## 4. Camada DAO

### Padrão

O projeto utiliza o padrão **Abstract Factory** para DAOs:

- `SchedulaDAOFactory` — fábrica abstrata que expõe métodos `getXxxDAO()` para cada entidade
- `HibernateDAOFactory` — implementação concreta usando Hibernate
- `HibernateUtil` — gerencia `SessionFactory` e sessões do Hibernate

### DAOs disponíveis

| DAO | Entidade |
|---|---|
| `ProcessoDAO` | `Processo` |
| `AcompanhamentoDAO` | `Acompanhamento` |
| `AcompanhamentoTipoDAO` | `AcompanhamentoTipo` |
| `AcordoParcelaDAO` | `AcordoParcela` |
| `AdvogadoDAO` | `Advogado` |
| `AudienciaTipoDAO` | `AudienciaTipo` |
| `ComarcaDAO` | `Comarca` |
| `EstadoCivilDAO` | `EstadoCivil` |
| `PessoaDAO` | `Pessoa` |
| `PessoaFisicaDAO` | `PessoaFisica` |
| `PessoaJuridicaDAO` | `PessoaJuridica` |
| `SchedulaDAO` | `Schedula` |
| `SchedulaRotinaDAO` | `SchedulaRotina` |
| `UfDAO` | `Uf` |
| `UsuarioDAO` | `Usuario` |
| `UsuarioDireitoDAO` | `UsuarioDireito` |
| `VaraDAO` | `Vara` |

Os DAOs herdam de `GenericHibernateDAO` (definido no pacote `br.com.perettis.gets.dao`), que fornece operações CRUD genéricas via Hibernate.

## 5. Segurança

- **Autenticação:** tela de login (`Autenticacao.java`) valida usuário e senha
- **Hash de senhas:** MD5 (classe `Seguranca.java` no pacote `model/`)
- **Controle de permissões:** entidade `UsuarioDireito` associa cada `Usuario` a permissões de acesso por funcionalidade (tela `CadastroUsuarioHabilitacoes`)
- **Troca de senha:** tela dedicada (`TrocaSenha.java`)

## 6. Relatórios

- **Engine:** JasperReports 3.0
- **Templates:** arquivos `.jrxml` no diretório `reports/`
- **Relatório principal:** Pauta Diária (`RelatorioPautaDiaria.java`) — lista compromissos e audiências filtrados por data
- **Exportação:** PDF (via iText 1.3.1) e Excel (via Apache POI 3.0.1)

## 7. Migração legada (ATDB → Schedula)

O sistema inclui 4 scripts de migração para importar dados do sistema anterior **ATDB** (Firebird):

| Script | Dados importados |
|---|---|
| `doAdtb_Advogados.java` | Tabela `ADVOGADOS` → entidade `Advogado` |
| `doAdtb_Requerentes.java` | Tabela `RECLAMANTES` → entidade `PessoaFisica` |
| `doAdtb_Requeridos.java` | Tabela `RECLAMADOS` → entidade `PessoaJuridica` |
| `doAdtb_Processos.java` | Tabelas `PROCESSOS` e `ANDAMENTO` → entidades `Processo`, `Acompanhamento`, `AcordoParcela` |

A tela `doAdtb.java`/`doAdtb.form` orquestra a execução dos scripts na ordem correta.

> **Nota:** esses scripts exigem o driver Firebird (`firebirdsql-full.jar`, incluído em `dist/lib/`) e acesso ao banco ATDB original. Os placeholders de conexão devem ser preenchidos antes do uso.

## 8. Build e distribuição

### Apache Ant

O projeto usa **Apache Ant** como sistema de build, integrado ao **NetBeans IDE**:

- `build.xml` — script principal de build
- `nbproject/` — configurações do NetBeans (projeto, classpath, etc.)

### Distribuição

O diretório `dist/` contém o JAR compilado pronto para execução:

```
dist/
├── schedula.jar      # Aplicação principal
└── lib/              # 24 bibliotecas de dependência
```

Para executar: `java -jar dist/schedula.jar`

## 9. Dependências

| Biblioteca | Versão | Finalidade |
|---|---|---|
| `hibernate3.jar` | 3.x | ORM — mapeamento objeto-relacional |
| `mysql-connector-java` | 5.1.5 | Driver JDBC para MySQL |
| `firebirdsql-full.jar` | — | Driver JDBC para Firebird (migração legada) |
| `jasperreports` | 3.0.0 | Engine de relatórios |
| `itext` | 1.3.1 | Geração de PDF (usado pelo JasperReports) |
| `poi` | 3.0.1 | Geração de Excel (usado pelo JasperReports) |
| `appframework` | 1.0.3 | Swing Application Framework (JSR-296) |
| `swing-worker` | 1.1 | SwingWorker backport |
| `swingx.jar` | — | Componentes Swing estendidos |
| `beansbinding` | 1.2.1 | Beans Binding (JSR-295) |
| `AbsoluteLayout.jar` | — | Layout manager do NetBeans |
| `br.com.perettis.gets.jar` | — | Biblioteca interna (DAOs genéricos, filtros, utilitários) |
| `antlr` | 2.7.6 | Parser (dependência do Hibernate) |
| `asm.jar` | — | Bytecode manipulation (dependência do Hibernate) |
| `asm-attrs.jar` | — | ASM attributes (dependência do Hibernate) |
| `cglib` | 2.1.3 | Geração de proxies (dependência do Hibernate) |
| `commons-beanutils.jar` | — | Utilitários JavaBeans (Apache Commons) |
| `commons-collections` | 2.1.1 | Coleções estendidas (Apache Commons) |
| `commons-logging` | 1.0.4 | Abstração de logging (Apache Commons) |
| `dom4j` | 1.6.1 | Parser XML (dependência do Hibernate) |
| `ehcache` | 1.2.3 | Cache de segundo nível (Hibernate) |
| `jdbc2_0-stdext.jar` | — | Extensões JDBC 2.0 |
| `jdom.jar` | — | Manipulação XML |
| `jta.jar` | — | Java Transaction API |

## 10. Configuração do banco de dados

### Arquivos de configuração

| Arquivo | Descrição |
|---|---|
| `src/.../model/hibernate.cfg.xml` | Configuração do Hibernate (conexão, dialeto, mapeamentos) |
| `env/CONF/SchedulaConfig.xml` | Configuração externa do Schedula (lida pelo `SchedulaConfigDAOXml`) |
| `env/CONF/SchedulaConfig_EXEMPLO_MYSQL5.xml` | Exemplo de configuração para MySQL 5 |

### Primeiros passos

1. Instale o MySQL 5.x e crie o banco `schedula`
2. Preencha os placeholders nos arquivos de configuração (host, usuário, senha)
3. Execute a aplicação — o Hibernate criará as tabelas automaticamente (`hbm2ddl`)
4. A classe `SchedulaInstalacao.java` pode ser usada para popular dados iniciais (UFs, estados civis, tipos de audiência, etc.)

### Alternativa: tela de configuração

Na primeira execução, se o arquivo `SchedulaConfig.xml` não existir ou não tiver dados válidos, a tela `SchedulaConfigForm` permite configurar a conexão interativamente.
