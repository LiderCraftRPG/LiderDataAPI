
# LiderDataAPI

LiderDataAPI é o plugin responsável por gerenciar as conexões com o banco de dados, e tem como objetivo unificar o acesso e como objetivo futuro coletar métricas de dados e ganhos de performance nas transações.


# Instalação Servidor

+ Coloque o plugin na pasta plugins do servidor
+ Inicio o servidor
+ Ao iniciar, irá acontecer um erro de banco de dados.
+ Altere os dados de conexão após primeiro início no arquivo plugins > LiderDataAPI > config.yml

# Instalação API

- Copie o arquivo JAR para a pasta resources em seu código
- Adicione a dependência no arquivo POM.XML apontando para o arquivo na pasta resources

- Exemplo:
    ```xml
    <dependency>
        <groupId>com.lidercraft.liderdataapi</groupId>
        <artifactId>LiderDataAPI</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/src/main/resources/LiderDataAPI-1.0-SNAPSHOT.jar</systemPath>
    </dependency>
    ```
## Uso/Exemplos

```java
    try (Connection conn = LiderDataAPI.getDatabase().getConnection()) {
        String sql = "SELECT * FROM LIDERECONOMIA_ACCOUNT";
        ResultSet rs = conn.prepareStatement(sql).executeQuery();

        while (rs.next()) {
            String player = rs.getString(1);
            double balance = rs.getDouble(2);
            ...
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
```


## Roadmap

- Implementar pool de conexões
- QueryBuilder
- Anti SqlInjection
- Inventory De/Serializer
- Item De/Serializer
- Location De/Serializer

