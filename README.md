# demo_liquibase
This project is a demo of the basic setup for a Liquibase extension for Maven. The Java project is build using Spring Boot.


## Setup
To use the Liquibase extension for Maven we'll need to include the plugin into the `pom.xml` file.

```
<plugin>  
  <groupId>org.liquibase</groupId>
  <artifactId>liquibase-maven-plugin</artifactId>
  <version>4.2.2</version>
</plugin> 
```

The plugin itself needs to be configured, you can do the configuration directly in the `pom.xml` or you can separate it into an external `liquibase.properties` file.
To create a separate external `liquibase.properties` file you'll need to tell the location of it to the plugin.
```
<plugin>
  <groupId>org.liquibase</groupId>
  <artifactId>liquibase-maven-plugin</artifactId>
  <version>4.3.5</version>
  
  <configuration>
     <propertyFile>src/main/resources/liquibase.properties</propertyFile>
  </configuration>
</plugin>
```

To let Liquibase to be able to interact with your database instance, you'll need to provide the Liquibase plugin with the appropriate connector dependency. For this project 
we'll use the MySQL connector.
```
<plugin>
  <groupId>org.liquibase</groupId>
  <artifactId>liquibase-maven-plugin</artifactId>
  <version>4.3.5</version>
  
  <configuration>
     <propertyFile>src/main/resources/liquibase.properties</propertyFile>
  </configuration>
  
  <dependencies>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.27</version>
    </dependency>
  </dependencies>
</plugin>
```


## Configuration File
Liquibase needs some basic configuration such as connection details.
```
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost/demo_liquibase?createDatabaseIfNotExist=true
username=root
password=
```


## Using Liquibase Maven Extension
### ChangeLogs
Liquibase works on the concept of Changelogs, to learn more details about Changelogs you can visit the official [documentation](https://docs.liquibase.com/concepts/changelogs/working-with-changelogs.html).
Liquibase needs to know a main Changelog file to execute updates from, you'll need to specify its location in the `liquibase.properties` file.
```
changeLogFile=src/main/resources/db/changelog/changelog-master.xml
```

Liquibase changelogs can be in many formats, the default is XML format but Liquibase also supports native SQL, YAML, and JSON.


### Update
To apply the changelogs you'll need to execute the `mvn liquibase:update` command, it'll scan the master changelog file and execute any changesets that haven't been executed before.


### Generate ChangeLog
Liquibase provide a powerful command to generate a changelog to build a database to its current state using the `mvn liquibase:generateChangeLog` command, you'll need to specify 
the ChangeLog output file in the `liquibase.properties` to define where it'll be generated. The generated file will be in the format that you specify, which could be any of the 
formats that Liquibase supports.
```
outputChangeLogFile=src/main/resources/db/changelog/outputChangeLog.xml
```

Or you could specify the output file right in the command as a parameter `mvn liquibase:generateChangeLog -Dliquibase.outputChangeLogFile=dboutput.xml`


### History
To see a history of the executed changelogs you can use the `mvn liquibase:history` command. It'll look something like this.
```
Reading from DATABASECHANGELOG
- Database updated at 1/27/22 2:02 PM. Applied 7 changeSet(s) in 0.0s, DeploymentId: 3266975019
  src/main/resources/db/changelog/changes/dev0/dev0.0_create_user_table.xml::dev0.0::Reyhan Patria
  src/main/resources/db/changelog/changes/dev0/dev0.1_add_data_to_user_table.xml::dev0.0::Reyhan Patria
  src/main/resources/db/changelog/changes/dev0/dev0.2_create_product_table.xml::dev0.0::Reyhan Patria
  src/main/resources/db/changelog/changes/dev0/dev0.3_insert_product_data.sql::dev0.3::Reyhan Patria
  src/main/resources/db/changelog/changes/dev0/dev0.4_create_transaction_table.sql::dev0.4::Reyhan Patria
  src/main/resources/db/changelog/changes/dev0/dev0.5_insert_transaction_data.sql::dev0.5::Reyhan Patria
  src/main/resources/db/changelog/changes/dev0/dev0.6_insert_transaction_details_data.xml::dev0.0::Reyhan Patria  
```


### Other Liquibase Commands
The standard Liquibase provides many powerful commands that can be used in various ways, I encourage you to explore about it the [documentation](https://docs.liquibase.com/commands/home.html)
