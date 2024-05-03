// src/org/jenkinsSharedLibrarie/Triger.groovy
package org.jenkinsSharedLibrarie

def triggerFromJob(jobName) {
  if (jobName == null || jobName.isEmpty()) {

  } else {
    triggers { 
      upstream(upstreamProjects: "${pipelineParams.docker_image_from}", threshold: hudson.model.Result.SUCCESS) 
    }
  }
}
