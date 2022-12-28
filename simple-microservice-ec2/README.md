  
# Launch an EC2 Instance

3. Open the Amazon EC2 console at [https://console.aws.amazon.com/ec2/](https://console.aws.amazon.com/ec2/).
    
2.  From the EC2 console dashboard, in the **Launch instance** box, choose **Launch instance**, and then choose **Launch instance** from the options that appear.
    
3.  Under **Name and tags**, for **Name**, enter a descriptive name for your instance.
    
4.  Under **Application and OS Images (Amazon Machine Image)**, do the following:
    
    1.  Choose **Quick Start**, and then choose Amazon Linux. This is the operating system (OS) for your instance.
        
    2.  From **Amazon Machine Image (AMI)**, select an HVM version of Amazon Linux 2. Notice that these AMIs are marked **Free tier eligible**. An _Amazon Machine Image (AMI)_ is a basic configuration that serves as a template for your instance.
        
5.  Under **Instance type**, from the **Instance type** list, you can select the hardware configuration for your instance. Choose the `t2.micro` instance type, which is selected by default. The `t2.micro` instance type is eligible for the free tier. In Regions where `t2.micro` is unavailable, you can use a `t3.micro` instance under the free tier. 
    
6.  Under **Key pair (login)**, for **Key pair name**, choose the key pair that you created when getting set up.
	Create a key pair here, it will download a .pem file with the name you typed. This step will be helpful to connect to the EC2 instance which you have created.
   
7.  Next to **Network settings**, choose **Edit**. For **Security group name**, you'll see that the wizard created and selected a security group for you. You can use this security group, or alternatively you can select the security group that you created when getting set up using the following steps:
    
    1.  Choose **Select existing security group**.
        
    2.  From **Common security groups**, choose your security group from the list of existing security groups.
        
8.  Keep the default selections for the other configuration settings for your instance.
    
9.  Review a summary of your instance configuration in the **Summary** panel, and when you're ready, choose **Launch instance**.
    
10.  A confirmation page lets you know that your instance is launching. Choose **View all instances** to close the confirmation page and return to the console.
    
11.  On the **Instances** screen, you can view the status of the launch. It takes a short time for an instance to launch. When you launch an instance, its initial state is `pending`. After the instance starts, its state changes to `running` and it receives a public DNS name. If the **Public IPv4 DNS** column is hidden, choose the settings icon in the top-right corner, toggle on **Public IPv4 DNS**, and choose **Confirm**.
    
12.  It can take a few minutes for the instance to be ready for you to connect to it. Check that your instance has passed its status checks; you can view this information in the **Status check** column.


# Connect to EC2 Instance - Windows OpenSSH


### Verify PowerShell prerequisites

To install OpenSSH on your Windows OS using PowerShell, you must be running PowerShell version 5.1 or later, and your account must be a member of the built-in Administrators group. Run `$PSVersionTable.PSVersion` from PowerShell to check your PowerShell version.

To check whether you are a member of the built-in Administrators group, run the following PowerShell command:

```cmd
(New-Object Security.Principal.WindowsPrincipal([Security.Principal.WindowsIdentity]::GetCurrent())).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
```

If you are a member of the built-in Administrators group, the output is `True`.

### Install OpenSSH for Windows using PowerShell

To install OpenSSH for Windows using PowerShell, run the following PowerShell command:

```bash
Add-WindowsCapability -Online -Name OpenSSH.Client~~~~0.0.1.0
```

Expected output:

```echo
Path          : 
Online        : True 
RestartNeeded : False
```

### Connect to the EC2 instance

-   Get the public DNS name of the instance.
     You can get the public DNS for your instance using the Amazon EC2 console. Check the **Public IPv4 DNS** column. 
    
-   Get the default user name for the AMI that you used to launch your instance:
        - For Amazon Linux 2 or the Amazon Linux AMI, the user name is `ec2-user`.
        - For a CentOS AMI, the user name is `centos` or `ec2-user`.
		- For a Debian AMI, the user name is `admin`.
		- For a Fedora AMI, the user name is `fedora` or `ec2-user`.
		- For a RHEL AMI, the user name is `ec2-user` or `root`.
		- For a SUSE AMI, the user name is `ec2-user` or `root`.
		- For an Ubuntu AMI, the user name is `ubuntu`.
		- For an Oracle AMI, the user name is `ec2-user`.
		- For a Bitnami AMI, the user name is `bitnami`.

###### To connect to EC2 instance using OpenSSH

1.  In PowerShell or the Command Prompt, use the **ssh** command to connect to the instance. You specify the path and file name of the private key (`.pem`), the user name for your instance, and the public DNS name for your instance. 
        
```bash
ssh -i /path/key-pair-name.pem instance-user-name@instance-public-dns-name
```

2.  Enter `yes`.
    
    You see a response like the following:

```echo
Warning: Permanently added 'ec2-3-88-178-225.compute-1.amazonaws.com' (ED25519) to the list of known hosts.
```

### Copy the build jar to EC2 Instance

Using SCP, copy the file to EC2 Instance

```shell
scp -i /path/key-pair-name.pem <spring-boot-jar> instance-user-name@instance-public-dns-name:~
```

### Connect to EC2 instance using SSH

```bash
ssh -i /path/key-pair-name.pem instance-user-name@instance-public-dns-name
```

Install Java 17 on EC2 Instance

```shell
sudo yum install java-17-amazon-corretto
```

Install Redis on EC2 Instance

```shell
sudo amazon-linux-extras install epel
sudo yum update
sudo yum install redis
```

To start/stop/status Redis server

```shell
sudo service redis status
sudo service redis start
sudo service redis stop
```

Change the permission of the jar file

```shell
chmod 700 simple-microservice-ec2-0.0.1.jar
```

### Run the SpringBoot executable jar

```shell
java -jar simple-microservice-ec2-0.0.1.jar
```

Hit the end point

```echo
http://<public-ipv4-address>:8080/car
```

will give an empty array

If its not accessible, edit the Inbound Rules to enable 8080 port under Security Groups

Execute the POST request ([Click here](http://<public-ipv4-address>:8080/car)) with few data like below:

```json
{ 
	"id":1, 
	"name":"CLA", 
	"manufacturer":"Mercedes", 
	"type":"Saloon" 
}
```

After this the GET endpoint (http://<public-ipv4-address>:8080/car) will returned the posted records.

# Terminate EC2 instance

1.  In the navigation pane, choose **Instances**. In the list of instances, select the instance.
    
2.  Choose **Instance state**, **Terminate instance**.
    
3.  Choose **Terminate** when prompted for confirmation.
    
    Amazon EC2 shuts down and terminates your instance. After your instance is terminated, it remains visible on the console for a short while, and then the entry is automatically deleted. You cannot remove the terminated instance from the console display yourself.