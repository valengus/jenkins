def call(String folderPath) {
    def cmd = "find ${folderPath} -maxdepth 1 -type d -name '*:*' | cut -c 3-"
    sh "${cmd}"
}
