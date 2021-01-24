# Digital Companion - Alexa skill
This code is a backend for Alexa skill called "Digital Companion" which shall handle all requests and will contain the business logic behind skill's behavior

## Steps to set up and build the project

### 1. Build locally
1. Clone the repository, go to the root directory of project.

2. Any Changes can be made as per a requirement using any IDE, open this as gradle project.
   
3. You can use installed gradle or use wrapper to build this project.
  #####
    gradlew clean
    gradlew jar
Or  
  #####
    gradle clean
    gradle jar

4. Find your jar with dependencies in '/build/libs'

### 2. Create the Alexa skill intent model and other configuration
1. Login to [Alexa Skills Kit Developer Console](https://developer.amazon.com/alexa/console/ask).

2. Click on "Create Skill" button and provide the suitable name.

3. On next page, select "Custom" and then click on "Create Skill".

4. Now we're ready to define the interaction model for the skill. Under “Invocation” tab on the left side, define your Skill Invocation Name.

5. From the root of this project, navigate to /models directory, find the JSON representation of intent model. Copy the contents of this file into "Build -> Interaction Model -> JSON Editor" window. Click on "Save Model" and then click on "Build Model" button. 

6. From the home page, in the list of skills created by you, find the new skills we just have created and click on "Copy Skill ID" link to copy the ID to clipboard. 

7. Save this ID as we will need it to link our configuration to hosted code.

### 3. Create the Lambda function on AWS
1. We need to host this code on [AWS Lambda](http://aws.amazon.com/lambda) as a serverless function. Refer [Hosting a custom skill on AWS Lambda](https://developer.amazon.com/en-US/docs/alexa/custom-skills/host-a-custom-skill-as-an-aws-lambda-function.html).

2. When creating a new function - select "Author from scratch" option and select Java8 as runtime.

3. Configure the trigger for "Alexa Skills Kit" and provide the skills ID we copied in last section.

4. Upload the JAR we had built locally as code for this function. 

5. Set the handler for this function. Provide full qualified class name of MainHandler class. Like "com.sci.demo.MainHandler".

6. Finally, copy the ARN of Lambda function and paste it in "Build -> Endpoint" tab in [Alexa Developer Skills Console](https://developer.amazon.com/alexa/console/ask/build/) under "AWS Lambda ARN" section. 

### 4. Test your skill
1. We can now invoke the skills by saying some sample utterances  to trigger intents.

2. For that we can now navigate to the [Test your skill](https://developer.amazon.com/alexa/console/ask/test/) page on developer console. 

3. You can now check the responses and logs for the code we deployed on [AWS Cloudwatch](https://aws.amazon.com/cloudwatch/)

4. Once your conversation session is completed, you can check the cards produced on [Amazon Echo webpage](http://echo.amazon.com/#skills).


## Sample Script 
Following is a sample conversation to test the skill we just have developed and configured! Pat is a dummy user.     
   #### Shopping at Walmart
        Pat     : Alexa, start digital companion
        Alexa   : Welcome to Digital Companion, to start, say 'Create new task'
        Pat     : Create new task
        Alexa   : Ok, sure! Can you please tell me what type of service do you want?
        Pat     : Grocery shopping
        Alexa   : Where do you want this service?
        Pat     : Walmart
        Alexa   : When do you want this service?
        Pat     : Today evening
        Alexa   : Whom shall I call for this task?
        Pat     : Ask Martha
        Alexa   : Do you want to add any notes?
        Pat     : Get some milk and rice
        Alexa   : I have created a new task, I will read it for you once again! 
                  Hello Martha, I need your help for grocery shopping at Walmart at 2021-01-24 EV. 
                  PS: Get some milk and rice.