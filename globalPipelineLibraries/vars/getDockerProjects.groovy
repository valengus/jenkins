// def call(String folderPath) {
//     def cmd = "find ${folderPath} -maxdepth 1 -type d -name '*:*' | cut -c 3-"
//     sh "${cmd}"
// }

def folders(path) {
    sh(script: "echo \$(ls ${path})", returnStdout: true)
}