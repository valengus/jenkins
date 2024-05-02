folder('github') {
    displayName('github')
    description('Projects stored on github')
}

folder('github/docker') {
    displayName('docker')
    description('https://github.com/valengus/docker.git')
}


job('example-1') {
  steps {
    def dockerProjects = sh(script: "find . -maxdepth 1 -type d -name '*:*' | cut -c 3-", returnStdout: true).split('\n')
    dockerProjects.each { item ->
      echo "${item}"
    }
  }
}

// pipelineJob('github/docker/docker') {
//   definition {
//     cps {
//       script('''@Library('globalPipelineLibraries') _
//       buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git')
//       '''.stripIndent())
//       sandbox()     
//     }
//   }
// }


// pipelineJob('docker') {
//   definition {
//     cps {
//       script('''@Library('globalPipelineLibraries') _
//       getDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git')
//       '''.stripIndent())
//       sandbox()     
//     }
//   }
// }

