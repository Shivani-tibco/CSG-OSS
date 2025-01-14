#!/usr/bin/env groovy
import groovy.util.logging.Slf4j
import java.nio.file.Files
import java.nio.file.Paths

@Slf4j
class ArtifactoryScanner {

    static void main(String[] args) {

        // Download WSS Unified Agent if it doesn't exist
        downloadWssAgent()

        // Run Artifactory Scan 
        runArtifactoryScan()

        // Upload Report to Artifactory (Placeholder - Implementation needed)
        // uploadReportToArtifactory() 
    }

    static void downloadWssAgent() {
        def wssAgentFileName = "jfrog.exe"
        def downloadUrl = "https://releases.jfrog.io/artifactory/jfrog-cli/v2/latest/win64/jfrog.exe" 

        if (!new File(wssAgentFileName).exists()) {
            log.info("Downloading WSS Unified Agent from ${downloadUrl} ...")
            try {
                Files.copy(new URL(downloadUrl).openStream(), Paths.get(wssAgentFileName))
                log.info("WSS Unified Agent downloaded.")
            } catch (IOException e) {
                log.error("Error downloading WSS Unified Agent: ${e.message}")
            }
        } else {
            log.info("WSS Unified Agent already exists.")
        }
    }

    static void runArtifactoryScan() {
        def configFilePath = 'wss-unified-agent.config'

        // Check if the config file exists
        if (!new File(configFilePath).exists()) {
            log.error("Error: WSS Unified Agent config file '${configFilePath}' not found.")
            return
        }

        def command = [
            'jfrog.exe', 
            'rt', 
            'scan', 
            '--config', configFilePath 
        ]

        log.info("Running Artifactory scan with command: ${command.join(' ')}")
        try {
            def process = command.execute()
            process.waitFor()

            // Print the output of the scan
            process.in.eachLine { line -> log.info(line) }

            log.info("Artifactory scan completed.")
        } catch (IOException e) {
            log.error("Error executing scan command: ${e.message}")
        }
    }

    // Placeholder for Artifactory upload logic
    // static void uploadReportToArtifactory() {
    //     // Implement Artifactory upload logic here 
    //     // using the generated report files
    // }
}
