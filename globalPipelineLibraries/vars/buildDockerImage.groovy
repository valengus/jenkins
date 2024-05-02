def call(Map pipelineParams) {

  pipeline {
    agent any

    parameters {
      choice (name: 'DOCKER_IMAGE', choices: ['oraclelinux:9', 'oraclelinux:8' ],  description: 'docker image to build')
      string (name: 'DOCKER_REGISTRY', defaultValue: 'local', description: 'docker registry')
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

      stage('2-Info') {
        steps {
          script {
            log.info "BRANCH - ${pipelineParams.branch}"
            log.info "DOCKER_IMAGE - ${params.DOCKER_IMAGE}"
            log.info "Changing directry to ${params.DOCKER_IMAGE}"
            sh "env"
            dir("${params.DOCKER_IMAGE}") {
              sh "cat Dockerfile"
            }
          }
        }
      }

      stage('3-Prepare') {
        steps {
          script {
            dir("${params.DOCKER_IMAGE}") {
              sh "docker image prune -af"
            }
          }
        }
      }

      stage('4-Build') {
        steps {
          script {
            dir("${params.DOCKER_IMAGE}") {
              sh "docker build --no-cache . -t ${params.DOCKER_REGISTRY}/${params.DOCKER_IMAGE}"
            }
          }
        }
      }




      stage('9-CleanUp') {
        steps {
          script {
            dir("${params.DOCKER_IMAGE}") {
            sh "docker ps -a"
            sh "docker image ls"
            log.info "CleanUp"
            }
          }
        }
      }

    }
  }
}
