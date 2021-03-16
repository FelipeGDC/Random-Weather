
# Random Location API Test application:

Application that will show the weather at a random location.
Architecture approach inspired by DDD and CLEAN concepts.


## Screens 📱
The app currently consists of 1 screen:
- A weather screen, in which the user can visualize the weather at a random point in the world. The location is generated when the app is launched and can be refreshed inside the app..

## Libraries 🛠️:
 - MVVM
 - Flow
 - Dagger
 - Ktlint
 - Android Jetpack
 - Moshi
 - Retrofit

## Structure 🎨

- __Data Layer__: Contains the repositories Implementations and one or multiple Data Sources.
  - __Datasource__: In which we have the source of the data we are going to work with, let it be the API implementation and abstraction, and/or the database. In this case, we have the API call.
   - __Repositories__: Repositories are responsible to coordinate data from the different Data Sources. A sort of abstraction for the data sources in order to avoid working directly with them. We make calls to them and we can ignore whether the data comes from the network or a local database.
- __DI__: the dependency injector package, where the modules and components are created.
- __Domain__: Collection of entity objects and related business logic that is designed to represent the enterprise business model.
  - __Models__: an abstraction of the objects that represent the logic of the project.
  -  __Use cases__: the interactors that define be the business logic of the application.
 - __UI__: with an MVVM pattern, everything is separated as features, the screens and logic behind them are found here.
- __Utils__: A variety of classes, extensions, and helpers to help and use across the application, that not necessarily have anything to do with the logic of the same.

## Testing 🧰
#### (There should totally be more tests, but I didn't have that much time to test every part of the application. I decided to test the repository, usecase and viewmodel.
- JUnit
- Mockito-Kotlin
- Kluent3


