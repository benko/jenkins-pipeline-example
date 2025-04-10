pipeline {
    agent { node { label "maven" } }
    parameters {
        string (name: 'INVOKER', defaultValue: 'John')
    }
    stages {
        stage("Announce our start") {
            steps {
                echo "Hello ${params.INVOKER}, about to build Greeter service."
            }
        }
    }
}