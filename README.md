# android-seed
This android seed project is developed to start developing any android app without worrying about the basic architectural setup.
* This project follows MVP (Model-View-Presenter) pattern which is the best pattern to follow in the current scenario of app     development.
* It uses <a href="http://www.vogella.com/tutorials/Dagger/article.html ">Dagger2</a> for Dependency Injection. 
  For details on MVP refer to <a href="https://www.captechconsulting.com/blogs/a-mvp-approach-to-lifecycle-safe-requests-with-   retrofit-20-and-rxjava"> MVP Approach using Retrofit and RxAndroid</a>
* It uses  <a href="https://square.github.io/retrofit/">Retrofit</a> for network calls.
* It uses  RxAndroid with RxJava for observables to do all the background tasks. Read more about observables 
 <a href="https://github.com/ReactiveX/RxAndroid">here</a>.
* Picasso is also added in case image downloading is required.
* It uses <a href="http://jakewharton.github.io/butterknife/">Butter Knife</a> for injecting views and adding click listeners.

How to start developing your app: 
* Go through all the links provided above.
* Clone or Download the project. 
* Change the package name as per your requirement. Follow <a href= "http://stackoverflow.com/questions/16804093/android-studio-rename-package" >this</a> if you face any diffculty. 
* Start creating and adding your files as required. 
* This project contains a basic activity to enter some text and fetch all the states matching that text within india and shows them in a recycler view in different activity. 
* If you have gone through above links it would be very easy to understand the approach and future development will be easy.
* I have kept all the views in "view" package, presenter logic in "presenter" package and network calls in "model" package.

Happy coding.
Contributors are most welcome. 


