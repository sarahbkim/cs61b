// ------------------ //
// First Lecture      //
// ------------------ //
// must declare any var before you can use it
// Everything in Java has a type and I need to declare it
//String myString; // declares a variable called myString with type String
//myString = new String(); // creates a new String object
//
//
// ------------------ //
// Objects & Methods  //
// ------------------ //
//String s1; // declare the var
//s1 = new String(); // create a new String obj and assign to s1
//String s2 = new String(); // declare and create at the same time
//
//s1 = "Yow!";
//s2 = s1 // s1 and s2 reference same obj
//s2 = new String(s1); // now 2 different, identical objs
//
// 3 string constructors
//new String(); // constructs an empty string
//"whatever" // automatically constructs a string
//new String(s1); // invoke new String with a parameter
//
// all objects -- constructors always have same name as their class
// need double quotes for strings
//s2 = s1.toUppercase();
//String s3 = s2.concat("!!") // returns "YOW!!!"
//String s3 = s2 + "!!!"; // can also write this way
//String s4 = "*".concat(s2).concat("*"); // returns *YOW!*
//String s4 = "*" + s2 + "*";
//
// ------------------ //
// Java I/O Classes   //
// ------------------ //
// In System class, for interacting with a user
//System.out is a PrintStream object
//System.in is an InputStream object
//readLine // defined on BufferedReader Object
// BufferedReader needs an InputStreamReader needs an InputStream needs System.in
//
//import java.io*
//class SimpleIO {
//	// a method in the class SimpleIO
//	public static void main(String[] args) throws Exception {
//		// declare BufferedReader variable
//		BufferedReader keybd = new BufferedReader(new InputStreamReader(System.in));
//		// System.in is the keyboard input
//		System.out.println(keybd.readLine());
//	}
//}
// to use the Java library, other than java.lang, you need to import those libraries
// any java program always begins with a method called main()
//
//
// ---------------- //
// Defining Classes //
// ---------------- //
// fields == vars stored inside objects; aka instance vars; used to store data;
// constructors help initialize fields

// "this" keyword refers to this object that you're in at the moment.
// "static" field: a single var shared by the whole class of objects, i.e. class var