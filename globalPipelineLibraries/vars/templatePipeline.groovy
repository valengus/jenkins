def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {

            stage('info') {
                steps {
                    script {
                        echo "pipelineParams.branch"
                    }
                }
            }

        }

    }
}

