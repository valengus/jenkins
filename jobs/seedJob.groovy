folder('github') {
    displayName('github')
    description('Projects stored on github')
}


pipelineJob('github/docker') {
  definition {
    cps {
      script('''@Library('globalPipelineLibraries') _
      buildDockerImage(branch: 'main', git_url: 'https://github.com/valengus/docker.git')
      '''.stripIndent())
      sandbox()     
    }
  }
}