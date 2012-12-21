#!/bin/bash

fontes=fontes
fontesJava=${fontes}/java

testes=testes
testesJava=${testes}/java

binarios=binarios
binariosJava=${binarios}/class

bibliotecas=bibliotecas
bibliotecasJava=${bibliotecas}/jar
bibliotecaJUnit=${bibliotecasJava}/jUnit.jar

construcao=construcao
arquivosFontesJava=${construcao}/arquivosFontesJava.txt
arquivosTestesJava=${construcao}/arquivosTestesJava.txt
relatorios=${construcao}/relatorios
relatorioTestesJava=${relatorios}/testesJava.txt
relatorioTestesJavaScript=${relatorios}/testesJavaScript.txt

recursos=recursos

limpar() {
	echo ":limpar";
	rm -rf ${binarios};
	rm -rf ${construcao};
}

criarDiretorios() {
	echo ":criarDiretorios"
	mkdir -p ${fontes};
	mkdir -p ${fontesJava};
	mkdir -p ${testes};
	mkdir -p ${testesJava};
	mkdir -p ${binarios};
	mkdir -p ${binariosJava};
	mkdir -p ${bibliotecas};
	mkdir -p ${bibliotecasJava};
	mkdir -p ${construcao};
	mkdir -p ${relatorios};
}

compilarFontesJava() {
	echo ":compilarFontesJava";
	find ${fontesJava} -name *.java > ${arquivosFontesJava};
	javac -g -deprecation -Werror -classpath ${bibliotecaJUnit}:${binariosJava} -sourcepath ${fontesJava} -d ${binariosJava} @${arquivosFontesJava};
}

compilarTestesJava() {
	echo ":compilarTestesJava";
	find ${testesJava} -name *.java > ${arquivosTestesJava};
	javac -g -deprecation -Werror -classpath ${bibliotecaJUnit}:${binariosJava} -sourcepath ${fontesJava}:${testesJava} -d ${binariosJava} @${arquivosTestesJava};
}

testar() {
	echo ":testar";
	classesTestesJava=$(sed -e s:${testesJava}::1 -e s:/:.:g -e s:.java:: -e s:[a-Z.]*figuracao[a-Z.]*:: -e s:^.:: < ${arquivosTestesJava});
	java -classpath ${bibliotecaJUnit}:${binariosJava} org.junit.runner.JUnitCore ${classesTestesJava} > ${relatorioTestesJava};
}

limpar;
criarDiretorios;
compilarFontesJava;
compilarTestesJava;
testar;
