// pipeConvertida.groovy
def call(String repoUrl) {
  pipeline {
       agent any
       tools {
           maven 'maven-3.8.5'
           jdk 'jdk8'
       }
       stages {
           stage("Tools initialization") {
               steps {
                   bat "mvn --version"
                   bat "java -version"
               }
           }
           stage("Checkout Code") {
               steps {
                   git branch: 'main',
                       url: "${repoUrl}"
               }
           }
           stage("Limpiar workspace") {
               steps {
                   bat "mvn clean"
               }
           }
           stage("Lanzar Testcase") {
              steps {
                   bat "mvn test"
               }
           }
           stage("Empaquetar Application") {
               steps {
                   bat "mvn package -DskipTests"
               }
           }
       }
   }
}
