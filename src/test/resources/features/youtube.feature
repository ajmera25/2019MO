Feature: youtube video search

	@desktopWeb
  Scenario: To test youtube videos on desktop Web UI
    Given User open youtube url "https://www.youtube.com" in "mobile"
    When User Search for "step-inforum" in "desktop"
    And User open "step-in forum" channel
    And User navigate to "Videos" tab
    And User fetch the video name "apiFetchedVideoName" from the api call "http://54.169.34.162:5252/video"
    Then Locate the video "apiFetchedVideoName" fetch from the api call
    And Capture the screenshot
    
    When User click the video "apiFetchedVideoName"
    And Change the video quality to "360p"
    And Get the list of upcoming videos
    
    
    @nativemobileapp
  Scenario: To test youtube videos on native mobile apps
    Given User Search for "step-inforum" in mobile
    And User open "step-in forum" channel in mobile
    #And User navigate to "Videos" tab
    #And User fetch the video name "apiFetchedVideoName" from the api call "http://54.169.34.162:5252/video"
    #Then Locate the video "apiFetchedVideoName" fetch from the api call
    #And Capture the screenshot
    
    #When User click the video "apiFetchedVideoName"
    #And Change the video quality to "360p"
    #And Get the list of upcoming videos
   
    
    
