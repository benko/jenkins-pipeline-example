pipeline {
    agent { node { label "maven" } }
    parameters {
        string (name: 'INVOKER', defaultValue: 'John')
        string (name: 'BRANCH', defaultValue: '')
    }
    environment {
        BRANCH_NAME = "main"
    }
    stages {
        stage("Announce our start") {
            steps {
                echo "Hello ${params.INVOKER}, about to build Greeter service."
            }
        }
        stage("Ask which branch of the repository to clone if not set") {
            when {
                expression { params.BRANCH == '' }
            }
            steps {
                script {
                    timeout(time: 60, unit: "SECONDS") {
                        try {
                            def response = input(
                                message: "What is the branch to clone (default is \"main\")?",
                                ok: "Proceed",
                                parameters: [
                                    string(name: "BRANCH_NAME",
                                            description: "The name of the branch to clone",
                                            defaultValue: "main")
                                ]
                            )
                            BRANCH_NAME = response.BRANCH_NAME
                            echo "Got input response: ${response}"
                            echo "Cloning branch ${response.BRANCH_NAME}"
                        } catch (exc) {
                            echo "User input timed out: ${exc}"
                            echo "Proceeding with defaults."
                        }
                    }
                    echo "After INPUT stage: cloning ${BRANCH_NAME}"
                }
            }
        }
        stage ("Clone git repository") {
            steps {
                echo "Cloning branch ${BRANCH_NAME}"
            }
        }
    }
}