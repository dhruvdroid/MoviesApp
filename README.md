# Android MVVM Architecture (HILT)

Consist of three main layer (3 Project modules with dependency between them)
- Presentation - UI (Activity, Fragment, ViewModel, ViewModelFactory)
- Domain (Usecase)
- Data - Data source (Rest API, Local DB)

Reference : https://antonioleiva.com/clean-architecture-android/

Dependencies in Use
- Hilt (Dependecy Injection)
- LiveData + ViewModel
- Coroutines
- Kotlinx serialization (Json Serialization Deserialization)
- Retrofit (Rest API Library)
- Coil (Image Loader)
- Navigation Components

Improvisation
- Pressing back button fragment is again getting reloaded. Need to check navigation component for the same. 
- Removing the actors default placeholder with some actual refernce and default launching of RV with scroll to first position
- Addition of the ROOM DB

