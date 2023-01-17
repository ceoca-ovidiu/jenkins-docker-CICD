# Table of Content

- [Table of Content](#table-of-content)
- [Versions](#versions)
- [Install Maven](#install-maven)
- [Install Docker](#install-docker)
- [Create Docker Repository](#create-docker-repository)
- [Install Homebrew](#install-homebrew)
- [Install Jenkins](#install-jenkins)
- [Jenkins Plugins](#jenkins-plugins)
- [(*optional*) Jenkins Global Tool Configuration](#optional-jenkins-global-tool-configuration)
- [Create JAR using Jenkins Job](#create-jar-using-jenkins-job)
- [Create Docker Container from the JAR](#create-docker-container-from-the-jar)
- [Create Pipeline to send email](#create-pipeline-to-send-email)
- [Errors](#errors)

# Versions

- OS: MacOS Ventura (13.1)
- VS Code: 1.74.2
- Apache Maven: 3.8.7
- Docker: 20.10.21
- Java: 19.0.1

# Install Maven

1. Go to [here](https://maven.apache.org/download.cgi) and download the **Binary tar.gz archive** of the latest version
![maven_download](media/maven_download.png)

2. Extract it using the following command

    ```
    tar -xvf apache-maven-3.8.7-bin.tar.gz
    ```

3. Take the path to your Apache Maven folder

4. Open a Terminal and enter the following command

    ```
    nano ~/.bash_profile
    ```
5. Here paste the following lines of code. Change the path with your personal Apache Maven path.
   
    ```
    export M2_HOME=/Users/ovidiuceoca/Documents/Maven/apache-maven-3.8.7
    export PATH=$PATH:$M2_HOME/bin
    ```

6. Press **Control+X > Y > Return/Enter**

7. In the same terminal run the following command

    ```
    source ~/.bash_profile
    ```

8. If there are cases when you get the error **mvn: command not found** just run in a terminal the command above to update the *bash_profile*
   
9. To see if everything is ok run the following command

    ```
    mvn -version
    ```

![maven_version](media/maven_version.png)

# Install Docker

You cand install *Docker* from [here](https://www.docker.com)

# Create Docker Repository

1. Go to this [link](https://hub.docker.com)
2. You should see something like this
![docker_no_repo](media/docker_no_repo.png)
3. Click **Create repository** and give it a name
![docker_repo](media/docker_repo.png)

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

! Jenkins page can be found [here](http://localhost:8080)

# Jenkins Plugins

1. Go to Jenkins *Dashboard*
2. On the left side, click on **Manage Jenkins**
3. Click on **Manage Plugins**
4. Here click on **Available Plugins**
5. Install the following *Docker* plugins and **Restart** Jenkins
![jenkins_docker_plugin](media/jenkins_docker_plugins.png)

# (*optional*) Jenkins Global Tool Configuration

Here you can install Docker and Maven directly into Jenkins.

![jenkins_global_tool_config](media/jenkins_global_tool_config.png)

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
![jenkins_build_steps](media/jenkins_build_steps.png)
    ```
    cd /Users/ovidiuceoca/.jenkins/workspace/Jenkins-docker/server
    source ~/.bash_profile
    mvn clean install
    ```

7. Select the job from the **Dashboard > Workspace (left side) > server > target**. Here you can see the jars created.
![jenkins_created_jar](media/jenkins_created_jar.png)

> NOTE: Change the lines according to your setup. 

# Create Docker Container from the JAR
1. Make sure that *Docker* is running in background
2. Select the job and click on **Configure**
3. Just add the following lines of code in **Build Steps** 
![jenkins_docker_image](media/jenkins_docker_image.png)
    ```
    cd ..
    docker build -t server-image .
    docker container create --name server-container server-image
    ```
4. There should be some error regarding *Docker* like **docker: command not found**, but you can follow the optional step above to install it in *Jenkins* and after that restart both platforms.
5. After running the job again, the docker starts creating the image   
![jenkins_docker_success](media/jenkins_docker_success.png)
6. And finally, open the terminal and enter the following command
    ```
    docker ps
    ```
7. You can see that the container is running. This is ok because that is what we wanted, but because is a Spring project, it means that the Jenkins job will never end so we need to create the container, but do not run it.
![docker_running_container](media/docker_running_container.png)

8. If you get an error like this, it is because there is already a container with that name on docker so to avoid this problem, the build steps should be changed. Firstly, the container should be stopped and deleted, and then rebuilt.:
![docker_duplicate_container](/media/docker_duplicate_container.png)

9. Change the code to the one below. I also added some *echo* commands to let the user know what is happening behind the scene.
![docker_duplicate_container_solution](media/docker_duplicate_container_solution.png)

    ```
    cd /Users/ovidiuceoca/.jenkins/workspace/Jenkins-docker/server
    source ~/.bash_profile
    mvn clean install
    cd ..
    echo "Building the image..."
    docker build -t server-image .
    echo "Stopping and removing already running container..."
    docker stop server-container || true && docker rm server-container || true
    echo "Creating the container..."
    docker container create --name server-container server-image
    echo "Container created. It is ready to run"
    ```

10. Now we have a created container but it is not running so open a terminal and run the following command:
    ```
    docker start server-container
    ```

11. Now the container is running and everytime you push something to the git repo, the job will activate and create a new image and container based on the code you pushed.

# Create Pipeline to send email

1. Create a pipeline from **Dashboard > New Item (top left) > Pipeline > OK**
2. Check **GitHub project** and **Poll SCM** and complete the fields (see above) and click **Save**
3. Now you need to install email *Jenkins Plugin*
4. Go **Dashboard > Manage Jenkins > Manage Plugins > Available Plugins > Email Extension Plugin > Install without restart > Restart Jenkins**
5. Go **Manage Jenkins-> Configure System > E-mail Notification** (! NOT EXTENDED EMAIL NOTIFICATION !)
6. Fill the following fields
![jenkins_email](/media/jenkins_email.png)
# Errors
![java_error_01](media/java_error_01.png)