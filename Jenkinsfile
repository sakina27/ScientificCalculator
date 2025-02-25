pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = 'scientific-calculator'
        GITHUB_REPO_URL = 'https://github.com/your-username/ScientificCalculator.git'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: "${GITHUB_REPO_URL}"
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE_NAME}", '.')
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('', 'DockerHubCred') {
                        sh "docker tag ${DOCKER_IMAGE_NAME} saki2726/${DOCKER_IMAGE_NAME}:latest"
                        sh "docker push saki2726/${DOCKER_IMAGE_NAME}"
                    }
                }
            }
        }
        stage('Deploy with Ansible') {
            steps {
                script {
                    ansiblePlaybook playbook: 'deploy.yml', inventory: 'inventory'
                }
            }
        }
    }
}
