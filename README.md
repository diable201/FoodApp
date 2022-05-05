# FoodApp
This is an app that makes making food easier and gives you full information about the selected meal including instructions of how to make that meal provided by a video.

## Requirements
1. Maximum one page description of the project, main features
2. Usage of network requests using Retrofit2
3. Usage of local database using Room
4. Usage of dependency injection
5. Well designed architecture MVP or MVVM
6. All features described in step 1 must be finished and tested. Without any
crashes and bugs.
7. GitHub repo with well described Readme.md


# Libraries and technologies used
- Navigation component : one activity contains multiple fragments instead of creating multiple activites.
- Retrofit : making HTTP connection with the rest API and convert meal json file to Kotlin/Java object.
- Room : Save meals in local database.
- MVVM & LiveData : Saperate logic code from views and save the state in case the screen configuration changes.
- Coroutines : do some code in the background.
- view binding : instead of inflating views manually view binding will take care of that.
- Glide : Catch images and load them in imageView.
