projeto=estruturados
fontes=fontes
testes=testes
bibliotecas=bibliotecas
recursos=recursos
binarios=binarios
construcao=construcao

fontesJava=${fontes}/java
testesJava=${testes}/java
bibliotecasJava=${bibliotecas}/jar
binariosJava=${binarios}/class

arquivosFontesJava=$(find ${fontesJava} -name *.java)
arquivosTestesJava=$(find ${testesJava} -name *.java)
classesTestesJava=$(echo ${arquivosTestesJava} | sed -e s:${testesJava}::g -e s:^/::g -e "s:\s/: :g" -e s:/:.:g -e s:[.]java::g -e s:[a-Z.]*figuracao[a-Z.]*::g)

limpar() {
	echo ":limpar";
	rm -rf ${binarios};
	rm -rf ${construcao};
}

criarEstrutura() {
	echo ":criarEstrutura";
	mkdir -p ${fontesJava};
	mkdir -p ${testesJava};
	mkdir -p ${bibliotecasJava};
	mkdir -p ${binariosJava};
	mkdir -p ${construcao};
}

adicionarBibliotecas() {
	echo ":adicionarBibliotecas";
}

construir() {
	limpar;
	criarEstrutura;
	adicionarBibliotecas;
}

compilar() {
	construir;
	echo ":compilar";
	javac -classpath ${bibliotecasJava}/*:${binariosJava} -sourcepath ${fontesJava} -d ${binariosJava} -Werror -deprecation -g ${arquivosFontesJava}
 	javac -classpath ${bibliotecasJava}/*:${binariosJava} -sourcepath ${testesJava} -d ${binariosJava} -Werror -deprecation -g ${arquivosTestesJava}
}

testar() {
	compilar;
	echo ":testar";
	java -classpath ${bibliotecasJava}/*:${binariosJava} org.junit.runner.JUnitCore ${classesTestesJava};
}

depurar() {
	compilar;
	echo ":depurar";
	jdb -classpath ${bibliotecasJava}/*:${binariosJava} org.junit.runner.JUnitCore ${classesTestesJava};
}

gerarVersao() {
	compilar;
	echo ":gerarVersao";
	jar -cf ${construcao}/${projeto}.jar -C ${binariosJava} .
}

if [ -n "$1" ]
then
	$1;
else
	construir;
fi
