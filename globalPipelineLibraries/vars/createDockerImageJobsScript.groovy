def call(Map pipelineParams) {

  node {

    def dockerProjects = sh(script: "find . -maxdepth 1 -type d -name '*:*' | cut -c 3-", returnStdout: true).split('\n')
    
    dockerProjects.each { item ->
      echo "${item}"
    
      jenkinsDockerBuildJobName = item.replace(":", "_")

      pipelineJob("${jenkinsDockerBuildJobName}") {
        definition {
          cps {
            script("@Library('globalPipelineLibraries') _ ;  buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git', docker_image: \"${item}\")".stripIndent())
            sandbox()     
          }
        }
      }

    }

  }

}
