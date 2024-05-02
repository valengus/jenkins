// folder('github') {
//     displayName('github')
//     description('Projects stored on github')
// }

// folder('github/docker') {
//     displayName('docker')
//     description('https://github.com/valengus/docker.git')
// }

job('DSL-Tutorial-1-Test') {
    scm {
        git('https://github.com/valengus/docker.git')
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {
        shell('echo Hello World!')
    }
}

