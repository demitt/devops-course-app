@Library('pipeline-shared-library-example@94f7408') _

def props = [
    repoName: "devops-course-app",
    jdk: 'JDK8',
    imageName: 'owner/image-name',
    isImagePushRequired: env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'develop' || true
]

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
                withMaven(maven: 'Maven3.6.3', jdk: "${props.jdk}", mavenOpts: '-Dmaven.test.skip=true') {
                    script {
                        sharedLib.buildArtifacts()
                    }
                }
            }

            post {
                unsuccessful {
                    script {
                        sharedLib.sayAboutError(props)
                    }
                }
            }
        }

        stage('Build Image') {
            when { expression { return props.isImagePushRequired } }

            steps {
                script {
                    def tag = sharedLib.getTag()
                    sharedLib.buildImage(tag)
                }
            }

            post {
                unsuccessful {
                    script {
                        sharedLib.sayAboutError(props)
                    }
                }
            }
        }

        stage('Push to AWS') {
            when { expression { return props.isImagePushRequired } }

            steps {
                script {
                    sharedLib.pushImageToAWS()
                }
            }

            post {
                unsuccessful {
                    script {
                        sharedLib.sayAboutError(props)
                    }
                }
            }
        }
    }
}
