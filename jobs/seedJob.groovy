folder('github') {
    displayName('github')
    description('Projects stored on github')
}

folder('github/docker') {
    displayName('docker')
    description('https://github.com/valengus/docker.git')
}

def dockerBuildJobs = [
  "oraclelinux9": null,
  "ansible2.16": "oraclelinux9",
  "terraform0.13": "ansible2.16",
]

dockerBuildJobs.eachWithIndex{
  entry, i ->
  println "$i $entry.key: $entry.value"

  pipelineJob("github/docker/$entry.key") {
    if (entry.value != null) {
      triggers { upstream("$entry.value", 'SUCCESS') }
    }
    definition {
      cps {
        script("@Library('globalPipelineLibraries') _ ; buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git', docker_image: \"$entry.key\")".stripIndent())
        sandbox()
      }
    }
  }

}
