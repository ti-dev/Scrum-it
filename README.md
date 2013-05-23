Scrum-it
========

Scrum-it is a digital Scrum Board developed by ti&m AG in Zurich (Switzerland).

It can be used to organize the Scrum process and manage projects, team members, sprints, userstories and tasks.

Technology
----------

Used technologies:

* HTML5 / CSS3
* JavaScript / jQuery / jQuery Mobile
* JSON (JavaScript Object Notation)
* Java EE with Spring
* MySQL / Hibernate / Envers

Installation
------------

Installation of Scrum-it is really easy.

### Prerequisites

Before installing Scrum-it, please make sure you have these components installed:

* [MySQL][2] [5.5.x][6]
* [Tomcat Server][3] [6.x][4] or [7.x][5]

### Installation steps

You only have to do a few steps to get Scrum-it up and running...

Get the sources from http://sourceforge.net/projects/scrum-it/files/ or build your own war file with the source code distrubuted here.
Then drop the scrumit.war file into the webapp folder of your Tomcat.

Next, you have to create a database with the name `scrumit` and configure the MySQL server.
Further you have to edit the file `/src/hibernate.cfg.xml`. There you have to change the values for `connection.url`, `connection.username` and `connection.password`.
Running Scrum-it for the first time, the database tables will be created automatically by taking the value `create` instead of `none` into the `hbm2ddl` property.
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

More information about the project
----------------------------------

Feel free to make changes and contribute to the project.

We have a video tutorial (german language): http://www.youtube.com/watch?v=zfcZF1EeY7Q

We also have a nice little movie about Scrum-it: http://www.youtube.com/watch?v=Hp9wMQkmYDw
[1]: http://github.com/ti-dev/Scrum-it/issues
[2]: http://www.mysql.com/
[6]: http://www.mysql.com/downloads/mysql/
[3]: http://tomcat.apache.org/
[4]: http://tomcat.apache.org/download-60.cgi
[5]: http://tomcat.apache.org/download-70.cgi

Support
-------

Unfortunately, I do not have the time to support this project anymore.
