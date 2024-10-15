package org.jsl

def checkOut(String repo, String branch) {

  checkout([
    $class: 'GitSCM',
    doGenerateSubmoduleConfigurations: false,
    extensions: [[$class: 'CloneOption', noTags: true, shallow: true, depth: 1, timeout: 30]],
    userRemoteConfigs: [[ url: "${repo}" ]],
    branches: [ [name: "${branch}"] ]
  ])

}
