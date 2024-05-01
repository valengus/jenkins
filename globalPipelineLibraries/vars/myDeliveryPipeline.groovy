

def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {

            stage('build') {
                steps {
                    script {
                        echo "Parameter from template creation: " + pipelineParams.someParam
                    }
                }
            }

        }

    }
}

