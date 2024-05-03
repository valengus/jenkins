import src.org.jenkinsSharedLibrarie.Triger
def t = new org.jenkinsSharedLibrarie.Triger()

def call(Map pipelineParams) {

  pipeline {
    agent any

    parameters {
      choice (name: 'DOCKER_IMAGE', choices: ["${pipelineParams.docker_image}"],  description: 'docker image to build')
      string (name: 'DOCKER_IMAGE_PATH', defaultValue: 'valengus', description: 'image path')
    }

    environment {
      DOCKERHUB_TOKEN=credentials('dockerhubToken')
      BUILDTIME = sh(script: "echo `date +%F_%H%M%S`", returnStdout: true).trim()
    }

    t.triggerFromJob("${pipelineParams.docker_image_from}")

    // def t = new org.jenkinsSharedLibrarie.Triger()
    // t.triggerFromJob("${pipelineParams.docker_image_from}")

    // org.jenkinsSharedLibrarie.Triger().triggerFromJob("${pipelineParams.docker_image_from}")

    // triger.skipBuildTrigger "${pipelineParams.docker_image_from}"

    // triggers { 
    //   upstream(upstreamProjects: "${pipelineParams.docker_image_from}", threshold: hudson.model.Result.SUCCESS) 
    // }

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
              sh "docker system prune --volumes -af"
              sh "echo $DOCKERHUB_TOKEN_PSW | docker login --username $DOCKERHUB_TOKEN_USR --password-stdin"
            }
          }
        }
      }

      stage('4-Build') {
        steps {
          script {
            dir("${params.DOCKER_IMAGE}") {
              sh "docker build --platform linux/amd64 --no-cache . -t ${params.DOCKER_IMAGE_PATH}/${params.DOCKER_IMAGE}:${env.BUILDTIME}"
            }
          }
        }
      }

      stage('5-Tag') {
        steps {
          script {
            log.info "Tag"
            sh "docker tag ${params.DOCKER_IMAGE_PATH}/${params.DOCKER_IMAGE}:${env.BUILDTIME} ${params.DOCKER_IMAGE_PATH}/${params.DOCKER_IMAGE}:latest"
          }
        }
      }

      stage('6-Test') {
        steps {
          script {
            dir("${params.DOCKER_IMAGE}") {
            log.info "Test"
            sh "docker image ls"
            sh "docker ps -a"
            }
          }
        }
      }

      stage('7-Push') {
        steps {
          sh "docker push --all-tags ${params.DOCKER_IMAGE_PATH}/${params.DOCKER_IMAGE}"
        }
      }

      stage('8-CleanUp') {
        steps {
          script {
            dir("${params.DOCKER_IMAGE}") {
            log.info "CleanUp"
            sh "docker logout"
            sh "docker system prune --volumes -af"
            }
          }
        }
      }

    }
  }
}
