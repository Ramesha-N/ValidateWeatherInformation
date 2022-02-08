# ValidateWeatherInformation

Please clone repo from master branch.

**To execute test scripts, Run command** : mvn clean install -Dtest.environment=DEV

**To execute from eclipse, Run using Maven Build** :  clean install -Dtest.environment=DEV

Created Maven project, which downloads all dependencies from Maven repository.
Used libraries like TestNG, Selenium WebDriver, RestAssured, Jacksons for Data Binding etc

Refer **src/test/resources/config/environment.properties** file for test data i.e. API_Key , application URL etc details.
All the configurations and test data are retrieved from the properties file.

**/com.abc.accuWeather/src/main/java/com/abc/accuWeather/API** package is created for adding all common components for API's
1. Created DTO package for adding serializtion and De-Serialzation of Request and Response body.
2. Created Util package, add utility classes only for API's.

**/com.abc.accuWeather/src/main/java/com/abc/accuWeather/core/util** package is created for common utils for both API as well as UI
1. ConfigReader class to read property file, here combinedConfiguration library is used to add all key values pairs of different properties files into one single variable.
2. GetURL class is added to maintain all the URLs of the application, i.e. based on test.environment set during run time, appropriate URL gets picked
3. SetBrowser class is to initialize chrome or firefox driver.

**/com.abc.accuWeather/src/main/java/com/abc/accuWeather/UI** package is created for adding all common components of UI
1. Created pages package to main all the locator information and page relate action methods.
2. Created core package to maintain WebElementWrapper class i.e. add common methods like SelectByVisibleText method which can be used accross multiple pages. Here dirver is initialized and PageFactory is initialzed.

Note : Every page i.e. HomePage class file should extend WebElementWrapper and implement isLoaded and getPageTitle method.

**/com.abc.accuWeather/src/test/java/com/abc/accuWeather/API/core** package is created for APIBaseTest.
1. APIBaseTest - It is an abstarct class and used for creating common methods like GET, PUT, POST and DELETE etc. It has setBaseUrl and setEndpoint as abstract methods.
2. BaseTest - It extends APIBaseTest. This class is created so that, user can create object of this class to access GET, PUT, POST common methods written in APIBaseTest class. Generally this BaseTest object is created in UI related test case to access common method written in APIBaseTest.

**/com.abc.accuWeather/src/test/java/com/abc/accuWeather/API/tests**
1. Here we write the Test scripts for APIs i.e I have written getWeatherInfo method to retrieve weather information using GetMethod. This java file should always extend BaseTest and implement SetBaseURL and SetEndpoint methods. 

**/com.abc.accuWeather/src/test/java/com/abc/accuWeather/UI/core**
1. Here we write TestNG Suite level or Test level @BeforeSuite or @AfterSuite method for initializing. The class calls the setBroswer method by passing @parameters broswer information to create chrome or firefox webDriver instance. The @Parameter information is defined in testng.xml file.

**/com.abc.accuWeather/src/test/java/com/abc/accuWeather/UI/pages/weatherTest**
1. Here we are writing the actual scripts of UI and adding all the necessary assetions.

**/com.abc.accuWeather/src/test/resources**
1. Here we maintain all the properties file and json files in the respective folder. We read all the data for test case execution from this package and folders. 





