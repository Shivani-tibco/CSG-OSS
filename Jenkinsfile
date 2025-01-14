pipeline {
    agent any 

    stages {
        stage('Artifactory Scan') {
            steps {
                // Assuming your mend-artifactoryScan.groovy and 
                // wss-unified-agent.config are in the same directory
                // as your Jenkinsfile
                sh 'groovy mend-artifactoryScan.groovy' 
            }
        }
        // Add other stages as needed (build, deploy, etc.)
    }
}
