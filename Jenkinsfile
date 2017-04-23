pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh 'chmod +x gradlew'
                sh './gradlew dependencies || true'
                sh './gradlew clean assemble'
            }
        }

       stage('Archive') {
           steps {
                archiveArtifacts 'kontact/build/outputs/aar/*.aar'
            }
       }
    }
}