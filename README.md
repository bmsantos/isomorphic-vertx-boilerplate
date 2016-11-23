# isomorphic-vertx-boilerplate

Isomorphic React / Redux / Vertx app boilerplate with node.js

### Build

Requires npm installed

```bash
./build-node.sh
mvn package
```

### Run frontend node server

```bash
npm --prefix ./src/main/node run start
```

### Run deployment Vert.x micro-service

```bash
java -jar target/isomorphic-vertx-boilerplate-fat.jar
```
