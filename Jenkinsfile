pipeline {
    agent any
    tools {
        maven "M3"
    }
    environment {
        // Defino la credencial de docker
        DOCKERHUB_CREDENTIALS = credentials('tgriffabenitez-dockerhub')
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
                // Hago login en dockerhub
                bat 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push docker image') {
            steps {
                // Hago push de la imagen a dockerhub
                bat 'docker push tgriffabenitez/ms-customer:latest'
            }
        }
    }

    post {
        always {
            // cierro sesion en dockerhub
            bat 'docker logout'
        }
    }
}
