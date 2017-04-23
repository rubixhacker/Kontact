pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                scm checkout
                sh '.gradlew clean assemble'
            }
        }
    }
}