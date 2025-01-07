pipeline {
    agent any

    stages {
        stage('Mend Scan') {
            steps {
                sh './mend-scan.groovy' 
            }
        }
    }
}