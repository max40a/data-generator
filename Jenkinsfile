pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                checkout scm
                sh './gradlew clean build -x test'
            }
        }
        stage('Test') {
            steps {

                sh './gradlew test'
                junit '**/build/test-results/test/*.xml'
            }
        }
    }
}
