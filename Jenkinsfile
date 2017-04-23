pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh './bash gradlew clean assemble'
            }
        }
    }
}