pipeline {
  agent {
    docker {
      image 'oraclelinux:9'
      args '-v /var/run/docker.sock:/var/run/docker.sock -u root'
    }
  }

  environment {
    ANSIBLE_ROLES_PATH = "$PWD"
  }

  options {
    disableConcurrentBuilds()
  }

  stages {

    stage('Install dependencies') {
      steps {
        sh 'dnf config-manager --add-repo=https://download.docker.com/linux/centos/docker-ce.repo'
        sh 'dnf install -y docker-ce docker-ce-cli containerd.io'
        sh 'dnf install -y python3 python3-pip python3-libselinux'
        sh 'pip install pip --upgrade'
        sh 'pip install -r requirements.txt'
      }
    }

    stage('Info') {
      steps {
        sh 'ansible --version'
        sh 'pip freeze'
      }
    }

    stage('Lint') {
      steps {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
          sh 'ansible-lint .'
        }
      }
    }

    stage('Molecule') {
      steps {
        sh 'molecule test --all'
      }
    }

  }

  post {
    success {
      echo 'Success'
    }
    failure {
      echo 'Failure'
    }
    always {
      echo 'Cleanup'
      sh 'molecule destroy --all'
    }
  }

}
