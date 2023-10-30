# jerseyGsonHandler

Attempt to use [jersey](https://eclipse-ee4j.github.io/jersey/) + [GSON](https://github.com/google/gson)

Seems to work when sending/receiving Objects but not when sending strings.

## Build / Run

Project uses [gradle wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) with [gretty](https://plugins.gradle.org/plugin/org.gretty) plugin for running the web-app under [jetty](https://eclipse.dev/jetty/).

```
./gradlew appRun
```

Then, just access in browser http://localhost:8080/jerseyGsonHandler/

index.html page is a simple page with an input where the user can put his name and then press one of two buttons:
- as string - it will submit the name as string and receive a hello message as string as well
```java
    public Response helloStr(String name) {
        String msg = "Hello " + name + "!";
        return Response.ok().entity(msg).build();
    }
```
- as object - it will submit a json object with a name property and response will be a message object
```java
    public Response helloStr(User user) {
        Message msg = new Message("Hello " + user.name() + "!");
        return Response.ok().entity(msg).build();
        }

```

## testing
```System.out.println``` was added to ```HelloResource``` and ```GsonMessageBodyHandler```

### as string
GSON is not being called for String...

#### Browser
For example, fill in ```Ricardo Almeida``` in the input and click ```as string```.

In the network tab it can be seen that the request is ```"Ricardo Almeida"``` and the response is ```Hello "Ricardo Almeida"!``` instead of ```"Hello Ricardo Almeida!"``` or, at most, ```'Hello Ricardo Almeida!'```.

Dialog message shows error message ```Error parsing data```

#### Server

Shows the message from the resource but not from the GSON handler. The string includes double-quotes!!!

```
Got hello string request ["Ricardo Almeida"]
```

### as object
GSON handler is being called correctly

#### Browser
For example, fill in ```Ricardo Almeida``` in the input and click ```as object```.

In the network tab it can be seen that the request is ```{"name":"Ricardo Almeida"}``` and the response is ```{
"message": "Hello Ricardo Almeida!"
}```.

Dialog message correctly shows ```Got: Hello Ricardo Almeida!```

#### Server

Shows the 3 messages as expected (read from GSON handler, resource and write from GSON handler).

```
GsonMessageBodyHandler.readFrom() -= User[name=Ricardo Almeida] =-
Got hello object request [User[name=Ricardo Almeida]]
GsonMessageBodyHandler.writeTo() -= Message[message=Hello Ricardo Almeida!] =-  {"message":"Hello Ricardo Almeida!"}
```