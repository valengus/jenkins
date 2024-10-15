import jenkins.model.Jenkins

folder('github') {
  displayName('github')
}

folder('github/ansibleRoles') {
  displayName('ansibleRoles')
}

def jobList = [
  [
    jobName:      "jenkins", 
    url:          "https://github.com/valengus/jenkins.git", 
    requirements: "requirements.txt"
  ],
]

jobList.each { job ->
  println (job.jobName + " " + job.url + " " + job.requirements)

  pipelineJob("github/ansibleRoles/${job.jobName}") {
    parameters {
      gitParam('BRANCH') {
        defaultValue('origin/main')
        description('Select git branch to release on')
        type('BRANCH')
      }
    }
    definition {
      cps {
        script("@Library('jenkinsSharedLibraries') _ ; ansibleRolesPipeline(jobName: \"${job.jobName}\", url: \"${job.url}\", requirements: \"${job.requirements}\")".stripIndent())
        sandbox()
      }
    }
  }

}
