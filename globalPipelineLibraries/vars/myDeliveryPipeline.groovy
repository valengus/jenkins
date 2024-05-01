def call(Map pipelineParams) {

  pipeline {
    agent any

    parameters {
      choice (name: 'DOCKER_IMAGE', choices: ['oraclelinux:9', 'oraclelinux:8' ],  description: 'docker image to build')
    }

    stages {

    stage('Checkout') {
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

      stage('INFO') {
        steps {
          script {
            echo "Branch - ${pipelineParams.branch}"
          }
        }
      }


    }
  }

}

