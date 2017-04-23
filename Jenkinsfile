pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh './gradlew clean assemble'
            }
        }
    }
}