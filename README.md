# OpKey Studio

* This contains three projects that can be opened by the latest Eclipse RCP package.
* You may google: 'Eclipse IDE for RCP and RAP Developers'

## Building the Project
* Fill the Selenium & Appium jars in the subfolders of OpKeyRuntimeLib, so that it starts getting compiling
* Next you will need to build the OpKeyStudioPlatform through the Maven Goal: clean compile install dependency:copy-dependencies
* Then you will need to build the OpKeyStudio project.
    * Open OpKeyStudio.product
	* Go to Contents Tab
	* Click on 'Add Required Plug-ins' button
	* Now go back to the Overview Tab and click on the Launch an Eclipse application option
	
## To Export the package:
* Go to the Launching TabCheck the 'Bundle JRE for this environment with the product' option
* Make sure you dont have the "-vm jdk/bin/javaw.exe" option set in Program Arguments
* Save the configuration
* Come back to the Overview Tab
* Inside the Exporting option click the 'Eclipse Product Export Wizard'
* Set RootDirectory to be 'OpKeyStudio'
* Click on Finish

***