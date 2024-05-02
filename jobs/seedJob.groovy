folder('github') {
    displayName('github')
    description('Projects stored on github')
}

folder('github/docker') {
    displayName('docker')
    description('https://github.com/valengus/docker.git')
}

checkout

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

