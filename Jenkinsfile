pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'scientific-calculator'
        GITHUB_REPO_URL = 'git@github.com:sakina27/ScientificCalculator.git'
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
                    docker.build("${DOCKER_IMAGE_NAME}")
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'DockerHubCred') {
                        sh "docker tag ${DOCKER_IMAGE_NAME} saki2726/${DOCKER_IMAGE_NAME}:latest"
                        sh "docker push saki2726/${DOCKER_IMAGE_NAME}"
                    }
                }
            }
        }

        stage('Deploy with Ansible') {
            steps {
                script {
                    try {
                        ansiblePlaybook playbook: 'deploy.yml', inventory: 'inventory'
                    } catch (Exception e) {
                        echo "Ansible deployment failed! Check logs."
                        error "Stopping pipeline due to deployment failure."
                    }
                }
            }
        }
    }
}
