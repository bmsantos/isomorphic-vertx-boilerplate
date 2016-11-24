# isomorphic-vertx-boilerplate

Isomorphic React / Redux / Vertx app boilerplate with node.js

### Features

1. Decouples frontend developement from backend. 
1. Server side rendering using Nashorn.
1. Server built with Vert.x.
1. JavaScript application built with react, redux, react-router.
1. JavaScript packaged with Webpack.
1. Frontend node express development server with hot reloading.

### Build

Requires npm installed

Full build - Installs node dependencies and builds app 

```bash
mvn package -Dfull
```

App build - just builds app 

```bash
mvn package
```

### Run frontend node server

```bash
./run-node.sh
```

### Run deployment Vert.x micro-service

```bash
./run-vertx.sh
```

This template was based on the work of [Arran White](https://github.com/arrwhidev/nashorn-webpack-react-redux-boilerplate). 
