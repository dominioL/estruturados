#!/bin/bash

projeto=Estruturados
pacoteDoProjeto=estruturados
pacoteGeral=br.dominioL

class=class
cobertura=cobertura
jar=jar
java=java
construcao=construcao

limpar() {
	echo ":limpar"
	rm -rf ${construcao}
	rm -rf ${class}
	rm -rf ${cobertura}
}

criarEstrutura() {
	echo ":criarEstrutura"
	mkdir -p ${class}
	mkdir -p ${cobertura}
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
	# java \
	# 	-classpath ${jar}/cobertura.jar:${jar}/asm.jar:${jar}/asmAnalysis.jar:${jar}/asmUtil.jar:${jar}/asmTree.jar:${jar}/asmCommons.jar:${jar}/log4j.jar:${jar}/oro.jar \
	# 	net.sourceforge.cobertura.instrument.Main \
	# 	--datafile ${construcao}/cobertura.ser \
	# 	--destination ${cobertura} \
	# 	classes ${class}
	# java \
	# 	-classpath 
	# 	-classpath ${jar}/cobertura.jar:${jar}/asm.jar:${jar}/asmAnalysis.jar:${jar}/asmUtil.jar:${jar}/asmTree.jar:${jar}/asmCommons.jar:${jar}/log4j.jar:${jar}/oro.jar \
	# 	net.sourceforge.cobertura.reporting.Main
}

executar() {
	compilar
	echo ":executar"
	java -classpath ${class}:${jar}/* ${pacoteGeral}.${pacoteDoProjeto}.${projeto}
}

executarTarefa() {
	dependencias=$1
}

echo :${pacoteDoProjeto}
if [ -n "$1" ]
then
	$1
else
	construir
fi
