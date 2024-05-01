folder('github') {
    displayName('github')
    description('Projects stored on github')
}

job('github/docker') {
  steps {
    shell('echo Hello World!')
  }
}

pipelineJob('job-name') {
  definition {
    cps {
      script('''@Library('globalPipelineLibraries')
      template.createMyStandardDeclarativePipeline(someParam: 'myParam')
      '''.stripIndent())
      sandbox()     
    }
  }
}
