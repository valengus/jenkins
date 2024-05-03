// src/org/jenkins/Triger.groovy
package org.jenkins

def checkOutFrom(repo) {
  git url: "git@github.com:jenkinsci/${repo}"
}


