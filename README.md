<h1 align="center">StoreApp</h1>

<p align="center">  
This is an android application where movies in different categories from the internet are shot, listed and added to the watch section later.
<br/>
<p align="center">I used <a href="https://developers.themoviedb.org/3">The Movie Db</a> API for building this application.</p>
</p>


## App Gif
<img src="https://github.com/emirkarabey/MovieApp/blob/master/appgif/movieappgif.gif"  width="25%"/>


#### App Screenshots

| Home | Details Screen | Watch Later Screen |
|:-:|:-:|:-:|
| ![Fist](https://github.com/emirkarabey/MovieApp/blob/master/screenshots/homescreen.png) | ![3](https://github.com/emirkarabey/MovieApp/blob/master/screenshots/detailsscreen.png) | ![3](https://github.com/emirkarabey/MovieApp/blob/master/screenshots/watchlater.png)

## Tech stack
* ✅ MVVM with Clean Architecture
* ✅ [Kotlin Flow][31] - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
* ✅ [Coroutines][51] - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
* ✅ [Paging3][34] - The Paging library helps you load and display pages of data from a larger dataset from local storage or over network. This approach allows your app to use both network bandwidth and system resources more efficiently.
* ✅ [Data Binding][35] - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
* ✅ [Navigation Component][24] - Handle everything needed for in-app navigation. asynchronous tasks for optimal execution.
* ✅ [Safe-Args][25] - For passing data between destinations
* ✅ [Dagger-Hilt][93] - A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
* ✅ [ViewModel][17] - Easily schedule asynchronous tasks for optimal execution.
* ✅ [Retrofit][90]- Retrofit is a REST client for Java/ Kotlin and Android by Square inc under Apache 2.0 license. Its a simple network library that is used for network transactions. By using this library we can seamlessly capture JSON response from web service/web API.
* ✅ [OkHttp][23] - Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
* ✅ [View Binding][11] - a feature that allows you to more easily write code that interacts with views.
* ✅ [Lifecycle][22] - As a user navigates through, out of, and back to your app, the Activity instances in your app transition through different states in their lifecycle.
* ✅ [Room][53] - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* ✅ [Glide][94] for image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.

[11]: https://developer.android.com/topic/libraries/view-binding
[53]: https://developer.android.com/jetpack/androidx/releases/room
[34]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[35]: https://developer.android.com/topic/libraries/data-binding
[93]: https://developer.android.com/jetpack/compose/libraries#hilt
[51]: https://developer.android.com/kotlin/coroutines
[90]: https://square.github.io/retrofit/
[31]: https://developer.android.com/kotlin/flow
[22]: https://developer.android.com/guide/components/activities/activity-lifecycle
[17]: https://developer.android.com/jetpack/compose/state#viewmodel-state
[23]: https://square.github.io/okhttp/
[24]: https://developer.android.com/guide/navigation/navigation-getting-started
[25]: https://developer.android.com/guide/navigation/navigation-pass-data
[94]: https://github.com/bumptech/glide
