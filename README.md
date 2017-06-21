# Kontact

A mapper around que

## Import the Dependency [ ![Download](https://api.bintray.com/packages/rubixhacker/Kontact/Kontact/images/download.svg) ](https://bintray.com/rubixhacker/Kontact/Kontact/_latestVersion)

To import the Android SDK, include it as a dependency in your build.gradle file

    compile 'com.hackedcube:kontact:X.X.X'


## Usage

To get all Contacts on the user's device

#####Java
    List<Kontact> kontacts = ContactUtils.queryAllContacts(this);

#####Kotlin
    val context = ...
        
    val kontactList: List<Kontact> = context.queryAllContacts()
    
    
    
To get a specific Contact from an id

#####Java
    Kontact kontact = ContactUtils.getContactFromId(this, <id>);
    
#####Kotlin
    
     val context = ...
            
     val kontact: Kontact = context.getContactFromId(<id>)