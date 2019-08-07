job('NodeJS example') {
    scm {
        git('git://github.com/manorbar/docker-cicd.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('Node11101') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        shell("npm install")
    }
}

job('NodeJS Docker example') {
    scm {
        git('git://github.com/manorbar/docker-cicd.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('Node11101') 
    }
    steps {
        dockerBuildAndPublish {
            buildContext('./basics')
            repositoryName('manorbar/docker-cicd') //qa / dev
            tag('${GIT_REVISION,length=9}')
            registryCredentials('manorbar')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

    
pipelineJob('pipeline-boilerplate'){
    def repo = 'https://github.com/jenkinsci/job-dsl-plugin.git'
    
        triggers {
        scm('H/5 * * * *')
    }
    description("pipeline example")
        definition {
        cpsScm {
            scm {
                git{
                    remote {url(repo)}
                    branches('master', '**/feature*')
                    scriptPath('./basics/misc/jenkinsfile')
                    extensions{}
                    
            }
        }
    }
}
}
pipelineJob('pipeline-boilerplate'){
    def repo = 'https://github.com/jenkinsci/job-dsl-plugin.git'
    
        triggers {
        scm('H/5 * * * *')
    }
    description("pipeline example")
        definition {
        cpsScm {
            scm {
                git{
                    remote {url(repo)}
                    branches('master', '**/feature*')
                    scriptPath('./basics/misc/jenkinsfile')
                    extensions{}
                    
            }
        }
    }
}
}

