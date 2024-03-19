package resources;



//for enum class, replace word "class" with "enum" below
//enum is a special class in java which has a collection of constants and methods
public enum EnumResource {
//it will take arguments like this :
AddObjectAPI("objects"),
GetObjectAPI("objects"),
PutObjectAPI("objects"),
DeleteObjectAPI("objects");
		
private String resource;
//now we will create constructor of this class that will accept arguments, as all methods are accepting one string, you should also make your condtructor accept a string
EnumResource(String resource) {
this.resource = resource;
}

public String getResource() {
return resource;
}
}
