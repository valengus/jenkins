def call(Map pipelineParams) {

  def dockerProjects = sh(script: "find . -maxdepth 1 -type d -name '*:*' | cut -c 3-", returnStdout: true).split('\n')
  
  dockerProjects.each { item ->
    echo "${item}"
  }

}