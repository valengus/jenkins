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

      stage('PREPARE') {
        steps {
          script {
            dir("${params.DOCKER_IMAGE}") {
              echo "BRANCH - ${pipelineParams.branch}"
              echo "DOCKER_IMAGE - ${params.DOCKER_IMAGE}"
              sh "cd ${params.DOCKER_IMAGE} && ls"
            }
          }
        }
      }


    }
  }
}
