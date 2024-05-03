def call(Map pipelineParams) {
  
  pipeline {
    agent any

    stages {

      stage('1-Checkout') {
        steps {
          cleanWs()
          checkout([
            $class: 'GitSCM',
            doGenerateSubmoduleConfigurations: false,
            userRemoteConfigs: [[ url: "${pipelineParams.git_url}" ]],
            branches: [ [name: "${pipelineParams.branch}"] ]
          ])
        }
      }

      stage('2') {
        steps {
          createDockerImageJobsScript()
        }
      }


    }
  }

}