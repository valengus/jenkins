@Library('globalPipelineLibraries')

pipelineJob("docker") {
  template.createDeclarativePipeline(someParam: 'myParam')
}
