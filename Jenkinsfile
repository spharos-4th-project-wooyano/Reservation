pipeline {
    agent any
    stages {
        stage('Check') {
            steps {
                git branch: 'develop',credentialsId:'0-shingo', url:'https://github.com/Spharos-final-project-WOOYANO/Reservation'
            }
        }
        stage('Build'){
            steps{
                script {
                    sh '''
                        pwd
                        chmod +x ./gradlew
                        ./gradlew build -x test
                    '''
                    
                }
                    
            }
        }
        stage('DockerSize'){
            steps {
                sh '''
                    docker stop reservaton-service || true
                    docker rm reservaton-service || true
                    docker rmi reservaton-service-img || true
                    docker build -t reservaton-service-img:latest .
                '''
            }
        }
        stage('Deploy'){
            steps{
                sh '''
                docker pull reservation-service-img
                docker run -d --name reservaton-service -p 8001:8001 reservation-service-img
                '''
            }
        }
    }
}

