#!/usr/bin/env bash
npm --prefix ./src/main/node install
npm --prefix ./src/main/node run build
mvn package
