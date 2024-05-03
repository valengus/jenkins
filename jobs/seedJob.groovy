folder('github') {
    displayName('github')
    description('Projects stored on github')
}

folder('github/docker') {
    displayName('docker')
    description('https://github.com/valengus/docker.git')
}



def map = [ 
  "oraclelinux9": null, 
  "ansible2.15": "oraclelinux9",
]

map.eachWithIndex{
  entry, i -> 
  println "$i $entry.key: $entry.value"

  pipelineJob("github/docker/$entry.key") {
    
    
  if (entry.value == null || entry.value.isEmpty()) {

  } else {
    triggers { 
      upstream(upstreamProjects: "$entry.value", threshold: hudson.model.Result.SUCCESS) 
    }
  }

    // triggers {
    //     upstream("$entry.value", 'SUCCESS')
    // }
    
    
    definition {
      cps {
        script("@Library('globalPipelineLibraries') _ ; buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git', docker_image: \"$entry.key\", docker_image_from: \"$entry.value\")".stripIndent())
        sandbox()     
      }
    }
  }


}



// def dockerImageList = [
//   "oraclelinux9",
//   "ansible2.15",

// ]

// dockerImageList.each { item ->

//   pipelineJob("github/docker/${item}") {
//     definition {
//       cps {
//         script("@Library('globalPipelineLibraries') _ ; buildDockerImageJob(branch: 'main', git_url: 'https://github.com/valengus/docker.git', docker_image: \"${item}\")".stripIndent())
//         sandbox()     
//       }
//     }
//   }

// }