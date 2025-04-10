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
        stage("Clone the repository") {
            environment {
                BRANCH_NAME = "main"
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
                }
                echo "After INPUT stage: cloning ${BRANCH_NAME}"
            }
        }
    }
}