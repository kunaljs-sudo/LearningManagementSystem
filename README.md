This file can be used as a template for initializing and running spring projects.

What's included:

1. Gradle file created from start.spring.io
2. Other dependencies like Mongo.
3. Spring Tests
4. CRUD operation on data

Usage -

1. To build the repository -

From the repository root,

1. run `./gradlew build test`run the build
2. run `./gradlew bootjar` to create executable jar. The jar will be located inside build directories.

## Need to be added:

Since this application uses cloud MySQL server from "https://aiven.io" as database and I have removed mysql-config.properties file which contain hostname, portname, username, password, etc. of database, if you want to use mysql cloud you can use Aven.io add the file in main/resources
FileName should be exact same as I have provided this filename to LmsApplication.java as property file, if you wish to change it change in LmsApplication.java too

#### mysql-config.properties

- mysql.host=hostname
- mysql.port=portname
- mysql.database=dbname
- mysql.username=username
- mysql.password=password

## PostMan Collection

- [PostManCollection.json](https://github.com/kunaljs-sudo/LeaderBoard/blob/main/LeaderBoard.postman_collection4.json)
