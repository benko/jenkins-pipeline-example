pipeline {
    agent { node { label "maven" } }
    stages {
        stage("Announce our start") {
            steps {
                echo "Hello, about to build Greeter service."
            }
        }
    }
}