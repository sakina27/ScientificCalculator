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

        stage('Test') {  // ✅ This ensures tests run in Jenkins
            steps {
                sh 'mvn test'
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

        stage('Run GUI') {
            steps {
                script {
                    sh 'docker ps -q --filter "name=calculator" | xargs -r docker stop'
                    sh 'docker ps -aq --filter "name=calculator" | xargs -r docker rm'
                    sh 'docker run -d -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix --name calculator saki2726/scientific-calculator'
                }
            }
        }
    }
}
