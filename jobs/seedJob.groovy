folder('github') {
    displayName('github')
    description('Projects stored on github')
}

folder('github/docker') {
    displayName('docker')
    description('https://github.com/valengus/docker.git')
}


def dockerImageList = [
  "oraclelinux9",
  "ansible2.15",

]

dockerImageList.each { item ->

  pipelineJob("github/docker/${item}") {
    definition {
      cps {
        script("@Library('globalPipelineLibraries') _ ; buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git', docker_image: \"${item}\")".stripIndent())
        sandbox()     
      }
    }
  }

}
