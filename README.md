# Table of Content

- [Table of Content](#table-of-content)
- [Versions](#versions)
- [Install Maven](#install-maven)
- [Install Docker](#install-docker)
- [Create Docker Repository](#create-docker-repository)
- [Install Homebrew](#install-homebrew)
- [Install Jenkins](#install-jenkins)
- [Create JAR using Jenkins Job](#create-jar-using-jenkins-job)

# Versions

- OS: MacOS Ventura (13.1)
- VS Code: 1.74.2
- Apache Maven: 3.8.7
- Docker: 20.10.21
- Java: 19.0.1

# Install Maven

# Install Docker

You cand install *Docker* from [here](https://www.docker.com)

# Create Docker Repository

# Install Homebrew

You can install *Homebrew* from [here](https://brew.sh)

# Install Jenkins

1. Install Jenkins
   
```
brew install jenkins-lts
```
2. Run Jenkins

```
brew services start jenkins-lts
```

3. Follow the instructions from [here](https://github.com/ceoca-ovidiu/jenkins/blob/main/README.md) to configure Jenkins

4. If you get some kind of error with Jenkins not starting up just use

```
brew services restart jenkins-lts
```

! On "Unlock Jenkins" page is the path where you can find the initial password that you need to use.

# Create JAR using Jenkins Job

1. On the top left cornet press **New Item**
2. Enter a name and choose **Freestyle Project**
3. Enter the link to your project in **GitHub project**
   
![jenkins_github_project](media/jenkins_github_project.png)

4. In **Source Code Management** add the *Git repository* and change the branch from *master* to *main*

![jenkins_git_repo](media/jenkins_git_repo.png)

5. In the **Build Triggers** section, check *Poll SCM* to check for repo update and add the [CRON](https://en.wikipedia.org/wiki/Cron#CRON_expression) syntax.

![jenkins_build_trig](media/jenkins_build_trig.png)

6. In **Build Steps** section, select *Execute shell* and insert the following lines of code

```
cd /Users/ovidiuceoca/.jenkins/workspace/Jenkins-docker/server
source ~/.bash_profile
mvn clean install
```

![jenkins_build_steps](media/jenkins_build_steps.png)

> NOTE: Change the lines according to your setup. 