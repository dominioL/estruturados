#!/bin/bash

projeto=Estruturados
pacoteDoProjeto=estruturados
pacoteGeral=br.dominioL

class=class
jar=jar
java=java
construcao=construcao

contrucaoCompilacao=${construcao}/compilacao.txt

limpar() {
	echo ":limpar"
	rm -rf ${construcao}
}

criarEstrutura() {
	echo ":criarEstrutura"
	mkdir -p ${class}
	mkdir -p ${jar}
	mkdir -p ${java}
	mkdir -p ${construcao}
}

adicionarBibliotecas() {
	echo ":adicionarBibliotecas"
}

compilar() {
	limpar
	criarEstrutura
	adicionarBibliotecas
	echo ":compilar"
	touch ${contrucaoCompilacao}
	arquivosJava=$(find ${java} -name *.java)
	javac -classpath ${jar}/*:${class} -sourcepath ${java} -d ${class} -Werror -deprecation -g ${arquivosJava} -Xlint -Xmaxerrs 10 -Xmaxwarns 10 &> ${contrucaoCompilacao}
	less ${contrucaoCompilacao}
}

construir() {
	compilar
	echo ":construir"
	jar cf ${construcao}/${pacoteDoProjeto}.jar -C ${class} .
}

testar() {
	compilar
	echo ":testar"
	arquivosTestesJava=$(find ${java} -name *Teste*.java)
	classesTestesJava=$(echo ${arquivosTestesJava} | sed -e s:${java}/::g -e s:^/::g -e "s:\s/: :g" -e s:/:.:g -e s:\.java::g -e s:[a-Z.]*figuracao[a-Z.]*::g)
	java -classpath ${jar}/*:${class} org.junit.runner.JUnitCore ${classesTestesJava}
}

executar() {
	compilar
	echo ":executar"
	java -classpath ${jar}/*:${class} ${pacoteGeral}.${pacoteDoProjeto}.${projeto}
}

echo :${pacoteDoProjeto}
if [ -n "$1" ]
then
	$1
else
	construir
fi
