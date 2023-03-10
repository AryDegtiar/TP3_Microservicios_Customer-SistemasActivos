pipeline {
    agent any
    tools {
        maven "M3"
        docker "docker"
    }

    stages {
        stage('Checkout') {
            steps {
                // Cambio al branch que tiene la implementacion de docker. Esto se hace a la hora de hacer el build del proyecto.
                git branch: "${Branch}", url: 'https://github.com/AryDegtiar/TP3_Microservicios_Customer-SistemasActivos.git'
            }
        }

        stage('Build java project') {
            steps {
                // Compilo el proyecto con maven para crear el .jar
                bat 'mvn clean package'
            }
        }

        stage("Unit Tests") {
            steps {
            // Ejecuto los tests unitarios con maven
                bat 'mvn test'
            }
        }

        stage('Build docker image') {
            steps {
                // Creo la imagen de docker
                bat 'docker build -t tgriffabenitez/ms-customer:latest .'
            }
        }

        stage('Docker login') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-pwd')]) {
                        bat "docker login -u tgriffabenitez -p ${dockerhub-pwd}"
                    }
                }
            }
        }

        stage('Push docker image') {
            steps {
                // Subo la imagen a dockerhub
                bat 'docker push tgriffabenitez/ms-customer:latest'
            }
        }
    }

    post {
        always {
            // Hago el logout de docker
            bat 'docker logout'
        }
    }
}
