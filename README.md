# Complex Actions Web Application

The web application which was hosted on Google App Engine. This web application was informational for users to see and read about our project.

If this is still up even today, the application can be found here: https://complexactionproject.appspot.com/

## How to set up Google App Engine

[Google App Engine](https://cloud.google.com/appengine/)
[Google Cloud SDK](https://cloud.google.com/sdk/)

  - I recommend using Eclipse IDE for this, as it provides a nice add-on for working with Google App Engine.
  - Make a new project on Google App Engine, go to console and create a new project, it might take some time to set up.
  - Download the Google Cloud SDK from up above. Follow the set-up tutorial they offer for your system (my case was Windows). Once it is installed and you are able to run commands fine, you can close it since we will be using it through an Eclipse add-on.
  - Install the Google Cloud add-on through Eclipse. Go to help->eclipse marketplace, and search for Google Cloud Tools. Install this add-on. You are probably going to have to restart your IDE.
  - Once that is installed, right click in your project area, you should be able to create a "App Engine Standard" or "App Engine Flexible" environment, based on what you want but in our case we want "App Engine Stantard". If you want to use what we have built, then you will have to clone the repo into your project directory with "git clone xyz", where the xyz is the .git URL.
  - When you are done and want to deploy it, you want to right click your project and select "Deploy to App Engine Standard". If all of the steps have been correct so far, you should be able to see the project you created on Google App Engine and deploy it with a single click.

There is also some extra stuff in here which you might not need/want to repurpose under src/main/java and src/test/java, particularly the Selenium tests. To make those Selenium tests run, you will need Firefox and a gecko driver added to your environment variables. I won't go into too many details, but the link for this stuff is here if you want to use it: https://www.seleniumhq.org/download/