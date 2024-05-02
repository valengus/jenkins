// folder('github') {
//     displayName('github')
//     description('Projects stored on github')
// }

// folder('github/docker') {
//     displayName('docker')
//     description('https://github.com/valengus/docker.git')
// }


job('DSL-Tutorial-1-Test') {
    triggers { scm("*/15 * * * *") }
    scm {
      git {
        remote {
            url("https://github.com/valengus/docker.git")
        }
      }
    }
    steps {
      def output = sh returnStdout: true, script: "find . -maxdepth 1 -type d -name '*:*' | cut -c 3-"
      foldersList = output.tokenize('\n').collect() { it }
    }
}
