#!/usr/bin/env groovy
import groovy.util.logging.Slf4j

@Slf4j
class ArtifactoryScanner {

    static void main(String[] args) {
        def configFile = new File('artifactory_scan_config')
        def config = loadConfig(configFile)

        if (!config) {
            log.error("Failed to load configuration from ${configFile.absolutePath}")
            return
        }

        // Download WSS Unified Agent (if not already present)
        downloadWssAgent()

        // Run Artifactory Scan for each URL
        config['artifactory.urls'].each { url ->
            runArtifactoryScan(config, url)
        }

        // Upload Report to Artifactory (Placeholder)
        // uploadReportToArtifactory(config) 
    }

    static Map<String, String> loadConfig(File configFile) {
        def config = [:]
        try {
            configFile.withReader { reader ->
                reader.eachLine { line ->
                    if (line.trim() && !line.startsWith("#")) { 
                        def parts = line.split("=")
                        config[parts[0].trim()] = parts[1].trim()
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error reading configuration file: ${e.message}")
            return null
        }
        // Assuming 'artifactory.urls' is a comma-separated list in the config file
        config['artifactory.urls'] = config['artifactory.urls'].split(",").collect { it.trim() } 
        return config
    }

    static void downloadWssAgent() { 
        // ... (Same as before) ...
    }

    static void runArtifactoryScan(Map<String, String> config, String artifactoryUrl) {
        def command = [
                'wss-unified-agent.exe',
                'scan', 
                '-Dartifactory.url=' + artifactoryUrl,
                '-Dartifactory.repoKeys=' + config['artifactory.repoKeys'],
                // Add other necessary command-line options here 
                // based on your specific WSS Unified Agent configuration
        ]

        log.info("Running Artifactory scan for ${artifactoryUrl} with command: ${command.join(' ')}")
        try {
            def process = command.execute()
            process.waitFor()
            log.info("Artifactory scan for ${artifactoryUrl} completed.")
        } catch (IOException e) {
            log.error("Error executing scan command for ${artifactoryUrl}: ${e.message}")
        }
    }

    // Placeholder for Artifactory upload logic
    // static void uploadReportToArtifactory(Map<String, String> config) {
    //     // Implement Artifactory upload logic here 
    //     // using the config properties and the generated report files
    // }
}
