def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {

            stage('Info') {
                steps {
                    script {
                        echo "${pipelineParams.branch}"
                    }
                }
            }




        }

    }
}

