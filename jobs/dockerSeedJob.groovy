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
      def clusterComponent = "./"
      stage('list components') {
          List<String> files = sh(script: "ls -A1 ${clusterComponent}", returnStdout: true).trim().split('\n')
      }
    }
}
