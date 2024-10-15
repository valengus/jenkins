def call(Map pipelineParams) {

  def repo = new org.jsl.publicGithub()

  pipeline {
    agent any

    environment {
      PATH = "$HOME/.venv${pipelineParams.jobName}/bin:$PATH"
    }

    stages {

      stage('Checkout') {
        steps {
          script {
            log.info "Clonning ${pipelineParams.url}"
            repo.checkOut(pipelineParams.url, "${params.BRANCH}")
            sh "env"
          }
        }
      }

      stage('Prepare') {
        steps {
          script {
            sh "rm -rf $HOME/.venv${pipelineParams.jobName}"
            sh "python3 -m venv $HOME/.venv${pipelineParams.jobName}"
            sh "$HOME/.venv${pipelineParams.jobName}/bin/pip3 install --upgrade pip"
            sh "$HOME/.venv${pipelineParams.jobName}/bin/pip3 install --upgrade setuptools"

            if (pipelineParams.requirements) {
              log.info "job.requirements is defined: ${pipelineParams.requirements}"
              sh "$HOME/.venv${pipelineParams.jobName}/bin/pip3 install -r ${pipelineParams.requirements}"
            } else { 
              log.info "job.requirements is not defined"
              if (fileExists('requirements.txt')) {
                sh "$HOME/.venv${pipelineParams.jobName}/bin/pip3 install -r requirements.txt"
              } else {
                echo "File requirements.txt not found"
                sh "$HOME/.venv${pipelineParams.jobName}/bin/pip3 install ansible-core ansible molecule docker molecule-docker yamllint ansible-lint"
              }
            }
          }
        }
      }

      stage('Info') {
        steps {
          script {
            sh "molecule --version"
          }
        }
      }

      stage('Test') {
        steps {
          script {
            sh "molecule test --all"
          }
        }
      }


    }
  }

}
