// src/org/jenkinsSharedLibrarie/Triger.groovy
package org.jenkinsSharedLibrarie

def triggerFromJob(jobName) {
  if (jobName == null || jobName.isEmpty()) {

  } else {
    triggers { 
      upstream(upstreamProjects: "${jobName}", threshold: hudson.model.Result.SUCCESS) 
    }
  }
}
