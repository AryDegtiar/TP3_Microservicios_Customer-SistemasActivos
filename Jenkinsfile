pipeline {
    agent any
    tools {
        maven "M3"
    }

    stages {
        stage('Checkout') {
            steps {
                // Cambio al branch que tiene la implementacion de docker. Esto se hace a la hora de hacer el build del proyecto.
                git branch: "${Branch}", url: 'https://github.com/AryDegtiar/TP3_Microservicios_Customer-SistemasActivos.git'
            }
        }

        stage('Build') {
            steps {
                // Compilo el proyecto con maven para crear el .jar
                bat 'mvn clean package'

                // Genero la imagen de docker que se llama ms-customer-img
                bat 'docker build -t ms-customer-img .'
            }
        }

        stage("Unit Tests") {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                // Hago el deploy de la imagen usando docker-compose
                bat 'docker-compose up -d'
            }
        }
    }
}
