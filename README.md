# Kontact

A mapper around Android's Contact APIs

## Import the Dependency [ ![Download](https://api.bintray.com/packages/rubixhacker/Kontact/Kontact/images/download.svg) ](https://bintray.com/rubixhacker/Kontact/Kontact/_latestVersion)

To import the Android SDK, include it as a dependency in your build.gradle file

    compile 'com.hackedcube:kontact:X.X.X'


## Usage

<br>
   
To get all Contacts on the user's device

##### Java
```java
List<Kontact> kontacts = ContactUtils.queryAllContacts(this);
```
##### Kotlin
```kotlin
val context = ...
        
val kontactList: List<Kontact> = context.queryAllContacts()
```

<br>

To get a specific Contact from an id

##### Java
```java
Kontact kontact = ContactUtils.getContactFromId(this, <id>);
```
##### Kotlin
```kotlin    
val context = ...
            
val kontact: Kontact = context.getContactFromId(<id>)
```

<br><br><br>

#### RX Support 

Grab the correct dependency for which version of RxJava you are using

<br>

For RxJava 1

    compile 'com.hackedcube:kontact-rxjava:X.X.X'

<br>

For RxJava 2

    compile 'com.hackedcube:kontact-rxjava2:X.X.X'

<br>

To get all Contacts on the user's device as a Single 

##### Java
```java
Single<List<Kontact>> kontactsSingle = RxContactUtils.allContacts(this);
```
##### Kotlin
```kotlin
val context = ...
        
val kontactListSingle: Single<List<Kontact>> = context.allContacts()
```

<br>

To get a specific Contact from an id

##### Java
```java
Single<Kontact> kontact = RxContactUtils.contact(this, <id>);
```
##### Kotlin
```kotlin    
val context = ...
            
val kontact: Single<Kontact> = context.contact(<id>)
```

<br><br><br><br><br>