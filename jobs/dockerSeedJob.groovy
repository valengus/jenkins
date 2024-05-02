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
      shell('ls')

        def  FILES_LIST = sh (script: "ls .", returnStdout: true).trim()
        //DEBUG
        echo "FILES_LIST : ${FILES_LIST}"
        //PARSING
        for(String ele : FILES_LIST.split("\\r?\\n")){ 
          println ">>>${ele}<<<"     
        }

    }
}
