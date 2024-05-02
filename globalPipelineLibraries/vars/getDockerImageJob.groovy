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

      stage('2-Info') {
        steps {
          script {
            sh "env"
            def dockerProjects = sh(script: "find . -maxdepth 1 -type d -name '*:*' | cut -c 3-", returnStdout: true).split('\n')
            dockerProjects.each { item ->
              echo "${item}"
            }

            buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git')

          }
        }
      }



    }
  }

}