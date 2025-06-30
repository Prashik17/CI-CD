pipeline {
    agent any
    environment {
        IMAGE_NAME = "springboot-app"
    }

    stages {
        steps {
            git 'https://github.com/Prashik17/CI-CD.git'
        }
    }

    stage('Build JAR') {
        steps {
            sh 'mvn clean package -DskipTests'
        }
    }

    stage('Build Docker Image') {
        steps {
            sh 'docker build -t $IMAGE_NAME .'
        }
    }

    stage('Load to Minikube') {
        steps {
            sh 'minikube image load $IMAGE_NAME:latest'
        }
    }

    stage('Deploy to Minikube') {
        steps {
            sh '''
                kubectl apply -f k8s/deployment.yaml
                kubectl apply -f k8s/service.yaml

            '''
        }
    }

}