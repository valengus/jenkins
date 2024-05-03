// src/org/jenkinssl/Triger.groovy
package org.jenkinssl

def triggerFromJob(jobName) {
  if (jobName == null || jobName.isEmpty()) {

  } else {
    triggers { 
      upstream(upstreamProjects: "${pipelineParams.docker_image_from}", threshold: hudson.model.Result.SUCCESS) 
    }
  }
}
