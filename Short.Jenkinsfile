@Library('pipeline-shared-library-example@2d42460') _

def props = [
    repoName: "devops-course-app",
    jdk: 'JDK8',
    imageName: 'owner/image-name',
    isImagePushRequired: env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'develop' || true,
    notificationSlackTarget: '#finance_manager_back-end',

    isJenkinsfile: true
]

wholePipeline props
