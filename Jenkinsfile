pipeline {
    agent any

    stages {
        stage('Build') {
            scm checkout
            sh '.gradlew clean assemble'
        }
    }
}