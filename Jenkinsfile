pipeline {
    agent any

    stages {
        stage('Mend Scan') {
            steps {
                sh 'chmod +x mend-artifactoryScan.groovy' // Correct way to execute a shell command
                sh './mend-artifactoryScan.groovy' 
            }
        }
    }
}
