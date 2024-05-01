def call(Map pipelineParams) {

  pipeline {
    agent any
    stages {

      stage('INFO') {
        steps {
          script {
            echo "Branch - ${pipelineParams.branch}"
          }
        }
      }

      stage('SCM') {
        steps {
          script {
            echo "Cloning - ${pipelineParams.branch}"
          }
        }
      }

    }
  }

}

