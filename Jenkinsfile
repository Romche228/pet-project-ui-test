pipeline {
    agent {
        label 'docker'
    }

    triggers {
        cron('H 5 * * *')
    }
    
    parameters {
        string(name: 'TOKEN', defaultValue: '5652749630:AAFQWK_dyy8IakITOMED9J6LvFYN6n1nDfA', description: 'token for telegram')
        string(name: 'CHAT_ID', defaultValue: '-1001863068714', description: 'chat id for telegram')
        string(name: 'CREDENTIALS_ID', defaultValue: 'jenkins-to-vault', description: 'Jenkins credentials id')
        string(name: 'DIRECTORY_PATH', defaultValue: 'backstage-ui-test', description: 'Enter the directory path')
        string(name: 'CONFIG_FILE_NAME', defaultValue: 'config.json', description: 'Enter the config file name')
        string(name: 'TESTNG_FILE_NAME', defaultValue: 'testng.xml', description: 'Enter the test suite file name')
        string(name: 'RUN_COMMAND', defaultValue: 'mvn -f ${DIRECTORY_PATH}/pom.xml test -DsuiteXmlFile=${TESTNG_FILE_NAME}', description: 'Enter the command to run tests')
    }

  	options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }
  
    environment {
        TOKEN = '5652749630:AAFQWK_dyy8IakITOMED9J6LvFYN6n1nDfA'
        CHAT_ID ='-1001863068714'
        VAULT_URL = "${params.VAULT_URL}"
        CREDENTIALS_ID = "${params.CREDENTIALS_ID}"
        COUNTOUR_CODE = "${DEPLOY_ENV.toLowerCase()}"
        DIRECTORY_PATH = "${params.DIRECTORY_PATH}"
        CONFIG_FILE_NAME = "${params.CONFIG_FILE_NAME}"
        TESTNG_FILE_NAME = "${params.TESTNG_FILE_NAME}"
        RUN_COMMAND = "${params.RUN_COMMAND}"
    }

    stages {
        stage('Generate token') {
            steps {
                script {
                    withCredentials([
                        [
                            $class: 'VaultTokenCredentialBinding',
                            credentialsId: "${CREDENTIALS_ID}",
                            vaultAddr: "https://secret.${COUNTOUR_CODE}.platform.tatar.ru"
                        ]
                    ]) {
                        catchError {
                            sh """
                                #int
                                jq --arg INT \${VAULT_TOKEN} --arg COUNTOUR \${COUNTOUR_CODE} '(.vaultToken = \$INT) | (.contour_code = \$COUNTOUR)' "${DIRECTORY_PATH}"/"${CONFIG_FILE_NAME}" > config_new.json && cat config_new.json > "${DIRECTORY_PATH}"/"${CONFIG_FILE_NAME}"
                            """
                        }
                    }
                }
            }
        }

        stage('Running docker container') {
            agent {
                docker {
                    image 'docker-hub/markhobson/maven-chrome:jdk-19'
                    label 'docker'
                  	args '-v /var/lib/jenkins:/var/lib/jenkins \
                        -v /etc/hosts:/etc/hosts:ro \
                        -v /etc/passwd:/etc/passwd:ro \
                        -v /etc/shadow:/etc/shadow:ro \
                        -v /etc/group:/etc/group:ro \
                        --network host'
                    reuseNode true
                }
            }

            stages {
                stage('Running tests') {
                    steps {
                        script {
                            if ("${DEPLOY_ENV}" == 'STAGE') {
                                TESTNG_FILE_NAME = 'testng-stage.xml'
                                RUN_COMMAND = "mvn -f ${DIRECTORY_PATH}/pom.xml test -DsuiteXmlFile=${TESTNG_FILE_NAME}"
                            }
                        }
                        catchError {
                            sh """
                            	${RUN_COMMAND}
                            """
                        }
                    }
                }
            }
        }

        stage('Allure report creation') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [[key: 'allure.results.directory', value: "${DIRECTORY_PATH}/target/allure-results"]],
                    report: "${DIRECTORY_PATH}/allure-report",
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: "${DIRECTORY_PATH}/target/allure-results"]]
                ])

                catchError {
                    sh "rm -rf ${DIRECTORY_PATH}/target/allure-results"
                }
            }
        }
      
      stage('Sending notifications') {
            steps {
                echo 'Отправка уведомления в Telegram канал'
            }
            post {
                // Cleaning up after building and pushing
                always {
                    cleanWs()
                    sh 'docker image prune -af'
                }

                failure {
                    sh("""
                        curl -s -X POST https://api.telegram.org/bot${TOKEN}/sendMessage -d chat_id=${CHAT_ID} -d parse_mode=markdown -d text='*${env.BUILD_URL}* : FAILURE'
                    """)
                }
            }
        }
    }
}

