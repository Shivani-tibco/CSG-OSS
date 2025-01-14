pipeline {
    agent { label 'vmrelas8' } 

    stages {
        stage('Artifactory Scan') {
            steps {
                // Checkout the code (if needed)
                checkout scm

                // Execute the Artifactory scan script
                sh 'groovy mend-artifactoryScan.groovy' 
            }
        }
        // Add other stages as needed (build, deploy, etc.)
    }
    
    post {
        always {
            // Archive the scan results (optional)
            archiveArtifacts artifacts: 'jfrog-cli/scan/*.json', fingerprint: true 
        }
    }
}
