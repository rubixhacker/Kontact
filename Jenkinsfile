pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh 'chmod +x gradlew'
                sh './gradlew clean assemble'
            }
        }
    }
}