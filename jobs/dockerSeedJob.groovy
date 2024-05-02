folder('github') {
    displayName('github')
    description('Projects stored on github')
}

folder('github/docker') {
    displayName('docker')
    description('https://github.com/valengus/docker.git')
}

job('TestJob') {
    scm {
        git('https://github.com/valengus/docker.git')
    }
    triggers {
        scm('H/15 * * * *')
    }
    steps {

      pipelineJob('github/docker/docker') {
        definition {
          cps {
          script('''@Library('globalPipelineLibraries') _
          buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git')
          '''.stripIndent())
          sandbox()     
          }
        }
      }

    }
}