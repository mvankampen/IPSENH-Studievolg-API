#!groovy

pipeline {
  agent any
  
  stages {
  
      stage('Build') {
        steps {
          echo 'Building...'
          sh 'make'
          archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true 
        }
      }

      stage('Test') {
       steps {
          echo 'Testing...'
          sh 'make check || true' 
          junit '**/target/*.xml'
        }    
      }

     stage('Deploy') {
       when {
         expression {
            currentBuild.result == null || currentBuild.result == 'SUCCESS'  
         }
       }  
      steps {
        echo 'Deploying...'
        echo 'And publishing...'
        sh 'make publish'
      }
    }
  }
}
