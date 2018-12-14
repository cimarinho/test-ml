Test-ML

1) Pré-requisito para execução local.

   	Apache-maven instalado e configurado.
   	Java versão 1.8 instalado e configurado.
  	Dentro do diretório do projeto (/test-ml)
   	mvn clean install -U.
   	
    java -jar /test-ml-impl/target/test-ml.jar
    
2) Foi criado 2 endpoint para teste.

    http://18.231.110.29:8080/stats
     
    http://18.231.110.29:8080/mutant
    
3) Dentro da pasta conf do projeto tem os arquivos do postman e jmetter para testes.

