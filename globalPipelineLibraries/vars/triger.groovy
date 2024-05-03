def skipBuildTrigger(jobName) {
  if (jobName == null || jobName.isEmpty()) {

  } else {
    triggers { upstream(upstreamProjects: "${pipelineParams.docker_image_from}", threshold: hudson.model.Result.SUCCESS) }
    upstream(upstreamProjects: "${pipelineParams.docker_image_from}", threshold: hudson.model.Result.SUCCESS)
  }
}