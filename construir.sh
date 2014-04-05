#!/bin/bash

projeto=Estruturados
pacoteDoProjeto=estruturados
pacoteGeral=br.dominioL

class=class
# emma=emma
jar=jar
java=java
construcao=construcao

limpar() {
	echo ":limpar"
	rm -rf ${construcao}
	rm -rf ${class}
	# rm -rf ${emma}
}

criarEstrutura() {
	echo ":criarEstrutura"
	mkdir -p ${class}
	# mkdir -p ${emma}
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
	arquivosJava=$(find ${java} -name *.java)
	javac -classpath ${class}:${jar}/* -sourcepath ${java} -d ${class} -Werror -deprecation -g ${arquivosJava} -Xlint -Xmaxerrs 10 -Xmaxwarns 10 | less
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
	java -classpath ${class}:${jar}/* org.junit.runner.JUnitCore ${classesTestesJava}
}

analisar() {
	compilar
	echo ":analisar"
	# arquivosTestesJava=$(find ${java} -name *Teste*.java)
	# classesTestesJava=$(echo ${arquivosTestesJava} | sed -e s:${java}/::g -e s:^/::g -e "s:\s/: :g" -e s:/:.:g -e s:\.java::g -e s:[a-Z.]*figuracao[a-Z.]*::g)
	# java -classpath ${jar}/emma.jar emma instr -m overwrite -instrpath ${class}:${jar}/jUnit.jar:${jar}/hamcrest.jar -ix -junit* -ix -hamcrest* | less
	# java -classpath ${jar}/emma.jar:${class}:${jar}/jUnit.jar:${jar}/hamcrest.jar org.junit.runner.JUnitCore ${classesTestesJava} | less
	# java -classpath ${jar}/emma.jar emma instr -m copy -outdir ${emma} -instrpath ${class} | less
	# java -classpath ${jar}/emma.jar:${emma}:${class}:${jar}/jUnit.jar:${jar}/hamcrest.jar org.junit.runner.JUnitCore ${classesTestesJava} | less
	# java -classpath ${jar}/emma.jar emma report -r txt,html,xml -in coverage.em -in coverage.ec | less
	# mv coverage* ${construcao}
}

executar() {
	compilar
	echo ":executar"
	java -classpath ${class}:${jar}/* ${pacoteGeral}.${pacoteDoProjeto}.${projeto}
}

echo :${pacoteDoProjeto}
if [ -n "$1" ]
then
	$1
else
	construir
fi
