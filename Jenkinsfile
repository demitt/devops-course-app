@Library('pipeline-shared-library-example') _

def repoName = "devops-course-app"
def needDeploying = env.BRANCH_NAME == 'master' || env.BRANCH_NAME != 'develop'
def jdk = 'JDK8'
def imageName = 'owner/image-name'

sharedLib = own


pipeline {
    options {
        buildDiscarder logRotator(daysToKeepStr: '6', numToKeepStr: '30')
    }

    agent any

    /* tools {
        maven 'Maven3.6.3'
    } */

    stages {
        /* stage('Clone sources') {
            steps {
                git url: 'https://github.com/demitt/devops-course-app.git', branch: 'shared-libraries'
            }
        } */

        stage('Build') {
            steps {
                withMaven(maven: 'Maven3.6.3', jdk: "${jdk}", mavenOpts: '-Dmaven.test.skip=true') {
                    script {
                        sharedLib.buildArtifacts()
                    }
                }
            }

            post {
                unsuccessful {
                    sayAboutError(repoName)
                }
            }
        }

        stage('Build Image') {
            when { expression { return needDeploying } }

            steps {
                script {
                    def tag = sharedLib.getTag()
                    buildImage(tag)
                }
            }

            post {
                unsuccessful {
                    sayAboutError(repoName)
                }
            }
        }

        stage('Push to AWS') {
            when { expression { return needDeploying } }

            steps {
				script {
					pushImageToAWS()
				}
            }

            post {
                unsuccessful {
                    sayAboutError(repoName)
                }
            }
        }
    }
}

def buildImage(tag) {
    echo "Build image with tag = ${tag}..."
}

def sayAboutError(repoName) {
    echo "${repoName} BUILD FAILED: branch: ${env.GIT_BRANCH}"
}

def pushImageToAWS() {
    echo "Publish image..."
}
