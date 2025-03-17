#!/bin/bash

# Criar diretório bin se não existir
mkdir -p bin

# Compilar todas as classes Java
javac -d bin src/main/java/model/*.java src/main/java/Main.java

# Criar o arquivo JAR
jar cfm CadastroPOO.jar manifest.txt -C bin .

# Executar o programa
java -jar CadastroPOO.jar 