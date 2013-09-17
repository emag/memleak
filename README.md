# memleak

## What is this?

A memory leak web application.

## Required

* JDK 7
* Maven 3.x
* Tomcat 7
* curl

## Usage

build this project and deploy on Tomcat or use embedded Jetty.

### Using Embedded Jetty

~~~
$ mvn clean package exec:java
~~~
Jetty listens HTTP Port on `8080` by default.

### Execute HTTP Request

Now running app, execute following command.

~~~
$ curl -X POST --dump-header ~/tmp/headers http://localhost:8080/memleak/increasing-session
$ curl -X POST -b ~/tmp/headers http://localhost:8080/memleak/increasing-session
~~~

And watch the standard output(catalina.out).

~~~
09:49:46.897 [http-bio-8080-exec-1] INFO  o.e.p.m.IncreasingSessionServlet - [Created new session]
09:49:47.673 [http-bio-8080-exec-1] INFO  o.e.p.m.IncreasingSessionServlet - Adding object num: 10000000
09:49:47.674 [http-bio-8080-exec-1] INFO  o.e.p.m.IncreasingSessionServlet - Used  Heap Memory: 336 MB
09:49:47.675 [http-bio-8080-exec-1] INFO  o.e.p.m.IncreasingSessionServlet - Total Heap Memory: 766 MB
09:49:47.675 [http-bio-8080-exec-1] INFO  o.e.p.m.IncreasingSessionServlet - Free  Heap Memory: 430 MB
09:49:47.675 [http-bio-8080-exec-1] INFO  o.e.p.m.IncreasingSessionServlet - Max   Heap Memory: 766 MB



09:50:15.608 [http-bio-8080-exec-3] INFO  o.e.p.m.IncreasingSessionServlet - Adding object num: 10000000
09:50:15.608 [http-bio-8080-exec-3] INFO  o.e.p.m.IncreasingSessionServlet - Used  Heap Memory: 559 MB
09:50:15.608 [http-bio-8080-exec-3] INFO  o.e.p.m.IncreasingSessionServlet - Total Heap Memory: 766 MB
09:50:15.609 [http-bio-8080-exec-3] INFO  o.e.p.m.IncreasingSessionServlet - Free  Heap Memory: 207 MB
09:50:15.609 [http-bio-8080-exec-3] INFO  o.e.p.m.IncreasingSessionServlet - Max   Heap Memory: 766 MB



Exception in thread "http-bio-8080-exec-5" java.lang.OutOfMemoryError: Java heap space
[...]
~~~


## Option

You can change some values by giving JAVA_OPTS.

* Adding object num
 * `org.emamotor.perf.memleak.adding_object_num`
 * Default: 10,000,000
* Jetty port 
 * `org.emamotor.perf.memleak.util.jetty_port`
 * Default: 8080
* Jetty log level(suports only INFO and DEBUG)
 * `org.emamotor.perf.memleak.util.jetty_log_level`
 * Default: INFO
