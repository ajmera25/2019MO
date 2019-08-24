Feature: To test inter user file transfer

	@interplatform
  Scenario: To test file shared by user one from desktop browser to be received by user two on mobile
    Given "User A" logs in to Dropbox web application.
    When "User A" shares the "file" with dropbox "User B"
    And "User B" logs in to Dropbox mobile application.
    Then "User B" receives the "file" on "web"
     
  @desktopWeb
  Scenario: To test file shared by user one from desktop browser to be received by user two on mobile
    Given "User A" logs in to Dropbox web application.
    
	@mobileWeb
  Scenario: To test file shared by user one from desktop browser to be received by user two on mobile
    Given "User B" logs in to Dropbox mobile application.
    
    
    
    
    
