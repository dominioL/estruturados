#!/bin/bash

projeto=Estruturados
pacoteDoProjeto=estruturados
pacoteGeral=br.dominioL

jar=jar
java=java
construcao=construcao

class=${construcao}/class
coberturaClasses=${construcao}/coberturaClasses
coberturaRelatorio=${construcao}/coberturaRelatorio
ser=${construcao}/ser

limpar() {
	echo ":limpar"
	rm -rf ${construcao}
}

criarEstrutura() {
	limpar
	echo ":criarEstrutura"
	mkdir -p ${jar}
	mkdir -p ${java}
	mkdir -p ${construcao}

	mkdir -p ${class}
	mkdir -p ${coberturaClasses}
	mkdir -p ${coberturaRelatorio}
	mkdir -p ${ser}
}

adicionarBibliotecas() {
	criarEstrutura
	echo ":adicionarBibliotecas"
}

compilar() {
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
	construir
	echo ":testar"
	arquivosTestesJava=$(find ${java} -name *Teste*.java)
	classesTestesJava=$(echo ${arquivosTestesJava} | sed -e s:${java}/::g -e s:^/::g -e "s:\s/: :g" -e s:/:.:g -e s:\.java::g -e s:[a-Z.]*figuracao[a-Z.]*::g)
	java -classpath ${class}:${jar}/* org.junit.runner.JUnitCore ${classesTestesJava}
}

analisar() {
	construir
	echo ":analisar"
	arquivosTestesJava=$(find ${java} -name *Teste*.java)
	classesTestesJava=$(echo ${arquivosTestesJava} | sed -e s:${java}/::g -e s:^/::g -e "s:\s/: :g" -e s:/:.:g -e s:\.java::g -e s:[a-Z.]*figuracao[a-Z.]*::g)
	cp -R ${class}/* ${coberturaClasses}

	java \
		-classpath ${jar}/cobertura.jar:${jar}/asm.jar:${jar}/asmAnalysis.jar:${jar}/asmUtil.jar:${jar}/asmTree.jar:${jar}/asmCommons.jar:${jar}/log4j.jar:${jar}/oro.jar \
		net.sourceforge.cobertura.instrument.Main \
		--datafile ${ser}/cobertura.ser \
		--destination ${coberturaClasses} \
		classes ${class}

	cd ${ser}
	java \
		-classpath ../../${coberturaClasses}:../../${jar}/hamcrest.jar:../../${jar}/jUnit.jar:../../${jar}/cobertura.jar:../../${jar}/asm.jar:../../${jar}/asmAnalysis.jar:../../${jar}/asmUtil.jar:../../${jar}/asmTree.jar:../../${jar}/asmCommons.jar:../../${jar}/log4j.jar:../../${jar}/oro.jar \
		org.junit.runner.JUnitCore ${classesTestesJava}
	cd ..
	cd ..
	java \
		-classpath ${jar}/cobertura.jar:${jar}/asm.jar:${jar}/asmAnalysis.jar:${jar}/asmUtil.jar:${jar}/asmTree.jar:${jar}/asmCommons.jar:${jar}/log4j.jar:${jar}/oro.jar \
		net.sourceforge.cobertura.reporting.Main \
		--datafile ${ser}/cobertura.ser \
		--destination ${coberturaRelatorio} \
		--source ${java} \
		--format html
}

executar() {
	construir
	echo ":executar"
}

echo :${pacoteDoProjeto}
if [ -n "$1" ]
then
	$1
else
	construir
fi
