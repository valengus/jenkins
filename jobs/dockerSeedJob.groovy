// folder('github') {
//     displayName('github')
//     description('Projects stored on github')
// }

// folder('github/docker') {
//     displayName('docker')
//     description('https://github.com/valengus/docker.git')
// }


job('demo') {
    steps {
        shell('echo Hello World!')
    }
}