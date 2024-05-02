def info(message) {
    echo "INFO: ${message}"
}

def warning(message) {
    echo "WARNING: ${message}"
}

def buildtime = sh(script: "echo `date +%F-%T`", returnStdout: true).trim()