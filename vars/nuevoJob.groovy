def call(Map args) {
  def containers = [
    containerTemplate(name: 'node', image: 'node', ttyEnabled: true),
    containerTemplate(name: 'docker', image: 'docker', ttyEnabled: true),
    containerTemplate(name: 'jnlp', image: 'jenkins/jnlp-slave:3.35-5-alpine', ttyEnabled: true)
  ]
  def REGISTRY_CONFIG = [
    url: "https://hub.docker.com/repository/docker/nahuic/myweb",
    credentials: "dockerNahuic"
  ]
  def name = args.name
  def label = "job-${name}-${UUID.randomUUID().toString()}".take(15)
  def tag = "${UUID.randomUUID().toString()}".take(5)
  def imageName = "${registry}:${tag}"

  podTemplate(label: label, containers: containers, serviceAccount: 'jenkins') {
    node(label) {
        stage('Checkout') {
          checkout scm
        }  
        container('node') {
            stage('Instalar Dependencias y otras hierbas') {
              sh 'npm install'
            }
            stage('Construir y publicar una imagen en el Registry') {
                  container('docker') {
                    docker.withRegistry(REGISTRY_CONFIG.url, REGISTRY_CONFIG.credentials) {
                      docker.build(imageName).push()
                    }
                  }
              }
          }   
    }
  }
}
