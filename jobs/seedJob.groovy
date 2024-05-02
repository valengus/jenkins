
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
