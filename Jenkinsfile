pipeline {
    agent { node { label "maven" } }
    parameters {
        string (name: 'INVOKER', defaultValue: 'Grega')
        string (name: 'BRANCH', defaultValue: 'main')
        booleanParam (name: 'RESTORE_MAVEN_CACHE', defaultValue: true)
        booleanParam (name: 'BACKUP_MAVEN_CACHE', defaultValue: true)
    }
    environment {
        BRANCH_NAME = "${params.BRANCH}"
        QUAY_CREDS = credentials('QUAY_IO_CREDS')
    }
    stages {
        stage("Ask which branch to clone if not set") {
            when {
                expression { params.BRANCH == '' }
            }
            steps {
                script {
                    timeout(time: 60, unit: "SECONDS") {
                        try {
                            def response = input(
                                message: "What is the branch to clone?",
                                ok: "Proceed",
                                parameters: [
                                    string(name: "BRANCH_NAME",
                                            description: "The name of the branch to clone (default is \"main\")",
                                            defaultValue: "main")
                                ]
                            )
                            BRANCH_NAME = response
                            echo "Got input response: ${response}"
                        } catch (exc) {
                            echo """
                                User input timed out: ${exc}
                                Proceeding with defaults.
                            """
                        }
                    }
                    echo "After INPUT stage: cloning ${BRANCH_NAME}"
                }
            }
        }
        stage("Log start parameters") {
            steps {
                echo """
                    Hello ${params.INVOKER}, about to build Greeter service.

                    Using the following Quay.io credentials:
                      username: ${QUAY_CREDS_USR}
                      password: ${QUAY_CREDS_PSW}
                    
                    Cloning branch "${BRANCH_NAME}"
                """
            }
        }
        stage ("Clone git repository") {
            steps {
                echo "Cloning branch ${BRANCH_NAME} from ${GIT_URL}"
                sh "pwd"
                git url: "${GIT_URL}", branch: "${BRANCH_NAME}"
            }
        }
        stage ("Run unit & code coverage tests") {
            parallel {
                stage ("Run unit tests") {
                    agent { node { label "maven" } }
                    steps {
                        sh '''
                            if [ -e /cache/artifacts.tar.gz ] && [ "${RESTORE_MAVEN_CACHE}" = "true" ]; then
                                echo -n "Restoring maven cache..."
                                mkdir -p /home/jenkins/.m2/repository
                                tar xf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository
                                echo "done."
                            fi
                            ./mvnw test
                            if [ -d /cache ] && [ "${BACKUP_MAVEN_CACHE}" = "true" ]; then
                                echo -n "Backing up maven cache..."
                                tar cf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository ./
                                echo "done."
                            fi
                        '''
                    }
                }
                stage ("Run code coverage") {
                    agent { node { label "maven" } }
                    steps {
                        sh '''
                            if [ -e /cache/artifacts.tar.gz ] && [ "${RESTORE_MAVEN_CACHE}" = "true" ]; then
                                echo -n "Restoring maven cache..."
                                mkdir -p /home/jenkins/.m2/repository
                                tar xf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository
                                echo "done."
                            fi
                            ./mvnw verify
                            if [ -d /cache ] && [ "${BACKUP_MAVEN_CACHE}" = "true" ]; then
                                echo -n "Backing up maven cache..."
                                tar cf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository ./
                                echo "done."
                            fi
                        '''
                    }
                }
            }
        }
        stage("Build the Application") {
            steps {
                sh '''
                    if [ -e /cache/artifacts.tar.gz ] && [ "${RESTORE_MAVEN_CACHE}" = "true" ]; then
                        echo -n "Restoring maven cache..."
                        mkdir -p /home/jenkins/.m2/repository
                        tar xf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository
                        echo "done."
                    fi
                    ./mvnw -DskipTests package
                    if [ -d /cache ] && [ "${BACKUP_MAVEN_CACHE}" = "true" ]; then
                        echo -n "Backing up maven cache..."
                        tar cf /cache/artifacts.tar.gz -C /home/jenkins/.m2/repository ./
                        echo "done."
                    fi
                '''
                archiveArtifacts 'target/*-runner.jar'
            }
        }
    }
}