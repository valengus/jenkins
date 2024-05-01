def call(Map pipelineParams) {

  pipeline {
    agent any

    parameters {
      choice (name: 'DOCKER_IMAGE', choices: ['oraclelinux:9', 'oraclelinux:8' ],  description: 'docker image to build')
    }

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

      stage('2-INFO') {
        steps {
          script {

            echo "BRANCH - ${pipelineParams.branch}"
            echo "DOCKER_IMAGE - ${params.DOCKER_IMAGE}"
            log.info "Changing directry to ${params.DOCKER_IMAGE}"
            sh "env"

            dir("${params.DOCKER_IMAGE}") {
              sh "cat Dockerfile"
            }


          }
        }
      }


    }
  }
}

