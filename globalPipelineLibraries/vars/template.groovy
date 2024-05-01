def createMyStandardDeclarativePipeline(Map templateParams) {   

    pipeline {
        agent any
        parameters {
            string(name: 'myInput', description: 'Some pipeline parameters')
        }
        stages {
            stage('Stage one') {
                steps {
                    script {
                        echo "${templateParams.someParam}"
                    }
                }
            }

        }
    }
}