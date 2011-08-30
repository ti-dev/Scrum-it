Scrum-it
========

Scrum-it is a digital Scrum Board developed by BSgroup Technology Innovation AG in Zürich (Switzerland).
It can be used to organize the Scrum process and manage projects, team members, sprints, userstories and tasks.

Do you want to see a demo? Take a look here: http://scrum-it-demo.bsgroupti.ch/

Installation
------------

Installation of Scrum-it is really easy.

### Prerequisites

Before installing Scrum-it, please make sure you have these components installed:
* MySQL 5.5.x
* Tomcat Server 6.x or 7.x

### Installation steps

You only have to do a few steps to get Scrum-it up and running...

Get the sources from http://sourceforge.net/projects/scrum-it/files/ or build your own war file with the source code distrubuted here.
Then drop the scrumit.war file into the webapp folder of your Tomcat.

Next, you have to create a database with the name `scrumit` and configure the MySQL server.
Further you have to edit the file /src/hibernate.cfg.xml. There you have to change the values for connection.url, connection.username and connection.password.
Running Scrum-it for the first time, the database tables will be created automatically by taking the value `create` instead of `none` into the hbm2ddl property.
After that, the database tables are created and you can change back the value to `none`.

Now, start your Tomcat Server and enjoy Scrum-it on every webbrowser (Firefox, Safari, Opera, Internet Explorer 9 and Google Chrome): http://127.0.0.1:8080/scrumit/

Contributing
------------

1. Fork it.
2. Create a branch (`git checkout -b my_scrumit`)
3. Commit your changes (`git commit -am "something added"`)
4. Push to the branch (`git push origin my_scrumit`)
5. Create an [Issue][1] with a link to your branch
6. Enjoy a refreshing Diet Coke and wait

Info
----

If you have any questions, please do not hesitate to contact us <scrum-it AT bsgroup.ch>

[1]: http://github.com/ti-dev/Scrum-it/issues
