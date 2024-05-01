def call(Map pipelineParams) {

  pipeline {
    agent any
    parameters {
      string(name: 'myInput', description: 'Some pipeline parameters')
    }
    stages {
      stage('Stage one') {
        steps {
          script {
            echo "Parameter from template creation: " + pipelineParams.someParam
          }
        }
      }
      stage('Stage two') {
        steps {
          script {
            echo "Job input parameter: " + params.myInput
          }
        }
      }
    }
  }

}
