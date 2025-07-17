# FipeMatch

Projeto desenvolvido em Java com foco em consumo de API, manipulação de JSON e princípios de orientação a objetos.  
A aplicação simula uma interface de consulta de preços de veículos com base na tabela FIPE, utilizando a API pública da [Fipe](https://deividfortuna.github.io/fipe/).

---

## 🚀 Funcionalidades

- Listagem de marcas, modelos e anos de veículos
- Consulta de preço FIPE para o veículo selecionado
- Interface interativa via terminal
- Integração com API externa via `HttpClient`
- Leitura e parsing de JSON com `jackson-databind`

---

## 🛠️ Tecnologias utilizadas

- Java 8
- Spring-boot
-  [Jackson](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.15.2)
- API pública da FIPE
- Maven

---

## 📦 Como rodar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/Bartolace/java8-javaFipeMatch.git
cd java8-javaFipeMatch
```

### 2. Abrir na sua IDE preferida

---

### 3. Sincronizar as dependências

---

## 💡 Exemplo de uso

```
Digite o tipo do veículo 
   - carros
   - motos
   - caminhoes
> carros

Digite a código da marca:
> 23

Digite o modelo:
> onix

Digite o código do modelo que deseja consultar:
> 8828

==================== Avaliações ====================
ONIX SEDAN Plus LTZ 1.0 12V TB Flex Mec.  ano: 2020  valor: R$ 66.928,00  combustível: Gasolina
ONIX SEDAN Plus LTZ 1.0 12V TB Flex Mec.  ano: 2021  valor: R$ 70.052,00  combustível: Gasolina
ONIX SEDAN Plus LTZ 1.0 12V TB Flex Mec.  ano: 2022  valor: R$ 73.930,00  combustível: Gasolina
ONIX SEDAN Plus LTZ 1.0 12V TB Flex Mec.  ano: 2023  valor: R$ 78.433,00  combustível: Gasolina
```

---

Desenvolvido por [Gabriel Bartolace](https://github.com/Bartolace) 🚗