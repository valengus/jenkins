#!/usr/bin/env groovy
import jenkins.model.*
@Library('globalPipelineLibraries')

folder('github') { }

def github_projects = [
  "docker"
]

github_projects.each {
  service = it
  println("${service}")

  pipelineJob("github/${service}") {
    template.createDeclarativePipeline(someParam: 'myParam')
 }

}