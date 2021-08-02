libraries {
    aws
}

application_environments {
    dev {
        longName = "Config from application repo"
        mavenVersion = "Maven3.6.3"
        JDK = "JDK11"
        url = "https://github.com/demitt/devops-course-app.git"
    }
}
