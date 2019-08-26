Feature: youtube video search

	@desktopWeb
  Scenario: To test youtube videos
    Given User open youtube url "https://www.youtube.com"
    When User Search for "step-inforum"
    And User open "step-in forum" channel
    And User navigate to "Videos" tab
    And User fetch the video name "apiFetchedVideoName" from the api call "http://54.169.34.162:5252/video"
    Then Locate the video "apiFetchedVideoName" fetch from the api call
    And Capture the screenshot
    
    When User click the video "apiFetchedVideoName"
    And Change the video quality to "360p"
    And Get the list of upcoming videos
   
    
    
