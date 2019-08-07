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
        nodejs('nodejs-new') 
    }
    steps {
        dockerBuildAndPublish {
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
