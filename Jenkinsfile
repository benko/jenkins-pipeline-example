pipeline {
    agent { node { label "maven" } }
    parameters {
        string (name: 'INVOKER', defaultValue: 'Grega')
        string (name: 'BRANCH', defaultValue: 'main')
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
                            BRANCH_NAME = response
                            echo "Got input response: ${response}"
                            // echo "Cloning branch ${response}"
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
                echo "Cloning branch ${BRANCH_NAME} from ${GIT_URL}"
                git url: "${GIT_URL}", branch: "${BRANCH_NAME}"
            }
        }
        stage ("Run unit & code coverage tests") {
            parallel {
                stage ("Run unit tests") {
                    agent { node { label "maven" } }
                    steps {
                        sh '''
                            if [ -e /cache/artifacts.tar.gz ]; then
                                mkdir -p /home/jenkins/.m2/repository
                                tar xf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository
                            fi
                            ./mvnw test
                            if [ -d /cache ]; then
                                tar cf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository ./
                            fi
                        '''
                    }
                }
                stage ("Run verify - code coverage") {
                    agent { node { label "maven" } }
                    steps {
                        sh '''
                            if [ -e /cache/artifacts.tar.gz ]; then
                                mkdir -p /home/jenkins/.m2/repository
                                tar xf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository
                            fi
                            ./mvnw verify
                            if [ -d /cache ]; then
                                tar cf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository ./
                            fi
                        '''
                    }
                }
            }
        }
    }
}