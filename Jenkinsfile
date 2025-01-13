pipeline {
    agent any

    stages {
        stage('Mend Scan') {
            steps {
                chmod +x mend-artifactoryScan.groovy
                sh './mend-artifactoryScan.groovy' 
            }
        }
    }
}
