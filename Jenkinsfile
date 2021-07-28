import java.text.SimpleDateFormat

def repoName = "devops-course-app"
def needDeploying = env.BRANCH_NAME == 'master' || env.BRANCH_NAME != 'develop'
def jdk = 'JDK8'
def imageName = 'owner/image-name'

pipeline {
    options {
        buildDiscarder logRotator(daysToKeepStr: '6', numToKeepStr: '30')
    }

    agent any

    stages {
        stage('Build') {
            steps {
                withMaven(maven: 'Maven3.6.3', jdk: "${jdk}") {
                	buildArtifacts()
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
					def tagValue = getTag();
					buildImage(tagValue)
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

def buildArtifacts() {
    sh script: """mvn clean package""",
            label: 'Build artifacts'
}

def getTag() {
    script {
        GIT_COMMIT_HASH_SHORT = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
        DATE_PART = new SimpleDateFormat("YYYYMMdd-'r'HHmm").format(new Date())
        def tagValue = "${DATE_PART}_${GIT_COMMIT_HASH_SHORT}"
    }
    return tagValue
}

def buildImage(tagValue) {
    echo "Build image..."
}

def sayAboutError(repoName) {
    echo "${repoName} BUILD FAILED: branch: ${env.GIT_BRANCH}"
}

def pushImageToAWS() {
    echo "Publish image..."
}