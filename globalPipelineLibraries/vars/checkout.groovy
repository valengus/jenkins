def call(Map pipelineParams) {

  checkout([
  $class: 'GitSCM',
  doGenerateSubmoduleConfigurations: false,
  userRemoteConfigs: [[ url: "https://github.com/valengus/docker.git" ]],
  branches: [ [name: "main"] ]
  ])

}