pipeline {
  agent any
  stages {
    stage('build ') {
      steps {
        git(url: 'https://github.com/kathywan/geoclient.git', branch: 'dev')
        sh './gradlew clean build'
      }
    }
  }
}