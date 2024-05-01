folder('github') {
    displayName('github')
    description('Projects stored on github')
}


pipelineJob('job-name') {
  definition {
    cps {
      script('''@Library('globalPipelineLibraries') _
      myDeliveryPipeline(branch: 'main')
      '''.stripIndent())
      sandbox()     
    }
  }
}
