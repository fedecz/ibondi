## iBondi
### What is iBondi?
iBondi is a social transport tracking system. It works by tracking the location of transport you are on, and publishing it to the server. No private data is sent, just the location of the transport you are traveling on.

### What I can use it for? 
There are two basic use cases for iBondi:

1. Person A hops into a plublic transport, *checks-in* to a given transport, and starts *posting his/her location*.
2. Person B is at a bus stop, waiting for the same transport A's on. B opens the mobile device, opens the map and the transport A is traveling on is showed on the map. Just to be clear, the transport information is displayed. Nothing about A is showed to B.

### Is that it ?
No. This would be the first phase. I have plenty of ideas around this to generate new functionality. But first, it has to do the basic things great.

### I'm a developer, What should I know?
iBondi consists of a **mobile application** (android for now), and a **web application**. In the repository you'll see another projects, like emulator, scripts, docs, etc that are not really part of the *deployables*.

When the mobile app checksin, the server gives back a *sessionId*. That session is valid for the person traveling all the way until he/she finishes his/her trip in that transport. (Multiple transports in users trip would require multiple sessionIds for that user).

Right after the device checkedIn and got an initial location fix, the device starts posting the new locations, along with the sessionId, to the server. The server would collect them and store them in the DB. (right now, [MongoDB](http://www.mongodb.org/)).

When another user wants to get the positions of the transports at a given time, the server would run a mongo-map-reduce query and reduce the sessions to simple transport objects. In other words, the server fetches all the active sessions, runs an [algorithm](http://en.wikipedia.org/wiki/DBSCAN) and returns a list of transports with the current position.

The webapp exposes an API in order to interact with the mobile devices. Such API is something like this:

        public interface MobileInterface {

                public String checkIn(SessionCheckInDTO sessionCheckIn);

                public Boolean postLocation(SessionUpdateDto location);

                public TransportLocationListDTO getLocations(LocationDTO myLocation);
        }

### Building iBondi
iBondi is a regular, multi-module Maven project.

        #build deployables
        $ mvn clean package

        #build all stuff including scripts, emulator and docs
        $ mvn clean package -Pall

### How Should I contribute? 
I don't consider myself an Android Developer, thats why I don't like the code I've written and I encourage anybody to create new frontends, always respecting the API ofcourse.

For now, the API is not secured, but it should before this app goes live. (right, is not live yet.). I haven't put any thought on this. Whether if OpenID or OAuth would be used and that kind of decisions.



