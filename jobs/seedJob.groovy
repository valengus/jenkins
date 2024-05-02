folder('github') {
    displayName('github')
    description('Projects stored on github')
}

folder('github/docker') {
    displayName('docker')
    description('https://github.com/valengus/docker.git')
}

def list = [ 
  "ansible:2.15.6",
  "oraclelinux:9",
  "ansible:2.7.8",
  "oraclelinux:8",
  "python:3.9",
  "ansible:molecule",
  "terraform:0.13.7",
  "oraclelinux-systemd:8",
  "s3fs-fuse:1.93-1",
  "jenkins:2.346.2",
  "centos:7",
  "php:8.1"
]

list.each { item ->
  echo "${item}"
  pipelineJob('github/docker/${item}') {
    definition {
      cps {
        script('''@Library('globalPipelineLibraries') _
        buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git', docker_image: "${item}")
        '''.stripIndent())
        sandbox()     
      }
    }
  }
}


// pipelineJob('github/docker/docker') {
//   definition {
//     cps {
//       script('''@Library('globalPipelineLibraries') _
//       buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git')
//       '''.stripIndent())
//       sandbox()     
//     }
//   }
// }


// def dockerProjects = sh(script: "find . -maxdepth 1 -type d -name '*:*' | cut -c 3-", returnStdout: true).split('\n')
// dockerProjects.each { item ->
//   echo "${item}"
// }