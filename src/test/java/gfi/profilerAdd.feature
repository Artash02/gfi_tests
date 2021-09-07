Feature: Check Profile Add Functionality

  Background:

    Given def fileBody = 'file:src/test/java/data/profile-add.json'
    Given def jsonFile = read(fileBody)
    Given url gfi_service_host + '/Profiler/Add'

  Scenario: Add Profile

    And request jsonFile
    When method post
    Then status 200
    * print response
    Given def DbUtils = Java.type('utils.DbUtils')
    Given def db = new DbUtils()
    Given def zcRequestId = response.zcRequestId
    Given def addRequestId = db.toUpperCase(zcRequestId)
    * print 'addRequestId' + addRequestId
    * print 'zcRequestId ' + response.zcRequestId
    Given def data = db.GetSqlRowData(zcRequestId)
    * print data
    And match data == {"ItemClass": "IPM.Note.WorkSite.Ems.Filed", "InternetMessageId": "internetMessageId", "AssignedDocumentId": "#string","AddRequestId": #(addRequestId),"OwnerEwsItemId": null,"Database": "gfidb","OwnerEmailAddressId": 1,"Id": 25,"From": "testgfi1_QA@imanage-prod.zerodemo.website","Processed": false,"DocumentInfo": "{\"id\":\"db000!10000.4\",\"documentNumber\":1200,\"name\":\"Mail subject\",\"extension\":\"msg\",\"class\":\"E-MAIL\",\"size\":55555,\"type\":\"string\",\"fileDate\":\"2021-08-30T07:42:51.959+00:00\",\"author\":\"string\",\"operator\":\"string\",\"version\":0,\"folderId\":\"string\",\"customs\":[{\"customId\":0,\"customValue\":\"string\"}]}","Location": "location_test"}